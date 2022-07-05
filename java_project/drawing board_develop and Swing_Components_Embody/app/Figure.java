package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

abstract class Figure {
	public int a, b, c, d, width, height;
	public Color color;
	
	public abstract void draw(Graphics g);
	public abstract void move(Point start, Point end);
	
	public boolean isInDrag(Point start, Point end) {
		if(start.x<a && start.y<b && end.x>c && end.y>d) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isIn(Point start) {
		if(a<start.x && start.x<c && b<start.y && start.y<d) {
			return true;
		}
		else{
			return false;
		}
	}
}
