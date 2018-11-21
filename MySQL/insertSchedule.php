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
$semester = $_POST['semester'];
$user_id = $_POST["user_id"];
$sql1 = "insert into teach_schedule(semester,Tid) values ('$semester',$user_id)";
$result1 = $conn->query($sql1);
//$course = array();
//if($result1->num_rows > 0  ){
  //  $course = array();
    //array_push($course,array("success"->"insert successful"));
//}
//echo json_encode($course);

$conn->close();
 ?>