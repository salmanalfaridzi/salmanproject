<?php
include 'koneksi.php';
$query = 'SELECT id, phone_name nama, price harga from tbl_phone';
$result = mysqli_query($koneksi,$query) or die(mysql_error());
$data = array();
while ($row = mysqli_fetch_object($result)) {
$data['handphone'][] = $row;
}
echo json_encode($data);
?>