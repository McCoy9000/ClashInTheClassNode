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
import org.springframework.stereotype.Repository;

import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject.permisos.persistence.PermissionIDAO;
import es.eroski.miDemoProject.utilidades.QueryUtils;

/**
 * Implementacion de la capa de persistencia para la entidad Permission
 * @author BICUGUAL
 */
@Repository
public class PermissionDAO implements PermissionIDAO{
	
	private static Logger logger = Logger.getLogger(PermissionDAO.class);

	@Autowired
	@Qualifier("jdbcTemplateSecurity")
	JdbcTemplate jdbcTemplate;
	@Value("#{appPropierties['app.id']}")
	private String appId;

	@Override
	public List<Permission> findAll(DataTableRequest datatableFilters) {
		StringBuffer query = new StringBuffer(
				"SELECT " + " PERMISSIONNAME, DESCRIPTION " + " FROM SEC_PERMISSION WHERE  IDAPP = "+ appId +" ");
		
		String whereConditionByColumn = QueryUtils.getWhereColumnConditionTranslateAndUpper(datatableFilters);
		if (!whereConditionByColumn.isEmpty())
			query.append(" AND "+ whereConditionByColumn);
		if (datatableFilters != null) {
			query = new StringBuffer(QueryUtils.getQueryLimits(datatableFilters, query.toString()));
		}
		logger.info("SQL : " + query);
		List<Permission> lstItems = (List<Permission>) jdbcTemplate.query(query.toString(), new ItemMapper());
		return lstItems;
	}

	@Override
	public Long findCount(DataTableRequest datatableFilters) {
		StringBuffer query = new StringBuffer(" SELECT COUNT(*) FROM SEC_PERMISSION WHERE  IDAPP = "+ appId +" ");

		String whereConditionByColumn = QueryUtils.getWhereColumnConditionTranslateAndUpper(datatableFilters);
		if (!whereConditionByColumn.isEmpty())
			query.append(" AND "+ whereConditionByColumn);
		logger.info("SQL : " +query);
		Long count = jdbcTemplate.queryForObject(query.toString(), Long.class);

		return count;
	}

	@Override
	public Permission getItemById(Object id) {
		String sql = "SELECT * FROM SEC_PERMISSION WHERE IDAPP = "+ appId +" AND PERMISSINONAME=?";
		logger.info("SQL : " +sql);
		Permission permiso = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, new ItemMapper());
		return permiso;
	}

	@Override
	public void createItem(Permission item) {
		List<Object> params = new ArrayList<Object>();
		params.add(item.getPermissionname());
		params.add(item.getDescription());
		StringBuffer query = new StringBuffer("INSERT INTO SEC_PERMISSION");
		query.append(" (PERMISSIONNAME, DESCRIPTION, IDAPP)");
		query.append(" VALUES (?,?," + appId + ")");
		logger.info("SQL : " +query);
		@SuppressWarnings("unused")
		int rs = this.jdbcTemplate.update(query.toString(), params.toArray());	
	}

	@Override
	public void updateItem(Permission item) {
		List<Object> params = new ArrayList<Object>();
		params.add(item.getPermissionname() );
		params.add(item.getDescription());
		params.add(item.getPermissionname());
		StringBuffer query = new StringBuffer("UPDATE SEC_PERMISSION SET ");
		query.append(" SEC_PERMISSIONNAME=?, DESCRIPTION=?, IDAPP="+appId+" WHERE PERMISSIONNAME=?");
		logger.info("SQL : " +query);
		@SuppressWarnings("unused")
		int rs = this.jdbcTemplate.update(query.toString(), params.toArray());
		
	}

	@Override
	public void deleteItem(Object id) {
		String sql = "DELETE FROM SEC_PERMISSION WHERE IDAPP = "+ appId +" AND PERMISSIONNAME=?";
		logger.info("SQL : " +sql);
		this.jdbcTemplate.update(sql, new Object[] { id });
		
	}
	
private class ItemMapper implements RowMapper<Permission> {
		
		public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {

			Permission permiso=new Permission();	
			permiso.setPermissionname(rs.getString("PERMISSIONNAME"));
			permiso.setDescription(rs.getString("DESCRIPTION"));
			return permiso;
		}
	}

}
