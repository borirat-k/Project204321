<?php
//getting user values
//$Gid=$_POST['Gid'];
//$Tid=$_POST['Tid'];
$message=$_POST['message'];
//$date_c=$_POST['date_c'];
//$time=$_POST['time'];


//array of responses
$output=array();

//require database
require_once('db.php');

//insert activity
//$conn=$dbh->prepare('INSERT INTO chat(Gid,Tid,message,date_c,time) VALUES (?,?,?,?,?)');
    $conn=$dbh->prepare('INSERT INTO chat(message) VALUES (?)');
 //binding parameters   
    //$conn->bindParam(1,$Gid);
    //$conn->bindParam(2,$Tid);
    $conn->bindParam(1,$message);
   // $conn->bindParam(4,$date_c);
    //$conn->bindParam(5,$time);

    $conn->execute();
    if($conn->rowCount() == 0)
    {
    $output['error'] = true;
    $output['message'] = "Sorry, Unable to add activity. Try Again";
    }
    elseif($conn->rowCount() !==0){
    $output['error'] = false;
    $output['message'] = "Activity Successfully Added";
    }
    
       echo json_encode($output);       

?>