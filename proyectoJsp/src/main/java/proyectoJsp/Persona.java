package proyectoJsp;

public class Persona {

	
	private String nombre;
	private String nif;
	private int edad;
	private Double peso;
	private Double altura;
	private Double imc;
	
	
	
	public Persona() {
		
	}

	
	
//	public String getNif() {
//		
//		StringBuilder sb = new StringBuilder();
//		
//		//agregamos letra del dni
//			
//		int modulo = this.nif % 23;
//		String[] letras= {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
//		
//		sb.append(this.nif);
//
//		sb.append(letras[modulo]);
//		
//		return sb.toString();
//	}

	public String getNif() {
		
		StringBuilder sb = new StringBuilder();
		
		//agregamos letra del dni
			
		int modulo = Integer.parseInt(nif) % 23;
		String[] letras= {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		
		sb.append(this.nif);

		sb.append(letras[modulo]);
		
		return sb.toString();
	}





	public void setNif(String nif) {
		this.nif = nif;
	}



	public Double getImc() {
		return imc;
	}



	public void setImc() {
		this.imc = this.peso/(this.altura*this.altura);
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	


	public int getEdad() {
		return edad;
	}



	public void setEdad(int edad) throws Exception {
		
		if(edad<=0) {
			throw new Exception("Edad no válida");
		}else {
			
			this.edad = edad;
		}
	}



	public Double getPeso() {
		return peso;
	}



	public void setPeso(Double peso) {
		this.peso = peso;
	}



	public Double getAltura() {
		return altura;
	}



	public void setAltura(Double altura) {
		this.altura = altura;
	}



	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + ", peso=" + peso + ", altura=" + altura + "]";
	}







	
	
	
	
}
