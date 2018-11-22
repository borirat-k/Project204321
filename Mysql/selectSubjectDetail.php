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

$user_id = $_POST["user_id"];
$semester = $_POST['semester'];
// $user_id = 3;
// $semester = '1/2561';
$sql = "select * from teach where Tid = $user_id and semester like '$semester'";

$result1 = $conn->query($sql);
if($result1->num_rows > 0){
    $course = array();
    while($row = mysqli_fetch_array($result1)){
        $idCourse = $row["Cid"];
        array_push($course,array("idCourse"=>$idCourse));
    }
    echo json_encode($course);
    // for ($x = 0; $x <= 10; $x++) {
    // echo $flag[$x];
    // }
}
$conn->close();
 ?>
