package mylib;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class KComponent{
	public int a, b, width, height;
	public String text;
	public Point start, end;
	
	public void paint(Graphics g) { //여기에서 뭘 할게 있나?
		
	}
	
	public void processMouseEvent(MouseEvent e) { //여기에서 뭘 할게 있나?
		// TODO Auto-generated method stub
		
	}
	
	public void setBounds(int x, int y, int w, int h) {
		a = x;
		b = y;
		width = w;
		height = h;
	}
	
	public boolean isSelected(Point end) { //마우스 위치 줬을 때, 내 사각형 안에 들어있는지 아닌지 알려주는 함수
		if((end.x > a && end.x < a+width) && (end.y > b && end.y < b+height)) { //마우스 위치
			return true;
		}
		else {
			return false;
		}
	}
}
