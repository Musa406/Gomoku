import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XOButton extends JButton implements ActionListener{
	ImageIcon X,O;
	static byte value=0;
	//static int cnt;
	/*
	0:nothing
	1:X
	2:O
	*/
	
	public XOButton(){
		X=new ImageIcon(this.getClass().getResource("p1.png"));
		O=new ImageIcon(this.getClass().getResource("p2.png"));
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		value++;
		value%=2;
		switch(value){
			case 0:
				setIcon(X);
				break;
			case 1:
				setIcon(O);
				break;

		}
	}
}