<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<html >
	<head>
    	<c:import url="/WEB-INF/views/_resources/p01_header.jsp" />
    	
    	<title>
	  		<spring:eval expression="@appPropierties.getProperty('app.name')" />
		</title>
    	
  	</head>    
<body>
	<c:import url="/WEB-INF/views/_resources/p02_menuCompleto.jsp" />
	
	<div class="container-fluid" id="contenedor">
	
		 <div>
          <div>
            <br>
            <div class="alert alert-danger">
            <h2 >
             <span class="fa pficon-info"></span> <spring:message code="error.p"/> ${errorInfo.messageForUser}
            </h2>
            </div>
            <div class="card-pf-body">
              <h5><spring:message code="error.p1"/> <a href="<spring:url value='/home'/>"> <spring:message code="error.p1.p"/> </a>.</h5> 
 			  <h5><spring:message code="error.p2"/></h5>
 			  <h3><spring:message code="error.url"/></h3> ${errorInfo.uri}
  
              <h3><spring:message code="error.exception"/></h3> ${errorInfo.nameClass} 

              <sec:authorize ifAllGranted="ROLE_ROOT">    
	              <a id="mas"   href="javascript:showDetails()"><spring:message code="error.detalle.mas"/></a>
	              <a id="menos" href="javascript:hideDetails()"><spring:message code="error.detalle.menos"/></a>
	              <br><br>
	              <div id="details">
	              <c:if test="${!empty errorInfo.error}">
		              <div style=" font-weight: bold">${errorInfo.error}</div>
		              <br>
	              </c:if>
	              	
	              <div>
	              	${errorInfo.stacktrace}
	              </div>
	              
           	  	</div>
           	</sec:authorize>	
           </div>
          </div>
        </div>
     </div>
 
 		<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
		<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=1.0'/>"></script>
	   	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=1.0'/>"></script>
	    <script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=1.0'/>"></script>

 
 <script>
$(document).ready(function() {  
	$('#details').hide();
	$('#menos').hide();
});
function showDetails(){
	$('#details').show();
	$('#menos').show();
	$('#mas').hide();
}
function hideDetails(){
	$('#details').hide();
	$('#menos').hide();
	$('#mas').show();
	
}
</script>    
</body>
</html>