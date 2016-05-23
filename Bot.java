import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Bot {
    // Coordinates
	private int x;
	private int y;
    
    // General Properties
    private Color color;
    private int instruction;
	public List<InstructionPair> Instructions;
    
    // Bot dimensions
    public static final int WIDTH = 5;
	public static final int HEIGHT = 5;
    
    // Used for doNext()
    private int currentButton;
    private boolean turn;
    private boolean done;
    
	// Constructor
	public Bot(int x, int y, Color color) {
		this.Instructions = new ArrayList<InstructionPair>();
        currentButton = 1; // Buttons are 1 - 100
        turn = false;
		this.x = x;
		this.y = y;
		this.color = color;
        this.instruction = 0;
        this.done = false;
	}
	
    // Getters
	public int getX() {	return this.x; }
	public int getY() {	return this.y; }
	public Color getColor() { return this.color; }
    public boolean getDone() { return this.done; }
	
    // Setters
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
    public void setTurn(boolean turn) { this.turn = turn; }
    
    // Perform the next instruction
    public boolean doNext() {
        InstructionPair ip;
        // If all instructions were done
        if(this.instruction >= Instructions.size() || Instructions.isEmpty()) {
            this.done = true;
        }
        
        // If there are instructions left
        if(!this.done) {
            ip = Instructions.get(instruction);
            int button = this.currentButton;
            int iButton = ip.getButton();
            
            // If the button you need is a higher number
            if(button < iButton) {
                this.currentButton += 1;
                this.x += 10;
            // If the button you need is a lower number
            } else if (button > iButton) {
                this.currentButton -= 1;
                this.x -= 10;
            // If you're on the button
            } else {
                // If it is this Bot's turn to press the button
                if(turn) {
                    instruction += 1;
                    // If you're finished with your instructions
                    if (instruction >= Instructions.size())
                        this.done = true;
                    return true;
                }
            }
        }
        // If you didn't do anything, moved up or down.
        return false;
    }
    
    // Reset the Bot
    public void reset(int x, int y) {
        this.Instructions.clear();
        this.done = false;
        this.currentButton = 1;
        this.instruction = 0;
        this.x = x;
        this.y = y;
    }
	
    // Draw method
	public void drawBot(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);
	}
	
    
	
}
