/**
 * 
 */
package es.eroski.miDemoProject._model;

/**
 * Modelo para la representacion de usuarios
 * @author BICUGUAL
 */
public class Usuario {

	private String username;
	private String password;
	private String descripcion;
	private Integer flagldap;
	
	public Usuario() {
		super();
	}
	
	
	public Usuario(String username, String password, String descripcion, Integer flagldap) {
		super();
		this.username = username;
		this.password = password;
		this.descripcion = descripcion;
		this.flagldap = flagldap;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getFlagldap() {
		return flagldap;
	}
	public void setFlagldap(Integer flagldap) {
		this.flagldap = flagldap;
	}
	
}
