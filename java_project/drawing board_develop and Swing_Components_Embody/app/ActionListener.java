import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

class ColorActionListener implements KActionListener{
	public KPanel myPanel;
	
	public ColorActionListener(KPanel p) {
		myPanel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand(); 
		switch(cmd) { // 메뉴 아이템의 종류 구분
			case "Red" :
				myPanel.color = Color.RED; break;
			case "Blue" :
				myPanel.color = Color.BLUE; break;
			case "Yellow" :
				myPanel.color = Color.YELLOW; break;
	}
	
	}
	
}

class FigureActionListener implements KActionListener{
	public KPanel myPanel;
	
	public FigureActionListener(KPanel p) {
		myPanel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand(); 
		switch(cmd) { // 메뉴 아이템의 종류 구분
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

class GroupActionListener implements KActionListener{
	public KPanel myPanel;
	
	public GroupActionListener(KPanel p) {
		myPanel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { //여기서 myPanel 어떻게 참조할건데?
		// TODO Auto-generated method stub
		myPanel.figType = -1;
		Iterator<Figure> itr = myPanel.MyArrayList.iterator();
		GroupFigure newGF = new GroupFigure();
		if(! myPanel.MyArrayList.isEmpty()) {
			while(itr.hasNext()) { //각 객체에 대해서
				Figure gf = itr.next();
				if(gf != null && gf.isInDrag(myPanel.start, myPanel.end)) {//마우스 드래그 범위에 있는지 
					newGF.add(gf); //다음 그룹에 넣고
					myPanel.MyArrayList.remove(gf); //기존에 있는 것 list에서 뺌, 실제로 null이 되지는 않을거다, linked list에서 연결만 사라지게 함
				}
			}
			if(! newGF.MyGroupFigureList.isEmpty()) {
				myPanel.add(newGF);
				newGF.setXY();
			}
		}
	}
	
}

class MoveActionListener implements KActionListener{
	public KPanel myPanel;
	
	public MoveActionListener(KPanel p) {
		myPanel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { //제대로 안됨 이유? //메뉴선택 후 해야하는데 그 순간 일어나버리는 거라서 start와 end점을 제때 못 담는다
		// TODO Auto-generated method stub
		myPanel.figType = -1;
		Iterator<Figure> itr = myPanel.MyArrayList.iterator();
		if(! myPanel.MyArrayList.isEmpty()) {
			while(itr.hasNext()) { //각 객체에 대해서
				Figure gf = itr.next();
				if(gf != null && gf.isIn(myPanel.start)) {
					gf.move(myPanel.start, myPanel.end);
				}
			}
		}
	}
	
}
