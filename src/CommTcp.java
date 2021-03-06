import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CommTcp extends Thread{
	private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private OnMessageListener observer;
    
    public void setObserver(OnMessageListener observer){
        this.observer = observer;
    }
    
    //Hilo de recepcion
    @Override
    public void run() {
    	try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Esperando...");
			this.socket = server.accept();
			System.out.println("Aceptado!");
			
			//Reader
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			this.reader = new BufferedReader(isr);
			
			//Writer 
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			this.writer = new BufferedWriter(osw);
			
			while(true) {
				recibirMensaje();
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //Solicitar conexion
    public void esperarConexion(){
    	this.start();
    }

    //Mandar un mensaje
    public void mandarMensaje(String mensaje){
    	new Thread(
    			()->{
    				try {
						writer.write(mensaje+"\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    	).start();
    }

    //Recibir mensaje
    public void recibirMensaje() throws IOException{
    	String line = reader.readLine();
    	if(observer!=null) observer.onMessage(line);
    }

    public void cerrarConexion(){
    	try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    public interface OnMessageListener{
        void onMessage(String mensaje);
    }

    
    

}