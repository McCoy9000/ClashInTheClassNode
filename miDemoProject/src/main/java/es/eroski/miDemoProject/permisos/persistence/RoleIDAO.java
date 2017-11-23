package es.eroski.miDemoProject.permisos.persistence;

import java.util.List;

import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject.generic.persistence.GenericDAO;

/**
 * Interfaz de persistencia para la entidad Role
 * @author BICUGUAL
 *
 */
public interface RoleIDAO extends GenericDAO<Role>{
	/**
	 * Obtiene la lista de permisos en base a un rolename
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	public List<Permission> getPermisosRole(String rolename) throws Exception;
	
	/**
	 * Obtiene la lista de permisos disponibles o seleccionables en base al rolename
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	public List<Permission> getPermisosRoleDisponible(String rolename) throws Exception;
	
	/**
	 * Elimina permisos para un rolename determinado
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	public int deletePermissionsRole(String rolename) throws Exception;
	
	/**
	 * Asocia/Inserta la lista de permisos al rolname recibidos en los parametros
	 * @param listapermisos
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	public int insertPermissionsRole(List<Permission> listapermisos,String rolename) throws Exception;

}
