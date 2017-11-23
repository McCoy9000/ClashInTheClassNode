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

<body class="cards-pf">
	
	<c:import url="/WEB-INF/views/_resources/p02_menuCompleto.jsp" />

	<div class="container-fluid container-cards-pf">
		<div class="row row-cards-pf">

		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
				<div class="card-pf-body">
					<div class="card-pf-top-element">
						<span class="fa fa-sign-in card-pf-icon-circle"></span>
					</div>
					<h2 class="card-pf-title text-center">Página de login</h2>
					<div class="card-pf-items text-center">
						<div class="card-pf-item">
							<span class="pficon pficon-screen"></span> <span
								class="card-pf-item-text">1</span>
						</div>
						<div class="card-pf-item">
							<span class="fa fa-check"></span>
						</div>
					</div>
					<p class="card-pf-info text-center">
						<strong>Creado el</strong> 01-03-2017
					</p>
				</div>
			</div>
		</div>

		<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
			<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class=" fa fa-exclamation-triangle card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Página de error</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-03-2017
						</p>
					</div>
				</div>
			</div>


			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-th card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Datatable (CRUD)</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">3</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-03-2017
						</p>
					</div>
				</div>
			</div>
			
			
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="pficon-virtual-machine card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Ventana modal</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">3</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-03-2017
						</p>
					</div>
				</div>
			</div>
			
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-bars card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Menú horizontal</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-03-2017
						</p>
					</div>
				</div>
			</div>


			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="pficon pficon-repository card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Pestañas (Tabs)</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-03-2017
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-spinner fa-spin card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Spinner</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-03-2017
						</p>
					</div>
				</div>
			</div>


			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-calendar card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Datepicker</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-03-2017
						</p>
					</div>
				</div>
			</div>
			
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-list card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Listas dependtes.</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-03-2017
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-list-alt card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Múltiple Select</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-08-2017
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-clock-o card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Datetimepicker</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-08-2017
						</p>
					</div>
				</div>
			</div>
			
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-keyboard-o card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Autocomplete</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-08-2017
						</p>
					</div>
				</div>
			</div>
			
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-search card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">jqGrid (Buscador)</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-08-2017
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-th card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">jqGrid (CRUD)</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">3</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-08-2017
						</p>
					</div>
				</div>
			</div>
			
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<div class="card-pf card-pf-view card-pf-view-select card-pf-view-multi-select">
					<div class="card-pf-body">
						<div class="card-pf-top-element">
							<span class="fa fa-info card-pf-icon-circle"></span>
						</div>
						<h2 class="card-pf-title text-center">Tooltips</h2>
						<div class="card-pf-items text-center">
							<div class="card-pf-item">
								<span class="pficon pficon-screen"></span> <span
									class="card-pf-item-text">1</span>
							</div>
							<div class="card-pf-item">
								<span class="fa fa-check"></span>
							</div>
						</div>
						<p class="card-pf-info text-center">
							<strong>Creado el</strong> 01-08-2017
						</p>
					</div>
				</div>
			</div>


		</div>
	</div>

	<script src="<spring:url value='/WebContent/3PResources/jquery/jquery-2.2.4.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Patternfly/js/patternfly.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/3PResources/Bootstrap/js/bootstrap-select.min.js?version=1.0'/>"></script>
	<script src="<spring:url value='/WebContent/resources/js/_sharedModules/menuPersistenceModule.js?version=1.0'/>"></script>

</body>

</html>