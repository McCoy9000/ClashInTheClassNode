<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<div class="modal fade" id="modalRole" data-accion="">
  <div class="modal-dialog">
    <div class="modal-content">
    <form id="form" class="form-horizontal"  action="javascript:btnSaveRole.click();"  method="POST">
      <div class="modal-header alert-primary">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          <span class="pficon pficon-close"></span>
        </button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body">
        
          <div class="form-group">
            <label class="col-sm-3 control-label" for="textInput-modal-markup"><spring:message code="role.formulario.rolename"/></label>
            <div class="col-sm-4">
              <input type="text" required id="rolename" name="rolename" class="form-control"></div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="textInput3-modal-markup"><spring:message code="role.formulario.description"/></label>
            <div class="col-sm-9">
              <input type="text" id="description" name="description" class="form-control">
            </div>
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="btn.cancelar"/></button>
        <button id="btnSaveRole" type="button" class="btn btn-primary"><spring:message code="btn.guardar"/></button>
      </div>
    </form>
    </div>  
  </div>
</div>

<script	src="${pageContext.request.contextPath}/WebContent/resources/js/permisos/formRoleModule.js?version=1.0"></script>