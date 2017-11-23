var mainDatetimepickerModule = (function(){
	'use strict';
	
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var calendario1={
		idHTML: "datetimepicker1",
		idJquery: "#datetimepicker1",
		init: function(){
			//configuracion minima
			$(this.idJquery).datetimepicker();
		}
	}
	
	var calendario2={
		idHTML: "datetimepicker2",
		idJquery: "#datetimepicker2",
		init: function(){
			//configuracion localizacion
			$(this.idJquery).datetimepicker({locale: 'es'});
		}
	}
	
	var calendario3={
		idHTML: "datetimepicker3",
		idJquery: "#datetimepicker3",
		init: function(){
			//configuracion formato
			$(this.idJquery).datetimepicker({format: 'LT'});
		}
	}
	
	var calendario4={
		idHTML: "datetimepicker4",
		idJquery: "#datetimepicker4",
		init: function(){
			//configuracion en linea
			$(this.idJquery).datetimepicker(
				{
					locale: 'es',
					inline: true,
	                sideBySide: true
	            }
			);
		}
	}
	
	var initializePage = function (){
		$(document).ready(function() {		
						
			//inicializacion del datetimepicker
			calendario1.init();
			
			//inicializacion del datetimepicker
			calendario2.init();
			
			//inicializacion del datetimepicker
			calendario3.init();
			
			//inicializacion del datetimepicker
			calendario4.init();
		} );
	}
	
	return {
		initPage: initializePage		
	}
})();

mainDatetimepickerModule.initPage();
