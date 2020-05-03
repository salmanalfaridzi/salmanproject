<?php
include 'koneksi.php';
$id = isset($_POST ['id']) ? $_POST['id'] : '';
$query = 'DELETE from tbl_phone where id = ' $id;
$result = mysqli_query($koneksi,$query) or die ();
if (mysql_affacted_rows()>0){
echo 'Delete Data Success';
} else { echo'';}
?>