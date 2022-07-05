package mylib;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

//프레임 자체를 만드는 것이 좀 복잡해서 KFrame 을 우리가 만드는 컴포넌트들의
//top window 처럼 사용한다.
//이것을 JFrame 안에 넣어서 사용하기 때문에 필요한 클래스가 AdapterFrame이다.
//여기는 건드리는 것 아니다. 신경 안 써도 된다.
class KAdapterFrame extends JFrame{ //응용 프로그램, 우리가 만든 것 사용할 수 있게 하려고 이렇게 함, PainterKFrame을 KFrame에 끼워넣기위해서 만든 게 KAdapterFrame
	KFrame myFrame;
	public KAdapterFrame() {
		myFrame = null;
				
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		getContentPane().setBackground(Color.white);
	}
	public void setKFrame(KFrame k) {
		myFrame = k;
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (myFrame != null) {
			myFrame.paint(g);
		}
	}
	
	@Override public void processMouseEvent(MouseEvent e) {
		if (myFrame != null) {
			myFrame.processMouseEvent(e);
			if(e.getID() == MouseEvent.MOUSE_CLICKED) {
				repaint(); //이렇게 해도 되?	
			}
		}
	}

}
