/**
 * 
 */
package es.eroski.miDemoProject._model;

import java.io.Serializable;

/**
 * Clase generica utilizada para la representacion de clave y valor en la carga de datos de los 
 * options para los selects.
 * @author BICUGUAL
 */
@SuppressWarnings("serial")
public class OptionSelectBean <T> implements Serializable {

	public T codigo;
	public String descripcion;
	
	public OptionSelectBean() {
		super();
	}

	public OptionSelectBean(T codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public T getCodigo() {
		return codigo;
	}
	public void setCodigo(T codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
