/**
 * Index.js; entry point into app
 */

'use strict';

// HAPI server config
const Hapi = require('hapi');

var server = new Hapi.Server();
server.connection({ port: process.env.PORT || 4000 });

require('./app/models/db');

server.bind({
});

server.register([require('inert'), require('vision'), require('hapi-auth-cookie')], err => {

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

  // load routes file
  server.route(require('./routes'));

  server.start((err) => {
    if (err) {
      throw err;
    }

    console.log('Server listening at: ', server.info.uri);
  });

});
