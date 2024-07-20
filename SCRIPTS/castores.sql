CREATE SCHEMA IF NOT EXISTS `castores` DEFAULT CHARACTER SET utf8;
USE `castores`;

-- -----------------------------------------------------
-- Table `castores`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `roles` (
    `idRol` INT(2) NOT NULL AUTO_INCREMENT,
    `rol` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idRol`)
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `castores`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuarios` (
    `idUsuario` INT(6) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(100) NOT NULL,
    `correo` VARCHAR(50) NOT NULL,
    `contrasena` VARCHAR(255) NOT NULL,
    `idRol` INT(2) NOT NULL,
    `estatus` INT(1) NOT NULL,
    PRIMARY KEY (`idUsuario`),
    INDEX `fk_idRol_idx` (`idRol` ASC),
    CONSTRAINT `fk_idRol`
    FOREIGN KEY (`idRol`)
    REFERENCES `roles` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `castores`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `productos` (
    `idProducto` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    `cantidad` INT NOT NULL,
    `estatus` INT(2) NOT NULL,
    PRIMARY KEY (`idProducto`)
    ) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `castores`.`historial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `historial` (
    `idMovimiento` INT NOT NULL AUTO_INCREMENT,
    `idProducto` INT NOT NULL,
    `idUsuario` INT NOT NULL,
    `movimiento` VARCHAR(20) NOT NULL,
    `cantidad` INT NOT NULL,
    `dateTim` DATETIME NOT NULL,
    PRIMARY KEY (`idMovimiento`),
    INDEX `fk_idProducto_idx` (`idProducto` ASC),
    INDEX `fk_idUsuario_idx` (`idUsuario` ASC),
    CONSTRAINT `fk_idProducto`
    FOREIGN KEY (`idProducto`)
    REFERENCES `productos` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_idUsuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;

USE `castores`;

INSERT INTO roles (idRol, rol) VALUES (1, "Administrador");
INSERT INTO roles (idRol, rol) VALUES (2, "Almacenista");

INSERT INTO usuarios (idUsuario, nombre, correo, contrasena, idRol, estatus)
VALUES (1, 'Miguel Romero', 'miguel@example.com', 'password123', 1, 1);

INSERT INTO usuarios (idUsuario, nombre, correo, contrasena, idRol, estatus)
VALUES (2, 'Luz Lopez', 'luz@example.com', 'password123', 2, 1);

INSERT INTO productos (idProducto, nombre, cantidad, estatus)
VALUES (1, 'Samsung Galaxy S1', 0, 1);

DELIMITER //

CREATE TRIGGER agregar_historial
    AFTER UPDATE ON productos
    FOR EACH ROW
BEGIN
    DECLARE id_user INT;
    SET id_user = @id_user;

    IF NEW.cantidad < OLD.cantidad THEN
        INSERT INTO historial(idProducto, idUsuario, movimiento, cantidad, dateTim)
        VALUES (OLD.idProducto, id_user, 'Salida', OLD.cantidad - NEW.cantidad, NOW());
    ELSEIF NEW.cantidad > OLD.cantidad THEN
        INSERT INTO historial(idProducto, idUsuario, movimiento, cantidad, dateTim)
        VALUES (OLD.idProducto, id_user, 'Entrada', NEW.cantidad - OLD.cantidad, NOW());
END IF;

END //

DELIMITER ;
