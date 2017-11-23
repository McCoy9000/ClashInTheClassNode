var mainDuallistboxModule = (function(){
	'use strict';
	
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var selectorMultiple={
		idHTML: "duallistbox",
		idJquery: "#duallistbox",
		init: function(){
			//configuracion con internacionalizacion y paso de items a traves de boton < o >
			$(this.idJquery).bootstrapDualListbox({
		    	filterTextClear:"Mostrar todos",
		    	filterPlaceHolder:"Filtrar",
		    	moveSelectedLabel:"Mover selección",
		    	moveAllLabel:"Mover todos",
		    	removeSelectedLabel:"Eliminar selección",
		    	removeAllLabel:"Eliminar todos",
		    	infoText:"Mostrando todos",
		    	infoTextFiltered:'<span class="label label-warning">Filtrados</span> {0} de {1}',
		    	infoTextEmpty:"Lista vacía",
		    	moveOnSelect: false,
		    	nonSelectedListLabel: '<b>Items a seleccionar</b>',
		    	selectedListLabel: '<b>Items seleccionados</b>'
			});
		}
	}
	
	var aceptar={
		idHTML: "aceptarBtn",
		idJquery: "#aceptarBtn",
		onClick: function (){
			var seleccion=$("#duallistbox").val()
			alert(seleccion);
		},
		
		init: function(){
			$(this.idJquery).unbind('click');
			$(this.idJquery).on('click', aceptar.onClick);
		}
	}
	
	
	var initializePage = function (){
		$(document).ready(function() {		
			//inicializacion
			selectorMultiple.init();
			
			//Botones
			aceptar.init();
		} );
	}
	
	return {
		initPage: initializePage		
	}
})();

mainDuallistboxModule.initPage();
