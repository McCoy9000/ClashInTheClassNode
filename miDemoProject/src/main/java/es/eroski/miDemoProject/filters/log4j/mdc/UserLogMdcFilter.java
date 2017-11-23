package es.eroski.miDemoProject.filters.log4j.mdc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import es.eroski.miDemoProject._model.User;

/**
 *Este filtro permite introducir información adicional en el log4j.
 * Lo que accemos es poner la ip origen y el usuario que realiza la opcion
 * @author BICUGUAL
 *
 */
public class UserLogMdcFilter implements Filter {
	 
	  @Override
	  public void init(FilterConfig filterConfig) throws ServletException {
	  }
	 
	  @Override
	  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
	          throws IOException, ServletException {
	    Authentication authentication =
	        SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null) {
	      MDC.put("iporigen",req.getRemoteAddr());
	      if (  authentication.getPrincipal() instanceof User ){
	    	  MDC.put("userLogin", ((User)authentication.getPrincipal()).getUserCode() );
	      }else{
	    	  MDC.put("userLogin", authentication.getPrincipal());
	      }
	    }
	    try {
	      chain.doFilter(req, resp);
	    } finally {
	      if (authentication != null) {
	        MDC.remove("userLogin");
	        MDC.remove("iporigen");
	      }
	    }
	  }
	 
	  @Override
	  public void destroy() {
	  }
	 
	}