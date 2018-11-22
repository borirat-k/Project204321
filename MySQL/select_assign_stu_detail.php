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
	$Stu_id = $_POST['Stu_id'];
	
	$sql = "SELECT DISTINCT title, fname, lname, max_score, score_correct
			FROM student as stu, assignment as a, checked as c, study s
			where c.cid = '$Cid' and c.aid = '$Aid' and stu.stu_id = '$Stu_id' and 
			c.Cid = a.Cid and a.Cid = s.Cid and c.Aid = a.Aid and c.Stu_id = s.Stu_id and s.Stu_id = stu.stu_id";
	// $sql = "SELECT *
			// FROM student as stu, assignment as a, checked as c, study s
			// where c.cid = '204100' and c.aid = 'Lec1' and stu.stu_id = '610710051' and 
			// c.Cid = a.Cid and a.Cid = s.Cid and c.Aid = a.Aid and c.Stu_id = s.Stu_id and s.Stu_id = stu.stu_id";
	
	$result=mysqli_query($con, $sql);
	if($result){
		while($row=mysqli_fetch_array($result)){
			$flag[]=$row;
		}
		
		print(json_encode($flag));
	}
	
	mysqli_close($con);
?>