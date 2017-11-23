package es.eroski.miDemoProject.utilidades.service;

import java.util.List;

import es.eroski.miDemoProject._model.JqgridPage;



/**
 * Intefaz para la gestion de paginacion del componente js jqGrid 
 * @author BICUGUAL
 *
 * @param <T>
 */
public interface JqgridPaginationIBS<T> {

	public JqgridPage<T> paginate(JqgridPage<T> resource, List<T> list, int max, int records, int page);
	
	public abstract int getPaginationLimit(int size, int max, int page);
	
	public int getTotalPages(int records, int max);

	
	
}