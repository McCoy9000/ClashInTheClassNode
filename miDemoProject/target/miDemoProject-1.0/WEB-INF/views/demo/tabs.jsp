<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<html lang="es-es">
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

			<ul class="nav nav-tabs">
				<li class="active">
					<a data-toggle="tab" href="#tab1">Pestaña principal</a>
				</li>
				<li>
					<a data-toggle="tab" href="#tab2">¿Qué es el lorem ipsum?</a>
				</li>
				<li>
					<a data-toggle="tab" href="#tab3">Ejemplo lorem ipsum</a>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">Pestaña desplegable <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li>
							<a data-toggle="tab" href="#tab4-op1">Opción 1</a>
						</li>
						<li>
							<a data-toggle="tab" href="#tab4-op2">Opción 2</a>
						</li>
					</ul>
				</li>
			</ul>
			<div class="tab-content">
				<div id="tab1" class="tab-pane fade in active">
				    <h3>Pestaña principal</h3>
				    <p>Este es un ejemplo de utilización de pestañas (tabs) de un solo nivel con menú deplegable (dropdown).</p>
				    <p><strong>NOTA:</strong>Este ejemplo se presenta dentro de un panel.</p>
				</div>
				<div id="tab2" class="tab-pane fade">
					<h3>¿Qué es el lorem ipsum?</h3>
					<p>El lorem ipsum, un texto de relleno muy utilizado en diseño gráfico</p>
				</div>
				<div id="tab3" class="tab-pane fade">
					<h3>Ejemplo lorem ipsum</h3>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit...</p>
				</div>
				<div id="tab4-op1" class="tab-pane fade">
					<h3>Pestaña desplegable - Opción 1</h3>
					<p>Ejemplo deplegable para la OPCIÓN 1</p>
				</div>
				<div id="tab4-op2" class="tab-pane fade">
					<h3>Pestaña desplegable - Opción 2</h3>
					<p>Ejemplo deplegable para la OPCIÓN 2</p>
				</div>
			</div>

		</div>    
    </div>
    
    <!-- JSP´s componentes-->    
    <script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=1.0'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=1.0'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=1.0'/>"></script>
    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=1.0'/>"></script>
           
</body>

</html>