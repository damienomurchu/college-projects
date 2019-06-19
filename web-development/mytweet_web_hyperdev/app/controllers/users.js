/*
 * user.js; controller that handles public profile-related functionality & views
 */

'use strict'

const User = require('../models/user');

// renders the publicprofiles view of all users
exports.publicprofiles = {
  auth: false,
  handler: function (request, reply) {
    User.find({}).then(allUsers => {
      allUsers.sort(function (a, b) {
        return (a.lastName + a.firstName).localeCompare((b.lastName + b.firstName));
      });

      reply.view('publicprofiles', {
        title: 'Timeline',
        users: allUsers,
      });
    }).catch(err => {
      console.log('Error in rendering public profiles: ' + err);
      reply.redirect('/');
    });
  },
};
