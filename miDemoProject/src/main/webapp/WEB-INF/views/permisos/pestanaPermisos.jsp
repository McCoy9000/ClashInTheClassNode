<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp"%>
<div class="row">
	<div class="col-sm-12">
	<div class="col-sm-12">
		<h3>
			&nbsp;<i class="fa fa-key">&nbsp;</i>
			<spring:message code="permiso.lista.tabla.title" />
		</h3>
			<div class="toolbar-pf-actions">
			<div class="spinner hide"></div>
			<div class="form-group" style="float: right">
				&nbsp;
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
		<c:import url="listPermisos.jsp" />
	</div>
</div>
<div>
	&nbsp;
</div>
</div>
<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=${app.version}'/>"></script>
<script	src="<spring:url value='/WebContent/resources/js/permisos/pestanaPermisosModule.js?version=${app.version}'/>"></script>
