<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>
<c:import url="formUsuario.jsp" />
<div class="row">
	<div class="col-sm-12">
	<div class="col-sm-12">
		
		<h3>
			&nbsp;<i class="fa fa-users">&nbsp;</i>
			<spring:message code="usuario.lista.tabla.title" />
		</h3>
		<div class="toolbar-pf-actions">
			<div class="spinner hide"></div>
			<div class="form-group" style="float: right">
				<sec:authorize access="hasRole('USERSADD')">
				<button id="newBtn" class="btn btn-primary" type="button"
					title="Nuevo registro">
					<span class="fa fa-plus"> </span>
					<spring:message code="btn.nuevo" />
				</button>
				</sec:authorize>
				<sec:authorize access="hasRole('USERSDELETE')">
				<button id="bulkDeleteBtn"
					class="btn btn-danger disabled bulkAction" disabled="disabled"
					type="button" title="Eliminar registros seleccionados">
					<span class="pficon pficon-delete"> <span
						class="selectedRowsNumber"></span></span>
					<spring:message code="btn.eliminar" />
				</button>
				</sec:authorize>
				
			</div>

		</div>
		<div class="row">
			<!-- /col -->
		</div>
		<!-- /row -->
	</div>
	<!-- /col -->
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-12">
			<c:import url="listUsuarios.jsp" />
		</div>
	</div>
	<div>
		&nbsp;
	</div>
	<div class="col-sm-12">
		<c:import url="dualListRoles.jsp" />
	</div>
</div>
<!-- /.row -->


<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
<script	src="<spring:url value='/WebContent/resources/js/permisos/pestanaUsuarioModule.js?version=1.0'/>"></script>

