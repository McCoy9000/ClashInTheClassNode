<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resources/p00_directivasJSP.jsp" %>

<!-- Las entradas internacionalizadas de menu se almacenan en variables para ser reutilizadas-->
<spring:message code='menu.logout' var="menulogout"/>
<spring:message code='menu.utilidades' var="menuUtilidades"/>
<spring:message code='menu.permisos' var="menuPermisos"/>
<spring:message code='menu.sesiones' var="menuSesiones"/>


<spring:message code='menu.demos' var="menuDemos"/>
<spring:message code='menu.formulario' var="menuFormulario"/>
<spring:message code='menu.modal' var="menuModal"/>
<spring:message code='menu.widgets' var="menuWidget"/>
<spring:message code='menu.alertas' var="menuAlertas"/>
<spring:message code='menu.menuHorizontal' var="menuHorizontal"/>
<spring:message code='menu.simple' var="menuSimple"/>
<spring:message code='menu.completo' var="menuCompleto"/>
<spring:message code='menu.tabs' var="menuTabs"/>
<spring:message code='menu.datetimepicker' var="menuDatetimepicker"/>
<spring:message code='menu.duallistbox' var="menuDuallistbox"/>
<spring:message code='menu.autocomplete' var="menuAutocomplete"/>
<spring:message code='menu.datatable' var="menuDatatable"/>
<spring:message code='menu.datatable.crud' var="menuDatatableCrud"/>
<spring:message code='menu.jqgrid' var="menuJqGrid"/>
<spring:message code='menu.jqgrid.find' var="menuJqGridFind"/>
<spring:message code='menu.jqgrid.crud' var="menuJqGridCrud"/>
<spring:message code='menu.paginaError' var="menuPaginaError"/>


<nav class="navbar navbar-default navbar-pf" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse-21">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href='<spring:url value="/home"/>'> 
			<spring:eval expression="@appPropierties.getProperty('app.name')" />
		</a>
	</div>

	<div class="collapse navbar-collapse navbar-collapse-21">
