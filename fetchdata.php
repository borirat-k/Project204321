<?php
class Constants
{
    static $db_server = "localhost";
    static $db_name = "cmucats";
    static $username = "root";
    static $password = "";

    static $sql_select_all = "select * from student";
}

class quotes
{
    public function connect()
    {
        $conn =new mysqli(Constants::$db_server,Constants::$username,Constants::$password,Constants::$db_name);
        if($conn->connect_error){
            echo "unable to connect";
        }
        else
        {
            return $conn;
        }
    }

    public function select(){
        $conn = $this->connect();
        if($conn != null){
            mysqli_set_charset($conn, "utf8");
            $result = $conn->query(Constants::$sql_select_all);
            if($result->num_rows>0)
            {
                $quotes = array();
                while($row = $result->fetch_array()){
                    array_push($quotes,array("stu_id"=>$row['stu_id'],"title"=>$row['Title'],
                "Fname"=>$row['Fname'],"Lname"=>$row['Lname'],"Email"=>$row['Email'],"Class"=>$row["Class"],
                "Degree"=>$row["Degree"]));
                }
                print(json_encode(array_reverse($quotes)));
            }
            else{
                print(json_encode("php exception: can't retrive from mysql"));
            }
            $conn->close();
        }
    }
}
$quotes = new quotes();
$quotes->select();
 ?>
