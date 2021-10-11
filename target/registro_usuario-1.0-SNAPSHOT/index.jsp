<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Usuario"%>

// crear el arrya usuario
<%
    ArrayList<Usuario> lista = (ArrayList<Usuario>)session.getAttribute("listusu");
    %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de usuarios</h1>
        <a href="MainController?op=nuevo">Nuevo</a>
        <table border ="1" >
            <tr>
                <td>id</td>
                <td>nombre</td>
                <td>apellido</td>
                <td>correo electronico</td>
                <td>contraseña</td>
                <td></td>
                <td></td>
            </tr>
            <%
                if(lista !=null){
                    for (Usuario item : lista){
                
                %>
            <tr>
                <td><%= item. getId()%></td>
                <td><%= item.getNombre()  %></td>
                <td><%= item.getApellidos() %></td>
                <td><%= item.getCorreo() %></td>
                <td><%= item.getContrasena() %></td>
                <td><a href="MainController?op=editar&id<%= item.getId() %>">editar</a></td>
                <td><a href="MainController?op=eliminar&id<%= item.getId() 
                        %> " onclick='return confirm("esta seguro de eliminer el registro");' >eliminar</a></td>
            </tr>
            <%
                }
                }
               %>
        </table>
    </body>
</html>
