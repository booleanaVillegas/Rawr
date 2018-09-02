import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Dino {
	private PApplet app;
	private PImage img, bala;
	private PImage[] vidas = new PImage[4];
	private int x, y, vel;
	//PVector vector;
	private ArrayList<Bala> balas;
	private int nVidas;
	private boolean saltar;
	private boolean vivo;

	public Dino(PApplet app, PImage img, PImage vidas[], PImage bala) {
		this.app = app;
		this.img = img;
		this.vidas = vidas;
		this.bala = bala;
		x = 350;
		y = 330;
		//vector = new PVector(x,y); 
		vel = 3;
		balas = new ArrayList<Bala>();
		saltar = false;
		vivo=true;
		nVidas = 3;
	}

	public void pintar() {
		app.imageMode(app.CENTER);
		app.image(img, x, y);
		app.imageMode(app.CORNER);
		app.image(vidas[nVidas], 0, 0);
		for (int i = 0; i < balas.size(); i++) {
			balas.get(i).pintarYMover();
		}
	}

	public void saltar(String comando) {
		//vector.y = vector.y + vel;
 y=y+vel;
		if (saltar) {
			vel = vel - 3;
		}
		if (vel <= -30) {
			vel = 3;
			saltar = false;
		}
		/*if (app.keyPressed) {
			if (app.keyCode == app.UP) {
				saltar = true;
			}
		}*/
		if(comando != null){
		if(comando.contains("saltar")){
			
			saltar=true;
			comando=null;
		}
		}
		if (y<-200 || y>700){
		nVidas=0;	
		}
	}

	public void disparar(String comando) {
		/*if (app.keyPressed) {
			if (app.keyCode == app.DOWN) {
				balas.add(new Bala(app, x, y, bala));
			}			
		}*/
		if(comando!=null){
			if (comando.contains("disparar")){
			balas.add(new Bala(app, x, y, bala));
			comando=null;
			}
		}		
		for (int i = 0; i < balas.size(); i++) {
			if (balas.get(i).getX() >= 1200) {
				balas.remove(i);
			}
		}
		
	}

	public boolean pierdeVida() {
		if (nVidas>0){
		nVidas--;
		}
		if (nVidas >= 1) {
			vivo= true;
		}
		if (nVidas <= 0) {
			vivo= false;
		}
		return vivo;
	}

	public int getnVidas() {
		return nVidas;
	}

	public void setnVidas(int nVidas) {
		this.nVidas = nVidas;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ArrayList<Bala> getBalas() {
		return balas;
	}
}
