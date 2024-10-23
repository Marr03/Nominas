<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar</title>
<link rel="stylesheet" href="../css/estilo.css" type="text/css">
<link rel="stylesheet" href="../css/listar.css" type="text/css">
<link rel="stylesheet" href="../css/text_error.css" type="text/css">
<link rel="stylesheet" href="../css/botonguardar.css" type="text/css">
</head>
<body>
	<h1>Editar Empleados</h1>
	 <br><br>

    <form action="../empresa" method="post"> 
    <input type="hidden" name="opcion" value="busqueda">
        <label for="opcion">Elige una opción de busqueda:</label>
        <select id="sort" name="sort">
            <option value="dni">DNI</option>
            <option value="nombre">Nombre</option>
            <option value="sexo">Sexo</option>
            <option value="categoria">Categoria</option>
            <option value="anyo">Años</option>
        </select>
        <br><br><br>
       	<input type="text" name="nombre" style="width: 500px;" required>
        <br><br>
         <input type="submit" id="submit" value="Buscar" class="button-10">
    </form>
    
    
    
    
    <br>
	<br>
    
	<a href="../index.jsp">Volver</a>
</body>
</html>