<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<html lang="es-es">
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
    	<c:import url="/WEB-INF/views/_resources/p05_modalConfirm.jsp" />
        
        <div class="row main">
        
        	<c:import url="/WEB-INF/views/_resources/p03_showMessage.jsp" />
            
            <div class="panel-heading">
               <div class="panel-title text-center">
                    <h1 class="title">Eliminar Usuario</h1>
                    <hr />
                </div>
            </div> 
            <div>
                <form class="form-horizontal">
                    <div class="form-group">
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon">
                                        <i class="fa fa-user fa" aria-hidden="true"></i>
                                </span>
                                <input type="text" class="form-control"
                                    name="name" 
                                    id="demoUsernameTxt"  
                                    placeholder="Introduce el usuario"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group ">
                        <button id="demoDeleteUsuarioBtn" type="button" 
                            class="btn btn-primary btn-lg btn-block">
                            Borrar Usuario
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- JSP´s componentes-->    
    <script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=${app.version}'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=${app.version}'/>"></script>
        
    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/i18nModule.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/resources/i18n/messages_${pageContext.response.locale}.js?version=${app.version}'/>"></script>
    
    <!-- JSP´s de la pagina-->
    <script src="<spring:url value='/WebContent/resources/js/demos/mainModalModule.js?version=${app.version}'/>"></script>
            
</body>

</html>