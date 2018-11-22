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
$att_time = $_POST['att_time'];
$sql1 = "insert into attend_time(att_time) values ('$att_time')";
$result1 = $conn->query($sql1);
//$course = array();
//if($result1->num_rows > 0  ){
  //  $course = array();
    //array_push($course,array("success"->"insert successful"));
//}
//echo json_encode($course);

$conn->close();
 ?>