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


$Tid = $_POST['user_id'];
$semester = $_POST['semester'];
$Cid = $_POST["Cid"];

// $sql = "select tc_id from teach_schedule where Tid = $Tid and semester like '$semester'";
$sql = "select tc_id from teach_schedule where Tid = '$Tid' and semester like '$semester'";
$result1 = $conn->query($sql);
if($result1->num_rows > 0){
	while($row = mysqli_fetch_array($result1)){
	$tc_id = $row["tc_id"];
	}
}
// echo $tc_id;
$sql1 = "INSERT INTO select_in(tc_id,Cid)VALUES('$tc_id','$Cid')";
$result = $conn->query($sql1);

$conn->close();
?>
 
