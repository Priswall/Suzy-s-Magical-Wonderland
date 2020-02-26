package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -226400529104463287L;
	Window window;
	boolean isRunning = false;
	BufferStrategy bs;
	private Thread t;
	private int WIDTH = 800, HEIGHT = 600;
	public static ArrayList<Character> keys = new ArrayList<>();

	public Game() {
		window = new Window(this, "Testing", WIDTH, HEIGHT);
	}

	public void start() {
		t = new Thread(this);
		isRunning = true;
		if(this.getBufferStrategy() == null) this.createBufferStrategy(3);
		bs = this.getBufferStrategy();

		t.run();
	}

	public void stop() {
		isRunning = false;
		try {t.join();} catch (InterruptedException e) {e.printStackTrace();}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (isRunning) {
		    long now = System.nanoTime();
		    delta += (now - lastTime) / ns;
		    lastTime = now;
		    while (delta >= 1) {
		        update();
		        delta--;
		    }
		    render();
		    frames++;

		    if (System.currentTimeMillis() - timer > 1000) {
		    	System.out.println("FPS: " + frames);
		        timer += 1000;
		        frames = 0;
		    }
		}
		stop();
	}

	public void update() {
	}

	public void render() {
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

		AffineTransform old = g.getTransform();

		g.setTransform(old);

		bs.show();
		g.dispose();
	}

	public static void main(String... strings) {
		new Game();
	}
}
