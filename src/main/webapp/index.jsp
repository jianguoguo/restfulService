<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<link rel="icon" href="https://www.paypal.com/favicon.ico"
	type="image/x-icon">
<link rel="shortcut icon" href="https://www.paypal.com/favicon.ico"
	type="image/x-icon">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<title>Services</title>
</head>
<body>
	<div class="container">
		<div class="bs-docs-section">

			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h3 id="typography">The service has two endpoints.</h3>
						<ul>
							<li>One is to take a Unicode string as a parameter and
								register it in the service.</li>
							<li>The other is to take a string id and return all
								registered strings that match with the string id</li>
						</ul>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h3 id="typography">
							Endpoints
							</h1>
					</div>
				</div>
			</div>
			<!-- Headings -->
			<div class="row">
				<div class="col-lg-4">
					<div class="bs-component">

						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">Endpoint1, register string</h3>
							</div>
							<div class="panel-body">
								<form method="post"
									onsubmit="register();event.preventDefault(); ">
									<fieldset>
										<div class='form-group'>
											<input class="form-control" placeholder="Register String"
												required="true" id="unicodeString" name='unicodeString'
												type="text" value="${cookie.unicodeString.value}" />
										</div>
										<div class="form-group form-inline">
											<span class="pull-right"><a
												href="<c:url value="/services/register/abc"/>">Directly
													access to Restful URL?</a></span>
										</div>
										<div class="form-group">
											<input class="btn btn-lg btn-success btn-block" type="submit"
												value="Register this string">
										</div>
										<div class="form-group">
											<span class="text-danger" id="error">${error}</span>
										</div>
										<div id="status"></div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="bs-component">

						<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">Endpoint2, fetch stringID</h3>
							</div>
							<div class="panel-body">
								<form action="#" method="post"
									onsubmit="fetchSringID();event.preventDefault(); ">
									<fieldset>
										<div class='form-group'>
											<input class="form-control" placeholder="StringID"
												required="true" id="stringID" name='stringID' type="text"
												value="${cookie.stringID.value}" />
										</div>
										<div class="form-group form-inline">
											<span class="pull-right"><a
												href="<c:url value="/services/fetch/489"/>">Directly
													access to Restful URL?</a></span>
										</div>
										<div class="form-group">
											<input class="btn btn-lg btn-primary btn-block" type="submit"
												value="Fetch this stringID" onclick='fetchSringID();'>
										</div>
										<div class="form-group">
											<span class="text-danger" id="error">${error}</span>
										</div>
										<div id="status"></div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>

				</div>
				<div class="col-lg-4">
					<div class="bs-component">

						<div class="panel panel-warning">
							<div class="panel-heading">
								<h3 class="panel-title">Endpoint3, fetchAll</h3>
							</div>
							<div class="panel-body">
								<form method="post">
									<fieldset>
										<div class="form-group form-inline">
											<span class="pull-right"><a
												href="<c:url value="/services/fetchall"/>">Directly
													access to Restful URL?</a></span>
										</div>
										<div class="form-group">
											<input class="btn btn-lg btn-info btn-block" type="button"
												value="Fetch all strings" onclick='fetchall();'>
										</div>
										<div class="form-group">
											<span class="text-danger" id="error">${error}</span>
										</div>
										<div id="status"></div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>

	<div id="dialog" title="Result">
		<div id="content" class="alert alert-dismissible alert-success">
			Loading...</div>
	</div>
	<script type="text/javascript">
		function ajaxMethod(url, id) {
			$.ajax({
				url : url,
				method : "GET",
				data : {},
				dataType : "html"
			}).done(function(msg) {
				$("#" + id).html("Result is:<br/>" + msg);
			}).fail(function(jqXHR, textStatus) {
				alert("Request failed: " + textStatus);
			});
		}
	</script>
	<script type="text/javascript">
		function register() {
			var str = $("#unicodeString").val();
			if (str == "") {
				return;
			}
			ajaxMethod("<c:url value="/services/register/"/>" + str, "content");
			$("#dialog").dialog("option", "title",
					"Register unicodeString, it is " + str).dialog("open");
		}
		function fetchSringID() {
			var id = $("#stringID").val();
			if (id == "") {
				return;
			}
			ajaxMethod("<c:url value="/services/fetch/"/>" + id, "content");
			$("#dialog").dialog("option", "title",
					"Fetch Strings, the stringID is " + id).dialog("open");
		}
		function fetchall() {
			ajaxMethod("<c:url value="/services/fetchall"/>", "content");
			$("#dialog").dialog("option", "title", "Fetch all").dialog("open");
		}
	</script>
	<script>
		$(function() {
			$("#dialog").dialog({
				autoOpen : false,
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
					}
				}
			});
		});
	</script>
</body>
</html>
