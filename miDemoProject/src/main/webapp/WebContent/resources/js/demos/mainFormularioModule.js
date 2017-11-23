function UsuarioDemo (fIncorporacion, tDesplegable, areaText, responsableText, nombre, apellido1, apellido2, email, telefono, movil) {
	/*Clase */		
	this.fIncorporacion=fIncorporacion;
	this.tDesplegable=tDesplegable;
	this.areaText=areaText;
	this.responsableText=responsableText;
	this.nombre=nombre;
	this.apellido1=apellido1;
	this.apellido2=apellido2;
	this.email=email;
	this.telefono=telefono;
	this.movil=movil;
}


var mainFormularioModule = (function(){
	'use strict';
	
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	/**
	  * Funcion para inicializar los tooltips
	  */
	 var _toolTip= {
			 idJquery: "a.infoToolTip",
			 init: function(){
				$(this.idJquery).tooltip();
			 }
		}
	
	//Formatea el texto del combo con "codigo - descripcion"
	var _formatDescripcionCombo =function (codigo, descripcion){
		return codigo + "-" + descripcion;
	}
	
    var _errorProcess = function(data) {
    	var respBody =data.responseJSON;
        showMessageModule.createAlert("danger", respBody.messageForUser +"<p>"+ respBody.nameClass+"</p>");
    };
	
    /**Combobox**/
    var respCBO = {
		idHTML: "responsableCmb",
		idJquery: "#responsableCmb",
		onChange: function(){
			
		},
		populate:function(data){
			_cargarCombobox(data, $(respCBO.idJquery));
		},
		init: function(){
			$(this.idJquery).combobox();
			$(this.idJquery).unbind('change');
			$(this.idJquery).on('change', respCBO.onChange);
		}
    }
        
	var areaCBO = {
		idHTML: "areaCmb",
		idJquery: "#areaCmb",
		onChange: function(){				

			if ($(this).val()!=null){
				var url= "./area/"+$(this).val()+"/responsables";
				ajaxModule.getJSON(url, null, respCBO.populate, _errorProcess);	
			}
			else{
				//Si no existen datos, deshabilito el combo
				$(respCBO.idJquery).combobox('disable');
				$(respCBO.idJquery).data('combobox').clearElement();
			}
			
		},
		init: function(){
			$(this.idJquery).combobox();
			$(this.idJquery).unbind('change select keyup');
			$(this.idJquery).on('change select keyup', areaCBO.onChange);
		}
	}
    
    //Funcion que habilita y carga las opciones del combo.
    var _cargarCombobox = function (data, $combo){
    	
    	var options = "";
    	
    	//Si existen registros en el data
    	if (data.length>0){
    		//recorro los elementos del data para dar valor a los options del combo
    		$.each(data, function (indice, elemento){
    			options = options
				+ "<option value='"
				+ elemento.codigo
				+ "'>"
				+ _formatDescripcionCombo(elemento.codigo,
						elemento.descripcion) + "</option>";
    		});
    		
    		//Habilito el combo
    		$combo.combobox('enable');
    	}else{
    		//Si no existen datos, deshabilito el combo
    		$combo.combobox('disable');
    		//$combo.data('combobox').clearTarget();
    		$combo.data('combobox').clearElement();
    	}
    		
    	$combo.html(options);
    	
    	$combo.data('combobox').refresh();
    	
    }

    
    /**Selectpickers**/
    var respSEL = {
		idHTML: "responsableSelect",
		idJquery: "#responsableSelect",
		onChange: function(){
			//$(this.idJquery).selectpicker('refresh'); 
		},
		populate:function(data){
			_cargarSelector(data, $(respSEL.idJquery)); 
		},
		init: function(){
			$(this.idJquery).unbind('changed.bs.select');
			$(this.idJquery).on('changed.bs.select', respSEL.onChange);
		}
    }
    
	var areaSEL = {
		idHTML: "areaSelect",
		idJquery: "#areaSelect",
		onChange: function(){				
			var url= "./area/"+$(this).selectpicker('val')+"/responsables";
			ajaxModule.getJSON(url, null, respSEL.populate, _errorProcess);
		},
		init: function(){
			$(this.idJquery).unbind('changed.bs.select');
			$(this.idJquery).on('changed.bs.select', areaSEL.onChange);
		}
	}
	
    //Funcion que habilita y carga las opciones del combo.
    var _cargarSelector = function (data, $combo){

    	//Borro los options exitentes
    	$combo.find("option").remove();
		
    	//Si existen registros en el data
    	if (data.length>0){
    		//recorro los elementos del data para dar valor a los options del combo
    		$.each(data, function (indice, elemento){
    			$combo.append($('<option>', {
    			    value: elemento.codigo,
    			    text: _formatDescripcionCombo (elemento.codigo,elemento.descripcion)
    			}));
    		});
    		
    		//Habilito el combo
    		$combo.prop('disabled', false);
    	}else{
    		//Si no existen datos, deshabilito el combo
    		$combo.prop('disabled', true);
    	}
    	
    	//Despues de manipular el selector es necesario refrescarlo
		$combo.selectpicker('refresh');
    }
    
    var check = {
		idHTML: "flag",
		idJquery: "#flag",
		onChange: function (event, state){
			if (!state){
				$("#comboboxGroup").addClass("hide");
				$("#selectGroup").removeClass("hide");
			}
			else{
				$("#comboboxGroup").removeClass("hide");
				$("#selectGroup").addClass("hide");
			}
		},
		init: function(){
			//configuracion del switch
			$(this.idJquery).bootstrapSwitch();
			$(this.idJquery).unbind('switchChange.bootstrapSwitch');
			$(this.idJquery).on('switchChange.bootstrapSwitch', check.onChange);
		}
    }
      
    var aceptar={
		idHTML: "aceptarBtn",
		idJquery: "#aceptarBtn",
		onClick: function (){
			
			var fIncorporacion = $(calendario.idJquery).data('datepicker').getFormattedDate('dd/mm/yyyy');
			
			var tDesplegable = $(check.idJquery).bootstrapSwitch('state');
			
			var areaText = tDesplegable ? $(areaCBO.idJquery+" option:selected").text() : $(areaSEL.idJquery).find("option:selected").text();
			var responsableText=tDesplegable ? $(respCBO.idJquery+" option:selected").text() : $(respSEL.idJquery).find("option:selected").text();
			var nombre =$("#nombre").val();
			var apellido1= $("#apellido1").val();
			var apellido2= $("#apellido2").val();
			var email=$("#email").val();
			var telefono=$("#telefono").val();
			var movil= $("#movil").val();

			var usuario = new UsuarioDemo (fIncorporacion, tDesplegable, areaText.trim(), responsableText.trim(), nombre, apellido1, apellido2, email, telefono, movil);
			
			modalFormModule.showModal(usuario);
		},
		
		init: function(){
			$(this.idJquery).unbind('click');
			$(this.idJquery).on('click', aceptar.onClick);
		}
    }
    
    var limpiar={
		idHTML: "limpiarBtn",
		idJquery: "#limpiarBtn",
		onClick: function (){
			$("#demoform")[0].reset();
			//reseteo calendario
			$(calendario.idJquery).datepicker('update', '');

			//refresco el area combo
			$(areaCBO.idJquery).data('combobox').clearElement();
			$(areaCBO.idJquery).data('combobox').clearTarget();
			$(areaCBO.idJquery).combobox().val("0");
			$(areaCBO.idJquery).data('combobox').refresh();
			
			//refresco el area select
			$(areaSEL.idJquery).selectpicker('val', 0);
			$(areaSEL.idJquery).selectpicker('refresh')
			
			//deshabilito el responsable combo
			$(respCBO.idJquery).combobox('disable');	    	
			$(respCBO.idJquery).html("");

        	$(respCBO.idJquery).data('combobox').clearElement();
        	$(respCBO.idJquery).data('combobox').clearTarget();
        	$(respCBO.idJquery).data('combobox').refresh();
        	
        	//deshabilito el responsable select
        	$(respSEL.idJquery).find("option").remove();
        	$(respSEL.idJquery).prop('disabled', true);
        	$(respSEL.idJquery).selectpicker('refresh');
    		
		},
		init: function(){
			$(this.idJquery).unbind('click');
			$(this.idJquery).on('click', limpiar.onClick);
		}
	}
    
    var calendario={
		idHTML: "date",
		idJquery: "#date",
		init: function(){
			//configuracion del calendario
			$(this.idJquery).datepicker({
			    language : 'es',
			    autoclose: true
			  });
		}
    }
    
	var initializePage = function (){
		$(document).ready(function() {		
			
			//Activamos eventos del ShowMessage
			showMessageModule.init();
			//inicializacion de selects
			areaSEL.init();
			respSEL.init();

			//inicializacion de combos
			areaCBO.init();
			respCBO.init();
			
			//inicializacion del check
			check.init();
			//inicializacion del datepicker
			calendario.init();
			
			//Tooltip
			_toolTip.init();
			
			//Botones
			aceptar.init();
			limpiar.init();
			
		} );
	}
	
	return {
		initPage: initializePage		
	}
})();

