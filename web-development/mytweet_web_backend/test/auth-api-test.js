'use strict';

const assert = require('chai').assert;
const MyTweetService = require('./mytweet-service');
const testfixtures = require('./testfixtures.json');
const utils = require('../app/api/utils.js');

suite('Auth API tests', function () {

  let users = testfixtures.users;
  let newTweet = testfixtures.newTweet;

  const myTweetService = new MyTweetService(testfixtures.myTweetService);

  test('login-logout', function () {
    myTweetService.createUser(users[0]);

    /*
    let returnedTweet = myTweetService.createTweet(newTweet);
    assert.isNull(returnedTweet);

    const response = myTweetService.login(users[0]);
    returnedTweet = myTweetService.createTweet(newTweet);
    assert.isNotNull(returnedTweet);

    myTweetService.deleteAllUsers();
    myTweetService.logout();
    returnedTweet = myTweetService.createTweet(newTweet);
    assert.isNull(returnedTweet);*/

    var returnedUsers = myTweetService.getUsers();
    assert.isNull(returnedUsers);

    const response = myTweetService.login(users[0]);
    returnedUsers = myTweetService.getUsers();
    assert.isNotNull(returnedUsers);

    myTweetService.deleteAllUsers();
    myTweetService.logout();
    returnedUsers = myTweetService.getUsers();
    assert.isNull(returnedUsers);
  });
});
