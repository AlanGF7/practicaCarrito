package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ListaCarritoServlet")

public class ListaCarritoServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //Generamos sesión
        response.setContentType("text/html; charset=utf-8");
        HttpSession sesion = request.getSession();
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");
        //Verificar si la lista artículos existe
        if(articulos == null)
        {
            //Inicializar la lista
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);
            
        }
        //Procesamos el artículo
        String articuloNuevo = request.getParameter("articulo");
        
        // Revisar y agregar el artículo nuevo
        
        if(articuloNuevo != null && !articuloNuevo.isEmpty())
        {
            articulos.add(articuloNuevo);
        }
        
        int contador = 1;

            PrintWriter out = response.getWriter();
            out.print("<html>");
            out.print("<head>");
            out.print("<title> Lista de carrito</title>");
            out.print("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.print("<link href='//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css' rel='stylesheet'>");
            out.print("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.print("<link href='Recursos/servletCSS.css' rel='stylesheet'/>");
            out.print("</head>");
            out.print("<body>");
            out.print("<div class='container' name='containerTitle' id='containerTitle'>");
            out.print("<h1 name='listaProductos' id='listaProductos'>Lista de artículos</h1>");
            out.print("</div>");
            out.print("<br/>");
            out.print("<div class='container' name='containerTable' id='containerTable'>");
            out.print("<table class='table table-dark table-striped table-hover'>");
            
            //Encabezado de la tabla
            out.print("<thead> <tr> <th scope='col'>ID</th> <th scope='col'>Artículo</th>' <th scope='col'>Cantidad</th></thead>");
            out.print("<tbody>");
            out.print("</div>");
            
            //Iterar artículos
            
            //Aquí está siendo el problema
            for(String articulo: articulos)
            {
                if(articulos.toString() == articuloNuevo)
                {
                    contador = 10;
                }
                else
                {
                    contador = 0;
                }
                out.print("<tr> <th scope='row'>" + contador++ + "</th> <td>" + articulo + "</td></tr>");
                //out.print("<li>" + articulo + "</li>");
            }
            out.print("</tbody>");
            out.print("</table>");
            out.print("</br>");
            out.print("<a href='/practicaCarrito'>");
            out.print("<button type='button' class='btn btn-primary' name='buttonRegresar' id='buttonRegresar'> Regresar</button> </a>");
        
            out.print("</body>");
            out.print("</html>");
        
    }
}
