import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public class Nube {

	private PImage[] nubes=new PImage[8];
	private PApplet app;
	private int numNube,x,y;

	public Nube(PApplet app, PImage[] nubes){
		this.app=app;
		this.nubes=nubes;
		numNube = (int) app.random(-1,8);
		x= (int) app.random(1200,2400);
		y= (int) app.random(0,600);
	}
	
	public void mover(ArrayList<Nube> nube){
		app.image(nubes[numNube], x, y);
		x= x-2;
		for (int i = 0; i < nube.size(); i++) {
			if (nube.get(i).getX()<-200){
				nube.remove(i);
			}
			if (nube.size()<5){
				nube.add(new Nube(app,nubes));
			}
		}
	}

	public int getX() {
		return x;
	}
	
}
