package proyectoServletCarrito;

import java.util.Objects;

public class Usuario {
	
	private String user;
	private String pass;


	
	
	public Usuario() {}
	
	public Usuario(String user) {
		this.user=user;
		this.pass=null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(user, other.user);
	}

	public Usuario(String user, String pass) {
		this.user=user;
		this.pass=pass;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	@Override
	public String toString() {
		return "Usuario [user=" + user + ", pass=" + pass + "]";
	}

	
	

	







}



