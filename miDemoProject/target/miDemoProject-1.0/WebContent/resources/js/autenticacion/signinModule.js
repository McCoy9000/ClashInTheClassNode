var signinModule = (function(){
	return {
		init: function(){
			$(document).ready(function() {
				//Muestro el modal al enviar el formulario
				$("#loginForm").on("submit", function(){
					$("#modalCargando").modal('show');
				});
			});			
		}
	}
})();

signinModule.init();
