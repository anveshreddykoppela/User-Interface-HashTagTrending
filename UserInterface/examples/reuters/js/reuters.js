var Manager;

(function($) {

	$(function() {
		Manager = new AjaxSolr.Manager({
			solrUrl : 'http://localhost:8983/solr/'
		});
		Manager.addWidget(new AjaxSolr.VolumeHashTags({
			id : 'hashtags',
			target : '#hashTagsTable'
		}));
		Manager.init();
		Manager.store.addByValue('q', 'hashtag:christmas');
		var params = {
			facet : true,
			'rows':0,
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
	});

})(jQuery);

function callManager(hashTagSelected) {
	var hashtagValue = 'hashtag:';
	hashtagValue += hashTagSelected[0];
	for (var n = 1; n < hashTagSelected.length; n++) {
		hashtagValue += ' OR ';
		hashtagValue += 'hashtag:'+hashTagSelected[n];
	}
	Manager.init();
	Manager.store.remove('q');
	Manager.store.addByValue('q', hashtagValue);
	Manager.store.addByValue('rows',0);
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
};
