package app;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

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
