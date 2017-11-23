package es.eroski.miDemoProject.sesiones.persistence.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Repository;

import es.eroski.miDemoProject._model.TableColumn;
import es.eroski.miDemoProject._model.DataTableRequest;
import es.eroski.miDemoProject._model.User;
import es.eroski.miDemoProject.sesiones.persistence.SessionIDAO;

@Repository
public class SessionDAO implements SessionIDAO {
	
	private static Logger logger = Logger.getLogger(SessionDAO.class);
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	@Override
	public List<SessionInformation> findAll(DataTableRequest datatableFilters) {
		List<SessionInformation> totalSessions = new ArrayList<>(); 	
		List<SessionInformation> finalSessions = new ArrayList<>();
		String order=(datatableFilters.getOrderColumn()!=null && !datatableFilters.getOrderColumn().isEmpty() && !"0".equals(datatableFilters.getOrderColumn())) ? " ORDER BY "+datatableFilters.getOrderColumn() : "";
		String orderAscDsc=!order.isEmpty() ? " "+datatableFilters.getAscDsc() : "";
		long inicio = datatableFilters.getStart();
		long last = datatableFilters.getStart()+datatableFilters.getLength()+1;
		long contador = 1;
		//filtraPorColumnas(datatableFilters);
		for(Object principal : sessionRegistry.getAllPrincipals()) {
			totalSessions.addAll(sessionRegistry.getAllSessions(principal, true));
			//
			//totalSessions = filtraPorColumnas(datatableFilters,totalSessions);
			//
			//if ( (contador > inicio) && (contador < last) ){
			//	totalSessions.addAll(sessionRegistry.getAllSessions(principal, true));
			//}
			
		}
		totalSessions = filtraPorColumnas(datatableFilters,totalSessions);
		Collections.sort(totalSessions, new ComparadorSessiones(order,orderAscDsc));
		for ( SessionInformation session : totalSessions ){
			if ( (contador > inicio) && (contador < last) ){
				finalSessions.add(session);
			}
			contador ++;
		}
		
		return finalSessions;
	}

	@Override
	public Long findCount(DataTableRequest datatableFilters) {
		String order=(datatableFilters.getOrderColumn()!=null && !datatableFilters.getOrderColumn().isEmpty() && !"0".equals(datatableFilters.getOrderColumn())) ? " ORDER BY "+datatableFilters.getOrderColumn() : "";
		String orderAscDsc=!order.isEmpty() ? " "+datatableFilters.getAscDsc() : "";
		List<SessionInformation> totalSessions = new ArrayList<>(); 
		for(Object principal : sessionRegistry.getAllPrincipals()) {
			  totalSessions.addAll(sessionRegistry.getAllSessions(principal, true));
		  }
		totalSessions = filtraPorColumnas(datatableFilters,totalSessions);
		Collections.sort(totalSessions, new ComparadorSessiones(order,orderAscDsc));
		return (long) totalSessions.size();
	}

    private class ComparadorSessiones implements Comparator<SessionInformation> {
        
    	private String order;
    	private String orderAscDsc;
    	
    
    	public ComparadorSessiones(String order,String orderAscDsc){
    			this.setOrder(order.trim());
    			this.setOrderAscDsc(orderAscDsc.trim());;
    			
    	}
    	
