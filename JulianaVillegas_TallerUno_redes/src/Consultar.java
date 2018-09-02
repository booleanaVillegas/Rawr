import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Consultar extends Thread{
TreeSet<Ip> ips = new TreeSet<Ip>(new CompararIps());
public Consultar() {
	
}
public void run(){
	while (true){
		for (int i = 0; i < 255; i++) {
			String ipFull = null;
			String ip = null;
			String ipMocha= null;
			int numeroIp = 0;
			int numeroPto = 0;
			int timeout= 350;
			try {
				ipFull= InetAddress.getLocalHost().toString();
				numeroIp= ipFull.lastIndexOf("/");
				ip= InetAddress.getLocalHost().toString().substring(numeroIp+1);
				numeroPto= ip.lastIndexOf(".");
				ipMocha= ip.substring(0,numeroPto);
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
						}	
			String nueva = ipMocha+"."+i;
			try {
				if (InetAddress.getByName(nueva).isReachable(timeout)){
				      ips.add(new Ip (nueva));
				   }
			} catch (Exception e) {
				e.printStackTrace();
			} 
			//System.out.println(ips.size());
		}
		//System.out.println("listo!");
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}

public int ips(){
	return  ips.size();
}
public void borrar(){
	ips.clear();
}
}
