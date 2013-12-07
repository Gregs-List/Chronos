<?php 
session_start();
session_status() == PHP_SESSION_NONE;
header('Location:index.html');

error_reporting(E_ALL);
ini_set('display_errors', '1');
?>