var listPermisosModule = (function(){
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
	var _idHTML = "tablaPermisos";
	var _idJquery = "#tablaPermisos";
	var $jq = null;
	var _classInputSearchJquery = ".search-input-text-permisos"
		
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
				url: "./permisos/",
				type: "GET",
                "error": _handleAjaxError
			 },				 
			"searching": true,
			 columns: [
		                 { data: null, className: "table-view-pf-select","width": "2%",
		               	  render: function (data, type, full, meta) {
		                       return '';
		                     },
		                     sortable: false,
		                     "searching": false,
		                   },
							{ data: "permissionname",  name:"PERMISSIONNAME", 
		                	   className: "datos",
		                	   "width": "28%"
							},
							{ data: "description", name:"DESCRIPTION", 
								className: "datos",
								"width": "68%"
							},
							{ data: null, 
								"width": "2%", 
								className: "acciones",
						      render: function (data, type, full, meta) {
						    	  return '';
						      },
						      sortable: false,
			                  "searching": false,
					    }
					]//Una vez que se acaba de pintar la tabla le asocio las siguientes acciones
		 		,"drawCallback": function( settings ) {
		 			eliminarIconoOrdenacion();
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
	
	var refreshSearch = function(){
		$jq.DataTable().search("").draw(false);
	};
	
	var eliminarIconoOrdenacion = function(){
		/**
		 * Elimino el icono de ordenacion y añado un check para seleccion general 
		 */
		var $headCell1=$('#tablaPermisos tr:eq(1) th:first');
		
		//Si existe el icono de ordenacion para la cabecera de la primera columna, lo elimino
		if ($headCell1.hasClass('sorting_asc'))
			$headCell1.removeClass('sorting_asc');
	};
	
	var getDataTable = function(){ return $jq.DataTable(); }
	
	return{
		getDataTable: getDataTable,
		loadData: loadData,
		refreshSearch: refreshSearch,
		columnSearch : columnSearch
	}

})();


