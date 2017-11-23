/**
 * 
 */
package es.eroski.miDemoProject.generic.service;

import java.util.List;

import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.DataTableResponse;

/**
 * Metodos usualmente comunes para cada entidad o items en la capa de servicio
 * @author BICUGUAL
 */
public interface GenericIBS <T>{
	
	/**
	 * Obtiene los datos necesarios para renderizar los registros solicitados por la tabla
	 * para el componente datatable
	 * @param datatableFilter
	 * @return
	 */
	public DataTableResponse<T> getDTResponseItems(DataTableRequest datatableFilter);
	
	/**
	 * Recupera un registro de base de datos
	 * @param id
	 * @return
	 */
	public T getItem(Object id);
	
	
	/**
	 * Modifica el registro en base de datos
	 * @param item
	 * @throws Exception 
	 */
	public void updateItem(T item) throws Exception;
	
	/**
	 * Guarda en base de datos el articulo
	 * @param item
	 * @throws Exception 
	 */
	public void createItem(T item) throws Exception;
	
	/**
	 * Elimina registros de base de datos
	 * @param lstIds. Lista de ids de los registros a eliminar
	 */
	public void deleteItems(List<Object> lstIds);
	
	
}
