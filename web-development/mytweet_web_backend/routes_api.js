const UsersApi = require('./app/api/usersapi');
const TweetsApi = require('./app/api/tweetsapi');

module.exports = [

  { method: 'POST', path: '/api/users/authenticate', config: UsersApi.authenticate },

  { method: 'GET', path: '/api/users/{id}', config: UsersApi.findById },
  { method: 'GET', path: '/api/users', config: UsersApi.findAll },
  { method: 'POST', path: '/api/users', config: UsersApi.registerUser },
  { method: 'DELETE', path: '/api/users/{id}', config: UsersApi.deleteOneUser },
  { method: 'DELETE', path: '/api/users', config: UsersApi.deleteAllUsers },

  { method: 'GET', path: '/api/tweets/{id}', config: TweetsApi.findById },
  { method: 'GET', path: '/api/users/{id}/tweets', config: TweetsApi.findByUser },
  { method: 'GET', path: '/api/users/{id}/tweet-timeline', config: TweetsApi.personalTimeline },
  { method: 'GET', path: '/api/tweets', config: TweetsApi.findAll },
  { method: 'POST', path: '/api/tweets', config: TweetsApi.sendTweet },
  { method: 'DELETE', path: '/api/tweets/{id}', config: TweetsApi.deleteOneTweet },
  { method: 'DELETE', path: '/api/tweets', config: TweetsApi.deleteAllTweets },
  { method: 'POST', path: '/api/tweets/delete-some', config: TweetsApi.deleteSomeTweets },

];
