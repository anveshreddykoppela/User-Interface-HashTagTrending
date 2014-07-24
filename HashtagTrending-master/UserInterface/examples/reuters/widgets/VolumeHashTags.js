/**
 * 
 */
(function($) {
	AjaxSolr.VolumeHashTags = AjaxSolr.AbstractFacetWidget
			.extend({
				afterRequest : function() {
					var tweetsDayCount = this.manager.response.facet_counts.facet_dates.tweetdate;
					var qTerm=this.manager.response.responseHeader.params.q;
					var dayCount = new Array();
					var count = 0;
					for ( var i in tweetsDayCount) {
						if (i != "end" && i != "gap" && i != "start") {
							dayCount[count] = new Array(2);
							dayCount[count][0] = i;
							dayCount[count][1] = tweetsDayCount[i];
							count++;
						}
					}
					//console.log(dayCount);
					preProcessingMultipleHashTags(qTerm,dayCount);
				},

			});
})(jQuery);
function preProcessingMultipleHashTags(qTerm,dayCount) {
	var hashTagTerm=qTerm.substring(8);
	//console.log(tweetsDayCountMap.length);
	tweetsDayCountMap[tweetsDayCountMap.length] = [hashTagTerm,dayCount];
	//console.log(tweetsDayCountMap);
	if ((tweetsDayCountMap.length) == hashTagsSelected.length) {
		//console.log(tweetsDayCountMap);
		var dates = new Array();// creating unique dates list from all available dates
		for (var i = 0; i < tweetsDayCountMap.length; i++) {
			var tempDayCount = tweetsDayCountMap[i][1];
			for (var j = 0; j < tempDayCount.length; j++) {
				if (dates.indexOf(tempDayCount[j][0]) < 0) {
					dates[dates.length] = tempDayCount[j][0];
				}
			}
		}
		drawChart(dates);
		//console.log("test");
		//console.log(dates.length);
	}
};
