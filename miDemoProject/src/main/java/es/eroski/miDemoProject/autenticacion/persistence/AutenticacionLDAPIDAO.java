/**
 * 
 */
package es.eroski.miDemoProject.autenticacion.persistence;

import es.eroski.miDemoProject._model.UserLdap;

/**
 * Inteface en la capa de persistencia para la la autenticacion de usuario por LDAP
 * @author BICUGUAL
 *
 */
public interface AutenticacionLDAPIDAO {

	/**
	 * Conecta con LDAP para validar al usuario
	 * @param username
	 * @param password
	 * @return
	 */
	public UserLdap findUserInLDAP(String username, String password)  throws Exception ;
}
