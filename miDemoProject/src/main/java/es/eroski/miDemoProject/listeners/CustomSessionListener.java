package es.eroski.miDemoProject.listeners;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.eroski.miDemoProject._model.User;

public class CustomSessionListener implements HttpSessionListener {
  private static Logger logger = Logger.getLogger(CustomSessionListener.class);  
  @Override
  public void sessionCreated(HttpSessionEvent arg0) {
	  
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent arg0) {
	  HttpSession session = arg0.getSession();
	  ApplicationContext ctx =
      WebApplicationContextUtils.
      getWebApplicationContext(session.getServletContext());
      SessionRegistry sessionRegistry =
      (SessionRegistry) ctx.getBean("sessionRegistry");
      //logger.info("Session httpsessionevent" + session.getId());
      for(Object principal : sessionRegistry.getAllPrincipals()) {	 
      		for ( SessionInformation si : sessionRegistry.getAllSessions(principal, true)){
	  			//logger.info("Session infomation " +si.getSessionId());
  				User user =  (User)si.getPrincipal();
  				//logger.info(user.getUserCode());
  				if ( session.getId().equals(si.getSessionId()) ){
  					logger.info("Marcamos fecha logout por session expired para " + user.getUserCode());
  					java.util.Date date= new java.util.Date();
  					user.setLogouttime(new Timestamp(date.getTime()));	
  				}
      		}
      }
	  	
  }
}