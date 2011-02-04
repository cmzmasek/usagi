package org.fxusagi.sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite {

	float _x_vector;
	float _y_vector;
	float _x_coord;
	float _y_coord;
	BufferedImage _bimage;

	Color[][] _data;

	//TODO "parents" -- coonection to other sprites
	//TODO animations: ONCE, CIRCULAR, BACK_and_forth, ?
	
	public Sprite(int start_x, int start_y, Color[][] data) {
		_x_coord = start_x;
		_y_coord = start_y;
		_data = data;
		
		_bimage = new BufferedImage(data.length, data.length, BufferedImage.TYPE_INT_ARGB);
	    
	    for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data.length; y++) {
				_bimage.setRGB(x, y, data[x][y].getRGB());
				
				
			}
		}
	}

	public void setVector(float x, float y) {
		_x_vector = x;
		_y_vector = y;
	}

	public float get_x() {
		return _x_coord;
	}
	
	public float get_y() {
		return _y_coord;
	}
	
	public void inverse_x() {
		_x_vector = -_x_vector;
	}
	
    public void inverse_y() {
    	_y_vector = -_y_vector;
	}
	
	public void move() {
		_x_coord += _x_vector;
		_y_coord += _y_vector;
	}

	public void render(Graphics2D g) {
		_bimage.setAccelerationPriority(1);
		g.drawImage(_bimage, (int) _x_coord, (int) _y_coord, null);
	
	}
}
