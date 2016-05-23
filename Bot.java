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
        if(!done) {
            if(instruction >= Instructions.size())
                ip = Instructions.get(Instructions.size() - 1);
            else
                ip = Instructions.get(instruction);
            int iButton = ip.getButton();
            if(ip != null) {
                if(iButton < this.currentButton) {
                    this.currentButton--;
                    this.x -= 10;
                } else if (iButton > this.currentButton) {
                    this.currentButton++;
                    this.x += 10;
                } else {
                    if(turn){
                        instruction++;
                        if(instruction >= Instructions.size())
                            this.done = true;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        // If it gets here, it does nothing
        return false;
        
    }
    
    public boolean getDone() {
        return this.done;
    }
    
    public void reset(int x, int y) {
        this.Instructions = new ArrayList<InstructionPair>();
        this.done = false;
        this.instruction = 0;
        this.x = x;
        this.y = y;
    }
	
	public void drawBot(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);
	}
	
    
	
}
