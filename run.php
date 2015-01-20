<?php
include_once("MDB2.php");
$db_info['dsn']['hostspec'] = 'host';
$db_info['dsn']['username'] = 'user';
$db_info['dsn']['password'] = 'pass';
$db_info['dsn']['database'] = 'dbname';
$db_info['dsn']['phptype']  = 'mysql';
$db_info['options.debug']   = 0;
$db_info['options']['portability'] = MDB2_PORTABILITY_ALL;

$start = round(microtime(true) * 1000);
$db = MDB2::connect($db_info['dsn'],$db_info['options']);
$end = round(microtime(true) * 1000);
echo "Connect time: " . ($end - $start) ."ms\n";

if ($db instanceof MDB2_Error)
{
    echo "Error Connecting to Database!!!<br>\n";
    exit(1);
}

$db->setFetchMode(MDB2_FETCHMODE_ASSOC);

$channels = explode("\n", file_get_contents("lookup.txt"));

$average = 0;
$start = round(microtime(true) * 1000);
foreach ($channels as $id)
{

    $in_start = round(microtime(true) * 1000);

    #$sql =  "SELECT id FROM channel where id = " . $db->quote($id);
    #$result = $db->queryOne($sql);
    $sql =  "UPDATE channel set views = 150 WHERE id = " . $db->quote($id);
    $result = $db->query($sql);


    $in_end = round(microtime(true) * 1000);
    if(PEAR::isError($result))
    {
        print $result->getMessage();
        print $sql."\n";
        return false;
    }
    $average += ($in_end - $in_start) /100;
}
$end = round(microtime(true) * 1000);

echo "Average time is " . $average ."ms\n";
echo "Total time for 100 records is " . ($end - $start) ."ms\n";
