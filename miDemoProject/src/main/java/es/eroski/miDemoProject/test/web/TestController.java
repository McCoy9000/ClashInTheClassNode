/**
 * 
 */
package es.eroski.miDemoProject.test.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.eroski.miDemoProject._exceptions.GenericJsonException;
import es.eroski.miDemoProject._model.Carta;
import es.eroski.miDemoProject._model.Menu;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.test.service.TestIBS;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * Controlador para el modulo de Usuarios
 * @author BICUGUAL
 *
 */
@Controller
@RequestMapping(value="/test")
public class TestController {

	private static Logger logger = Logger.getLogger(TestController.class);	
	
	@Autowired
	TestIBS testService;
	
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
		Menu menu=new Menu(messages.get("menu.demos"), messages.get("menu.datatable"), messages.get("menu.datatable.crud"));
		model.addAttribute("menu", menu);
		
		return "test/mainTest";
	
	}
	
	/**
	 * Carga del formulario 
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/formulario", method = RequestMethod.GET)
	public String formulario(Principal principal) throws Exception{
		return "test/formTest";
	}
	
	
	/**
	 * Carga de datos en el grid
	 * @param request
	 * @param response
	 * @param draw
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public  @ResponseBody List<Carta>  getItemList(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Carta> result;	
		
		
		try{

			result=testService.getUserList();
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("error.operacion"), e);
			
		}
		return result;
	}
	
	
	/**
	 * Devuelve el registro de base de datos que coincida con el id pasado como parametro
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public  @ResponseBody Usuario getItem(
			@PathVariable("id") Object id){
		
		Usuario item=null;
		try{
			item=testService.getItem(id);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException("Error al cargar el usuario con id "+id, e);
			
		}
		return item;
	}
	
	/**
	 * Crear un nuevo registro en base de datos
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public  @ResponseBody String createItem(
			@RequestBody Usuario item){
		
		try{
			testService.createItem(item); 
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException("Error al guardar el registro", e);
			
		}
		return "El proceso de guardado ha finalizado correctamente";
	}

	/**
	 * Modificar un registro de base de datos
	 * @param id
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public  @ResponseBody String updateItem(
			@PathVariable("id") Object id,
			@RequestBody Usuario item){
		
		try{
			//Compruebo si el item existe en base de datos
			if (testService.getItem(id)!=null){
				testService.updateItem(item);
			}else{
				throw new GenericJsonException( new Throwable("No existe en base de datos el registro con id "+id));
			} 
				
			
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException("Error al cargar el usuario con id "+id, e);
			
		}
		return "El proceso de guardado ha finalizado correctamente";
	}
	
	/**
	 * Elimina los registros cuyos ids han sido pasados como parametros
	 * @param lstIds
	 * @return
	 */
	@RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
	public  @ResponseBody String deleteItems(
			@PathVariable("ids") List<Object> lstIds){
		try{
			testService.deleteItems(lstIds);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException("Error al eliminar usuarios", e);
			
		}
		
		return "El proceso de eliminación ha finalizado correctamente";
	}
	

}
