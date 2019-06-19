'use strict'

const User = require('../models/user');
const Tweet = require('../models/tweet');
const Boom = require('boom');
const utils = require('./utils.js');

// finds & returns all tweets
exports.findAll = {
  auth: {
    strategy: 'jwt',
  },
  handler: function (request, reply) {
    Tweet.find({}).populate('user').then(tweets => {
      if (tweets != null) {
        reply(tweets);
      } else {
        reply(Boom.notFound('could not find any tweets'));
      }
    }).catch(err => {
      reply(Boom.notFound('could not find any tweets'));
    });
  },
};

// finds and returns a specific tweet by tweet id
exports.findById = {
  auth: false,
  handler: function (request, reply) {
    Tweet.findOne({ _id: request.params.id }).populate('user').then(tweet => {
      if (tweet != null) {
        reply(tweet);
      } else {
        reply(Boom.notFound('id not found'));
      }
    }).catch(err => {
      reply(Boom.notFound('id not found'));
    });
  },
};

// finds and returns tweets by user
exports.findByUser = {
  auth: false,
  handler: function (request, reply) {
    Tweet.find({ user: request.params.id }).then(usersTweets => {
      if (usersTweets != null) {
        reply(usersTweets);
      } else {
        reply(Boom.notFound('could not find any tweets for that user'));
      }
    }).catch(err => {
      reply(Boom.notFound('could not find any tweets for that user'));
    });
  },
};

// find and returns all tweets of the user & those they follow (finds user by email address)
exports.personalTimeline = {
  auth: false,
  handler: function (request, reply) {
    User.findOne({ _id: request.params.id }).populate('following').then(user => {
      let tweetUsers = user.following;
      tweetUsers.push(user);
      Tweet.find({ user: { $in: tweetUsers } }).populate('user').then(timelineTweets => {
        if (timelineTweets != null) {
          console.log(timelineTweets);
          reply(timelineTweets);
        } else {
          reply(Boom.notFound('could not find that users personalised timeline of tweets'));
        }
      }).catch(err => {
        reply(Boom.notFound('could not find that users personalised timeline of tweets'));
      });
    });
  },
};

//  sends a tweet
exports.sendTweet = {

  auth: {
    strategy: 'jwt',
  },

  payload: {
    parse: true,
    output: 'data',
  },

  handler: function (request, reply) {
    const data = request.payload;
    console.log('picdata from payload: ' + data.picture);
    let senderid = utils.getUserIdFromRequest(request);

    User.findOne({ _id: senderid }).then(sender => {
      let tweet = new Tweet(data);
      tweet.user = sender;
      //if (data.picture.length)
      //if (Object.keys(data.picture).length)
      //tweet.picture = data.picture;
      return tweet;
      console.log('returned tweet: ' + tweet);
    }).catch(err => {
      console.log('Error creating tweet from data: ' + err);
    }).then(tweet => {
      tweet.save();
      if (tweet != null) {
        reply(tweet).code(201);
      } else {
        reply(Boom.notFound('could not add tweet'));
      }

      console.log('saved tweet: ' + tweet + '\n');
    }).catch(err => {
      reply(Boom.badImplementation('error creating tweet'));
    });
  },
};

// deletes one or more tweets
exports.deleteOneTweet = {
  auth: {
    strategy: 'jwt',
  },
  handler: function (request, reply) {
    Tweet.remove({ _id: request.params.id }).then(tweet => {
      reply(tweet).code(204);
    }).catch(err => {
      reply(Boom.notFound('id not found'));
    });
  },
};


// accepts a jsonarray of tweet ids, and deletes them
exports.deleteSomeTweets = {
  auth: {
    strategy: 'jwt',
  },
  handler: function (request, reply) {
    const tweetids = JSON.parse(request.payload);

    Tweet.remove({ _id: { $in: tweetids } }).then(err => {
      reply().code(204);
    }).catch(err => {
      reply(Boom.badImplementation('error removing tweets'));
    });
  },
};

// deletes all tweets
exports.deleteAllTweets = {
  auth: {
    strategy: 'jwt',
  },
  handler: function (request, reply) {
    Tweet.remove({}).then(err => {
      reply().code(204);
      console.log('All tweets removed!');
    }).catch(err => {
      reply(Boom.badImplementation('error removing tweets'));
    });
  },
};
