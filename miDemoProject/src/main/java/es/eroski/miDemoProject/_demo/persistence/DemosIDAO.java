package es.eroski.miDemoProject._demo.persistence;

import java.util.List;

import es.eroski.miDemoProject._model.JqgridPaginationRequest;
import es.eroski.miDemoProject._model.OptionSelectBean;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.generic.persistence.GenericDAO;

/**
 * @author BICUGUAL
 *
 */
public interface DemosIDAO extends GenericDAO<Usuario>{

	/**
	 * Emulo la carga de areas desde bd
	 * @return
	 */
	public List<OptionSelectBean<Integer>> getLstAreas();	
	
	/**
	 * Emulo la carga de areas desde bd
	 * @return
	 */
	public List<OptionSelectBean<Integer>> getLstResponsablesByIdArea(Integer idArea);
	
	/**
	 * Emulo la carga de areas desde bd
	 * @return
	 */
	public List<OptionSelectBean<Integer>> getLstLenguajes(String term);
	
	/**
	 * Obtiene la lista de registros mapeada con su correspondiente objeto de modelo
	 * @param jqGridRequest
	 * @param username
	 * @param descripcion
	 * @param flagldap
	 * @return
	 */
	public List<Usuario> findAllJG(JqgridPaginationRequest jqGridRequest, String username, String descripcion, Integer flagldap);
	
	
	/**
	 * Obtine el numero total de registros encontrados
	 * @param jqGridRequest
	 * @param username
	 * @param descripcion
	 * @param flagldap
	 * @return
	 */
	public Long findCountJG(JqgridPaginationRequest jqGridRequest, String username, String descripcion, Integer flagldap);
	
}
