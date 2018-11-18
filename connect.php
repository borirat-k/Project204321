<?php
    $host = "localhost";
    $db = "cmucats";
    $username = "root";
    $password = "";


    $con = mysqli_connect($host,$username,$password,$db) or Die('Unable to connect');


    if(mysqli_connect_errno($con)){
        echo "Failed to Connect to Database"." ".mysqli_connect_error();
    }
    else{
        echo "Great work!!"; 
        mysqli_set_charset($con, "utf8");
    }

    /*$Tid = $_POST["Tid"];
    $Gid = $_POST["Gid"];
    $Message = $_POST["Message"];
    $Date_c = $_POST["Date_c"];
    $Time = $_POST["Time"];*/

    $sql = "Select * From group_chat";
    //$query = "Insert into chat(Tid,Gid,Message,Date_c,Time) Values ('$Tid','$Gid','$Message','$Date_c','$Time');";

    $result=mysqli_query($con, $sql);
    if($result){
        while($row=mysqli_fetch_array($result)){
            $flag[]=$row;
        }
        
        print(json_encode($flag));
    }
    
    mysqli_close($con);



?>