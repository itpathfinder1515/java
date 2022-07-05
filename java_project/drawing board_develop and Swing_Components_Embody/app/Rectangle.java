package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

class Rectangle extends Figure{
	
	public Rectangle(Color e, int x1, int y1, int x2, int y2) {
		color = e;
		a = x1;
		b = y1;
		c = x2;
		d = y2;
		width = x2 - x1;
		height = y2 - y1;
	}
	@Override
	public void draw(Graphics g) {  
		g.setColor(color);
		g.drawRect(a, b, width, height);
		//g.setColor(new Color(200, 255, 255));
		//g.fillRect(a+1, b+1, width-1, height-1);
	}
	
	@Override
	public void move(Point start, Point end) {
		int x = end.x - start.x;
		int y = end.y - start.y;
		a += x;
		b += y;
		c += x;
		d += y;
	}
}

class Oval extends Figure{
	
	public Oval(Color e, int x1, int y1, int x2, int y2) {
		color = e;
		a = x1;
		b = y1;
		c = x2;
		d = y2;
		width = x2 - x1;
		height = y2 - y1;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawOval(a, b, width, height);
		//g.setColor(new Color(200, 255, 255));
		//g.fillOval(a+1, b+1, width-2, height-2);
	}
	
	@Override
	public void move(Point start, Point end) {
		int x = end.x - start.x;
		int y = end.y - start.y;
		a += x;
		b += y;
		c += x;
		d += y;
	}
}

class Line extends Figure{
	
	public Line(Color e, int x1, int y1, int x2, int y2) {
		color = e;
		a = x1;
		b = y1;
		c = x2;
		d = y2;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(a, b, c, d);
	}
	
	@Override
	public void move(Point start, Point end) {
		int x = end.x - start.x;
		int y = end.y - start.y;
		a += x;
		b += y;
		c += x;
		d += y;
	}
}

