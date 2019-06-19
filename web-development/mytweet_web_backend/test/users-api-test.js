'use strict';

const assert = require('chai').assert;
const MyTweetService = require('./mytweet-service');
const testfixtures = require('./testfixtures.json');
const _ = require('lodash');

suite('User API tests', function () {

  let users = testfixtures.users;
  let newUser = testfixtures.newUser;

  const myTweetService = new MyTweetService(testfixtures.myTweetService);

  beforeEach(function () {
    //myTweetService.deleteAllUsers();
    myTweetService.createUser(users[0]);
    myTweetService.login(users[0]);
  });

  afterEach(function () {
    myTweetService.deleteAllUsers();
    myTweetService.logout();
  });

  test('create a user', function () {
    const returnedUser = myTweetService.createUser(newUser);
    assert(_.some([returnedUser], newUser), 'returnedUser must be a superset of newUser');
    assert.isDefined(returnedUser._id);
  });

  test('get user', function () {
    const c1 = myTweetService.createUser(newUser);
    const c2 = myTweetService.getUser(c1._id);
    assert.deepEqual(c1, c2);
  });

  test('get invalid user', function () {
    const c1 = myTweetService.getUser('1234');
    assert.isNull(c1);
    const c2 = myTweetService.getUser('012345678901234567890123');
    assert.isNull(c2);
  });

  test('delete a user', function () {
    const c = myTweetService.createUser(newUser);
    assert(myTweetService.getUser(c._id) != null);
    myTweetService.deleteSomeUsers([c._id]);
    assert(myTweetService.getUser(c._id) == null);
  });

  test('get all users', function () {
    for (let c of users) {
      myTweetService.createUser(c);
    }

    const allUsers = myTweetService.getUsers();
    assert.equal(allUsers.length, users.length + 1);
  });

  test('get users detail', function () {
    for (let c of users) {
      myTweetService.createUser(c);
    }

    const allUsers = myTweetService.getUsers();
    for (var i = 0; i < users.length; i++) {
      assert(_.some([allUsers[i+1]], users[i]), 'returnedUser must be a superset of newUser');
    }
  });

  test('get all users empty', function () {
    const allUsers = myTweetService.getUsers();
    assert.equal(allUsers.length, 1); // 1 user created in before
  });

});
