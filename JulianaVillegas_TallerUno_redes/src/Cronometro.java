
public class Cronometro extends Thread {
int segs;
private boolean juego;
Cronometro(){
	
}
public void setJuego(boolean juego) {
	this.juego = juego;
}
public void run(){
	while(true){
		if (juego){
		segs++;
		}
		else{
			segs+=0;
		}
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
public int getSegs() {
	return segs;
}
public void setSegs(int segs) {
	this.segs = segs;
}
}
