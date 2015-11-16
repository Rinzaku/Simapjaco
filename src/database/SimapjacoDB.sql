-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Simapjaco
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Simapjaco` ;

-- -----------------------------------------------------
-- Schema Simapjaco
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Simapjaco` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Simapjaco` ;

-- -----------------------------------------------------
-- Table `Ropa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Ropa` ;

CREATE TABLE IF NOT EXISTS `Ropa` (
  `id_ropa` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nombre_prenda` VARCHAR(45) NOT NULL COMMENT '',
  `descripcion` VARCHAR(45) NOT NULL COMMENT '',
  `existencias` INT NOT NULL COMMENT '',
  `precio` DOUBLE NOT NULL COMMENT '',
  PRIMARY KEY (`id_ropa`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Catalogo_color`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Catalogo_color` ;

CREATE TABLE IF NOT EXISTS `Catalogo_color` (
  `id_color` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `color` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id_color`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Catalogo_talla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Catalogo_talla` ;

CREATE TABLE IF NOT EXISTS `Catalogo_talla` (
  `id_talla` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `talla` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id_talla`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Modelo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Modelo` ;

CREATE TABLE IF NOT EXISTS `Modelo` (
  `id_modelo` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_ropa` INT NOT NULL COMMENT '',
  `id_color` INT NOT NULL COMMENT '',
  `id_talla` INT NOT NULL COMMENT '',
  `modelo` VARCHAR(45) NOT NULL COMMENT '',
  `existencias` INT NOT NULL COMMENT '',
  `estado` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`id_modelo`, `id_ropa`)  COMMENT '',
  CONSTRAINT `fk_Modelo_Ropa`
    FOREIGN KEY (`id_ropa`)
    REFERENCES `Ropa` (`id_ropa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Modelo_Catalogo_color1`
    FOREIGN KEY (`id_color`)
    REFERENCES `Catalogo_color` (`id_color`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Modelo_Catalogo_talla1`
    FOREIGN KEY (`id_talla`)
    REFERENCES `Catalogo_talla` (`id_talla`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Modelo_Ropa_idx` ON `Modelo` (`id_ropa` ASC)  COMMENT '';

CREATE INDEX `fk_Modelo_Catalogo_color1_idx` ON `Modelo` (`id_color` ASC)  COMMENT '';

CREATE INDEX `fk_Modelo_Catalogo_talla1_idx` ON `Modelo` (`id_talla` ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table `Ventas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Ventas` ;

CREATE TABLE IF NOT EXISTS `Ventas` (
  `id_ventas` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `fecha` VARCHAR(45) NOT NULL COMMENT '',
  `no_articulos` INT NOT NULL COMMENT '',
  `precio_total` DOUBLE NOT NULL COMMENT '',
  PRIMARY KEY (`id_ventas`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Detalle_venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Detalle_venta` ;

CREATE TABLE IF NOT EXISTS `Detalle_venta` (
  `id_detalle_venta` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_venta` INT NOT NULL COMMENT '',
  `id_modelo` INT NOT NULL COMMENT '',
  `id_ropa` INT NOT NULL COMMENT '',
  `cantidad_articulo` INT NOT NULL COMMENT '',
  `precio_unitario` DOUBLE NOT NULL COMMENT '',
  `estado` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`id_detalle_venta`, `id_venta`)  COMMENT '',
  CONSTRAINT `fk_Detalle_venta_Ventas1`
    FOREIGN KEY (`id_venta`)
    REFERENCES `Ventas` (`id_ventas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Detalle_venta_Modelo1`
    FOREIGN KEY (`id_modelo`,`id_ropa`)
    REFERENCES `Modelo` (`id_modelo`,`id_ropa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Detalle_venta_Ventas1_idx` ON `Detalle_venta` (`id_venta` ASC)  COMMENT '';

CREATE INDEX `fk_Detalle_venta_Modelo1_idx` ON `Detalle_venta` (`id_modelo` ASC,`id_ropa` ASC)  COMMENT '';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

/*==============================================================*/
/* ------------------      DATOS DE PRUEBA      ----------------*/
/*==============================================================*/

/*==============================================================*/
/* INSERTS ROPA                                                 */
/*==============================================================*/

INSERT INTO ropa(nombre_prenda,descripcion, existencias, precio) VALUES ('pantalon','pantalon de mezclilla para caballero', 15,floor(RAND()*(500-1)+1));
INSERT INTO ropa(nombre_prenda,descripcion, existencias, precio) VALUES ('falda','falda de vestir para dama', 25,floor(RAND()*(500-1)+1));
INSERT INTO ropa(nombre_prenda,descripcion, existencias, precio) VALUES ('blusa','blusa para dama', 10,floor(RAND()*(500-1)+1));
INSERT INTO ropa(nombre_prenda,descripcion, existencias, precio) VALUES ('vestido','vestido de vestir par dama', 30,floor(RAND()*(500-1)+1));
INSERT INTO ropa(nombre_prenda,descripcion, existencias, precio) VALUES ('playera','playera polo para caballero', 15,floor(RAND()*(500-1)+1));
INSERT INTO ropa(nombre_prenda,descripcion, existencias, precio) VALUES ('playera','playera sport para caballero', 25,floor(RAND()*(500-1)+1));
INSERT INTO ropa(nombre_prenda,descripcion, existencias, precio) VALUES ('chamarra','chamarra de piel caballero', 25,floor(RAND()*(500-1)+1));
INSERT INTO ropa(nombre_prenda,descripcion, existencias, precio) VALUES ('zapatos','zapatos para dama', 10,floor(RAND()*(500-1)+1));

/*==============================================================*/
/* INSERTS CATALOGO COLOR                                       */
/*==============================================================*/

INSERT INTO catalogo_color(color) VALUES ('BLANCO');
INSERT INTO catalogo_color(color) VALUES ('AZUL');
INSERT INTO catalogo_color(color) VALUES ('NEGRO');
INSERT INTO catalogo_color(color) VALUES ('CAFE');
INSERT INTO catalogo_color(color) VALUES ('FIUSHA');
INSERT INTO catalogo_color(color) VALUES ('ROSA');
INSERT INTO catalogo_color(color) VALUES ('MORADO');
INSERT INTO catalogo_color(color) VALUES ('AZUL CIELO');
INSERT INTO catalogo_color(color) VALUES ('VERDE');
INSERT INTO catalogo_color(color) VALUES ('PLATEADO');
INSERT INTO catalogo_color(color) VALUES ('DURAZNO');

/*==============================================================*/
/* INSERTS CATALOGO TALLA                                       */
/*==============================================================*/

INSERT INTO catalogo_talla(talla) VALUES ('UNITALLA');
INSERT INTO catalogo_talla(talla) VALUES ('CH');
INSERT INTO catalogo_talla(talla) VALUES ('M');
INSERT INTO catalogo_talla(talla) VALUES ('G');
INSERT INTO catalogo_talla(talla) VALUES ('XG');
INSERT INTO catalogo_talla(talla) VALUES ('7-28');
INSERT INTO catalogo_talla(talla) VALUES ('9-30');
INSERT INTO catalogo_talla(talla) VALUES ('11-32');
INSERT INTO catalogo_talla(talla) VALUES ('13-34');
INSERT INTO catalogo_talla(talla) VALUES ('15-36');
INSERT INTO catalogo_talla(talla) VALUES ('17-38');

/*==============================================================*/
/* INSERTS MODELO                                               */
/*==============================================================*/
-- DO $$
-- BEGIN
--  FOR i IN 1..100 LOOP
--    INSERT INTO modelo(id_ropa,id_color,id_talla,modelo,existencias) VALUES ((SELECT id_ropa FROM ropa  ORDER BY RANDOM() LIMIT 1),(SELECT id_color FROM catalogo_color  ORDER BY RANDOM() LIMIT 1),(SELECT id_talla FROM catalogo_talla  ORDER BY RANDOM() LIMIT 1),(SELECT ('123456'||(SELECT floor(RANDOM()*(50-1)+1)))), floor(random()*(50-1)+1)); 
--  END LOOP;
-- END$$;
-- La senetencia anterior solo funciona en PostgreSQL T-T

DELIMITER $$
CREATE PROCEDURE insert_modelo()

  BEGIN 
    DECLARE a INT Default 1;
    simple_loop:LOOP
      INSERT INTO modelo(id_ropa,id_color,id_talla,modelo,existencias, estado) VALUES ((SELECT id_ropa FROM ropa  ORDER BY RAND() LIMIT 1),(SELECT id_color FROM catalogo_color  ORDER BY RAND() LIMIT 1),(SELECT id_talla FROM catalogo_talla  ORDER BY RAND() LIMIT 1),(SELECT CONCAT('12345',(SELECT floor(RAND()*(50-1)+1)))), floor(RAND()*(50-1)+1),'ACTIVO');  
      SET a=a+1;
      IF a=101 THEN
        LEAVE simple_loop;
      END IF;
  END LOOP simple_loop;
END $$

CALL insert_modelo()