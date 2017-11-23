package es.eroski.miDemoProject.utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FechaUtils {

	
	private static final String formatoFecha_ES = "dd/MM/yyyy";
	private static final String formatoFechaNumerico = "ddMMyyyy";
	
    /**
    * Convierte una fecha en formato DDMMYYYY a otra dependiente del idioma.
    *
    */
    public static String formatearFecha(String fechaDDMMYYYY, Locale locale) {

    	String fechaPantalla = "";
    	if (fechaDDMMYYYY != null){
    		Date fecha = convertirStringAFecha(fechaDDMMYYYY);
			final SimpleDateFormat sdfFormateador = new SimpleDateFormat();
	        sdfFormateador.applyPattern(obtenerFormatoFecha(locale));
			fechaPantalla = sdfFormateador.format(fecha);
		}

        return fechaPantalla;
    }
    
    /**
     * Convierte una fecha a formato DDMMYYYY.
     *
     */
     public static String formatearFecha(Date fecha) {

		String fechaPantalla = "";
		final SimpleDateFormat sdfFormateador = new SimpleDateFormat();
		sdfFormateador.applyPattern(formatoFechaNumerico);
		fechaPantalla = sdfFormateador.format(fecha);
		return fechaPantalla;
     }


     /**
      * Convierte una fecha a formato DDMMYYYY.
      *
      */
      public static String formatearFecha_ES(Date fecha) {

 		String fechaPantalla = "";
 		final SimpleDateFormat sdfFormateador = new SimpleDateFormat();
 		sdfFormateador.applyPattern(formatoFecha_ES);
 		fechaPantalla = sdfFormateador.format(fecha);
 		return fechaPantalla;
      }

    
     
    /**
    * Convierte una fecha en formato DDMMYYYY a Date.
    *
    */
	public static Date convertirStringAFecha (String fechaDDMMYYYY){
		//Formatear fecha de pantalla que vendría en formato DDMMYYYY
		int dia = Integer.valueOf(fechaDDMMYYYY.substring(0, 2));
		int mes =Integer.valueOf(fechaDDMMYYYY.substring(2, 4)) - 1;
		int anyo =Integer.valueOf(fechaDDMMYYYY.substring(4));
		GregorianCalendar diaTransformado = new GregorianCalendar();
		diaTransformado.set(anyo, mes, dia);
		diaTransformado.set(Calendar.HOUR_OF_DAY, 0);
		diaTransformado.set(Calendar.MINUTE, 0);
		diaTransformado.set(Calendar.SECOND, 0);
		diaTransformado.set(Calendar.MILLISECOND, 0);
		
		return diaTransformado.getTime();
	}
    
	/**
	* Devuelve un String con el formato de la fecha en funci�n del idioma.
	* 
	*/
	public static String obtenerFormatoFecha(Locale locale) {
		//Dependiendo del locale debería devolver un formato diferente
        return formatoFecha_ES;
	}
	
}
