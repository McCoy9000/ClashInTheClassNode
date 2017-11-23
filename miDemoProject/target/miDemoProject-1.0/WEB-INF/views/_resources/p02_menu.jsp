<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!-- HEADER and MENU inicio -->
<nav class="navbar navbar-default navbar-pf" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    
    <a class="navbar-brand" href='<spring:url value="/home"/>'>
      <img src="<spring:url value='/WebContent/3PResources/Patternfly/img/brand.svg'/>" alt="PatternFly Enterprise Application" />
    </a>
  </div>
  <div class="collapse navbar-collapse navbar-collapse-1">
    <ul class="nav navbar-nav navbar-utility">
      <li>
        <a href="#">Status</a>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        	<span class="pficon pficon-user"></span>
         	<sec:authentication property="principal.username" />
         	<b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li>
          	<a class="app_selector_delete" href="<spring:url value='/logout'/>"><i class="fa fa-power-off"></i> <spring:message code="menu.logout"/></a></li>
        </ul>
      </li>
    </ul>
    <ul class="nav navbar-nav navbar-primary">
      <li class="active">
        <a href="${pageContext.request.contextPath}/demos/menuHorizontalSimple">Menu horizontal simple</a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/demos/menuHorizontalCompleto">Menu horizontal completo</a>
      </li>
	</ul>
  </div>
</nav>
<!-- HEADER and MENU fin -->