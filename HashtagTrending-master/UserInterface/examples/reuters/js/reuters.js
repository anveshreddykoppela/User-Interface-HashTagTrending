var Manager;
hasMoreHashTags = true;
numberOfHashTags=0;
tweetsDayCountMap = [];
(function($) {

	$(function() {
		Manager = new AjaxSolr.Manager({
			solrUrl : 'http://localhost:8983/solr/'
		});
		Manager.addWidget(new AjaxSolr.VolumeHashTags({
			id : 'hashtags',
			target : '#hashTagsTable'
		}));
	});

})(jQuery);

function callManager() {
	//numberOfHashTags=hashTagsSelected.length;
	//console.log(hashTagsSelected);
	//console.log(tweetsDayCountMap.length);
	while(tweetsDayCountMap.length!=0){
		tweetsDayCountMap.pop();
	}
	//console.log(tweetsDayCountMap.length);
		for (var m = 0; m < hashTagsSelected.length; m++) {
			var hashTag='hashtag:'+hashTagsSelected[m];
			Manager.init();
			Manager.store.remove('q');
			Manager.store.addByValue('q', hashTag);
			//Manager.store.addByValue('rows', 0);
			var params = {
				facet : true,
				'facet.mincount' : 1,
				'facet.date' : 'tweetdate',
				'facet.date.start' : 'NOW/YEAR-1YEARS',
				'facet.date.end' : 'NOW/DAY+1DAY',
				'facet.date.gap' : '+1DAY',
				'json.nl' : 'map'
			};
			for ( var name in params) {
				Manager.store.addByValue(name, params[name]);
			}
			Manager.doRequest();
		}
};
