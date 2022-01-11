package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "pedidos")
public class Pedido {
	
	public ArrayList<Producto> listaProductos;
    
	private static int contador=0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "fecha", nullable = false)
	private Date fecha;
    @Column(name = "precioEnvio", nullable = false)
	private int precioEnvio;
    @Column(name = "totalPedido", nullable = false)
	private Double totalPedido;
    @Column(name = "nombre", nullable = false)
	private String nombre;
    @Column(name = "apellidos", nullable = false)
	private String apellidos;
	
    @Column(name = "direccion", nullable = false)
	private String direccion;
    @Column(name = "mail", nullable = false)
	private String mail;
	
    @Column(name = "telefono", nullable = false)
	private String tlf;
	

	/**
	 * Constructor vacio
	 */
	public Pedido() {
		
		this.listaProductos = new  ArrayList<Producto>();
		this.id=contador;
		contador++;
		this.fecha=new Date();
		
		
	}
	
	
	
	
	/**
	 * Constructor con parametro id
	 * @param id
	 */
	public Pedido(int id) {
		this.id=id;
	
	}



	/**
	 * Devuelve el coste total del pedido
	 * @return
	 */
	public Double getTotalPedido() {
		return totalPedido;
	}




	/**
	 * Modifica el coste total del pedido
	 * @param totalPedido
	 */
	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}








	/**
	 * Devuelve el id del pedido
	 * @return
	 */
	public int getId() {
		return id;
	}




	/**
	 * Modifica el id del pedido
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * Devuelve la fecha en la que se realizo el pedido
	 * @return
	 */
	public String getFecha() {
		
		
		Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate 
		 
        
        String strDateFormat = "dd-MM-yy"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); 
		
		
		return objSDF.format(objDate);
	}








	/**
	 * Se obtiene el precio del envio
	 * @return
	 */
	public int getPrecioEnvio() {
		return precioEnvio;
	}
	
	
	
	
	/**
	 * Modifica el precio del envio
	 * @param precioEnvio
	 */
	public void setPrecioEnvio(int precioEnvio) {
		this.precioEnvio = precioEnvio;
	}

	
	/**
	 * Devuelve la lista de productos de un pedido
	 * @return HashMap<Producto, Integer>
	 */
	public  ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	

	/**
	 * Devuelve el nombre del pedido
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}




	/**
	 * Modifica el nombre del usuario del pedido
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	/**
	 * Devuelve los apellidos del usuario del pedido
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}




	/**
	 * Modifica el apellido del usuario del pedido
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	
	/**
	 * Obtiene la direccion del pedido
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}




	/**
	 * Modifica la dirección del pedido
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}




	/**
	 * Obtiene el mail del usuario del pedido
	 * @return
	 */
	public String getMail() {
		return mail;
	}



	
	/**
	 * Modifica el correo del pedido
	 * @param mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}




	/**
	 * Obtiene el telefono del pedido
	 * @return
	 */
	public String getTlf() {
		return tlf;
	}




	/**
	 * Modifica el telefono del pedido
	 * @param tlf2
	 */
	public void setTlf(String tlf2) {
		this.tlf = tlf2;
	}





	




	/**
	 * Devuelve el codigo hash del pedido
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}




	/**
	 * Devuelve true si el pedido recibido es igual 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return id == other.id;
	}


	

	
	
	
}

