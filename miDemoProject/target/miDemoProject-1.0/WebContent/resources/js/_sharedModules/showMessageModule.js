var showMessageModule = (function(){
	'use strict';	
	var initModule = function (){
		$(document).ready(function() {
			//Observo el click en el boton X de las alertas para ocultarlas
			$("#alertCloseBtn").on("click", function (){
				$("#alert").hide();
			});
		});
	}	
	
	/* Muestra mensaje de alerta
	 * @param type Tipo de alerta: danger , warning, success, info
	 * @param htmlText Texto html que se desea mostrar en la alerta
	 */
	var createAlert = function (type,htmlText){
		
		//reinicio la alerta
		removeAlert();

		//En funcion del tipo establecemos color de fondo para la alerta e icono
		switch (type) {
		case "success":
			$("#alert").addClass("alert-success");
			$("#alertIcon").addClass("pficon-ok");
			break
		case "info":
			$("#alert").addClass("alert-info");
			$("#alertIcon").addClass("pficon-info");
			break
		case "warning":
			$("#alert").addClass("alert-warning");
			$("#alertIcon").addClass("pficon-warning-triangle-o");
			break
		default:
			$("#alert").addClass("alert-danger");
			$("#alertIcon").addClass("pficon-error-circle-o");
		}
		
		$("#alertText").append(htmlText);
		$("#alert").show();

		//Posiciona el scroll de la ventana arriba del todo 
		$(document).scrollTop(0);
		
	}

	/* Resetea las alertas
	 */
	var removeAlert = function(){
		//Borro clase separa el fondo de alerta alert-danger alert-warning alert-success alert-info
		$("#alert").removeClass("alert-danger alert-warning alert-success alert-info");
		//Elimino el icono Icono de alerta pficon-ok pficon-info pficon-warning-triangle-o pficon-error-circle-o
		$("#alertIcon").removeClass("pficon-ok pficon-info pficon-warning-triangle-o pficon-error-circle-o");
		//Sustituyo a "" el texto de alerta
		$("#alertText").text("");
		//Oculto el div
		$("#alert").hide();
	}

	return {
		init: initModule,
		createAlert: createAlert,
		removeAlert: removeAlert
	}
	
})();