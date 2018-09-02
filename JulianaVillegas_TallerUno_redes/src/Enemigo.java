import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemigo {

	private PApplet app;
	private PImage img;
	private int x,y;

	public Enemigo(PApplet app, PImage img) {
		this.img=img;
		this.app=app;
		x= (int)app.random(1200,2400);
		y= (int)app.random(50,600);
	}
	public void mover(ArrayList<Enemigo> ene) {
		app.imageMode(app.CENTER);
		app.image(img, x, y);
		x= x-2;
		for (int i = 0; i < ene.size(); i++) {
			if (ene.get(i).getX()<-200){
				ene.remove(i);
			}   
			if (ene.size()<2){
				ene.add(new Enemigo(app,img));
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
