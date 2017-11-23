var mainUsuarioJGModule = (function() {
	/**
	 * Naming convenctions 
	 * _privateVariable 
	 * _privateFunction 
	 * publicFunction
	 * $varName jQuery object
	 */
	'use strict';

	/**
	 * Al pulsar el boton nuevo, abre el formulario de datos en una ventana modal.
	 */
	var _nuevoBTN = {
		idHTML : "newBtn",
		$jq : $("#newBtn"),
		onClick : function(){
			//Eliminamos mensajes
			showMessageModule.removeAlert();
			formUsuarioJGModule.initializeNew();
		},
		init : function() {
			this.$jq.unbind('click');
			this.$jq.on('click', _nuevoBTN.onClick);
		}
	};

	/**
	 * Al pulsar el boton eliminar, abre ventana de confirmacion.
	 */
	var _eliminarBTN = {
		idHTML : "bulkDeleteBtn",
		$jq : $("#bulkDeleteBtn"),
		onClick : function() {
			//Eliminamos mensajes
			showMessageModule.removeAlert();
			
			var arrayIds = new Array();
			var textConfirm = "Desea eliminar los registros seleccionados: ";
			$.each(listUsuarioJGModule.getSelectedDataIDs(), function(indice, elemento) {
				arrayIds.push(elemento);

				if (indice != 0)
					textConfirm += ", ";
				textConfirm += elemento
			});

			modalConfirmModule.showConfirmacion(textConfirm, arrayIds,formUsuarioJGModule.borrarDatos);
		},
		init : function() {
			this.$jq.unbind('click');
			this.$jq.on('click', _eliminarBTN.onClick);
		}
	}

	/**
	 * Al pulsar el boton exportar se genera una formulario dinamico con los datos 
	 * necesarios del grid para realizar la busqueda de datos necesaria y exportarlos
	 */
	var _exportarBTN = {
		idHTML : "exportBtn",
		$jq : $("#exportBtn"),
		onClick : function() {
			//Eliminamos mensajes
			showMessageModule.removeAlert();
			
			modalConfirmModule.showConfirmacion("Desea exportar el resultado de la consulta a Excel",null,_exportarBTN.onConfirm);
		},
		onConfirm: function(){
			
			var form = "<form name='csvexportform' action='jqGridCrud/exportar' method='post' accept-charset='utf-8' modelAttribute='form'>";
			
			//Columnas
		    var colNames = $(listUsuarioJGModule.$jqgrid).jqGrid("getGridParam", "colNames"), contador=0;
		    
		    //Las 2 primeras columnas se corresponden con el numero de columna y el check y la última a la botonera, pero eso las evito
		    for (var i = 2; i < colNames.length-1; i++)
		    	form = form + "<input type='hidden' name='titleColumns["+(i-2)+"]' value='"+colNames[(i)]+"'>";
		    
		    var nameOrderColum =$(listUsuarioJGModule.$jqgrid).jqGrid('getGridParam','sortname');
		    var ascDsc=$(listUsuarioJGModule.$jqgrid).jqGrid('getGridParam','sortorder');
		    
			//Si no se ha realizado ninguna ordenacion el valor es undefined, por eso evaluamos si cargamos el valor
		    if (nameOrderColum != null){
		    	form = form + "<input type='hidden' name='orderColumn' value='"+nameOrderColum+"'>";
				form = form + "<input type='hidden' name='ascDsc' value='"+ascDsc+"'>";
		    }
			
			// recorro los campos de busqueda para recoger los valores
			$.each($('.ui-search-input').find('input,select'), function(indice,	elemento) {
				
				form = form + "<input type='hidden' name='columns["+indice+"].name' value='"+elemento.name+"'>";
				form = form + "<input type='hidden' name='columns["+indice+"].searchValue' value='"+$(elemento).val()+"'>";
			});

			//Truco para que i8 convierta a utf8 los datos enviados en el post
			form = form + "<input name='iehack' type='hidden' value='&#9760;'/>";
			form = form + "</form><script>document.csvexportform.submit();</script>";

			//Añado el formulario a un div de la jsp
			excelPopupModule.showPopup(form);
			
		},
		init : function() {
			this.$jq.unbind('click');
			this.$jq.on('click', _exportarBTN.onClick);
		}
	}

	/**
	 * Funcion de error comun a las llamadas ajax
	 */
	var errorProcess = function(data) {
		var respBody = data.responseJSON;
		showMessageModule.createAlert("danger", respBody.messageForUser + "<p>"+ respBody.nameClass + "</p>");
	};

	/**
	 * Inicializacion de eventos una vez carga la página.
	 */
	var initializePage = function() {
		$(document).ready(function() {

			// Activamos eventos del ShowMessage
			showMessageModule.init();
			// Activamos eventos del formulario
			formUsuarioJGModule.init();

			_nuevoBTN.init();
			_eliminarBTN.init();
			_exportarBTN.init();

			// Le paso la locale
			listUsuarioJGModule.createGrid("es");

		});
	}

	return {
		initPage : initializePage,
		errorProcess : errorProcess,
	}

})();

mainUsuarioJGModule.initPage();
