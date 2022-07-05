package mylib;

import java.awt.Graphics;

public class KToolBar extends KContainer{
	public int idx;
	
	public KToolBar() {
		super();
		idx = 0;
		
		a = 8;
		b = 31;
		width = 783;
		height = 30;
	}
	
	public void add(KMenu c) {
		super.add(c);
	} 
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(a, b, width, height);
	}	
}
