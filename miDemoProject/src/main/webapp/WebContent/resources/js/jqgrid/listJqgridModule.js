'use strict';

/*Clase para el objeto grid*/
var Grid = function (locale){
	// Atributos		
	this.name = "tabla";
	this.nameJQuery = "#tabla";
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
				"width" : 100
			}
			,{
				"name" : "flagldap",
				"index": "FLDAP",
				"width" : 50,
				"align" : "center"
			}
			,{
				"name" : "descripcion",
				"index": "DESCRIPTION",
				"width" : 250
			}
		];
	this.sortIndex = null;
	this.sortOrder = "asc"; // Valores posibles "asc" o "desc"
	this.pagerName = "tablaPager";
	this.pagerNameJQuery = "#tablaPager";
	this.title = null;
	this.recordText=null;
	this.actualPage = null;
	this.localdata = null;
	this.emptyRecords = null;
    this.firstLoad = true;
    
    //Metodos
	this.getActualPage = function getActualPage(){ 
		if (this.actualPage == null)
			this.actualPage = 1;
		else
			this.actualPage = $(this.nameJQuery).getGridParam('page');
		
		return this.actualPage;
	}
	
	this.getSelectedRow = function getSelectedRow(){ 
		return $(this.nameJQuery).getGridParam('selrow'); 
	}
	
	this.getCellValue = function getCellValue(rowId, colName){ 
		return $(this.nameJQuery).getRowData(rowId)[colName]; 
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
		
};

var listJqgridModule = (function(){
	
	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */
	
	//Variables privadas
	var _idHTML = "tabla";
	var _idJquery = "#tabla";
	var _idPagerHTML = "tablaPager";
	var _idPagerJquery= "#tablaPager";
	
	var _grid=null;
	
	/**
	 * Carga de mensajes de internacionalizacion para el grid y el grid
	 */
	var createGrid = function (locale){	
		
		_grid = new Grid(locale);
		_grid.colNames = ['Usuario', 'Contraseña', 'LFLAG', 'Descripción' ];
		_grid.index = '';
		_grid.sortOrder = 'asc';
		_grid.title =  'Listado de usuarios';
		_grid.emptyRecords= 'No existen resultados';
		_grid.recordText='{2} registros encontrados';
		
		$(_idJquery).jqGrid({
			
			styleUI : 'Bootstrap',
			loadui: 'disabled',
 			responsive: true,
 			multiselect: false,
 			height : "auto",// por defecto 150px
			autowidth : true,
//			width : "auto",
			//shrinkToFit:false,
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
				//Si carga por primera vez no llamo a la carga de datos	
				if (!_grid.firstLoad)
					reloadData();
				else{
					//Correccion de un pixel de anchura en la tabla cuando se carga por primera vez
					var anchura=$("#gbox_"+_grid.name).width();
					$("#gbox_"+_grid.name).width(anchura+1);
					var anchura2= $(".ui-jqgrid-titlebar.ui-jqgrid-caption").width();
					$(".ui-jqgrid-titlebar.ui-jqgrid-caption").width(anchura2+1)
				}
			}	

		 });
		 		 		
	}
	
			
	/**
	 * Carga de datos en el grid
	 */
	var reloadData = function () {
		$("#load_"+_idHTML).show();
		
		//Capturo los datos de busqueda introducidos pasarselos al controller en la llamada ajax
		var objJson = mainJqgridModule.getFiltrosJson();
		
		var url='./jqgridLoadData?page='+_grid.getActualPage()+'&max='+_grid.getRowNumPerPage()+'&index='+_grid.getSortIndex()+'&sortorder='+_grid.getSortOrder();
		
		ajaxModule.getJSON(url, objJson, _loadDataSucces, mainJqgridModule.errorProcess);
	    _grid.firstLoad=false;
	}
	
	
	var _loadDataSucces=function(data){
		$(_idJquery)[0].addJSONData(data);
		//Al cargar las filas, establece la celda rn (la numerica) a active. De esta forma lo corrijo
		$(".jqgrid-rownum").removeClass("active");
		$(".loading").css("display", "none");
	}
	
	/**
	 * Funcion para limpiar / resetear el grid
	 */
	var clearGridData= function(){
		//Correccion de anchura
		var anchura=$("#gbox_"+_grid.name).width();
		$("#gbox_"+_grid.name).width(anchura-1);
		var anchura2= $(".ui-jqgrid-titlebar.ui-jqgrid-caption").width();
		$(".ui-jqgrid-titlebar.ui-jqgrid-caption").width(anchura2-1)
		
		//Al resetear el grid establezco firstLoad=true para que dentro del complete no ejecute la busqueda
		_grid.firstLoad=true;
		
		//Elimino los iconos de ordenacion
		$("span.s-ico",$(_idJquery)[0].grid.hDiv).hide(); // hide sort icons
		$(_idJquery).setGridParam({sortname: ''}).trigger('reloadGrid');

		//Elimino los datos del grid
		$(_idJquery).clearGridData();
		createGrid();
		
	}

	
	return{
		createGrid: createGrid,
		clearGridData:clearGridData,
		reloadData:reloadData
	}
})();


