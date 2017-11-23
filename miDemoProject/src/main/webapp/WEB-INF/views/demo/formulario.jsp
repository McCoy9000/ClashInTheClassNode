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
	<body class="">
		<c:import url="/WEB-INF/views/_resources/p02_menuCompleto.jsp" />
	    
		<c:import url="/WEB-INF/views/_resources/p03_showMessage.jsp" />
		
		<div class="container">
	
			<div class="row">	
				<div class="panel panel-info">
				  <div class="panel-heading">
				    <h3 class="panel-title">Demo de formulario en el que se utilizan varios widgets</h3>
				  </div>
				  <div class="panel-body">
				  	<form id="demoform" class="form-horizontal">
						<fieldset>
						
						<!-- Form Name -->
						<legend>Formulario de empleado</legend>
							
						<!-- Datepicker-->
						<div class="form-group">
						  <label class="col-md-offset-1 col-md-2 control-label" for="fincorp">Fecha incorporación</label>  
						  <div class="col-md-2">
 						  		<div id="date" class="input-group date">
   								<input id="calendario" type="text" class="form-control">
   								<div class="input-group-addon">
       								<span class="fa fa-calendar"></span>
   								</div>
							</div>
						  </div>
						  
						  
						  <label class="col-md-2 control-label" for="flag">
						  	<a href="#" class="infoToolTip" title="Selecciona el tipo de compontes con el que deseas visualizar las areas y responsables">
						  	<i class="fa fa-info-circle" aria-hidden="true"></i></a> Tipo de listas
						  </label>  
						  
						  <div class="col-md-3">
						  	<input id="flag" class="bootstrap-switch"   data-on-text="Combobox" data-off-text="Select" type="checkbox" checked>  
						  </div>

						</div>
						
						<!-- Bootstrap Combobox --> 
						<div id="comboboxGroup" class="form-group"> 
							<label class="col-md-2 control-label" for="areaCmb">Area</label>
							<div class="col-md-3">
							    <select id="areaCmb" name="area" class="combobox form-control" data-live-search="true">
							      <option value="0">Selecione una área de negocio</option>
							      <c:forEach var="area" items="${lstAreas}" varStatus="i">
 							      	<option value="${area.codigo}">	<c:out value="${area.descripcion}"/></option>
							      </c:forEach>
							    </select>
							 </div>
							 <label class="col-md-1 control-label" for="responsableCmb">Responsable</label>
							 <div class="col-md-3">
							 	<select id="responsableCmb" name="responsable" class="combobox form-control" title="Seleccione un responsable" disabled>
							    </select>
							 </div>
						</div>
						
						<!-- Bootstrap Select -->  
						<div id="selectGroup" class="form-group hide"> 
							<label class="col-md-2 control-label" for="areaSelect">Area</label>
							<div class="col-md-3">
							    <select id="areaSelect" name="area" class="selectpicker form-control" data-live-search="true">
							      <option value="0">Selecione una área de negocio</option>
							      <c:forEach var="area" items="${lstAreas}" varStatus="i">
 							      	<option value="${area.codigo}">	<c:out value="${area.descripcion}"/></option>
							      </c:forEach>
							    </select>
							 </div>
							 <label class="col-md-1 control-label" for="responsableSelect">Responsable</label>
							 <div class="col-md-3">
							 	<select id="responsableSelect" name="responsable" class="selectpicker form-control" title="Seleccione un responsable" disabled>
							    </select>
							 </div>
						</div>
						
						<!-- Text input-->
						<div class="form-group ">
						  <label class="col-md-2 control-label" for="email">Nombre</label>  
						  <div class="col-md-3">
						  	<input id="nombre" name="nombre" type="text" placeholder="Nombre del empleado" class="form-control" required>  
						  </div>
						  <label class="col-md-1 control-label" for="apellido1">Apellidos</label>  
						  <div class="col-md-3">
						  	<input id="apellido1" name="apellido1" type="text" placeholder="1ª apellido" class="form-control" required>  
						  </div>
						  <div class="col-md-3">
						  	<input id="apellido2" name="apellido2" type="text" placeholder="2ª apellido" class="form-control" required>  
						  </div>
						  
						</div>
						
						<!-- Prepended text-->
						<div class="form-group">
						  <label class="col-md-2 control-label" for="email">Email</label>
						  <div class="col-md-3">
						    <div class="input-group">
						      <span class="input-group-addon"><i class="fa fa-envelope fa-lg" aria-hidden="true"></i></span>
						      <input id="email" name="email" class="form-control" placeholder="Correo electrónico" type="text">
						    </div>
						   </div>
						  
						  <label class="col-md-1 control-label" for="telefono">Teléfono</label>
						  <div class="col-md-2">
						    <div class="input-group">
						      <span class="input-group-addon"><i class="fa fa-phone fa-lg" aria-hidden="true"></i></span>
						      <input id="telefono" name="telefono" class="form-control" placeholder="Teléfono fijo" type="text">
						    </div>
						   </div>
						   <label class="col-md-1 control-label" for="movil">Móvil</label>
						  <div class="col-md-2">
						    <div class="input-group">
						      <span class="input-group-addon"><i class="fa fa-mobile fa-lg" aria-hidden="true"></i></span>
						      <input id="movil" name="movil" class="form-control" placeholder="Teléfono móvil" type="text">
						    </div>
						   </div>
						</div>
						
						</fieldset>
					</form>

				  </div><!-- /.panel content -->
				  <div class="panel-footer">
				  	<!-- Icono de cargando -->
					<div class="pull-left spinner hide"></div>
					<!-- Button -->
				   	<div class="pull-right">
						<button id="limpiarBtn" class="btn btn-default">Limpiar</button>
						<button id="aceptarBtn" class="btn btn-primary">Aceptar</button>
				   	</div>

        			<div class="clearfix"></div>
				  </div><!-- /.panel footer -->
				  
				</div>
			</div>
			<!-- /.row -->
			
			
			
			<div id="modal" class="modal fade">
			  <div class="modal-dialog modal-lg">
			    <div class="modal-content">
			      <div class="modal-header">
			       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
			          <span class="pficon pficon-close"></span>
			        </button>
			        <h4 class="modal-title">Datos seleccionados</h4>
			      </div>
			      <div class="modal-body">
			       	<div class="row">
			       		<label class="col-md-3 control-label text-right">Fecha incorporación: </label>
			       		<label id="fIncorporacion" class="col-md-2 control-label"></label>
			       		<label class="col-md-offset-2 col-md-2 control-label text-right">Tipo de listas: </label>
			       		<label id="tDesplegable" class="col-md-2 control-label"></label>
			       	</div>
			       	<div class="row">
			       		<label class="col-md-3 control-label text-right">Area: </label>
			       		<label id="areaText" class="col-md-4 control-label"></label>
			       		<label class="col-md-2 control-label text-right">Responsable: </label>
			       		<label id="responsableText" class="col-md-3 control-label"></label>
			       	</div>
			       	<div class="row">
			       		<label class="col-md-3 control-label text-right">Usuario: </label>
			       		<label id="usuarioText" class="col-md-9 control-label"></label>
			       	</div>
			       	
			       	<div class="row">
			       		<label class="col-md-3 control-label text-right">Email: </label>
			       		<label id="emailText" class="col-md-3 control-label"></label>
			       		<label class="col-md-1 control-label text-right">Teléfono: </label>
			       		<label id="telefonoText" class="col-md-2 control-label"></label>
			       		<label class="col-md-1 control-label text-right">Móvil: </label>
			       		<label id="movilText" class="col-md-2 control-label"></label>
			       	</div>
			      </div>
			   <div class="modal-footer">
			   		<div class="row">
			            <div class="col-12-xs text-center">
			                <button id="cancelBtn" type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			            </div>
			        </div>
			   </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div>	
			
			
		</div>
		<!-- class container -->

		

		<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=${app.version}'/>"></script>
		<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-combobox.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-switch.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-datepicker.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-datepicker.es.min.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/utilModule.js?version=${app.version}'/>"></script>
	    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/ajaxModule.js?version=${app.version}'/>"></script>
		<script src="<spring:url value='/WebContent/resources/js/_sharedModules/showMessageModule.js?version=${app.version}'/>"></script>
		
		<script src="<spring:url value='/WebContent/resources/js/demos/mainFormularioModule.js?version=${app.version}'/>"></script>
	</body>
</html>