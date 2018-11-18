-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 09, 2018 at 11:22 AM
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
('204111', 'HW01', '2018-08-15', '2018-08-22', 20),
('204111', 'HW02', '2018-08-23', '2018-08-30', 20);

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

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `Cid` char(6) NOT NULL,
  `C_name` varchar(100) NOT NULL,
  `time_teach` varchar(15) NOT NULL,
  `date_teach` varchar(3) NOT NULL,
  `stu_amount` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`Cid`, `C_name`, `time_teach`, `date_teach`, `stu_amount`) VALUES
('204111', 'Fundamentals of Programming', '0930 - 1130', '000', 40);

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
(600510591, 'นาย', 'สมพักตร์', 'สูงส่ง', 'Sompak@gmail.com', 2, 'ปริญญาตรี'),
(610510889, 'นาย', 'สมพง', 'ทำดี', 'Sompong@gmail.com', 1, 'ปริญญาตรี'),
(610510890, 'นาย', 'สมชาย', 'ทรายทอง', 'Somchai@gmail.com', 1, 'ปริญญาตรี');

-- --------------------------------------------------------

--
-- Table structure for table `study`
--

CREATE TABLE `study` (
  `Stu_id` int(9) NOT NULL,
  `Cid` char(6) NOT NULL,
  `Sec` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(1, 'นาย', 'ประหยัด', 'ปวงจักร์ทา', 'Boss@gmail.com', 'Bossprayat'),
(2, 'นาย', 'รัฐพล', 'นันทวงศ์', 'Earth@gmail.com', 'Earth1234'),
(3, 'นาย', 'บริรัฐ', 'คำปิงยศ', 'Tee@gmail.com', 'Tee87654');

-- --------------------------------------------------------

--
-- Table structure for table `teach_schedule`
--

CREATE TABLE `teach_schedule` (
  `tc_id` int(3) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `Tid` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ADD PRIMARY KEY (`Tid`,`Cid`),
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
  MODIFY `Tid` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `teach_schedule`
--
ALTER TABLE `teach_schedule`
  MODIFY `tc_id` int(3) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `select_in_ibfk_2` FOREIGN KEY (`tc_id`) REFERENCES `teach_schedule` (`tc_id`);

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
