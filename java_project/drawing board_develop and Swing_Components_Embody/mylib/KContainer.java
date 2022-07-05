package mylib;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

class KContainer extends KComponent{
	public ArrayList<KComponent> componentList;
	
	public KContainer() {
		componentList = new ArrayList<>();
	}
	
	public KContainer(String text) { //KMenu생성자를 이걸로 조세형이 하라는데, 굳이 여기서?
		componentList = new ArrayList<>();
		this.text = text;
	}
	
	public void add(KComponent c) {
		componentList.add(c);
	}
	
	
	public void processMouseEvent(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (e.getID()) {
		case MouseEvent.MOUSE_PRESSED:
			start = e.getPoint();  break;
		case MouseEvent.MOUSE_RELEASED:
		    end = e.getPoint();
		    
		    Iterator<KComponent> itr = componentList.iterator();
			while (itr.hasNext()) {
				KComponent c = itr.next();
				if(start != null && end != null && c.isSelected(start) && c.isSelected(end)){
					c.processMouseEvent(e);
				}
				else { //여기서 메뉴아이템이 눌리면 그 눌린 메뉴아이템 포함된 쪽 paint 다 없애는 걸 여기서 하라는 것 같은데?
//						addFigure(figType, start, end);
				}
			}
			break;
		}
//		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Iterator<KComponent> itr = componentList.iterator();
		while(itr.hasNext()) {
			itr.next().paint(g);
		}
	}
}