    	@Override
        public int compare(SessionInformation arg0, SessionInformation arg1) {
        	// LOGINTIME Y DEFECTO
    		if ( (this.getOrder().equals("")) ||  (this.getOrder().equals("ORDER BY LOGINTIME")) ){
	    		   if ( (((User)arg0.getPrincipal()).getLogintime()).before(((User)arg1.getPrincipal()).getLogintime()) ){
	                   if ( (this.getOrderAscDsc().equals("")) ||  (this.getOrderAscDsc().equals("desc")) ){ 
	                	   return 1;
	                   }else{
	                	   return -1;  
	                   }
	    		   }else if ((((User)arg0.getPrincipal()).getLogintime()).after(((User)arg1.getPrincipal()).getLogintime())){
	    			   if ( (this.getOrderAscDsc().equals("")) ||  (this.getOrderAscDsc().equals("desc")) ){ 
	                	   return -1;
	                   }else{
	                	   return 1;  
	                   }             
	    		   }
	    		   return 0; 
    		}else{
    			 	int aux = 1;
    			 	if ( this.getOrderAscDsc().equals("desc")  ){
    			 		aux = -1;
    			 	}else{
    			 		aux = 1;
    			 	}
		    		   if ( (this.getOrder().equals("ORDER BY LOGOUTTIME")) ){
		    			   Timestamp auxf = new Timestamp(0);
		    			   boolean reset1 = false;
		    			   boolean reset2 = false;
		    			   if ( (((User)arg0.getPrincipal()).getLogouttime() == null ) ){
		    				   ((User)arg0.getPrincipal()).setLogouttime(auxf);
		    				   reset1 = true;
		    			   }
		    			   if ( (((User)arg1.getPrincipal()).getLogouttime() == null ) ){
		    				   ((User)arg1.getPrincipal()).setLogouttime(auxf);
		    				   reset2 = true;
		    			   }
	    				   if ( (((User)arg0.getPrincipal()).getLogouttime()).before(((User)arg1.getPrincipal()).getLogouttime()) ){
		                       if ( (this.getOrderAscDsc().equals("")) ||  (this.getOrderAscDsc().equals("desc")) ){ 
		                    	   if ( reset1 ) ((User)arg0.getPrincipal()).setLogouttime(null);
				    			   if ( reset2 ) ((User)arg1.getPrincipal()).setLogouttime(null);
		                    	   return 1;
		                       }else{
		                    	   if ( reset1 ) ((User)arg0.getPrincipal()).setLogouttime(null);
				    			   if ( reset2 ) ((User)arg1.getPrincipal()).setLogouttime(null);
		                    	   return -1;  
		                       }
		        		   }else if ((((User)arg0.getPrincipal()).getLogouttime()).after(((User)arg1.getPrincipal()).getLogouttime())){
		        			   if ( (this.getOrderAscDsc().equals("")) ||  (this.getOrderAscDsc().equals("desc")) ){ 
		        				   if ( reset1 ) ((User)arg0.getPrincipal()).setLogouttime(null);
				    			   if ( reset2 ) ((User)arg1.getPrincipal()).setLogouttime(null);
		        				   return -1;
		                       }else{
		                    	   if ( reset1 ) ((User)arg0.getPrincipal()).setLogouttime(null);
				    			   if ( reset2 ) ((User)arg1.getPrincipal()).setLogouttime(null);
		                    	   return 1;  
		                       }             
		        		   }
		    			   if ( reset1 ) ((User)arg0.getPrincipal()).setLogouttime(null);
		    			   if ( reset2 ) ((User)arg1.getPrincipal()).setLogouttime(null);
		    			   return 0;
		    		   }else{
		    			   if ( (this.getOrder().equals("ORDER BY IP")) ){
		    				 	return(((User)arg0.getPrincipal()).getIp()).compareTo(((User)arg1.getPrincipal()).getIp())*aux;
		    			   }else{
		    				   if ( (this.getOrder().equals("ORDER BY SESSIONID"))){
		    					   return(arg0.getSessionId()).compareTo(arg1.getSessionId())*aux;
		    				   }else{
		    					    if ( (this.getOrder().equals("ORDER BY USERNAME"))){
		    					    	return(((User)arg0.getPrincipal()).getUsername()).compareTo(((User)arg1.getPrincipal()).getUsername())*aux;
		    					    }else{
		    					    	 if ( (this.getOrder().equals("ORDER BY USERCODE")) ){
		    					    		 return(((User)arg0.getPrincipal()).getUserCode()).compareTo(((User)arg1.getPrincipal()).getUserCode())*aux;
		    					    	 }else{
		    					    		 return 0;
		    					    	 }
		    					    	
		    					    }
		    				   }
		    				  
		    			   }   
		    		   }  	
    		}
              
        }
        
    	
    	
		
		public String getOrder() {
			return order;
		}

		
		public void setOrder(String order) {
			this.order = order;
		}

	
		public String getOrderAscDsc() {
			return orderAscDsc;
		}

	
		public void setOrderAscDsc(String orderAscDsc) {
			this.orderAscDsc = orderAscDsc;
		}
    }

    
	@Override
	public Long ExpirarSesion(String sesion) {
		logger.info("Solicitud expiracion sesion id : " + sesion);
		long result = 0;
		for (Object principal : sessionRegistry.getAllPrincipals()) {
			User user = (User) principal;
        	for( SessionInformation information : sessionRegistry.getAllSessions(principal,false)){
				if ( (information.getSessionId().equals(sesion))){
					 java.util.Date date= new java.util.Date();
					 user.setLogouttime(new Timestamp(date.getTime()));
					 information.expireNow();
					 result = 1;
				}
			}
         }
		 return result;
	}
	//
	public List<SessionInformation> filtraPorColumnas(DataTableRequest datatableFilters,List<SessionInformation> list){
		List<SessionInformation> lista = new ArrayList<>();
		//
		for ( SessionInformation session : list ){
			boolean encontrado = true;
			for (TableColumn dtc:datatableFilters.getLstColumns()){
				if (dtc.getSearchable() && !dtc.getSearchValue().isEmpty()){
					if ( dtc.getName().equals("USERCODE")  ){
						if ( !( ((User)session.getPrincipal()).getUserCode().contains(dtc.getSearchValue()))){
							encontrado = false;
						}
					}else{
						if ( dtc.getName().equals("USERNAME")  ){
							if ( !(((User)session.getPrincipal()).getUsername().contains(dtc.getSearchValue()))){
								encontrado = false;
							}
						}else{
							if ( dtc.getName().equals("SESSIONID")  ){
								if (!(session.getSessionId().contains(dtc.getSearchValue()))){
									encontrado = false;
								}
							}else{
								 if ( dtc.getName().equals("IP")  ){
									 if ( !(((User)session.getPrincipal()).getIp().contains(dtc.getSearchValue()))){
											encontrado = false;
									}
									 
								 }else{
									 if ( dtc.getName().equals("LOGINTIME")  ){
										 if ( !( ((User)session.getPrincipal()).getLogintime().toString().contains(dtc.getSearchValue()))){
												encontrado = false;
										  }
									 }else{
										 if ( !( ((User)session.getPrincipal()).getLogintime().toString().contains(dtc.getSearchValue()))){
												encontrado = false;
										 }
										 
									 }
								 }
							}
						}
					}
				}
			}
			//
			if ( encontrado ){
				//logger.info("Añadimos a lista");
				lista.add(session);
			}
		}
		return lista;
	}
	//
	
}
