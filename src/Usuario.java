
public class Usuario {
	
	private String nombre, cedula;

	public Usuario(String nombre, String cedula) {
		this.cedula = cedula;
		this.nombre = nombre;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	

}
