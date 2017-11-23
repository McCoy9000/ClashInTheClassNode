'use strict';

/*Clase para el objeto grid*/
var GridClass = function (locale){
	// Atributos
	this.name = listUsuarioJGModule.idjqgridHTML;
	this.nameJQuery = listUsuarioJGModule.$jqgrid;
	this.colNames = null;
	this.cm = [	
			{
				"name" : "username",
				"index": "USERNAME",
				"key": true,
				"width" : 100
			},
			{
				"name" : "password",
				"index": "PASSWORD",
				"width" : 100,
				search:false,
				sortable:false
			}
			,{
				"name" : "flagldap",
				"index": "FLDAP",
				"width" : 50,
				"align" : "center",
				formatter:'select', 
				stype: 'select',
				searchoptions: { value: ":Todos;1:SI;0:NO" },
				sorttype: function(cell)  {       	 
		        	var label=["todos", "si", "no"]; 
					return label[cell];
		        },
		        editoptions: { value: ":Todos;1:SI;0:NO"}
			}
			,{
				"name" : "descripcion",
				"index": "DESCRIPTION",
				"width" : 150,
				"editable": true,
				"edittype": 'text'
			},
			{
				"name" : "acciones",
				"width" : 80,
				"formatter" : listUsuarioJGModule.columnButtons,
				"fixed":true,
				"hidden" : false,
				"sortable" :false,
				"search": false
				
			}
		];
	this.sortIndex = null;
	this.sortOrder = "asc"; // Valores posibles "asc" o "desc"
	this.pagerName = listUsuarioJGModule.idjqgridPagerHTML;
	this.pagerNameJQuery = listUsuarioJGModule.$jqgridPager;
	this.title = null;
	this.recordText=null;
	this.actualPage = null;
	this.emptyRecords = null;
    this.firstLoad=true;
    
    //Metodos
	this.getActualPage = function getActualPage(){ 
		if (this.actualPage == null)
			this.actualPage = 1;
		else
			this.actualPage = $(this.nameJQuery).getGridParam('page');
		
		return this.actualPage;
	}
		
	this.getRowNumPerPage = function getRowNumPerPage () {
		return $(this.nameJQuery).getGridParam('rowNum');
	} 

	this.getSortIndex = function getSortIndex () {
		if ($(this.nameJQuery).getGridParam('sortname')!=null){
			return $(this.nameJQuery).getGridParam('sortname');
		}else{
			//ordenacion de columnas por defecto
			return "";
		}
	}
	
	this.getSortOrder = function getSortOrder () {
		if ($(this.nameJQuery).getGridParam('sortorder')!=null){
			return $(this.nameJQuery).getGridParam('sortorder');
		}else{
			//ordenacion de columnas por defecto
			return "";
		}
	}
	
	this.getSelectedRows = function getSelectedRows(){ 
		return $(this.nameJQuery).getGridParam('selarrrow');
	}
	
	this.reloadGrid = function reloadGrid (){
		$(this.nameJQuery).trigger("reloadGrid");
	}	
		
};

