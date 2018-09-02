import java.util.Comparator;

public class CompararIps implements Comparator<Ip> {

	@Override
	public int compare(Ip o1, Ip o2) {
		// TODO Auto-generated method stub
		return o1.getDir().compareTo(o2.getDir());
	}



}
