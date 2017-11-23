package es.eroski.miDemoProject.permisos.web;

import java.util.ArrayList;
//import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.eroski.miDemoProject._exceptions.GenericJsonException;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;
import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject.permisos.service.PermisosIBS;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * Controlador para la entidad Role
 * @author BICUGUAL
 */
@Controller
@RequestMapping(value="/roles")
public class RoleController {
	
private static Logger logger = Logger.getLogger(RoleController.class);	
	
	@Autowired
	PermisosIBS permisosService;
	
	@Autowired
	Messages messages;

	/**
	 * Obtiene la lista de roles para mostrarlos mediante el componente datatable
	 * @param request
	 * @param response
	 * @param draw
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public  @ResponseBody DataTableResponse<Role>  getPagedItems(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(required = false, defaultValue="0") Long draw, 
			@RequestParam(required = false, defaultValue="1") Long start,
			@RequestParam(required = false, defaultValue="0") Long length) {
		
		DataTableRequest datatableFilter= new DataTableRequest(draw,length,start, request);	
		DataTableResponse<Role> result;
		
		try{

			result=permisosService.getDTResponseRoles(datatableFilter);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("error.operacion"), e);
			
		}
		return result;
	}
	
	/**
	 * Obtiene los datos de un rol en base al id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public  @ResponseBody Role getItem(
			@PathVariable("id") Object id){
		
		Role item=null;
		try{
			item=permisosService.getRole(id);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("role.error.carga")+" "+id, e);
		}
		return item;
	}
	
	/**
	 * Crear un rol
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public  @ResponseBody String createItem(
			@RequestBody Role item){
		
		try{
			permisosService.createRole(item); 
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("role.error.guardar"), e);
		}
		
		return messages.get("role.mensaje.guardar");
	}
	
	/**
	 * Modificar un rol
	 * @param id
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public  @ResponseBody String updateItem(
			@PathVariable("id") Object id,
			@RequestBody Role item){
		
		try{
			//Compruebo si el item existe en base de datos
			if (permisosService.getRole(id)!=null){
				permisosService.updateRole(item);
			}else{
				
				throw new GenericJsonException( new Throwable(messages.get("role.error.no.existe") + " "+id));
			} 
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("role.error.guardar"), e);
			
		}
		return messages.get("role.mensaje.guardar");
	}
	
	/**
	 * Elimina una lista de roles
	 * @param lstIds
	 * @return
	 */
	@RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
	public  @ResponseBody String deleteItems(
			@PathVariable("ids") List<Object> lstIds){
		try{
			permisosService.deleteRoles(lstIds);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("role.error.eliminar"), e);
		}
		
		return messages.get("role.mensaje.eliminar");
	}
	
	/**
	 * Obtine la lista de permisospara un rol en base a su id
	 * @param request
	 * @param rolename
	 * @return
	 */
	@RequestMapping(value ="/permisosRol/{id}", produces =MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET )
	@ResponseBody
	List<Permission> permisosRole (HttpServletRequest request,
							 @PathVariable("id") String rolename){
			 List<Permission> resultado = null;
			 try{  
				 logger.info("permisosRole");
				 resultado = permisosService.obtenerPermisosRole(rolename);
			  }catch(Exception e){
				   logger.error("Se ha producido un error JSON");
				   throw new GenericJsonException(messages.get("permisos.error.carga.lista"), e);
			  }
			 return resultado;
	}
	
	/**
	 * Obtiene la lista de permisos seleccionables o disponibles para un rol en base a su id
	 * @param request
	 * @param rolename
	 * @return
	 */
	@RequestMapping(value ="/permisosRolDispon/{id}", produces =MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET )
	@ResponseBody
			List<Permission> permisosDisponiblesRole (HttpServletRequest request,
			 						@PathVariable("id") String rolename){
			List<Permission> resultado = null;
			try{  
				 logger.info("permisosDisponiblesRole");
				 resultado = permisosService.obtenerPermisosRoleDisponibles(rolename);
			  }catch(Exception e){
				   logger.error("Se ha producido un error JSON");
				   throw new GenericJsonException(messages.get("permisos.error.carga.lista"), e);
			  }	
   		    return resultado;
	}
	
	/**
	 * Modifica los permisos asociados a un rol
	 * @param request
	 * @param rolename
	 * @param listaPermisos
	 * @return
	 */
	@RequestMapping(value ="/permisosRolUpdate/{id}/{ids}",method = RequestMethod.PUT)
	@ResponseBody
	String permisosRoleUpdate (HttpServletRequest request,
										@PathVariable("id") String rolename,
										@PathVariable("ids") List<Object> listaPermisos){
			 
		List<Permission> permisos = null;
		@SuppressWarnings("unused")
		int resultado = 0;
		try{
			if ( listaPermisos != null  ){
				 if ( !(((String)listaPermisos.get(0)).equals("VACIO")) ){
					 permisos = new ArrayList<Permission>();
					 for(Object permiso:listaPermisos){
						 Permission p = new Permission();
						 p.setPermissionname((String)permiso);
						 permisos.add(p);
					}
				 }
			}
		resultado = permisosService.actualizarPermisosRole(permisos, rolename);
		}catch( Exception e){
			resultado = -1;
			logger.error("Se ha producido un error JSON");
			throw new GenericJsonException(messages.get("permisos.error.actualizar"), e);
		}
		return messages.get("permisos.error.actualizar").replace("#rolename", rolename);
	}
	
}
