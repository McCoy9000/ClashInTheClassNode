/**
 * 
 */
package es.eroski.miDemoProject._demo.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.eroski.miDemoProject._demo.persistence.DemosIDAO;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.JqgridPaginationRequest;
import es.eroski.miDemoProject._model.OptionSelectBean;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.utilidades.QueryUtils;


/**
 * @author BICUGUAL
 *
 */
@Repository
public class DemosDAO implements DemosIDAO {

	@Autowired
	@Qualifier("jdbcTemplateSecurity")
	JdbcTemplate jdbcTemplate; 
	
	private static Logger logger = Logger.getLogger(DemosDAO.class);
	
	/*
	 * USER_DEMO JQGRID 
	 */
	
	@Override
	public List<Usuario> findAllJG(JqgridPaginationRequest jqGridRequest, String username, String descripcion, Integer flagldap) {
		StringBuffer query = new StringBuffer("SELECT " + " USERNAME, PASSWORD, FLDAP, DESCRIPTION " + " FROM USER_DEMO");
		
		// Construyo la WHERE
		query.append(getWhereQuery(username, descripcion, flagldap));
		
		// Construyo ORDENACION
		StringBuffer order = new StringBuffer();

		if (jqGridRequest != null) {
			if (jqGridRequest.getSort() != null) {
				order.append(" order by " + jqGridRequest.getSort() + " " + jqGridRequest.getAscDsc());
				query.append(order);
			}
		}
		
		if (jqGridRequest != null) {
			query = new StringBuffer(QueryUtils.getQueryLimits(jqGridRequest, query.toString()));
		}

		List<Usuario> lstItems = (List<Usuario>) jdbcTemplate.query(query.toString(), new ItemMapper());

		return lstItems;
				
	}

	@Override
	public Long findCountJG(JqgridPaginationRequest jqGridRequest, String username, String descripcion, Integer flagldap) {
		StringBuffer query = new StringBuffer(" SELECT COUNT(*) FROM USER_DEMO");
		
		query.append(getWhereQuery(username, descripcion, flagldap));

		Long count = jdbcTemplate.queryForObject(query.toString(), Long.class);

		return count;

	}
	
	
	private String getWhereQuery(String username, String descripcion, Integer flagldap) {
		StringBuffer where = new StringBuffer(" WHERE 1=1");

		//Busqueda username
		if (username != null && !username.isEmpty()){
			where.append(" AND UPPER(USERNAME) LIKE UPPER ('%" +username+ "%')");
		}
		//Busqueda descripcion
		if (descripcion != null && !descripcion.isEmpty()){
			where.append(" AND UPPER(DESCRIPTION) LIKE UPPER ('%" +descripcion+ "%')");
		}
		//Busqueda descripcion
		if (flagldap != null){
			where.append(" AND FLDAP=" +flagldap);
		}
		
		
		return where.toString();
	}
	
	
	/*
	 * USER_DEMO DATATABLE 
	 */
	
	@Override
	public List<Usuario> findAll(DataTableRequest datatableFilters) {
		StringBuffer query = new StringBuffer(
				"SELECT " + " USERNAME, PASSWORD, FLDAP, DESCRIPTION " + " FROM USER_DEMO");
		
		String whereConditionByColumn = QueryUtils.getWhereColumnConditionTranslateAndUpper(datatableFilters);
		if (!whereConditionByColumn.isEmpty())
			query.append(" WHERE " + whereConditionByColumn);
		

		if (datatableFilters != null) {
			query = new StringBuffer(QueryUtils.getQueryLimits(datatableFilters, query.toString()));
		}

		List<Usuario> lstItems = (List<Usuario>) jdbcTemplate.query(query.toString(), new ItemMapper());

		return lstItems;
	}

	@Override
	public Long findCount(DataTableRequest datatableFilters) {
		StringBuffer query = new StringBuffer(" SELECT COUNT(*) FROM USER_DEMO");

		String whereConditionByColumn = QueryUtils.getWhereColumnConditionTranslateAndUpper(datatableFilters);
		if (!whereConditionByColumn.isEmpty())
			query.append(" WHERE " + whereConditionByColumn);

		Long count = jdbcTemplate.queryForObject(query.toString(), Long.class);

		return count;
	}

	/*
	 * USER_DEMO COMPARTIDO PARA JQGRID Y DATATABLE
	 */
	
	@Override
	public Usuario getItemById(Object id) {
		String sql = "SELECT * FROM USER_DEMO WHERE USERNAME=?";
		Usuario usuario = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, new ItemMapper());

