<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar</title>
    <link rel="stylesheet" type="text/css" href="css//listar.css">
<link rel="stylesheet" type="text/css" href="css//boton_guardar.css">
<link rel="stylesheet" type="text/css" href="css//enlace_a.css">
<link rel="stylesheet" type="text/css" href="css//text_error.css">
</head>
<body>
    <h1>Listar Empleados</h1>
    <c:if test="${!empty lista}">
        <table class="listar-table">
            <tr class="color_th">
                <c:choose>
                    <c:when test="${accion == 'buscarSalario'}">
                        <td>DNI</td>
                        <td>Nombre</td>
                        <td>Salario</td>
                    </c:when>
                    <c:when test="${accion == 'buscarEditar'}">
                        <td>DNI</td>
                        <td>Nombre</td>
                        <td>Sexo</td>
                        <td>Categoria</td>
                        <td>Años Trabajados</td>
                        <td>Accion</td>
                    </c:when>
                    <c:otherwise>
                    	<td>DNI</td>
                        <td>Nombre</td>
                        <td>Sexo</td>
                        <td>Categoria</td>
                        <td>Años Trabajados</td>
                    </c:otherwise>
                </c:choose>
            </tr>
            
            <c:forEach var="empleado" items="${lista}">
                <tr>
                    <c:choose>
                        <c:when test="${accion == 'buscarSalario'}">
                         	<td><c:out value="${ empleado.dni}"></c:out></td>
                            <td><c:out value="${ empleado.nombre}"></c:out></td>
                            <td><c:out value="${ empleado.sueldo}"></c:out></td>
                        </c:when>
                        <c:when test="${accion == 'buscarEditar'}">
                         	<td><c:out value="${ empleado.dni}"></c:out></td>
                            <td><c:out value="${ empleado.nombre}"></c:out></td>
                            <td><c:out value="${ empleado.sexo}"></c:out></td>
                            <td><c:out value="${ empleado.categoria}"></c:out></td>
                            <td><c:out value="${ empleado.anyos}"></c:out></td>
                            <td><a href="empresa?opcion=meditar&dni=${empleado.dni}">Editar</a><c:out value=""></c:out></td>
                        </c:when>
                        <c:otherwise>
                        	<td><c:out value="${ empleado.dni}"></c:out></td>
                            <td><c:out value="${ empleado.nombre}"></c:out></td>
                            <td><c:out value="${ empleado.sexo}"></c:out></td>
                            <td><c:out value="${ empleado.categoria}"></c:out></td>
                            <td><c:out value="${ empleado.anyos}"></c:out></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <br><br><br>

    <%
        if (session.getAttribute("Error") != null) {
    %>
    <br>
    <tr>
        <td id="text_error"><span style="color:green"><%=session.getAttribute("Error")%></span></td>
    </tr>
    <% }
        session.removeAttribute("Error");
        session.invalidate();
    %>
    <br><br>
    <a href="index.jsp">Volver al index</a>
     <br><br>
    <a href="javascript:history.back()">Volver atras</a>
</body>
</html>
