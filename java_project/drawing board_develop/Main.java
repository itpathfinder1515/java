import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



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

class GroupFigure extends Figure{
	public ArrayList<Figure> MyGroupFigureList;
	
	public GroupFigure () {
		MyGroupFigureList = new ArrayList<>();
	}
	
	public void add(Figure fig) {
		MyGroupFigureList.add(fig);
	}
	
	public void draw(Graphics g) {
		Iterator<Figure> itr = MyGroupFigureList.iterator();
		while(itr.hasNext()) {
			itr.next().draw(g);
		}
	}

	public void setXY() {
		int x1 = MyGroupFigureList.get(0).a;
		int y1 = MyGroupFigureList.get(0).b;
		int x2 = MyGroupFigureList.get(0).c;
		int y2 = MyGroupFigureList.get(0).d;
		Iterator<Figure> itr = MyGroupFigureList.iterator();
		while(itr.hasNext()) {
			Figure gf = itr.next();
			if(gf.a < x1) {
				a = gf.a;
				b = gf.b;
			}
			else if(gf.b < y1) {
				b = gf.b;
			}
			else if(gf.c > x2) {
				c = gf.c;
			}
			else if(gf.d > y2) {
				d = gf.d;
			}
		}
	}
	
	public void move(Point start, Point end) {
		int x = end.x - start.x;
		int y = end.y - start.y;
		Iterator<Figure> itr = MyGroupFigureList.iterator();
		while(itr.hasNext()) {
			Figure gf = itr.next();
			gf.a += x;
			gf.b += y;
			gf.c += x;
			gf.d += y;
		}
	}
}



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



class ColorActionListener implements ActionListener{
	public MyPanel myPanel;
	
	public ColorActionListener(MyPanel p) {
		myPanel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand(); 
		switch(cmd) { // ?????? ???????????? ?????? ??????
			case "Red" :
				myPanel.color = Color.RED; break;
			case "Blue" :
				myPanel.color = Color.BLUE; break;
			case "Yellow" :
				myPanel.color = Color.YELLOW; break;
	}
	
	}
	
}



class FigureActionListener implements ActionListener{
	public MyPanel myPanel;
	
	public FigureActionListener(MyPanel p) {
		myPanel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand(); 
		switch(cmd) { // ?????? ???????????? ?????? ??????
			case "None" :
				myPanel.figType = -1; break;
			case "Rect" :
				myPanel.figType = 0; break;
			case "Oval" :
				myPanel.figType = 1; break;
			case "Line" :
				myPanel.figType = 2; break;
		}
	
	}
	
}

class GroupActionListener implements ActionListener{
	public MyFrame myFrame;
	public MyPanel myPanel;
	
	public GroupActionListener(MyFrame f, MyPanel p) {
		myFrame = f;
		myPanel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { //????????? myPanel ????????? ????????????????
		// TODO Auto-generated method stub
		myPanel.figType = -1;
		Iterator<Figure> itr = myFrame.MyArrayList.iterator();
		GroupFigure newGF = new GroupFigure();
		if(! myFrame.MyArrayList.isEmpty()) {
			while(itr.hasNext()) { //??? ????????? ?????????
				Figure gf = itr.next();
				if(gf != null && gf.isInDrag(myPanel.start, myPanel.end)) {//????????? ????????? ????????? ????????? 
					newGF.add(gf); //?????? ????????? ??????
					myFrame.MyArrayList.remove(gf); //????????? ?????? ??? list?????? ???, ????????? null??? ????????? ????????????, linked list?????? ????????? ???????????? ???
				}
			}
			if(! newGF.MyGroupFigureList.isEmpty()) {
				myFrame.add(newGF);
				newGF.setXY();
			}
		}
	}
	
}

class MoveActionListener implements ActionListener{
	public MyFrame myFrame;
	public MyPanel myPanel;
	
	public MoveActionListener(MyFrame f, MyPanel p) {
		myFrame = f;
		myPanel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { //????????? ?????? ??????? //???????????? ??? ??????????????? ??? ?????? ?????????????????? ????????? start??? end?????? ?????? ??? ?????????
		// TODO Auto-generated method stub
		myPanel.figType = -1;
		Iterator<Figure> itr = myFrame.MyArrayList.iterator();
		if(! myFrame.MyArrayList.isEmpty()) {
			while(itr.hasNext()) { //??? ????????? ?????????
				Figure gf = itr.next();
				if(gf != null && gf.isIn(myPanel.start)) {
					gf.move(myPanel.start, myPanel.end);
				}
			}
		}
	}
	
}

class MyPanel extends JPanel{
	public MyFrame myFrame;
	public Point start, end;
	public int figType;
	public Color color;
	
