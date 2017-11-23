<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<!--[if IE 9]><html lang="en-us" class="ie9 login-pf"><![endif]-->
<!--[if gt IE 9]><!-->
<html lang="es-es">
<!--<![endif]-->
	<head>
    	<c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
    	<link rel="stylesheet" href="<spring:url value='/WebContent/3PResources/duallistbox/css/bootstrap-duallistbox.min.css'/>" >
    	
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
            <div class="col-sm-12">
           		<ul id="tabsopciones" class="nav nav-tabs">
           		<sec:authorize access="hasRole('USERSSEARCH')">
				<li id="tabuserlink" class="active">
					<a data-toggle="tab" href="#tabUsuarios">
					<spring:message code="permisos.tablink.usuarios" /></a>
				</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLESSEARCH')">
				<li id="tabroleslink"><a data-toggle="tab" href="#tabRoles">
					<spring:message code="permisos.tablink.roles" /></a>
				</li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('PERMISOSSEARCH')">
				<li id="tabpermisoslink" ><a data-toggle="tab" href="#tabPermisos">
					<spring:message code="permisos.tablink.permisos" /></a>
				</li>
				</sec:authorize>
			</ul>
			</div>
           </div>
           <div class="tab-content" style="background-color: white">
				<sec:authorize access="hasRole('USERSSEARCH')">
				<div id="tabUsuarios" class="tab-pane fade in active">
					<c:import url="pestanaUsuarios.jsp" />
				</div>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLESSEARCH')">
				<div id="tabRoles" class="tab-pane fade">
						<c:import url="pestanaRoles.jsp" />
				</div>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('PERMISOSSEARCH')">
				<div id="tabPermisos" class="tab-pane fade">
						<c:import url="pestanaPermisos.jsp" />
				</div>
				</sec:authorize>
			</div>
            <!-- /.row -->
		</div>
        <!-- /.container-fluid -->
	</div>

	<!-- class container -->
	<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=1.0'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/duallistbox/js/jquery.bootstrap-duallistbox.min.js'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/DataTables/js/jquery.dataTables.min.js?version=1.0'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-switch.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/resources/i18n/messages_${pageContext.response.locale}.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/resources/js/_sharedModules/i18nModule.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/resources/js/_sharedModules/ajaxModule.js?version=1.0'/>"></script>
	<script	src="<spring:url value='/WebContent/resources/js/_sharedModules/excelPopupModule.js?version=1.0'/>"></script>
	<script	src="<spring:url value='/WebContent/resources/js/permisos/mainPermisosModule.js?version=1.0'/>"></script>
	<script>
		//mainPermisosModule.setTabUser(false);
		//mainPermisosModule.setTabRole (false);
		//mainPermisosModule.setTabPermiso (false);
	  	<sec:authorize access="hasAnyRole('USERSSEARCH')">
			mainPermisosModule.setTabUser(true);
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLESSEARCH')">
			mainPermisosModule.setTabRole(true);
		</sec:authorize>
		<sec:authorize access="hasAnyRole('PERMISOSSEARCH')">
			mainPermisosModule.setTabPermiso(true);
	    </sec:authorize>
		mainPermisosModule.checkTab();
	</script>
</body>
</html>