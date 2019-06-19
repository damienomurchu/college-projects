/*
 * db.js; db handler to manage all db-related functionality & settings
 */

'use strict'

const mongoose = require('mongoose');
mongoose.Promise = global.Promise;

//let dbURI = 'mongodb://localhost/mytweet';
let dbURI = 'mongodb://mytweet-user:secret@ds017776.mlab.com:17776/mytweet-web';

if (process.env.NODE_ENV === 'production') {
  dbURI = process.env.MONGOLAB_URI;
}

mongoose.connect(dbURI);

// manage successful db connection
mongoose.connection.on('connected', function () {
  console.log('Mongoose connected to: ' + dbURI);
});

// manage seeding of db
mongoose.connection.on('connected', function () {
  console.log('Mongoose connected to ' + dbURI);
  if (process.env.NODE_ENV != 'production') {
    var seeder = require('mongoose-seeder');
    const data = require('./dbseed.json');
    const Tweet = require('./tweet');
    const User = require('./user');
    seeder.seed(data, { dropDatabase: false, dropCollections: true }).then(dbData => {
      console.log('preloading Test Data');
      console.log(dbData);
    }).catch(err => {
      console.log(error);
    });
  }
});

// manage db connection error
mongoose.connection.on('error', function (err) {
  console.log('Mongoose connection error: ' + err);
});

// manage db disconnection
mongoose.connection.on('disconnected', function () {
  console.log('Mongoose disconnected');
});
