'use strict'

const User = require('../models/user');
const Boom = require('boom');
const utils = require('./utils.js');
const bcrypt = require('bcrypt');

exports.authenticate = {
  auth: false,
  handler: function (request, reply) {
    const user = request.payload;
    User.findOne({ email: user.email }).then(foundUser => {
      bcrypt.compare(user.password, foundUser.password).then(res => {
        // res == true
        if (res) {
          const token = utils.createToken(foundUser);
          reply({ success: true, token: token }).code(201);
        } else {
          reply({ success: false, message: 'Authentication failed. User not found.' }).code(201);
        }
      }).catch(err => {
        reply(Boom.notFound('internal db failure'));
      });
    });
  },
};

// creates a new users
exports.registerUser = {
  auth: false,
  handler: function (request, reply) {
    const user = new User(request.payload);
    bcrypt.hash(user.password, 10).then(hash => {
      user.password = hash;
      user.save();
    }).then(newUser => {
      reply(newUser).code(201);
    }).catch(err => {
      reply(Boom.badImplementation('error creating User'));
    });
  },
};

// deletes one or more users
exports.deleteOneUser = {
  auth: {
    strategy: 'jwt',
  },
  handler: function (request, reply) {
    User.remove({ _id: request.params.id }).then(user => {
      reply(user).code(204);
    }).catch(err => {
      reply(Boom.notFound('id not found'));
    });
  },
};

/*
// deletes one or more users
exports.deleteSomeUsers = {
  auth: {
    strategy: 'jwt',
  },
  handler: function (request, reply) {
    const userids = request.params.ids;
    userids.forEach(userid => {
      Tweet.remove({ _id: userid });
    }).then(deletedTweets => {
      reply().code(204);
    }).catch(err => {
      reply(Boom.badImplementation('error removing users'));
    });
  },
};
*/

// deletes all users
exports.deleteAllUsers = {
  auth: {
    strategy: 'jwt',
  },
  handler: function (request, reply) {
    User.remove({}).then(err => {
      reply().code(204);
      console.log('All users removed!');
    }).catch(err => {
      reply(Boom.badImplementation('error removing Users'));
    });
  },
};

// finds and returns all users
exports.findAll = {
  auth: {
    strategy: 'jwt',
  },
  handler: function (request, reply) {
    User.find({}).then(users => {
      if (users != null) {
        reply(users);
      } else {
        reply(Boom.notFound('could not find any users'));
      }
    }).catch(err => {
      reply(Boom.notFound('could not find any users'));
    });
  },
};

// finds and returns user(s) that matches id
exports.findById = {
  auth: false,
  handler: function (request, reply) {
    User.findOne({ _id: request.params.id }).then(user => {
      if (user != null) {
        reply(user);
      } else {
        reply(Boom.notFound('id not found'));
      }
    }).catch(err => {
      reply(Boom.notFound('id not found'));
    });
  },
};
