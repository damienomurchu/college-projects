/**
 * Index.js; entry point into app
 */

'use strict';

// HAPI server config
const Hapi = require('hapi');
const utils = require('./app/api/utils.js');

var server = new Hapi.Server();
server.connection({ port: process.env.PORT || 4000 });

require('./app/models/db');

server.bind({
});

server.register([require('inert'), require('vision'), require('hapi-auth-cookie'), require('hapi-auth-jwt2')], err => {

  // registers disinfect
  server.register({
    register: require('disinfect'),
    options: {
      disinfectQuery: true,
      disinfectParams: true,
      disinfectPayload: true,
    },
  }, (err) => {
    //
  });

  if (err) {
    throw err;
  }

  // handlebars config
  server.views({
    engines: {
      hbs: require('handlebars'),
    },
    relativeTo: __dirname,
    path: './app/views',
    layoutPath: './app/views/layouts',
    partialsPath: './app/views/partials',
    layout: true,
    isCached: false,
  });

  // authentication settings
  server.auth.strategy('standard', 'cookie', {
    password: 'supersecretmegapasswordnobodywilleverguess',
    cookie: 'mytweet-cookie',
    isSecure: false,
    ttl: 24 * 60 * 60 * 1000,
  });

  // implemented authentication choice
  server.auth.default({
    strategy: 'standard',
  });

  // jwt tokens for api
  server.auth.strategy('jwt', 'jwt', {
    key: 'secretpasswordnotrevealedtoanyone',
    validateFunc: utils.validate,
    verifyOptions: { algorithms: ['HS256'] },
  });

  // load routes file
  server.route(require('./routes'));
  server.route(require('./routes_api'));

  server.start((err) => {
    if (err) {
      throw err;
    }

    console.log('Server listening at: ', server.info.uri);
  });

});
