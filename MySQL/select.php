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
$Gid = $_POST["Gid"];

$sql = "select * from chat Where Gid = '$Gid' Order by Date_c,Time ASC";
$result1 = $conn->query($sql);

if($result1->num_rows > 0){
    $course = array();
    while($row = mysqli_fetch_array($result1)){
        $Tid = $row["Tid"];
        $Gid = $row["Gid"];
        $Message = $row["Message"];
        $Date_c = $row["Date_c"];
        $Time = $row["Time"];


        array_push($course,array("Tid"=>$Tid ,"Gid"=>$Gid,"Message"=>$Message,"Date_c"=>$Date_c,"Time"=>$Time));
    }
    echo json_encode($course);
    // for ($x = 0; $x <= 10; $x++) {
    // echo $flag[$x];
    // }
}
$conn->close();
 ?>