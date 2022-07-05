package mylib;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

class KFrame extends KContainer{ //KFrame이 바깥 Frame인 것 처럼 생각해라, 은 끼워 넣는 container 역할
	protected KMenuBar theMenuBar;
	protected KToolBar theToolBar;
	protected KContainer contentPane; //★이게 panel역할이다 //이걸 어떻게 담지?
	
//	public KFrame() {
//		contentPane
//	}
	
	public void setKMenuBar(KMenuBar mb) {
		// 원래 메뉴바는 frame에 있는게 정상이지만...
		add(mb);
		theMenuBar = mb;
	}
	
	public void setKToolBar(KToolBar tb) {
		add(tb);
		theToolBar = tb;
	}
	
	public void setContentPane(KContainer kc) {
		add(kc);
		contentPane = kc;
	}
	
	@Override
	public void processMouseEvent(MouseEvent e) { //이걸 보면 Container에 
		super.processMouseEvent(e);
		//System.out.println("Mouse Event of KFrame");
	}
	
	@Override
	public void paint(Graphics g) { //super.paint()를 하면 특별히 할 일이 없음
		// TODO Auto-generated method stub
		super.paint(g); 
	}
	
	
}
