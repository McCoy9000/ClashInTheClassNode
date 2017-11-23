var utilModule = (function(){
	'use strict';

	/**
     *  Naming convenctions
     * _privateVariable
     * _privateFunction
     * publicFunction
     * $varName jQuery object
     */

	/**
	 * Evalua una expresion como direccion de correo correcto. 
	 * @param valor. Expresion a validar
	 * @return Devuelve true para valores validos y false para los no validos.
	 * 
	 * Ejemplos de validaciones de email
		isValidEmail('david@davidburgosonline.com'); // true
		isValidEmail('david@ejemplo.com.es');        // true
		isValidEmail('@google.com');                 // false
		isValidEmail('hola@.com');                   // false
		isValidEmail('hola@google.c');               // false
		isValidEmail('hola@google');                 // false
		
	 * @author BICUGUAL
	 */
	var isValidEmail = function (valor){
		if (/^\w+([\.\+\-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(valor)){
			return true
		}
		return false
	}
	
	/**
	 * Valida una expresion como numerica de 9 digitos, sin espacios
	 * @param valor. Expresion a validar
	 * @return Devuelve true para valores validos y false para los no validos.
	 * 
	 * Ejemplos de validaciones de telefono
		isValidPhone('944965801'); 		// true
		isValidPhone('661257261');  	// true
		isValidPhone('94 4965801');		// false
		isValidPhone('661 257 261');	// false
		isValidPhone('+34944965801');	// false
		
	 * @author BICUGUAL
	 */
	var isValidPhone = function (valor){
		if(/^\d{9}$/.test(valor) ) 
			return true;
		
		return false;
	}
	
	/**
	 * Comprueba si una url es valida
	 * @param valor. Expresion a validar
	 * @return Devuelve true para valores validos y false para los no validos.
	 * 
	 * @author BICUGUAL
	 */
	var isUrlValid= function (valor) {
	    //Declaramos la expresión regular que se usará para validar la url pasada por parámetro
	    var regexp = /^(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/
	    //Retorna true en caso de que la url sea valida o false en caso contrario
	    return regexp.test(valor);
	 }
	
	/**
	 * Comprueba si es un DNI correcto (entre 5 y 8 letras seguidas de la letra que corresponda).
	 * Acepta NIEs (Extranjeros con X, Y o Z al principio).
	 * @param valor. Expresion a validar
	 * @return Devuelve true para valores validos y false para los no validos.
	 * 
	 * @author BICUGUAL
	 */
	var isValidDNI= function (valor) {
	    var numero, letraDni, letras;
	    var expresion_regular_valor = /^[XYZ]?\d{5,8}[A-Z]$/;

	    var valor = valor.toUpperCase();

	    if(expresion_regular_valor.test(valor) === true){
	        numero = valor.substr(0,valor.length-1);
	        numero = numero.replace('X', 0);
	        numero = numero.replace('Y', 1);
	        numero = numero.replace('Z', 2);
	        letraDni = valor.substr(valor.length-1, 1);
	        numero = numero % 23;
	        letras = 'TRWAGMYFPDXBNJZSQVHLCKET';
	        letras = letras.substring(numero, numero+1);
	        if (letras != letraDni) {
	            //alert('Dni erroneo, la letra del NIF no se corresponde');
	            return false;
	        }else{
	            //alert('Dni correcto');
	            return true;
	        }
	    }else{
	        //alert('Dni erroneo, formato no válido');
	        return false;
	    }
	}

	/**
	 * Comprueba si un CIF es valido.
	 * @param cif. El CIF a validar
	 * @return Devuelve true para valores validos y false para los no validos.
	 * 
	 * @author BICUGUAL
	 */
	var isValidCIF= function (cif) {
		if (cif.length<=8)
			return false;
		
		var cadenacif = new Array("A","B","C","D","E","F","G","H","I","J");
		var arrcif = new Array(9);
		var aux1;
		var aux2;
		var i;
		var digito;
		for (i=0;i<=8;i++){ 
			arrcif[i] = (cif.substr(i,1) - 1) + 1;
		}
		aux1 = arrcif[2] + arrcif[4] + arrcif[6]
		aux2 = _sumaImpares(arrcif[1]) + _sumaImpares(arrcif[3]) + _sumaImpares(arrcif[5]) + _sumaImpares(arrcif[7])
		aux1 = aux1 + aux2
		digito = 10 - (aux1%10)
		if (cadenacif[digito-1] == cif.substr(8,1))
		{
			return (true)
		}else{
			if (!isNaN(cif.substr(8,1))){
				if (digito == 10) digito=0;
				return (digito==arrcif[8]);
			}else{
				return (false);
			}
		}	
	}
	
	var _sumaImpares = function (ndigito){ //Necesaria para isValidCIF
		   var nsuma = ndigito * 2;
		   if (nsuma >= 10) nsuma = (nsuma%10) + 1;
		   return nsuma;
	}
	
	/**
	 * Comprueba si una expresion tiene un patron numerico correcto. 
	 * Son expresiones correctas numeros enteros y decimales, tambien negativos.
	 * @param valor. Expresion numerica a validar
	 * @param sep. Separador decimal
	 * @return Devuelve true para valores numericos validos y false para los no validos.
	 * 
	 * Ejemplos de validaciones
		utilModule.isValidNumberFormat(0 , ","); 		// true
		utilModule.isValidNumberFormat(140 , ","); 		// true
		utilModule.isValidNumberFormat(0,001 , ",");	// true
		utilModule.isValidNumberFormat(12,9 , ","); 	// true
		utilModule.isValidNumberFormat(-1 , ",");		// true
		utilModule.isValidNumberFormat(-1,02 , ",");	// true
		utilModule.isValidNumberFormat(0,001 , "."); 	// false
		utilModule.isValidNumberFormat(letras , ","); 	// false
	 *
	 * 
	 * @author BICUGUAL
	 */
	var isValidNumberFormat = function (valor, sep){
//		var regexp = /^[0-9]\d*(\,\d+)?$/;
		var regexp2 = /^-?(0\.\d*[1-9]\d*|[1-9]\d*(\,\d+)?)$/;
		var regexp= new RegExp("^-?(0\\"+sep+"\\d*[1-9]\\d*|[0-9]\\d*(\\"+sep+"\\d+)?)$");
		
		return regexp.test(valor);
	}
	
	
	return {
		isValidEmail: isValidEmail,
		isValidPhone : isValidPhone,
		isUrlValid : isUrlValid,
		isValidDNI : isValidDNI,
		isValidCIF : isValidCIF,
		isValidNumberFormat: isValidNumberFormat
	}
	
})();

