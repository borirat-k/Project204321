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
	$Aid = $_POST['Aid'];
	// $Cid = 204100;
	// $Aid = 'Lec3';
	// $start_date = '2018-10-17';
	// $Dead_line = '2018-10-19';
	// $max_score = '50';
	
	$sql = "SELECT * FROM assignment WHERE Cid = '$Cid' and Aid = '$Aid'";
	// $sql = "INSERT INTO `assignment` (`Cid`, `Aid`, `start_date`, `Dead_line`, `max_score`) 
			// VALUES ('204100', 'Lec3', '2018-8-18', '2018-08-27', '50')";
	
	$result=mysqli_query($con, $sql);
	if($result){
		while($row=mysqli_fetch_array($result)){
			$flag[]=$row;
		}
		
		print(json_encode($flag));
	}
	mysqli_close($con);
?>