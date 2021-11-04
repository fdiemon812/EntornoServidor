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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalogo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion= request.getSession(false);
		
		if(sesion!=null) {
			
			
			PrintWriter out = response.getWriter();
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
					+ "		 <form action=\"/proyectoServletCarrito/Catalogo\" method=\"post\">\n"
					+ "			\n"
					+ "			\n"
					+ "			<div class=\"container\">\n"
					+ "				<h3>Resumen</h3>\n"
					+ "				\n"
					+ "				<table align=\"center\">\n"
					+ "                    <tr>\n"
					+ "                        <td><b>Descripción</b></td>\n"
					+ "                        <td>Uds</td>\n"
					+ "                        <td>Precio</td>\n"
					+ "                    </tr>\n"
					+ "                    <tr>\n"
					+ "                        <td>Mordedor </td>\n"
					+ "                        <td>Ud</td>\n"
					+ "                        <td>19</td>\n"
					+ "                    </tr>\n"
					+ "                    <tr>\n"
					+ "                        <td>MordedorAma </td>\n"
					+ "                        <td>Ud</td>\n"
					+ "                        <td>14</td>\n"
					+ "                    </tr>\n"
					+ "                    <tr>\n"
					+ "                        <td>Mordedor - S</td>\n"
					+ "                        <td>Ud</td>\n"
					+ "                        <td>10</td>\n"
					+ "                    </tr>\n"
					+ "\n"
					+ "\n"
					+ "                </table>\n"
					+ "		\n"
					+ "		\n"
					+ "                   <b><p>Envío</p></b>\n"
					+ "                \n"
					+ "                   <input type=\"radio\" id=\"int\" name=\"place\" value=\"int\">\n"
					+ "                <label for=\"int\">10 días - Gratis </label> <br>\n"
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
			
		}else {
			
			
			
			
			response.sendRedirect("HTML/sesionExpirada.html");
			
		}
		

	
		
		
	
	}

}
