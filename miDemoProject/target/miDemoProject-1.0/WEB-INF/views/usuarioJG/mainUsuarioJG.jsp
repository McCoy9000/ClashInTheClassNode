<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<html lang="es-es">
    <head>
        <c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
        <link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/styles.css?version=1.0'/>" >
        <!-- Estilos de jquery-iu bootstrap para utilizar el jqgrid -->
		<link rel="stylesheet" href="<spring:url value='/WebContent/3PResources/jqGrid/css/ui.jqgrid-bootstrap.css?version=1.0'/>" />
		<!-- Css para la correccion de conflictos en los estilos con patternfly-->
		<link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/jqgrid-custom-styles.css?version=1.0'/>" />
		
		<title>
	  		<spring:eval expression="@appPropierties.getProperty('app.name')" />
		</title>
		
    </head>
	<body>
	    <c:import url="/WEB-INF/views/_resources/p02_menuCompleto.jsp" />
		
		<c:import url="/WEB-INF/views/_resources/p05_modalConfirm.jsp" />
	
		<c:import url="formUsuarioJG.jsp" />
	
	    <div class="container">
	    	<div class="row">
				
				<c:import url="/WEB-INF/views/_resources/p03_showMessage.jsp" />
				
				<div class="panel panel-info">
					<div class="panel-heading clearfix">
						<h3 class="panel-title pull-left">JqGrid - Crud de usuarios</h3>
					</div>
					<div class="panel-body">
						<c:import url="listUsuarioJG.jsp" />
					</div><!-- /.panel content -->
					<div class="panel-footer">
						<!-- Icono de cargando -->
						<div class="pull-left spinner hide"></div>
						<!-- Botonera -->
						<div class="pull-right">
							<button id="newBtn" class="btn btn-primary"><span class="fa fa-plus"> </span> <spring:message code="btn.nuevo"/></button>
							<button id="bulkDeleteBtn" class="btn btn-danger bulkAction disabled" title="Eliminar registros seleccionados"> <span class="pficon pficon-delete"> <span class="selectedRowsNumber"></span></span> <spring:message code="btn.eliminar"/></button>
							<button id="exportBtn" class="btn btn-success"><span class="fa fa-file-excel-o fa-lg"></span>  <spring:message code="btn.exportar"/></button>
						</div>
		        		<div class="clearfix"></div>
					</div><!-- /.panel footer -->
				</div>
				
			</div>
			
	    </div>
	    
	    <!-- Para  añadir los formularios en los envio a excel -->
		<div id="excellPopup"></div>
		
		
	    <!-- JSP´s componentes-->    
	    <script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
		<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=1.0'/>"></script>
	    
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-switch.min.js?version=1.0'/>"></script>
	    
	    
		<!-- Utilidad para las llamas ajax -->
		<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/ajaxModule.js?version=1.0'/>"></script>
		
		<!-- Utilidad para mostrar mensajes -->
		<script src="<spring:url value='/WebContent/resources/js/_sharedModules/showMessageModule.js?version=1.0'/>"></script>
		
		<!-- Utilidad para mensajes internacionalizados-->
		<script src="<spring:url value='/WebContent/resources/i18n/messages_${pageContext.response.locale}.js?version=1.0'/>"></script>
		<script src="<spring:url value='/WebContent/resources/js/_sharedModules/i18nModule.js?version=1.0'/>"></script>
		
		<!-- Utilidad para exportarciones a excel-->
		<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/excelPopupModule.js?version=1.0'/>"></script>
		
		<!-- jqGrid e internacionalizacion-->
		<script	src="<spring:url value='/WebContent/3PResources/jqGrid/js/jquery.jqGrid.min.js?version=1.0'/>"></script>
		<script	src="<spring:url value='/WebContent/3PResources/jqGrid/i18n/grid.locale-es.js?version=1.0'/>"></script>
		
		<!-- Js´s  -->
		<script	src="<spring:url value='/WebContent/resources/js/usuarioJG/mainUsuarioJGModule.js?version=1.0'/>"></script>		
	
	</body>

</html>