-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2018 at 10:18 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aliens`
--

-- --------------------------------------------------------

--
-- Table structure for table `alien`
--

CREATE TABLE `alien` (
  `aid` int(4) NOT NULL,
  `alienName` varchar(15) NOT NULL,
  `age` int(4) NOT NULL,
  `marks` int(4) NOT NULL,
  `city` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alien`
--

INSERT INTO `alien` (`aid`, `alienName`, `age`, `marks`, `city`) VALUES
(1, 'Ankit Makwana', 24, 70, 'Mumai'),
(2, 'Ankit Makwana', 20, 60, 'Mumai'),
(3, 'Ankit Makwana', 20, 60, 'Mumai'),
(4, 'Devang', 25, 68, 'Mumbai'),
(5, 'Hiren', 35, 80, 'Delhi'),
(6, 'Kamlesh', 15, 70, 'Delhi'),
(7, 'Vijay', 17, 60, 'Delhi'),
(8, 'Sneha', 18, 65, 'Mumbai'),
(9, 'Sagar', 14, 45, 'Mumbai'),
(10, 'mayur', 15, 45, 'Mumbai'),
(11, 'Pooja', 18, 95, 'Mumbai'),
(12, 'vishal', 22, 80, 'Surat'),
(13, 'Vikas', 23, 70, 'Mumbai'),
(14, 'sameer', 25, 80, 'Delhi'),
(15, 'jigar', 27, 75, 'Mumbai'),
(16, 'Vijay', 29, 45, 'Delhi'),
(17, 'Naman', 28, 85, 'Surat'),
(18, 'Siffa', 32, 89, 'Punjab'),
(19, 'Ritu', 33, 45, 'Mumbai'),
(20, 'jigar', 39, 90, 'Mumbai'),
(21, 'Vijay', 49, 65, 'Mumbai'),
(22, 'jigar', 52, 80, 'Mumbai'),
(23, 'Kamlesh', 55, 70, 'Delhi'),
(24, 'Devang', 47, 65, 'Mumbai'),
(25, 'Komal', 49, 58, 'Mumbai'),
(26, 'Sejal', 58, 78, 'Mumbai'),
(27, 'Vijay', 25, 98, 'Mumbai'),
(28, 'Vaibhav', 32, 97, 'Delhi'),
(29, 'viral', 25, 78, 'Mumbai'),
(30, 'Vimal', 56, 78, 'Punjab'),
(31, 'varsha', 19, 46, 'Punjab'),
(32, 'Siddhesh', 42, 49, 'Punjab'),
(33, 'vishal', 39, 80, 'Mumbai'),
(34, 'Devang', 39, 90, 'Delhi'),
(35, 'Vikrant', 39, 90, 'Punjab');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alien`
--
ALTER TABLE `alien`
  ADD PRIMARY KEY (`aid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
