package mylib;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

abstract class KAbstractButton extends KComponent{
	//버튼에서 이벤트 처리할 때 어차피 ActionEvent 객체를 생성해야한다. new ActionEvent(c, MouseEvent.MOUSE_CLICKED, "")
	public ArrayList<KActionListener> actionListenerList;
	public ActionEvent ae;
	
	public KAbstractButton(String fig) {
		text = fig;
		actionListenerList = new ArrayList<>();
		ae = new ActionEvent(this, MouseEvent.MOUSE_CLICKED, fig);
	}
	
	@Override
	public void processMouseEvent(MouseEvent e) {
		// 처리할 내용
		Iterator<KActionListener> itr = actionListenerList.iterator(); //착각하지마 이거 지금은 MyActionListerOne밖에 없어, 그것만 가지고 가는거야
		while (itr.hasNext()) {
			itr.next().actionPerformed(ae);
		}
	}
	
	public void setActionCommand(String str) { //event객체에다 해야 한다.
		// TODO Auto-generated method stub
		ae = new ActionEvent(this, MouseEvent.MOUSE_CLICKED, str);
	}
	
	public void addKActionListener(KActionListener kActionListenerOne) {//걍 여기다 쓰고 밑에다 안 쓸래
		actionListenerList.add(kActionListenerOne);
	}
}
