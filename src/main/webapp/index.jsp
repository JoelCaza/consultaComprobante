<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Consulta de Comprobantes</title>
</head>
<body>
<h1>Consulta de Comprobantes Electrónicos</h1>
<form action="consultaComprobante" method="post">
    <label for="claveAcceso">Clave de Acceso:</label>
    <input type="text" id="claveAcceso" name="claveAcceso" required>
    <button type="submit">Consultar</button>
</form>
</body>
</html>
