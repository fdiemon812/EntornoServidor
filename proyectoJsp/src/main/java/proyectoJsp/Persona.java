package proyectoJsp;


/**
 * Clase para recoger la información del usuario al registrarse
 * @author Flavio
 *
 */
public class Persona {

	
	
	
	
	private String nombre;
	private String nif;
	private int edad;
	private Double peso;
	private Double altura;
	private Double imc;
	
	
	/**
	 * Crea una persona sin datos
	 */
	public Persona() {
		
	}

	

	/**
	 * Calcula automaticamente la letra del dni y la añade. Devuelve String. 
	 * @return String del numero introducido junto a la letra
	 */
	public String getNif() {
		
		StringBuilder sb = new StringBuilder();
		
		//agregamos letra del dni
			
		int modulo = Integer.parseInt(nif) % 23;
		String[] letras= {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		
		sb.append(this.nif);

		sb.append(letras[modulo]);
		
		return sb.toString();
	}




/**
 *  Modifica el atributo nif
 * @param nif
 */
	public void setNif(String nif) {
		this.nif = nif;
	}



	/**
	 * 
	 * @return Double imc
	 */
	public Double getImc() {
		return imc;
	}


	/**
	 * Calcula imc basandose en peso y altura. 
	 */
	public void setImc() {
		this.imc = this.peso/(this.altura*this.altura);
	}



	/**
	 * 
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Modifica el atributo nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	


	/**
	 * 
	 * @return int edad
	 */
	public int getEdad() {
		return edad;
	}


/**
 * Modifica la edad, lanza excepcion si edad>=0;
 * @param edad
 * @throws Exception
 */
	public void setEdad(int edad) throws Exception {
		
		if(edad<=0) {
			throw new Exception();
		}else {
			
			this.edad = edad;
		}
	}



	/**
	 * 
	 * @return Double peso
	 */
	public Double getPeso() {
		return peso;
	}


	/**
	 *  Modifica el atributo peso
	 * @param peso
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}


	/**
	 * 
	 * @return Double altura
	 */
	public Double getAltura() {
		return altura;
	}


	/**
	 *  Modifica el atributo altura
	 * @param altura
	 */
	public void setAltura(Double altura) {
		this.altura = altura;
	}



	







	
	
	
	
}
