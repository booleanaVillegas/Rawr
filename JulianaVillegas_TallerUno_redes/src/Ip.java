import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ip  implements Comparable {
	public InetAddress ip;
	private String dir;

	public Ip (String dir){
		this.dir=dir;
		try {
			ip= InetAddress.getByName(dir);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return dir.compareTo(((Ip) arg0).getDir());
	}

	public String getDir() {
		return dir;
	}

	
}
