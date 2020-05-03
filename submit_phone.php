<?php
include 'koneksi.php';
$id = isset($_POST ['id']) ? $_POST['id'] : '';
$nama = isset($_POST ['nama']) ? $_POST['nama'] : '';
$harga = isset($_POST ['harga']) ? $_POST['harga'] : '';
$query = 'INSERT INTO tbl_phone (phone_name,price) values ("'$nama'","'$harga'")';
if($id > 0){
$query = 'UPDATE tbl_phone set phone_name = "'$nama'", price = "'$harga'" where id = '$id;
}
mysql_query($query) or die (mysql_error());
?>