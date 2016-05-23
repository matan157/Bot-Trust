import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Thread;

public class Main implements Runnable{
	// List of instructions
	public static List<InstructionPair> Instructions;
	// Bots
	public static Bot Orange;
	public static Bot Blue;
	// Which bot's turn
	public static int turn; // 0 == Orange, 1 == Blue
	// Used to print to screen
	public static String currentSteps = "Steps: ";
	// Frame created
	public static Frame frame;
	// Delay between instructions
	public static final int DELAY = 100;
	
	public void run() {
		// Initialize bots and list of instructions
		Orange = new Bot(100, Frame.HEIGHT / 2, Color.ORANGE);
		Blue = new Bot(100, Frame.HEIGHT / 2 - 20, Color.BLUE);
		Instructions = new ArrayList<InstructionPair>();
		// New main object and frame object
		Main main = new Main();
		frame = new Frame();
		frame.setVisible(true);
		// Try for scanner input
		try {
			// Read in using a pipe
			Scanner input = new Scanner(System.in);
			int cases = input.nextInt();
			String bot;
			int button;
			int steps;
			// Number of instruction sets
			for(int i = 0; i < cases; i++) {
				// Separate problems from each other in the command line
				System.out.println("==================================");
				System.out.println("Problem No: " + (i+1));
				steps = input.nextInt();
				for(int j = 0; j < steps; j++) {
					bot = input.next();
					button = input.nextInt();
					Instructions.add(new InstructionPair(bot, button)); // Add instruction to main list
					// Add instruction to corresponding bot's instructions 
					if( bot.equals("O")) {
						Orange.Instructions.add(new InstructionPair(bot, button));
					} else if (bot.equals("B")) {
						Blue.Instructions.add(new InstructionPair(bot, button));
					}
				}
				
				// Simulate runs the instructions
				main.simulate();
				// Clear for next problem
				Instructions.clear();
				Orange.reset(100, Frame.HEIGHT / 2);
				Blue.reset(100, Frame.HEIGHT / 2 - 20);
				System.out.println("==================================");
				// Sleep between instructions
				try {
					Thread.sleep(1000);                 
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
			input.close();
		} finally {
			
		}
	}
	
	// Static main method for non-static run method
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
    }
	
	// Drawing both bots
	public static void drawBots(Graphics g) {
		Orange.drawBot(g);
		Blue.drawBot(g);
	}
	
	// Draw text on the screen for how many steps are being taken
	public static void drawSteps(Graphics g, String s) {
		g.drawString(s, Frame.WIDTH / 2 - 20, 50);
	}
	
	// Main simulation
	public void simulate()  {
		// Instructions run on 0 index
		int instruction = 0;
		InstructionPair ip = Instructions.get(instruction);
		// Set whose turn it is
		if(ip.getBot().equals("O")) {
			Orange.setTurn(true);
			Blue.setTurn(false);
		} else {
			Orange.setTurn(false);
			Blue.setTurn(true);
		}
		// Start the steps
		int step = 1;
		// Loop until instructions are done
		while(true) {
			this.currentSteps = "Steps: " + step;
			System.out.println("Step No: " + step);
			// IF the bot clicked a button, it will return true
			boolean oClicked = Orange.doNext();
			boolean bClicked = Blue.doNext();
			
			// If the button that needs to be clicked is clicked
			if(oClicked || bClicked) {
				// Go on to the next instruction or finish the current problem's simulation
				instruction++;
				if(instruction >= Instructions.size())
					break;
				// Set next bot's turn
				ip = Instructions.get(instruction);
				if(ip.getBot().equals("O")) {
					Orange.setTurn(true);
					Blue.setTurn(false);
				} else {
					Orange.setTurn(false);
					Blue.setTurn(true);
				}
			}
			step++;
			try {
				// Delay between instructions
    			Thread.sleep(DELAY);                 
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			// If both bots are done, the simulation is over.
			if(Orange.getDone() && Blue.getDone()) {
				break;
			}	
		}
		
	}
}