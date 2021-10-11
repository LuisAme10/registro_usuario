<%@page import="com.emergentes.modelo.Usuario"%>
<%
    Usuario item = (Usuario) request.getAttribute("miUsuario");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= (item.getId()==0) ? "nuevo registro" : "editar registro"%> </h1>
      
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="<%= item.getId() %>" />
            <table>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" value="<%= item.getNombre() %>" ></td>
                
                </tr>
                <tr>
                    <td>apellidos</td>
                    <td><input type="text" name="apellidos" value="<%= item.getApellidos() %>" /> </td>
                    <td></td>
                </tr>
                <tr>
                    <td>correo</td>
                    <td><input type="email" name="correo" value="<%= item.getCorreo()%>" /></td>
                </tr>
                <tr>
                    <td>contraseña</td>
                    <td><input type="password" name="contrasena" value="<%= item.getContrasena() %>" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="enviar" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
