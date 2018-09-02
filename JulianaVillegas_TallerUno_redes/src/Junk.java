import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Junk {

	private PImage[] comiditas;
	private PApplet app;
	private int numComi;
	private int x;
	private int y;

	public Junk(PApplet app, PImage[] comiditas) {
		this.app=app;
		this.comiditas=comiditas;
		numComi = (int) app.random(-1,5);
		x= (int)app.random(1200,2400);
		y= (int)app.random(50,600);
	}

	public void mover(ArrayList<Junk> comidas) {
		app.imageMode(app.CENTER);
		app.image(comiditas[numComi], x, y);
		x= x-2;
		for (int i = 0; i < comidas.size(); i++) {
			if (comidas.get(i).getX()<-200){
				comidas.remove(i);
			}
			if (comidas.size()<4){
				comidas.add(new Junk(app,comiditas));
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
