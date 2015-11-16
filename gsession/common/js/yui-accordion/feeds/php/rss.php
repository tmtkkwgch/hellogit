<?php
require_once('simplepie.inc');
require_once('Class.rss.php');

$rss = new rss($_GET['feed']);
echo $rss->getRenderedOutput();
?>