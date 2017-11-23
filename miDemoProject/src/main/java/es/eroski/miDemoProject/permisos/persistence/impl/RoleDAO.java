package es.eroski.miDemoProject.permisos.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject._model.User;
import es.eroski.miDemoProject.permisos.persistence.RoleIDAO;
import es.eroski.miDemoProject.utilidades.QueryUtils;

/**
 * Implementacion de la capa de persistencia para la entidad Role
 * @author BICUGUAL
 */
@Repository
public class RoleDAO implements RoleIDAO{
	
	private static Logger logger = Logger.getLogger(RoleDAO.class);

	@Autowired
	@Qualifier("jdbcTemplateSecurity")
	JdbcTemplate jdbcTemplate;
	@Value("#{appPropierties['app.id']}")
	private String appId;

	@Override
	public List<Role> findAll(DataTableRequest datatableFilters) {
		StringBuffer query = new StringBuffer(
				"SELECT " + " ROLENAME, DESCRIPTION " + " FROM SEC_ROLE WHERE  IDAPP = "+ appId +" ");
		
		String whereConditionByColumn = QueryUtils.getWhereColumnConditionTranslateAndUpper(datatableFilters);
		if (!whereConditionByColumn.isEmpty())
			query.append(" AND "+ whereConditionByColumn);
		if (datatableFilters != null) {
			query = new StringBuffer(QueryUtils.getQueryLimits(datatableFilters, query.toString()));
		}
		logger.info("SQL : " +query);
		List<Role> lstItems = (List<Role>) jdbcTemplate.query(query.toString(), new ItemMapper());
		return lstItems;
	}

	@Override
	public Long findCount(DataTableRequest datatableFilters) {
		StringBuffer query = new StringBuffer(" SELECT COUNT(*) FROM SEC_ROLE WHERE  IDAPP = "+ appId +" ");

		String whereConditionByColumn = QueryUtils.getWhereColumnConditionTranslateAndUpper(datatableFilters);
		if (!whereConditionByColumn.isEmpty())
			query.append(" AND "+ whereConditionByColumn);
		logger.info("SQL : " +query);
		Long count = jdbcTemplate.queryForObject(query.toString(), Long.class);

		return count;
	}

	@Override
	public Role getItemById(Object id) {
		String sql = "SELECT * FROM SEC_ROLE WHERE IDAPP = "+ appId +" AND ROLENAME=?";
		logger.info("SQL : "+sql);
		Role role = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, new ItemMapper());

		return role;
	}

	@Override
	public void createItem(Role item) {
		User user =
				 (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String aux = user.getUserCode();
		List<Object> params = new ArrayList<Object>();
		params.add(item.getRolename());
		params.add(item.getDescription());
		params.add(aux);
		StringBuffer query = new StringBuffer("INSERT INTO SEC_ROLE");
		query.append(" (ROLENAME, DESCRIPTION, IDAPP,CREATED_BY)");
		query.append(" VALUES (?,?," + appId + ",?)");
		logger.info("SQL : "+query);
		int rs = this.jdbcTemplate.update(query.toString(), params.toArray());
		logger.info("Resultado crear: " + rs);
	}

	@Override
	public void updateItem(Role item) {
		User user =
				 (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String aux = user.getUserCode();
		List<Object> params = new ArrayList<Object>();
		params.add(item.getRolename());
		params.add(item.getDescription());
		params.add(aux);
		params.add(item.getRolename());
		StringBuffer query = new StringBuffer("UPDATE SEC_ROLE SET ");
		query.append(" ROLENAME=?, DESCRIPTION=?, MODIFIED_BY=? WHERE IDAPP="+appId+" AND ROLENAME=?");
		logger.info("SQL : "+query);
		int rs = this.jdbcTemplate.update(query.toString(), params.toArray());
		logger.info("Resultado: " + rs);
		
	}

	@Override
	public void deleteItem(Object id) {
		String sql = "DELETE FROM SEC_ROLE WHERE IDAPP = "+ appId +" AND ROLENAME=?";
		logger.info("SQL : "+sql);
		this.jdbcTemplate.update(sql, new Object[] { id });
		
	}

	@Override
	public List<Permission> getPermisosRole(String rolename) throws Exception {
		List<Permission> lista = null;
		String SQLA = "";	
		SQLA = SQLA + " SELECT PERMISSIONNAME,NULL DESCRIPTION FROM SEC_ROLES_PERMISSIONS ";
		SQLA = SQLA + " WHERE ROLENAME = '"+rolename+"' AND IDAPP = "+ appId;		
	
		try{
			logger.info("SQL : "+ SQLA);
			lista = jdbcTemplate.query(SQLA,new PermissionMapper());
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
		return lista;
	}

	@Override
	public List<Permission> getPermisosRoleDisponible(String rolename) throws Exception {
		List<Permission> lista = null;
		String SQLA = "";
		SQLA = SQLA + " SELECT PERMISSIONNAME,NULL DESCRIPTION FROM SEC_PERMISSION WHERE IDAPP = "+ appId+" MINUS ";     
		SQLA = SQLA + " SELECT PERMISSIONNAME,NULL DESCRIPTION FROM SEC_ROLES_PERMISSIONS  ";
		SQLA = SQLA + " WHERE ROLENAME = '"+rolename+"' AND IDAPP = "+ appId;
		try{
			logger.info("SQL : "+ SQLA);
			lista = jdbcTemplate.query(SQLA,new PermissionMapper());
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
		return lista;
	}

	@Override
	public int deletePermissionsRole(String rolename) throws Exception {
		String SQLA = "";
		int result = -1;
		SQLA = SQLA + " DELETE SEC_ROLES_PERMISSIONS WHERE";
		SQLA = SQLA + " ROLENAME = '"+rolename +"' AND";
		SQLA = SQLA + " IDAPP = "+ appId;
		
		try{
			logger.info("SQL : " + SQLA);
			result = jdbcTemplate.update(SQLA);
		}catch(Exception e){
			result = -1;
			logger.error(e);
			throw e;
		}
		return result;
	}

	@Override
	public int insertPermissionsRole(List<Permission> listapermisos, String rolename) throws Exception {
		User user =
				 (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int result = 0;
		String aux = user.getUserCode();
		
		String SQLAPRI = " INSERT INTO SEC_ROLES_PERMISSIONS (IDAPP,PERMISSIONNAME,ROLENAME,CREATED_BY,MODIFIED_BY)";
		for (Permission permiso:listapermisos){
			try{
				String SQLA = "";
				SQLA = SQLAPRI + " VALUES("+appId+",'"+permiso.getPermissionname()+"','"+rolename+"','"+aux+"','"+aux+"')";
				logger.info("SQL : " + SQLA);
				result = result + jdbcTemplate.update(SQLA);
			}catch(Exception e){
				result = -1;
				logger.error(e);
				throw e;
			}
		}
		return result;
	}
	
	/**
	 * Mapep para la entidad Role
	 * @author BICUGUAL
	 */
	private class ItemMapper implements RowMapper<Role> {
		
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

			Role role=new Role();	
			role.setRolename(rs.getString("ROLENAME"));
			role.setDescription(rs.getString("DESCRIPTION"));
			return role;
		}
	}
	
	/**
	 * Mapeo para la entidad Permission
	 * @author BICUGUAL
	 */
	private class PermissionMapper implements RowMapper<Permission>  {

		public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
			  Permission permission  = new Permission();
			  permission.setPermissionname(rs.getString("PERMISSIONNAME"));
			  permission.setDescription(rs.getString("DESCRIPTION"));
		      return permission;
		   }

	}

}
