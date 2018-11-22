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


$Tid = $_POST["Tid"];
$Gid = $_POST["Gid"];
$Message = $_POST["Message"];
$Date_c = $_POST["Date_c"];
$Time = $_POST["Time"];


$sql1 = "INSERT INTO chat(Tid,Gid,Message,Date_c,Time) VALUES ($Tid,'$Gid','$Message','$Date_c','$Time')";
$result1 = $conn->query($sql1);

$conn->close();
 ?>