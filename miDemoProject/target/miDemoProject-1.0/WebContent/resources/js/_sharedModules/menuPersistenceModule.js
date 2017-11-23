// Util: PatternFly Submenu persistente
// Añade o elimina la clase 'active' a los links del menu principal para que muestre u oculte el submenu
var menuPersistenceModule=(function(){
  
	
	'use strict';
	/**
	 *  Naming convenctions
	 * _privateVariable
	 * _privateFunction
	 * publicFunction
	 * $varName jQuery object
	 */
	
	//Variables privadas
	var _classNavPrimary=".navbar-primary";
	
	//Menu de utilidades (usuario)
	var entradasMenuUtility = {
		idJquery:  "ul.navbar-utility li",
		onClick: function(e){
			
			//Compruebo que la entrada no este desactivada
			if($(this).hasClass("disabled")){
				return false;
			}else{
				//Desactivo a todas las entradas que estan al mismo nivel
				$(this).siblings().removeClass('active').find('li').removeClass('active');
				
				//Si no es una lista desplegable en la que hacemos click
				if (!$(this).hasClass("dropdown") && !$(this).hasClass("dropdown-submenu") ){
					//Elimino todas las clases activas para las entradas de menu primario
					$(entradasMenuPrimario.idJquery).removeClass('active');  
					//elimino la clase persistent-secondary del menu primario para que no aparezca el menu secundario vacio.
					$(_classNavPrimary).removeClass("persistent-secondary");
				}
				
		  		$(this).addClass('active');
			}
			
		},
		init: function(){
			$(this.idJquery).unbind('click');
			$(this.idJquery).on('click', entradasMenuUtility.onClick);
		}
			
	};

  
  	//Menu primario
  	var entradasMenuPrimario = {
		idJquery:  "ul.navbar-primary >li:not(.context)",
		onClick: function(){
			
			//Compruebo que la entrada no este desactivada
			if($(this).hasClass("disabled")){
				 return false;
			}else{
				//Si no tiene menu secundario 
				if ($(this).first().children().length < 2){
					//elimino la clase persistent-secondary del menu primario para que no aparezca el menu secundario vacio.
					$(_classNavPrimary).removeClass("persistent-secondary");
					//elimino la clase activa para todos las entradas del menu secundario incluidas sub-entradas	
					$(entradasMenuSecundario.idJquery).removeClass("active");
					$(entradasMenuTerciario.idJquery).removeClass("active");
				}
				else{
					//Si tine menu secundario lo añado para asegurarme de que se visualiza
					$(this).siblings().find("li").removeClass("active");
					$(_classNavPrimary).addClass("persistent-secondary");
				}
			  	
				//Elimino todas las clases activas para las entradas de menu primario
				$(entradasMenuPrimario.idJquery).removeClass('active');
				//Elimino todas las clases activas para las entradas de menu utility
				$(entradasMenuUtility.idJquery).removeClass("active");
				//Añado la clase activo para esta entrada
				$(this).addClass('active');
			}
		},
		init: function(){
			$(this.idJquery).unbind('click');
			$(this.idJquery).on('click', entradasMenuPrimario.onClick);
		}
	};

  	//Menu secundario
  	var entradasMenuSecundario = {
		idJquery:  ".navbar-persistent >li",
		onClick: function(e){
			
			//Compruebo que la entrada no este desactivada
			if($(this).hasClass("disabled")){
				return false;
			}else{
				$(this).siblings().removeClass('active').find('li').removeClass('active');
				$(this).removeClass('active');
				$(this).addClass('active');
			}
		},
		init: function(){
			$(this.idJquery).unbind('click');
			$(this.idJquery).on('click', entradasMenuSecundario.onClick);
		}
	};
    //Menu terciario
  	var entradasMenuTerciario = {
  		idJquery:  ".navbar-persistent >li li",
  		onClick: function(e){
  			 
	  		if($(this).hasClass("disabled")){
	  			return false;
			}else{
				$(this).siblings().removeClass('active').find('li').removeClass('active');
				$(this).addClass('active');
			}
  		},
  		init: function(){
  			$(this.idJquery).unbind('click');
  			$(this.idJquery).on('click', entradasMenuTerciario.onClick);
  		}
  	};
  	
  	//Carga de pagina desde menu contextual
  	var entradasMenuContextual = {
  			idJquery: ".selectpicker",
			onChange: function (e){
				window.location = $(this).selectpicker('val');
			},
			init: function(){
				$(this.idJquery).unbind('changed.bs.select');
				$(this.idJquery).on('changed.bs.select', entradasMenuContextual.onChange);
			}
	};
  	
  	var initializeMenu = function (){
		$(document).ready(function() {
			
			entradasMenuUtility.init();
			entradasMenuContextual.init();
			entradasMenuPrimario.init();
			entradasMenuSecundario.init();
			entradasMenuTerciario.init();
			
			
		} );
	}
	
	return {
		initMenu: initializeMenu
	}
  

})();

menuPersistenceModule.initMenu();
