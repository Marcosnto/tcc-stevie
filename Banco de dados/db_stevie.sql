-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 11-Set-2019 às 02:38
-- Versão do servidor: 10.1.35-MariaDB
-- versão do PHP: 7.1.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_stevie`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_tag`
--

CREATE TABLE `tb_tag` (
  `id_tag` int(11) NOT NULL,
  `cod_tag` varchar(200) NOT NULL,
  `tag_norte` varchar(200) DEFAULT NULL,
  `tag_sul` varchar(200) DEFAULT NULL,
  `tag_leste` varchar(200) DEFAULT NULL,
  `tag_oeste` varchar(200) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `regiao` enum('bloco D') DEFAULT NULL,
  `tipo_tag` enum('local','caminho','objeto','alerta') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_tag`
--

INSERT INTO `tb_tag` (`id_tag`, `cod_tag`, `tag_norte`, `tag_sul`, `tag_leste`, `tag_oeste`, `nome`, `descricao`, `regiao`, `tipo_tag`) VALUES
(1, 'A1', NULL, 'A2', NULL, NULL, 'Janela', 'Janela - Corredor do Bloco D', 'bloco D', 'alerta'),
(13, 'AD', NULL, NULL, 'A3', NULL, 'Sala de Aula 101', 'Sala de Aula 101', 'bloco D', 'local'),
(14, 'A5', NULL, NULL, 'A2', NULL, 'Sala de Aula 103', 'Sala de Aula 103', 'bloco D', 'caminho'),
(15, 'AA', 'A2', 'A3', NULL, NULL, 'Caminho', 'Corredor - Bloco D', 'bloco D', 'caminho'),
(16, 'A3', 'AA', 'AB', 'A8', 'AD', 'Caminho', 'Corredor - Bloco D', 'bloco D', 'caminho'),
(17, 'A8', NULL, NULL, NULL, 'A3', 'Laboratório de Sistemas de Informação', 'Laboratório de Sistemas de Informação', 'bloco D', 'local'),
(18, 'A2', 'A1', 'AA', 'A6', 'A5', 'Caminho', 'Corredor - Bloco D', 'bloco D', 'caminho'),
(19, 'A6', NULL, NULL, NULL, 'A2', 'Sala de Aula 102', 'Sala de Aula 102', 'bloco D', 'local'),
(20, 'C2', NULL, NULL, NULL, 'AB', 'Escada', 'Escada de acesso ao andar inferior', 'bloco D', 'alerta'),
(21, 'B1', 'AB', 'C9', NULL, 'C6', 'Caminho', 'Hall do bloco D', 'bloco D', 'caminho'),
(22, 'C6', NULL, NULL, 'B1', 'C1', NULL, NULL, 'bloco D', 'caminho'),
(23, 'C1', NULL, NULL, 'C6', NULL, 'Caminho', 'Salão do Bloco D', 'bloco D', 'caminho'),
(24, 'A7', 'C6', NULL, NULL, NULL, 'Bebedouro', 'Bebedouro no Hall do Bloco D', 'bloco D', 'objeto'),
(25, 'A9', 'C1', NULL, NULL, NULL, 'Lixeiro', 'Lixeiro - Salão do Bloco D', 'bloco D', 'objeto'),
(26, 'C9', 'B1', 'BD', NULL, NULL, 'Portão', 'Portão', 'bloco D', 'alerta'),
(27, 'B2', 'BD', NULL, 'BB', 'BE', 'Caminho', 'Área dos departamentos - Bloco D', 'bloco D', 'caminho'),
(28, 'BB', NULL, 'BC', 'B2', 'B0', 'Caminho', 'Corredor de Departamentos (Matemática e Contábeis) no bloco D', 'bloco D', 'caminho'),
(30, 'A4', 'B2', NULL, NULL, NULL, 'Quadro de avisos', 'Quadro de aviso - Parede da área de departamentos no Bloco D', 'bloco D', 'objeto'),
(31, 'AB', 'A3', 'B1', 'C2', 'C0', 'Caminho', 'Corredor - Bloco D', 'bloco D', 'caminho'),
(32, 'C0', NULL, NULL, 'AB', NULL, 'Salão bloco D', 'Salão bloco D', 'bloco D', 'local'),
(33, 'BD', 'C9', 'B2', NULL, NULL, 'Caminho', 'Área dos departamentos - Bloco D', 'bloco D', 'caminho'),
(34, 'BC', 'BB', NULL, NULL, NULL, 'Sala de Professor - DMAI', 'Sala de professor', 'bloco D', 'local'),
(35, 'B0', NULL, NULL, 'BB', 'AC', 'Caminho', 'Corredor de Departamentos (Matemática e Contábeis) no bloco D', 'bloco D', 'caminho'),
(36, 'AC', NULL, 'C3', 'B0', NULL, 'Caminho', 'Corredor de Departamentos (Matemática e Contábeis) no bloco D', 'bloco D', 'caminho'),
(37, 'C3', 'AC', NULL, NULL, NULL, 'Departamento de Matemática', 'Departamento de Matemática', 'bloco D', 'local'),
(38, 'BE', NULL, NULL, 'AE', 'B2', 'Caminho', 'Corredor de departamentos (Sistemas de Informação e Química) - Bloco D', 'bloco D', 'caminho'),
(39, 'AE', NULL, 'BF', 'C7', 'BE', 'Caminho', 'Corredor dos Departamentos (Sistemas de Informação e Química) no Bloco D', 'bloco D', 'caminho'),
(40, 'BF', 'AE', NULL, NULL, NULL, 'Sala de Professor - DSI', 'Sala de Professor', 'bloco D', 'local'),
(41, 'C7', NULL, NULL, NULL, 'AE', 'Departamento de Sistemas de Informação', 'Departamento de Sistemas de Informação', 'bloco D', 'local');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_tag`
--
ALTER TABLE `tb_tag`
  ADD PRIMARY KEY (`id_tag`),
  ADD UNIQUE KEY `cod_tag_UNIQUE` (`cod_tag`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_tag`
--
ALTER TABLE `tb_tag`
  MODIFY `id_tag` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
