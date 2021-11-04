package proyectoServletCarrito;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InicioUsuario
 */
@WebServlet("/InicioUsuario")
public class InicioUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InicioUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Usuario user = new Usuario();
		PrintWriter out = response.getWriter();
		
		if(user.compruebaUsuario(request.getParameter("usuario"), request.getParameter("pass"))) {
			
			out.println ("<HTML>");
			out.println ("<BODY>");
			out.println ("<H1>Datos del formulario</H1>");
			out.println ("<BR>");
			
			
			
			out.println ("Valor 1:" );
			out.println ("<BR>");
			out.println ("Valor 2:" );
			out.println ("<BR>");
			out.println ("Valor 3:");
			out.println ("<BR>");
			
			out.println ("</BODY>");
			out.println ("</HTML>");
			
			
			
			
		}else {
			
			out.println ("<HTML>");
			out.println ("<BODY>");
			out.println ("<H1>Datos del formulario</H1>");
			out.println ("<BR>");
			
			
			
			out.println ("no entra" );
			
			
			out.println ("</BODY>");
			out.println ("</HTML>");
		}

		
//		
//		doGet(request, response);
	}

}
