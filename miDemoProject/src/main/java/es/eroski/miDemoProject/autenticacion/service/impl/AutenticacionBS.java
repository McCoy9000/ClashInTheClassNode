/**
 * 
 */
package es.eroski.miDemoProject.autenticacion.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.eroski.miDemoProject._model.Permission;
import es.eroski.miDemoProject._model.Privilege;
import es.eroski.miDemoProject._model.Role;
import es.eroski.miDemoProject._model.User;
import es.eroski.miDemoProject._model.UserLdap;
import es.eroski.miDemoProject.autenticacion.persistence.AutenticacionIDAO;
import es.eroski.miDemoProject.autenticacion.persistence.AutenticacionLDAPIDAO;
import es.eroski.miDemoProject.autenticacion.service.AutenticacionIBS;
import es.eroski.miDemoProject.utilidades.Messages;

/**
 * Servicio que implementa la logica de autenticacion combinando la seguridad en
 * base de datos con el LDAP. Si el usuario existe en BD y tiene activado el
 * flag de LDAP, se valida contra LDAP. En caso contrario, se compara la
 * contraseñas introducida con la de bd.
 * 
 * @author BICUGUAL
 */
@Service
public class AutenticacionBS implements AutenticacionIBS {

	private static Logger logger = Logger.getLogger(AutenticacionBS.class);

	@Autowired
	AutenticacionIDAO autenticacion;

	@Autowired
	AutenticacionLDAPIDAO autenticacionLDAP;
	
	@Autowired
	Messages messages;
	
	
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userCode = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = null;
		user = loadUser(userCode, password);
    	
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		logger.info("Finaliza autenticacion OK");
		return new UsernamePasswordAuthenticationToken(user, password, authorities);

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);

	}

	/**
	 * Comprobando si existe, devuelve el usuario con los privilegios de la base
	 * de datos y comproban si presencia en el LDAP si procede.
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws UsernameNotFoundException
	 */
	private User loadUser(final String username,final String password) throws UsernameNotFoundException{
 		User user = autenticacion.getUserByUsername(username);
		    	
    	if (user == null) {
    		logger.warn("NO USER BD");
	    	throw new BadCredentialsException(messages.get("login.error.user"));
        }
    	else{
	    	//Si el usuario existe en BD comprobamos si hay que verificar al usuario en LDAP
    		if (user.getFlagldap().compareTo(1)==0)   { 
    			
    			try{
	    		    logger.info("USUARIO LDAP: "+username);
	    			UserLdap userLdap=autenticacionLDAP.findUserInLDAP(username, password);
	    			logger.info("VALIDACION LDAP OK: "+username);
    				//Cambio el username por el nombre y apellidos del LDAP
	    			user.setUsername(userLdap.getName().concat(" "+userLdap.getSurname()));
	    			
	    	   }catch(Exception e){
	    		   	logger.warn("VALIDACION LDAP NOK: "+username);
	    			logger.warn(e);
	    			logger.warn(e.getMessage());
	    			throw new BadCredentialsException(messages.get("login.error.ldap"));
	    	   }
		    }
    		//En caso de no tener que validar contra LDAP verificamos coincidencia de password
    		else{
    			logger.info("USUARIO BD: "+username);
    			try {
    				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    			if (!passwordEncoder.matches(password, user.getPassword())) {
		    		
	    				logger.warn("VALIDACION BD PASSWORD INCORRECTA: "+username);
		    			throw new Exception();
	    			}
	    			
				} catch (Exception e) {
					throw new BadCredentialsException(messages.get("login.error.pass"));
				}
	    		logger.info("VALIDACION BD OK: "+username);
	    	}

    		//Asigno los permisos
    		user.setAuthorities(this.getUserAuthorities(username));
    		
    		//Si el usuario no tiene ningun permiso, mostramos error de login
    		if (user.getAuthorities().isEmpty()) {
    			logger.warn("USUARIO SIN PERMISOS");
    			//throw new BadCredentialsException(messages.get("login.error.permisos"));
			}
    	}
		    	
	    return user;   	
 }

	/**
	 * Carga los privilegios del usuario para la aplicacion segun la bd de
	 * seguridad
	 * 
	 * @param username
	 * @return
	 */
	private List<Privilege> getUserAuthorities(String username) {
		
        List<Privilege> privileges = new ArrayList<Privilege>();
        List<Role> roles = new ArrayList<Role>();
        roles = autenticacion.getRolesByUser(username);
        for (Role rol : roles) {
               Privilege privilegerol = new Privilege();
               privilegerol.setName(rol.getRolename());
               privileges.add(privilegerol);
               List<Permission> permissions = new ArrayList<Permission>();
               permissions = autenticacion.getPermissionByRole(rol.getRolename());
               for (Permission permission : permissions) {
                      Privilege privilege = new Privilege();
                      privilege.setName(permission.getPermissionname() );
                      privileges.add(privilege);
               }

        }
        return privileges;

	}

	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
