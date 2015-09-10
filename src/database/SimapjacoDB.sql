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
-- Table `Simapjaco`.`Ropa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Simapjaco`.`Ropa` ;

CREATE TABLE IF NOT EXISTS `Simapjaco`.`Ropa` (
  `id_ropa` INT NOT NULL COMMENT '',
  `nombre_prenda` VARCHAR(45) NOT NULL COMMENT '',
  `descripcion` VARCHAR(45) NOT NULL COMMENT '',
  `existencias` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_ropa`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Simapjaco`.`Catalogo_color`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Simapjaco`.`Catalogo_color` ;

CREATE TABLE IF NOT EXISTS `Simapjaco`.`Catalogo_color` (
  `id_color` INT NOT NULL COMMENT '',
  `color` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id_color`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Simapjaco`.`Catalogo_talla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Simapjaco`.`Catalogo_talla` ;

CREATE TABLE IF NOT EXISTS `Simapjaco`.`Catalogo_talla` (
  `id_talla` INT NOT NULL COMMENT '',
  `talla` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id_talla`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Simapjaco`.`Modelo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Simapjaco`.`Modelo` ;

CREATE TABLE IF NOT EXISTS `Simapjaco`.`Modelo` (
  `id_modelo` INT NOT NULL COMMENT '',
  `id_ropa` INT NOT NULL COMMENT '',
  `id_color` INT NOT NULL COMMENT '',
  `id_talla` INT NOT NULL COMMENT '',
  `modelo` VARCHAR(45) NOT NULL COMMENT '',
  `existencias` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id_modelo`, `id_ropa`)  COMMENT '',
  CONSTRAINT `fk_Modelo_Ropa`
    FOREIGN KEY (`id_ropa`)
    REFERENCES `Simapjaco`.`Ropa` (`id_ropa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Modelo_Catalogo_color1`
    FOREIGN KEY (`id_color`)
    REFERENCES `Simapjaco`.`Catalogo_color` (`id_color`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Modelo_Catalogo_talla1`
    FOREIGN KEY (`id_talla`)
    REFERENCES `Simapjaco`.`Catalogo_talla` (`id_talla`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Modelo_Ropa_idx` ON `Simapjaco`.`Modelo` (`id_ropa` ASC)  COMMENT '';

CREATE INDEX `fk_Modelo_Catalogo_color1_idx` ON `Simapjaco`.`Modelo` (`id_color` ASC)  COMMENT '';

CREATE INDEX `fk_Modelo_Catalogo_talla1_idx` ON `Simapjaco`.`Modelo` (`id_talla` ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table `Simapjaco`.`Ventas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Simapjaco`.`Ventas` ;

CREATE TABLE IF NOT EXISTS `Simapjaco`.`Ventas` (
  `id_ventas` INT NOT NULL COMMENT '',
  `fecha` VARCHAR(45) NOT NULL COMMENT '',
  `no_articulos` INT NOT NULL COMMENT '',
  `precio_total` DOUBLE NOT NULL COMMENT '',
  PRIMARY KEY (`id_ventas`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Simapjaco`.`Detalle_venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Simapjaco`.`Detalle_venta` ;

CREATE TABLE IF NOT EXISTS `Simapjaco`.`Detalle_venta` (
  `id_detalle_venta` INT NOT NULL COMMENT '',
  `id_venta` INT NOT NULL COMMENT '',
  `id_ropa` INT NOT NULL COMMENT '',
  `cantidad_articulo` INT NOT NULL COMMENT '',
  `precio_unitario` DOUBLE NOT NULL COMMENT '',
  PRIMARY KEY (`id_detalle_venta`, `id_venta`)  COMMENT '',
  CONSTRAINT `fk_Detalle_venta_Ventas1`
    FOREIGN KEY (`id_venta`)
    REFERENCES `Simapjaco`.`Ventas` (`id_ventas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Detalle_venta_Ropa1`
    FOREIGN KEY (`id_ropa`)
    REFERENCES `Simapjaco`.`Ropa` (`id_ropa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Detalle_venta_Ventas1_idx` ON `Simapjaco`.`Detalle_venta` (`id_venta` ASC)  COMMENT '';

CREATE INDEX `fk_Detalle_venta_Ropa1_idx` ON `Simapjaco`.`Detalle_venta` (`id_ropa` ASC)  COMMENT '';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
