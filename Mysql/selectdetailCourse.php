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


$Cid = $_POST['Cid'];

$sql = "select * from course where Cid like  '$Cid' ";

$result1 = $conn->query($sql);
if($result1->num_rows > 0){
    $course = array();
    while($row = mysqli_fetch_array($result1)){
        $idCourse = $row["Cid"];
        $time_teach = $row["time_teach"];
        $date_teach = $row["date_teach"];

        array_push($course,array("idCourse"=>$idCourse,"time_teach"=>$time_teach,"date_teach"=>$date_teach));
    }
    echo json_encode($course);
    // for ($x = 0; $x <= 10; $x++) {
    // echo $flag[$x];
    // }
}
$conn->close();
 ?>
