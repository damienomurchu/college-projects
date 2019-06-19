'use strict'

const User = require('../models/user');
const Joi = require('joi');
const bcrypt = require('bcrypt');

/* Accounts controller that manages account-related functionality & rendering of views */

// renders the welcome screen with signup & login forms
exports.welcome = {
  auth: false,
  handler: function (request, reply) {
    reply.view('welcome', { title: 'Welcome to myTweet' });
  },
};

// processes & validates new user signups
exports.signup = {
  auth: false,

  validate: {

    payload: {
      firstName: Joi.string().required(),
      lastName: Joi.string().required(),
      email: Joi.string().email().required(),
      password: Joi.string().required(),
    },

    failAction: function (request, reply, source, error) {
      reply.view('welcome', {
        title: 'Sign up error',
        signuperrors: error.data.details,
      }).code(400);
    },

    options: {
      abortEarly: false,
    },

  },

  handler: function (request, reply) {
    const user = new User(request.payload);

    // hash & salt password
    bcrypt.hash(user.password, 10).then(hash => {
      user.password = hash;
      user.save();
    }).then(newUser => {
      reply.redirect('/');
    }).catch(err => {
      reply.redirect('/');
    });
  },
};

// processes & validates user login details
exports.login = {
  auth: false,

  validate: {

    payload: {
      email: Joi.string().email().required(),
      password: Joi.string().required(),
    },

    failAction: function (request, reply, source, error) {
      reply.view('welcome', {
        title: 'Login error',
        loginerrors: error.data.details,
      }).code(400);
    },

    options: {
      abortEarly: false,
    },

  },

  handler: function (request, reply) {
    const user = request.payload;
    if ((user.email === 'admin@mytweet.ie') && (user.password === 'secret')) {
      request.cookieAuth.set({
        loggedIn: true,
        loggedInUser: user.email,
      });
      reply.redirect('/admin');
    } else {
      User.findOne({ email: user.email }).then(foundUser => {
        bcrypt.compare(user.password, foundUser.password).then(res => {
          // res == true
          if (res) {
            request.cookieAuth.set({
              loggedIn: true,
              loggedInUser: user.email,
            });
            reply.redirect('/timeline');
          } else {
            reply.redirect('/');
          }
        }).catch(err => {
          reply.redirect('/');
        });
      });
    }
  },
};

exports.logout = {
  auth: false,
  handler: function (request, reply) {
    reply.redirect('/');
  },
};

// renders the settings view that allows users update their details
exports.settings = {
  handler: function (request, reply) {
    var userEmail = request.auth.credentials.loggedInUser;
    User.findOne({ email: userEmail }).then(foundUser => {
      reply.view('settings', {
        title: 'Update your settings',
        user: foundUser,
      });
    });
  },
};

// handles the updated user settings from the settings view
exports.updatesettings = {

  validate: {

    payload: {
      firstName: Joi.string().required(),
      lastName: Joi.string().required(),
      email: Joi.string().email().required(),
      password: Joi.string().required(),
      avatar: Joi.any(),
    },

    failAction: function (request, reply, source, error) {
      var userEmail = request.auth.credentials.loggedInUser;
      User.findOne({ email: userEmail }).then(foundUser => {
        reply.view('settings', {
          title: 'Error updating settings',
          settingserrors: error.data.details,
          user: foundUser,
        }).code(400);
      });
    },

    options: {
      abortEarly: false,
    },
  },
  plugins: {
    disinfect: {
      disinfectQuery: true,
      disinfectParams: true,
      disinfectPayload: false,
    },
  },
  payload: {
    parse: true,
    output: 'data',
  },

  handler: function (request, reply) {
    const data = request.payload;
    User.findOne({ email: data.email }).then(foundUser => {
      foundUser.firstName = data.firstName;
      foundUser.lastName = data.lastName;
      foundUser.password = bcrypt.hashSync(data.password, 10);
      if (typeof data.avatar != 'undefined')
        foundUser.avatar.data = data.avatar;
      return foundUser;
    }).then(user => {
      user.save();
    }).then(user => {
      reply.redirect('timeline', {
        title: 'Settings updated',
      });
    });
  },
};

