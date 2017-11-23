var ajaxModule = (function(){
	'use strict';
	
	var runAjax = function (pURL, pData, pMethod, pDatatype, pDoneCallback, pFailCallback){
		//Se usa la llamada más básica de JQuery para no depender del API que puede cambiar en las versiones.
		var host=window.location.host;
		var contexto= window.location.pathname.substring(0, window.location.pathname.indexOf("/",2))
		
		return $.ajax({
    		url: pURL,
    		cache : false,
    		data: pData,// "JSON.parse('{"key": "value", "key": "value",...}"');
    		contentType : "application/"+pDatatype,
    		dateType: pDatatype,  // "json", "xml", "script"
    		method: pMethod,      // "get", "post", "put"
    		beforeSend: function(jqXHR, settings){
    			/* A pre-request callback function that can be used to modify the jqXHR object before it is sent. 
    			 * Use this to set custom headers, etc. The jqXHR and settings objects are passed as arguments. 
    			 * Returning false in the beforeSend function will cancel the request.*/ 
    			$(".spinner").removeClass("hide");
    			return true;    			
    		},
    		success: function(data, textStatus, jqXHR) {
    			/* Definición de la función para el evento "success". Se ejecuta cuando la Request ha ido bien.
    					data: data returned from the server, 
    					textStatus: a string describing the status, 
    					jqXHR object
    			*/
    				
    			if( jqXHR.getResponseHeader('content-type').indexOf('text/html') >= 0 )		
    				window.location.href = "http://"+host+contexto+"/expiredSession";
    			else{
//    				console.log("Ajax call OK");
    				pDoneCallback(data, jqXHR);
    			}
    		},
    		error: function(jqXHR, textStatus, errorThrown) {
    			/* Definición de la función para el evento "error". Se ejecuta cuando la Request NO ha ido bien.
    					jqXHR object, 
    					textStatus: a string describing the type of error that occurred. Possible values: "timeout", "error", "abort", and "parsererror",
    					errorThrown: When an HTTP error occurs, errorThrown receives the textual portion of the HTTP status, such as "Not Found" or "Internal Server Error."
    			*/
    			if (errorThrown == "Prohibido")
    				window.location.href = "http://"+host+contexto+"/denied";
    			else{
//    				console.log("Ajax call ERROR!!");
    				pFailCallback(jqXHR);
    			}
    		},
    		complete: function(jqXHR, textStatus){
    			/* Definición de la función para el evento "complete". Se ejecuta siempre cuando finaliza la Request después de ejecutar success o error callback.
						jqXHR object, 
						textStatus: a string categorizing the status of the request ("success", "notmodified", "nocontent", "error", "timeout", "abort", or "parsererror") 
    			 */
    			$(".spinner").addClass("hide");
    		}
			
		}); //ajax call		
	};
	
	var getJSON = function (pURL, pData, pDoneCallback, pFailCallback){
		return runAjax(pURL, pData, 'get', 'json', pDoneCallback, pFailCallback); 
	};

	var postJSON = function (pURL, pData, pDoneCallback, pFailCallback){
		return runAjax(pURL, pData, 'post', 'json', pDoneCallback, pFailCallback); 
	};

	var putJSON = function (pURL, pData, pDoneCallback, pFailCallback){
		return runAjax(pURL, pData, 'put', 'json', pDoneCallback, pFailCallback); 
	};
	
	var deleteJSON = function (pURL, pData, pDoneCallback, pFailCallback){
		return runAjax(pURL, pData, 'delete', 'json', pDoneCallback, pFailCallback); 
	};
	
	var getHTML = function (pURL, pDoneCallback, pFailCallback){
		return runAjax(pURL, "", 'get', 'html', pDoneCallback, pFailCallback); 
	};
	
	return {
		getJSON: getJSON,
		postJSON: postJSON,
		putJSON: putJSON,
		deleteJSON: deleteJSON,
		getHTML: getHTML
	}
})();