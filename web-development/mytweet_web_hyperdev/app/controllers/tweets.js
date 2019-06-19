/*
 * tweets.js; controller to handle tweet-related functionality & views
 */

'use strict'

const Tweet = require('../models/tweet');
const User = require('../models/user');
const Hbs = require('handlebars');

// Handlebar helper to format a shortened date for the display of tweets
Hbs.registerHelper('shortDate', function (longDate) {
  let shortDate = longDate.toString();
  return shortDate.substring(4, 10) + ', ' + shortDate.substring(16, 21);
});

// renders the main global tweet timeline
exports.timeline = {
  auth: false,
  handler: function (request, reply) {
    const userid = request.params.user;
    console.log('userid: ' + userid);
    if (userid === undefined) {
      Tweet.find({}).populate('user').then(allTweets => {
        allTweets.sort(function (a, b) {
          return b.date - a.date;
        });

        reply.view('timeline', {
          title: 'Timeline',
          tweets: allTweets,
        });
      }).catch(err => {
        console.log('Error in rendering tweetlist timeline: ' + err);
        reply.redirect('/');
      });
    } else {
      Tweet.find({ user: userid }).populate('user').then(userTweets => {
        userTweets.sort(function (a, b) {
          return b.date - a.date;
        });

        reply.view('timeline', {
          title: 'Timeline',
          tweets: userTweets,
        });
      }).catch(err => {
        console.log('Error in rendering tweet timeline for user: ' + userid + '' + err);
        reply.redirect('/');
      });
    }

  },
};

// processes the sending of new tweets
exports.sendtweet = {
  handler: function (request, reply) {
    const data = request.payload;
    var tweeterEmail = request.auth.credentials.loggedInUser;

    User.findOne({ email: tweeterEmail }).then(foundUser => {
      let tweet = new Tweet(data);
      tweet.date = new Date();
      tweet.user = foundUser;
      return tweet;
    }).catch(err => {
      console.log('Error finding user in db: ' + err);
    }).then(tweet => {
      tweet.save();
    }).catch(err => {
      console.log('Error saving user to db: ' + err);
    }).then(newTweet => {
      reply.redirect('timeline');
    }).catch(err => {
      console.log('Failed to save tweet; redirecting to home.' + err);
      reply.redirect('/');
    });
  },
};

// deletes a selected list of tweets
exports.deletetweets = {
  handler: function (request, reply) {
    const tweets = Object.keys(request.payload);
    tweets.forEach(function (id) {
      Tweet.findByIdAndRemove(id, function (err) {
        if (err) throw err;
        console.log('Deleted id: ' + id);
      });
    });

    reply.redirect('/timeline');

  },
};
