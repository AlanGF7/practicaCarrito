package web;

import datos.Articulos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ListaCarritoServlet")

public class ListaCarritoServlet extends HttpServlet {

    int indice = 0;
    //int contador = 0;
    boolean llave = false;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Generamos sesión
        response.setContentType("text/html; charset=utf-8");
        HttpSession sesion = request.getSession();

        List<String> articulos = (List<String>) sesion.getAttribute("articulos");

        //Funcional para local
        Articulos AgregarArticulo = new Articulos();
        Articulos ActualizarArticulo = new Articulos();
        List<Articulos> tiendita = (List<Articulos>) sesion.getAttribute("Clase");

        //Verificar si la lista artículos existe
        if (articulos == null) {
            //Inicializar la lista
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);

            //Nuevo intento guardar datos clase
            tiendita = new ArrayList<>();
            sesion.setAttribute("Clase", tiendita);

        }

        //Procesamos el artículo
        String articuloNuevo = request.getParameter("articulo");
        //Puede que haya problema aquí

        // Revisar y agregar el artículo nuevo
        if (articuloNuevo != null && !articuloNuevo.isEmpty()) {
            //articulos.add(articuloNuevo);

            for (Articulos miau : tiendita) {
                if (miau.getNombre().equals(articuloNuevo)) {
                    int dato = miau.getCantidad();
                    dato++;
                    ActualizarArticulo.setId(miau.getId());
                    ActualizarArticulo.setNombre(miau.getNombre());
                    ActualizarArticulo.setCantidad(dato);
                    tiendita.remove(miau.getId());
                    tiendita.add(miau.getId(), ActualizarArticulo);
                    llave = true;
                    break;
                }
            }

            if (!llave) {
                AgregarArticulo = new Articulos(indice, articuloNuevo, 1);
                indice++;
                tiendita.add(AgregarArticulo);
            }else{
                llave = false;
            }

        }

        //int sumatoria = 0;
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

        if (!tiendita.isEmpty()) {
            tiendita.forEach(objeto
                    -> {
                System.out.println(objeto);
                out.print("<tr> <th scope='row'>" + objeto.getId() + "</th> <td>" + objeto.getNombre() + "</td> <td>" + objeto.getCantidad() + "</td></tr>");
            });
        }
        //out.print("<tr> <th scope='row'>" + AgregarArticulo.getId() + "</th> <td>" + AgregarArticulo.getNombre() + "</td> <td>" + AgregarArticulo.getCantidad() + "</td></tr>");

        out.print("</tbody>");
        out.print("</table>");
        out.print("</br>");
        out.print("<a href='/practicaCarrito'>");
        out.print("<button type='button' class='btn btn-dark' name='buttonRegresar' id='buttonRegresar'> Regresar</button> </a>");

        out.print("</body>");
        out.print("</html>");

    }
}