var listUsuarioJGModule = (function(){
	
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */

	//Variables privadas
	var idHTML = "tabla";
	var idJquery = "#tabla";
	var idPagerHTML = "tablaPager";
	var idPagerJquery= "#tablaPager";
	
	var _grid=null;
	var _lastSelId;
	
	/**
	 * Carga de mensajes de internacionalizacion para el grid y el grid
	 */
	var createGrid = function (locale){	
		
		_grid = new GridClass(locale);
		_grid.colNames = ['Usuario', 'Contraseña', 'LFLAG', 'Descripción', 'Acciones' ];
		_grid.index = '';
		_grid.sortOrder = 'asc';
		_grid.title =  'Listado de usuarios';
		_grid.emptyRecords= 'No existen resultados';
		_grid.recordText='{2} registros encontrados';
		
		$(_grid.nameJQuery).jqGrid({
			
			styleUI : 'Bootstrap',
			loadui: 'disabled',
 			responsive: true,
 			multiselect: true,
 			height : "auto",// por defecto 150px
			autowidth : true,
			rownumbers : true,
 			datatype: "local",
			colNames:_grid.colNames,
		    colModel:_grid.cm,
			headertitles: true ,
			autoencode:true,//codifica html
			pager : _grid.pagerNameJQuery,
			rowNum : 10,
			rowList : [ 10, 20, 50 ],
			viewrecords : true,
			recordtext : _grid.recordText,
			caption : _grid.title,
			hidegrid : false, //false, para ocultar el boton que colapsa el grid.
			sortable : true,
			index: _grid.sortIndex,
			sortname: _grid.sortIndex,
			sortorder: _grid.sortOrder,
			emptyrecords : _grid.emptyRecords,
			loadComplete : function(data) {
				
				//Al cargarse la tabla por primera vez aparecen la cabecera y el pie de la tabla con diferente anchura que el cuerpo de la tabla.
				if (_grid.firstLoad){
					//Correccion de un pixel de anchura en la tabla cuando se carga por primera vez
					var anchura=$(".ui-jqgrid").width();
					$(".ui-jqgrid").width(anchura+1);
					var anchura2= $(".ui-jqgrid-titlebar.ui-jqgrid-caption").width();
					$(".ui-jqgrid-titlebar.ui-jqgrid-caption").width(anchura2+1);
				}
				
				//Elimino los mensajes que hubiese con la nueva recarga
				showMessageModule.removeAlert();
				
				//Elimino la seleccion de filas
				_resetSelectedRows();
				
				//cargamos el grid
				_loadData();
			},
			//Al hacer doble click pongo en modo edicion los campos de la tabla editables
			ondblClickRow: function(id){
				
				//Elimino los mensajes que hubiese con la nueva recarga
				showMessageModule.removeAlert();
				
				var rowData=$(idJquery).jqGrid ('getRowData', id);
				
				if(id && id!==_lastSelId){ 
					$(idJquery).restoreRow(_lastSelId); 
					_lastSelId=id; 
				}
				
				$(_grid.nameJQuery).jqGrid('editRow',id, 
					{ 
					    keys : true,
					    successfunc : function (){
							//Reestablezco el texto del loading que cambia a "Guardando..." cuando guarda un registro en la edicion inline y no vuelve a su estado original.
							$("#load_"+idHTML).text("Cargando...");
							
							showMessageModule.createAlert("success", "El proceso de guardado ha finalizado correctamente");
							
							return true;
					    },
					    errorfunc: function(rowID, response){
					    	$.jgrid.info_dialog("Error",'<div class="ui-state-error">Error al guardar el regitro con id:'+rowID+ ' </div>');
					    	return true;
					    },
					    
					    url : 'jqGridCrud/'+id,
					    mtype : "PUT",
					    autoencode:true
					});
				},
				ajaxRowOptions: { 
					contentType: "application/json",
		            dataType: "json"
				},
				ajaxGridOptions: {cache: false},
				serializeRowData: function(postdata){
	   	 			return JSON.stringify(postdata);
	 			},
	 			afterSubmitCell : function(serverresponse, rowid, cellname, value, iRow, iCol){
			    	$("#info_dialog").position({
				        of: window,
				        my: "center center",
				        at: "center center"
					});
	            	return [false, ''];
	            },
				onSelectRow: function (rowid,status,e){
					_setSelectedRowsNumber();
					
				},
				onSelectAll: function(aRowids, status) {
					_setSelectedRowsNumber();
				},
				
				//Antes de hacer click en una celda
				beforeSelectRow: function(rowid, e) {			
					//valido que si se pulsa en un boton de la fila (boton= BUTTON / icono= SPAN)
					if (e.target.tagName.toUpperCase() ==="BUTTON" || e.target.tagName.toUpperCase()==="SPAN") {
				        // Devuelvo false para que no se seleccione la fila
				        return false;
				    }
					
				    return true;
				}

		 });
		
		// activa la toolbar de busquedas por defecto las busquedas que sean: "que contengan"
		 $(_grid.nameJQuery).jqGrid('filterToolbar',{defaultSearch : "cn"});
	}
	
			
	/**
	 * Carga de datos en el grid
	 */
	var _loadData = function () {

		//Muestro el div de cargando...
		$("#load_"+idHTML).show();
		
		//Capturo los datos de busqueda introducidos pasarselos al controller en la llamada ajax
		var filtros= new UsuarioClass($("#gs_username").val(),null,$("#gs_flagldap").val(),$("#gs_descripcion").val());
		var objJson=filtros.prepareToJsonObject();
		
		var url='jqGridCrud/?page='+_grid.getActualPage()+'&max='+_grid.getRowNumPerPage()+'&index='+_grid.getSortIndex()+'&sortorder='+_grid.getSortOrder();
		
		//Llamada a la carga del grid mediante ajaxModule
		ajaxModule.getJSON(url, objJson, _loadDataSucces, mainUsuarioJGModule.errorProcess);
	}
	
	var _loadDataSucces=function(data){
		$(_grid.nameJQuery)[0].addJSONData(data);
		//Al cargar las filas, establece la celda rn (la numerica) a active. De esta forma lo corrijo
		$(".jqgrid-rownum").removeClass("active");
		
		//Oculto el div de Cargando...
		$(".loading").css("display", "none");
		
		_grid.firstLoad=false;
	}
	
	/**
	 * Funcion para crear los botones de la columna de acciones
	 */
	var columnButtons = function (cellValue, opts, rData) {
		var username=rData['username'];
		var celda = "<button class='btn btn-warning' style='margin-right:10px' onclick=\"listUsuarioJGModule.onClickEditRowBTN('"+username+"');\" type='button' title='Editar registro'><span class='pficon pficon-edit'></span></button>" +
					"<button class='btn btn-danger' onclick=\"listUsuarioJGModule.onClickDeleteRowBTN('"+username+"');\" type='button' title='Eliminar registro'><span class='pficon pficon-delete'></span></button>";
		
		return celda;
	}
	
	/**
	 * Funcion que se invoca al pulsar el boton borrar de la columna de acciones
	 */
	var onClickDeleteRowBTN = function (id){
		var arrayIds=new Array();
		var textConfirm="";
		
			arrayIds.push(id);
			textConfirm="Desea eliminar el registro: "+id;
			
			modalConfirmModule.showConfirmacion(textConfirm, arrayIds, formUsuarioJGModule.borrarDatos);
	};
	
	
	/**
	 * Funcion que se invoca al pulsar el boton editar de la columna de acciones
	 */
	var onClickEditRowBTN = function (id){
		//Llamada ajax para cargar el item
		formUsuarioJGModule.initializeWithData(id);
	};
	
	/**
	 * Resetea la seleccion de registros
	 */	
	var _resetSelectedRows = function (){
		$(_grid.nameJQuery).jqGrid('resetSelection');
		_setSelectedRowsNumber();
	}
	
	/**
	 * Renderiza el numero de registros seleccionados en elementos con la clase
	 * y deshabilita o habilita en funcion del numero de registros con la clase bulkAction  
	 */
	var _setSelectedRowsNumber = function (){
		var selectedRows=getSelectedDataIDs().length;
		
		if (selectedRows>1){
			$('.bulkAction').removeClass("disabled");
			$('.bulkAction').prop("disabled", "");
			//Añado el numero de filas seleccionadas en la pagina a los elementos 
			$(".selectedRowsNumber").text("("+selectedRows+")");
		}
		else if (selectedRows<=1){
			$('.bulkAction').addClass("disabled");
			$('.bulkAction').prop("disabled", "disabled");
			//Añado el numero de filas seleccionadas en la pagina a los elementos 
			$(".selectedRowsNumber").text("");
		}
	};
	
	/**
	 * Devuelve la lista de codigos de servicio de las filas seleccionadas
	 */
	var getSelectedDataIDs = function (){
		return _grid.getSelectedRows();
	}
	
	/**
	 * Dispara la recarga de datos en el grid
	 */
	var reloadGrid= function(){
		_grid.reloadGrid();
	}
	
	return{
		idjqgridHTML:idHTML,
		$jqgrid: idJquery,
		idjqgridPagerHTML:idPagerHTML,
		$jqgridPager:idPagerJquery,
				
		createGrid: createGrid,
		getSelectedDataIDs:getSelectedDataIDs,
		columnButtons:columnButtons,
		onClickEditRowBTN:onClickEditRowBTN,
		onClickDeleteRowBTN:onClickDeleteRowBTN,
		reloadGrid:reloadGrid
	}
})();