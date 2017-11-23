function FilterClass (username, descripcion) {
	/*Clase */		
	this.username = username;
	this.descripcion = descripcion;
	
    //Metodos	
	this.prepareToJsonObject = function (){
		var jsonObject = null;
			jsonObject = {
				 "username" : this.username,
				 "descripcion" : this.descripcion
			 }; 
		return jsonObject;
	}
}

var mainJqgridModule = (function(){
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	'use strict';
	
	//Variables privadas
	var _classInputSearchJquery = ".search-input-text";
	
	/**Campo de texto username*/  	
	var _username = {
		idHTML: "username",
		idJquery: "#username",		
	}
	
	/**Campo de texto descripcion*/  	
	var _descripcion = {
		idHTML: "descripcion",
		idJquery: "#descripcion",		
	}
	
	/**
	 * Funcion que dispara la busqueda a traves de la tecla return cuando
	 * el foco se encuentra en cualquier campo de la zona de busqueda.
	 */
	var _keydownSearch = function (){
		//Si se pulsa intro con el foco en algun elemento con la clase controlReturn, volvemos a lanzar el buscar.
		$(_classInputSearchJquery).on('keydown', function (e) {
	  	  if(e.which == 13) {
	  		 _finder();
	  	  }
	    });
	}
	
	/**
	 * Funcion que dispara la busqueda
	 */
	var _finder = function (){
		var messageVal=_findValidation();
		if (messageVal==null){
			//Si existen mensajes previos, los borro
	  		 showMessageModule.removeAlert();
	  		 //Llamo a la recarga de datos
	  		 listJqgridModule.reloadData();
		}
		else{
			showMessageModule.createAlert("warning", messageVal);
		}
	}
	
	/**
	 * Validacion de datos introducidos para las búsquedas
	 */
	var _findValidation = function (){
		var messageVal=null;		
		//validacion campos obligatorios
		messageVal=_isCamposObligatoriosInformados();
				
		return messageVal;
	}
	
	/**
	 * Validacion de datos
	 */
	var _isCamposObligatoriosInformados = function (){
		
		var username=$(_username.idJquery).val();
		var descripcion=$(_descripcion.idJquery).val();
		if (username =="" && descripcion =="") {
			return "No se han introducido datos";
		}
		return null;
	}
	
		
	/**
	 * Funcion que dispara resetea el formulario a traves del boton limpiar
	 */
	var _resetBTN = {
			idHTML: "limpiarBtn",
			$jq: $("#limpiarBtn"),
			onClick: function(){
				//reseteo los campos de texto
				$(_classInputSearchJquery).val("");
								
				listJqgridModule.clearGridData();
				showMessageModule.removeAlert();

			},
			init: function(){
				this.$jq.unbind('click');
				this.$jq.on('click', _resetBTN.onClick);
			}
	};
	
	
	/**
	 * Funcion que dispara la busqueda a traves del boton buscar
	 */
	var _buscarBTN = {
		idHTML: "buscarBtn",
		$jq: $("#buscarBtn"),
		onClick: function(){
			_finder();
		},
		init: function(){
			this.$jq.unbind('click');
			this.$jq.on('click', _buscarBTN.onClick);
		}
	};
	
	
	/**
	 * Funcion que obtiene los datos del filtro de busquedas.
	 * Devuelve un objeto en formato JSON
	 */
	var getFiltrosJson = function (){
		var username = $(_username.idJquery).val();
		var descripcion = $(_descripcion.idJquery).val();
		
		var filtros= new FilterClass (username,descripcion);
		return filtros.prepareToJsonObject();
	}
	
	/**
	 * Funcion de error comun a las llamadas ajax
	 */
	 var errorProcess = function(data) {
	    	var respBody =data.responseJSON;
	        showMessageModule.createAlert("danger", respBody.messageForUser +"<p>"+ respBody.nameClass+"</p>");
	 };
	

	/**
	 * Inicializacion de eventos una vez carga la página.
	 */
	var initializePage = function (){
		$(document).ready(function() {
			
			//Eventos
			_keydownSearch();
			
			_buscarBTN.init();
			_resetBTN.init();
			
			//Le paso la locale
			listJqgridModule.createGrid("es");
			
		} );
	}
	
	return {
		initPage: initializePage,
		getFiltrosJson : getFiltrosJson,
		errorProcess:errorProcess,
	}
	
})();

mainJqgridModule.initPage();
showMessageModule.init();
