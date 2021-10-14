<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html>

<body>
	<h2>Hello ${dateTime }</h2>
</body>

	<form:form action="show" modelAttribute="dateTime" method="POST">
		<form:button value="Send">Send</form:button>
	</form:form>
	

</html>