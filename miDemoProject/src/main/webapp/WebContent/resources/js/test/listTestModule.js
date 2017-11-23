var listTestModule = (function(){
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
	var _idHTML = "listaTest";
	var _idJquery = "#listaTest";
	var $jq = null;
	var _classInputSearchJquery = ".search-input-text"
	var listTest = null;
	
	var testPassed = function(data, jqXHR){
		$jq = $(_idJquery);
		console.log("Eureka!!")
		//alert("EUREKA!!!");		
		$jq.html("<p>Eureka!!!</p>");
		console.log(data);
		function myFunction(item, index){
			$jq.append('<div class="list-group-item"><div class="list-group-item-header"><div class="list-view-pf-expand"><span class="fa fa-angle-right"></span></div><div class="list-view-pf-checkbox"><input type="checkbox"></div><div class="list-view-pf-actions"><button class="btn btn-default"><a href="paneldecontrol.html">Ver</a></button><div class="dropdown pull-right dropdown-kebab-pf"><button class="btn btn-link dropdown-toggle" type="button" id="dropdownKebabRight9" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="fa fa-ellipsis-v"></span></button><ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownKebabRight9"><li><a href="#">Editar</a></li><li><a href="#">Eliminar</a></li></ul></div></div><div class="list-view-pf-main-info"><div class="list-view-pf-left"><span class="fa pficon-project list-view-pf-icon-sm"></span></div><div class="list-view-pf-body"><div class="list-view-pf-description"><div class="list-group-item-heading">' + data[index].nombre + '</div><div class="list-group-item-text">' + data[index].descripcion + '</div></div><div class="list-view-pf-additional-info"><div class="list-view-pf-additional-info-item"><span class="pficon pficon-project"></span><strong>12</strong> Jugadores</div></div></div></div></div></div>');
			
		};
		data.forEach(myFunction);
	};

	var loadData = function (){	
		
		listTest = ajaxModule.getJSON("./test/", "JSON.parse()", testPassed, null);
		
	};
	
	var getList = function (listTest){
		console.log("pasa por getList");
		var json = JSON.parse(listTest);
		$jq.append("<p>Eureka!!!</p>");
		
		
	}
	
	return{
		loadData: loadData,
		getList: getList
	}

})();


