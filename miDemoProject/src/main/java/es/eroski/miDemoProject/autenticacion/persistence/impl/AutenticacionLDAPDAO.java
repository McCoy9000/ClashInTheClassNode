/**
 * 
 */
package es.eroski.miDemoProject.autenticacion.persistence.impl;

import javax.naming.Name;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.CollectingAuthenticationErrorCallback;
import org.springframework.stereotype.Service;

import es.eroski.miDemoProject._model.UserLdap;
import es.eroski.miDemoProject.autenticacion.persistence.AutenticacionLDAPIDAO;

/**
 * Implementacion de la capa de persistencia para la Autenticacion en LDAP 
 * @author BICUGUAL
 */
@Service
public class AutenticacionLDAPDAO implements AutenticacionLDAPIDAO {

	private static final Logger logger=Logger.getLogger(AutenticacionLDAPDAO.class);
	
	@Autowired
	LdapTemplate ldapTemplate;

		
	@Override 
	public  UserLdap findUserInLDAP(String username, String password) throws Exception{ 
	
	      CollectingAuthenticationErrorCallback errorCallback = new CollectingAuthenticationErrorCallback();
    	  boolean authenticated = ldapTemplate.authenticate("", "(uid="+username+")", password ,errorCallback);
	      
	      if (authenticated){
	    	  logger.info("USER: "+username+" LDAP OK" );
	    	  Name dn = buildDn(username);
	    	  return (UserLdap) ldapTemplate.lookup(dn,  new UserLdapContextMapper());
	      }else{
	    	  Exception error = errorCallback.getError();
	    	  logger.info("USER: "+username+" error:"+error.getMessage() );
	    	  throw error;
	      }
	   }

	/**
	 * Mapper para el Usuario de LDAP
	 */
	private static class UserLdapContextMapper implements ContextMapper {
		public Object mapFromContext(Object ctx) {
			DirContextAdapter context = (DirContextAdapter) ctx;
			UserLdap userLdap = new UserLdap();
			userLdap.setCode(context.getStringAttribute("cn"));
			userLdap.setName(context.getStringAttribute("givenName"));
			userLdap.setSurname(context.getStringAttribute("sn"));
			return userLdap;
		}
	}

	/**
	 * Devuelve el DistinguishedName con el user cargado
	 * @param user
	 * @return
	 */
	protected static Name buildDn(String user) {
		DistinguishedName dn = new DistinguishedName();
		dn.add("cn", user);
		return dn;
	}
	
}
