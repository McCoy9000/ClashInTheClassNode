
var mainModalModule = (function(){
	'use strict';
	
	var alertConfirm=function(id){
		showMessageModule.createAlert("success", i18nModule._("success.delete.user")+id);
	};
	
	var eliminarBTN = {
			idHTML: "demoDeleteUsuarioBtn",
			idJquery: "#demoDeleteUsuarioBtn",
			onClick: function(){
				var textConfirm = i18nModule._("confirm.delete.user");
				var id=$("#demoUsernameTxt").val();
				textConfirm += id;
				modalConfirmModule.showConfirmacion(textConfirm, id, alertConfirm);
			},			
			init: function(){
				$(this.idJquery).unbind('click');
				$(this.idJquery).on('click', eliminarBTN.onClick);
			}
	}
	
	var initializePage = function (){
		$(document).ready(function() {		
			//Eventos
			eliminarBTN.init();
			
		} );
	}
	
	return {
		initPage: initializePage,
		alertConfirm: alertConfirm,
	}
})();


mainModalModule.initPage();
showMessageModule.init();
