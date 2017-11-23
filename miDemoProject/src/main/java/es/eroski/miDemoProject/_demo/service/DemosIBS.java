/**
 * 
 */
package es.eroski.miDemoProject._demo.service;

import java.util.List;

import es.eroski.miDemoProject._model.GenericExcelReport;
import es.eroski.miDemoProject._model.JqgridPage;
import es.eroski.miDemoProject._model.JqgridPaginationRequest;
import es.eroski.miDemoProject._model.OptionSelectBean;
import es.eroski.miDemoProject._model.TableExportForm;
import es.eroski.miDemoProject._model.Usuario;
import es.eroski.miDemoProject.generic.service.GenericIBS;

/**
 * Implementacion para la capa de servicio del modulo de demos
 * @author BICUGUAL
 *
 */
public interface DemosIBS extends GenericIBS<Usuario> {

	/**
	 * Devuleve una lista de areas para ser utilizada en un select o combo en la jsp formulario
	 * @return
	 */
	public List<OptionSelectBean<Integer>> getLstAreas();
	
	/**
	 * Devuleve una lista de responsable para cargar un select o combo en la jsp formulario
	 * @param idArea
	 * @return
	 */
	public List<OptionSelectBean<Integer>> getLstResponsablesByIdArea(Integer idArea);
	
	
	
	/**
	 * Devuleve una lista de lenguajes coincidentes con el termino introducido en el input
	 * @param term
	 * @return
	 */
	public List<OptionSelectBean<Integer>> getLstLenguajes(String term);
	
	
	
	/**
	 * Devuelve una representacion de pagina para el componente de tabla jqgrid con el resultado 
	 * de la busqueda de usuarios lanzada.
	 * @param jqGridRequest
	 * @param username
	 * @param descripcion
	 * @param flagldap
	 * @return
	 * @throws Exception
	 */
	public JqgridPage<Usuario> getJGResponseItems(JqgridPaginationRequest jqGridRequest, String username, String descripcion, Integer flagldap) throws Exception;
	
	/**
	 * Modifica tan solo la descripcion del registro
	 * @param usuario
	 * @throws Exception
	 */
	public void updateJqGridUsuario(Usuario usuario) throws Exception;
	
	/**
	 * En base a los datos recibidos del formulario, realiza una consulta y utiliza la implementacion generica de exportacion
	 * para crear un excel con el resultado de la consulta.
	 * @param form
	 * @param response
	 * @throws Exception 
	 */
	public GenericExcelReport getItemstoExport(TableExportForm form) throws Exception;
}
