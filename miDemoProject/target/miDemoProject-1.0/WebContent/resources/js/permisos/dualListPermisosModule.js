var dualListPermisosModule = (function(){
	'use strict';

	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var _titulocab = '#tituloDualListRoles';
	
	var saveRolesBTN = {
			idHTML: "savePermisosBtn",
			$jq: $("#savePermisosBtn"),
			onClick: null,
			init: function(){
				this.$jq.unbind('click');
				this.$jq.on('click', 
						function(){
							var permisosSeleccionados = [];
							$('#duallistboxRole option').each(function() {
						    		if($(this).is(':selected')){
						    			permisosSeleccionados.push(this.value); 
						    		}
							});
							var id = $(_titulocab).text();
							if ( permisosSeleccionados.length == 0 ){
								permisosSeleccionados.push('VACIO');
							}
							showMessageModule.removeAlert();
							var ajaxCall = ajaxModule.putJSON('./roles/permisosRolUpdate/'+id+'/'+permisosSeleccionados, null, successProcessPermisosUpdate, errorProcess);
						}
				); 
			}
	};
	
	var selectorMultiple={
	        idHTML: "duallistboxRole",
	        idJquery: "#duallistboxRole",
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
	                nonSelectedListLabel: '<b>Permisos disponibles</b>',
	                selectedListLabel: '<b>Permisos seleccionados</b>'
	            });
	        },
	        show:function(){
	        	$(this.idJquery+'Div').css({"visibility": ""});
	        	$(this.idJquery+'Div').show();
	        },
	        hide:function(){
	        	
	        	$(this.idJquery+'Div').hide();
	        },
	        getpermisos:function(id){
	        	var ajaxCall = ajaxModule.getJSON('./roles/permisosRol/'+id, null, successProcessPermisos, errorProcess);
	        	
	        },
	        getpermisosdispon: function(id){
	        	var ajaxCall = ajaxModule.getJSON('./roles/permisosRolDispon/'+id, null, successProcessPermisosDisponibles, errorProcess);  
	        },
	        disable: function(){
	        	 $('select[name="'+this.idHTML+'_helper1"]').prop('disabled', true);
	        	 $('select[name="'+this.idHTML+'_helper2"]').prop('disabled', true);
	        	 $('select[name="'+this.idHTML+'"]').parent().find('.moveall').prop('disabled',true);
	        	 $('select[name="'+this.idHTML+'"]').parent().find('.move').prop('disabled',true);
	        	 $('select[name="'+this.idHTML+'"]').parent().find('.removeall').prop('disabled',true);
	        	 $('select[name="'+this.idHTML+'"]').parent().find('.remove').prop('disabled',true);
	        }
	    }
	
	 var successProcessPermisos = function(data){
			
			$.each(data, function(i, item) {
			     $("#duallistboxRole").append('<option value="'+item.permissionname+'" selected>'+item.permissionname+'</option>');
			  	 $("#duallistboxRole").bootstrapDualListbox('refresh');
		    });
    };
    var successProcessPermisosDisponibles = function(data){
    	    //$("#duallistboxUser").empty();
    		$.each(data, function(i, item) {
			     $("#duallistboxRole").append('<option value="'+item.permissionname+'" >'+item.permissionname+'</option>');
			  	 $("#duallistboxRole").bootstrapDualListbox('refresh');
		    });
    };
    
    var successProcessPermisosUpdate = function(data){
    	showMessageModule.createAlert("success", data);
    }
    
    var errorProcess = function(data) {
    	
    	var contentype = data.getResponseHeader("Content-Type");
    	if ( contentype == 'application/json;charset=UTF-8'){
       	 	var respBody =data.responseJSON;
            showMessageModule.createAlert("danger", respBody.messageForUser +"<p>"+ respBody.error+"</p>"); 
    	}else{
    		showMessageModule.createAlert("danger", "Código Error : "+ data.status +" - "+ data.statusText);
    	}
    
    };
	
	var selectorMultipleShow = function(id){
		$(_titulocab).html(id);
		selectorMultiple.show();
	}
	var selectorMultipleHide = function(){
		$(_titulocab).html('');
		selectorMultiple.hide();
		
	}
	var disableMultiple = function(){
		selectorMultiple.disable();
	}
	var getPermisosRol = function(id){
		selectorMultiple.getpermisos(id);
	}
	var getPermisosRolDisponibles = function(id){
		selectorMultiple.getpermisosdispon(id);
	}
	var init = function(){
		selectorMultiple.init();
		saveRolesBTN.init();
	}
	return {
		init:init,
		selectorMultipleShow:selectorMultipleShow,
		selectorMultipleHide:selectorMultipleHide,
		disableMultiple:disableMultiple,
		getPermisosRol: getPermisosRol,
		getPermisosRolDisponibles:getPermisosRolDisponibles
	}
	
})();