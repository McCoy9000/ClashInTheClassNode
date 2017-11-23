/**
 * 
 */
package es.eroski.miDemoProject.generic.persistence;

import java.util.List;

import es.eroski.miDemoProject._model.DataTableRequest;

/**
 * Metodos usualmente comunes para cada entidad o items en la capa de persistencia
 * @author BICUGUAL
 */
public interface GenericDAO <T>{

	/**
	 * Obtiene la lista de registros mapeada con su correspondiente objeto de modelo
	 * @param pagination
	 * @return
	 */
	public List<T> findAll(DataTableRequest datatableFilters);
	
	/**
	 * Obtine el numero total de registros encontrados
	 * @return
	 */
	public Long findCount(DataTableRequest datatableFilters);
	
	/**
	 * Obtiene un registro de dase de datos en base al id
	 * @param id.
	 * @return
	 */
	public T getItemById(Object id);
	
	
	/**
	 * Crea un nuevo registro en base de datos.
	 * @param item
	 */
	public void createItem(T item);
	
	/**
	 * Modifica un registro en base de datos
	 * @param item
	 */
	public void updateItem(T item);
	
	/**
	 * Elimina un registro de la base de datos en base a su id
	 * @param id
	 */
	public void deleteItem(Object id);

	
	
}
