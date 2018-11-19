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
	
	$sql = "SELECT * FROM teach , course where teach.Tid = '3' and teach.Cid = course.Cid";
	// $sql = "SELECT * FROM assignment WHERE Cid = '204100'";
	// $sql = "select * from assignment";
	
	$result=mysqli_query($con, $sql);
	if($result){
		while($row=mysqli_fetch_array($result)){
			$flag[]=$row;
		}
		
		print(json_encode($flag));
	}
	
	mysqli_close($con);
?>