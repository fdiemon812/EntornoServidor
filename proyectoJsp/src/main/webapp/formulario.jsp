<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Formulario para recabar información del usuario y poder trabajar con ella posteriormente  -->


<!DOCTYPE html>
<html>

<link rel="stylesheet" href="css/estilo.css">

<head>
<meta charset="UTF-8">
<title>Formulario de Registro</title>
</head>
<body>
	
	
	
	<form action="final.jsp" method="POST" class="marco">
	
	
	
	<div>
		
		<label for="nombre">Nombre: </label>
        <input type="text" name="nombre" id="nombre">
		
	</div>
	
	<div>
		
		<label for="edad">Edad: </label>
        <input type="text" name="edad" id="edad">
		
	</div>
	
	<div>
		
		<label for="peso">Peso: </label>
        <input type="text" name="peso" id="peso">
		
	</div>
	
		<div>
		
		<label for="altura">Altura: </label>
        <input type="text" name="altura" id="altura">
		
	</div>
	
	
	<div>
		
		<label for="nif">DNI SIN letra: </label>
        <input type="text" name="nif" id="nif">
		
	</div>
	<div>
		
		
        <input type="submit" name="enviar" id="enviar" value="Enviar">
		
	</div>
	
	</form>
	<div align="center" >

<%@ include file="footer.jsp" %>
</div>
	
</body>
</html>