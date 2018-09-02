import processing.core.PApplet;
import processing.core.PImage;

public class Bala {

	private PApplet app;
	private int x;
	private int y;
	private PImage bala;

	public Bala(PApplet app, int x, int y, PImage bala) {
		this.app=app;
		this.x=x;
		this.y=y;
		this.bala=bala;
	}

	public void pintarYMover() {
		app.imageMode(app.CENTER);
		app.image(bala, x, y);
		x= x+3;
		app.imageMode(app.CORNER);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	
}
