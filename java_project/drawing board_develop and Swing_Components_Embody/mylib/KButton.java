package mylib;

import java.awt.Graphics;

class KButton extends KAbstractButton{
	
	public KButton(String fig) {//자기 버튼 그림그리는 것
		super(fig);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawRect(a, b, width, height);
		g.drawString(text, a+20, b+20);
	}
	
}