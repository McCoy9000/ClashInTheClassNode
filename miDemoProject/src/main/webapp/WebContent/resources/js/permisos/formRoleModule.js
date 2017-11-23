'use strict';
function RoleClass (rolename,description) {
	/*Clase */		
	this.rolename = rolename;
	this.description= description;
		
    // Preparamos la URL que se va a llamar de forma asincrona (AJAX)
    this.url = "./roles/";
	    
    //Metodos	
	this.prepareToJsonObject = function (){
		var jsonObject = null;
			jsonObject = {
				 "rolename" : this.rolename,
				 "description": this.description
			 }; 
		return jsonObject;
	}
	    	    
    this.runAjaxCallGetItem = function(pDoneCallback, pErrorCallback){
   		var ajaxCall = ajaxModule.getJSON(this.url+this.rolename, null, pDoneCallback, pErrorCallback);
    };
    this.runAjaxCallCreateItem = function(pDoneCallback, pErrorCallback){
   		var ajaxCall = ajaxModule.postJSON(this.url, JSON.stringify(this.prepareToJsonObject()), pDoneCallback, pErrorCallback);
    };
    
    this.runAjaxCallUpdateItem = function(pDoneCallback, pErrorCallback){
   		var ajaxCall = ajaxModule.putJSON(this.url+this.rolename, JSON.stringify(this.prepareToJsonObject()), pDoneCallback, pErrorCallback);
    };
    
    this.runAjaxCallDeleteItems = function(pDoneCallback, pErrorCallback){
    	var ajaxCall = ajaxModule.deleteJSON(this.url+this.rolename, null, pDoneCallback, pErrorCallback);
    };
	    
}
	

var formRoleModule = (function(){
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var _idHTML = "modalRole";
	var _idJquery = "#modalRole";	
	var $modal = null; // function(){return $(_idJquery);}; //Se protege con una función para evitar dependencia de JQuery en tiempo de carga de la página.
		
	var init = function() {
		$modal = $(_idJquery);
		guardarBTN.init();
	};
	
	// ¿Qué hacer si la llamada ajax para coger datos ha ido bien?
    var successGetItem = function(data) {
    	formRoleModule.initializeWithData(data);
    };
    
    // ¿Que hacer si la llamada ajax para delete, Update y Create ha ido bien? 
    var successSaveItem = function(data){
    	listRolesModule.refreshSearch();
    	showMessageModule.createAlert("success", data);
    };
    
    // ¿Qué hacemos si la llamada Ajax genera algún error?
    var errorProcess = function(data) {
    
    	var contentype = data.getResponseHeader("Content-Type");
    	if ( contentype == 'application/json;charset=UTF-8'){
       	 	var respBody =data.responseJSON;
            showMessageModule.createAlert("danger", respBody.messageForUser +"<p>"+ respBody.error+"</p>"); 
    	}else{
       		showMessageModule.createAlert("danger", "Código Error : "+ data.status +" - "+ data.statusText);
    	}
    };
	
	var guardarBTN = {
			idHTML: "btnSaveRole",
			$jq: null, //function () {return $("#btnSave")},
			onClick: function (){
				$modal.modal("hide");
				//Cargamos el item con los valores del formulario
				var rolename=$("#rolename").val();
				var description=$("#description").val();
				var role = new RoleClass (rolename,description);
				var accion=$modal.data("accion");
				//Valores posibles para accion: nuevo y editar
				if (accion=="nuevo")
					role.runAjaxCallCreateItem(successSaveItem, errorProcess);
				
				else if (accion=="editar")
					role.runAjaxCallUpdateItem(successSaveItem, errorProcess);
			},
			init: function(){
				this.$jq = $("#" + this.idHTML);
				this.$jq.unbind('click');
				this.$jq.on('click', guardarBTN.onClick);
			}
	}
	
	var borrarDatos = function (arrayIds){
		//Llamada ajax para eliminar items
		
		var role = new RoleClass (arrayIds);
		role.runAjaxCallDeleteItems(successSaveItem, errorProcess);
	}
	
	var initializeWithData = function (id){
		
		var mostrarDatos = function (data){  //cargarFormulario
			var titulo = i18nModule._("title.edit")+" "+data.rolename;
			$modal.find('.modal-title').text(titulo);
			//Añado un identificador al formulario para diferenciar la acciona realizar cuando pulso guardar
			$modal.data("accion", "editar");
			$("#rolename").val(data.rolename);
			$("#description").val(data.description);
			$modal.modal("show");
		}
		
		var role = new RoleClass (id);
		role.runAjaxCallGetItem(mostrarDatos, errorProcess);
	}
		
	var initializeNew = function(){
		$modal.find('.modal-title').text(i18nModule._("title.new"));
		$modal.find('.modal-body :input').val("");
		//Añado un identificador al formulario para diferenciar la acciona realizar cuando pulso guardar
		$modal.data("accion", "nuevo");
		$modal.modal("show");	
	};
	
	return {
		initializeNew: initializeNew,
		initializeWithData: initializeWithData,
		init : init,
		borrarDatos: borrarDatos
	}
})();
