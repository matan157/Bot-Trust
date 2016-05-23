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
	private Color color;
	public List<InstructionPair> Instructions;
	public Bot(int x, int y, Color color) {
		this.Instructions = new ArrayList<InstructionPair>();
        currentButton = 1;
        turn = false;
		this.x = x;
		this.y = y;
		this.color = color;
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
        if(Instructions.isEmpty()) { return false; }
        InstructionPair ip = Instructions.get(0);
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
                    Instructions.remove(0);
                    return true;
                }
            }
        }
        // If it gets here, it does nothing
        return false;
        
    }
	
	public void drawBot(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, WIDTH, HEIGHT);
	}
	
    
	
}
