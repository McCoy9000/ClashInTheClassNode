var dualListRolesModule = (function(){
	'use strict';

	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	var _titulocab = '#tituloDualList';
	
	var saveRolesBTN = {
			idHTML: "saveRolesBtn",
			$jq: $("#saveRolesBtn"),
			onClick: null,
			init: function(){
				this.$jq.unbind('click');
				this.$jq.on('click', 
						function(){
							var rolesSeleccionados = [];
							$('#duallistboxUser option').each(function() {
						    		if($(this).is(':selected')){
						    			rolesSeleccionados.push(this.value); 
						    		}
							});
							var id = $(_titulocab).text();
							if ( rolesSeleccionados.length == 0 ){
								rolesSeleccionados.push('VACIO');
							}
							showMessageModule.removeAlert();
							var ajaxCall = ajaxModule.putJSON('./usuarios/rolesUsuarioUpdate/'+id+'/'+rolesSeleccionados, null, successProcessRolesUpdate, errorProcess);
						}
				); 
			}
	};
	
	var selectorMultiple={
	        idHTML: "duallistboxUser",
	        idJquery: "#duallistboxUser",
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
	                nonSelectedListLabel: '<b>Roles disponibles</b>',
	                selectedListLabel: '<b>Roles seleccionados</b>'
	            });
	        },
	        show:function(){
	        	$(this.idJquery+'Div').css({"visibility": ""});
	        	$(this.idJquery+'Div').show();
	        },
	        hide:function(){
	        	
	        	$(this.idJquery+'Div').hide();
	        },
	        getroles:function(id){
	        	var ajaxCall = ajaxModule.getJSON('./usuarios/rolesUsuario/'+id, null, successProcessRoles, errorProcess);
	        	
	        },
	        getrolesdispon: function(id){
	        	var ajaxCall = ajaxModule.getJSON('./usuarios/rolesUsuarioDispon/'+id, null, successProcessRolesDisponibles, errorProcess);  
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
	
	 var successProcessRoles = function(data){
			
			$.each(data, function(i, item) {
			     $("#duallistboxUser").append('<option value="'+item.rolename+'" selected>'+item.rolename+'</option>');
			  	 $("#duallistboxUser").bootstrapDualListbox('refresh');
		    });
    };
    var successProcessRolesDisponibles = function(data){
    	    //$("#duallistboxUser").empty();
    		$.each(data, function(i, item) {
			     $("#duallistboxUser").append('<option value="'+item.rolename+'" >'+item.rolename+'</option>');
			  	 $("#duallistboxUser").bootstrapDualListbox('refresh');
		    });
    };
    
    var successProcessRolesUpdate = function(data){
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
	var getRolesUsuario = function(id){
		selectorMultiple.getroles(id);
	}
	var getRolesUsuarioDisponibles = function(id){
		selectorMultiple.getrolesdispon(id);
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
		getRolesUsuario: getRolesUsuario,
		getRolesUsuarioDisponibles:getRolesUsuarioDisponibles
	}
	
})();