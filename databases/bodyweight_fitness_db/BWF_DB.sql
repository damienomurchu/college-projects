/* 
 * @Course      WIT ICT Skills, Higher Diploma Computer Science
 * @CA          Databases, 2016
 * @Lecturer    Brenda Mullally
 *
 * @Student     Damien Murphy (96406801)
 * 
 * @File        BWF_DB.sql
 * @Description All SQL statements used to create, populate and query database.
 *              Application Express Workspace: IE_A176_SQL01_S14
 *
*/

/* Create USERs table in db */
CREATE TABLE users (
	username     VARCHAR2(20) NOT NULL,
	name         VARCHAR2(20),
	dateofbirth  DATE NOT NULL,
	gender       VARCHAR2(6) NOT NULL,
	height       NUMBER(3) NOT NULL CHECK (height < 300),
	country      VARCHAR2(30),
CONSTRAINT users_username_pk PRIMARY KEY (username)
);

/* Create LOGINSs table in db */
CREATE TABLE logins (
	email        VARCHAR2(40) NOT NULL,
	password     VARCHAR2(20) NOT NULL,
  username     VARCHAR2(20) NOT NULL UNIQUE,
CONSTRAINT lgn_dtl_email_pk PRIMARY KEY (email),
CONSTRAINT lgn_dtl_username_fk FOREIGN KEY (username) 
  REFERENCES users(username)
);

/* Create MEALs table in db */
CREATE TABLE meals (
	id           INT NOT NULL,
	mealdate     DATE NOT NULL,
	mealtime     NUMBER(4,2) NOT NULL,
	name         VARCHAR2(20) NOT NULL,
  username     VARCHAR2(20) NOT NULL,
CONSTRAINT meal_id_pk PRIMARY KEY (id),
CONSTRAINT meal_username_fk FOREIGN KEY (username) 
  REFERENCES users(username)
);

/* Create FOOD_ITEMs table in db */
CREATE TABLE food_items (
	id           INT NOT NULL,
	foodname     VARCHAR2(20) NOT NULL,
	portionsize  VARCHAR2(15) NOT NULL,
	calories     NUMBER(4) NOT NULL,
	proteingrams NUMBER(3) NOT NULL,
	carbgrams    NUMBER(3) NOT NULL,
	fatgrams     NUMBER(3) NOT NULL,
	meal_id      NUMBER NOT NULL,
CONSTRAINT food_item_id_pk PRIMARY KEY (id),
CONSTRAINT food_item_id_fk FOREIGN KEY (meal_id) 
  REFERENCES meals(id)
);

/* Create DIET_GOALs table in db */
CREATE TABLE diet_goals (
  id           INT NOT NULL,
	calories     NUMBER(4) NOT NULL,
	proteingrams NUMBER(3) NOT NULL,
	carbgrams    NUMBER(3) NOT NULL,
	fatgrams     NUMBER(3) NOT NULL,
	username     VARCHAR2(20) NOT NULL UNIQUE,
CONSTRAINT diet_goal_id_pk PRIMARY KEY (id),
CONSTRAINT diet_goal_username_fk FOREIGN KEY (username) 
  REFERENCES users(username)
);

/* Create WORKOUTs table in db */
CREATE TABLE workouts (
  id           INT NOT NULL,
	workoutdate  DATE NOT NULL,
	workoutname  VARCHAR2(20) NOT NULL,
	username     VARCHAR2(20) NOT NULL,
CONSTRAINT workout_id_pk PRIMARY KEY (id),
CONSTRAINT workout_username_fk FOREIGN KEY (username) 
  REFERENCES users(username)
);

/* Create CARDIO_EXERCISE table in db */
CREATE TABLE cardio_exercise (
  id           INT NOT NULL,
	exercisename VARCHAR2(30) NOT NULL,
	time         NUMBER(3,2),
	distance     NUMBER(3),
	intensity    NUMBER(1),
	note         VARCHAR2(150),
	workout_id   NUMBER NOT NULL,
CONSTRAINT cardio_id_pk PRIMARY KEY (id),
CONSTRAINT cardio_exc_workout_id_fk FOREIGN KEY (workout_id) 
  REFERENCES workouts(id)
);

