<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>
<div class="form-group">
	<div id="duallistboxUserDiv" class="col-md-12" style="visibility: hidden" >
	 <b>Roles asociados a:</b> &nbsp;<label id="tituloDualList"></label>
	 	 <br>
	 	 <div class="col-sm-11">&nbsp;</div>
	 	 <div class="duallistcontainer">
	 	 	<sec:authorize access="hasAnyRole('USERSROLESEDIT')"> 
	 	 	<button id="saveRolesBtn" class="btn btn-primary" type="button"
					title="Guardar">
					<span class="fa fa-plus"> </span>
					<spring:message code="btn.guardar" />
			</button>
			</sec:authorize>
	 	 </div>
		 <select name="duallistboxUser" id="duallistboxUser"  multiple="multiple" size="5">
		</select>
	</div>
</div>
<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
<script	src="${pageContext.request.contextPath}/WebContent/resources/js/permisos/dualListRolesModule.js?version=1.0"></script>