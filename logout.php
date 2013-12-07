<?php 
session_start();
(session_status() == PHP_SESSION_NONE);
header('Location:index.html');
?>