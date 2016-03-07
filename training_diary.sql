CREATE SCHEMA IF NOT EXISTS `treningsdagbok` ;
USE `treningsdagbok` ;

-- -----------------------------------------------------
-- Table `treningsdagbok`.`sport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`sport` (
 `name` VARCHAR(30) NOT NULL,
 PRIMARY KEY (`name`))


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Training Session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Training Session` (
 `Date` DATETIME NOT NULL,
 `Duration` INT NULL,
 `Shape` INT NULL,
 `Rating` INT NULL,
 `Note` INT NULL,
 `sport_name` VARCHAR(30) NOT NULL,
 `Type` VARCHAR(45) NULL,
 `Weather_type` VARCHAR(45) NULL,
 `Temperature` DECIMAL(3) NULL,
 `Humidity` DECIMAL(3) NULL,
 `Spectators` INT NULL,
 PRIMARY KEY (`Date`),
 INDEX `fk_Training Session_sport_idx` (`sport_name` ASC),
 CONSTRAINT `fk_Training Session_sport`
   FOREIGN KEY (`sport_name`)
   REFERENCES `treningsdagbok`.`sport` (`name`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION)


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Exercise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Exercise` (
 `Name` VARCHAR(30) NOT NULL,
 `Description` VARCHAR(60) NULL,
 PRIMARY KEY (`Name`))


-- -----------------------------------------------------
-- Table `treningsdagbok`.`ExcercisePerformed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`ExcercisePerformed` (
 `Sets` INT NULL,
 `Reps` INT NULL,
 `Load` INT NULL,
 `Distance` DECIMAL(2) NULL,
 `Duration` INT NULL,
 `Exercise_Name` VARCHAR(30) NOT NULL,
 `Training Session_Date` DATETIME NOT NULL,
 PRIMARY KEY (`Exercise_Name`, `Training Session_Date`),
 INDEX `fk_ExcercisePerformed_Training Session1_idx` (`Training Session_Date` ASC),
 CONSTRAINT `fk_ExcercisePerformed_Exercise1`
   FOREIGN KEY (`Exercise_Name`)
   REFERENCES `treningsdagbok`.`Exercise` (`Name`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
 CONSTRAINT `fk_ExcercisePerformed_Training Session1`
   FOREIGN KEY (`Training Session_Date`)
   REFERENCES `treningsdagbok`.`Training Session` (`Date`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION)


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


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Group` (
 `Name` VARCHAR(30) NOT NULL,
 PRIMARY KEY (`Name`))


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


-- -----------------------------------------------------
-- Table `treningsdagbok`.`Exercise_is_similar_to`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `treningsdagbok`.`Exercise_is_similar_to` (
 `Exercise_Name` VARCHAR(30) NOT NULL,
 `Exercise_Name1` VARCHAR(30) NOT NULL,
 PRIMARY KEY (`Exercise_Name`, `Exercise_Name1`),
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
