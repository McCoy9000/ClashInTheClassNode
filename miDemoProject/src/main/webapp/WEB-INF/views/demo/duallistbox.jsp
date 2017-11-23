<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<html lang="es-es">
    <head>
        <c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
        <link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/styles.css?version=${app.version}'/>" >
        <link rel="stylesheet" href="<spring:url value='/WebContent/3PResources/duallistbox/css/bootstrap-duallistbox.min.css?version=${app.version}'/>" >
        
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
					<h3 class="panel-title">demo duallistbox</h3>
				</div>
				<div class="panel-body">
					<form id="demoform" class="form-horizontal">
						<div class="col-md-12">
							<select id="duallistbox" multiple="multiple" size="10">
						      <option value="option1">Option 1</option>
						      <option value="option2">Option 2</option>
						      <option value="option3" selected="selected">Option 3</option>
						      <option value="option4">Option 4</option>
						      <option value="option5">Option 5</option>
						      <option value="option6" selected="selected">Option 6</option>
						      <option value="option7">Option 7</option>
						      <option value="option8">Option 8</option>
						      <option value="option9">Option 9</option>
						      <option value="option10">Option 10</option>
						    </select>
					    </div>
				   	</form>
	
				</div><!-- /.panel content -->
				<div class="panel-footer">
				<!-- Icono de cargando -->
					<div class="pull-left spinner hide"></div>
					<!-- Button -->
					<div class="pull-right">
						<button id="aceptarBtn" class="btn btn-primary">Aceptar</button>
					</div>
	
	        		<div class="clearfix"></div>
				</div><!-- /.panel footer -->
			</div>
		</div>
		
    </div>
    
    <!-- JSP´s componentes-->    
    <script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/duallistbox/js/jquery.bootstrap-duallistbox.min.js?version=${app.version}'/>"></script>

	<script src="<spring:url value='/WebContent/resources/js/demos/mainDuallistboxModule.js?version=${app.version}'/>"></script>

</body>

</html>