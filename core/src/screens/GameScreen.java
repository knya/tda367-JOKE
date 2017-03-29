package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import utilities.SpriteAdapter;
import utilities.SpriteCollector;

public class GameScreen implements Screen{
	SpriteBatch batch;
	Texture img;
	SpriteCollector SC;
	
	@Override
	public void show() {
		SC = SpriteCollector.getInstace();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		SC.addSprite(new SpriteAdapter(new Sprite(img)));	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		drawSprites();
		batch.end();
		
	}

	private void drawSprites() {
		if(SC.getSprites() != null){
			for(SpriteAdapter sprite : SC.getSprites()){
				sprite.getSprite().draw(batch);
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		
	}

}
