import java.awt.Color;
import javax.swing.JFrame;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 400;
	
	public Frame() {
		initUI();
	}
	
	private void initUI() {
		add(new Panel());
		
		setTitle("Bot Trust");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}		
}
