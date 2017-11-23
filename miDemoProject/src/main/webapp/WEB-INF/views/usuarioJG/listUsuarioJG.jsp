<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!-- Table HTML -->
<table id="tabla" class="table-striped"></table>
<!-- Paginador HTML -->
<div id="tablaPager"></div>
			
<script	src="<spring:url value='/WebContent/resources/js/usuarioJG/listUsuarioJGModule.js?version=${app.version}'/>"></script>			