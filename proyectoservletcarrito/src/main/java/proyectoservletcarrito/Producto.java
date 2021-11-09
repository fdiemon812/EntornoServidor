package proyectoservletcarrito;

public class Producto {

	private static final int _PRECIO_MORDE=19;
	private static final int _PRECIO_MORDE_A=14;
	private static final int _PRECIO_MORDE_S=10;
	
	private static final int[] listaPrecios= {_PRECIO_MORDE, _PRECIO_MORDE_A,_PRECIO_MORDE_S};
	
	private static final String _NOMBRE_MORDE ="Mordedor";
	private static final String _NOMBRE_MORDE_A ="Mordedor - A";
	private static final String _NOMBRE_MORDE_S ="Mordedor - S";
	
	private final String[] listaNombres= {_NOMBRE_MORDE, _NOMBRE_MORDE_A, _NOMBRE_MORDE_S};
	
	
	/**
	 * Constructor vacio, en el ejercicio no nos hace falta pues solo usamos la clase para invocar una lista estática
	 */
	public Producto() {
		super();
	}
	
	/**
	 * Nos devuelve una lista con los nombres de los productos
	 * @return lista con nombres
	 */
	public String[] getListaNombres() {
		
		
		return listaNombres;
	}
	
	/**
	 * Nos devuelve una lista con los precios de los productos. 
	 * @return lista con precios
	 */
public int[] getListaPrecios() {
		
		
		return listaPrecios;
	}
	
	
}
