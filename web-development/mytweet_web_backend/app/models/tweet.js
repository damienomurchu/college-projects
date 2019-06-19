/*
 * tweet.js; model representing tweets sent by users
 */

const mongoose = require('mongoose');
const User = require('./user');

// mongoose tweet schema
const tweetSchema = mongoose.Schema({
  date: Date,
  content: String,
  user: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'User',
  },
  picture: {
    data: Buffer,
    contentType: String,
  },
});

const Tweet = mongoose.model('Tweet', tweetSchema);
module.exports = Tweet;
