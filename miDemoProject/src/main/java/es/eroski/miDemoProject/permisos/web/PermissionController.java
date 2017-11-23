package es.eroski.miDemoProject.permisos.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.eroski.miDemoProject._exceptions.GenericJsonException;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;
import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject.permisos.service.PermisosIBS;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * Controlador para la entidad Permission
 * @author BICUGUAL
 *
 */
@Controller
@RequestMapping(value="/permisos")
public class PermissionController {
	
	private static Logger logger = Logger.getLogger(PermissionController.class);	
	@Autowired
	PermisosIBS permisosService;
	
	@Autowired
	Messages messages;
	
	/**
	 * Obtiene la lista de permisos para mostrarla en un componente datatables
	 * @param request
	 * @param response
	 * @param draw
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public  @ResponseBody DataTableResponse<Permission>  getPagedItems(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(required = false, defaultValue="0") Long draw, 
			@RequestParam(required = false, defaultValue="1") Long start,
			@RequestParam(required = false, defaultValue="0") Long length) {
		
		DataTableRequest datatableFilter= new DataTableRequest(draw,length,start, request);	
		DataTableResponse<Permission> result;
		
		try{

			result=permisosService.getDTResponsePermissions(datatableFilter);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("error.operacion"), e);
			
		}
		return result;
	}
	
	/**
	 * Obtiene los datos de un permiso en base a su id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public  @ResponseBody Permission getItem(
			@PathVariable("id") Object id){
		
		Permission item=null;
		try{
			item=permisosService.getPermission(id);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("permiso.error.carga") + " "+id, e);
		}
		return item;
	}

}
