<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Empleados</title>
<link rel="stylesheet" href="css/index.css" type="text/css">
</head>
<body>
<h1>Menu de opciones</h1>
	<table>
		<tr>
			<td>
				<%-- <a href="productos?opcion=eliminar&id=<c:out value="${ producto.id}"></c:out>"> --%>
				<a href="empresa?opcion=listar">Listar Empleados de la DB</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="views/buscarSalario.jsp">Salario de un Empleado</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="views/buscarEditar.jsp">Modificar datos de un Empleado</a>
			</td>
		</tr>
	</table>
</body>
</html>