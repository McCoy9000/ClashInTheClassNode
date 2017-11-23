package es.eroski.miDemoProject.autenticacion.handlers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import es.eroski.miDemoProject._model.User;

/**
 * Tras el logout en la aplicacion se capturan en el User diferentes valores para contemplarlos 
 * en la gestion de sesion 
 * @author BICUGUAL
 *
 */
public class CustomSuccessLogoutHandler implements LogoutSuccessHandler{
	
	private static final Logger logger =Logger.getLogger(CustomSuccessLogoutHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onLogoutSuccess(HttpServletRequest response, 
							    HttpServletResponse request, 
							    Authentication authentication)
			throws IOException, ServletException {
		logger.info("Pasa tras logout ok");
		if ( authentication != null){
			 User user = (User)authentication.getPrincipal();
	 		 java.util.Date date= new java.util.Date();
	 		 user.setLogouttime(new Timestamp(date.getTime()));		
		}
		redirectStrategy.sendRedirect(response, request, "/logout/success");
		
	}

}
