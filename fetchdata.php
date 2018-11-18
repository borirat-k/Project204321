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
$tc_id = $_POST["tc_id"];
$sql = "select course.Cid,course.time_teach,course.date_teach,teach_schedule.semester from course join select_in on course.Cid = select_in.Cid join teach_schedule on select_in.tc_id = teach_schedule.tc_id where teach_schedule.tc_id = $tc_id";
$result1 = $conn->query($sql);

if($result1->num_rows > 0){
    $course = array();
    while($row = mysqli_fetch_array($result1)){
        $idCourse = $row["Cid"];
        $time_teach = $row["time_teach"];
        $date_teach = $row["date_teach"];
        $semester = $row["semester"];

        array_push($course,array("idCourse"=>$idCourse,"time_teach"=>$time_teach,"date_teach"=> $date_teach,"semester"=>$semester));
    }

    echo json_encode($course);

    // for ($x = 0; $x <= 10; $x++) {
    // echo $flag[$x];
    // }
}
$conn->close();
 ?>
