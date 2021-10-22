<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario de Registro</title>
</head>
<body>
	
	
	
	<form action="final.jsp" method="POST">
	
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
	
	
</body>
</html>