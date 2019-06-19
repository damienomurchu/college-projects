# MyTweet #

MyTweet is a Node.js web app where users can signup, login, send and view tweets. Admin functionality is also provided, whereby an admin user can perform various operations on site users & data.

## Live app version(s) ##

Hosted version(s) of the MyTweet app may be seen here: 

* Heroku : https://mytweet-murchu-final.herokuapp.com/

## Dependencies ##

MyTweet was developed using node.js v6.6.0, and npm v3.10.3. In addition a number of npm modules were used in the development of MyTweet, and these are: 

* handlebars: 4.0.5
* hapi: 15.1.1
* hapi-auth-cookie: 6.1.1
* inert: 4.0.2
* joi: 9.2.0
* mongoose: 4.6.5
* mongoose-seeder: 1.2.1
* vision: 4.1.0

## Accessing app ##

Access to the app is via the email and password selected during signup. Sample users have been seeded into the database, and simple access to the app can be gained by the login:

* username: homer@simpson.com
* password: secret

As admin functionality has been provided, and users may be deleted, if this user is not available, simply signup a new user & login this way.

Admin functionality may always be accessed via the credentials:

* username: admin@mytweet.ie
* password: secret

Note: full user persistence has only been provided via a mongo db from story 07 (st_07) onwards, so previous story versions may require users to be re-signed up if the app is restarted, as persistence is only provided in the form of a basic in-memory list with these story versions.

## Branches/ Structure ##

Repo follows a gitflow-like branch & merging structure, with the following branch types:

* features : ft1_xxx, ft2_xxx, etc
* dev : finished feature branches are merged back to dev
* master : finished development on dev is merged back to master

All work is done on individual feature branches, with finished features merged back to dev, and dev merged back to master when finished.

## Story Tags ##

Development of the app has been via the below functionality stories:

* st_00 : Adds baseline project infrastructure
* st_01 : Adds welcome view & func
* st_02 : Adds tweet timeline
* st_03 : Adds signup & login func
* st_04 : Adds tweeting func
* st_05 : Adds settings func
* st_06 : Adds session mgt & cookies
* st_07 : Adds mongo persistence
* st_08 : Adds tweeting counter
* st_09 : Adds validation to forms
* st_10 : Adds delete tweets func
* st_11 : Adds admin-related func
* st_12 : Refactors UI/ appearance
* st_13 : Adds database seeding
* st_14 : Corrects sorting & dates of tweets