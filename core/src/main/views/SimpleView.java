package views;

import com.badlogic.gdx.scenes.scene2d.Stage;

import utilities.DrawablesCollector;
import utilities.SpriteAdapter;

public abstract class SimpleView {
	private DrawablesCollector DC;
	
	protected SimpleView(DrawablesCollector DC){
		this.DC = DC;
	}
	
	public void addToView(SpriteAdapter sprite){
		DC.addSprite(sprite);
	}
	
	public void removeFromView(SpriteAdapter sprite) {
		DC.removeSprite(sprite);
	}
	
	public void addToView(Stage stage) {
		DC.addStage(stage);
	}

	public void removeFromView(Stage stage) {
		DC.removeStage(stage);
	}
	
	public DrawablesCollector getDrawablesCollector(){
		return DC;
	}
}
