package org.fxusagi.test;

import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Test {

	//See: http://www.gamedev.net/reference/articles/article2418.asp
	//See: http://stackoverflow.com/questions/2670975/drawing-performance-in-java-6-updates-19-20-versus-java-6-update-3
	
	private static final int DIM_X = 960;
	private static final int DIM_Y = 540;
	private static final boolean FULLSCREEN = false;

	public static void main(String[] args) {
		System.out.println("running game...");
		startGame();
	}

	public static void startGame() {
		final JFrame frame = new JFrame();

		frame.setIgnoreRepaint(true);
		frame.setUndecorated(true);
		boolean running = true;
		// Add ESC listener to quit...
		// frame.addKeyListener( new KeyAdapter() {
		// public void keyPressed( KeyEvent e ) {
		//		      
		// if( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
		// // running = false;
		// }
		// });

		// Get graphics configuration...
		GraphicsDevice gd = null;
		if (FULLSCREEN) {
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			gd = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gd.getDefaultConfiguration();

			// Change to full screen
			gd.setFullScreenWindow(frame);
			if (gd.isDisplayChangeSupported()) {
				gd.setDisplayMode(new DisplayMode(480, 320, 32,
						DisplayMode.REFRESH_RATE_UNKNOWN));
			} else {
				System.exit(0);
			}
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setAlwaysOnTop(true);

		frame.setSize(DIM_X, DIM_Y);

		Renderer r = new Renderer(DIM_X, DIM_Y);
		frame.add(r);
		frame.pack();
		frame.setVisible(true);

		r.init();

		int c = 0;
		while (c < 10000 && running) {
			r.move();
			r.render();
			c++;
		}
		if (gd != null) {
			gd.setFullScreenWindow(null);
		}
		System.exit(0);

	}

}
