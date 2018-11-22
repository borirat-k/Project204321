-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2018 at 11:48 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cmucats`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

CREATE TABLE `assignment` (
  `Cid` char(6) NOT NULL,
  `Aid` varchar(20) NOT NULL,
  `start_date` date NOT NULL,
  `Dead_line` date NOT NULL,
  `max_score` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`Cid`, `Aid`, `start_date`, `Dead_line`, `max_score`) VALUES
('204321', '#1', '2018-08-14', '2018-08-21', 55),
('204321', '#4', '2018-11-21', '2018-11-21', 55),
('204111', 'HW01', '2018-08-19', '2018-08-30', 20),
('204111', 'HW02', '2018-11-20', '2018-11-20', 50),
('204100', 'Lec1', '2018-10-18', '2018-10-28', 100),
('204100', 'Lec2', '2018-11-15', '2018-10-20', 66),
('204100', 'Lec3', '2018-11-20', '2018-11-20', 50);

-- --------------------------------------------------------

--
-- Table structure for table `attend`
--

CREATE TABLE `attend` (
  `stu_id` int(9) NOT NULL,
  `Cid` char(6) NOT NULL,
  `att_id` int(3) NOT NULL,
  `att_date` date NOT NULL,
  `att_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `Cid` char(6) NOT NULL,
  `att_id` int(3) NOT NULL,
  `Date_at` date NOT NULL,
  `Hno` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `attend_time`
--

