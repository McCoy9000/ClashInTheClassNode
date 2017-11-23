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
	
	<c:import url="/WEB-INF/views/_resources/p05_modalConfirm.jsp" />

	<c:import url="formUsuario.jsp" />

	<div class="container">
		
		<div class="row">
			<c:import url="/WEB-INF/views/_resources/p03_showMessage.jsp" />
			
			<h2> <spring:message code="usuario.lista.tabla.title"/></h2>	
			<div class="col-sm-12">
				<div class="toolbar-pf-actions">
					<div class="form-group" style="float:right">
						<button id="newBtn" class="btn btn-primary" type="button" title="Nuevo registro">
							<span class="fa fa-plus"> </span> <spring:message code="btn.nuevo"/>
						</button>
						<button id="bulkDeleteBtn" class="btn btn-danger disabled bulkAction" disabled="disabled" type="button" title="Eliminar registros seleccionados">
							<span class="pficon pficon-delete"> <span class="selectedRowsNumber"></span></span> <spring:message code="btn.eliminar"/>
						</button>
						<button id="exportBtn" class="btn btn-success" type="button" title="Exportar a excel">
							<span class="fa fa-file-excel-o fa-lg"> </span> <spring:message code="btn.exportar"/>
						</button>
					</div>
				</div>
				<div class="row">		
					<!-- /col -->
				</div>
				<!-- /row -->
			</div>
		<!-- /col -->
		</div>

		<div class="row">	
			<c:import url="listUsuarios.jsp" />
		</div>
		<!-- /.row -->

		<!-- Para  añadir los formularios en los envio a excel -->
		<div id="excellPopup"></div>
		
	</div>
	<!-- class container -->
	<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/DataTables/js/jquery.dataTables.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-switch.min.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='/WebContent/resources/i18n/messages_${pageContext.response.locale}.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='/WebContent/resources/js/_sharedModules/i18nModule.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='/WebContent/resources/js/_sharedModules/ajaxModule.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/excelPopupModule.js?version=${app.version}'/>"></script>
	
	<script	src="<spring:url value='/WebContent/resources/js/usuarioDT/mainUsuarioModule.js?version=${app.version}'/>"></script>
	
</body>
</html>