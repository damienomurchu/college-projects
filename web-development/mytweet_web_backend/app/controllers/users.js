/*
 * user.js; controller that handles public profile-related functionality & views
 */

'use strict'

const User = require('../models/user');
//const Hbs = require('handlebars');

/*
// Handlebar helper to format a shortened date for the display of tweets
Hbs.registerHelper('isFollowing', function (loggedInUser, otherUserId) {
  loggedInUser.following.forEach(function (followedUser) {
    if (followedUser._id.equals(otherUserId))
      return true;
  });

  return false;
});
*/

function sortUsers(users) {
  users.sort(function (a, b) {
    return (a.lastName + a.firstName).localeCompare((b.lastName + b.firstName));
  });

  return users;
}

// renders the publicprofiles view of all users (except the user themselves)
exports.publicprofiles = {
  handler: function (request, reply) {
    var loggedInUserEmail = request.auth.credentials.loggedInUser;
    User.find({}).then(allUsers => {
      allUsers = allUsers.filter(user => {
        return user.email !== loggedInUserEmail;
      });
      return sortUsers(allUsers);
    }).then(allUsers => {
      reply.view('publicprofiles', {
        title: 'Timeline',
        loggedInUserEmail: loggedInUserEmail,
        users: allUsers,
      });
    }).catch(err => {
      console.log('Error in rendering public profiles: ' + err);
      reply.redirect('/');
    });
  },
};

exports.getavatar = {
  auth: false,

  handler: function (request, reply) {
    User.findOne({ _id: request.params._id }).then(user => {
      reply(user.avatar.data).type('image');
    }).catch(err => {
      console.log('Could not find avatar for user id: ' + request.params._id);
    });
  },
};

exports.followuser = {
  handler: function (request, reply) {
    User.findOne({ email: request.auth.credentials.loggedInUser }).then(loggedInUser => {
      User.findOne({ _id: request.params.id }).then(followeduser => {
        loggedInUser.following.push(followeduser);
        followeduser.followers.push(loggedInUser);

        followeduser.save().then(followeduser => {
          loggedInUser.save().then(followinguser => {
            reply.redirect('/publicprofiles');
          });
        });
      });
    });
  },
};
