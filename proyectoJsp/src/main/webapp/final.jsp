<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recomendación</title>

</head>


<body>

	<jsp:useBean id="persona" class="proyectoJsp.Persona" scope="session"/>
	<jsp:setProperty name="persona" property="*"/>
	
	
			
			 

<div>

Nombre:
	<jsp:getProperty name="persona" property="nombre"/>

</div>
	
	
<div>

Edad:
<jsp:getProperty name="persona" property="edad"/>
</div>

<div>

DNI CON la letra:
<jsp:getProperty name="persona" property="nif"/>
</div>

<div>

Indice de Masa Corporal:
<% persona.setImc();%>
<jsp:getProperty name="persona" property="imc"/>
</div>


<div>

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
	



</body>
</html>