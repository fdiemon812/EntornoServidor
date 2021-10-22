<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>

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

<body>
<div align= "center">
<h1> Bienvenido a mi primer JSP</h1>
<h2>Eres la visita número: <%=contador %></h2>

<a href="formulario.jsp"><input type="button" value="Registro"></a>


</div>
</body>

<br>
<div align="center">

<%@ include file="footer.jsp" %>
</div>

</html>