CREATE TABLE `attend_time` (
  `att_time` time NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE `chat` (
  `Tid` int(3) NOT NULL,
  `Gid` varchar(10) NOT NULL,
  `Message` text NOT NULL,
  `Date_c` date NOT NULL,
  `Time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chat`
--

INSERT INTO `chat` (`Tid`, `Gid`, `Message`, `Date_c`, `Time`) VALUES
(3, '002', 'Ok krub', '2018-11-20', '00:00:00'),
(1, '002', 'jfifydd', '2018-11-20', '08:15:43'),
(1, '002', 'sawad dee', '2018-11-20', '08:16:20'),
(1, '002', 'hello ', '2018-11-20', '08:17:34'),
(1, '002', 'ja', '2018-11-20', '08:23:33'),
(3, '002', 'sawaddee', '2018-11-20', '13:06:16'),
(3, '002', 'krub', '2018-11-20', '13:24:39'),
(2, '003', 'Ok', '2018-11-20', '13:32:21'),
(3, '003', 'Ok mai krub', '2018-11-20', '13:37:56'),
(3, '002', 'Hello  204321', '2018-11-20', '14:12:59'),
(5, '002', 'New Message', '2018-11-20', '14:18:00'),
(3, '003', 'Krub pom', '2018-11-22', '15:53:55');

-- --------------------------------------------------------

--
-- Table structure for table `checked`
--

CREATE TABLE `checked` (
  `Stu_id` int(9) NOT NULL,
  `Tid` int(3) NOT NULL,
  `Cid` char(6) NOT NULL,
  `Aid` varchar(20) NOT NULL,
  `score_correct` int(3) NOT NULL,
  `date` date NOT NULL,
  `picname_full` text NOT NULL,
  `score_original` int(3) NOT NULL,
  `picname_score_front` text NOT NULL,
  `picname_score_back` text NOT NULL,
  `stu_id_original` int(9) NOT NULL,
  `picname_stu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `checked`
--

INSERT INTO `checked` (`Stu_id`, `Tid`, `Cid`, `Aid`, `score_correct`, `date`, `picname_full`, `score_original`, `picname_score_front`, `picname_score_back`, `stu_id_original`, `picname_stu`) VALUES
(581610238, 3, '204100', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581610238, 3, '204100', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581610238, 3, '204100', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581610262, 3, '204100', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581610262, 3, '204100', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581610262, 3, '204100', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581810057, 3, '204100', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581810057, 3, '204100', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581810057, 3, '204100', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581810122, 3, '204100', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581810122, 3, '204100', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(581810122, 3, '204100', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510525, 3, '204321', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510525, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510525, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510525, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510526, 3, '204321', '#1', 48, '2018-08-21', '', 0, '', '', 0, ''),
(590510526, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510526, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510526, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510528, 3, '204321', '#1', 44, '2018-08-21', '', 0, '', '', 0, ''),
(590510528, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510528, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510528, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510529, 3, '204321', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510529, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510529, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510529, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510530, 3, '204321', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510530, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510530, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510530, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510531, 3, '204321', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510531, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510531, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510531, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510532, 3, '204321', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510532, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510532, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510532, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510533, 3, '204321', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510533, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510533, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510533, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510538, 3, '204321', '#1', 43, '2018-08-21', '', 0, '', '', 0, ''),
(590510538, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510538, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510538, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510540, 3, '204321', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510540, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510540, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510540, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510543, 3, '204321', '#1', 43, '2018-08-21', '', 0, '', '', 0, ''),
(590510543, 3, '204321', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510543, 3, '204321', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(590510543, 3, '204321', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(591810288, 3, '204100', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(591810288, 3, '204100', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(591810288, 3, '204100', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510654, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510654, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510654, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510654, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510655, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510655, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510655, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510655, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510656, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510656, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510656, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510656, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510657, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510657, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510657, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510657, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510658, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510658, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510658, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510658, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510659, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510659, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510659, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510659, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510660, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510660, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510660, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510660, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510661, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510661, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510661, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510661, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510678, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510678, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510678, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510678, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510679, 3, '204111', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510679, 3, '204111', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510679, 3, '204111', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610510679, 3, '204111', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610710051, 3, '204100', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610710051, 3, '204100', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610710051, 3, '204100', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610710051, 3, '204100', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610710051, 5, '204100', 'Lec1', 72, '2018-09-14', '', 0, '', '', 0, ''),
(610710176, 3, '204100', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610710176, 3, '204100', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610710176, 3, '204100', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610710176, 3, '204100', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(610710176, 5, '204100', 'Lec1', 18, '2018-09-17', '', 0, '', '', 0, ''),
(611610158, 3, '204100', '#1', 0, '0000-00-00', '', 0, '', '', 0, ''),
(611610158, 3, '204100', '#4', 0, '0000-00-00', '', 0, '', '', 0, ''),
(611610158, 3, '204100', 'HW02', 0, '0000-00-00', '', 0, '', '', 0, ''),
(611610158, 3, '204100', 'Lec3', 0, '0000-00-00', '', 0, '', '', 0, ''),
(611610158, 5, '204100', 'Lec1', 69, '2018-09-15', '', 0, '', '', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `Cid` char(6) NOT NULL,
  `C_name` varchar(100) NOT NULL,
  `time_teach` varchar(15) NOT NULL,
  `date_teach` varchar(4) NOT NULL,
  `stu_amount` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`Cid`, `C_name`, `time_teach`, `date_teach`, `stu_amount`) VALUES
('204100', 'Information Technology and Modern Life', '12.30-14.30', 'TuF', 85),
('204111', 'Fundamentals of Programming', '09.30-11.30', 'TuF', 28),
('204321', 'Database System1', '13.00-14.30', 'TuF', 26),
('204383', 'operating system', '11.00-12.30', 'MTh', 50);

-- --------------------------------------------------------

--
-- Table structure for table `course_cmu`
--

CREATE TABLE `course_cmu` (
  `cmuc_id` char(6) NOT NULL,
  `Mid_time` time NOT NULL,
  `Final_time` time NOT NULL,
  `Mid_date` date NOT NULL,
  `Final_date` date NOT NULL,
  `ts_id` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `group_chat`
--

CREATE TABLE `group_chat` (
  `Gid` varchar(10) NOT NULL,
  `G_name` varchar(100) NOT NULL,
  `Cid` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `group_chat`
--

INSERT INTO `group_chat` (`Gid`, `G_name`, `Cid`) VALUES
('001', 'Information Technology and Modern Life Group', '204100'),
('002', 'Fundamentals of Programming Group', '204111'),
('003', 'Database System1 Group', '204321');

-- --------------------------------------------------------

--
-- Table structure for table `holiday`
--

CREATE TABLE `holiday` (
  `Holiday_no` int(3) NOT NULL,
  `Hname` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `p_location`
--

CREATE TABLE `p_location` (
  `att_id` int(3) NOT NULL,
  `location_x` float NOT NULL,
  `location_y` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `register_in`
--

CREATE TABLE `register_in` (
  `stu_id` int(9) NOT NULL,
  `cmuc_id` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `select_in`
--

CREATE TABLE `select_in` (
  `tc_id` int(3) NOT NULL,
  `Cid` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `select_in`
--

INSERT INTO `select_in` (`tc_id`, `Cid`) VALUES
(8, '204111'),
(10, '204111');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `stu_id` int(9) NOT NULL,
  `Title` char(6) NOT NULL,
  `Fname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `Email` text NOT NULL,
  `Class` int(1) NOT NULL,
  `Degree` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`stu_id`, `Title`, `Fname`, `Lname`, `Email`, `Class`, `Degree`) VALUES
(581610238, 'นาย', 'ภานุวัฒน์', 'พิมสาร', '', 4, 'ปริญญาตรี'),
(581610262, 'น.ส', 'วนิดา', 'ปลงใจ', '', 4, 'ปริญญาตรี'),
(581810057, 'น.ส.', 'ชนฐิตา', 'ไกรศรีกุล', '', 4, 'ปริญญาตรี'),
(581810122, 'น.ส.', 'นันทพร', 'สถิรปัญญา', '', 4, 'ปริญญาตรี'),
(590510525, 'นาย', 'พิศลย์', 'เรือนอินทร์', '', 3, 'ปริญญาตรี'),
(590510526, 'นาย', 'ศรายุธ', 'เตชะแก้ว', '', 3, 'ปริญญาตรี'),
(590510528, 'นางสาว', 'กฤตยาณี', 'เขื่อนแก้ว', '', 3, 'ปริญญาตรี'),
(590510529, 'นาย', 'กฤษดา', 'บุญทอง', '', 3, 'ปริญญาตรี'),
(590510530, 'นางสาว', 'กวิสรา', 'ทองดีเลิศ', '', 3, 'ปริญญาตรี'),
(590510531, 'นาย', 'กิตติโชติ', 'พฤทธิพงศ์เดชา', '', 3, 'ปริญญาตรี'),
(590510532, 'นาย', 'คุณานนต์', 'ชุมแสง', '', 3, 'ปริญญาตรี'),
(590510533, 'นาย', 'จักรพงษ์', 'หลวงพงษ์', '', 3, 'ปริญญาตรี'),
(590510538, 'นาย', 'ณฐกร', 'ภักดียนต์เจริญ', '', 3, 'ปริญญาตรี'),
(590510540, 'นาย', 'ณัฐชนน', 'ต้อนรับ', '', 3, 'ปริญญาตรี'),
(590510543, 'นาย', 'ณัฐวุฒิ', 'สุแก้ว', '', 3, 'ปริญญาตรี'),
(591810288, 'นาย', 'ศิริมงคล', 'ญาณะก๋อง', '', 3, 'ปริญญาตรี'),
(600510591, 'นาย', 'สมพักตร์', 'สูงส่ง', 'Sompak@gmail.com', 2, 'ปริญญาตรี'),
(610510654, 'นางสาว', 'นนทรพร', 'จันทร์มณีวงศ์', '', 1, 'ปริญญาตรี'),
(610510655, 'นางสาว', 'นุชธิดา', 'มีโพธิ์', '', 1, 'ปริญญาตรี'),
(610510656, 'นางสาว', 'ประดุจดาว', 'เมาจา', '', 1, 'ปริญญาตรี'),
(610510657, 'นางสาว', 'ปรียารัตน์', 'จับจ่าย', '', 1, 'ปริญญาตรี'),
(610510658, 'นาย', 'ปัฐยาวัศ', 'คำฟู', '', 1, 'ปริญญาตรี'),
(610510659, 'นาย', 'ปัณณวิชญ์', 'จันทร์ดวงโอด', '', 1, 'ปริญญาตรี'),
(610510660, 'นางสาว', 'ปาณิศา', 'จันทมาศ', '', 1, 'ปริญญาตรี'),
(610510661, 'นาย', 'พงศธร', 'สาริขิต', '', 1, 'ปริญญาตรี'),
(610510678, 'นางสาว', 'ขวัญจิรา', 'กาเกตุ\r\n', '', 1, 'ปริญญาตรี'),
(610510679, 'นาย', 'จาตุรนต์', 'วงศ์เศรษฐี\r\n', '', 1, 'ปริญญาตรี'),
(610510889, 'นาย', 'สมพง', 'ทำดี', 'Sompong@gmail.com', 1, 'ปริญญาตรี'),
(610510890, 'นาย', 'สมชาย', 'ทรายทอง', 'Somchai@gmail.com', 1, 'ปริญญาตรี'),
(610710051, 'น.ส.', 'ณัฐพร', 'กลไกรศาสตร์', '', 1, 'ปริญญาตรี'),
(610710176, 'นาย', 'ธนภัทร', 'มุนินทร์', '', 1, 'ปริญญาตรี'),
(611610158, 'นาย', 'สหัสวรรษ', 'ปามา', '', 1, 'ปริญญาตรี');

-- --------------------------------------------------------

--
-- Table structure for table `study`
--

CREATE TABLE `study` (
  `Stu_id` int(9) NOT NULL,
  `Cid` char(6) NOT NULL,
  `Sec` char(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `study`
--

INSERT INTO `study` (`Stu_id`, `Cid`, `Sec`) VALUES
(581610238, '204100', '006'),
(581610262, '204100', '006'),
(581810057, '204100', '006'),
(581810122, '204100', '006'),
(590510525, '204321', '001'),
(590510526, '204321', '001'),
(590510528, '204321', '001'),
(590510529, '204321', '001'),
(590510530, '204321', '001'),
(590510531, '204321', '001'),
(590510532, '204321', '001'),
(590510533, '204321', '001'),
(590510538, '204321', '001'),
(590510540, '204321', '001'),
(590510543, '204321', '001'),
(591810288, '204100', '006'),
(610510654, '204111', '001'),
(610510655, '204111', '001'),
(610510656, '204111', '001'),
(610510657, '204111', '001'),
(610510658, '204111', '001'),
(610510659, '204111', '001'),
(610510660, '204111', '001'),
(610510661, '204111', '001'),
(610510678, '204111', '001'),
(610510679, '204111', '001'),
(610710051, '204100', '006'),
(610710176, '204100', '006'),
(611610158, '204100', '006');

-- --------------------------------------------------------

--
-- Table structure for table `teach`
--

CREATE TABLE `teach` (
  `Tid` int(3) NOT NULL,
  `Cid` char(6) NOT NULL,
  `Sec` int(2) NOT NULL,
  `Semester` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teach`
--

INSERT INTO `teach` (`Tid`, `Cid`, `Sec`, `Semester`) VALUES
(3, '204111', 1, '1/2562'),
(3, '204321', 1, '1/2561'),
(3, '204383', 1, '1/2561'),
(5, '204100', 6, '1/2561');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `Tid` int(3) NOT NULL,
  `Title` char(6) NOT NULL,
  `Fname` varchar(100) NOT NULL,
  `Lname` varchar(100) NOT NULL,
  `Email` text NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`Tid`, `Title`, `Fname`, `Lname`, `Email`, `Password`) VALUES
(1, 'ดร.', 'จักริน', 'ชวชาติ', 'jakarin.c@cmu.ac.th', 'jakarin111'),
(2, 'ดร.', 'วิจักษณ์', 'ศรีสัจจะเลิศวาจา', 'wijak.cscmu@gmail.com', 'wijak33'),
(3, 'ดร.', 'อารีรัตน์', 'ตรงรัศมีทอง', 'areerat.t@cmu.ac.th', 'areerat123'),
(4, 'นาง', 'เบญจมาศ', 'ปัญญางาม', 'Benjamas@cmu.ac.th', '12345'),
(5, 'ดร.', 'ประภาพร', 'เตชอังกูร', 'prapaporn.techaang@cmu.ac.th', 'prapaporn1234');

-- --------------------------------------------------------

--
-- Table structure for table `teach_schedule`
--

CREATE TABLE `teach_schedule` (
  `tc_id` int(3) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `Tid` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teach_schedule`
--

INSERT INTO `teach_schedule` (`tc_id`, `semester`, `Tid`) VALUES
(8, '1/2561', 3),
(9, '1/2562', 3),
(10, '1/2562', 3);

-- --------------------------------------------------------

--
-- Table structure for table `test_schedule`
--

CREATE TABLE `test_schedule` (
  `ts_id` char(6) NOT NULL,
  `Type` varchar(10) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `Tid` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `test_schedule`
--

INSERT INTO `test_schedule` (`ts_id`, `Type`, `semester`, `Tid`) VALUES
('111001', 'Midterm', '1/2561', 1),
('111002', 'Final', '1/2561', 1),
('111003', 'Midterm', '1/2561', 2),
('111004', 'Final', '1/2561', 2),
('111005', 'Midterm', '1/2561', 3),
('111006', 'Final', '1/2561', 3);

-- --------------------------------------------------------

--
-- Table structure for table `view_group_chat`
--

CREATE TABLE `view_group_chat` (
  `Tid` int(3) NOT NULL,
  `Gid` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `Time` time NOT NULL,
  `V_status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assignment`
--
ALTER TABLE `assignment`
  ADD PRIMARY KEY (`Aid`,`Cid`) USING BTREE,
  ADD KEY `Cid` (`Cid`);

--
-- Indexes for table `attend`
--
ALTER TABLE `attend`
  ADD PRIMARY KEY (`att_id`,`stu_id`,`Cid`),
  ADD KEY `stu_id` (`stu_id`),
  ADD KEY `Cid` (`Cid`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`att_id`,`Cid`) USING BTREE,
  ADD KEY `Cid` (`Cid`),
  ADD KEY `Hno` (`Hno`);

--
-- Indexes for table `attend_time`
--
ALTER TABLE `attend_time`
  ADD PRIMARY KEY (`att_time`);

--
-- Indexes for table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`Date_c`,`Time`,`Tid`,`Gid`) USING BTREE,
  ADD KEY `Tid` (`Tid`),
  ADD KEY `Gid` (`Gid`);

--
-- Indexes for table `checked`
--
ALTER TABLE `checked`
  ADD PRIMARY KEY (`Stu_id`,`Tid`,`Aid`,`Cid`),
  ADD KEY `Tid` (`Tid`),
  ADD KEY `Aid` (`Aid`),
  ADD KEY `Cid` (`Cid`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`Cid`);

--
-- Indexes for table `course_cmu`
--
ALTER TABLE `course_cmu`
  ADD PRIMARY KEY (`cmuc_id`),
  ADD KEY `course_cmu_ibfk_1` (`ts_id`);

--
-- Indexes for table `group_chat`
--
ALTER TABLE `group_chat`
  ADD PRIMARY KEY (`Gid`),
  ADD KEY `group_chat_ibfk_1` (`Cid`);

--
-- Indexes for table `holiday`
--
ALTER TABLE `holiday`
  ADD PRIMARY KEY (`Holiday_no`);

--
-- Indexes for table `p_location`
--
ALTER TABLE `p_location`
  ADD PRIMARY KEY (`att_id`,`location_x`,`location_y`);

--
-- Indexes for table `register_in`
--
ALTER TABLE `register_in`
  ADD PRIMARY KEY (`stu_id`,`cmuc_id`),
  ADD KEY `register_in_ibfk_2` (`cmuc_id`);

--
-- Indexes for table `select_in`
--
ALTER TABLE `select_in`
  ADD PRIMARY KEY (`tc_id`,`Cid`),
  ADD KEY `select_in_ibfk_1` (`Cid`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`stu_id`) USING BTREE;

--
-- Indexes for table `study`
--
ALTER TABLE `study`
  ADD PRIMARY KEY (`Stu_id`,`Cid`),
  ADD KEY `Cid` (`Cid`);

--
-- Indexes for table `teach`
--
ALTER TABLE `teach`
  ADD PRIMARY KEY (`Tid`,`Cid`,`Semester`) USING BTREE,
  ADD KEY `Cid` (`Cid`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`Tid`);

--
-- Indexes for table `teach_schedule`
--
ALTER TABLE `teach_schedule`
  ADD PRIMARY KEY (`tc_id`),
  ADD KEY `Tid` (`Tid`);

--
-- Indexes for table `test_schedule`
--
ALTER TABLE `test_schedule`
  ADD PRIMARY KEY (`ts_id`),
  ADD KEY `Tid` (`Tid`);

--
-- Indexes for table `view_group_chat`
--
ALTER TABLE `view_group_chat`
  ADD PRIMARY KEY (`Tid`,`Gid`,`date`,`Time`),
  ADD KEY `view_group_chat_ibfk_2` (`Gid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `holiday`
--
ALTER TABLE `holiday`
  MODIFY `Holiday_no` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `Tid` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `teach_schedule`
--
ALTER TABLE `teach_schedule`
  MODIFY `tc_id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignment`
--
ALTER TABLE `assignment`
  ADD CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`Cid`) REFERENCES `course` (`Cid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attend`
--
ALTER TABLE `attend`
  ADD CONSTRAINT `attend_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `attend_ibfk_2` FOREIGN KEY (`Cid`) REFERENCES `course` (`Cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `attend_ibfk_3` FOREIGN KEY (`att_id`) REFERENCES `attendance` (`att_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`Cid`) REFERENCES `course` (`Cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`Hno`) REFERENCES `holiday` (`Holiday_no`);

--
-- Constraints for table `chat`
--
ALTER TABLE `chat`
  ADD CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`Tid`) REFERENCES `teacher` (`Tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `chat_ibfk_2` FOREIGN KEY (`Gid`) REFERENCES `group_chat` (`Gid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `checked`
--
ALTER TABLE `checked`
  ADD CONSTRAINT `checked_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `checked_ibfk_2` FOREIGN KEY (`Tid`) REFERENCES `teacher` (`Tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `checked_ibfk_3` FOREIGN KEY (`Aid`) REFERENCES `assignment` (`Aid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `checked_ibfk_4` FOREIGN KEY (`Cid`) REFERENCES `course` (`Cid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `course_cmu`
--
ALTER TABLE `course_cmu`
  ADD CONSTRAINT `course_cmu_ibfk_1` FOREIGN KEY (`ts_id`) REFERENCES `test_schedule` (`ts_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `group_chat`
--
ALTER TABLE `group_chat`
  ADD CONSTRAINT `group_chat_ibfk_1` FOREIGN KEY (`Cid`) REFERENCES `course` (`Cid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `p_location`
--
ALTER TABLE `p_location`
  ADD CONSTRAINT `p_location_ibfk_1` FOREIGN KEY (`att_id`) REFERENCES `attendance` (`att_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `register_in`
--
ALTER TABLE `register_in`
  ADD CONSTRAINT `register_in_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `register_in_ibfk_2` FOREIGN KEY (`cmuc_id`) REFERENCES `course_cmu` (`cmuc_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `select_in`
--
ALTER TABLE `select_in`
  ADD CONSTRAINT `select_in_ibfk_1` FOREIGN KEY (`Cid`) REFERENCES `course` (`Cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `select_in_ibfk_2` FOREIGN KEY (`tc_id`) REFERENCES `teach_schedule` (`tc_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `study`
--
ALTER TABLE `study`
  ADD CONSTRAINT `study_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `study_ibfk_2` FOREIGN KEY (`Cid`) REFERENCES `course` (`Cid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teach`
--
ALTER TABLE `teach`
  ADD CONSTRAINT `teach_ibfk_1` FOREIGN KEY (`Tid`) REFERENCES `teacher` (`Tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `teach_ibfk_2` FOREIGN KEY (`Cid`) REFERENCES `course` (`Cid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teach_schedule`
--
ALTER TABLE `teach_schedule`
  ADD CONSTRAINT `teach_schedule_ibfk_1` FOREIGN KEY (`Tid`) REFERENCES `teacher` (`Tid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `test_schedule`
--
ALTER TABLE `test_schedule`
  ADD CONSTRAINT `test_schedule_ibfk_1` FOREIGN KEY (`Tid`) REFERENCES `teacher` (`Tid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `view_group_chat`
--
ALTER TABLE `view_group_chat`
  ADD CONSTRAINT `view_group_chat_ibfk_1` FOREIGN KEY (`Tid`) REFERENCES `teacher` (`Tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `view_group_chat_ibfk_2` FOREIGN KEY (`Gid`) REFERENCES `group_chat` (`Gid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
