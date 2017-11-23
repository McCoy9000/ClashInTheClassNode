var mainAutocompleteModule = (function() {
	'use strict';

	/**
	 * Naming convenctions _privateVariable _privateFunction publicFunction
	 * $varName jQuery object
	 */

	/**
	 * Autocomplete para lenguajes
	 */
	var _lenguajes = {
		idHTML : "lenguaje",
		idJquery : "#lenguaje",
		url : "./lenguaje",
		response : null,
		request : null,
		populate : function(data) {
			// si no hay resultados en la busqueda
			if (!data.length) {
				var result = [ {
					label : _lenguajes.request.term,
					value : ''
				} ];
				_lenguajes.response(result);
			}
			// si hay resultados en la busqueda
			else {
				// devuelvo los datos obtenidos de la busqueda para mostrarlos
				// por pantalla
				_lenguajes.response($.map(data, function(v, i) {
					return {
						label : v.descripcion,
						// value:v.descripcion
						id : v.codigo
					};
				}));
			}
		},
		init : function() {
			$(this.idJquery).autocomplete(
					{
						source : function(request, response) {
							// Guardo el texto introducido en un objeto porque
							// el controlador espera un objeto llamado term
							var term = {
								term : request.term
							};
							// Guardo los req y res en el objeto para acceder a
							// ellos desde otra funcion
							_lenguajes.request = request;
							_lenguajes.response = response;

							if (request.term.length >= 1) {
								ajaxModule.getJSON(_lenguajes.url, term,
										_lenguajes.populate, errorProcess);
							}
						},
						select : function(event, ui) {// cuando selecciono un
														// elemento de la busqueda
							event.preventDefault();
							// asigno el valor de label a la caja de texto
							$(_lenguajes.idJquery).val(ui.item.label);

							if (typeof ui.item.id !== "undefined") {
								$(_lenguajes.idJquery).data("codlenguaje",
										ui.item.id);
							}

						},
						// Al cambiar el foco si no existe ningun valor reseteo
						// el lenguaje y su codigo
						change : function(event, ui) {
							// Si no hay coincidencias reseteo el grupo y el
							// cliente
							if (!ui.item) {
								$(_lenguajes.idJquery).val("");
								$(_lenguajes.idJquery).data("codlenguaje", "");
							}
						}

					});
		}

	}

	/**
	 * Funcion de error comun a las llamadas ajax
	 */
	var errorProcess = function(data) {
		var respBody = data.responseJSON;
		showMessageModule.createAlert("danger", respBody.messageForUser + "<p>"
				+ respBody.nameClass + "</p>");
	};

	var aceptar = {
		idHTML : "aceptarBtn",
		idJquery : "#aceptarBtn",
		onClick : function() {
			var lenguaje=$("#lenguaje").val();
			var id=$("#lenguaje").data("codlenguaje");
			if (lenguaje!=''){
				var seleccion= id+ ". " + lenguaje;
				alert(seleccion);
			}
			else{
				alert("Sin seleccion");
			}
		},

		init : function() {
			$(this.idJquery).unbind('click');
			$(this.idJquery).on('click', aceptar.onClick);
		}
	}

	var initializePage = function() {
		$(document).ready(function() {
			// inicializacion
			_lenguajes.init();

			// Botones
			aceptar.init();
		});
	}

	return {
		initPage : initializePage
	}
})();

mainAutocompleteModule.initPage();
showMessageModule.init();