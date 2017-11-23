<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<html lang="es-es">
    <head>
        <c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
        <link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/styles.css?version=${app.version}'/>" >
        <!-- Estilos de jquery-iu bootstrap para utilizar el jqgrid -->
		<link rel="stylesheet" href="<spring:url value='/WebContent/3PResources/jqGrid/css/ui.jqgrid-bootstrap.css?version=${app.version}'/>" />
		<link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/jqgrid-custom-styles.css?version=${app.version}'/>" />
		
		<title>
	  		<spring:eval expression="@appPropierties.getProperty('app.name')" />
		</title>
        
    </head>
	<body>
	    <c:import url="/WEB-INF/views/_resources/p02_menuCompleto.jsp" />
	
	    <div class="container">
	    	<div class="row">
				
				<c:import url="/WEB-INF/views/_resources/p03_showMessage.jsp" />
				
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Demo JqGrid - Buscador de usuarios</h3>
					</div>
					<div class="panel-body">
						<form id="demoform" class="form-horizontal">
							
							<!--  1º fila	 -->
							<div class="form-group ">
								<label class="col-md-2 control-label" for="username">Usuario</label>
								<div class="col-md-3">
									<input id="username" name="username" type="text" placeholder='Código de usuario' class="form-control search-input-text ">
								</div>
		
								<label class="col-md-2 control-label" for="pasajero">Descripción</label>
								<div class="col-md-4">
									<input id="descripcion" name="descripcion" type="text" placeholder='Descripción' class="form-control search-input-text " >
								</div>
		
							</div>
						    
					   	</form>
	
					</div><!-- /.panel content -->
					<div class="panel-footer">
					<!-- Icono de cargando -->
						
						<!-- Botonera -->
						<div class="pull-right">
							<button id="limpiarBtn" class="btn btn-default"><span class="fa fa-eraser"></span> Resetear</button>
							<button id="buscarBtn" class="btn btn-primary"><span class="fa fa-search"></span> Buscar</button>
						</div>
		        		<div class="clearfix"></div>
					</div><!-- /.panel footer -->
				</div>
				
				<!-- Lista Paginada -->
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Resultados de la búsqueda</h3>
					</div>
					<div class="panel-body">
						<table id="tabla" class="table-striped"></table>
						<div id="tablaPager"></div>
					</div>
				</div>
				
			</div>
			
	    </div>
	    
	    <!-- JSP´s componentes-->    
	    <script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=${app.version}'/>"></script>
		<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=${app.version}'/>"></script>
	    
		<!-- Utilidad para las llamas ajax -->
		<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/ajaxModule.js?version=${version}'/>"></script>
		
		<!-- Utilidad para mostrar mensajes -->
		<script src="<spring:url value='/WebContent/resources/js/_sharedModules/showMessageModule.js?version=${version}'/>"></script>
		
		<!-- jqGrid e internacionalizacion-->
		<script	src="<spring:url value='/WebContent/3PResources/jqGrid/js/jquery.jqGrid.min.js?version=${version}'/>"></script>
		<script	src="<spring:url value='/WebContent/3PResources/jqGrid/i18n/grid.locale-es.js?version=${version}'/>"></script>
		
		<!-- Js´s  -->
		<script	src="<spring:url value='/WebContent/resources/js/jqgrid/mainJqgridModule.js?version=${version}'/>"></script>
		<script	src="<spring:url value='/WebContent/resources/js/jqgrid/listJqgridModule.js?version=${version}'/>"></script>
		
	
	</body>

</html>