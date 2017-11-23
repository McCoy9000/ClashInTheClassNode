/**
 * 
 */
package es.eroski.miDemoProject.permisos.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;
import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.permisos.persistence.PermissionIDAO;
import es.eroski.miDemoProject.permisos.persistence.RoleIDAO;
import es.eroski.miDemoProject.permisos.persistence.UsuarioIDAO;
import es.eroski.miDemoProject.permisos.service.PermisosIBS;

/**
 * Implementacion de la capa de servicio para la entidad Usuario
 * @author BICUGUAL
 *
 */
@Service
public class PermisosBS implements PermisosIBS {

	private static Logger logger = Logger.getLogger(PermisosBS.class);
	
	@Autowired
	UsuarioIDAO usuarioDao;
	@Autowired
	RoleIDAO roleDao;
	@Autowired
	PermissionIDAO permisoDao;
	@Autowired
	@Qualifier("encoder")
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public DataTableResponse<Usuario> getDTResponseUsers(DataTableRequest datatableFilter) {
		List<Usuario> lstItems=usuarioDao.findAll(datatableFilter);
		Long recordsTotal=usuarioDao.findCount(datatableFilter);
		Long recordsFiltered=recordsTotal;
		return new DataTableResponse<Usuario>(datatableFilter.getDraw(), recordsTotal, recordsFiltered, lstItems, null);
	}

	@Override
	public Usuario getUser(Object id) {
		Usuario item=usuarioDao.getItemById(id);
		return item;
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void updateUser(Usuario item) throws Exception{
		Usuario oldUser=usuarioDao.getItemById(item.getUsername());
		String password=item.getPassword();
		// Si la contraseña está a blanco, cogemos la contraseña anterior.
		if ( item.getPassword().equals("")){
			 item.setPassword(oldUser.getPassword());
		}else{
			item.setPassword(passwordEncoder.encode(password));
		}
		usuarioDao.updateItem(item);
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void createUser(Usuario item) throws Exception {
		item.setPassword(passwordEncoder.encode(item.getPassword()));
		usuarioDao.createItem(item);
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void deleteUsers(List<Object> lstIds) {
		for (Object id: lstIds){
			usuarioDao.deleteItem((String) id);
		}
	}
	
	@Override
	public List<Role> obtenerRolesUsuario(String username) throws Exception {
		try{
			logger.info("obtenerRolesUsuario");
			return usuarioDao.getRolesUsuario(username);
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<Role> obtenerRolesUsuarioDisponibles(String usenanme) throws Exception {
		try{
			logger.info("obtenerRolesUsuarioDisponibles");
			return usuarioDao.getRolesUsuarioDisponibles(usenanme);
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public int actualizarRolesUsuario(List<Role> listaroles, String username) throws Exception {
		int result = -1;
		try{
			logger.info("actualizarRolesUsuario");
			result = usuarioDao.deleteRolesUsuario(username);
			if ( listaroles != null ){
				result = usuarioDao.insertRolesUsuario(listaroles, username);
			}	
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
		return result;
	}


	@Override
	public DataTableResponse<Role> getDTResponseRoles(DataTableRequest datatableFilter) {
		List<Role> lstItems=roleDao.findAll(datatableFilter);
		Long recordsTotal=roleDao.findCount(datatableFilter);
		Long recordsFiltered=recordsTotal;
		return new DataTableResponse<Role>(datatableFilter.getDraw(), recordsTotal, recordsFiltered, lstItems, null);

	}

	@Override
	public Role getRole(Object id) {
		Role item=roleDao.getItemById(id);
		return  item;
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void updateRole(Role item) throws Exception {
		roleDao.updateItem(item);
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void createRole(Role item) throws Exception {
		roleDao.createItem(item);
		
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public void deleteRoles(List<Object> lstIds) {
		for (Object id: lstIds){
			roleDao.deleteItem((String) id);
		}
		
	}

	@Override
	public List<Permission> obtenerPermisosRole(String rolename) throws Exception {
		try{
			logger.info("obtenerPermisosRole");
			return roleDao.getPermisosRole(rolename);
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<Permission> obtenerPermisosRoleDisponibles(String rolename) throws Exception {
		try{
			logger.info("obtenerPermisosRoleDisponible");
			return roleDao.getPermisosRoleDisponible(rolename);
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
	}

	@Override
	@Transactional("transactionManagerSecurity")
	public int actualizarPermisosRole(List<Permission> listapermisos, String rolename) throws Exception {
		int result = -1;
		try{
			logger.info("actualizarPermisosRole");
			result = roleDao.deletePermissionsRole(rolename);
			if ( listapermisos != null ){
				result = roleDao.insertPermissionsRole(listapermisos, rolename);
			}	
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
		return result;
	}

	@Override
	public DataTableResponse<Permission> getDTResponsePermissions(DataTableRequest datatableFilter) {
		List<Permission> lstItems=permisoDao.findAll(datatableFilter);
		Long recordsTotal=permisoDao.findCount(datatableFilter);
		Long recordsFiltered=recordsTotal;
		return new DataTableResponse<Permission>(datatableFilter.getDraw(), recordsTotal, recordsFiltered, lstItems, null);
	}

	@Override
	public Permission getPermission(Object id) {
		Permission item=permisoDao.getItemById(id);
		return item;
	}
	
}
