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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import es.eroski.miDemoProject._model.User;

/**
 * Tras la la autenticacion en la aplicacion se capturan en el User diferentes valores para contemplarlos 
 * en la gestion de sesion 
 * @author BICUGUAL
 *
 */
public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {

	private static final Logger logger =Logger.getLogger(CustomAuthenticationSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
			logger.info("Pasa tras login ok");
			if ( authentication != null){
				User user = (User)authentication.getPrincipal();
				user.setIp(request.getRemoteAddr());
				java.util.Date date= new java.util.Date();
				user.setLogintime(new Timestamp(date.getTime()));			
			}
			redirectStrategy.sendRedirect(request, response, "/login/success");
	}

}
