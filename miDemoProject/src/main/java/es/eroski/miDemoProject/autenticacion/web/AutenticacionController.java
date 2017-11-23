package es.eroski.miDemoProject.autenticacion.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controlador para gestionar el login/logout y acceso denegado a la aplicación.
 * Ademas, existe un handler al que se llama en las sessiones caducadas para redirigir al login. 
 * @author BICUGUAL
 */
@Controller
public class AutenticacionController {
	
	private static Logger logger = Logger.getLogger(AutenticacionController.class);
	
	//VIEW NAMES. Identificamos el nombre de las vistas a las que redirigir la navegacion
	private final String LOGIN_VIEW = "autenticacion/signin"; //Es una ruta a una vista
	
	/**
	 * Muestra la pagina de login
	 * @param model
	 * @return pagina de login
	 */
	@RequestMapping(value = {"/","/login"}, method=RequestMethod.GET)
	public String login(Model model) {
		logger.info("Muestro autenticacion");
		
		return LOGIN_VIEW;
	}
	
	/**
	 * En caso de login correcto comprueba LDAP si es necesario
	 * @param model
	 * @return pagina de login
	 */
	@RequestMapping(value = "/login/success", method=RequestMethod.GET)
	public ModelAndView loginSuccess (ModelMap model) {
		logger.info("Login correcto");
        return new ModelAndView("redirect:/home");
	}
	
	/**
	 * Muestra la pargina de acceso denegado cuando el usuario no tiene permisos para ejecutar una accion.
	 * @return pagina de acceso denegado
	 */
	@RequestMapping(value = "/denied", method=RequestMethod.GET)
	public ModelAndView denied(ModelMap model, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		
		/* Determinamos cual es la url anterior. 
		 * Si viene de login (/login, /login/success, /login/failure) quiere decir
		 * que el usuario se ha autenticado pero no tiene acceso a la pagina principal de la aplicacion.
		 * Direccionamos a un nuevo requestmapping 
		 */

		if ( referer != null && referer.contains("/login")){
			model.addAttribute("noautorizado",true);
			return  new ModelAndView("forward:/login");
		}else{
			logger.info("Acceso denegado para el usuario: "+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return new ModelAndView("autenticacion/accesoDenegado");
		}
	}
	
	
	/**
	 * En caso de login incorrecto reenvio a /login
	 * @param model
	 * @return pagina de login
	 */
	@RequestMapping(value = "/login/failure", method=RequestMethod.GET)
	public ModelAndView loginFailure(ModelMap model) {
		return new ModelAndView("forward:/login");
	}
	
	/**
	 * El cierre de session redirecciona a /login
	 * @param model
	 * @return pagina de login
	 */
	@RequestMapping(value = "/logout/success", method=RequestMethod.GET)
	public ModelAndView logoutSuccess (HttpSession sesion, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		logger.info("Session cerrada correctamente");
		//Invalido la session y creo otra antes de la redireccion para que no salte por el /expiredSession
		sesion.invalidate();
		request.getSession(true);
		
		redirectAttributes.addFlashAttribute("logout", true);
		
       return new ModelAndView("redirect:/login");
	}

	
	/**
	 * La session caducada redirecciona a /login
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/expiredSession", method=RequestMethod.GET)
	public ModelAndView expiredSession (
		Model model,RedirectAttributes redirectAttributes,HttpServletRequest request, HttpSession sesion) {
		logger.info("Session caducada");
		
		if (request.getHeader("Referer")!=null) 
			redirectAttributes.addFlashAttribute("expiredSession", true);
		return new ModelAndView("redirect:/login");
	}
	
	
}