/* Create RESISTANCE_EXERCISE table in db */
CREATE TABLE resistance_exercise (
  id           INT NOT NULL,
  exercisename VARCHAR2(30) NOT NULL,
  setnumber    NUMBER(1) NOT NULL,
  repetitions  NUMBER(2) NOT NULL,
  weight       NUMBER(3),
  restperiod   NUMBER(3,2),
  intensity    NUMBER(1),
  note         VARCHAR2(150),
  workout_id   NUMBER NOT NULL,
CONSTRAINT resistance_id_pk PRIMARY KEY (id),
CONSTRAINT resist_exc_workout_id_fk FOREIGN KEY (workout_id) 
  REFERENCES workouts(id)
);

/* Create BODY_MEASUREMENTs table in db */
CREATE TABLE body_measurements (
  id           INT NOT NULL,
	daterecorded DATE NOT NULL,
	bodyweight   NUMBER(3),
	bodyfat      NUMBER(2,2),
	username     VARCHAR2(20) NOT NULL,
CONSTRAINT body_record_id_pk PRIMARY KEY (id),
CONSTRAINT body_record_username_fk FOREIGN KEY (username) 
  REFERENCES users(username)
);

/* Create BODY_GOALs table in db */
CREATE TABLE body_goals (
  id           INT NOT NULL,
  bodyweight   NUMBER(3),
  bodyfat      NUMBER(2,2), 
  username     VARCHAR2(20) NOT NULL UNIQUE,
CONSTRAINT body_goal_id_pk PRIMARY KEY (id),
CONSTRAINT bdy_gl_username_fk FOREIGN KEY (username) 
  REFERENCES users(username)
);

/* Insert statements to insert sample data into each db table */

/* Insert sample values into USERs table */
INSERT ALL
  INTO users VALUES ('smck', 'stella', '01-Jan-1994', 'female', 170, 'Ireland')
  INTO users VALUES ('jjo', 'john', '01-Jan-1983', 'male', 185, 'Ireland')
  INTO users VALUES ('arido', 'ari', '01-Jan-1991', 'female', 155, 'Ireland')
  INTO users VALUES ('mfoy', 'mike', '01-Jan-1987', 'male', 180, 'Ireland')
  INTO users VALUES ('grig', 'greg', '01-Jan-1983', 'male', 172, 'Ireland')
  INTO users VALUES ('dmo', 'damien', '01-Jan-1979', 'male', 175, 'Ireland')
SELECT * FROM dual;

/* Insert sample values into LOGINs table */
INSERT ALL
  INTO logins VALUES ('stella@mckay.com', 'passw', 'smck')
  INTO logins VALUES ('john@talljohn.com', 'asswo', 'jjo')
  INTO logins VALUES ('ari@downey.com', 'sswor', 'arido')
  INTO logins VALUES ('mike@foy.com', 'sword', 'mfoy')
  INTO logins VALUES ('greg@obv.com', 'wordp', 'grig')
  INTO logins VALUES ('damien@murphy.com', 'ordpa', 'dmo')
SELECT * FROM dual;

/* Insert sample values into MEALs table */
INSERT ALL
  INTO meals VALUES (1, '18/Mar/2016', 13.15, 'Lunch', 'mfoy')
  INTO meals VALUES (2, '23/Mar/2016', 18.00, 'Dinner', 'smck')
  INTO meals VALUES (3, '23/Mar/2016', 16.00, 'Snack', 'arido')
  INTO meals VALUES (4, '23/Mar/2016', 19.30, 'Supper', 'arido')
  INTO meals VALUES (5, '24/Mar/2016', 13.45, 'Lunch', 'dmo')
  INTO meals VALUES (6, '24/Mar/2016', 16.20, 'Snack', 'dmo')
SELECT * FROM dual;

/* Insert sample values into FOOD_ITEMs table */
INSERT ALL
  INTO food_items VALUES (1, 'Bolognese Sauce', '1.5 cups', 325, 40, 15, 12, 1)
  INTO food_items VALUES (2, 'Pasta', '50g', 350, 9, 35, 19, 1)
  INTO food_items VALUES (3, 'Green Salad', '2 cups', 70, 4, 12, 1, 2)
  INTO food_items VALUES (4, 'Pork Chop', '2 fried', 325, 40, 5, 16, 2)
  INTO food_items VALUES (5, 'Baby Potatoes', '1 cup', 250, 11, 40, 5, 2)
  INTO food_items VALUES (6, 'Apple', '1 large', 100, 3, 20, 1, 3)
  INTO food_items VALUES (7, 'Sweet Potato', '1 large', 275, 13, 45, 5, 4)
  INTO food_items VALUES (8, 'Salmon', '1 medium fillet', 225, 35, 0, 9, 4)
  INTO food_items VALUES (9, 'Chicken Curry', '1.5 cups', 325, 30, 40, 5, 5)
  INTO food_items VALUES (10, 'Rice', '1 cup', 350, 12, 55, 9, 5)
  INTO food_items VALUES (11, 'Banana', '1 large', 130, 5, 25, 1, 6)
