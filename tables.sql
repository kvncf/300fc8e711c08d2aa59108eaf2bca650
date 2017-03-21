CREATE TABLE IF NOT EXISTS `VI_CLIENTES` (
  `documento` BIGINT(20) NOT NULL,
  `nombre` VARCHAR(50)  NOT NULL,
  `telefono` VARCHAR(20)  NOT NULL,
  `direccion` VARCHAR(150)  NOT NULL,
  `email` VARCHAR(150)  NOT NULL,
  `vetado` TINYINT(1) NOT NULL);


-- -----------------------------------------------------
-- Table `VI_TIPOITEM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VI_TIPOITEM` (
  `id` INT(11) NOT NULL,
  `descripcion` VARCHAR(50)  NOT NULL);


-- -----------------------------------------------------
-- Table `VI_ITEMS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VI_ITEMS` (
  `id` INT(11) NOT NULL,
  `nombre` VARCHAR(50)  NOT NULL,
  `descripcion` VARCHAR(500)  NOT NULL,
  `fechalanzamiento` DATE NOT NULL,
  `tarifaxdia` BIGINT(20) NOT NULL,
  `formatorenta` VARCHAR(20) NOT NULL,
  `genero` VARCHAR(20)  NOT NULL,
  `TIPOITEM_id` INT(11) NOT NULL);


-- -----------------------------------------------------
-- Table `VI_ITEMRENTADO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VI_ITEMRENTADO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `CLIENTES_documento` BIGINT(20) NOT NULL,
  `ITEMS_id` INT(11) NOT NULL,
  `fechainiciorenta` DATE NOT NULL,
  `fechafinrenta` DATE NOT NULL);


