<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!DOCTYPE html>
<!--[if IE 9]><html lang="en-us" class="ie9 login-pf"><![endif]-->
<!--[if gt IE 9]><!-->
<html lang="es-es" class="login-pf">
<!--<![endif]-->
  <head>
    
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="<spring:url value='/WebContent/resources/images/favicon.ico?version=${app.version}'/> " type="image/x-icon" />
    
    <link rel="stylesheet" href="<spring:url value='/WebContent/3PResources/Patternfly/css/patternfly.css?version=${app.version}'/>" >
    <link rel="stylesheet" href="<spring:url value='/WebContent/3PResources/Patternfly/css/patternfly-additions.css?version=${app.version}'/>" >
    <link rel="stylesheet" href="<spring:url value='/WebContent/resources/css/styles.css?version=${app.version}'/>" >
    
    <title>
	  	<spring:eval expression="@appPropierties.getProperty('app.name')" />
	</title>
    
  </head>

  <body>
  	<%@ include file="/WEB-INF/views/_resources/p04_modalLoading.jsp" %>
    <span id="badge">
      <img src="<spring:url value='/WebContent/resources/images/logo_e.png'/>" alt="Eroski Logo" />
     
    </span>
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div id="brand">
            <h1>
             <spring:eval expression="@appPropierties.getProperty('app.name')" /> 
             <c:set var="versionado" value="${app.version}"></c:set>
             <c:choose>
             	<c:when test="${!empty versionado}">- ${versionado}</c:when>
             	<c:otherwise>(local) </c:otherwise>
             </c:choose>
             
             <span class="small pull-right" style="position: absolute;bottom: 0;right: 0;"><spring:message code="app.fechaUltimaModificacion"/></span> 
            </h1>
          </div><!--/#brand-->
        </div><!--/.col-*-->
        <div class="col-sm-7 col-md-6 col-lg-5 login">
          <form id="loginForm" class="form-horizontal" role="form" name="loginForm" action="<spring:url value='/j_spring_security_check'/>" method="post">
            <div class="col-sm-10 col-md-10">
              <div class="input-group">
              	<span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" name="userCode" placeholder="<spring:message code="login.code"/>" tabindex="1" required>
              </div>
            </div>
            <div class="col-sm-10 col-md-10" >
             &nbsp;
            </div>
            <div class="col-sm-10 col-md-10">
              <div class="input-group">
              	<span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name="password" placeholder="<spring:message code="login.password"/>" tabindex="2" required>
              </div>
            </div>
            <div class="col-sm-10 col-md-10" >
             &nbsp;
            </div>
            <div class="col-sm-10 col-md-10 submit" >
              <div class="input-group-center">
                <button id="btn_login" type="submit" class="btn btn-primary btn-lg" tabindex="3"><spring:message code="login.btn.entrar"/></button>
              </div>
             </div>
          </form>
        </div><!--/.col-*-->
        <div class="col-sm-5 col-md-6 col-lg-7 details">
	  		<c:choose>
	  			<c:when test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}">
		  			<div class="alert alert-danger">
		            	<span class="pficon pficon-info"></span>
		            	<strong>${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</strong>
		            </div>
	  			</c:when>
	  			<c:when test="${expiredSession}">
		  			<div class="alert alert-danger">
		            	<span class="pficon pficon-info"></span>
		            	<strong><spring:message code="login.session.expired"/></strong>
		            </div>
	  			</c:when>
	  			<c:when test="${logout}">
		  			<div class="alert alert-info">
		            	<span class="pficon pficon-info"></span>
		            	<strong><spring:message code="login.session.logout"/></strong>
		            </div>
	  			</c:when>
	  			<c:when test="${noautorizado}">
		  			<div class="alert alert-danger">
		            	<span class="pficon pficon-info"></span>
		            	<strong><spring:message code="login.error.permisos"/></strong>
		            </div>
	  			</c:when>
	  			<c:otherwise>
		  			<h3><spring:message code="login.p.title"/></h3> 
			        <p><spring:message code="login.p1"/></p>
		    	    <p><spring:message code="login.p2"/></p>             
	  			</c:otherwise>
	  		
	  		</c:choose>
  		</div><!--/.col-*-->
      </div><!--/.row-->
    </div><!--/.container-->
    
    <script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=${app.version}'/>"></script>
    <script src="<spring:url value='/WebContent/resources/js/autenticacion/signinModule.js?version=${app.version}'/>"></script>
  </body>
 
</html>