SELECT * FROM dual;

/* Insert sample values into DIET_GOALs table */
INSERT ALL
  INTO diet_goals VALUES (1, 1800, 80, 258, 50, 'smck')
  INTO diet_goals VALUES (2, 2600, 120, 373, 70, 'jjo')
  INTO diet_goals VALUES (3, 1800, 85, 253, 50, 'arido')
  INTO diet_goals VALUES (4, 2650, 130, 364, 75, 'mfoy')
  INTO diet_goals VALUES (5, 2550, 120, 360, 70, 'grig')
  INTO diet_goals VALUES (6, 2700, 140, 366, 75, 'dmo')
SELECT * FROM dual;

/* Insert sample values into WORKOUTs table */
INSERT ALL
  INTO workouts VALUES (1, '19/Mar/2016', 'Bodyweight routine', 'dmo')
  INTO workouts VALUES (2, '20/Mar/2016', 'Run', 'smck')
  INTO workouts VALUES (3, '21/Mar/2016', 'Spin class', 'mfoy')
  INTO workouts VALUES (4, '23/Mar/2016', 'Olympic lifting', 'arido')
  INTO workouts VALUES (5, '23/Mar/2016', 'Swim', 'grig')
  INTO workouts VALUES (6, '24/Mar/2016', 'Yoga', 'dmo')
  INTO workouts VALUES (7, '24/Mar/2016', 'Walk', 'arido')
  INTO workouts VALUES (8, '24/Mar/2016', 'Run', 'smck')
SELECT * FROM dual;

/* Insert sample values into CARDIO_EXERCISE table */
INSERT ALL
  INTO cardio_exercise VALUES (1, 'Running', 0.30, 4, 6, null, 2)
  INTO cardio_exercise VALUES (2, 'Spinning', 0.50, 8.5, null, null, 3)
  INTO cardio_exercise VALUES (3, 'Swimming', 0.45, 7, null, null, 5)
  INTO cardio_exercise VALUES (4, 'Yoga', 1.00, null, null, null, 6)
  INTO cardio_exercise VALUES (5, 'Walking', 0.50, 2.5, null, null, 7)
  INTO cardio_exercise VALUES (6, 'Running', 0.35, null, null, null, 8)
SELECT * FROM dual;

/* Insert sample values into RESISTANCE_EXERCISE table */
INSERT ALL
  INTO resistance_exercise VALUES (1, 'Squat', 1, 12, null, 1.30, 7, null, 1)
  INTO resistance_exercise VALUES (2, 'Squat', 2, 12, null, 1.30, 7.5, null, 1)
  INTO resistance_exercise VALUES (3, 'Pushups', 1, 10, null, 1.30, 7, null, 1)
  INTO resistance_exercise VALUES (4, 'Pushups', 2, 10, null, 1.30, 8, null, 1)
  INTO resistance_exercise VALUES (5, 'Rows', 1, 15, null, 1.30, 8, null, 1)
  INTO resistance_exercise VALUES (6, 'Rows', 2, 15, null, 1.30, 8.5, null, 1)
  INTO resistance_exercise VALUES (7, 'Bench Press', 1, 5, 60, 3.00, 6, null, 4)
  INTO resistance_exercise VALUES (8, 'Bench Press', 2, 3, 70, 3.00, 7, null, 4)
  INTO resistance_exercise VALUES (9, 'Bench Press', 3, 1, 80, 3.00, 8, null, 4)
  INTO resistance_exercise VALUES (10, 'Deadlift', 1, 5, 75, 3.00, 6.5, null, 4)
  INTO resistance_exercise VALUES (11, 'Deadlift', 2, 3, 85, 3.00, 7.5, null, 4)
  INTO resistance_exercise VALUES (12, 'Deadlift', 3, 1, 95, 3.00, 9, null, 4)
SELECT * FROM dual;

