/*
 * admin.js; controller that manages admin-related functionality & rendering of views
 */

'use strict'

const Tweet = require('../models/tweet');
const User = require('../models/user');
const Joi = require('joi');

// renders the main admin page
exports.main = {
  handler: function (request, reply) {
    User.find({}).then(allUsers => {
      Tweet.find({}).populate('user').then(allTweets => {
        allTweets.sort(function (a, b) {
          return b.date - a.date;
        });

        reply.view('admin', {
          title: 'Admin settings',
          users: allUsers,
          tweets: allTweets,
          avgTweets: (allTweets.length / allUsers.length).toString().substring(0, 3),
        });
      });
    });
  },
};

// processes a list of users & deletes all their tweets
exports.deletealltweets = {
  handler: function (request, reply) {
    const data = request.payload.users;
    let users = [];
    if (request.payload.users.constructor != Array) {
      users.push(data);
    } else {
      users = data;
    }

    users.forEach(function (userId) {
      Tweet.find({ user: userId }).then(foundTweets => {
        foundTweets.forEach(function (id) {
          Tweet.findByIdAndRemove(id, function (err) {
            if (err) throw err;
            console.log('Deleted id: ' + id);
          });
        });
      });
    });

    reply.redirect('/admin');
  },
};

// processes & deletes a list of users
exports.deleteusers = {
  handler: function (request, reply) {
    const data = request.payload.users;
    var users = [];
    if (request.payload.users.constructor != Array) {
      users.push(data);
    } else {
      users = data;
    }

    Tweet.find({ user: users }).then(foundTweets => {
      // delete tweets individually
      foundTweets.forEach(function (id) {
        Tweet.findByIdAndRemove(id, function (err) {
          if (err) throw err;
        });
      });

      return null;
    }).then(nothing => {
      // delete user(s)
      console.log(users);
      users.forEach(function (userId) {
        User.findByIdAndRemove(userId, function (err) {
          if (err) throw err;
        });

        return null;
      });
    }).then(nothing => {
      reply.redirect('/admin', {
        title: 'Users deleted',
      });
    });
  },

};

// registers a new mytweet user
exports.registeruser = {
  auth: false,

  validate: {

    payload: {
      firstName: Joi.string().required(),
      lastName: Joi.string().required(),
      email: Joi.string().email().required(),
      password: Joi.string().required(),
    },

    failAction: function (request, reply, source, error) {
      reply.redirect('/admin', {
        title: 'Failed to signup user',
        signuperrors: error.data.details,
      }).code(400);
    },

    options: {
      abortEarly: false,
    },

  },

  handler: function (request, reply) {
    const user = new User(request.payload);

    user.save().then(newUser => {
      reply.redirect('/admin');
    }).catch(err => {
      reply.redirect('/admin', {
        title: 'Failed to register user',
      });
    });
  },
};

// processes & deletes a list of specific tweets
exports.deletetweets = {
  handler: function (request, reply) {
    const tweets = Object.keys(request.payload);
    tweets.forEach(function (id) {
      Tweet.findByIdAndRemove(id, function (err) {
        if (err) throw err;
        console.log('Deleted id: ' + id);
      });
    });

    reply.redirect('/admin');

  },
};
