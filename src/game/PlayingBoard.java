package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class PlayingBoard extends JPanel {
	
	
	private Graphics2D g2D;
	private BufferedImage image;
	
	private int sideLength; 
	private int boardSize;
	private final int cellLength; 
	
	
	public PlayingBoard(int sideLength, int boardSize) {
		this.sideLength = sideLength;
		this.boardSize = boardSize;
		this.cellLength  = sideLength / boardSize;
		
		
		image = new BufferedImage(sideLength+10, sideLength+10, BufferedImage.TYPE_INT_ARGB);
		
		g2D = (Graphics2D)image.getGraphics();

		
		g2D.setColor(new Color(162,175,184));
		g2D.fillRect(0,0,sideLength, sideLength);
		
		g2D.setColor(Color.black);
		for(int i=1; i<=boardSize; i++) {
			g2D.drawLine(i*cellLength, 0, i*cellLength, sideLength);
		}
		
		for(int i=1; i<=boardSize; i++) {
			g2D.drawLine(0, i*cellLength, sideLength, i*cellLength);
		}
		
		
	}
	
	public int getRelativePos(int x) {
		if(x >= sideLength) x = sideLength-1;
		
		return (int) ( x * boardSize / sideLength );
	}
	public Dimension getPreferredSize() {
		return new Dimension(sideLength, sideLength);
	}
	
//	public void printWinner(int winner) {
//		
//		String text = winner == 2 ? "YOU WON!" : (winner == 1 ? "COMPUTER WON!" : "TIED!");
//		
//	}
	public void drawStone(int posX, int posY, boolean black) {
		
		if(posX >= boardSize || posY >= boardSize) return;
		
		g2D.setColor(black ? Color.black : Color.white);
		g2D.fillOval((int)(cellLength*(posX+0.05)), 
					 (int)(cellLength*(posY+0.05)), 
					 (int)(cellLength*0.9), 
					 (int)(cellLength*0.9));
		g2D.setColor(Color.black);
		g2D.setStroke(new BasicStroke(2));
		g2D.drawOval((int)(cellLength*(posX+0.05)), 
					 (int)(cellLength*(posY+0.05)), 
					 (int)(cellLength*0.9), 
					 (int)(cellLength*0.9));
		
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g.create();
		
		
		g2D.drawImage(image, 0, 0, sideLength, sideLength, null);
		
		g2D.setColor(Color.black);
        g2D.drawRect(0, 0, sideLength, sideLength);
	}	
	
	public void attachListener(MouseListener listener) {
		addMouseListener(listener);
	}
}