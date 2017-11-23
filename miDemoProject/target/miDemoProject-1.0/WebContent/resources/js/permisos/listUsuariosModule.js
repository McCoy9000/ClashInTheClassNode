var listUsuariosModule = (function(){
	'use strict';
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	//Variables privadas
	var _selectedRows = new Array();
	var _idHTML = "tabla";
	var _idJquery = "#tabla";
	var $jq = null;
	var _classInputSearchJquery = ".search-input-text-usuarios"
	var _edit = "false"; // Esta variable es para saber si se permite editar o no
	var _delete = "false";// Esta variable es para permitir si se permite borrar o no
	var _rolesview = "false";// Esta variable es para mostrar roles o no
	var _rolesedit = "false";//Esta variable es para poder editar roles o no
	
	var setEdit = function(val){
		_edit = val;
	}
	var setDelete = function(val){
		_delete = val;
	}
	var setRolesView = function(val){
		_rolesview = val;
	}
	var setRolesEdit = function(val){
		_rolesedit = val;
	}
	
	var _preLoadData = function(){
		//Esta función hay que invocarla antes de que se cargue la tabla, oculto por codigo el input de busqueda incrustado en el header de la tabla para poder 
		//utilizar otro buscador externo manteniendo la funcionalidad de la busqueda de datatables y abstraerme en el backend del front diseñado.
			$jq.on( 'preInit.dt', function () {
				$(_idJquery+"_filter").hide();
		  } );
	};
	
	var loadData = function (){	
		$jq = $(_idJquery);
		_preLoadData();
		$jq.DataTable( {
	   	 	"processing": true,
	   	 	"lengthMenu": [ 10,20,50 ],
	        "serverSide": true,
	        "language": {
	       	 "url": "./WebContent/3PResources/DataTables/i18n/spanish.json"
	        },
	        dom: "<'dataTables_header' i>" + "<'row'<'col-sm-12't>>" +  "<'dataTables_footer'<'col-sm-7'l> p>",
			ajax: {
				url: "./usuarios/",
				type: "GET",
                "error": _handleAjaxError
			 },				 
			"searching": true,
			 columns: [
		                 { data: null, className: "table-view-pf-select","width": "2%",
		               	  render: function (data, type, full, meta) {
		                       // Select row checkbox renderer
		                       var id = data.username;
		                       return '<input class=" select-checkbox" type="checkbox" id="u' +  meta.row  + '" name="selectedItems[]" value="'+id+'">';
		                     },
		                     sortable: false,
		                     "searching": false,
		                   },
							{ data: "username",  name:"USERNAME", 
		                	   className: "datos",
		                	   "width": "15%"
							},
							{ data: "flagldap", name:"FLDAP", "className": "text-center", 
								"width": "10%",
								 render: function (data, type, full, meta) {
				                       if (data == 0){
				                    	   return 'NO';
				                       }else{
				                    	   return 'SI';
				                       }
				                },
							},
							{ data: "descripcion", name:"DESCRIPTION",
								className: "datos"
								, "width": "65%"
							}
							,
							{ data: null, 
								"width": "8%", 
								className: "acciones",
						      render: function (data, type, full, meta) {
			
						    	  var id = data.username;
						    	  var aux = ''
						    	  aux = aux + '<div>';
						    	  if ( _edit == 'true' ){
						    		  aux = aux = '<button class="btn btn-warning editRow" onclick="listUsuariosModule.onClickEditRowBTN(\''+id+'\')" type="button" title="Editar registro" style="float:left"><span class="pficon pficon-edit"></span></button>';
						    	  }
						    	  if ( _delete == 'true' ){
						    	 	aux = aux +'<button class="btn btn-danger deleteRow" onclick="listUsuariosModule.onClickDeleteRowBTN(\''+id+'\')" type="button" title="Eliminar registro" style="float:right"><span class="pficon pficon-delete"></span></button>';									   
						    	  }
						    	  aux = aux + '</div>';
						    	  return aux;
						      },
						      sortable: false,
			                  "searching": false,
					    }
					]
			 		//Una vez que se acaba de pintar la tabla le asocio las siguientes acciones
			 		,"drawCallback": function( settings ) {
			 			listUsuariosModule.eventClickRow();
			 			listUsuariosModule.addComunCheckToTable();
			 			listUsuariosModule.resetSelectedRows();
			 		}
		});
	};
	
	  var _handleAjaxError = function(xhr, textStatus, error){
	    	if (xhr.status == 200){
		    	window.location.href = "./expiredSession";
		    }else{
				if ( xhr.status == 0 ){
					alert('ERROR GRAVE: PARECE QUE EL SERVIDOR NO RESPONDE O ESTÁ APAGADO. \n PÓNGASE EN CONTACTO CON EL ADMINISTRADOR DEL SISTEMA');
				}else{
					createAlert('danger','Se ha producido un error: ' + xhr.status + ' ' + error);
				}	
			}
	    	
	  };
	
	
	var eventClickRow = function (){
		/**
		 * Al clickar en la celda de una fila que no sea no sea la de la botonera,
		 * la selecciono o deselección aplicamos el comportamiento
		 */
		dualListRolesModule.selectorMultipleHide();
		$(_idJquery + " tbody tr td").not('.acciones,.datos,.text-center').on( 'click', function () {
		
			//Compruebo si la fila esta seleccionada o deseleccionada para realizar la accion inversa
			var accion=$(this).parent().hasClass('active')?"uncheck":"check";
			 
			//Si la a realizar es unchek, compruebo que el checkbox de la cabacera no este seleccionada para deseleccionarle en caso afirmativo 
			if (accion=='uncheck' && $('#checkboxes').prop("checked"))
			 $('#checkboxes').prop("checked", false);
			 
			 _clickRow($(this).parent(), accion);
		});
		
		$(_idJquery + " tbody tr td").not('.table-view-pf-select,.acciones').on( 'click', function () {
			var aux = ($($(this).parent()).children('td').eq(1)).text()
			$("#duallistboxUser").empty();
			// Solo mostramos tabla de roles si el usuario tiene permisos.
			if ( _rolesview == 'true'){
				dualListRolesModule.selectorMultipleShow(aux);
				dualListRolesModule.getRolesUsuario(aux);
				dualListRolesModule.getRolesUsuarioDisponibles(aux);
			}
			if ( _rolesedit == 'false'	){
				dualListRolesModule.disableMultiple();
			}
		});
	};
	
	
	var onClickEditRowBTN = function (id){
		//Llamada ajax para cargar el item
		dualListRolesModule.selectorMultipleHide();
		showMessageModule.removeAlert();
		formUsuarioModule.initializeWithData(id);
	};
	
	var onClickDeleteRowBTN = function (id){
		showMessageModule.removeAlert();
		dualListRolesModule.selectorMultipleHide();
		var arrayIds=new Array();
		var textConfirm="";
		
			arrayIds.push(id);
			textConfirm="Desea eliminar el registro: "+id;
			
			modalConfirmModule.showConfirmacion(textConfirm, arrayIds, formUsuarioModule.borrarDatos);
	};
	
	/**
	 * Funcion para filtrar a traves de los inputs de busqueda de las columnas
	 */
	var columnSearch= function (){
		$(_classInputSearchJquery).on('keydown', function (e) {   // for text boxes
			//Si se pulsa intro, lanzaramos el buscar.
			if(e.which == 13) {   
				
				var i =$(this).attr('data-column');  // getting column index
				var v =$(this).val();  // getting search input value				
				$jq.DataTable().columns(i).search(v).draw();
			}
		} );
	};

	var addComunCheckToTable = function(){
		/**
		 * Elimino el icono de ordenacion y añado un check para seleccion general 
		 */
		var $headCell1=$('table tr:eq(1) th:first');
		
		//Si existe el icono de ordenacion para la cabecera de la primera columna, lo elimino
		if ($headCell1.hasClass('sorting_asc'))
			$headCell1.removeClass('sorting_asc');
		
		//Si no existe el checkbox general en la cabecera de la primera columna, lo creo
		if ($('#checkboxes').length==0)
				$headCell1.append('<input class="select-checkbox" type="checkbox" id="checkboxes">');
		//Si existe, lo desmarco 
		else
			$('#checkboxes').prop("checked", false);
			
		//Si hacemos click en el check de la cabecera selecciono o deselecciono todas las filas en funcion del estado
		$headCell1.click(function(){
			var accion=$('#checkboxes').prop("checked")?"check":"uncheck";
			$.each($(_idJquery+" tbody tr"), function (indice, elemento){
				_clickRow($(elemento), accion);
			});
		});
		
	};
	
	var _clickRow = function ($fila, accion){
		/**
		 * Funcion que gestiona las acciones a realizar cuando seleccionamos o deseleccionamos una fila de la tabla.
		 * @param $fila tr
		 * @param accion (check - uncheck)
		 */
		//Obtengo el check de la fila
		var $check=$fila.find("input:checkbox");
		
		//Si la accion es deseleccion
		if (accion=='uncheck'){
	  		//Quito el estilo de la fila
			$fila.removeClass('active');
	  		//Deselecciono el check
	  		$check.prop("checked", false);
	  		//Elimino del array de seleccionados el elemento
	  		_selectedRows.splice( $.inArray($check.val(),_selectedRows) ,1 );
	  	}
	  	else{	
	  		//añado el estilo a la fila
	  		$fila.addClass('active');
	  		//Deselecciono el check
	  		$check.prop("checked", true);
	  		//Si el elemento no existe en el array, lo inserto
	  		if ($.inArray($check.val(),_selectedRows)==-1)
	  			_selectedRows.push($check.val());
	  	}

		_setSelectedRowsNumber();
		dualListRolesModule.selectorMultipleHide();
	};
	
	var resetSelectedRows = function(){
		/**
		 * Funcion para vaciar la lista de registros seleccionados
		 */
		//Limpio el array de seleccionados
		_selectedRows=new Array();
		//Restablezco el numero de registros seleccionados
		_setSelectedRowsNumber();
	};
	
	var _setSelectedRowsNumber = function (){
		/**
		 * Renderiza el numero de registros seleccionados en elementos con la clase
		 * y deshabilita o habilita en funcion del numero de registros con la clase bulkAction  
		 */
		var selectedRowsNumber=_selectedRows.length;
		
		if (selectedRowsNumber>1){
			$('#bulkDeleteBtn').removeClass("disabled");
			$('#bulkDeleteBtn').prop("disabled", "");
			//Añado el numero de filas seleccionadas en la pagina a los elementos 
			$(".selectedRowsNumber").text("("+_selectedRows.length+")");
		}
		else if (selectedRowsNumber<=1){
			$('#bulkDeleteBtn').addClass("disabled");
			$('#bulkDeleteBtn').prop("disabled", "disabled");
			//Añado el numero de filas seleccionadas en la pagina a los elementos 
			$(".selectedRowsNumber").text("");
		}
	};		
	
	var refreshSearch = function(){
		$jq.DataTable().search("").draw(false);
	};
	
	var getSelectedRows = function(){
		return _selectedRows;
	};
	
	var getDataTable = function(){ return $jq.DataTable(); }
	
	return{
		getDataTable: getDataTable,
		loadData: loadData,
		onClickEditRowBTN: onClickEditRowBTN,
		onClickDeleteRowBTN: onClickDeleteRowBTN,
		eventClickRow: eventClickRow,
		addComunCheckToTable: addComunCheckToTable,
		resetSelectedRows: resetSelectedRows,
		refreshSearch: refreshSearch,
		columnSearch : columnSearch,
		getSelectedRows: getSelectedRows,
		setEdit: setEdit,
		setDelete: setDelete,
		setRolesView:setRolesView,
		setRolesEdit:setRolesEdit
	}

})();


