/**
 * 
 */
package es.eroski.miDemoProject.autenticacion.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject._model.User;
import es.eroski.miDemoProject.autenticacion.persistence.AutenticacionIDAO;

/**
 * Implementacion de la capa de persistencia para la Autenticacion en BD 
 * @author BICUGUAL
 */
@Repository
public class AutenticacionDAO implements AutenticacionIDAO {

	private static final Logger logger =Logger.getLogger(AutenticacionDAO.class);
		
	@Autowired
	JdbcTemplate jdbcTemplateSecurity;
	
	@Value("#{appPropierties['app.id']}")
	private String appId;
	
	@Value("#{appPropierties['app.name']}")
	private String appName;

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		  try{ 
			StringBuffer sql = new StringBuffer("SELECT * FROM SEC_USER WHERE ");
			sql.append(" UPPER(USERNAME) = ?  ");
			logger.info("SQL : " + sql.toString());
			user = jdbcTemplateSecurity.queryForObject(sql.toString(),
					new Object[] { username.toUpperCase() }, new UserMapper());
		  }catch(Exception e){
			  logger.error(e);
		  }
	      return user;
	}
	
	@Override
	public List<Role> getRolesByUser(String username) {
		StringBuffer sql = new StringBuffer(
				" SELECT * FROM SEC_USER A,SEC_ROLE B, SEC_USERS_ROLES C WHERE ");
		sql.append(" B.IDAPP = " + appId + " AND ");
		sql.append(" C.IDAPP = " + appId + " AND ");
		sql.append(" A.USERNAME = C.USERNAME AND ");
		sql.append(" B.ROLENAME = C.ROLENAME AND UPPER(A.USERNAME) = ? ");

		List<Role> roles = null;

		try {
			logger.info("SQL : " + sql.toString());
			roles = jdbcTemplateSecurity.query(sql.toString(),
					new Object[] { username.toUpperCase() }, new RoleMapper());
		} catch (Exception e) {
			logger.error(e);
		}
		return roles;
	}
	
	@Override
	public List<Permission> getPermissionByRole(String role) {
		StringBuffer sql = new StringBuffer(
				" SELECT * FROM SEC_ROLE A, SEC_PERMISSION B, SEC_ROLES_PERMISSIONS C "
				+ "WHERE ");
		sql.append(" A.IDAPP = " + appId + " AND ");
		sql.append(" B.IDAPP = " + appId + " AND ");
		sql.append(" C.IDAPP = " + appId + " AND ");
		sql.append(" A.ROLENAME = C.ROLENAME AND B.PERMISSIONNAME = C.PERMISSIONNAME AND A.ROLENAME = ? ");

		List<Permission> permissions = null;

		try {
			logger.info("SQL : " + sql.toString());
			permissions = jdbcTemplateSecurity.query(sql.toString(),
					new Object[] { role }, new PermissionMapper());
		} catch (Exception e) {
			logger.error(e);
		}
		return permissions;
	}
	
	
	/**
	 * Mapper para la tabla USERS de la base de datos de seguridad
	 */
	private class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {

			User user = new User();

			user.setUsername(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setFlagldap(rs.getInt("FLDAP"));
			user.setUserCode(rs.getString("USERNAME"));

			return user;
		}
	}
	
	/**
	 * Mapper para la tabla ROLES de la base de datos de seguridad
	 */
	private class  RoleMapper implements RowMapper<Role> {
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role  = new Role();
			role.setRolename(rs.getString("ROLENAME"));
			return role;
		}
	}
	
	/**
	 * Mapper para la tabla PERMISSIONS de la base de datos de seguridad
	 */
	private class PermissionMapper implements RowMapper<Permission>  {

		public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
			  Permission permission  = new Permission();
			  permission.setPermissionname(rs.getString("PERMISSIONNAME"));
		      return permission;
		   }

	}
	
}
