/**
 * 
 */
package es.eroski.miDemoProject.test.persistence.impl;

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

import es.eroski.miDemoProject._model.Carta;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.test.persistence.TestIDAO;
import es.eroski.miDemoProject.utilidades.QueryUtils;


/**
 * @author BICUGUAL
 *
 */
@Repository
public class TestDAO implements TestIDAO {

	@Autowired
	@Qualifier("jdbcTemplateSecurity")
	JdbcTemplate jdbcTemplate; 
	
	private static Logger logger = Logger.getLogger(TestDAO.class);
	

	public List<Carta> findAll() {
		StringBuffer query = new StringBuffer(
				"SELECT " + " NOMBRE, DESCRIPCION, NIVEL, PRECIO " + " FROM CARTA");
		
		List<Carta> lstItems = (List<Carta>) jdbcTemplate.query(query.toString(), new ItemMapper());

		return lstItems;
	}

	/*
	 * USER_DEMO DATATABLE 
	 */
	
	@Override
	public List<Usuario> findAll(DataTableRequest datatableFilters) {
		//TODO Implementar método
		return null;
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
		//TODO implementar método
		return null;
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
	private class ItemMapper implements RowMapper<Carta> {
	
		public Carta mapRow(ResultSet rs, int rowNum) throws SQLException {

			Carta carta=new Carta();
			
			carta.setNombre(rs.getString("NOMBRE"));
			carta.setDescripcion(rs.getString("DESCRIPCION"));
			carta.setNivel(rs.getInt("NIVEL"));
			carta.setPrecio(rs.getInt("PRECIO"));
			
			return carta;
		}
	}


}
