/*
 * routes.js; server routes file
 */

const Accounts = require('./app/controllers/accounts');
const Tweets = require('./app/controllers/tweets');
const Assets = require('./app/controllers/assets');
const Admin = require('./app/controllers/admin');
const Users = require('./app/controllers/users');

module.exports = [

  { method: 'GET', path: '/', config: Accounts.welcome },
  { method: 'POST', path: '/signup', config: Accounts.signup },
  { method: 'POST', path: '/login', config: Accounts.login },
  { method: 'GET', path: '/logout', config: Accounts.logout },

  { method: 'GET', path: '/timeline', config: Tweets.timeline },
  { method: 'GET', path: '/timeline/{user}', config: Tweets.timeline },
  { method: 'POST', path: '/sendtweet', config: Tweets.sendtweet },

  { method: 'GET', path: '/publicprofiles', config: Users.publicprofiles },

  { method: 'GET', path: '/settings', config: Accounts.settings },
  { method: 'POST', path: '/updatesettings', config: Accounts.updatesettings },

  { method: 'POST', path: '/deletetweets', config: Tweets.deletetweets },

  { method: 'GET', path: '/admin', config: Admin.main },

  { method: 'POST', path: '/admin/deleteusers', config: Admin.deleteusers },
  { method: 'POST', path: '/admin/deletealltweets', config: Admin.deletealltweets },
  { method: 'POST', path: '/admin/registeruser', config: Admin.registeruser },
  { method: 'POST', path: '/admin/deletetweets', config: Admin.deletetweets },

  {
    method: 'GET',
    path: '/{param*}',
    config: { auth: false },
    handler: Assets.servePublicDirectory,
  },

];
