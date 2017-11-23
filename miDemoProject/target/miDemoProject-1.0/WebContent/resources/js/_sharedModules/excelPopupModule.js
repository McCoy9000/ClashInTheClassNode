var excelPopupModule= (function (){
	/*PRE: En el HTML/JSP se deben añadir los div con id  excellPopup y excellWindow en la pagina donde se realiza la exportacion*/
	'use strict';
	var idHTML = "excellPopup";
	var idJquery = "#excellPopup";
	
	var showPopup= function (form){
		$(idJquery).html(form);
//		$(idJquery).fadeIn('fast');
//		
//		closePopup();
	};
	
	var closePopup = function (){
		$(idJquery).fadeOut('fast');
	};
	
	return {
		showPopup : showPopup
	};	
})();