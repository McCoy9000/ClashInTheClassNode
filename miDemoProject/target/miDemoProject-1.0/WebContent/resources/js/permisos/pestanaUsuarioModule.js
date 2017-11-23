var pestanaUsuarioModule = (function(){
	'use strict';

	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var nuevoBTN = {
			idHTML: "newBtn",
			$jq: $("#newBtn"),
			onClick: null,
			init: function(){
				this.$jq.unbind('click');
				this.$jq.on('click', 
						function(){
							formUsuarioModule.initializeNew();
							showMessageModule.removeAlert();
							dualListRolesModule.selectorMultipleHide();
					}
				); //$(this.idJquery)
			}
	};
	
	var eliminarVariosBTN = {
			idHTML: "bulkDeleteBtn",
			$jq: $("#bulkDeleteBtn"),
			onClick: function(){
				showMessageModule.removeAlert();
				//pestanaUsuarioModule.selectorMultipleHide();
				var arrayIds=new Array();
				var textConfirm = "Desea eliminar los registros seleccionados: ";
				$.each(listUsuariosModule.getSelectedRows(), function (indice, elemento){
					arrayIds.push(elemento);
					
					if (indice != 0)
						textConfirm+=", ";
					textConfirm += elemento
				});
				
				modalConfirmModule.showConfirmacion(textConfirm, arrayIds, formUsuarioModule.borrarDatos);
			},			
			init: function(){
				this.$jq.unbind('click');
				this.$jq.on('click', eliminarVariosBTN.onClick);
			}
	}
	
	var init = function (){
		
		//Activamos eventos del ShowMessage
		showMessageModule.init();
		//Activamos eventos del formulario
		formUsuarioModule.init();
		
		//Activamos eventos de la página
		nuevoBTN.init();
		eliminarVariosBTN.init();
					
		//cargo la tabla
		listUsuariosModule.loadData();
		//Busquedas para los inputs de la tabla
		listUsuariosModule.columnSearch();
		dualListRolesModule.init();
		dualListRolesModule.selectorMultipleHide();

	}
	return {
		init: init
	}
})();

