create database db_stevie;
use db_stevie;

CREATE TABLE TB_TAG (
  `id_tag` INT NOT NULL AUTO_INCREMENT,
  `cod_tag` VARCHAR(200) NOT NULL,
  `tag_frente` VARCHAR(200) NULL,
  `tag_tras` VARCHAR(200) NULL,
  `tag_esquerda` VARCHAR(200) NULL,
  `tag_direita` VARCHAR(200) NULL,
  PRIMARY KEY (`id_tag`)
);


CREATE TABLE TB_REGIAO(
  `id_regiao` INT NOT NULL AUTO_INCREMENT,
  `nome_regiao` VARCHAR(45) NULL,
  PRIMARY KEY (`id_regiao`)
  );

CREATE TABLE TB_PONTO_INTERESSE(
  `id_ponto_interesse` INT NOT NULL AUTO_INCREMENT,
  `id_regiao` INT NOT NULL,
  `id_tag` INT NOT NULL,
  `nome` VARCHAR(200) NULL,
  `descricao` VARCHAR(200) NULL,
  PRIMARY KEY (`id_ponto_interesse`),
    FOREIGN KEY (`id_regiao`)
    REFERENCES TB_REGIAO(`id_regiao`),
    FOREIGN KEY (`id_tag`)
    REFERENCES TB_TAG (`id_tag`))




