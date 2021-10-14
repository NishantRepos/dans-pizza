<!DOCTYPE html>

<html>

<head>
<title>Pizza Order Success</title>

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

		<div class="content text-center">
			<br>
			<h2>Add Pizza Order Details Success</h2>
			<br>
			<br> <label class="text-success fw-bold"> Hi: ${pizzaOrderBean.customerName}, your
				order is placed with orderId: ${pizzaOrderBean.orderId}, Bill to be
				paid is: ${pizzaOrderBean.bill} </label> <br> <br> <a
				class="btn btn-primary"
				href="${pageContext.request.contextPath}/index.jsp">Home</a>

		</div>

	</div>


</body>

</html>