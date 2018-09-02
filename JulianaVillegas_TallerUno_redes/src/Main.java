import processing.core.PApplet;

public class Main extends PApplet {
Logica log;


public void setup() {
	log= new Logica(this);
	size(1200,700);
	
}
public void draw(){
	background(63,169,245);
	log.pintar();
	log.condiciones();
}


}
