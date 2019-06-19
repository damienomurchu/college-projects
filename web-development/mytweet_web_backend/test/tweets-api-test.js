'use strict';

const assert = require('chai').assert;
const MyTweetService = require('./mytweet-service');
const testfixtures = require('./testfixtures.json');
const _ = require('lodash');

suite('Tweet API tests', function () {

  let users = testfixtures.users;
  let tweets = testfixtures.tweets;
  let newTweet = testfixtures.newTweet;

  const myTweetService = new MyTweetService(testfixtures.myTweetService);

  beforeEach(function () {
    myTweetService.createUser(users[0]);
    myTweetService.login(users[0]);
    myTweetService.deleteAllTweets();
  });

  afterEach(function () {
    myTweetService.deleteAllUsers();
    myTweetService.deleteAllTweets();
    myTweetService.logout();
  });

  test('create a tweet', function () {
    const returnedTweet = myTweetService.createTweet(newTweet);
    console.log('returned tweet: ' + returnedTweet);
    assert(_.some([returnedTweet], newTweet), 'returnedTweet must be a superset of newTweet');
    assert.isDefined(returnedTweet._id);
  });

  test('get tweet', function () {
    const c1 = myTweetService.createTweet(newTweet);
    const c2 = myTweetService.getTweet(c1._id);
    assert(_.some([c2], c1), 'returnedTweet must be a superset of newTweet');
    //assert.deepEqual(c1, c2);
  });

  test('get invalid tweet', function () {
    const c1 = myTweetService.getTweet('1234');
    assert.isNull(c1);
    const c2 = myTweetService.getTweet('012345678901234567890123');
    assert.isNull(c2);
  });

  test('delete a tweet', function () {
    const c = myTweetService.createTweet(newTweet);
    assert(myTweetService.getTweet(c._id) != null);
    myTweetService.deleteTweet(c._id);
    assert(myTweetService.getTweet(c._id) == null);
  });

  test('get all tweets', function () {
    for (let c of tweets) {
      myTweetService.createTweet(c);
    }

    const allTweets = myTweetService.getTweets();
    assert.equal(allTweets.length, tweets.length);
  });

  test('get tweets detail', function () {
    for (let c of tweets) {
      myTweetService.createTweet(c);
    }

    const allTweets = myTweetService.getTweets();
    for (var i = 0; i < tweets.length; i++) {
      //assert(_.some([allTweets[i]], tweets[i]), 'tweets[i] must be a subset of allTweets[i]');
      assert.equal(allTweets[i].date, tweets[i].date);
      assert.equal(allTweets[i].content, tweets[i].content);
    }
  });

  test('get all tweets empty', function () {
    const allTweets = myTweetService.getTweets();
    assert.equal(allTweets.length, 0); // 1 tweet created in before
  });

});
