import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Fruta {

	private PImage[] frutitas;
	private PApplet app;
	private int numFruta;
	private int x,y;

	public Fruta(PApplet app, PImage[] frutitas) {
		this.app=app;
		this.frutitas=frutitas;
		numFruta= (int) app.random(-1,4);
		x= (int)app.random(1200,2400);
		y= (int)app.random(50,600);
	}

	public void mover(ArrayList<Fruta> frutas) {
		app.imageMode(app.CENTER);
		app.image(frutitas[numFruta], x, y);
		x= x-2;
		
		for (int i = 0; i < frutas.size(); i++) {
			if (frutas.get(i).getX()<-200){
				frutas.remove(i);
			}
			if (frutas.size()<3){
				frutas.add(new Fruta(app,frutitas));
			}
		}
		app.imageMode(app.CORNER);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
