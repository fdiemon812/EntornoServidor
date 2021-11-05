package proyectoServletCarrito;

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
	
	
	//Esto en realidad, podría estar todo en una clase Producto, y luego crear objetos y usarlos. 
	
	private int PRECIO_MORDE=19;
	private int PRECIO_MORDE_A=14;
	private int PRECIO_MORDE_S=10;
	
	private int[] listaPrecios= {PRECIO_MORDE, PRECIO_MORDE_A,PRECIO_MORDE_S};
	
	private String NOMBRE_MORDE ="Mordedor";
	private String NOMBRE_MORDE_A ="Mordedor - A";
	private String NOMBRE_MORDE_S ="Mordedor - S";
	
	private String[] listaNombres= {NOMBRE_MORDE, NOMBRE_MORDE_A, NOMBRE_MORDE_S};
       
    
    public Catalogo() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		doPost(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion= request.getSession(false);
		
		if(sesion!=null) {
			
			
			String udMorde = request.getParameter("morde")+"";
			String udMordeA = request.getParameter("mordeA")+"";
			String udMordeS = request.getParameter("mordeS")+"";
			
			String[] listaUd= {udMorde, udMordeA, udMordeS};

			
			
			sesion.setAttribute("udMordeA", udMordeA);
			sesion.setAttribute("udMorde", udMorde);
			sesion.setAttribute("udMordeS", udMordeS);
			
			
			
			StringBuilder filas = new StringBuilder();
			for(int i= 0; i<listaUd.length;i++) {
				
				if(Integer.parseInt(listaUd[i])>0) {
					
					
					filas.append(""
							+ " <tr>\n"
							+ "<td>"+listaNombres[i]+" </td>\n"
							+ "<td>"+Integer.parseInt(listaUd[i])+"</td>\n"
							+ "   <td>"+listaPrecios[i]+"</td>\n"
							+ "                    </tr>"
							+ "");
					
				
					
				}
				
			}
			
			PrintWriter out = response.getWriter();
			
			
					if(filas.toString().equalsIgnoreCase("")) {
						
						response.sendRedirect("HTML/catalogo.html");
					}else{
			
			
			out.print("<!DOCTYPE html><html>"
					+ "<head>\n"
					+ "<meta charset=\"UTF-8\">\n"
					+ "<title>Insert title here</title>\n"
					+ "</head>\n"
					+ "\n"
					+ "<link rel='stylesheet' href='HTML/resumen.css'>\n"
					+ "	<body>\n"
					+ "		\n"
					+ "		\n"
					+ "		 <form action=\"/proyectoServletCarrito/FinalPedido\" method=\"post\">\n"
					+ "			\n"
					+ "			\n"
					+ "			<div class=\"container\">\n"
					+ "				<h3>Resumen</h3>\n"
					+ "				\n"
					+ "				<table align=\"center\">\n"
					+ " <tr>\n"
					+ "                        <td><b>Descripcion</b></td>\n"
					+ "                        <td>Uds</td>\n"
					+ "                        <td>Precio</td>\n"
					+ "                    </tr>"+filas.toString()+""
					+ "\n"
					+ "\n"
					+ "                </table>\n"
					+ "		\n"
					+ "		\n"
					+ "                   <b><p>Envio</p></b>\n"
					+ "                \n"
					+ "                   <input type=\"radio\" id=\"int\" name=\"place\" value=\"int\">\n"
					+ "                <label for=\"int\">10 dias - Gratis </label> <br>\n"
					+ "                <input type=\"radio\" id=\"ext\" name=\"place\" value=\"ext\">\n"
					+ "                <label for=\"ext\">48H - 3.95€</label>\n"
					+ "                    \n"
					+ "					\n"
					+ "					\n"
					+ "					<fieldset>\n"
					+ "						\n"
					+ "		\n"
					+ "					<button type=\"submit\" class=\"registerbtn\">Finalizar compra</button>\n"
					+ "		\n"
					+ "				</fieldset>\n"
					+ "				</div>\n"
					+ "				\n"
					+ "			   \n"
					+ "			  </form>"
					+ "		</body></html>");
			
		
		
				}
		
		
		
		
		
		}else {
			
			
			
			
			response.sendRedirect("HTML/sesionExpirada.html");
			
		}
		

	
		
		
	
	}

}
