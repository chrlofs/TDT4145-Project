-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema treningsdagbok
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema treningsdagbok
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `treningsdagbok` ;
USE `treningsdagbok` ;

-- -----------------------------------------------------
-- Table `treningsdagbok`.`sport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`sport` (
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Training Session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`TrainingSession` (
  `Date` DATETIME NOT NULL,
  `Duration` INT NULL,
  `Shape` INT NULL,
  `Rating` INT NULL,
  `Note` TEXT NULL,
  `sport_name` VARCHAR(30) NOT NULL,
  `Type` VARCHAR(45) NULL,
  `Weather_type` VARCHAR(45) NULL,
  `Temperature` DECIMAL(3) NULL,
  `Humidity` DECIMAL(3) NULL,
  `Spectators` INT NULL,
  PRIMARY KEY (`Date`),
  INDEX `fk_TrainingSession_sport_idx` (`sport_name` ASC),
  CONSTRAINT `fk_TrainingSession_sport`
    FOREIGN KEY (`sport_name`)
    REFERENCES `treningsdagbok`.`sport` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Exercise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Exercise` (
  `Name` VARCHAR(30) NOT NULL,
  `Description` VARCHAR(60) NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `treningsdagbok`.`ExercisePerformed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`ExercisePerformed` (
  `Sets` INT NULL,
  `Reps` INT NULL,
  `Weight` INT NULL,
  `Distance` FLOAT(4, 2) NULL,
  `Duration` INT NULL,
  `Exercise_Name` VARCHAR(30) NOT NULL,
  `TrainingSession_Date` DATETIME NOT NULL,
  PRIMARY KEY (`Exercise_Name`, `TrainingSession_Date`),
  INDEX `fk_ExercisePerformed_Training Session1_idx` (`TrainingSession_Date` ASC),
  CONSTRAINT `fk_ExercisePerformed_Exercise1`
    FOREIGN KEY (`Exercise_Name`)
    REFERENCES `treningsdagbok`.`Exercise` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExercisePerformed_TrainingSession1`
    FOREIGN KEY (`TrainingSession_Date`)
    REFERENCES `treningsdagbok`.`TrainingSession` (`Date`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Goal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Goal` (
  `Date` DATE NOT NULL,
  `Speed` DECIMAL(3) NULL,
  `TotalWeightLifted` INT NULL,
  `Achieved` TINYINT(1) NULL,
  `Exercise_Name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Date`, `Exercise_Name`),
  INDEX `fk_Goal_Exercise1_idx` (`Exercise_Name` ASC),
  CONSTRAINT `fk_Goal_Exercise1`
    FOREIGN KEY (`Exercise_Name`)
    REFERENCES `treningsdagbok`.`Exercise` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Group` (
  `Name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Exercise_has_Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Exercise_has_Group` (
  `Exercise_Name` VARCHAR(30) NOT NULL,
  `Group_Name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Exercise_Name`, `Group_Name`),
  INDEX `fk_Exercise_has_Group_Group1_idx` (`Group_Name` ASC),
  INDEX `fk_Exercise_has_Group_Exercise1_idx` (`Exercise_Name` ASC),
  CONSTRAINT `fk_Exercise_has_Group_Exercise1`
    FOREIGN KEY (`Exercise_Name`)
    REFERENCES `treningsdagbok`.`Exercise` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Exercise_has_Group_Group1`
    FOREIGN KEY (`Group_Name`)
    REFERENCES `treningsdagbok`.`Group` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Group_has_Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Group_has_Group` (
  `Group_Name` VARCHAR(30) NOT NULL,
  `Group_Name1` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Group_Name`, `Group_Name1`),
  INDEX `fk_Group_has_Group_Group2_idx` (`Group_Name1` ASC),
  INDEX `fk_Group_has_Group_Group1_idx` (`Group_Name` ASC),
  CONSTRAINT `fk_Group_has_Group_Group1`
    FOREIGN KEY (`Group_Name`)
    REFERENCES `treningsdagbok`.`Group` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Group_has_Group_Group2`
    FOREIGN KEY (`Group_Name1`)
    REFERENCES `treningsdagbok`.`Group` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Exercise_is_similar_to`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Exercise_is_similar_to` (
  `Exercise_Name` VARCHAR(30) NOT NULL,
  `Exercise_Name1` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Exercise_Name`, `Exercise_Name2`),
  INDEX `fk_Exercise_has_Exercise_Exercise2_idx` (`Exercise_Name1` ASC),
  INDEX `fk_Exercise_has_Exercise_Exercise1_idx` (`Exercise_Name` ASC),
  CONSTRAINT `fk_Exercise_has_Exercise_Exercise1`
    FOREIGN KEY (`Exercise_Name`)
    REFERENCES `treningsdagbok`.`Exercise` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Exercise_has_Exercise_Exercise2`
    FOREIGN KEY (`Exercise_Name1`)
    REFERENCES `treningsdagbok`.`Exercise` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
