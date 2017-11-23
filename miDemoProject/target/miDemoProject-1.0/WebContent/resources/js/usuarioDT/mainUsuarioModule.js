var mainUsuarioModule = (function(){
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
				this.$jq.on('click', formUsuarioModule.initializeNew); //$(this.idJquery)
			}
	};
	
	var eliminarVariosBTN = {
			idHTML: "bulkDeleteBtn",
			$jq: $("#bulkDeleteBtn"),
			onClick: function(){
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
	
	var exportarBTN = {
			idHTML: "exportBtn",
			$jq: $("#exportBtn"),
			onClick: function(){
				
				//Obtengo de la configuracion de la tabla cual es el orden actual
				var datatableOrder = listUsuariosModule.getDataTable().settings().order()[0];
				var form = "<form name='csvexportform' action='datatableCrud/exportar' method='post' accept-charset='utf-8' modelAttribute='exportForm'>";
				
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
	
	var initializePage = function (){
		$(document).ready(function() {
			
			//Activamos eventos del ShowMessage
			showMessageModule.init();
			//Activamos eventos del formulario
			formUsuarioModule.init();
			
			//Activamos eventos de la página
			nuevoBTN.init();
			eliminarVariosBTN.init();
			exportarBTN.init();
						
			//cargo la tabla
			listUsuariosModule.loadData();
			//Busquedas para los inputs de la tabla
			listUsuariosModule.columnSearch();
		} );
	}
	
	return {
		initPage: initializePage
	}
})();

mainUsuarioModule.initPage();
