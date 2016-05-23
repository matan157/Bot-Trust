import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Bot {
	private int x;
	private int y;
    private int currentButton;
	public static final int WIDTH = 5;
	public static final int HEIGHT = 5;
    private boolean turn;
    private boolean done;
	private Color color;
    private int instruction;
	public List<InstructionPair> Instructions;
	public Bot(int x, int y, Color color) {
		this.Instructions = new ArrayList<InstructionPair>();
        currentButton = 1;
        turn = false;
		this.x = x;
		this.y = y;
		this.color = color;
        this.instruction = 0;
        this.done = false;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
    
    public void setTurn(boolean turn) {
        this.turn = turn;
    }
    
    public boolean doNext() {
        InstructionPair ip;
        if(this.instruction >= Instructions.size() || Instructions.isEmpty()) {
            this.done = true;
        }
        
        if(!this.done) {
            ip = Instructions.get(instruction);
            int button = this.currentButton;
            int iButton = ip.getButton();
            
            if(button < iButton) {
                this.currentButton += 1;
                this.x += 10;
            } else if (button > iButton) {
                this.currentButton -= 1;
                this.x -= 10;
            } else {
                if(turn) {
                    instruction += 1;
                    if (instruction >= Instructions.size())
                        this.done = true;
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean getDone() {
        return this.done;
    }
    
    public void reset(int x, int y) {
        this.Instructions.clear();
        this.done = false;
        this.currentButton = 1;
        this.instruction = 0;
        this.x = x;
        this.y = y;
    }
	
	public void drawBot(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);
	}
	
    
	
}
