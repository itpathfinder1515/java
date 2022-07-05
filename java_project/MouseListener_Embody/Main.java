import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
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



interface MyActionListenerI{
	public void actionPerformed(ActionEvent e);
}

class MyActionListenerOne implements MyActionListenerI{
	public MyPanel myPanel;
	
	public MyActionListenerOne(MyPanel p) {
		myPanel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		myPanel.myButton = (MyButton) e.getSource();
		if(myPanel.myButton == myPanel.MyButtonList.get(0)) {
			myPanel.figType = 0;
		}
		else if(myPanel.myButton == myPanel.MyButtonList.get(1)) {
			myPanel.figType = 1;
		}
		else if(myPanel.myButton == myPanel.MyButtonList.get(2)){
			myPanel.figType = 2;
		}
			
	}
	
}

class MyButton{
	public ArrayList<MyActionListenerI> MyActionListenerList;
	public String figure;
	public int a, b, width, height;
	public ActionEvent ae;
	
	public MyButton(String fig) {//자기 버튼 그림그리는 것
		figure = fig;
		MyActionListenerList = new ArrayList<>();
		ae = new ActionEvent(this, MouseEvent.MOUSE_CLICKED, "");
	}
	
	public void setBounds(int x, int y, int w, int h) {
		a = x;
		b = y;
		width = w;
		height = h;
	}
	public void drawButton(Graphics g) {
		g.drawRect(a, b, width, height);
		g.drawString(figure, a, b);
	}
	
	public boolean isClicked(Point end) { //마우스 위치 줬을 때, 내 사각형 안에 들어있는지 아닌지 알려주는 함수
		if((end.x > a && end.x < a+width) && (end.y > b && end.y < b+height)) { //마우스 위치
			return true;
		}
		else {
			return false;
		}
	}
	
	public void processMouseEvent(MouseEvent e) {
		   // 처리할 내용
		switch (e.getID()) {
			case MouseEvent.MOUSE_RELEASED:
				Iterator<MyActionListenerI> itr = MyActionListenerList.iterator(); //착각하지마 이거 지금은 MyActionListerOne밖에 없어, 그것만 가지고 가는거야
				while (itr.hasNext()) {
					itr.next().actionPerformed(ae);
				}
				break;
		}
	}

	public void addMyActionListener(MyActionListenerI myActionListenerOne) {
		// TODO Auto-generated method stub
		MyActionListenerList.add(myActionListenerOne);
	}
}

class MyPanel extends JPanel{
	public MyFrame myFrame;
	public ArrayList<MyButton> MyButtonList;
	public Point start, end;
	public MyButton myButton;
	public int figType;
	
	public MyPanel(MyFrame f) {
		this.myFrame = f;
		setBackground(new Color(200, 255, 255));
		
		MyButtonList = new ArrayList<>();
		start = null;
		end = null;
		myButton = null;
		figType = 0;
		
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		MyButton btnRect = new MyButton("사각형");
		MyButton btnOval = new MyButton("타원");
		MyButton btnLine = new MyButton("선분");
		btnRect.setBounds(10, 60, 70, 25);  // 위치와 크기를 결정한다. x=10 y=60 width=70, height=25 이다.
		btnOval.setBounds(90, 60, 70, 25);
		btnLine.setBounds(170, 60, 70, 25);

		add(btnRect);
		add(btnOval);
		add(btnLine);
		
		btnRect.addMyActionListener(new MyActionListenerOne(this)); 
		btnOval.addMyActionListener(new MyActionListenerOne(this)); 
		btnLine.addMyActionListener(new MyActionListenerOne(this)); 
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
		else if(type == 2){
			myFrame.add(new Line(a.x, a.y, b.x, b.y));
		}
	}
	
	public void add(MyButton btn) {
		MyButtonList.add(btn);
	}
	
	@Override public void processMouseEvent(MouseEvent e) {
		   // 처리할 내용
		switch (e.getID()) {
			case MouseEvent.MOUSE_PRESSED:
				start = e.getPoint();  break;
			case MouseEvent.MOUSE_RELEASED:
			    end = e.getPoint();
			    
			    Iterator<MyButton> itr = MyButtonList.iterator();
				while (itr.hasNext()) {
					MyButton b = itr.next();
					if(start != null && end != null && b.isClicked(end)){
						b.processMouseEvent(e);
					}
					else {
						if(myButton != null) {
							addFigure(figType, start, end);
						}
					}
				}
				break;
		}
		repaint();
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
