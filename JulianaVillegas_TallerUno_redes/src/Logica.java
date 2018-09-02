import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Logica implements Observer {
	private PApplet app;
	private Dino dino;
	private ArrayList<Fruta> frutas;
	private ArrayList<Junk> comidas;
	private ArrayList<Nube> nubes;
	private ArrayList<Nube> nubesDos;
	private ArrayList<Enemigo> enemies;
	private PImage[] imgs = new PImage[30];
	private PImage[] vidas = new PImage[4];
	private PImage[] comiditas = new PImage[5];
	private PImage[] nubesitas = new PImage[8];
	private PImage[] frutitas = new PImage[4];
	private int puntos;
	private Thread hilo;
	private Conexion con;
	private Consultar consul;
	private String msj;
	private int pantallas=0;
	private int opacity=2, o=12;
	private PFont font;
	private int balaDisplay, balaDisponible, balaGastada;
	private int tiempo;
	private Cronometro cron;

	public Logica(PApplet app) {
		this.app = app;
		///////// FUENTE  /////
		font = app.createFont("../imagen/Helvetica.otf", 30);
		//// CARGO TODAS LAS IMG ////
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = app.loadImage("../imagen/rawr" + i + ".png");
		}

		//// MINI ARREGLOS DE IMAGENES DE CADA COSA /////
		for (int i = 0; i < vidas.length; i++) {
			vidas[i] = imgs[i + 23];
		}
		for (int i = 0; i < comiditas.length; i++) {
			comiditas[i] = imgs[i + 18];
		}
		for (int i = 0; i < frutitas.length; i++) {
			frutitas[i] = imgs[i + 14];
		}
		for (int i = 0; i < nubesitas.length; i++) {
			nubesitas[i] = imgs[i + 3];
		}

		////// INICIO OBJS Y ARRALISTS/////
		dino = new Dino(app, imgs[12], vidas, imgs[13]);
		frutas = new ArrayList<Fruta>();
		comidas = new ArrayList<Junk>();
		nubes = new ArrayList<Nube>();
		nubesDos = new ArrayList<Nube>();
		enemies = new ArrayList<Enemigo>();
		con = new Conexion();
		con.addObserver(this);
		hilo = new Thread(con);
		hilo.start();
		consul=new Consultar();
		consul.start();
		cron= new Cronometro();
		cron.start();
		
msj= new String(" ");

		/// CREO LOS OBJETOS DE LOS ARRAYLIST //
		for (int i = 0; i < 5; i++) {
			nubes.add(new Nube(app, nubesitas));
		}
		for (int i = 0; i < 5; i++) {
			nubesDos.add(new Nube(app, nubesitas));
		}
		for (int i = 0; i < 3; i++) {
			frutas.add(new Fruta(app, frutitas));
		}
		for (int i = 0; i < 4; i++) {
			comidas.add(new Junk(app, comiditas));
		}
		for (int i = 0; i < 2; i++) {
			enemies.add(new Enemigo(app, imgs[11]));
		}
	}

	public void pintar() {
		opacity = o + opacity;

		if (opacity <= 0 || opacity >= 255) {

			o *= -1;
		}
		
		switch(pantallas){
		/// INICIO E INSTRUCCIONES ////
		case 0:
			app.image(imgs[0], 0, 0);
			app.tint(255,opacity);
			app.image(imgs[1], 0, 0);
			app.noTint();
			
				break;
			case 1:
				app.image(imgs[2], 0, 0);
				app.tint(255,opacity);
				app.image(imgs[1], 0, 0);
				app.noTint();
				
				break;
		//// JUEGOOOO ///
		case 2:
		tiempo=cron.getSegs();	
		for (int i = 0; i < nubesDos.size(); i++) {
			nubesDos.get(i).mover(nubesDos);
		}

		dino.pintar();

		for (int i = 0; i < nubes.size(); i++) {
			nubes.get(i).mover(nubes);
		}
		for (int i = 0; i < frutas.size(); i++) {
			frutas.get(i).mover(frutas);
		}
		for (int i = 0; i < comidas.size(); i++) {
			comidas.get(i).mover(comidas);
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).mover(enemies);
		}
        app.image(imgs[13], 340, 60, imgs[13].width/2, imgs[13].height/2);
		app.fill(255);
		app.textFont(font);
		app.text("Puntaje:"+puntos, 1000, 90);
		if (balaDisplay >0){
		app.text(balaDisplay, 400, 90);
		}else{
			app.text("0", 400, 90);
		}
		app.text("Tiempo:"+tiempo, 700, 90);
		break;
		/// PANTALLA PERDER ////
		case 3:
			app.image(imgs[29], 0, 0);
			app.tint(255,opacity);
			app.image(imgs[28], 0, 0);
			app.noTint();
			app.text("Puntaje:"+puntos, 530, 550);
			app.text("Tiempo:"+tiempo, 530, 585);
			break;
	}
	}
	public void condiciones() {
		
	
		switch (pantallas){
		case 0:
			cron.setJuego(false);
			if(msj!=null){
		if(msj.contains("saltar")){
			
			pantallas=1;
		}
			}
			msj=null;
			break;
		case 1:
			cron.setJuego(false);
			if(msj!=null){
			if(msj.contains("saltar")){
				pantallas=2;
				consul.borrar();
				cron.setSegs(0);
			}
			}
			msj=null;
			break;
		case 2:
			cron.setJuego(true);
		balaDisponible=consul.ips();	
		
		balaDisplay=balaDisponible-balaGastada;
		
		dino.saltar(msj);
		if(msj!=null){
			if(msj.contains("disparar")){
				if (balaDisplay>0){
				balaGastada++;
				}
			}
			}
		if(balaDisplay>0){
		dino.disparar(msj);
		}
		msj=null;
		/// RECOGE FRUTAS ////
		for (int i = 0; i < frutas.size(); i++) {
			if (app.dist(dino.getX(), dino.getY(), frutas.get(i).getX(), frutas.get(i).getY()) < 50) {
				frutas.remove(i);
				puntos += 10;
				break;
			}
		}
		/// RECOGE COMIDA CHATARRA ///
		for (int i = 0; i < comidas.size(); i++) {
			if (app.dist(dino.getX(), dino.getY(), comidas.get(i).getX(), comidas.get(i).getY()) < 50) {
				comidas.remove(i);
				puntos += 5;
				break;
			}
		}
		/// MATA ENEMIGOS CON BALAS ////
		for (int i = 0; i < enemies.size(); i++) {
			for (int j = 0; j < dino.getBalas().size(); j++) {
				Enemigo ene = enemies.get(i);
				Bala bal = dino.getBalas().get(j);
				if (app.dist(ene.getX(), ene.getY(), bal.getX(), bal.getY()) < 100) {
					enemies.remove(i);
					dino.getBalas().remove(j);
					break;
				}
			}
		}
		//// ME MATAN ENEMIGOS ////
		for (int i = 0; i < enemies.size(); i++) {
			Enemigo ene = enemies.get(i);
			if (app.dist(ene.getX(), ene.getY(), dino.getX(), dino.getY()) < 60) {
				dino.pierdeVida();
				enemies.remove(i);
			}
		}
		if (dino.getnVidas()<=0){
			pantallas=3;
		}
		break;
		case 3:
			cron.setJuego(false);
			//System.out.println("estoy en la pantalla");
			System.out.println(msj);
			if(msj!=null){
				System.out.println("primer if");
			if(msj.contains("reiniciar")){
				System.out.println("holi");
				pantallas=2;
				reiniciar();
				
			}
			
			}
			else {
			//	System.out.println("soy null");
			}
			msj=null;
			break;
	}
		
	}
	public void update(Observable arg0, Object arg1) {
		//System.out.println(arg1);
		msj= (String) arg1;
		
	}
public void reiniciar(){
	cron.setJuego(true);
	cron.setSegs(0);
	dino.setnVidas(3);
	puntos=0;
	dino = new Dino(app, imgs[12], vidas, imgs[13]);
	nubes.clear();
	nubesDos.clear();
	frutas.clear();
	comidas.clear();
	enemies.clear();
	consul.borrar();
	for (int i = 0; i < 5; i++) {
		nubes.add(new Nube(app, nubesitas));
	}
	for (int i = 0; i < 5; i++) {
		nubesDos.add(new Nube(app, nubesitas));
	}
	for (int i = 0; i < 3; i++) {
		frutas.add(new Fruta(app, frutitas));
	}
	for (int i = 0; i < 4; i++) {
		comidas.add(new Junk(app, comiditas));
	}
	for (int i = 0; i < 2; i++) {
		enemies.add(new Enemigo(app, imgs[11]));
	}
}
}
