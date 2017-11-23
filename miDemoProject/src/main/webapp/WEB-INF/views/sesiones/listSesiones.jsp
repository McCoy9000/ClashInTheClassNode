<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>
<!-- Table HTML -->
<table id="tablaSesiones" class="datatable table table-striped table-bordered table-hover table-responsive">
  <thead>
    <tr>
     <th></th>
     <th><input type="text" class="form-control search-input-text" placeholder='<spring:message code="permiso.lista.tabla.search1"/>' data-column="1" data-name='USERCODE'/></th>
     <th><input type="text" class="form-control search-input-text" placeholder='<spring:message code="permiso.lista.tabla.search2"/>' data-column="2" data-name='USERNAME' /></th>
     <th><input type="text" class="form-control search-input-text" placeholder='<spring:message code="permiso.lista.tabla.search2"/>' data-column="3" data-name='SESSIONID' /></th>
     <th><input type="text" class="form-control search-input-text" placeholder='<spring:message code="permiso.lista.tabla.search2"/>' data-column="4" data-name='IP' /></th>
     <th><input type="text" class="form-control search-input-text" placeholder='<spring:message code="permiso.lista.tabla.search2"/>' data-column="5" data-name='LOGINTIME' /></th>
     <th><input type="text" class="form-control search-input-text" placeholder='<spring:message code="permiso.lista.tabla.search2"/>' data-column="5" data-name='LOGOUTTIME' /></th>
     <th></th>
   </tr>
   <tr>
     <th></th>
     <th><spring:message code="sesiones.lista.tabla.column1"/></th>
     <th><spring:message code="sesiones.lista.tabla.column2"/></th>
     <th><spring:message code="sesiones.lista.tabla.column3"/></th>
     <th><spring:message code="sesiones.lista.tabla.column4"/></th>
     <th><spring:message code="sesiones.lista.tabla.column5"/></th>
     <th><spring:message code="sesiones.lista.tabla.column6"/></th>
     <th></th>
    </tr>
  </thead>	  
</table>
<script	src="${pageContext.request.contextPath}/WebContent/resources/js/sesiones/listSesionesModule.js?version=${app.version}"></script>