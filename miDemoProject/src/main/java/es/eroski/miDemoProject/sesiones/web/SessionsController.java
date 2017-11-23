package es.eroski.miDemoProject.sesiones.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import es.eroski.miDemoProject._exceptions.GenericJsonException;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;
import es.eroski.miDemoProject._model.Menu;
import es.eroski.miDemoProject.sesiones.service.SessionIBS;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * Controlador para la gestion de sesiones
 * @author BICUGUAL
 *
 */
@Controller
@RequestMapping(value="/sesiones")
public class SessionsController {
private static Logger logger = Logger.getLogger(SessionsController.class);	
	
	@Autowired
	SessionIBS sessionsService;
	
	@Autowired
	Messages messages;
	
	/**
	 * Muestra la pagina de sesiones
	 * @param principal
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Principal principal, Model model) throws Exception{
		model.addAttribute("menu", new Menu(messages.get("menu.utilidades"), messages.get("menu.sesiones")));
		return "sesiones/mainSesiones";
	
	}
	
	/**
	 * Obtiene la lista de sesiones para ser mostradas con el componente datatable
	 * @param request
	 * @param response
	 * @param draw
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public  @ResponseBody DataTableResponse<SessionInformation>  getPagedItems(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(required = false, defaultValue="0") Long draw, 
			@RequestParam(required = false, defaultValue="1") Long start,
			@RequestParam(required = false, defaultValue="0") Long length) {
		
		DataTableRequest datatableFilter= new DataTableRequest(draw,length,start, request);	
		DataTableResponse<SessionInformation> result;
		
		try{

			result=sessionsService.getDTResponseSessions(datatableFilter);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("error.operacion"), e);
			
		}
		return result;
	}
	
	/**
	 * Expira la session
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/expirar/{id}",method = RequestMethod.PUT)
	public  @ResponseBody String updateItem(
			@PathVariable("id") Object id){
			logger.info("Solicitud expiracion sesion id : " + (String)id);
			String usersession = RequestContextHolder.currentRequestAttributes().getSessionId();
			if   ( ( id.equals(usersession)) ) {
				throw new GenericJsonException(messages.get("sesion.error.activa"), new Exception(""));
			}else{
				if ( (sessionsService.expirarSession((String)id) == 1 ) ){
					return messages.get("sesion.mensaje.expirar")+ ": " + (String)id;
				}else{
					throw new GenericJsonException(messages.get("sesion.error.expirar")+": "+id, new Exception(""));
				}
			}
	}	
	
	
}
