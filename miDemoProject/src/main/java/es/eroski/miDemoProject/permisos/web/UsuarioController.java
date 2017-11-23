/**
 * 
 */
package es.eroski.miDemoProject.permisos.web;

import java.util.ArrayList;
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
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.permisos.service.PermisosIBS;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * Controlador para la entidad Usuario
 * @author BICUGUAL
 *
 */
@Controller
@RequestMapping(value="/usuarios")
public class UsuarioController {

	private static Logger logger = Logger.getLogger(UsuarioController.class);	
	
	@Autowired
	PermisosIBS permisosService;
	
	@Autowired
	Messages messages;

	
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

			result=permisosService.getDTResponseUsers(datatableFilter);
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
			item=permisosService.getUser(id);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("usuario.error.cargar")+id, e);
			
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
			permisosService.createUser(item); 
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("usuario.error.guardar"), e);
			
		}
		return messages.get("usuario.mensaje.guardar");
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
			if (permisosService.getUser(id)!=null){
				permisosService.updateUser(item);
			}else{
				throw new GenericJsonException( new Throwable(messages.get("usuario.error.no.existe")+" "+id));
			}
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("usuario.error.modificar")+" "+id, e);
		}
		
		return messages.get("usuario.mensaje.guardar");
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
			permisosService.deleteUsers(lstIds);
		}catch(Exception e){
			logger.info(e.getMessage());
			throw new GenericJsonException(messages.get("usuario.error.eliminar"), e);
		}
		
		return messages.get("usuario.mensaje.eliminar");
	}
	

	/**
	 * Devuelve la lista de roles en formato JSON
	 * @param request
	 * @param username
	 * @return
	 */
	@RequestMapping(value ="/rolesUsuario/{id}", produces =MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET )
	@ResponseBody
	List<Role> rolesUsuario (HttpServletRequest request, @PathVariable("id") String username){
		 List<Role> resultado = null;
		 try{  
			 logger.info("rolesUsuario");
			 resultado = permisosService.obtenerRolesUsuario(username);
		  }catch(Exception e){
			   logger.error("Se ha producido un error JSON");
			   throw new GenericJsonException(messages.get("role.error.cargar"), e);
		  }
		  return resultado;
	}
	
	/**
	 * Devuelve la lista de roles que el usuario tiene disponibles para asociar
	 * 
	 * @param request
	 * @param username
	 * @return
	 */
	@RequestMapping(value ="/rolesUsuarioDispon/{id}", produces =MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET )
	@ResponseBody
	List<Role> rolesDisponiblesUsuario (HttpServletRequest request,
										@PathVariable("id") String username){
		 List<Role> resultado = null;
		 try{  
			 logger.info("rolesUsuarioDispo");
			 resultado = permisosService.obtenerRolesUsuarioDisponibles(username);
		  }catch(Exception e){
			   logger.error("Se ha producido un error JSON");
			   throw new GenericJsonException(messages.get("role.error.cargar"), e);
		  }
		  return resultado;
	}
	
	/**
	 * Modifica la lista de roles asociados a un usuario
	 * @param username
	 * @param listaRoles
	 * @return
	 */
	@RequestMapping(value ="/rolesUsuarioUpdate/{id}/{ids}",method = RequestMethod.PUT)
	@ResponseBody
	String rolesUsuarioUpdate (@PathVariable("id") String username,
								@PathVariable("ids") List<Object> listaRoles){

		// Falta llamada a logica de negocio.
		List<Role> roles = null;
		@SuppressWarnings("unused")
		int resultado = 0;
		try{
			if ( listaRoles != null  ){
				 if ( !(((String)listaRoles.get(0)).equals("VACIO")) ){
					 roles = new ArrayList<Role>();
					 for(Object role:listaRoles){
						 Role r = new Role();
						 r.setRolename((String)role);
						 roles.add(r);
					}
				 }
			}
		resultado = permisosService.actualizarRolesUsuario(roles, username);
		}catch( Exception e){
			resultado = -1;
			logger.error("Se ha producido un error JSON");
			 throw new GenericJsonException(messages.get("role.error.modificar"), e);
		}
		return messages.get("role.mensaje.modificar").replace("#username", username);
	}
}
