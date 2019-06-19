'use strict'

const SyncHttpService = require('./sync-http-service');
const baseUrl = 'http://localhost:4000';

class MyTweetService {

  constructor(baseUrl) {
    this.httpService = new SyncHttpService(baseUrl);
  }

  login(user) {
    return this.httpService.setAuth('/api/users/authenticate', user);
  }

  logout() {
    this.httpService.clearAuth();
  }

  getUsers() {
    return this.httpService.get('/api/users');
  }

  getUser(id) {
    return this.httpService.get('/api/users/' + id);
  }

  createUser(newUser) {
    return this.httpService.post('/api/users', newUser);
  }

  deleteSomeUsers(id) {
    return this.httpService.delete('/api/users/' + id);
  }

  deleteAllUsers() {
    return this.httpService.delete('/api/users');
  }

  getTweet(id) {
    return this.httpService.get('/api/tweets/' + id);
  }

  getTweetsFromUser(userid) {
    return this.httpService.get('/api/users/' + userid + '/tweets');
  }

  getTweets() {
    return this.httpService.get('/api/tweets');
  }

  createTweet(newTweet) {
    return this.httpService.post('/api/tweets', newTweet);
  }

  deleteTweet(id) {
    return this.httpService.delete('/api/tweets/' + id);
  }

  deleteAllTweets() {
    return this.httpService.delete('/api/tweets');
  }

}

module.exports = MyTweetService;
