
package com.emergentes.controlador;

import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // iniciar session
        HttpSession ses = request.getSession();
       if(ses.getAttribute("listusu")== null){
            ArrayList<Usuario> listaux = new ArrayList<>();
            ses.setAttribute("listusu", listaux);
        }
        
        ArrayList<Usuario> lista = (ArrayList<Usuario>)ses.getAttribute("listusu");
        
        String op = request.getParameter("op");
        String opcion = (op !=null)? request.getParameter("op"): "view";
        
        Usuario obj1= new Usuario ();
        int id , pos;
        
        switch (opcion){
            case "nuevo":
                request.setAttribute("miUsuario",obj1);
                request.getRequestDispatcher("editar.jsp").forward(request,response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request,id);
                obj1 = lista.get(pos);
                request.setAttribute("miUsuario", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                id=Integer.parseInt(request.getParameter("id"));
                pos=buscarIndice(request, id);
                lista.remove(pos);
                ses.setAttribute("listusu", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "view":
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        ArrayList<Usuario> lista =(ArrayList<Usuario>)ses.getAttribute("listusu");
        Usuario obj1 = new Usuario();
        
        obj1.setId(Integer.parseInt(request.getParameter("id")));
        obj1.setNombre(request.getParameter("nombre"));
        obj1.setApellidos(request.getParameter("apellidos"));
        obj1.setCorreo(request.getParameter("correo"));
        obj1.setContrasena(request.getParameter("contrasena"));
        
     
        
        int idt =obj1.getId();
        if(idt==0){
            int ultID;
            ultID = ultimoId(request);
            obj1.setId(ultID);
            lista.add(obj1);
        }
        else{
            lista.set(buscarIndice(request,idt), obj1);
           
        }
        ses.setAttribute("listusu", lista);
        response.sendRedirect("index.jsp");
   
    }
    private int ultimoId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Usuario> lista =(ArrayList<Usuario>)ses.getAttribute("listusu");
         int idaux=0;
         for(Usuario item : lista){
             idaux= item.getId();
         }
         return idaux +1;
    }
    
    private int buscarIndice(HttpServletRequest request , int id){
        HttpSession ses = request.getSession();
        ArrayList<Usuario> lista =(ArrayList<Usuario>) ses.getAttribute("listusu");
         int i =0;
         if( lista.size()>0){
             while( i< lista.size()){
                 if(lista.get(i).getId()== id){
                     break;
                 }
                 else{
                     i++;
                 }
             }
         }
         return i;
        
    }
}
