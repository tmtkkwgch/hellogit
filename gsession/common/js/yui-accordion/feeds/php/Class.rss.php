<?php

Class rss {
	
	public $url;
	public $responseObject;
	
	public function __construct($sURL) {
		$this->url = $sURL;
		try{
			require_once('simplepie.inc');

			// We'll process this feed with all of the default options.
			$this->responseObject = new SimplePie($sURL);

			// This makes sure that the content is sent to the browser as text/html and the UTF-8 character set (since we didn't change it).
			
			$this->responseObject->handle_content_type();

		}
		catch(Exception $e) {
			echo 'ERROR: could not fetch feed';
			die();
		}
	}

	public function __destruct() {
			$this->responseObject->__destruct();
			unset($this->responseObject);
		}		
	
	public function getRawOutput() {
		//$sOut = print_r($this->responseObject->get_items(), true);
		print_r($this->responseObject);
	}
	
	function getRenderedOutput() {
		$sOut = '';
		$nCount = 0;
		
		$aItems = $this->responseObject->get_items();
		foreach($aItems as $item) {
			if(0 === $nCount) {
				$sOut .= '<div class="headline">' . "\n";
				$sOut .= '<h3><a href="' . $item->get_link() . '">' . $item->get_title() . '</a></h3>' . "\n";
				$sOut .= '<p>';
				$sOut .= $this->responseObject->sanitize($item->get_description(), SIMPLEPIE_CONSTRUCT_MAYBE_HTML) . '</p>'. "\n";
				$sOut .= '</div>' . "\n";
				$sOut .= '<h4>More headlines</h4><ul class="news">' . "\n";
			}
			else {
				if($nCount < 10) {
					$sOut .= '<li><a href="' . $item->get_link() . '">' . $item->get_title() . '</a></li>' . "\n";
				}
			}
			$nCount++;
		}
		$sOut .= '</ul>' . "\n";
		return $sOut;
	}
}
/*
echo '<h2>The Guardian</h2>';
$n1 = new rss('http://www.guardian.co.uk/rss');
echo $n1->getRenderedOutput();
echo '<h2>Times Online</h2>';
$n2 = new rss('http://www.timesonline.com/?rss=news');
echo $n2->getRenderedOutput();
echo '<h2>Telegraph</h2>';
$n3 = new rss('http://www.telegraph.co.uk/news/majornews/rss');
echo $n3->getRenderedOutput();
echo '<h2>The Sun Online</h2>';
$n4 = new rss('http://www.thesun.co.uk/sol/homepage/feeds/rss/article312900.ece');
echo $n4->getRenderedOutput();
*/

//echo "<!--";
//echo $n3->getRawOutput();
//echo "-->";

?>
