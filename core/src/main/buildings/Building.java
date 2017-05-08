package buildings;

import utilities.Node;
import utilities.SpriteAdapter;

public abstract class Building {
	private String name;
	private SpriteAdapter pos;
	private float size;
	private boolean active;
	
	protected Building(String name, int x, int y, float size){
		this.size = size;
		this.name = name;
		this.pos = new SpriteAdapter(x, y);
		setActive(false);
	}
	
	public abstract String getDescription();
	
	public float getRadius(){
		return 0;
	}
	public float getSize(){
		return size;
	}
	
	public void setSize(float size){
		this.size = size;
	}
	
    public Node getPos() {
        return new Node(pos.getX(),pos.getY());
    }
    
    public void setPos(Node pos) {
        this.pos.setPosition(pos.getX(), pos.getY());
    }
    
    public void setPos(int x, int y) {
        this.pos.setPosition(x, y);
    }

    public SpriteAdapter getSpriteAdapter(){
        return pos;
    }

    public String getName(){
        return this.name;
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
