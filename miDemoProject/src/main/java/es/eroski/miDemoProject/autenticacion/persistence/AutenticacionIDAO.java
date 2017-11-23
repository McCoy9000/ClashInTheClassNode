/**
 * 
 */
package es.eroski.miDemoProject.autenticacion.persistence;

import java.util.List;

import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject._model.User;

/**
 * Interfaz para la capa de datos para la autenticacion de usuarios.
 * @author BICUGUAL
 */
public interface AutenticacionIDAO {

	/**
	 * Obtiene un usuario de la base de datos en base al username
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);
	
	/**
	 * Obtiene la lista de roles de la bd de seguridad en base al username
	 * @param username
	 * @return
	 */
	public List<Role> getRolesByUser(String username);
	
	/**
	 * Obtiene la lista de permisos de la bd de seguridad en base al nombre del rol  
	 * @param role
	 * @return
	 */
	public List<Permission> getPermissionByRole(String role);
	
}
