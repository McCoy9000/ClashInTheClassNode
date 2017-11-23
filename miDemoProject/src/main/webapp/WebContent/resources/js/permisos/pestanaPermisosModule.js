var pestanaPermisosModule = (function(){
	'use strict';

	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var init = function (){
			showMessageModule.init();
			listPermisosModule.loadData();
			listPermisosModule.columnSearch();
	} 
	return {
		init: init
	}
})();

