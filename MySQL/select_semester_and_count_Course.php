<?php
$servername = "localhost";
$username = "root";
$password="";
$dbname ="cmucats";
//create connection
$conn = new mysqli($servername,$username,$password,$dbname);
//check connection
if($conn->connect_error)
    die("connection failed:".$conn->connect_error);
mysqli_set_charset($conn,"utf8");

$user_id = $_POST['user_id'];

$sql1="select DISTINCT(Semester) from teach";

$result1 = $conn->query($sql1);

if($result1->num_rows > 0){
    $course = array();
    while($row = mysqli_fetch_array($result1)){
        $semester = $row["Semester"];
		$sql2 = "select count(Cid) as count_course from teach where Tid = $user_id and Semester LIKE '$semester' ";
		$result2 = $conn->query($sql2);
		if($result2->num_rows > 0){
			while($row = mysqli_fetch_array($result2)){
				$count_course = $row['count_course'];
				array_push($course,array("semester"=>$semester,"count_course"=>$count_course));
			}
		}
    }
    echo json_encode($course);

}
$conn->close();
 ?>
