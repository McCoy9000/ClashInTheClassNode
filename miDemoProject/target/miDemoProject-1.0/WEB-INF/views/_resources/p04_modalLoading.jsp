<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<div class="modal fade bs-example-modal-sm" id="modalCargando" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm">
      <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          <span class="pficon pficon-close"></span>
        </button>
        <h4 class="modal-title" id="myModalLabel"><spring:message code="modal.title"/></h4>
      </div>
      <div class="modal-body" align="center">
     	<p><spring:message code="modal.p1"/></p>
        <p><spring:message code="modal.p2"/></p>
      </div>
      <div class="spinner spinner-lg"></div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>