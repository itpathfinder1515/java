import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

abstract class Figure{
	public int a, b, c, d, width, height;
	abstract public void draw(Graphics g);
}

class Rectangle extends Figure{
	
	public Rectangle(int x1, int y1, int x2, int y2) {
		a = x1;
		b = y1;
		width = x2 - x1;
		height = y2 - y1;
	}
	@Override
	public void draw(Graphics g) {
		g.drawRect(a, b, width, height);
	}
}

class Oval extends Figure{
	
	public Oval(int x1, int y1, int x2, int y2) {
		a = x1;
		b = y1;
		width = x2 - x1;
		height = y2 - y1;
	}
	@Override
	public void draw(Graphics g) {
		g.drawOval(a, b, width, height);
	}
}

class Line extends Figure{
	
	public Line(int x1, int y1, int x2, int y2) {
		a = x1;
		b = y1;
		c = x2;
		d = y2;
	}
	@Override
	public void draw(Graphics g) {
		g.drawLine(a, b, c, d);
	}
}

class MyButton{
	public String figure;
	public int a, b, c, d;
	
	public MyButton(String fig) {//자기 버튼 그림그리는 것
		figure = fig;
	}
	
	public void setBounds(int x1, int y1, int x2, int y2) {
		a = x1;
		b = y1;
		c = x2;
		d = y2;
	}
	public void drawButton(Graphics g) {
		g.drawRect(a, b, c, d);
		g.drawString(figure, a, b);
	}
	
	public boolean isClicked(Point end) { //마우스 위치 줬을 때, 내 사각형 안에 들어있는지 아닌지 알려주는 함수
		if((end.x > a && end.x < a+c) && (end.y > b && end.y < b+d)) { //마우스 위치
			return true;
		}
		else {
			return false;
		}
	}
}

class MyPanel extends JPanel{
	public MyFrame myFrame;
	public ArrayList<MyButton> MyButtonList;
	public Point start, end;
	public int figuretype;
	
	class MyMouseListener implements MouseListener{ //마우스 처리할 때는 그림을 그리는 게 아니고 도형만 생성해주는거다.

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			start = e.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			end = e.getPoint();
			
			Iterator<MyButton> itr = MyButtonList.iterator();
			while (itr.hasNext()) {
				if(start != null && end != null && itr.next().isClicked(end)){
					figuretype = getButton(end);
				}
				else {
					addFigure(figuretype, start, end);
				}
			}
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public MyPanel(MyFrame f) {
		this.myFrame = f;
		setBackground(new Color(200, 255, 255));
		addMouseListener(new MyMouseListener());
		
		MyButtonList = new ArrayList<>();
		start = null;
		end = null;
		figuretype = 0;
		
		
		MyButton btnRect = new MyButton("사각형");
		MyButton btnOval = new MyButton("타원");
		MyButton btnLine = new MyButton("선분");
		btnRect.setBounds(10, 60, 70, 25);  // 위치와 크기를 결정한다. x=10 y=60 width=70, height=25 이다.
		btnOval.setBounds(90, 60, 70, 25);
		btnLine.setBounds(170, 60, 70, 25);

		this.add(btnRect);
		this.add(btnOval);
		this.add(btnLine);
	}
	
	@Override 
	public void paint(Graphics g) { //panel에서 paint하는 게 정상이다. 
		super.paint(g);

		Iterator<MyButton> itr1 = MyButtonList.iterator();
		while (itr1.hasNext()) {
			itr1.next().drawButton(g);
		}

		Iterator<Figure> itr2 = myFrame.MyArrayList.iterator();
		while (itr2.hasNext()) {
			itr2.next().draw(g);
		}
	}
	
	public void addFigure(int type, Point a, Point b) {
		if(type == 0) {
			myFrame.add(new Rectangle(a.x, a.y, b.x, b.y));
		}
		else if(type == 1) {
			myFrame.add(new Oval(a.x, a.y, b.x, b.y));
		}
		else {
			myFrame.add(new Line(a.x, a.y, b.x, b.y));
		}
	}
	
	public void add(MyButton btn) {
		MyButtonList.add(btn);
	}
	
	public int getButton(Point b) {
		if(b !=null){
			if((b.x > 10 && b.x < 80) && (b.y > 60 && b.y < 85)) { //마우스 위치
				return 0;
			}
			else if((b.x > 90 && b.x < 160)  && (b.y > 60 && b.y < 85)) {
				return 1;
			}
			else if((b.x > 170 && b.x < 240)  && (b.y > 60 && b.y < 85)) {
				return 2;
			}
			else {
				return -1;
			}
		}
		return -1;
	}
	
}


class MyFrame extends JFrame{
	public ArrayList<Figure> MyArrayList; //도형 객체는 Frame에 있어야 한다고 했다.
	
	public MyFrame(String title) {
		super(title);
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyArrayList = new ArrayList<>();
	}
	public void add(Figure fig) { 
		MyArrayList.add(fig);
	}	
	
}


public class Main { //main은 frame 만 만들어주고 끝이다. 아무것도 하지 않는다.
	public static void main(String[] args) {
		MyFrame f = new MyFrame("도형을 그리는 그림판");

		f.setContentPane(new MyPanel(f));
		f.setVisible(true); //setvisible 위치를 맨 뒤로 바꾸면 된다. //이유가 뭐지?

	}
}