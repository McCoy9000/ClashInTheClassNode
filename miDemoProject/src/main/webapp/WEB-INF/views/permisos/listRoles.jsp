<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>
<!-- Table HTML -->
<table id="tablaRoles" class="datatable table table-striped table-bordered table-hover table-responsive">
  <thead>
    <tr>
      <th>
      </th>
      <th><input type="text" class="form-control search-input-text-roles" placeholder='<spring:message code="role.lista.tabla.search1"/>' data-column="1" data-name='ROLENAME'/></th>
     <th><input type="text" class="form-control search-input-text-roles" placeholder='<spring:message code="role.lista.tabla.search2"/>' data-column="2" data-name='DESCRIPTION' /></th>
     <th></th>
   </tr>
   <tr>
     <th><input type="checkbox" id="checkboxesRoles" name="selectAllRoles"></th>
     <th><spring:message code="role.lista.tabla.column1"/></th>
     <th><spring:message code="role.lista.tabla.column2"/></th>
     <th><spring:message code="role.lista.tabla.column3"/></th>
    </tr>
  </thead>	  
</table>

<script	src="${pageContext.request.contextPath}/WebContent/resources/js/permisos/listRolesModule.js?version=${app.version}"></script>
<script>
    <sec:authorize access="hasAnyRole('ROLESEDIT')"> 
    	listRolesModule.setEdit('true');	
 	</sec:authorize>
 	<sec:authorize access="hasAnyRole('ROLESDELETE')"> 
 		listRolesModule.setDelete('true');
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLESPERMISOSVIEW')"> 
		listRolesModule.setPermisosView('true');
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLESPERMISOSEDIT')"> 
		listRolesModule.setPermisosEdit('true');
	</sec:authorize>
	
</script>	