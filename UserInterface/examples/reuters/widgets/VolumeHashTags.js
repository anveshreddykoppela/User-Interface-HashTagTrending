/**
 * 
 */
(function($) {
	AjaxSolr.VolumeHashTags = AjaxSolr.AbstractFacetWidget
			.extend({
				afterRequest : function() {
					var tweetsDayCount = this.manager.response.facet_counts.facet_dates.tweetdate;
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
					drawChart(dayCount);
				}
			});
})(jQuery);
