/*
 * user.js; model representing registered users of the site
 */

'use strict'

const mongoose = require('mongoose');

// mongoose db user schema
const userSchema = mongoose.Schema({
  firstName: String,
  lastName: String,
  email: String,
  password: String,
});

const User = mongoose.model('User', userSchema);
module.exports = User;
