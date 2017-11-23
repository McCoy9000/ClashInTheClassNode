<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<!--[if IE 9]><html lang="en-us" class="ie9 login-pf"><![endif]-->
<!--[if gt IE 9]><!-->
<html lang="es-es">
<!--<![endif]-->

    <head>
    	<c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
		<link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/styles.css?version=${app.version}'/>" >
		
		<title>
	  		<spring:eval expression="@appPropierties.getProperty('app.name')" />
		</title>
		
  	</head>
	<body>
		<c:import url="/WEB-INF/views/_resources/p02_menuCompleto.jsp" />
		
		<div class="container">
			Página de  Menú completo
		</div>
		<!-- class container -->

		<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=${app.version}'/>"></script>
		<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=${app.version}'/>"></script>
		<script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=${app.version}'/>"></script>

	</body>
</html>