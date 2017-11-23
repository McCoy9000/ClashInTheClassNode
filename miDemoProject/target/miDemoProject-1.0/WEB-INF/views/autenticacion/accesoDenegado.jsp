<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>
<!DOCTYPE html>
<!--[if IE 9]><html lang="en-us" class="ie9 login-pf"><![endif]-->
<!--[if gt IE 9]><!-->
<html lang="es-es">
<!--<![endif]-->
	<head>
    	<c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
    	
    	<title>
	  		<spring:eval expression="@appPropierties.getProperty('app.name')" />
		</title>
  	</head>
<body>
	<c:import url="/WEB-INF/views/_resources/p02_menuCompleto.jsp" />
		<div class="blank-slate-pf">
		  <div class="blank-slate-pf-icon">
		    <span class="fa fa-minus-circle" aria-hidden="true"></span>
		  </div>
		  <h1>
		   	<spring:message code='acceso.denegado.h1'/>
		  </h1>
		  <p>
		    <spring:message code='acceso.denegado.p1'/>
		  </p>
		  <p>
		  	<spring:message code='acceso.denegado.p2'/>
		  </p>
		</div>
		
		<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
		<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=1.0'/>"></script>
	    
	    
	</body>
</html>