<!-- 		Menu de utilidades -->
		<ul class="nav navbar-nav navbar-utility">
			<li class="dropdown  ${menu.menuLevel1 == menuUtilidades ? 'active': ''}">
				<a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <span class="pficon pficon-user"></span>
					<sec:authentication property="principal.username" /> <b	class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					 <sec:authorize access="hasRole('PERMISOSACCESS')"> 
					<li class=" ${menu.menuLevel2 == menuPermisos ? 'active': ''}"><a	href="<spring:url value='/permisos'/>">
						<i class="fa fa-lock"></i> <c:out value="${menuPermisos}"/> </a>
					</li>
					 <li class="divider"></li>
					 </sec:authorize>
					 <sec:authorize access="hasRole('SESSIONSACCESS')"> 
					<li  class="${menu.menuLevel2 == menuSesiones ? 'active': ''}"><a	href="<spring:url value='/sesiones'/>">
						<i class="fa fa-users"></i> <c:out value="${menuSesiones}"/> </a>
					</li>
					 <li class="divider"></li>
					 </sec:authorize>
					<li><a	href="<spring:url value='/logout'/>">
						<i class="fa fa-power-off"></i> <c:out value="${menulogout}"/> </a>
					</li>
				</ul>
			</li>
		</ul>

		<ul class="nav navbar-nav navbar-primary ${menu.menuLevel1 == menuDemos ? 'persistent-secondary' : ' '}">
			<li class="context context-bootstrap-select">
				<select	class="selectpicker" data-live-search="true" data-none-results-text="<spring:message code='menu.seleccion.no.matched'/>" title="<spring:message code='menu.seleccion'/>">
					
					<optgroup label="<spring:message code='menu.n1'/>">
												
						<option value="${pageContext.request.contextPath}/demos/formulario" data-subtext='<c:out value="${menuDemos}"/>'>
							<c:out value="${menuFormulario}"/>
						</option>
						
						<option value="${pageContext.request.contextPath}/demos/ventanaModal" data-subtext='<c:out value="${menuDemos}"/>'>
							<c:out value="${menuModal}"/> 
						</option>
						
						<option disabled data-subtext='<c:out value="${menuDemos} - ${menuWidget}"/>'><c:out value="${menuAlertas}"/></option>
						
						<option value="${pageContext.request.contextPath}/demos/menuHorizontalSimple" data-subtext='<c:out value="${menuDemos} - ${menuWidget}"/>'>
							<c:out value="${menuSimple}"/>
						</option>
						
						<option value="${pageContext.request.contextPath}/demos/menuHorizontalCompleto" data-subtext='<c:out value="${menuDemos} - ${menuWidget}"/>'>
							<c:out value="${menuCompleto}"/> 
						</option>
						
						<option value="${pageContext.request.contextPath}/demos/tabs" data-subtext='<c:out value="${menuDemos} - ${menuWidget}"/>'>
							<c:out value="${menuTabs}"/> 
						</option>
						
						<option value="${pageContext.request.contextPath}/demos/datetimepicker" data-subtext='<c:out value="${menuDemos} - ${menuWidget}"/>'>
							<c:out value="${menuDatetimepicker}"/> 
						</option>
						
						<option value="${pageContext.request.contextPath}/demos/duallistbox" data-subtext='<c:out value="${menuDemos} - ${menuWidget}"/>'>
							<c:out value="${menuDuallistbox}"/> 
						</option>
						
						<option value="${pageContext.request.contextPath}/demos/autocomplete" data-subtext='<c:out value="${menuDemos} - ${menuWidget}"/>'>
							<c:out value="${menuAutocomplete}"/> 
						</option>
						
						<option value="${pageContext.request.contextPath}/datatableCrud" data-subtext='<c:out value="${menuDemos} - ${menuDatatable}"/>'>
							<c:out value="${menuDatatableCrud}"/> 
						</option>
						
						<option value="${pageContext.request.contextPath}/demos/jqgrid" data-subtext='<c:out value="${menuDemos} - ${menuJqGrid }"/>'>
							<c:out value="${menuJqGridFind}"/> 
						</option>
						
						<option value="${pageContext.request.contextPath}/jqGridCrud" data-subtext='<c:out value="${menuDemos} - ${menuJqGrid }"/>'>
							<c:out value="${menuJqGridCrud}"/> 
						</option>
						
						<option value="${pageContext.request.contextPath}/demos/testErrorPage" data-subtext='<c:out value="${menuDemos}"/>'>
							<c:out value="${menuPaginaError}"/>
						</option>
						
					</optgroup>
				</select>
			</li>
			
			<li class="${menu.menuLevel1== menuDemos ? 'active': ''}">
				<a href="#"><c:out value="${menuDemos}"/><b class="caret"></b></a>
				<ul class="nav navbar-nav navbar-persistent">
					
					<li class="${menu.menuLevel2==menuFormulario ? 'active': ''}">
						<a href="${pageContext.request.contextPath}/demos/formulario">
							<c:out value="${menuFormulario}"/>
						</a>
					</li>
					
					<li class="${menu.menuLevel2==menuModal ? 'active': ''}">
						<a href="${pageContext.request.contextPath}/demos/ventanaModal">
							<c:out value="${menuModal}"/>
						</a>
					</li>
					
					<li class="dropdown-submenu ${menu.menuLevel2== menuWidget ? 'active': ''}">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<c:out value="${menuWidget}"/>
						</a>
						<ul class="dropdown-menu">
							<li class="disabled">
								<a href="#">
									<c:out value="${menuAlertas}"/>
								</a>
							</li>
							<li class="dropdown-submenu dropup ${menu.menuLevel3== menuHorizontal ? 'active': ''}">
								<a tabindex="-1" href="#">
									<c:out value="${menuHorizontal}"/>
								</a>
								<ul class="dropdown-menu">
									<li>
										<a href="${pageContext.request.contextPath}/demos/menuHorizontalSimple">
											<c:out value="${menuSimple}"/>
										</a>
									</li>
									
									<li class="${menu.menuLevel4 == menuCompleto ? 'active': ''}"> 
										<a href="${pageContext.request.contextPath}/demos/menuHorizontalCompleto">
											<c:out value="${menuCompleto}"/>
										</a>
									</li>
								</ul>
							</li>
							<li class=" ${menu.menuLevel3== menuTabs ? 'active': ''}">
								<a href="${pageContext.request.contextPath}/demos/tabs">
									<c:out value="${menuTabs}"/>
								</a>
							</li>
							<li class=" ${menu.menuLevel3== menuDatetimepicker ? 'active': ''}">
								<a href="${pageContext.request.contextPath}/demos/datetimepicker">
									<c:out value="${menuDatetimepicker}"/>
								</a>
							</li>
							
							<li class=" ${menu.menuLevel3== menuDuallistbox ? 'active': ''}">
								<a href="${pageContext.request.contextPath}/demos/duallistbox">
									<c:out value="${menuDuallistbox}"/>
								</a>
							</li>
							
							<li class=" ${menu.menuLevel3== menuAutocomplete ? 'active': ''}">
								<a href="${pageContext.request.contextPath}/demos/autocomplete">
									<c:out value="${menuAutocomplete}"/>
								</a>
							</li>
						</ul>
					</li>
					
					<li class="dropdown-submenu ${menu.menuLevel2== menuDatatable ? 'active': ''}">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<c:out value="${menuDatatable}"/>
						</a>
						<ul class="dropdown-menu">							
							<li class=" ${menu.menuLevel3== menuDatatableCrud ? 'active': ''}">
								<a href="${pageContext.request.contextPath}/datatableCrud">
									<c:out value="${menuDatatableCrud}"/>
								</a>
							</li>
						</ul>
					</li>
					
					<li class="dropdown-submenu ${menu.menuLevel2== menuJqGrid ? 'active': ''}">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<c:out value="${menuJqGrid}"/>
						</a>
						<ul class="dropdown-menu">
													
							<li class=" ${menu.menuLevel3== menuJqGridFind ? 'active': ''}">
								<a href="${pageContext.request.contextPath}/demos/jqgrid">
									<c:out value="${menuJqGridFind}"/>
								</a>
							</li>
							
							<li class=" ${menu.menuLevel3== menuJqGridCrud ? 'active': ''}">
								<a href="${pageContext.request.contextPath}/jqGridCrud">
									<c:out value="${menuJqGridCrud}"/>
								</a>
							</li>
						</ul>
					</li>

					<li class="${menu.menuLevel2 == menuPaginaError ? 'active': ''}">
						<a href="${pageContext.request.contextPath}/demos/testErrorPage">
							<c:out value="${menuPaginaError}"/>
						</a>
					</li>
						
				</ul>
			</li>
			
		</ul>
	</div>
</nav>