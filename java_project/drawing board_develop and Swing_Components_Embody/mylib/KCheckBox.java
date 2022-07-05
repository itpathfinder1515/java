package mylib;

import java.awt.Graphics;
import java.awt.Point;

class KCheckBox extends KAbstractButton{
	public boolean ToggleButton;
	
	public KCheckBox(String fig) {
		super(fig);
		ToggleButton = false;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint(Graphics g) { //체크박스 어떻게 꾸밀지
		g.drawRect(a, b, width, height);
		g.drawString(text, a+width+4, b+height);
		if(ToggleButton == true) {
			g.drawLine(a, b, a+width/2, b+height);
			g.drawLine(a+width/2, b+height, a+10, b);
		}
	}
	
}
