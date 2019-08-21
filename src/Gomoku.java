import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gomoku extends JFrame{
	JPanel p=new JPanel();
	XOButton buttons[]=new XOButton[100];
	
	public static void main(String args[]){
		new Gomoku();
	}
	
	public Gomoku(){
		super("Gomoku");
		setSize(400,400);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setBackground(Color.cyan);
		
		p.setLayout(new GridLayout(10,10));
		
		for(int i=0;i<100;i++){
			buttons[i]=new XOButton();
			p.add(buttons[i]);
			buttons[i].setBackground(Color.darkGray);
		}
		add(p);
		
		setVisible(true);
	}
}