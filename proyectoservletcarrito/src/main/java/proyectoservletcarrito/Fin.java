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
 * Servlet implementation class Fin
 */
@WebServlet("/Fin")
public class Fin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion= request.getSession(false); //si no existe la sesion, no la va a crear
		
		if(sesion!=null) { //comprobamos que sigue existiendo la sesion
			
			
			
			PrintWriter out = response.getWriter();
			
			
			
			//Creamos la siguiente pagina HTML con un formulario nuevo. 
			out.print("<!DOCTYPE html>\n"
					+ "<html lang=\"en\">\n"
					+ "<head>\n"
					+ "    <meta charset=\"UTF-8\">\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
					+ "    <title>Reserva</title>\n"
					+ "</head>\n"
					+ "\n"
					+ "<link rel=\"stylesheet\" href=\"HTML/index.css\">\n"
					+ "\n"
					+ "<header>\n"
					+ "\n"
					+ "\n"
					+ "</header>\n"
					+ "\n"
					+ "\n"
					+ "<body>\n"
					+ "\n"
					+ "\n"
					+ "    <form action=\"/proyectoservletcarrito/CerrarSesion\" method=\"post\">\n"
					+ "\n"
					+ "\n"
					+ "        <div class=\"container\">\n"
					+ "          <h3>Pedido Finalizado</h3>\n"
					+ "          <p>Esperamos verte pronto!</p>\n"
					+ "         <button type=\"submit\" class=\"registerbtn\">Cerrar Sesion</button>\n"
					+ "        </div>\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "       \n"
					+ "      </form>\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "</body>\n"
					+ "\n"
					+ "<footer>\n"
					+ "\n"
					+ "\n"
					+ "   \n"
					+ "</footer>\n"
					+ "</html>");
			
		
		
				
		
		
		
		
		
		}else {
			
			
			
			try {
				
				response.sendRedirect("HTML/sesionExpirada.html");
			} catch (Exception e) {
			
				e.printStackTrace();
				
				
			}
			
		}
		

	
		
		
	
	}

}
