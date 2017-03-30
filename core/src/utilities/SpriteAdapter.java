package utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteAdapter extends Sprite{
	private int ID;
	
	private static int uniqueID = 0;
	
	public SpriteAdapter(){
		super();
	}
	
	public SpriteAdapter(int x, int y){
		super(new Sprite());
		super.setPosition(x, y);
		ID = uniqueID++;
	}
	
	public SpriteAdapter(Texture texture){
		super(texture);
		ID = uniqueID++;
	}

	public int getId(){
		return ID;
	}
	
	@Override
	public void setTexture(Texture texture){
		super.setSize(Math.abs(texture.getWidth()), Math.abs(texture.getHeight()));
		super.setTexture(texture);
		super.setRegion(0, 0, texture.getWidth(), texture.getHeight());
		super.setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpriteAdapter other = (SpriteAdapter) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}