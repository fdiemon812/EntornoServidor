package proyectoservletcarrito;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/FinalPedido")
public class FinalPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Double IVA = 1.21;
       
   
    public FinalPedido() {
        super();
    }

    
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession sesion= request.getSession(false);
	

		
		if(sesion!=null) {
			
			PrintWriter out = response.getWriter();
			
			//Obtenemos los datos de la sesión
			String[] listaUd= (String[]) sesion.getAttribute("listaUd");
			
			Producto product= new Producto();
			String[] listaNombres = product.getListaNombres();
			int[] listaPrecios = product.getListaPrecios();
			
			
			String tipoEnvio = request.getParameter("envio");
			double precioEnvio=0;
			if(tipoEnvio.equals("48H")) {
				
				 precioEnvio=3.98;
			}
			
			
			StringBuilder filas = new StringBuilder();
			Double totalFactura=0.0;
			for(int i= 0; i<listaUd.length;i++) {
				if(Integer.parseInt(listaUd[i])>0) {
					
					//Creamos las filas finales para la factura
					filas.append(""
							+ " <tr>\n"
							+ "<td>"+listaNombres[i]+" </td>\n"
							+ "<td>"+listaUd[i]+"</td>\n"
							+ "   <td>"+(Integer.parseInt(listaUd[i])*listaPrecios[i])+"</td>\n"
							+ "  <td>"+(Math.round((IVA*(Integer.parseInt(listaUd[i])*listaPrecios[i]))*100.0)/100.0)+"</td></tr>"
							+ "");
					
					totalFactura=totalFactura+(Math.round((IVA*(Integer.parseInt(listaUd[i])*listaPrecios[i]))*100.0)/100.0);
					
				}
				
				
				
			}
			

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
					+ "<form action=\"/proyectoservletcarrito/Fin\" method=\"post\">\n"
					+ "\n"
					+ "\n"
					+ "<div class=\"container\">\n"
					+ "<h3>Resumen de "+sesion.getAttribute("nombreUsuario")+"</h3>\n"
					+ "<table align=\"center\">\n"
					+ " <tr>\n"
					+ "                        <td><strong>Descripcion</strong></td>\n"
					+ "                        <td>Uds</td>\n"
					+ "                        <td>Precio</td><td>+IVA</td>\n"
					+ "                    </tr>"+filas.toString()+""
					+ "<tr>"
					+ "<td><strong>"+tipoEnvio+"</strong></td>"
					+ "<td>1</td>"
					+ "<td>"+(precioEnvio+0.00)+"</td>"
						+ "  <td>"+(0.00+Math.round(((IVA*precioEnvio)*100.0)/100.0))+"</td></tr>"
					+ "</tr>"
					+ "<tr><td><b>Total factura: "+(0.00+totalFactura+Math.round(((IVA*precioEnvio)*100.0)/100.0))+"</b></tr></td><br>"
					+ ""
					+ "                </table><br><br>\n"
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
			
		}else {
			
			response.sendRedirect("HTML/sesionExpirada.html");
			
		}
		
		
		
	}

}
