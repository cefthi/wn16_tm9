<!DOCTYPE html>
<html>
<?php 
    $path="moviefiles/".$_GET["film"]."/info.txt";
    $patho="moviefiles/".$_GET["film"]."/overview.txt";
    $over="\"moviefiles/".$_GET["film"]."/overview.png\"";
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
/*for($i=1;$i<=$count;$i++){
$tp=$pco."review".$i.".txt";
$cf=fopen($tp,'r');
$cfs=file_get_contents($tp);
echo $cfs;
}*/
//while ($c=fgetc($ofile)) {}//$overview=$overview."</dt>";
    
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
            <div id="Overview">
                <img  id="overr" src=<?=$over?>alt="overview">
                <dl class="OverViewdl">
                    <?= $overview ?>
                </dl>
            </div>
            <div id="reviews">
                <div id="reviewsbar">
                   <img id="reviewsbarimg" src=<?=$p?> alt="overview"> 
                   <div id="rate"><?=$rating?><span id="inr">% out of <?=$count ?> reviews </span></div>
                </div>
                <div class="reviewcol">
                    <div class="reviewquote">
                        <img class="likeimg" src="images/rotten.gif" alt="rotten">
                        "Ditching the cheeky, self-aware wink that 
helped to excuse the concept's inherent corniness, 
                        the movie attempts to look polished and 'cool,' 
                        but the been-there animation can't compete with 
the then-cutting-edge puppetry of the 1990 live-action movie."
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                        Peter Debruge<br> 
                        Variety
                    </div>
                    <div class="reviewquote">
                        <img class="likeimg" src="images/fresh.gif" alt="fresh">
                        "TMNT is a fun, action-filled adventure that 
will satisfy longtime fans and generate a legion of new ones."
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                        Todd Gilchrist<br>  
                        IGN Movies
                    </div>
                    <div class="reviewquote">
                        <img class="likeimg" src="images/rotten.gif" alt="rotten">
                        "It stinks!"<br><br>
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                        Jay Sherman (unemployed) <br><br>
                    </div>
                    <div class="reviewquote">
                        <img class="likeimg" src="images/rotten.gif" alt="rotten">
                        "The rubber suits are gone and they've been 
redone with fancy computer technology, but that hasn't stopped them     
                        from becoming dull."
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                        Joshua Tyler<br>
                        CinemaBlend.com  
                    </div>
                    <div class="reviewquote">
                        <img class="likeimg" src="images/fresh.gif" alt="fresh">
                        " love how they shot it; used the hand held camera to follow the actors, 
                        but didn't use fast cuts or incredible shake, so you could actually see what was going on."
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                        Tengfei Sun<br><br>
                    </div>
                </div>
                <div class="reviewcol">
                    <div class="reviewquote">
                        <img class="likeimg" src="images/rotten.gif" alt="rotten">
                        "The turtles themselves may look prettier, but 
are no smarter; torn irreparably from their countercultural roots,
                        our superheroes on the half shell have been 
firmly co-opted by the industry their creators once sought to spoof."
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                        Jeannette Catsoulis<br>
                        New York Times 
                    </div>
                    <div class="reviewquote">
                        <img class="likeimg" src="images/rotten.gif" alt="rotten">
                        "Impersonally animated and arbitrarily plotted, 
                        the story appears to have been made up as the filmmakers went along."
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                         Ed Gonzalez<br>
                         Slant Magazine 
                    </div>
                    <div class="reviewquote">
                        <img class="likeimg" src="images/fresh.gif" alt="fresh">
                        "The striking use of image and motion allows each sequence to leave an impression. 
                        It's an accomplished restart to this franchise."
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                        Mark Palermo<br>
                        Coast (Halifax, Nova Scotia) 
                    </div>
                    <div class="reviewquote">
                        <img class="likeimg" src="images/rotten.gif" alt="rotten">
                        "The script feels like it was computer generated. 
                        This mechanical presentation lacks the cheesy charm of the three live action films."
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                        Steve Rhodes<br>
                        Internet Reviews  
                    </div>
                    <div class="reviewquote">
                        <img class="likeimg" src="images/fresh.gif" alt="fresh">
                        "They did it the best way they could while keeping it true to the book. "
                    </div>
                    <div class="personalquote">
                        <img class="personimg" src="images/critic.gif" alt="critic">
                        Tengfei Sun<br><br>
                    </div>
                </div>
            </div> 
            <div id="bottombar">
                (1-10) of 8
            </div> 
             <div id="reviewsbar">
                   <img id="reviewsbarimg" src=<?=$p?> alt="overview"> 
                   <div id="rate"><?= $rating?><span id="inr">% out of <?=$count ?> reviews </span></div>
                </div>  
        </div>

        <div id="ee1"><img id="pi" src="images/b.jpg"/>
        <span id="banner"><img src="images/banner.png" alt="banner"><img id="pi" src="images/b.jpg"/></span></div>

        <div id="w3ccheck">
            <a href="http://validator.w3.org/check/referer"><img src="images/w3c-html.png" alt="Valid HTML5"></a> <br>
            <a href="http://jigsaw.w3.org/css-validator/check/referer"><img src="images/w3c-css.png" alt="Valid CSS"></a>
	</div>
</body></html>