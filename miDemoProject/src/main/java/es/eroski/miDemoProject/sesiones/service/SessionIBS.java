package es.eroski.miDemoProject.sesiones.service;

import org.springframework.security.core.session.SessionInformation;

import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;

/**
 * Interfaz para la gestion de sesiones
 * @author BICUGUAL
 *
 */
public interface SessionIBS {
	/**
	 * Obtiene la lista de sesiones para ser mostrada mediante el componente datatable
	 * @param datatableFilter
	 * @return
	 */
	public DataTableResponse<SessionInformation> getDTResponseSessions(DataTableRequest datatableFilter);
	
	/**
	 * Expira la sesion
	 * @param sesion
	 * @return
	 */
	public Long expirarSession(String sesion);

}
