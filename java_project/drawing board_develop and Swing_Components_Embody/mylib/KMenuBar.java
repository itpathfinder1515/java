package mylib;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Iterator;

class KMenuBar extends KContainer{
	public int idx;
	
	public KMenuBar() {
		super();
		idx = 0;
		
		a = 8;
		b = 61;
		width = 783;
		height = 30;
	}
	
	public void add(KMenu c) {
		super.add(c);
	} 
	
	@Override //override써주는게 맞나?
	public void processMouseEvent(MouseEvent e) {
		// TODO Auto-generated method stub
		Iterator<KComponent> itr = componentList.iterator(); 
		while (itr.hasNext()) {
			KMenu myMenu = (KMenu) itr.next();
			if(myMenu.isSelected(e.getPoint())) {
				myMenu.processMouseEvent(e); //이것도 연쇄적으로 이렇게 만들어야 했다.
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(a, b, width, height);
		
		idx = 0; //Menu그리기, 꼭 여기서 해야 하나? 비효율적인 것 같은데?
		Iterator<KComponent> itr = componentList.iterator(); 
		while (itr.hasNext()) {
			KMenu myMenu = (KMenu) itr.next();
			myMenu.setBounds(8+100*idx, 61, 100, 30);	
			myMenu.setMenuBar(this);
			myMenu.paint(g); //메뉴는 container에 들어가는 게 아니라 메뉴바에 add가 되기 때문에 paint를 여기서 불러줘야 함
			idx++;
		}
	}
}
