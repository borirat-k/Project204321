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
//$teacher_id = $_POST['user_id'];
$user_id = $_POST["user_id"];
$sql = "select * from teach_schedule where Tid = $user_id order by tc_id desc";
$result1 = $conn->query($sql);

if($result1->num_rows > 0){
    $course = array();
    while($row = mysqli_fetch_array($result1)){
        $tc_id = $row["tc_id"];
        $semester = $row["semester"];
        array_push($course,array("tc_id"=>$tc_id ,"semester"=>$semester));
    }

    echo json_encode($course);

    // for ($x = 0; $x <= 10; $x++) {
    // echo $flag[$x];
    // }
}
$conn->close();
 ?>
