package mylib;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Iterator;

class KMenuItem extends KAbstractButton{
	public KMenu myMenu;
	protected boolean visible;
	
	public KMenuItem(String text) {
		super(text);
		visible = false;
	}
	
	public void setBounds(int x, int y, int w, int h) {
		a = x;
		b = y;
		width = w;
		height = h;
	}
	
	public void setMenu(KMenu m) {
		myMenu = m;
	}
	
	@Override
	public void processMouseEvent(MouseEvent e) { //이게 Frame에 없으니까 Menu에다 넣어야 함
		// TODO Auto-generated method stub	
		
		if(visible) {
			System.out.println(text + "메뉴아이템 눌림");
			Iterator<KActionListener> itr3 = actionListenerList.iterator(); //착각하지마 이거 지금은 MyActionListerOne밖에 없어, 그것만 가지고 가는거야
			while (itr3.hasNext()) {
				itr3.next().actionPerformed(ae);
			}
		}
		
		Iterator<KComponent> itr = myMenu.componentList.iterator(); 
		while (itr.hasNext()) { 
			KMenuItem myMenuItem = (KMenuItem) itr.next();
				if(myMenuItem.visible == true) {
					myMenuItem.visible = !myMenuItem.visible;
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if(visible) {
			g.drawRect(a, b, width, height);
			g.drawString(text, a+20, b+20);
			//System.out.println("메뉴아이템 paint");
		}
	}
}


