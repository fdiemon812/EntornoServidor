package proyectoservletcarrito;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Catalogo
 */
@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	


    public Catalogo() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		doPost(request, response);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion= request.getSession(false); //si no existe la sesion, no la va a crear
		
		if(sesion!=null) { //comprobamos que sigue existiendo la sesion
			
			
			String udMorde = request.getParameter("morde")+"";
			String udMordeA = request.getParameter("mordeA")+"";
			String udMordeS = request.getParameter("mordeS")+"";
			
			String[] listaUd= {udMorde, udMordeA, udMordeS};

			
			
			sesion.setAttribute("listaUd", listaUd);

			Producto product= new Producto();
			String[] listaNombres = product.getListaNombres();
			int[] listaPrecios = product.getListaPrecios();
			
			
			StringBuilder filas = new StringBuilder();
			for(int i= 0; i<listaUd.length;i++) {   
				
				
				//creamos las filas para meterlas en el resumen de pedido
				if(Integer.parseInt(listaUd[i])>0) {
					
					
					filas.append(""
							+ " <tr>\n"
							+ "<td>"+listaNombres[i]+" </td>\n"
							+ "<td>"+Integer.parseInt(listaUd[i])+"</td>\n"
							+ "   <td>"+(listaPrecios[i] * Integer.parseInt(listaUd[i]))+"</td>\n"
							+ "                    </tr>"
							+ "");
					
				
					
				}
				
			}
			
			PrintWriter out = response.getWriter();
			
			//Si no hay filas nos redirige de nuevo a la pagina
					if(filas.toString().equalsIgnoreCase("")) {
						try {
							response.sendRedirect("HTML/catalogo.html");
							
						} catch (Exception e) {
								e.printStackTrace();
								}
					}else{
			
			//Creamos la siguiente pagina HTML con un formulario nuevo. 
			out.print("<!DOCTYPE html><html>"
					+ "<head>\n"
					+ "<meta charset=\"UTF-8\">\n"
					+ "<title>Insert title here</title>\n"
					+ "</head>\n"
					+ "\n"
					+ "<link rel='stylesheet' href='HTML/resumen.css'>\n"
					+ "<body>\n"
					+ "\n"
					+ "\n"
					+ "<form action=\"/proyectoservletcarrito/FinalPedido\" method=\"post\">\n"
					+ "\n"
					+ "\n"
					+ "<div class=\"container\">\n"
					+ "<h3>Resumen</h3>\n"
					+ "<table align=\"center\">\n"
					+ "<tr>\n"
					+ "                        <td><b>Descripcion</b></td>\n"
					+ "                        <td>Uds</td>\n"
					+ "                        <td>Precio</td>\n"
					+ "                    </tr>"+filas.toString()+""
					+ "\n"
					+ "\n"
					+ "                </table>\n"
					+ "\n"
					+ "\n"
					+ "                   <b><p>Envio</p></b>\n"
					+ "                \n"
					+ "                <input type=\"radio\" id=\"int\" name=\"envio\" value=\"10-Dias\" required>\n"
					+ "                <label for=\"int\">10 dias - Gratis </label> <br>\n"
					+ "                <input type=\"radio\" id=\"ext\" name=\"envio\" value=\"48H\" required>\n"
					+ "                <label for=\"ext\">48H - 3.95€</label>\n"
					+ "                    \n"
					+ "\n"
					+ "\n"
					+ "<fieldset>\n"
					+ "\n"
					+ "\n"
					+ "<button type=\"submit\" class=\"registerbtn\">Finalizar compra</button>\n"
					+ "\n"
					+ "</fieldset>\n"
					+ "</div>\n"
					+ "\n"
					+ "\n"
					+ "</form>"
					+ "</body></html>");
			
		
		
				}
		
		
		
		
		
		}else {
			
			
			
			try {
				
				response.sendRedirect("HTML/sesionExpirada.html");
			} catch (Exception e) {
			
				e.printStackTrace();
				
				
			}
			
		}
		

	
		
		
	
	}

}
