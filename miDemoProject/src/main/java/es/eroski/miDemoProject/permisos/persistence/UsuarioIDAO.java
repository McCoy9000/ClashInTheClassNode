package es.eroski.miDemoProject.permisos.persistence;

import java.util.List;

import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.generic.persistence.GenericDAO;

/**
 * Interfaz de persistencia para la entidad Usuario.
 * Extiende de GenericDao que contempla los metodos comunes para realizar un CRUD.
 * En caso de necesitar metodos adicionales, deberian describirse aqui.
 * @author BICUGUAL
 *
 */
public interface UsuarioIDAO extends GenericDAO<Usuario>{
	/**
	 * Obtiene los roles de un usuario en base a su username 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRolesUsuario(String username) throws Exception;
	
	/**
	 * Obtiene los roles que puede añadir un usuario determinado en base a su username 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRolesUsuarioDisponibles(String username) throws Exception;
	
	/**
	 * Elimina los roles de una usuario en base a su username
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public int deleteRolesUsuario(String username) throws Exception;
	
	/**
	 * Añade los roles pasados como parametro a un usuario, en base a su username
	 * @param listaroles
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public int insertRolesUsuario(List<Role> listaroles,String username) throws Exception;
	
}
