var modalConfirmModule = (function(){
	/*PRE: En el HTML/JSP se debe importar el archivo p05_modalConfirm.jsp */
	'use strict';
	var _dataToConfirm = null;
	var _fnConfirmacion = null;
	
	var showConfirmacion = function(textConfirm, dataToConfirm, fnConfirmacion){
		$("#modalConfirm").find('.modal-body').html("<h4>¿ "+ textConfirm +" ?</h4>");
		_dataToConfirm = dataToConfirm;
		_fnConfirmacion = fnConfirmacion;
		$("#modalConfirm").modal("show");
		confirmarBTN.activateEvents();
	};

	var confirmarBTN = {
		idHTML: "confirmBtn",
		idJquery: "#confirmBtn",
		onClick: function(){
			$("#modalConfirm").modal('hide');
			_fnConfirmacion(_dataToConfirm);
		},
		activateEvents: function(){
			$(this.idJquery).unbind('click');
			$(this.idJquery).on('click', confirmarBTN.onClick);
		}
	};

	return {
		showConfirmacion: showConfirmacion		
	};
})();