	class MyMouseListener extends MouseAdapter{ //????????? ????????? ?????? ????????? ????????? ??? ????????? ????????? ?????????????????????.

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			start = e.getPoint();
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			end = e.getPoint();
			
			if(start != null && end != null && figType > -1) {
				addFigure(color, figType, start, end);	
			}
			
			repaint();
		}
		
	}
	
	public MyPanel(MyFrame f) {
		this.myFrame = f;
		setBackground(new Color(200, 255, 255));
	
		start = null;
		end = null;

		figType = 0;
		color = Color.RED;
		
		addMouseListener(new MyMouseListener());

	}
	
	@Override 
	public void paint(Graphics g) { //panel?????? paint?????? ??? ????????????. 
		super.paint(g);
		
		Iterator<Figure> itr = myFrame.MyArrayList.iterator();
		while (itr.hasNext()) {
			itr.next().draw(g);
		}
	}
	
	public void addFigure(Color e, int type, Point a, Point b) {
		if(type == 0) {
			myFrame.add(new Rectangle(e, a.x, a.y, b.x, b.y));
		}
		else if(type == 1) {
			myFrame.add(new Oval(e, a.x, a.y, b.x, b.y));
		}
		else if(type == 2){
			myFrame.add(new Line(e, a.x, a.y, b.x, b.y));
		}
	}
	
}


class MyFrame extends JFrame{
	public ArrayList<Figure> MyArrayList; //?????? ????????? Frame??? ????????? ????????? ??????.
	public MyPanel myPanel;
	
	public MyFrame(String title) {
		super(title);
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyArrayList = new ArrayList<>();
	}
	
	public void add(Figure fig) { 
		MyArrayList.add(fig);
	}	
	
	public void setMenuBar(MyPanel p) {
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		
		
		//??????
		JMenu ColorMenu = new JMenu("Color");
		
		JMenuItem itemRed = new JMenuItem("Red");
		JMenuItem itemBlue = new JMenuItem("Blue");
		JMenuItem itemYellow = new JMenuItem("Yellow");
		
		itemRed.addActionListener(new ColorActionListener(p));
		itemBlue.addActionListener(new ColorActionListener(p));
		itemYellow.addActionListener(new ColorActionListener(p));
		
		ColorMenu.add(itemRed);
		ColorMenu.add(itemBlue);
		ColorMenu.add(itemYellow);
		
		//??????
		JMenu figureMenu = new JMenu("Figure");
		
		JMenuItem itemNone = new JMenuItem("None");
		JMenuItem itemRect = new JMenuItem("Rect");
		JMenuItem itemOval = new JMenuItem("Oval");
		JMenuItem itemLine = new JMenuItem("Line");
		
		itemNone.addActionListener(new FigureActionListener(p)); 
		itemRect.addActionListener(new FigureActionListener(p)); 
		itemOval.addActionListener(new FigureActionListener(p)); 
		itemLine.addActionListener(new FigureActionListener(p)); 
		
		figureMenu.add(itemNone);
		figureMenu.add(itemRect);
		figureMenu.add(itemOval);
		figureMenu.add(itemLine);
		
		//?????????
		JMenu groupMenu = new JMenu("Groupage");
		
		JMenuItem itemGroup = new JMenuItem("Group");
		itemGroup.addActionListener(new GroupActionListener(this, p));

		groupMenu.add(itemGroup);
		
		//??????
		JMenu moveMenu = new JMenu("Move");
		
		JMenuItem itemMove = new JMenuItem("Move");
		itemMove.addActionListener(new MoveActionListener(this, p));
		
		moveMenu.add(itemMove);
		
		mb.add(ColorMenu);
		mb.add(figureMenu);
		mb.add(groupMenu);
		mb.add(moveMenu);
	}
}


public class Main { //main??? frame ??? ??????????????? ?????????. ???????????? ?????? ?????????.
	public static void main(String[] args) {
		MyFrame f = new MyFrame("????????? ????????? ?????????");
		MyPanel p = new MyPanel(f);
	
		f.setContentPane(p);
		f.setMenuBar(p);
		f.setVisible(true); //setvisible ????????? ??? ?????? ????????? ??????. //????????? ???????

	}
}
