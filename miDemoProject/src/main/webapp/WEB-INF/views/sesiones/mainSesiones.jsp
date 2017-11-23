<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp"%>
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
	<c:import url="/WEB-INF/views/_resources/p05_modalConfirm.jsp" />
	<div id="page-wrapper">
		<div class="container-fluid well">
			<div class="row">
				<div class="col-sm-12">
					<br>
					<c:import url="/WEB-INF/views/_resources/p03_showMessage.jsp" />
				</div>
			</div>
			<!-- Page Heading -->
			<div class="row">
				<div class="col-sm-12">&nbsp;</div>
			</div>
			<div class="tab-content" style="background-color: white">
				<div class="row">
					<div class="col-sm-12">
						<div class="col-sm-12">
							<h3>
								&nbsp;<i class="fa fa-users">&nbsp;</i>
								<spring:message code="sesiones.lista.tabla.title" />
							</h3>
							<div class="toolbar-pf-actions">
								<div class="spinner hide"></div>
								<div class="form-group" style="float: right">
								<button id="refreshBtn" class="btn btn-primary" type="button">
									<span class="fa fa-refresh"> </span>
									<spring:message code="btn.resfrecar" />
								</button>
								</div>
								<!--  -->
							</div>
							<div class="row">
								<!-- /col -->
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="col-sm-12">
							<c:import url="listSesiones.jsp" />
						</div>
					</div>
					<div>&nbsp;</div>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>

	<!-- class container -->
	<script	src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/moment/moment.js'/>"></script>
	
	<script	src="<spring:url value='WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/3PResources/DataTables/js/jquery.dataTables.min.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-switch.min.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/resources/i18n/messages_${pageContext.response.locale}.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/i18nModule.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/ajaxModule.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/excelPopupModule.js?version=${app.version}'/>"></script>
	<script	src="<spring:url value='/WebContent/resources/js/sesiones/mainSesionesModule.js?version=${app.version}'/>"></script>
	<script>
		<sec:authorize access="hasAnyRole('SESSIONSEXPIRE')">
			listSesionesModule.setExpire(true);
		</sec:authorize>
	</script>	
</body>
</html>