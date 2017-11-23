<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<html lang="es-es">
    <head>
        <c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
        <link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/styles.css?version=1.0'/>" >
        <!-- Estilos de jquery-iu bootstrap para utilizar el autocomplete -->
		<link rel="stylesheet" href="<spring:url value='/WebContent/3PResources/jquery-ui-boostrap/css/jquery-ui.css?version=1.0'/>" />
		
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
					<h3 class="panel-title">Demo autocomplete</h3>
				</div>
				<div class="panel-body">
					<form id="demoform" class="form-horizontal">
						<label class="col-md-2 control-label" for="grupo">Selecciona un lenguaje:</label>
						<div class="col-md-3">
							<input id="lenguaje" name="lenguaje" type="text" placeholder='Introduce el nombre de un lenguaje' class="form-control" data-codLenguaje="" />
						</div>
						
						<div class="col-md-offset-3 col-md-4">
					      <h4>
					        Listado de lenguajes
					      </h4>
					      
					      <ol>
					        <li>ActionScript</li>
					        <li>AppleScript</li>
					        <li>Asp</li>
					        <li>BASIC</li>
					        <li>C</li>
					        <li>COBOL</li>
					        <li>Fortran</li>
					        <li>Groovy</li>
					        <li>Java</li>
					        <li>JavaScript</li>
					        <li>Perl</li>
					        <li>PHP</li>
					        <li>Python</li>
					        <li>Ruby</li>
					        <li>Scala</li>
					        <li>Scheme</li>
					      </ol>
					    </div>
					    
				   	</form>

				</div><!-- /.panel content -->
				<div class="panel-footer">
				<!-- Icono de cargando -->
					<div class="pull-left spinner hide"></div>
					<!-- Button -->
					<div class="pull-right">
						<button id="aceptarBtn" class="btn btn-primary">Ver selección</button>
					</div>
	
	        		<div class="clearfix"></div>
				</div><!-- /.panel footer -->
			</div>
		</div>
		
    </div>
    
    <!-- JSP´s componentes-->    
    <script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=1.0'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=1.0'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=1.0'/>"></script>
    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=1.0'/>"></script>
    
	<!-- Utilidad para las llamas ajax -->
	<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/ajaxModule.js?version=1.0'/>"></script>
	<!-- Utilidad para mostrar mensajes -->
	<script src="<spring:url value='/WebContent/resources/js/_sharedModules/showMessageModule.js?version=1.0'/>"></script>

    <script src="<spring:url value='/WebContent/3PResources/jquery-ui-boostrap/js/jquery-ui.min.js?version=1.0'/>"></script>

	<script src="<spring:url value='/WebContent/resources/js/demos/mainAutocompleteModule.js?version=1.0'/>"></script>

</body>

</html>