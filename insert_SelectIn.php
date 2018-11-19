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

$tc_id = $_POST["tc_id"];
$Cid = $_POST["Cid"];


$sql1 = "insert into select_in values ($tc_id,$Cid)";
$result1 = $conn->query($sql1);

$course = array();
if($result1->num_rows > 0){
    $course = array();
    array_push($course,array("success"->"insert successful"));
    }
    echo json_encode($course);
}
$conn->close();
 ?>
