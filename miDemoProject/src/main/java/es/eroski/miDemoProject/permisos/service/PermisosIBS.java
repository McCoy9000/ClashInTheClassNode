/**
 * 
 */
package es.eroski.miDemoProject.permisos.service;

import java.util.List;

import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;
import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject._model.Usuario;

/**
 * Interfaz de servicio para el modulo de permisos
 * @author BICUGUAL
 */
public interface PermisosIBS{


	/**
	 * Obtiene la lista de usuarios para mostrarla con el componente datatable
	 * @param datatableFilter
	 * @return
	 */
	public DataTableResponse<Usuario> getDTResponseUsers(DataTableRequest datatableFilter);

	/**
	 * Obtiene los datos de un usuario en base a su id
	 * @param id
	 * @return
	 */
	public Usuario getUser(Object id);
	
	/**
	 * Modifica los datos del usuario pasado como parametro
	 * @param item
	 * @throws Exception
	 */
	public void updateUser(Usuario item) throws Exception;
	
	/**
	 * Crea un nuevo usuario
	 * @param item
	 * @throws Exception
	 */
	public void createUser(Usuario item) throws Exception;
	
	/**
	 * Elimina la lista de usuarios cuyos ids coincidan con la lista de ids pasados como parametro 
	 * @param lstIds
	 */
	public void deleteUsers(List<Object> lstIds);
	
	/**
	 * Obtiene los roles relacionados con un username
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public List<Role> obtenerRolesUsuario(String username) throws Exception;
	
	/**
	 * Obtiene los roles seleccionables para un usuario
	 * @param usenanme
	 * @return
	 * @throws Exception
	 */
	public List<Role> obtenerRolesUsuarioDisponibles(String usenanme) throws Exception;
	
	/**
	 * Modifica la lista de roles de un usuario
	 * @param listaroles
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public int actualizarRolesUsuario(List<Role> listaroles,String username) throws Exception;
	
	/**
	 * Obtiene la lista de roles para mostrarla con el componente datatable
	 * @param datatableFilter
	 * @return
	 */
	public DataTableResponse<Role> getDTResponseRoles(DataTableRequest datatableFilter);
	
	/**
	 * Obtiene los datos de un rol en base a su id
	 * @param id
	 * @return
	 */
	public Role getRole(Object id);
	
	/**
	 * Modifica los datos de un rol en base a los recibidos como parametro
	 * @param item
	 * @throws Exception
	 */
	public void updateRole(Role item) throws Exception;
	
	/**
	 * Crea un nuevo Rol
	 * @param item
	 * @throws Exception
	 */
	public void createRole(Role item) throws Exception;
	
	/**
	 * Elimina la lista de roles cuyo id coincida con la lista de ids recibida como parametro 
	 * @param lstIds
	 */
	public void deleteRoles(List<Object> lstIds);
	
	
	/**
	 * Obtiene la lista de permisos relacionados con un rolname
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	public List<Permission> obtenerPermisosRole(String rolename) throws Exception;
	
	/**
	 * Obtiene la lista de permisos seleccionables para un rolname
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	public List<Permission> obtenerPermisosRoleDisponibles(String rolename) throws Exception;
	
	/**
	 * Modifica la lista de permisos para un rolname
	 * @param listapermisos
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	public int actualizarPermisosRole(List<Permission> listapermisos,String rolename) throws Exception;
	
	
	/**
	 * Obtiene la lista de permisos para ser mostrada con el componente datatable
	 * @param datatableFilter
	 * @return
	 */
	public DataTableResponse<Permission> getDTResponsePermissions(DataTableRequest datatableFilter);
	
	/**
	 * Obtiene los datos de un permiso en base a su id
	 * @param id
	 * @return
	 */
	public Permission getPermission(Object id);
	

		
}
