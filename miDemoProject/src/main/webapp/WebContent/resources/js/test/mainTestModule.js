var mainTestModule = (function(){
	'use strict';

	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
		
	var initializePage = function (){
		$(document).ready(function() {
			
			//Activamos eventos del ShowMessage
			showMessageModule.init();
			//Activamos eventos del formulario
			formTestModule.init();
			
			//cargo la tabla
			listTestModule.loadData();
			//Busquedas para los inputs de la tabla
		} );
	}
	
	return {
		initPage: initializePage
	}
})();

mainTestModule.initPage();
