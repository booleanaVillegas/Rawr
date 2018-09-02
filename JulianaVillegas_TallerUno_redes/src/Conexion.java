import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;

public class Conexion extends Observable implements Runnable {
	DatagramSocket dtSocket;
	DatagramPacket paquete;
	final int PORT=5000;

	public Conexion(){
		try {
			dtSocket= new DatagramSocket(PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}


	public void run() {
 while(true){
	setChanged();
	notifyObservers(recibir());	 
 }
		
	}
	
	public String recibir(){
		byte[] by= new byte[1024];
		paquete= new DatagramPacket(by,by.length);
		try {
			dtSocket.receive(paquete);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String mensaje = new String(by);
		return mensaje;
	}
	
	
	
}
