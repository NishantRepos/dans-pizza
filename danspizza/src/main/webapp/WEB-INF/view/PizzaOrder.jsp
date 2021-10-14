<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>

<html>

<head>
<title>Pizza Order</title>
<!-- reference our style sheet -->

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">

<style type="text/css">
.error {
	color: red;
}
</style>
</head>

<body>

	<div class="container d-flex align-items-center justify-content-center">
		<div class="content">

			<br>
			<h2 class="text-center">Add Pizza Details</h2>
			<br>

			<form:form action="saveCustomer" modelAttribute="pizzaOrderBean"
				method="POST">
				<table
					class="table table-striped table-bordered table-responsive text-center">

					<thead>
						<tr></tr>
					</thead>

					<tbody>
						<tr>
							<th class="table-dark">Customer Name</th>
							<td><form:input path="customerName" /></td>
						</tr>
						<tr>
							<th class="table-dark">Customer Contact</th>
							<td><form:input path="contactNumber" /></td>
						</tr>
						<tr>
							<th class="table-dark">Pizza Name</th>
							<td><form:select path="pizzaId">
									<form:options items="${pizzaName}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="table-dark">Quantity</th>
							<td><form:input path="numberOfPiecesOrdered" /></td>
						</tr>
					</tbody>
				</table>
				<div
					class="div-btn d-flex align-items-center justify-content-center">
					<div>
						<form:button class="btn btn-success">Order</form:button>
						<br> <br> <a class="btn btn-primary"
							href="${pageContext.request.contextPath}/index.jsp">Home</a> <br>
						<br>
					</div>
				</div>
				
				<div class="div-btn d-flex align-items-center justify-content-center text-center">

				<div>
					<spring:hasBindErrors name="pizzaOrderBean">
						<c:forEach var="error" items="${errors.allErrors}">
							<b class="text-danger"><spring:message message="${error}"></spring:message>
							</b>
							<br>
						</c:forEach>
					</spring:hasBindErrors>
				</div>

			</div>
			</form:form>

			


		</div>


	</div>


</body>

</html>