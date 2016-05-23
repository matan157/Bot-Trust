import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
	public Bot Orange = new Bot(100, Frame.HEIGHT / 2, Color.ORANGE);
	public Bot Blue = new Bot(100, Frame.HEIGHT / 2 - 20, Color.BLUE);
	
	private void drawButtons(Graphics g) {
		int buttonX = 100;
		int buttonY = Frame.HEIGHT/2 - 10;
		
		g.setColor(Color.BLACK);
		// Draw 100 boxes
		for(int i = 0; i < 100; i++) {
			g.fillRect(buttonX, buttonY, 5, 5);
			buttonX += 10;
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawButtons(g);
		Main.drawBots(g);
	}

	
}
