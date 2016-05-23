import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static List<InstructionPair> Instructions;
	public static Bot Orange;
	public static Bot Blue;
	public static int turn; // 0 == Orange, 1 == Blue
	
	public static Frame frame;
	
	public Main() {
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// Added both bots
		Orange = new Bot(100, Frame.HEIGHT / 2, Color.ORANGE);
		Blue = new Bot(100, Frame.HEIGHT / 2 - 20, Color.BLUE);
		Instructions = new ArrayList<InstructionPair>();
		
		Main main = new Main();
		frame = new Frame();
        frame.setVisible(true);
		try {
			Scanner input = new Scanner(System.in);
			int cases = input.nextInt();
			String bot;
			int button;
			int steps;
			for(int i = 0; i < cases; i++) {
				System.out.println("Problem No: " + (i+1));
				steps = input.nextInt();
				for(int j = 0; j < steps; j++) {
					bot = input.next();
					button = input.nextInt();
					new InstructionPair(bot, button);
					Instructions.add(new InstructionPair(bot, button));
					if( bot.equals("O")) {
						Orange.Instructions.add(new InstructionPair(bot, button));
					} else if (bot.equals("B")) {
						Blue.Instructions.add(new InstructionPair(bot, button));
					}
				}
				main.simulate();
				Instructions.clear();
				Orange.Instructions.clear();
				Blue.Instructions.clear();
			}
			
			input.close();
		} finally {
			
		}
		

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
            }
        });
    }
	
	public static void drawBots(Graphics g) {
		Orange.drawBot(g);
		Blue.drawBot(g);
	}
	
	public void simulate() {
		int instruction = 0;
		InstructionPair ip = Instructions.get(instruction);
		if(ip.getBot() == "O") {
			Orange.setTurn(true);
			Blue.setTurn(false);
		} else {
			Orange.setTurn(false);
			Blue.setTurn(true);
		}
		for(int i = 1; i < Integer.MAX_VALUE; i++) {
			System.out.println("Step No: " + i);
			boolean clickedO = Orange.doNext();
			boolean clickedB = Blue.doNext();
			frame.getContentPane().repaint();
			if(clickedO || clickedB) {
				instruction++;
				ip = Instructions.get(instruction);
				if(ip == null)
					break;
				if(ip.getBot() == "O") {
					Orange.setTurn(true);
					Blue.setTurn(false);
				} else {
					Orange.setTurn(false);
					Blue.setTurn(true);
				}
			}
		}
		
	}
}