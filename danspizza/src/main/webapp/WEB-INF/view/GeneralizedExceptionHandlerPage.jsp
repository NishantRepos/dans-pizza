<!DOCTYPE html>

<html>

<head>

<title>Exception Handling Page</title>

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
			<h2>Generalized Exception Handler Page</h2>
			<br>
			<hr>
			<br><label class="fw-bold text-danger">Exception Occured is: ${message}</label><br> <br>
			<hr>
			<br> <br> <a class="btn btn-primary"
				href="${pageContext.request.contextPath}/index.jsp">Home</a>
		</div>

	</div>

</body>
</html>