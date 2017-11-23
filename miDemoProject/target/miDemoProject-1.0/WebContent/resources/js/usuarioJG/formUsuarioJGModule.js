'use strict';
function UsuarioClass (username, password,flagldap, descripcion) {
	/*Clase */		
	this.username = username;
	this.password= password;
	this.flagldap= flagldap;
	this.descripcion= descripcion;
		
    // Preparamos la URL que se va a llamar de forma asincrona (AJAX)
    this.url = "jqGridCrud/";
	    
    //Metodos	
	this.prepareToJsonObject = function (){
		var jsonObject = null;
			jsonObject = {
				 "username" : this.username,
				 "password" : this.password,
				 "flagldap" : this.flagldap,
				 "descripcion": this.descripcion
			 }; 
		return jsonObject;
	}
	    	    
    this.runAjaxCallGetItem = function(pDoneCallback, pErrorCallback){
   		var ajaxCall = ajaxModule.getJSON(this.url+this.username, null, pDoneCallback, pErrorCallback);
    };
    
    this.runAjaxCallCreateItem = function(pDoneCallback, pErrorCallback){
   		var ajaxCall = ajaxModule.postJSON(this.url, JSON.stringify(this.prepareToJsonObject()), pDoneCallback, pErrorCallback);
    };
    
    this.runAjaxCallUpdateItem = function(pDoneCallback, pErrorCallback){
   		var ajaxCall = ajaxModule.putJSON(this.url+this.username, JSON.stringify(this.prepareToJsonObject()), pDoneCallback, pErrorCallback);
    };
    
    this.runAjaxCallDeleteItems = function(pDoneCallback, pErrorCallback){
   		var ajaxCall = ajaxModule.deleteJSON(this.url+this.username, null, pDoneCallback, pErrorCallback);
    };
	    
}

var formUsuarioJGModule = (function(){
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var _idHTML = "modal";
	var _idJquery = "#modal";	
	var $modal = null; // function(){return $(_idJquery);}; //Se protege con una función para evitar dependencia de JQuery en tiempo de carga de la página.
	
    // ¿Que hacer si la llamada ajax para delete, Update y Create ha ido bien? 
    var successSaveItem = function(data){
    	listUsuarioJGModule.reloadGrid();
    	showMessageModule.createAlert("success", data);
    };
	
	var guardarBTN = {
			idHTML: "btnSave",
			$jq: null, //function () {return $("#btnSave")},
			onClick: function (){
				$modal.modal("hide");
				//Cargamos el item con los valores del formulario
				var username=$("#username").val();
				var password=$("#password").val();
				var flagldap=$('#flagldap').bootstrapSwitch('state')?1:0;
				var descripcion=$("#descripcion").val();
				
				var usuario = new UsuarioClass (username,password,flagldap,descripcion);
				
				var accion=$modal.data("accion");
				//Valores posibles para accion: nuevo y editar
				if (accion=="nuevo")
					usuario.runAjaxCallCreateItem(successSaveItem, mainUsuarioJGModule.errorProcess);
				
				else if (accion=="editar")
					usuario.runAjaxCallUpdateItem(successSaveItem, mainUsuarioJGModule.errorProcess);
			},
			init: function(){
				this.$jq = $("#" + this.idHTML);
				this.$jq.unbind('click');
				this.$jq.on('click', guardarBTN.onClick);
			}
	}
	
	var borrarDatos = function (arrayIds){
		//Llamada ajax para eliminar items
		var usuario = new UsuarioClass (arrayIds);
		
		var paginaActual=$(listUsuarioJGModule.$jqgrid).getGridParam('page');
		
		//Si la pagina actual es mayor que 1 
		if (paginaActual>1){
			//Si al borrar dejamos a la pagina sin registros
			if(arrayIds.length - $(listUsuarioJGModule.$jqgrid).getGridParam('reccount')==0){
				//establecemos la pagina a mostrar como la pagina anterior  
				$(listUsuarioJGModule.$jqgrid).setGridParam({page:paginaActual-1})	
			}
		}
		
		usuario.runAjaxCallDeleteItems(successSaveItem, mainUsuarioJGModule.errorProcess);
	}
	
	var initializeWithData = function (id){
		
		var mostrarDatos = function (data){  //cargarFormulario
			var titulo = i18nModule._("title.edit")+" "+data.username;
			$modal.find('.modal-title').text(titulo);
			//Añado un identificador al formulario para diferenciar la acciona realizar cuando pulso guardar
			$modal.data("accion", "editar");
			$("#username").val(data.username);
			$("#username").attr('data-id', data.username);
			$("#password").val(data.password);
			
			if (data.flagldap=="1")
				$('#flagldap').bootstrapSwitch('state', true);
			else
				$('#flagldap').bootstrapSwitch('state', false);
			
			$("#descripcion").val(data.descripcion);
		
			$modal.modal("show");
		}
		
		var usuario = new UsuarioClass (id);
		usuario.runAjaxCallGetItem(mostrarDatos, mainUsuarioJGModule.errorProcess);
	}
		
	var initializeNew = function(){
		$modal.find('.modal-title').text(i18nModule._("title.new"));
		$modal.find('.modal-body :input').val("");
		$('#flagldap').bootstrapSwitch('state', false);
		//Añado un identificador al formulario para diferenciar la acciona realizar cuando pulso guardar
		$modal.data("accion", "nuevo");
		$modal.modal("show");	
	};
	
	var init = function() {
		$modal = $(_idJquery);
		guardarBTN.init();
		$("#flagldap").bootstrapSwitch();
	};
	
	return {
		init : init,
		initializeNew: initializeNew,
		initializeWithData: initializeWithData,
		borrarDatos: borrarDatos
	}
})();
