<?php
// require "ConnectDatabase.php";
//
// $count = 0;
// $sql = "insert into image(pathtoimage) values ('192.168.0.102/picture/$count')";
//
// $result = $conn->query($sql);


   $name = $_POST['name']#&#91;'name'&#93;; //image name
   $image = $_POST['image']#&#91;'image'&#93;; //image in string format



   // decode the image
   $decodedImage = base64_decode($image);

   // upload the image
   file_put_contents("pic/".$name.".jpg", $decodedImage);

 ?>
