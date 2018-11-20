<?php
	$host='127.0.0.1';
	$username='root';
	$pwd='';
	$db='cmucats';
	
	//$host='202.28.24.216';
	//$username='cmucats';
	//$pwd='CMUcats59';
	//$db='cmucats';

	error_reporting(0);
	$con = new mysqli ($host, $username, $pwd, $db) or die('Unable to connect');
	//$mysqli = new mysqli ("localhost", "root", "", "cmucats");
	//$mysqli = new mysqli ("ชื่อ Server", "ชื่อ User", "Password", "ชื่อฐานข้อมูล");
	
	if(mysqli_connect_errno($con)){ //ส่วนการตรวจสอบว่าเราเชื่อมฐานข้อมูลสำเร็จหรือไม่
		echo "Failed to Connect to Database"." ".mysqli_connect_error();
	}
	else{
		//echo "Great work!!"; //กรณีที่เชื่อต่อสำเร็จ
		mysqli_set_charset($con, "utf8");
	}
	
	$Cid = $_POST['Cid'];
	$Aid_old = $_POST['Aid_old'];
	$Aid_new = $_POST['Aid_new'];
	$Dead_line = $_POST['Dead_line'];
	$max_score = $_POST['max_score'];
	// $Cid = 204100;
	// $Aid = 'Lec3';
	// $start_date = '2018-10-17';
	// $Dead_line = '2018-10-19';
	// $max_score = '50';
	$sql = "UPDATE `assignment`
			SET `Aid` = '$Aid_new', 
			`Dead_line` = '$Dead_line', 
			`max_score` =  '$max_score'
			WHERE `assignment`.`Cid` = '$Cid' AND `assignment`.`Aid` = '$Aid_old'";
	// $sql = "INSERT INTO `assignment` (`Cid`, `Aid`, `start_date`, `Dead_line`, `max_score`) 
			// VALUES ('204100', 'Lec3', '2018-8-18', '2018-08-27', '50')";
	
	mysqli_query($con, $sql) or die (mysqli_error($con));
	
	// INSERT into checked (stu_id, tid, cid, aid)
	// SELECT s.Stu_id, t.Tid, s.Cid, a.Aid
	// from study as s, teacher as t, assignment as a
	// WHERE a.Aid = 'Lec1' and t.Tid = '5'
	mysqli_close($con);
?>