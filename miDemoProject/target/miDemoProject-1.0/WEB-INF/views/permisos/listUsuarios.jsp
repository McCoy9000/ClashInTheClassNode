<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

			<!-- Table HTML -->
			<table id="tabla" class="datatable table table-striped table-bordered table-hover table-responsive">
			  <thead>
			    <tr>
			      <th>
			      </th>
			      <th><input type="text" class="form-control search-input-text-usuarios" placeholder='<spring:message code="usuario.lista.tabla.search1"/>' data-column="1" data-name='USERNAME'/></th>
			   	  <th>&nbsp;</th>
			      <th><input type="text" class="form-control search-input-text-usuarios" placeholder="<spring:message code="usuario.lista.tabla.search4"/>" data-column="3" data-name='DESCRIPTION'/></th>
			      <th></th>
			    </tr>
			    <tr>
			      <th><input type="checkbox" id="checkboxes" name="selectAll"></th>
			      <th><spring:message code="usuario.lista.tabla.column1"/></th>
			      <th><spring:message code="usuario.lista.tabla.column2"/></th>
			      <th><spring:message code="usuario.lista.tabla.column3"/></th>
			      <th><spring:message code="usuario.lista.tabla.column4"/></th>

			    </tr>
			  </thead>	  
			</table>
			
<script	src="${pageContext.request.contextPath}/WebContent/resources/js/permisos/listUsuariosModule.js?version=1.0"></script>
<script>
    <sec:authorize access="hasAnyRole('USERSEDIT')"> 
	 	listUsuariosModule.setEdit('true');	
 	</sec:authorize>
 	<sec:authorize access="hasAnyRole('USERSDELETE')"> 
 		listUsuariosModule.setDelete('true');
	</sec:authorize>
	<sec:authorize access="hasAnyRole('USERSROLESVIEW')"> 
		listUsuariosModule.setRolesView('true');
	</sec:authorize>
	<sec:authorize access="hasAnyRole('USERSROLESEDIT')"> 
		listUsuariosModule.setRolesEdit('true');
	</sec:authorize>
	
</script>			