/* Insert sample values into BODY_MEASUREMENTs table */
INSERT ALL
  INTO body_measurements VALUES (1, '12/Jan/2016', 81, null, 'dmo')
  INTO body_measurements VALUES (2, '15/Jan/2016', null, .12, 'jjo')
  INTO body_measurements VALUES (3, '18/Feb/2016', 80, null, 'dmo')
  INTO body_measurements VALUES (4, '01/Mar/2016', 73, null, 'grig')
  INTO body_measurements VALUES (5, '24/Mar/2016', 79, null, 'dmo')
SELECT * FROM dual;

/* Insert sample values into BODY_GOALs table */
INSERT ALL
  INTO body_goals VALUES (1, 75, null, 'dmo')
  INTO body_goals VALUES (2, null, .10, 'jjo')
  INTO body_goals VALUES (3, 71, null, 'grig')
  INTO body_goals VALUES (4, null, .15, 'arido')
SELECT * FROM dual;

/* Sample Queries */

/* return user details for username given */
SELECT 
  username    AS "Username",
  name        AS "Name",
  dateofbirth AS "Date of birth",
  gender      AS "Gender",
  country     AS "Country"
FROM users
WHERE username = 'dmo';

/* returns workouts for given user */
SELECT
  name        AS "Name",
  workoutname AS "Workout",
  workoutdate AS "Date"
FROM users
INNER JOIN workouts
ON users.username = workouts.username
WHERE users.username = 'dmo';

/* returns all resistance workouts with exercises for given user */
SELECT
  name        AS "Name",
  workoutname AS "Workout",
  workoutdate AS "Date",
  exercisename AS "Exercise Name",
  setnumber   AS "Set Number",
  repetitions AS "Repetitions",
  weight      AS "Weight",
  restperiod  AS "Rest Period",
  intensity   AS "Intensity",
  note        AS "Notes"
FROM users
INNER JOIN workouts 
  ON users.username = workouts.username
INNER JOIN resistance_exercise
  ON workouts.id = resistance_exercise.workout_id
WHERE users.username = 'dmo'; 

/* returns all cardio workouts with exercises for given user between a specific date */
SELECT
  name        AS "Name",
  workoutname AS "Workout",
  workoutdate AS "Date",
  exercisename AS "Exercise Name",
  time        AS "Time",
  distance    AS "Distance",
  intensity   AS "Intensity",
  note        AS "Notes"
FROM users
INNER JOIN workouts 
  ON users.username = workouts.username
INNER JOIN cardio_exercise
  ON workouts.id = cardio_exercise.workout_id
WHERE users.username = 'smck'
AND (workoutdate BETWEEN '20/Mar/2016' AND '24/Mar/2016');


/* return all meals and food contents by date for a user */
SELECT
  users.name  AS "Name",
  mealtime    AS "Time of Meal",
  meals.name  AS "Meal Name",
  foodname    AS "Food Item",
  portionsize AS "Portion",
  calories    AS "Calories",
  proteingrams AS "Protein (g)",
  carbgrams   AS "Carbs (g)",
  fatgrams    AS "Fat (g)"
FROM users
INNER JOIN meals 
  ON users.username = meals.username
INNER JOIN food_items
  ON meals.id = food_items.meal_id
WHERE users.username = 'dmo';


/* return total calories and macronutrients for a specific meal */
SELECT
  meal_id         AS "Meal",
  SUM(calories)   AS "Total Calories",
  SUM(proteingrams) AS "Total Protein (g)",
  SUM(carbgrams)  AS "Total Carbs (g)",
  SUM(fatgrams)   AS "Total Fat (g)"
FROM food_items
GROUP BY meal_id;


/* returns the total calories and macronutrients for a user for a specific day */
SELECT
  mealdate        AS "Date",
  SUM(calories)   AS "Total Calories",
  SUM(proteingrams) AS "Total Protein (g)",
  SUM(carbgrams)  AS "Total Carbs (g)",
  SUM(fatgrams)   AS "Total Fat (g)"
FROM users
INNER JOIN meals 
  ON users.username = meals.username
INNER JOIN food_items
  ON meals.id = food_items.meal_id
WHERE (users.username = 'dmo' AND mealdate = '24/Mar/2016')
GROUP BY meals.mealdate;


/* returns body measurements recorded by a user, ordered by most recent first */
SELECT 
  users.name  AS "Name",  
  daterecorded AS "Date Taken",
  bodyweight AS "Bodyweight (kg)",
  bodyfat AS "Body Fat (%)"
FROM users
INNER JOIN body_measurements
  ON users.username = body_measurements.username
WHERE users.username = 'dmo'
ORDER BY daterecorded DESC;