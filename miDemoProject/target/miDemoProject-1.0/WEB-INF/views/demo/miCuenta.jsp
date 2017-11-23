<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<!--[if IE 9]><html lang="en-us" class="ie9 login-pf"><![endif]-->
<!--[if gt IE 9]><!-->
<html lang="es-es">
<!--<![endif]-->

    <head>
    	<c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
		<link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/styles.css?version=1.0'/>" >
		
		<title>
	  		<spring:eval expression="@appPropierties.getProperty('app.name')" />
		</title>
		
  	</head>
	<body>
		<c:import url="/WEB-INF/views/_resources/p02_menuCompleto.jsp" />
		
		<div class="container">
			<div class="row">	
				<div class="panel panel-info">
				  <div class="panel-heading">
				    <h3 class="panel-title">Mi Cuenta</h3>
				  </div>
				  <div class="panel-body">
				  
				  	<span class="fa fa-user"></span><sec:authentication	property="principal.username" /> 
				  	
					<sec:authentication property="principal.authorities" var="authorities" />
					
					<table	class="table table-hover table-bordered table-striped text-nowrap floatThead">
						<thead>
							<tr>
								<td>
									Lista de permisos
								 </td>
								
							</tr>
						</thead>
<!-- 						<thead> -->
<!-- 							<tr> -->
<!-- 								<td>Permisos</td> -->
<!-- 							</tr> -->
<!-- 						</thead> -->
						<tbody>
							<c:forEach items="${authorities}" var="authority" varStatus="vs">
								<tr>
									<td><span class="fa fa-eye"> </span>
										${authority.authority}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div><!-- /.panel content -->

				  
				</div>
			</div>
			<!-- /.row -->  
		</div>

		<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
		<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=1.0'/>"></script>

	</body>
</html>