package es.eroski.miDemoProject.sesiones.persistence;

import java.util.List;

import org.springframework.security.core.session.SessionInformation;

import es.eroski.miDemoProject._model.DataTableRequest;

/**
 * Capa de persistencia para la gestion de sessiones
 * @author BICUGUAL
 *
 */
public interface SessionIDAO {
	/**
	 * Obtiene una lista de sesiones en base a los parametros recibidos
	 * @param datatableFilters
	 * @return
	 */
	public List<SessionInformation> findAll(DataTableRequest datatableFilters);
	
	/**
	 * Obtiene el numero de sessiones en base a los parametros recibidos
	 * @param datatableFilters
	 * @return
	 */
	public Long findCount(DataTableRequest datatableFilters);
	
	/**
	 * Modifica la sesion pasada como parametro dandole valores de finalizacion.
	 * @param sesion
	 * @return
	 */
	public Long ExpirarSesion(String sesion);

}