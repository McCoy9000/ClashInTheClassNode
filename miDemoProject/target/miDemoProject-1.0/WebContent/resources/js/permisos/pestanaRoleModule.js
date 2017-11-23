var pestanaRoleModule = (function(){
	'use strict';

	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var nuevoBTN = {
			idHTML: "newBtnRole",
			$jq: $("#newBtnRole"),
			onClick: null,
			init: function(){
				this.$jq.unbind('click');
				this.$jq.on('click', 
						function(){
							formRoleModule.initializeNew();
							showMessageModule.removeAlert();
							dualListPermisosModule.selectorMultipleHide();
					}
				); //$(this.idJquery)
			}
	};
	
	var eliminarVariosBTN = {
			idHTML: "bulkDeleteBtnRole",
			$jq: $("#bulkDeleteBtnRole"),
			onClick: function(){
				showMessageModule.removeAlert();
				//pestanaUsuarioModule.selectorMultipleHide();
				var arrayIds=new Array();
				var textConfirm = "Desea eliminar los registros seleccionados: ";
				$.each(listRolesModule.getSelectedRows(), function (indice, elemento){
					arrayIds.push(elemento);
					
					if (indice != 0)
						textConfirm+=", ";
					textConfirm += elemento
				});
				
				modalConfirmModule.showConfirmacion(textConfirm, arrayIds, formRoleModule.borrarDatos);
			},			
			init: function(){
				this.$jq.unbind('click');
				this.$jq.on('click', eliminarVariosBTN.onClick);
			}
	}
	
	var exportarBTN = {
			idHTML: "exportBtnRole",
			$jq: $("#exportBtnRole"),
			onClick: function(){
				showMessageModule.removeAlert();
				dualListRolesModule.selectorMultipleHide();
				//Obtengo de la configuracion de la tabla cual es el orden actual
				var datatableOrder = listUsuariosModule.getDataTable().settings().order()[0];
				var form = "<form name='csvexportform' action='usuarios/exportar' method='post' accept-charset='utf-8' modelAttribute='exportForm'>";
				
				var datatableNameOrderColum=$($('.search-input-text')[(datatableOrder[0])-1]).data("name");
				
				//Si no se ha realizado ninguna ordenacion el valor es undefined, por eso evaluamos si cargamos el valor 
				if (typeof datatableNameOrderColum != 'undefined') 
					form = form + "<input type='hidden' name='orderColumn' value='"+datatableNameOrderColum+"'>";
				
				form = form + "<input type='hidden' name='ascDsc' value='"+datatableOrder[1]+"'>";
				
				$.each($('.search-input-text'), function (indice, elemento){
					var columnName = $(elemento).attr('data-name');
					var columnIndex = $(elemento).attr('data-column');
					var realSeachValue = listUsuariosModule.getDataTable().settings().column([columnIndex]).search();
					var titleColumns=$(listUsuariosModule.getDataTable().settings().column([columnIndex]).header()).html();
					form = form + "<input type='hidden' name='columns["+indice+"].name' value='"+columnName+"'>";
					form = form + "<input type='hidden' name='titleColumns["+indice+"]' value='"+titleColumns+"'>";
					form = form + "<input type='hidden' name='columns["+indice+"].searchValue' value='"+realSeachValue+"'>";

				});
				
				//Truco para que i8 convierta a utf8 los datos enviados en el post
				form = form + "<input name='iehack' type='hidden' value='&#9760;' />";
				form = form + "</form><script>document.csvexportform.submit();</script>";
				
				excelPopupModule.showPopup(form);
								
			},
			init: function(){
				this.$jq.unbind('click');
				this.$jq.on('click', exportarBTN.onClick);
			}
	}

	var init = function (){
	
			
		//Activamos eventos del ShowMessage
		showMessageModule.init();
		//Activamos eventos del formulario
		formRoleModule.init();
		
		//Activamos eventos de la página
		nuevoBTN.init();
		eliminarVariosBTN.init();
		exportarBTN.init();
		
		//cargo la tabla 
		listRolesModule.loadData();
		//Busquedas para los inputs de la tabla
		listRolesModule.columnSearch();
		dualListPermisosModule.init();
		dualListPermisosModule.selectorMultipleHide();

	}
	
	return {
		init: init
	}
})();
