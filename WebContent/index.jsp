<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/index.css">

<title>Method Analyzer</title>
</head>
<body>
	<h2>Press a number and 3 classes</h2>
	<form action="Analyzer" method="get" onsubmit="getClassesNames()">
		Data size : <input type="text" name="dataSize"/>
		<br>
		Class name : <input type="text" name="className0" id="clazz0" class="classfield">
		Class name : <input type="text" name="className1" id="clazz1" class="classfield">
		Class name : <input type="text" name="className2" id="clazz2" class="classfield">
		<input type="submit" value="Submit"/>
	</form>
</body>

<script type="text/javascript">
var action = new Array();

function getClassesNames() {
	
	for(var i=0;i<3;i++){
		
		action[i] = document.getElementById("clazz"+i).value;
		
	}
	
}

</script>
</html>