mainFormularioModule.initPage();



var modalFormModule = (function(){
	'use strict';
	var _idHTML = "modal";
	var _idJquery = "#modal";	
	
	var showModal = function(usuario){
		activateEvents();
		$("#fIncorporacion").html("<strong>"+ usuario.fIncorporacion +"</strong>");
		$("#tDesplegable").html("<strong>"+ (usuario.tDesplegable ? "Combobox": "Select" ) +"</strong>");
		$("#areaText").html("<strong>"+ usuario.areaText +"</strong>");
		$("#responsableText").html("<strong>"+ usuario.responsableText  +"</strong>");
		
		$("#usuarioText").html("<strong>"+ usuario.nombre+ " " + usuario.apellido1 + " " + usuario.apellido2 +"</strong>");
		$("#emailText").html("<strong>"+ usuario.email  +"</strong>")
		$("#telefonoText").html("<strong>"+ usuario.telefono  +"</strong>")
		$("#movilText").html("<strong>"+ usuario.movil  +"</strong>")
		$(_idJquery).modal("show");
		
	};
	
	var hideModal = function(){
		//Limpio los datos introducidos
		$("#fIncorporacion").html("");
		$("#tDesplegable").html("");
		$("#areaText").html("");
		$("#responsableText").html("");
		$("#usuarioText").html("");
		$("#emailText").html("");
		$("#telefonoText").html("");
		$("#movilText").html("");
	}
	
	var activateEvents= function(){
		//Evento cerrar ventana
		$(_idJquery).unbind('hidden.bs.modal');
		$(_idJquery).on('hidden.bs.modal', hideModal);
	}
	return {
		showModal: showModal,
		activateEvents:activateEvents
	};
})();