<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<div class="modal fade" id="modal" data-accion="">
  <div class="modal-dialog">
  <form id="form" class="form-horizontal" action="javascript:btnSave.click();"  method="POST">
    <div class="modal-content">
      <div class="modal-header alert-primary">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          <span class="pficon pficon-close"></span>
        </button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body">
        
          <div class="form-group">
            <label class="col-sm-3 control-label" for="textInput-modal-markup"><spring:message code="usuario.formulario.username"/></label>
            <div class="col-sm-4">
              <input type="text" required id="username" name="username" class="form-control"></div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="textInput2-modal-markup"><spring:message code="usuario.formulario.password"/></label>
            <div class="col-sm-9">
              <input  type="password" id="password" name="password" class="form-control"
              		  required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$" 
					  title=<spring:message code='usuario.validar.password'/> /></div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="textInput3-modal-markup"><spring:message code="usuario.formulario.fldap"/></label>
            <div class="col-sm-9">
              <input id="flagldap" class="bootstrap-switch" type="checkbox" checked>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="textInput3-modal-markup"><spring:message code="usuario.formulario.descripcion"/></label>
            <div class="col-sm-9">
              <input type="text" required id="descripcion" name="descripcion" class="form-control">
            </div>
          </div>
  
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="btn.cancelar"/></button>
		<button id="btnSave" type="button" class="btn btn-primary"><spring:message code="btn.guardar"/></button>
      </div>
    </div>
    </form>
  </div>
</div>
<script	src="${pageContext.request.contextPath}/WebContent/resources/js/permisos/formUsuarioModule.js?version=${app.version}"></script>