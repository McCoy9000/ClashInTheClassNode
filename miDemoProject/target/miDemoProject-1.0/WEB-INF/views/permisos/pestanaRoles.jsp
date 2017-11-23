<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp"%>
<c:import url="formRole.jsp" />
<div class="row">
	<div class="col-sm-12">
	<div class="col-sm-12">
		<h3>
			&nbsp;<i class="fa fa-object-group">&nbsp;</i>
			<spring:message code="role.lista.tabla.title" />
		</h3>
		<div class="toolbar-pf-actions">
			<!--  -->
			<div class="spinner hide"></div>
			<div class="form-group" style="float: right">
				<sec:authorize access="hasRole('ROLESADD')">
				<button id="newBtnRole" class="btn btn-primary" type="button"
					title="Nuevo registro">
					<span class="fa fa-plus"> </span>
					<spring:message code="btn.nuevo" />
				</button>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLESDELETE')">
				<button id="bulkDeleteBtnRole"
					class="btn btn-danger disabled bulkAction" disabled="disabled"
					type="button" title="Eliminar registros seleccionados">
					<span class="pficon pficon-delete"> <span
						class="selectedRowsNumberRole"></span></span>
					<spring:message code="btn.eliminar" />
				</button>
				</sec:authorize>
				<!-- 
				<sec:authorize access="hasRole('ROLESEXPORT')">
				<button id="exportBtnRole" class="btn btn-success" type="button"
					title="Exportar a excel">
					<span class="fa fa-file-excel-o fa-lg"> </span>
					<spring:message code="btn.exportar" />
				</button>
				</sec:authorize>
				-->
				&nbsp;
			</div>
			<!--  -->
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
		<c:import url="listRoles.jsp" />
	</div>
	</div>
	<div>
		&nbsp;
	</div>
	<div class="col-sm-12">
		 <c:import url="dualListPermisos.jsp" />
	</div>
</div>
<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
<script	src="<spring:url value='/WebContent/resources/js/permisos/pestanaRoleModule.js?version=1.0'/>"></script>
