package proyectoservletcarrito;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/InicioUsuario")
public class InicioUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InicioUsuario() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			doPost(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession(true);         //Obtengo la sesión, si no existe crea una
		sesion.setAttribute("nombreUsuario", request.getParameter("usuario"));  //Almaceno el nombre de usuario en la sesion
		sesion.setMaxInactiveInterval(10);									//Establezco 10 seg de expiracion de sesion
		
		
		
		Usuario user = new Usuario();
		
		
		if(user.compruebaUsuario(request.getParameter("usuario"), request.getParameter("pass"))) { //Si se introduce un usario existente pasa
			
			//No hay que comprobar la sesion, porque justo esta creada si no existe.
			
			
			try {
				
				response.sendRedirect("HTML/catalogo.html");   //Redirige al siguiente catalogo
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			
			
			
		}else {
			sesion.invalidate();  //Si no existe el usuario, invalidamos la sesion y al volver a la pagina se creara de nuevo. 
			
			try {		
				
			response.sendRedirect("HTML/error.html");
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}


	}

}
