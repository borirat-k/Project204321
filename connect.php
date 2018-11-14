<?php
    $host = "localhost";
    $db = "cmucats";
    $username = "root";
    $password = "";


    $conn = mysqli_connect($host,$username,$password,$db);

    if($conn){
        $q = "select * from student";
        $result = mysqli_query($conn,$q);
    }
    
    echo "login successful...!";





?>