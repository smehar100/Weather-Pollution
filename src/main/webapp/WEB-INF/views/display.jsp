<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<title>Weather Detail of ${cityName}</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
</head>

<body>

	<h1 align="center">Weather Detail of ${cityName}</h1>
	<c:set var="climate" value="${data.climate}" />
	<c:set var="air_quality" value="${data.airQuality}" />
	<h2>Temperature Details</h2>
	<h3>
		<div>
			The current temp is :  ${climate.temp}
			<br> The minimum temp is :  ${climate.temp_min}
			<br> The maximun temp is :  ${climate.temp_max}
		</div>
	</h3>
	<c:if test="${not empty air_quality}">
	<h2>Air Quality Details</h2>
	
		<h3><div id="air_details">
		The pm 2.5  level is :  ${air_quality.pm2_5}
		<br>
		The pm 10  level is :  ${air_quality.pm10}
		<br>
		The air quality is :  ${air_quality.category.value}
		    </div>
		</h3> 
    </c:if>
	<div id="footer" align="center">
		<b> This project makes use of openweathermap.org. For More
			information please go to there site <a
			href="https://openweathermap.org/api" target="_blank">link. </a>
		</b>
	</div>
</body>
</html>
