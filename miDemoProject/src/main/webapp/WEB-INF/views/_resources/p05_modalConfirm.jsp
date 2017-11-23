<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<div id="modalConfirm" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          <span class="pficon pficon-close"></span>
        </button>
        <h4 class="modal-title"><spring:message code="modal.confirm.title"/></h4>
      </div>
      <div class="modal-body">
       
      </div>
   <div class="modal-footer">
   		<div class="row">
            <div class="col-12-xs text-center">
                <button id="cancelBtn" type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="btn.cancelar"/></button>
        		<button id="confirmBtn" type="button" class="btn btn-primary"><spring:message code="btn.aceptar"/></button>
            </div>
        </div>
   </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
  
  <script src="${pageContext.request.contextPath}/WebContent/resources/js/_sharedModules/modalConfirmModule.js?version=${app.version}"></script>
</div><!-- /.modal -->