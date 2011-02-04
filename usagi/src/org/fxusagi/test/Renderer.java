package org.fxusagi.test;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;



import org.fxusagi.sprite.Sprite;

public class Renderer extends Canvas {

	private static final long serialVersionUID = -8850440406879162400L;

	

	ArrayList<Sprite> _sprites;
	BufferStrategy _strategy;
    final int _dim_x;
    final int _dim_y;
	
	public Renderer( int dim_x, int dim_y ) {
		setIgnoreRepaint( true );
		_dim_x = dim_x;
		_dim_y = dim_y;
		setSize(_dim_x, _dim_y);
		
	}
	
	public Color[][] getRandomColor(final int size) {
		Color[][] data = new Color[size][size];
		Color c = new Color((int) (Math.random() * 255),
				(int) (Math.random() * 255), (int) (Math.random() * 255));
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				
				   data[x][y] = c;
				

			}
		}
		return data;
	}
	
	public Color[][] getRandomColor2(final int size) {
		Color[][] data = new Color[size][size];
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);
		int a = (int) (Math.random() * 255);
		
		Color c = new Color( r,g,b);
		Color ct = new Color( r,g,b,a);
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if ( x < size/2 ) {
					data[x][y] = ct;
				}
				else {
				   data[x][y] = c;
				}

			}
		}
		return data;
	}

	public Color[][] getS1() {
		
		Color w = new Color( 255, 255, 255);
		Color r = new Color( 255, 0, 0);
		Color t = new Color( 0, 0, 0, 0);
		Color[][] data = new Color[][] {
				new Color[] { w,w,t,t,t,t,w,w },
				new Color[] { w,r,t,t,t,t,t,w },
				new Color[] { t,r,t,t,r,r,r,r },
				new Color[] { t,r,r,r,r,t,t,t },
				new Color[] { t,r,r,r,r,t,t,t },
				new Color[] { t,r,t,t,r,r,r,r },
				new Color[] { w,r,t,t,t,t,t,w },
				new Color[] { w,w,t,t,t,t,w,w }
				
		};
		
		return data;
	}
	
public Color[][] getS2() {
		
		Color w = new Color( 200, 200, 200);
		Color r = new Color( 0, 255, 255);
		Color b = new Color( 0, 0, 255);
		Color t = new Color( 0, 0, 0, 0);
		Color[][] data = new Color[][] {
				new Color[] { w,w,t,b,b,t,w,w },
				new Color[] { w,w,t,b,b,t,w,w },
				new Color[] { w,w,b,b,b,b,w,w },
				new Color[] { w,w,b,b,b,b,w,w },
				new Color[] { w,w,t,r,r,t,w,w },
				new Color[] { w,w,t,r,r,t,w,w },
				new Color[] { w,w,r,t,t,r,w,w },
				new Color[] { w,w,t,t,t,t,w,w }
				
		};
		
		return data;
	}
	
	public void init() {

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setAlwaysOnTop(true);
		//setUndecorated(true);
		

		setVisible(true);

		
		createBufferStrategy(2);
		_strategy = getBufferStrategy();
		createSprites();
		
	}

	public void createSprites() {
		_sprites = new ArrayList<Sprite>();
		for (int x = 0; x < 0; ++x) {

//			Sprite a = new Sprite((int) (Math.random() * DIM_X),
//					(int) (Math.random() * DIM_Y), getRandomColor(32));
//			Sprite b = new Sprite((int) (Math.random() * DIM_X),
//					(int) (Math.random() * DIM_Y), getRandomColor(16));
//			Sprite c = new Sprite((int) (Math.random() * DIM_X),
//					(int) (Math.random() * DIM_Y), getRandomColor(8));

			Sprite a = new Sprite((int) (_dim_x/2),
					(int) ( _dim_y/2), getRandomColor(1+(int) (Math.random() * 64)));
			Sprite b = new Sprite((int) (_dim_x/2),
					(int) ( _dim_y/2), getRandomColor(1+(int) (Math.random() * 64)));
			Sprite c = new Sprite((int) (_dim_x/2),
					(int)  ( _dim_y/2), getRandomColor2(1+(int) (Math.random() * 64)));
			Sprite d = new Sprite((int) (_dim_x/2),
					(int)  ( _dim_y/2), getRandomColor2(1+(int) (Math.random() * 64)));
			
			a.setVector((float) (Math.random() * 2),
					(float) (Math.random() * 2));
			b.setVector((float) (Math.random()* 2), -(float) (Math.random()* 2));
			c.setVector(-(float) (Math.random()* 2), (float) (Math.random()* 2));
			d.setVector(-(float) (Math.random()* 2), -(float) (Math.random()* 2));
			_sprites.add(a);
			_sprites.add(b);
			_sprites.add(c);
			_sprites.add(d);
		}
		for (int x = 0; x < 200; ++x) {


			Sprite a = new Sprite((int) (_dim_x/2),
					(int) ( _dim_y/2), getS1());
				a.setVector((float) (Math.random() * 2),
					(float) (Math.random() * 2));
				Sprite b = new Sprite((int) (_dim_x/2),
						(int) ( _dim_y/2), getS2());
					b.setVector((float) (Math.random() * 2),
						(float) (Math.random() * 2));
			_sprites.add(a);
			_sprites.add(b);
		
		}
	}
	
	public void update(Graphics g) {
		paint(g);
	}

	public void move() {
		for (Sprite s : _sprites) {
			if (s.get_x() > _dim_x || s.get_x() < 0) {
				s.inverse_x();
			}
			if (s.get_y() > _dim_y || s.get_y() < 0) {
				s.inverse_y();
			}
			s.move();
		}
	}

	public void render() {
		Graphics g = _strategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, _dim_x, _dim_y);
		for (Sprite s : _sprites) {
			s.render((Graphics2D) g);
		}
		g.dispose(); // needed? slow down?
		if( !_strategy.contentsLost() ) {
			_strategy.show();
		}
		
		// Tell the System to do the Drawing now, otherwise it can take a few
		// extra ms until
		// Drawing is done which looks very jerky
		Toolkit.getDefaultToolkit().sync(); // needed? slow down?
	}

}
