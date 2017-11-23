<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>


	<!-- Table HTML -->
	<table id="tabla" class="datatable table table-striped table-bordered table-hover table-responsive">
	  <thead>
	    <tr>
	      <th>
	      </th>
	      <th><input type="text" class="form-control search-input-text" placeholder='<spring:message code="usuario.lista.tabla.search1"/>' data-column="1" data-name='USERNAME'/></th>
	   	  <th><input type="text" class="form-control search-input-text hide" data-column="2" data-name='PASSWORD'/>&nbsp;</th>
	   	  <th><input type="text" class="form-control search-input-text hide" data-column="3" data-name='FLDAP'/></th>
	      <th><input type="text" class="form-control search-input-text" placeholder="<spring:message code="usuario.lista.tabla.search4"/>" data-column="4" data-name='DESCRIPTION'/></th>
	      <th></th>
	    </tr>
	    <tr>
	      <th><input type="checkbox" id="checkboxes" name="selectAll"></th>
	      <th><spring:message code="usuario.lista.tabla.column1"/></th>
	      <th>Contraseña</th>
	      <th><spring:message code="usuario.lista.tabla.column2"/></th>
	      <th><spring:message code="usuario.lista.tabla.column3"/></th>
	      <th><spring:message code="usuario.lista.tabla.column4"/></th>
	
	    </tr>
	  </thead>	  
	</table>
			
<script	src="${pageContext.request.contextPath}/WebContent/resources/js/usuarioDT/listUsuariosModule.js?version=${app.version}"></script>			