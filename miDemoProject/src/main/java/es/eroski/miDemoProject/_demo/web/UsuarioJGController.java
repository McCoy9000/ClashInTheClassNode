/**
 * 
 */
package es.eroski.miDemoProject._demo.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import es.eroski.miDemoProject._model.GenericExcelReport;
import es.eroski.miDemoProject._model.JqgridPage;
import es.eroski.miDemoProject._model.JqgridPaginationRequest;
import es.eroski.miDemoProject._model.Menu;
import es.eroski.miDemoProject._model.TableExportForm;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * @author BICUGUAL
 *
 */
@Controller
@RequestMapping(value="/jqGridCrud")
public class UsuarioJGController {

	@Autowired
	DemosIBS demoService;
	
	@Autowired
	Messages messages;
	
	/**
	 * Muestra jsp JqGrid
	 * @param model
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET)
	public String index(Model model){
		Menu menu=new Menu(messages.get("menu.demos"), messages.get("menu.jqgrid"), messages.get("menu.jqgrid.crud"));
		model.addAttribute("menu", menu);
		
		return "usuarioJG/mainUsuarioJG";
	}
	
	/**
	 * Busqueda de resultados para el jqgrid
	 * @param username
	 * @param descripcion
	 * @param flagldap
	 * @param page
	 * @param max
	 * @param index
	 * @param sortOrder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public  @ResponseBody JqgridPage<Usuario> getPagedItems(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "descripcion", required = false) String descripcion,
			@RequestParam(value = "flagldap", required = false) String flagldap,
			@RequestParam(value = "page", required = false, defaultValue = "1") Long page,
			@RequestParam(value = "max", required = false, defaultValue = "10") Long max,
			@RequestParam(value = "index", required = false) String index,
			@RequestParam(value = "sortorder", required = false) String sortOrder) throws Exception{
		
		JqgridPage<Usuario> list=new JqgridPage<Usuario>();
		
		try {
			
			JqgridPaginationRequest pagination= new JqgridPaginationRequest(max,page, sortOrder, !index.isEmpty()?index:null);
	        
			Integer flagldapInt=flagldap==null || flagldap.isEmpty()?null: new Integer(flagldap);

			list = demoService.getJGResponseItems(pagination, username, descripcion,  flagldapInt);
		} catch (Exception e) {
			throw new GenericJsonException("Error en la operación", e);
		}
		
		return list;		

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
	public  @ResponseBody String  updateItem(
			@PathVariable("id") Object id,
			@RequestBody Usuario item){
		
		String retorno;
		
		//Si flag y contraseñan son nulos se esta realizando una edicion en linea de la tabla y debe devolverse "true"
		if (item.getFlagldap()==null && item.getPassword()==null)
			retorno = "true";
		else
			retorno = "El proceso de guardado ha finalizado correctamente";
		
		try{
			demoService.updateJqGridUsuario(item);
		} catch (Exception e) {
			throw new GenericJsonException("Error en la operación", e);
		}

		return retorno;
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
	  
		
		GenericExcelReport formData=null;
		String excelName=messages.get("usuario.lista.tabla.title");
		excelName=excelName.replace(' ', '_');
		
		try{
			formData=demoService.getItemstoExport(form);
			
			if (formData.getDatos()!=null && formData.getDatos().size()>60000)
				throw new Exception("Excel no puede mostrar mas de 60.000 registros, filtre mas la busqueda a exportar");
				
		}catch(Exception e){
			throw new GenericException("Error al exportar los usuarios ", e);
		}
	  
	  ModelAndView modelAndView = new ModelAndView("genericExcelView", "formData", formData).addObject("excelName",excelName);
	  
	  return modelAndView;
	 }
}