		return usuario;
	}

	@Override
	public void createItem(Usuario item) {
		List<Object> params = new ArrayList<Object>();
		params.add(item.getUsername());
		params.add(item.getPassword());
		params.add(item.getDescripcion());
		params.add(item.getFlagldap());

		StringBuffer query = new StringBuffer("INSERT INTO USER_DEMO");
		query.append(" (USERNAME, PASSWORD, DESCRIPTION, FLDAP)");
		query.append(" VALUES (?,?,?,?)");

		int rs = this.jdbcTemplate.update(query.toString(), params.toArray());

		logger.info("Resultado crear: " + rs);

	}

	@Override
	public void updateItem(Usuario item) {
		List<Object> params = new ArrayList<Object>();
		params.add(item.getPassword());
		params.add(item.getDescripcion());
		params.add(item.getFlagldap());
		params.add(item.getUsername());

		StringBuffer query = new StringBuffer("UPDATE USER_DEMO SET ");
		query.append(" PASSWORD=?, DESCRIPTION=?, FLDAP=? WHERE USERNAME=?");

		int rs = this.jdbcTemplate.update(query.toString(), params.toArray());
		System.out.println("Resultado: " + rs);

	}

	@Override
	public void deleteItem(Object id) {
		String sql = "DELETE FROM USER_DEMO WHERE USERNAME=?";
		this.jdbcTemplate.update(sql, new Object[] { id });

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

	
	/*
	 * RESTO DE DEMOS
	 */
	@Override
	public List<OptionSelectBean<Integer>> getLstAreas() {
		List<OptionSelectBean<Integer>> lstAreas=new ArrayList<OptionSelectBean<Integer>>();
		lstAreas.add(new OptionSelectBean<Integer>(1, "APPLICATION MANAGEMENT"));
		lstAreas.add(new OptionSelectBean<Integer>(2, "ARQUITECTURAS TECNOLOGICAS"));
		lstAreas.add(new OptionSelectBean<Integer>(3, "BPO"));
		lstAreas.add(new OptionSelectBean<Integer>(4, "CENTRO DE SERVICIO A USUARIOS"));
		lstAreas.add(new OptionSelectBean<Integer>(5, "DESARROLLO DE PROYECTOS"));
		
		return lstAreas;
	}

	@Override
	public List<OptionSelectBean<Integer>> getLstResponsablesByIdArea(Integer idArea) {
		List<OptionSelectBean<Integer>> lstResponsables=new ArrayList<OptionSelectBean<Integer>>();
		switch (idArea) {
		case 1:
			lstResponsables.add(new OptionSelectBean<Integer>(1, "Fernando Fernandez"));
			lstResponsables.add(new OptionSelectBean<Integer>(2, "Martín Martinez"));
			break;
		case 2:
			lstResponsables.add(new OptionSelectBean<Integer>(3, "Sancho Sanchez"));
			lstResponsables.add(new OptionSelectBean<Integer>(4, "Hernando Hernandez"));
			break;
		case 3:
			lstResponsables.add(new OptionSelectBean<Integer>(5, "Gutier Gutierrez"));
			break;
		case 4:
			lstResponsables.add(new OptionSelectBean<Integer>(6, "Eduardo Eduardez"));
			lstResponsables.add(new OptionSelectBean<Integer>(7, "Galindo Galindez"));
			break;
		case 5:
			lstResponsables.add(new OptionSelectBean<Integer>(8, "Iñigo Iñiguez"));
			break;
			
		default:
			break;
		}
		return lstResponsables;
	}

	
	@Override
	public List<OptionSelectBean<Integer>> getLstLenguajes(String term){
		List<OptionSelectBean<Integer>> lstLenguajes=new ArrayList<OptionSelectBean<Integer>>();
		lstLenguajes.add(new OptionSelectBean<Integer>(1, "ActionScript"));
		lstLenguajes.add(new OptionSelectBean<Integer>(2, "AppleScript"));
		lstLenguajes.add(new OptionSelectBean<Integer>(3, "Asp"));
		lstLenguajes.add(new OptionSelectBean<Integer>(4, "BASIC"));
		lstLenguajes.add(new OptionSelectBean<Integer>(5, "C"));
		lstLenguajes.add(new OptionSelectBean<Integer>(6, "COBOL"));
		lstLenguajes.add(new OptionSelectBean<Integer>(7, "Fortran"));
		lstLenguajes.add(new OptionSelectBean<Integer>(8, "Groovy"));
		lstLenguajes.add(new OptionSelectBean<Integer>(9, "Java"));
		lstLenguajes.add(new OptionSelectBean<Integer>(10, "JavaScript"));
		lstLenguajes.add(new OptionSelectBean<Integer>(11, "Perl"));
		lstLenguajes.add(new OptionSelectBean<Integer>(12, "PHP"));
		lstLenguajes.add(new OptionSelectBean<Integer>(13, "Python"));
		lstLenguajes.add(new OptionSelectBean<Integer>(14, "Ruby"));
		lstLenguajes.add(new OptionSelectBean<Integer>(15, "Scala"));
		lstLenguajes.add(new OptionSelectBean<Integer>(16, "Scheme"));
		
		
		List<OptionSelectBean<Integer>> lstResult=new ArrayList<OptionSelectBean<Integer>>();
		
		// Recorro la lista de lenguajes
		for (OptionSelectBean<Integer> lenguaje: lstLenguajes) {
			if (lenguaje.getDescripcion().contains(term)) {
				lstResult.add(lenguaje);
			}
		}
		
		
		return lstResult;
	}

}
