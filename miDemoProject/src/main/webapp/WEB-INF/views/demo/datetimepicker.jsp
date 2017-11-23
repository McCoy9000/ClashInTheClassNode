<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<html lang="es-es">
    <head>
        <c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
        <link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/styles.css?version=${app.version}'/>" >
        <link rel="stylesheet" href="<spring:url value='/WebContent/3PResources/datetimepicker/css/bootstrap-datetimepicker.min.css?version=${app.version}'/>" >
        
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
				    <h3 class="panel-title">Ejemplos datetimepickers</h3>
				  </div>
				  <div class="panel-body">
				  	<form id="demoform" class="form-horizontal">
						<fieldset>
						
						<!-- Form Name -->
						<legend></legend>
							
						<!-- Datepicker-->
						<div class="form-group">
						  <label class="col-md-2 control-label" for="fincorp">Configuración mínima</label>  
						  <div class="col-md-2">
 						  		<div id='datetimepicker1' class="input-group date">
   								<input id="calendario" type="text" class="form-control">
   								<div class="input-group-addon">
       								<span class="fa fa-calendar"></span>
   								</div>
							</div>
						  </div>
						  
						  <label class="col-md-offset-3 col-md-2 control-label" for="fincorp">Internacionalizado 'ES'</label>  
						  <div class="col-md-2">
 						  		<div id='datetimepicker2' class="input-group date">
   								<input id="calendario" type="text" class="form-control">
   								<div class="input-group-addon">
       								<span class="fa fa-calendar"></span>
   								</div>
							</div>
						  </div>
						  
						</div>
						
						<div class="form-group">
						  
						  <label class="col-md-2 control-label" for="fincorp">Con formato</label>  
						  <div class="col-md-2">
 						  		<div id='datetimepicker3' class="input-group date">
   								<input id="calendario" type="text" class="form-control">
   								<div class="input-group-addon">
       								<span class="fa fa-calendar"></span>
   								</div>
							</div>
						  </div>
						  
						  <label class="col-md-1 control-label" for="fincorp">En linea</label>
						  <div class="col-md-6">
                			<div id="datetimepicker4"></div>
            			</div>
						  
						</div>
						
						 
						
						</fieldset>
					</form>

				  </div><!-- /.panel content -->
				  
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
    <script src="<spring:url value='/WebContent/3PResources/moment/moment.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/datetimepicker/js/bootstrap-datetimepicker.min.js?version=${app.version}'/>"></script>

	<script src="<spring:url value='/WebContent/resources/js/demos/mainDatetimepickerModule.js?version=${app.version}'/>"></script>

</body>

</html>