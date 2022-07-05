package mylib;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class KPanel extends KContainer{
	public ArrayList<Figure> MyArrayList; //도형 객체는 Frame에 있어야 한다고 했다.
	public Point start, end;
	public int figType;
	public Color color;
	
	public KPanel() {
		MyArrayList = new ArrayList<>();
		start = null;
		end = null;

		figType = 0;
		color = Color.RED;
		
		a = 8;
		b = 183;
		width = 783;
		height = 410;
		
//		KButton kb = new KButton("testbutton");
//		kb.setBounds(100, 100, 100, 30);
//		kb.setActionCommand("select_rect");
//		add(kb);
//		kb.addKActionListener( new KActionListener() {
//			
//			
//			@Override			
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String cmd = e.getActionCommand(); 
//				switch(cmd) { // 메뉴 아이템의 종류 구분
//					case "select_rect" :
//						System.out.println("Button command " + e.getActionCommand() + " clicked"); break;
//				}
//			}
//		});
		
		KCheckBox kcb = new KCheckBox("KCheckBox");
		kcb.setBounds(600, 200, 10, 10);
		kcb.setActionCommand("select_KCheckBox");
		add(kcb);
		kcb.addKActionListener( new KActionListener() {
			
			@Override			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cmd = e.getActionCommand(); 
				switch(cmd) { // 메뉴 아이템의 종류 구분
					case "select_KCheckBox" :
						kcb.ToggleButton = !kcb.ToggleButton; break;
				}
			}
		});
		
	}
	
	@Override
	public void processMouseEvent(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (e.getID()) {
		case MouseEvent.MOUSE_PRESSED:
			start = e.getPoint();  break;
		case MouseEvent.MOUSE_RELEASED:
		    end = e.getPoint();
		    
		    if(start != null && end != null && figType > -1) {
				addFigure(color, figType, start, end);	
			}
			break;
		}
	}
	
	@Override 
	public void paint(Graphics g) { //panel에서 paint하는 게 정상이다. 
		super.paint(g);
		
		Iterator<Figure> itr = MyArrayList.iterator();
		while (itr.hasNext()) {
			itr.next().draw(g);
		}
	}
	
	public void add(Figure fig) { 
		MyArrayList.add(fig);
	}	
	
	public void addFigure(Color e, int type, Point a, Point b) {
		if(type == 0) {
			add(new Rectangle(e, a.x, a.y, b.x, b.y));
		}
		else if(type == 1) {
			add(new Oval(e, a.x, a.y, b.x, b.y));
		}
		else if(type == 2){
			add(new Line(e, a.x, a.y, b.x, b.y));
		}
	}
}