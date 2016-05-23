import java.awt.Color;
import java.awt.Graphics;
import java.lang.Thread;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
	// Draw the buttons on screen
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
	
	// Panel repaints every 0.1 seconds
	public Panel() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException exp) {
					}
					repaint();
				}
			}
		});
		t.start();
	}

	
	// Paint everything
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawButtons(g);
		Main.drawBots(g);
		Main.drawSteps(g, Main.currentSteps);
	}
	
	
}
