import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet implements CommTcp.OnMessageListener{
	
	private CommTcp commTcp;
	private ArrayList <Usuario> usuarios;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());

	}
	
	public void settings() {
		size(500,500);
	
	}
	
	public void setup() {
		usuarios = new ArrayList<Usuario>();
		commTcp = new CommTcp();
		commTcp.setObserver(this);
		commTcp.esperarConexion();
	}
	
	public void draw() {
		
	}

	@Override
	public void onMessage(String mensaje) {
			String [] datos = mensaje.split(",");
			String nombre = datos[0];
			String cedula = datos[1];
			usuarios.add(new Usuario (nombre, cedula));
			System.out.println(mensaje);
			
			if(mensaje == "lista") {
			for (int i = 0; i < usuarios.size(); i++) {
				commTcp.mandarMensaje(usuarios.get(i).getNombre()+","+ usuarios.get(i).getCedula());
			}
			}
	}
	
	

}
