<?php
	$host='127.0.0.1';
	$username='root';
	$pwd='';
	$db='cmucats';

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
	$Aid = $_POST['Aid'];
	
	$sql = "DELETE FROM `assignment` 
			WHERE `assignment`.`Cid` = '$Cid' AND `assignment`.`Aid` = '$Aid';";
	
	mysqli_query($con, $sql) or die (mysqli_error($con));
	
	mysqli_close($con);
?>