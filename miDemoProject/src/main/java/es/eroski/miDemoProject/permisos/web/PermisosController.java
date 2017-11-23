/**
 * 
 */
package es.eroski.miDemoProject.permisos.web;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.eroski.miDemoProject._model.Menu;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * Controlador para el modulo de Permisos
 * @author BICUGUAL
 *
 */
@Controller
@RequestMapping(value="/permisos")
public class PermisosController {

	private static Logger logger = Logger.getLogger(PermisosController.class);	
	
	@Autowired
	Messages messages;
	
	
	/**
	 * Carga la pagina principal del módulo
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Principal principal, Model model) throws Exception{
		logger.info(principal.getName());
		model.addAttribute("menu", new Menu(messages.get("menu.utilidades"),messages.get("menu.permisos")));
		return "permisos/mainPermisos";
	
	}
}
