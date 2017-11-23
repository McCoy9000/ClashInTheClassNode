/**
 * 
 */
package es.eroski.miDemoProject._demo.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.eroski.miDemoProject._demo.service.DemosIBS;
import es.eroski.miDemoProject._exceptions.GenericException;
import es.eroski.miDemoProject._exceptions.GenericJsonException;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;
import es.eroski.miDemoProject._model.GenericExcelReport;
import es.eroski.miDemoProject._model.Menu;
import es.eroski.miDemoProject._model.TableExportForm;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * Controlador para el modulo de Usuarios
 * @author BICUGUAL
 *
 */
@Controller
@RequestMapping(value="/datatableCrud")
public class UsuarioDTController {

	private static Logger logger = Logger.getLogger(UsuarioDTController.class);	
	
	@Autowired
	DemosIBS demoService;
	
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
		
		return "usuarioDT/mainUsuario";
	
	}
	
	/**
	 * Carga del formulario 
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/formulario", method = RequestMethod.GET)
	public String formulario(Principal principal) throws Exception{
		return "usuarioDT/formUsuario";
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
	public  @ResponseBody DataTableResponse<Usuario>  getPagedItems(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(required = false, defaultValue="0") Long draw, 
			@RequestParam(required = false, defaultValue="1") Long start,
			@RequestParam(required = false, defaultValue="0") Long length) {
		
		DataTableRequest datatableFilter= new DataTableRequest(draw,length,start, request);	
		DataTableResponse<Usuario> result;
		
		try{

			result=demoService.getDTResponseItems(datatableFilter);
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
			item=demoService.getItem(id);
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
			demoService.createItem(item); 
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
			if (demoService.getItem(id)!=null){
				demoService.updateItem(item);
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
			demoService.deleteItems(lstIds);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException("Error al eliminar usuarios", e);
			
		}
		
		return "El proceso de eliminación ha finalizado correctamente";
	}
	
	/**
	 * Carga la pagina principal del módulo
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/exportar", method = RequestMethod.POST)
	 ModelAndView generateExcel(@ModelAttribute("exportForm") TableExportForm form,  
			 HttpServletResponse response) throws Exception {
	  
		logger.info("Exportacion");
		
		GenericExcelReport formData=null;
		String excelName=messages.get("usuario.lista.tabla.title");
		excelName=excelName.replace(' ', '_');
		
		try{
			formData=demoService.getItemstoExport(form);
			
			if (formData.getDatos()!=null && formData.getDatos().size()>60000){
			
				throw new Exception("Excel no puede mostrar mas de 60.000 registros, filtre mas la busqueda a exportar");
			}
				
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericException("Error al exportar los usuarios ", e);
		}
	  
	  ModelAndView modelAndView = new ModelAndView("genericExcelView", "formData", formData).addObject("excelName",excelName);
	  
	  return modelAndView;
	 }
}
