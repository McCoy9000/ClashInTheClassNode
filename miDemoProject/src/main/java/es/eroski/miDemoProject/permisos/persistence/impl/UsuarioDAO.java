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
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject._model.User;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.permisos.persistence.UsuarioIDAO;
import es.eroski.miDemoProject.utilidades.QueryUtils;

/**
 * Implementación de la capa de persistencia para usuarios
 * @author BICUGUAL
 */
@Repository
public class UsuarioDAO implements UsuarioIDAO {

	private static Logger logger = Logger.getLogger(UsuarioDAO.class);

	@Autowired
	@Qualifier("jdbcTemplateSecurity")
	JdbcTemplate jdbcTemplate;
	@Value("#{appPropierties['app.id']}")
	private String appId;

	@Override
	public List<Usuario> findAll(DataTableRequest datatableFilters) {
		StringBuffer query = new StringBuffer(
				"SELECT " + " USERNAME, PASSWORD, FLDAP, DESCRIPTION " + " FROM SEC_USER");
		
		String whereConditionByColumn = QueryUtils.getWhereColumnConditionTranslateAndUpper(datatableFilters);
		if (!whereConditionByColumn.isEmpty())
			query.append(" WHERE " + whereConditionByColumn);
		

		if (datatableFilters != null) {
			query = new StringBuffer(QueryUtils.getQueryLimits(datatableFilters, query.toString()));
		}
		logger.info("SQL : "+query);
		List<Usuario> lstItems = (List<Usuario>) jdbcTemplate.query(query.toString(), new ItemMapper());

		return lstItems;
	}

	@Override
	public Long findCount(DataTableRequest datatableFilters) {
		StringBuffer query = new StringBuffer(" SELECT COUNT(*) FROM SEC_USER");

		String whereConditionByColumn = QueryUtils.getWhereColumnConditionTranslateAndUpper(datatableFilters);
		if (!whereConditionByColumn.isEmpty())
			query.append(" WHERE " + whereConditionByColumn);
		logger.info("SQL : "+query);
		Long count = jdbcTemplate.queryForObject(query.toString(), Long.class);

		return count;
	}

	@Override
	public Usuario getItemById(Object id) {
		String sql = "SELECT * FROM SEC_USER WHERE USERNAME=?";
		logger.info("SQL : "+sql);
		Usuario usuario = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, new ItemMapper());

		return usuario;
	}

	@Override
	public void createItem(Usuario item) {
		User user =
				 (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String aux = user.getUserCode();
		List<Object> params = new ArrayList<Object>();
		params.add(item.getUsername());
		params.add(item.getPassword());
		params.add(item.getDescripcion());
		params.add(item.getFlagldap());
		params.add(aux);
		

		StringBuffer query = new StringBuffer("INSERT INTO SEC_USER");
		query.append(" (USERNAME, PASSWORD, DESCRIPTION, FLDAP,CREATED_BY)");
		query.append(" VALUES (?,?,?,?,?)");
		logger.info("SQL : "+query);	
		int rs = this.jdbcTemplate.update(query.toString(), params.toArray());

		logger.info("Resultado crear: " + rs);

	}

	@Override
	public void updateItem(Usuario item) {
		User user =
				 (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String aux = user.getUserCode();
		
		
		List<Object> params = new ArrayList<Object>();
		params.add(item.getPassword());
		params.add(item.getDescripcion());
		params.add(item.getFlagldap());
		params.add(aux);
		params.add(item.getUsername());
		

		StringBuffer query = new StringBuffer("UPDATE SEC_USER SET ");
		query.append(" PASSWORD=?, DESCRIPTION=?, FLDAP=?,MODIFIED_BY=? WHERE USERNAME=?");
		logger.info("SQL : "+query);
		int rs = this.jdbcTemplate.update(query.toString(), params.toArray());
		logger.info("Resultado: " + rs);

	}

	@Override
	public void deleteItem(Object id) {
		String sql = "DELETE FROM SEC_USER WHERE USERNAME=?";
		logger.info("SQL : "+sql);
		this.jdbcTemplate.update(sql, new Object[] { id });

	}
	
	@Override
	public List<Role> getRolesUsuario(String username) throws Exception {
		List<Role> lista = null;
		String SQLA = "";
		SQLA = SQLA + " SELECT ROLENAME,NULL DESCRIPTION FROM SEC_USERS_ROLES WHERE USERNAME = '"+username+"' AND IDAPP = "+ appId;
		try{
			logger.info("SQL : " + SQLA);
			lista = jdbcTemplate.query(SQLA,new RoleMapper());
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
		return lista;
	}

	@Override
	public List<Role> getRolesUsuarioDisponibles(String usenanme) throws Exception {	
		List<Role> lista = null;
		String SQLA = "";
		SQLA = SQLA + " SELECT ROLENAME,NULL DESCRIPTION FROM SEC_ROLE WHERE IDAPP = "+ appId + "";//+ SQLAUXROOT;
		SQLA = SQLA + " MINUS ";
		SQLA = SQLA + "	SELECT ROLENAME,NULL DESCRIPTION FROM SEC_USERS_ROLES WHERE USERNAME ='"+usenanme+"' AND IDAPP = "+ appId;
		try{
			logger.info("SQL : " + SQLA);
			lista = jdbcTemplate.query(SQLA,new RoleMapper());
		}catch(Exception e){
			logger.error(e);
			throw e;
		}
		return lista;
	}

	@Override
	public int deleteRolesUsuario(String username) throws Exception {
		String SQLA = "";
		int result = -1;
		SQLA = SQLA + " DELETE SEC_USERS_ROLES WHERE";
		SQLA = SQLA + " USERNAME = '"+username +"' AND";
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
	public int insertRolesUsuario(List<Role> listaroles, String username) throws Exception {
		User user =
				 (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int result = 0;
		
		
		String aux = user.getUserCode();
		
		String SQLAPRI = " INSERT INTO SEC_USERS_ROLES (IDAPP,ROLENAME,USERNAME,CREATED_BY,MODIFIED_BY)";
		for (Role role:listaroles){
			try{
				String SQLA = "";
				SQLA = SQLAPRI + " VALUES("+appId+",'"+role.getRolename()+"','"+username+"','"+aux+"','"+aux+"')";
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
	 * Mapeo para la consulta de usuarios
	 * @author BICUGUAL
	 */
	private class ItemMapper implements RowMapper<Usuario> {
	
		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

			Usuario user=new Usuario();
			
			user.setUsername(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setDescripcion(rs.getString("DESCRIPTION"));
			user.setFlagldap(rs.getInt("FLDAP"));
			
			return user;
		}
	}
	
	/**
	 * Mapeo consulta Roles
	 * @author BICUGUAL
	 */
	private class  RoleMapper implements RowMapper<Role> {
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role  = new Role();
			role.setRolename(rs.getString("ROLENAME"));
			role.setDescription(rs.getString("DESCRIPTION"));
			return role;
		}
	}

}
