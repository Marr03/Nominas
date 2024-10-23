<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Busqueda</title>
<link rel="stylesheet" href="../css/estilo.css" type="text/css">
<link rel="stylesheet" href="../css/listar.css" type="text/css">
<link rel="stylesheet" href="../css/text_error.css" type="text/css">
<link rel="stylesheet" href="../css/botonguardar.css" type="text/css">
</head>
<body>
	<h1>Buscar Salario Empleado</h1><br><br>
	<form action="../empresa" method="post">
		<input type="hidden" name="opcion" value="buscar">
		<table>
			<tr>
				<td>Escribe el DNI del empleado para mostrar su salario:</td>
				<td class="td_form"><input type="text" name="dni" size="" required></td>
			</tr>
			</table>
			<br>
	 <input type="submit" id="submit" value="Buscar" class="button-10">
	</form>
	<br>
	<br>
	<a href="../index.jsp">Volver al index</a>
	 <br><br>
    <a href="javascript:history.back()">Volver atras</a>
</body>
</html>