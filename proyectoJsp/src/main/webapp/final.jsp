<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page errorPage="error.jsp" %>
    
    
    <!-- Esta página va a mostrar los datos resultantes y la recomendación médica en función del imc de la 
    persona. También muestra el dni con la letra generada automaticamente. -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recomendación</title>

</head>

<link rel="stylesheet" href="css/estilo.css">

<body>

	<jsp:useBean id="persona" class="proyectoJsp.Persona" scope="session"/>
	<jsp:setProperty name="persona" property="*"/>
	
	
			
			 
<div class="marcoCentrado">
<div id="datos">

Nombre:

<b>
	<jsp:getProperty name="persona" property="nombre"/>
</b>

</div>
	
	
<div id="datos">

Edad:


<b>
<jsp:getProperty name="persona" property="edad"/>
</b>
</div>

<div id="datos">

DNI CON la letra:


<b>
<jsp:getProperty name="persona" property="nif"/>
</b>
</div>

<div id="datos">

Indice de Masa Corporal:
<% persona.setImc();%>


<b>
<jsp:getProperty name="persona" property="imc"/>
</b>
</div>

<br>
<div id="datos">

Observaciones:
<% if(persona.getImc()>29.9){
	
%> Tiene que bajar de peso. Coma sano y haga deporte. <%

}else if(persona.getImc()<25){
	
	
	
	
%> Tiene que subir su peso. Coma más y mejor!! <%



}else{
	
		
%> Estas estupendo<%

}
%>
</div>
	


</div>
	
<div align="center" >

<%@ include file="footer.jsp" %>
</div>


</body>
</html>