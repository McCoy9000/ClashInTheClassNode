var mainSesionesModule = (function(){
	'use strict';

	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var refreshBTN = {
			idHTML: "refreshBtn",
			$jq: $("#refreshBtn"),
			onClick: null,
			init: function(){
				this.$jq.unbind('click');
				this.$jq.on('click', 
						function(){
							showMessageModule.removeAlert();
							listSesionesModule.refreshSearch();
					}
				); //$(this.idJquery)
			}
	};
	 var initializePage = function (){
			$(document).ready(function() {
				showMessageModule.init();
				listSesionesModule.loadData();
				listSesionesModule.columnSearch();
				refreshBTN.init();
			} );
		}
	 return {
			initPage: initializePage
		}
})();
mainSesionesModule.initPage();