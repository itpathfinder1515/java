package app;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import mylib.*;

//여기는 응용

class PainterKFrame extends KFrame{
	
	public PainterKFrame() {
		super();
	}
	
	public void initGUI() {
		/*
		어떤 메뉴를 더할 지, 그것은 여기에서 하면 된다.(Panel생성자에서 하는 짓은 따로, 그거를 KFrame의 contentPane에 넣어야 한다.)
		*/
		
		//ContentPane
		KPanel p = new KPanel();
		setContentPane(p);
		
		//ToolBar
		KToolBar tb = new KToolBar();
		setKToolBar(tb);
		
		//MenuBar
		KMenuBar mb = new KMenuBar();
		setKMenuBar(mb);
		
		//색상
		KMenu ColorMenu = new KMenu("Color");
		
		KMenuItem itemRed = new KMenuItem("Red");
		KMenuItem itemBlue = new KMenuItem("Blue");
		KMenuItem itemYellow = new KMenuItem("Yellow");
		
		itemRed.addKActionListener(new ColorActionListener(p));
		itemBlue.addKActionListener(new ColorActionListener(p));
		itemYellow.addKActionListener(new ColorActionListener(p));
		
		ColorMenu.add(itemRed);
		ColorMenu.add(itemBlue);
		ColorMenu.add(itemYellow);
		
		//도형
		KMenu figureMenu = new KMenu("Figure");
		
		KMenuItem itemNone = new KMenuItem("None");
		KMenuItem itemRect = new KMenuItem("Rect");
		KMenuItem itemOval = new KMenuItem("Oval");
		KMenuItem itemLine = new KMenuItem("Line");
		
		itemNone.addKActionListener(new FigureActionListener(p)); 
		itemRect.addKActionListener(new FigureActionListener(p)); 
		itemOval.addKActionListener(new FigureActionListener(p)); 
		itemLine.addKActionListener(new FigureActionListener(p)); 
		
		figureMenu.add(itemNone);
		figureMenu.add(itemRect);
		figureMenu.add(itemOval);
		figureMenu.add(itemLine);
		
		//그룹화
		KMenu groupMenu = new KMenu("Groupage");
		
		KMenuItem itemGroup = new KMenuItem("Group");
		itemGroup.addKActionListener(new GroupActionListener(p));

		groupMenu.add(itemGroup);
		
		//이동
		KMenu moveMenu = new KMenu("Move");
		
		KMenuItem itemMove = new KMenuItem("Move");
		itemMove.addKActionListener(new MoveActionListener(p));
		
		moveMenu.add(itemMove);
		
		mb.add(ColorMenu);
		mb.add(figureMenu);
		mb.add(groupMenu);
		mb.add(moveMenu);
		
		//메뉴아이템을 걍 여기다 add해버리는게 마음 편함
		add(itemRed);
		add(itemBlue);
		add(itemYellow);
		add(itemNone);
		add(itemRect);
		add(itemOval);
		add(itemLine);
		add(itemGroup);
		add(itemMove);
		
		add(p); //이 짓 또 해야 하지 않음?
	}
}

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KAdapterFrame adapterframe = new KAdapterFrame();
		adapterframe.setSize(800,600);
		
		PainterKFrame myFrame = new PainterKFrame();
		adapterframe.setKFrame(myFrame);
		myFrame.initGUI();
		adapterframe.setVisible(true);
	}
}
