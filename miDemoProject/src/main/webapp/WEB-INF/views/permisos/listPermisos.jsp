<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>
<!-- Table HTML -->
<table id="tablaPermisos" class="datatable table table-striped table-bordered table-hover table-responsive">
  <thead>
    <tr>
     <th></th>
     <th><input type="text" class="form-control search-input-text-permisos" placeholder='<spring:message code="permiso.lista.tabla.search1"/>' data-column="1" data-name='PERMISSIONNAME'/></th>
     <th><input type="text" class="form-control search-input-text-permisos" placeholder='<spring:message code="permiso.lista.tabla.search2"/>' data-column="2" data-name='DESCRIPTION' /></th>
     <th></th>
   </tr>
   <tr>
     <th></th>
     <th><spring:message code="permiso.lista.tabla.column1"/></th>
     <th><spring:message code="permiso.lista.tabla.column2"/></th>
     <th></th>
    </tr>
  </thead>	  
</table>
<script	src="${pageContext.request.contextPath}/WebContent/resources/js/permisos/listPermisosModule.js?version=${app.version}"></script>
