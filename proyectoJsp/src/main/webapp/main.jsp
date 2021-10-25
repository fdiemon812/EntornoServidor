<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<!--  Página inicial, tendremos un contador y nos servirá de bienvenida.  -->
<!-- Uso de excalamacion para definir metodos y variables -->
<%!

int contador=0;

void sumaContador() {
	contador++;
	
}
%>
<!-- ejecutamos la función -->
<% sumaContador();%>


<html>

<link rel="stylesheet" href="css/estilo.css">
<title>
BIENVENIDO!!</title>
<body>
<div align= "center">
<h1> Bienvenido a mi primer JSP</h1>


	

<div class="marco">
<h2>Eres la visita número: <%=contador %></h2>

<a href="formulario.jsp"><input type="button" value="Registro"></a>

</div>
</div>
</body>

<br>
<div align="center" >

<%@ include file="footer.jsp" %>
</div>

</html>