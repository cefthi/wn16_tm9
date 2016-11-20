<!DOCTYPE html>
<html>
<?php 
    $path="moviefiles/".$_GET["film"]."/info.txt";
    $patho="moviefiles/".$_GET["film"]."/overview.txt";
    $overv="moviefiles/".$_GET["film"]."/overview.png";
    $over="\"moviefiles/".$_GET["film"]."/overview.png\"";
   // $im=fopen($overv,'r');
    if(!file_exists($overv)){$over="\"moviefiles/".$_GET["film"]."/overview.jpg\"";}
    $pathr="moviefiles/".$_GET["film"]."/review";
    $file=fopen($path,'r');$title=fgets($file);$year=fgets($file);
    $rating=fgets($file);
    $pco="moviefiles/".$_GET["film"]."/";
   if(intval($rating)>60){$p="\"images/freshbig.jpg\"";$p1="\"images/fresh.gif\"";}else{$p="\"images/rottenbig.png\"";$p1="\"images/rotten.gif\"";}
    $ofile=fopen($patho,'r');
    $overrr=file_get_contents($patho);
    $overview="<dt>";
for($i=1;$i<strlen($overrr);$i++) {

    if($overrr[$i]==':'){$overview=$overview.$overrr[$i]."</dt>"."<dd>";
    }
    else $overview=$overview.$overrr[$i];
    if($overrr[$i]=="\n"){$overview=$overview.$overrr[$i]."</dd>"."<dt>";
    }
}
$fi = new FilesystemIterator($pco, FilesystemIterator::SKIP_DOTS);
$count=iterator_count($fi)-3;

$r="<div class=\"reviewcol\">";
$lef=(int)($count/2);
for($i=1;$i<=$count;$i++){
	if($i==$lef+1){$r=$r."</div>"."<div class=\"reviewcol\">";}
	$r=$r."<div class=\"reviewquote\">";
	$pathrf=$pathr.$i.".txt";
	$rfile=fopen($pathrf,'r');
	 $review=file_get_contents($pathrf);
	 list($text,$te,$name,$company)=file($pathrf);
	$g=strtolower($te);
	 	$r=$r."<img class=\"likeimg\" src=\"images/".$g.".gif\" alt=\"rotten\">";


$r=$r.$text."</div>"."<div class=\"personalquote\">";
$r=$r. "<img class=\"personimg\" src=\"images/critic.gif\" alt=\"critic\">";
$r=$r.$name."<br>".$company."<br>"."</div>";
}
$r=$r."</div>";

    
    ?><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title> <?= $title?> - Rancid Tomatoes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="movie.css">
        <link rel="shortcut icon" type="image/gif" href=<?=$p1?>>
    </head>
    <body>

        <div id="ee"><img id="pi" src="images/b.jpg"/>
        <span id="banner"><img src="images/banner.png" alt="banner"><img id="pi" src="images/b.jpg"/></span></div>
        <h1><?=$title?>( <?= $year?>)</h1> 
        <div id="overall">
         <div id="reviewsbar">
                   <img id="reviewsbarimg" src=<?=$p?> alt="overview"> 
                   <div id="rate"><?=$rating?><span id="inr">% out of <?=$count ?> reviews </span></div>
                </div>
            <div id="Overview">
                <img  id="overr" src=<?=$over?>alt="overview">
                <dl class="OverViewdl">
                    <?= $overview ?>
                </dl>
            </div>
            <div id="reviews">
               
                
                   <?=$r?>
                </div>
              <div id="bottombar">
                (1-10) of <?= $count?>
            </div> 
             <div id="reviewsbar">
                   <img id="reviewsbarimg" src=<?=$p?> alt="overview"> 
                   <div id="rate"><?= $rating?><span id="inr">% out of <?=$count ?> reviews </span></div>
                </div>  
            </div> 
            
      <br><br>
       

      <br><br>
     
        <div id="w3ccheck">
            <a href="http://validator.w3.org/check/referer"><img src="images/w3c-html.png" alt="Valid HTML5"></a> <br>
            <a href="http://jigsaw.w3.org/css-validator/check/referer"><img src="images/w3c-css.png" alt="Valid CSS"></a>
	</div>
<div id="ee1"><img id="pi" src="images/b.jpg"/>
        <span id="banner"><img src="images/banner.png" alt="banner"><img id="pi" src="images/b.jpg"/></span>
        </div>
</body></html>