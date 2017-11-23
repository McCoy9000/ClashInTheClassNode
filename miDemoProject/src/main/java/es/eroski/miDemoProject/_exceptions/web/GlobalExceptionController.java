/**
 * 
 */
package es.eroski.miDemoProject._exceptions.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import es.eroski.miDemoProject._exceptions.GenericException;
import es.eroski.miDemoProject._exceptions.GenericJsonException;
import es.eroski.miDemoProject._model.ErrorInfo;

/**
 * Clase para el manejo global de excepciones. Por el momento no vamos a utilizarla
 * @author BICUGUAL
 */

@ControllerAdvice
public class GlobalExceptionController {

	private static Logger logger = Logger.getLogger(GlobalExceptionController.class);
	
	public static final String DEFAULT_ERROR_VIEW = "error/errorPage";
	
	/**
	 * Gestion para errores generales que devuelven una vista
	 * @param req
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {GenericException.class })
	public ModelAndView handleDefaultException(HttpServletRequest req, GenericException ex) {
        logger.info("Se ha lanzado una excepcion: " + ex.getMessageForUser());
        ErrorInfo error= new ErrorInfo(ex,req.getRequestURL().toString());
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW, "errorInfo",error);
        
	    return mav;
	  }
	
	/**
	 * Gestion para errores que devuele un Json con la informacion del error
	 * @param req
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {GenericJsonException.class })
	public @ResponseBody ErrorInfo handleDefaultJsonException(HttpServletRequest req, GenericJsonException ex){
        logger.info("Se ha lanzado una excepcion Json: " + ex.getMessageForUser());
        ex.getMessage();
        ErrorInfo error= new ErrorInfo(ex,req.getRequestURL().toString());
 	    
        return error;
	  }
	
}
