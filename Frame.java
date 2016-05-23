import java.awt.Color;
import javax.swing.JFrame;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	// Dimensions
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 400;
	
	// Frame has a panel
	public static Panel panel;
	
	public Frame() {
		initUI();
	}
	
	// Initialize the screen
	private void initUI() {
		panel = new Panel();
		add(panel);
		
		setTitle("Bot Trust");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}		
}
