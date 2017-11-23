package es.eroski.miDemoProject.home.web;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador para el Home o pagina principal
 * @author BICUGUAL
 */
@Controller
public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class);

	@Resource 
	private MessageSource messageSource;
	
	//VIEW NAMES. Identificamos el nombre de las vistas a las que redirigir la navegación
	private static final String HOME_VIEW = "home/homeApplication";

	/**
	 * Entrada a pagina principal
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Principal principal) {
		logger.info("Entra en pantalla home");
		
		return HOME_VIEW;
	}

}