var listSesionesModule = (function(){
	'use strict';
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	var _expire = false;
	var _idHTML = "tablaSesiones";
	var _idJquery = "#tablaSesiones";
	var $jq = null;
	var _classInputSearchJquery = ".search-input-text"
    var _url = "./sesiones/expirar/";
		
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
				url: "./sesiones/",
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
							{ data: "principal.userCode",  name:"USERCODE", 
		                	   className: "datos",
		                	   "width": "11%"
							},
							{ data: "principal.username", name:"USERNAME", 
								className: "datos",
								"width": "17%"
							},
							{ data: "sessionId", name:"SESSIONID", 
								className: "datos",
								"width": "17%"
							},
							{ data: "principal.ip", name:"IP", 
								className: "datos",
								"width": "17%"
							},
							{ data: "principal.logintime", name:"LOGINTIME", 
								className: "datos","width": "17%",
								render: function (data, type, full, meta) {
									 if ( data != null){
											return (moment(data).format("DD/MM/YYYY - HH:mm:ss:SSS"));
										}else{
											return '';
										}
				                },
							},
							{ data: "principal.logouttime", name:"LOGOUTTIME", 
								className: "datos","width": "17%",
								render: function (data, type, full, meta) {
									if ( data != null){
										return (moment(data).format("DD/MM/YYYY - HH:mm:ss:SSS"));
									}else{
										return '';
									}
								},
							},
							{ data: null, 
								"width": "2%", 
								className: "acciones",
						        render: function (data, type, full, meta) {
						        var aux = '';
						        if ( (_expire == true) && (data.principal.logouttime == null)  ){
						        	aux = '<button class="btn btn-danger deleteRow" onclick="listSesionesModule.onClickExpireRowBTN(\''+data.sessionId+'\')" type="button" style="float:right"><span class="pficon pficon-delete"></span></button>';
						        }
						        return aux;
						      },
						      sortable: false,
			                  "searching": false,
					    }
					]//Una vez que se acaba de pintar la tabla le asocio las siguientes acciones
		 		,"drawCallback": function( settings ) {
		 			_eliminarIconoOrdenacion();
		 		}
		});
		
	};
	
	var onClickExpireRowBTN = function (id){
		showMessageModule.removeAlert();
		var ajaxCall = ajaxModule.putJSON(_url+id, null, successExpired, errorProcess);
	};
	
	
	var successExpired = function(data){
		listSesionesModule.refreshSearch();
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
	var _eliminarIconoOrdenacion = function(){
		/**
		 * Elimino el icono de ordenacion y añado un check para seleccion general 
		 */
		var $headCell1=$('#tablaSesiones tr:eq(1) th:first');
		
		//Si existe el icono de ordenacion para la cabecera de la primera columna, lo elimino
		if ($headCell1.hasClass('sorting_asc'))
			$headCell1.removeClass('sorting_asc');
	};
	
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
	
	var getDataTable = function(){ return $jq.DataTable(); }
	var setExpire = function(val){
		_expire = val;
	}
	return{
		getDataTable: getDataTable,
		loadData: loadData,
		refreshSearch: refreshSearch,
		columnSearch : columnSearch,
		onClickExpireRowBTN:onClickExpireRowBTN,
		setExpire:setExpire
	}
})();