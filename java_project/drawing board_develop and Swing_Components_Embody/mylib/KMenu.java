package mylib;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Iterator;

class KMenu extends KContainer{ //다 참조 가지고 있어야 한다.
	public KMenuBar myMenuBar;
	public int idx;
	
	public KMenu(String text) {
		super(text);
	} 
	
	public void add(KMenuItem c) {
		super.add(c);
	}
	
	@Override //override써주는게 맞나?
	public void processMouseEvent(MouseEvent e) { //이게 Frame에 없으니까 작동이 안 돼서 안 보여지는 것이다.
		// TODO Auto-generated method stub
		
		Iterator<KComponent> itr = myMenuBar.componentList.iterator(); 
		while (itr.hasNext()) {
			KMenu myMenu = (KMenu) itr.next();
			Iterator<KComponent> itr2 = myMenu.componentList.iterator(); 
			while (itr2.hasNext()) { 
				KMenuItem myMenuItem = (KMenuItem) itr2.next();
				if(myMenuItem.visible == true) {
					myMenuItem.visible = !myMenuItem.visible;
				}
			}
		}
		
		
		Iterator<KComponent> itr3 = componentList.iterator(); 
		while (itr3.hasNext()) { 
			KMenuItem myMenuItem = (KMenuItem) itr3.next();
			myMenuItem.visible = !myMenuItem.visible;
			//System.out.println(text + "마우스이벤트 작동됨");
		}	
	}
	
	public void setBounds(int x, int y, int w, int h) {
		a = x;
		b = y;
		width = w;
		height = h;
	}
	
	public void setMenuBar(KMenuBar mb) {
		myMenuBar = mb;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(a, b, width, height);
		g.drawString(text, a+20, b+20);
		
		idx = 0; //일단 메뉴아이템 생성은 무조건 하고 visible로 보여주는 것이다.
		Iterator<KComponent> itr = componentList.iterator(); 
		while (itr.hasNext()) { 
			KMenuItem myMenuItem = (KMenuItem) itr.next();
			myMenuItem.setBounds(a, b+30+30*idx, 100, 30);	
			myMenuItem.setMenu(this);
			//myMenuItem.visible = !myMenuItem.visible; 이거를 어디다 둬야 할까?
			myMenuItem.paint(g); //메뉴아이템는 container에 들어가는 게 아니라 메뉴바에 add가 되기 때문에 paint를 여기서 불러줘야 함
			idx++;
			//System.out.println(text + "메뉴Item paint됨, 보여지지 않을 뿐");
		}
	}
	
}
