CREATE DATABASE IF NOT EXISTS 'training_diary' ;
USE 'training_diary';

CREATE TABLE IF NOT EXISTS 'training_diary'.'sport' (
  'name' VARCHAR(30) NOT NULL,
  PRIMARY KEY ('name'));

CREATE TABLE IF NOT EXISTS 'training_diary'.'training_session' (
  'Date' DATETIME NOT NULL,
  'Duration' INT NULL,
  'Shape' INT NULL,
  'Rating' INT NULL,
  'Note' INT NULL,
  'sport_name' VARCHAR(30) NOT NULL,
  'Type' VARCHAR(45) NULL,
  'Weather_type' VARCHAR(45) NULL,
  'Temperature' DECIMAL(3) NULL,
  'Humidity' DECIMAL(3) NULL,
  'Spectators' INT NULL,
  PRIMARY KEY ('Date'),
  INDEX 'fk_training_session_sport_idx' ('sport_name' ASC),
  CONSTRAINT 'fk_training_session_sport'
  FOREIGN KEY ('sport_name')
  REFERENCES 'training_diary'.'sport' ('name')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS 'training_diary'.'exercise' (
  'Name' VARCHAR(30) NOT NULL,
  'Description' VARCHAR(60) NULL,
  PRIMARY KEY ('Name'))

CREATE TABLE IF NOT EXISTS 'training_diary'.'excercise_performed' (
  'Sets' INT NULL,
  'Reps' INT NULL,
  'Load' INT NULL,
  'Distance' DECIMAL(2) NULL,
  'Duration' INT NULL,
  'exercise_Name' VARCHAR(30) NOT NULL,
  'training_session_Date' DATETIME NOT NULL,
  PRIMARY KEY ('exercise_Name', 'training_session_Date'),
  INDEX 'fk_excercise_performed_training_session1_idx' ('training_session_Date' ASC),
  CONSTRAINT 'fk_excercise_performed_exercise1'
  FOREIGN KEY ('exercise_Name')
  REFERENCES 'training_diary'.'exercise' ('Name')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'fk_excercise_performed_training_session1'
  FOREIGN KEY ('training_session_Date')
  REFERENCES 'training_diary'.'training_session' ('Date')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS 'training_diary'.'goal' (
  'Date' DATE NOT NULL,
  'Speed' DECIMAL(3) NULL,
  'TotalWeightLifted' INT NULL,
  'Achieved' TINYINT(1) NULL,
  'exercise_Name' VARCHAR(30) NOT NULL,
  PRIMARY KEY ('Date', 'exercise_Name'),
  INDEX 'fk_goal_exercise1_idx' ('exercise_Name' ASC),
  CONSTRAINT 'fk_goal_exercise1'
  FOREIGN KEY ('exercise_Name')
  REFERENCES 'training_diary'.'exercise' ('Name')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS 'training_diary'.'group' (
  'Name' VARCHAR(30) NOT NULL,
  PRIMARY KEY ('Name'))

CREATE TABLE IF NOT EXISTS 'training_diary'.'exercise_has_group' (
  'exercise_Name' VARCHAR(30) NOT NULL,
  'group_Name' VARCHAR(30) NOT NULL,
  PRIMARY KEY ('exercise_Name', 'group_Name'),
  INDEX 'fk_exercise_has_group_group1_idx' ('group_Name' ASC),
  INDEX 'fk_exercise_has_group_exercise1_idx' ('exercise_Name' ASC),
  CONSTRAINT 'fk_exercise_has_group_exercise1'
  FOREIGN KEY ('exercise_Name')
  REFERENCES 'training_diary'.'exercise' ('Name')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'fk_exercise_has_group_group1'
  FOREIGN KEY ('group_Name')
  REFERENCES 'training_diary'.'group' ('Name')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS 'training_diary'.'group_has_group' (
  'group_Name' VARCHAR(30) NOT NULL,
  'group_Name1' VARCHAR(30) NOT NULL,
  PRIMARY KEY ('group_Name', 'group_Name1'),
  INDEX 'fk_group_has_group_group2_idx' ('group_Name1' ASC),
  INDEX 'fk_group_has_group_group1_idx' ('group_Name' ASC),
  CONSTRAINT 'fk_group_has_group_group1'
  FOREIGN KEY ('group_Name')
  REFERENCES 'training_diary'.'group' ('Name')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'fk_group_has_group_group2'
  FOREIGN KEY ('group_Name1')
  REFERENCES 'training_diary'.'group' ('Name')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS 'training_diary'.'exercise_is_similar_to' (
  'exercise_Name' VARCHAR(30) NOT NULL,
  'exercise_Name1' VARCHAR(30) NOT NULL,
  PRIMARY KEY ('exercise_Name', 'exercise_Name1'),
  INDEX 'fk_exercise_has_exercise_exercise2_idx' ('exercise_Name1' ASC),
  INDEX 'fk_exercise_has_exercise_exercise1_idx' ('exercise_Name' ASC),
  CONSTRAINT 'fk_exercise_has_exercise_exercise1'
  FOREIGN KEY ('exercise_Name')
  REFERENCES 'training_diary'.'exercise' ('Name')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'fk_exercise_has_exercise_exercise2'
  FOREIGN KEY ('exercise_Name1')
  REFERENCES 'training_diary'.'exercise' ('Name')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
