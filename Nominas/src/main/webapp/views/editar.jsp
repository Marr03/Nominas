<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Editar Empleado</title>
    <link rel="stylesheet" type="text/css" href="css/listar.css">
    <link rel="stylesheet" type="text/css" href="css/boton_guardar.css">
    <link rel="stylesheet" type="text/css" href="css/enlace_a.css">
    <link rel="stylesheet" type="text/css" href="css/text_error.css">
</head>
<body>
    <h1>Editar Empleado</h1>
    <form action="empresa" method="post">
        <input type="hidden" name="opcion" value="editar">
        <input type="hidden" name="dni" value="${empleado.dni}"> 
        <table>
            <tr>
                <td>Nombre:</td>
                <td><input type="text" name="nombre" size="50" value="${empleado.nombre}" required></td>
            </tr>
            <tr>
                <td>Categoría:</td>
                <td><input type="number" name="categoria" size="50" value="${empleado.categoria}" required></td>
            </tr>
            <tr>
            	<td>Sexo:</td>
			    <td>
			        <input type="text" name="sexo" size="50" value="${empleado.sexo}" required>
			    </td>
            </tr>
            <tr>
                <td>Años:</td>
                <td><input type="number" name="anyo" size="50" value="${empleado.anyos}" required></td>
            </tr>
        </table>
        <br>
        <input type="submit" id="submit" value="Guardar" class="button-10">
    </form>
    
    <%
        if (session.getAttribute("Error") != null) {
    %> 
        <br>			
        <tr>
            <span style="color:green"><%=session.getAttribute("Error")%></span>
        </tr>
    <%
        }
        session.removeAttribute("Error");
        session.invalidate();
    %>
    
    <br><br>
    <a href="index.jsp">Volver al index</a>
     <br><br>
    <a href="javascript:history.back()">Volver atras</a>
</body>
</html>
