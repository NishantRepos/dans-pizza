<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html>

<head>
<title>Order Report</title>

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

</head>

<body>

	<div class="container d-flex align-items-center justify-content-center">

		<div class="content">

			<br>
			<h2 class="text-center">Order within Price Range Report</h2>

			<br> <br>

			<form:form action="getdetails" modelAttribute="billRangeBean"
				method="POST">

				<table
					class="table table-striped table-bordered table-responsive text-center">
					<tbody>
						<tr>
							<td class="table-dark">From Price</td>
							<td><form:input path="fromPrice" /></td>
							<td class="table-dark">To Price</td>
							<td><form:input path="toPrice" /></td>
						</tr>
					</tbody>
				</table>
				<br>

				<div
					class="btn-fetch d-flex align-items-center justify-content-center">
					<div>
						<form:button class="btn btn-success">Fetch Details</form:button>
					</div>
				</div>


				<br>
				<br>

				<div
					class="result-table d-flex align-items-center justify-content-center">
					<c:choose>
						<c:when
							test="${empty billRangeBean.fromPrice || empty billRangeBean.toPrice}">

						</c:when>
						<c:otherwise>
							<table class="table table-striped table-bordered table-responsive text-center">
								<thead class="table-dark">
									<tr>
										<th>Order Id</th>
										<th>Customer Name</th>
										<th>PizzaId</th>
										<th>Quantity</th>
										<th>Bill</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="tempOrderBean" items="${listPizzaOrderBean}">

										<tr>
											<td>${tempOrderBean.orderId}</td>
											<td>${tempOrderBean.customerName}</td>
											<td>${tempOrderBean.pizzaId}</td>
											<td>${tempOrderBean.numberOfPiecesOrdered}</td>
											<td>${tempOrderBean.bill}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<br>
							<br>
							<br>
						</c:otherwise>
					</c:choose>
				</div>


			</form:form>
			<br>
			<div class="div-home d-flex align-items-center justify-content-center text-center">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/index.jsp">Home</a>
			</div>
			
			
		</div>

	</div>


	


</body>

</html>







