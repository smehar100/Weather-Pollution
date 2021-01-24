<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<title>Welcome to Weather Detail Application</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js"></script>
</head>

<body>

	<h1 align="center">Welcome to Weather Detail Application</h1>
	<h2>Please enter below details</h2>
	<form:form action="${pageContext.request.contextPath}/details/submit"
		modelAttribute="input" method="POST" autocomplete="false">
		<h3>City Name:</h3>
		<input type="text" name="cityName" id="input_city">
		<br />
		<h3>Action:</h3>
		<div>
			<input type="radio" id="action_temp" name="action" value="temp"><label
				for="temp">Temperature</label><br> <input type="radio"
				id="action_temp_air" name="action" value="temp_air"><label
				for="temp_air">Temperature and Air Quality</label><br>
		</div>
		<div align="center">
			<input type="submit" id="submit" value="Submit"
				onclick="return validation()" />
		</div>
	</form:form>
	<div id="footer" align="center" ><b>
		This project makes use of openweathermap.org. For More information
		please go to there site <a href="https://openweathermap.org/api" target="_blank">link.
		</a>
		</b>
	</div>
</body>
</html>
