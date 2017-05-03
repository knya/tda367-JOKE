package utilities;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DrawablesCollectorTest {

    private DrawablesCollector drawablesCollector;
    private SpriteAdapter spriteAdapter;
    private Stage stage;

    @Before
    public void setUp() throws Exception {
        drawablesCollector = DrawablesCollector.getInstance();
        spriteAdapter = new SpriteAdapter();
//        stage = new Stage();
    }

    @Test
    public void getInstance() throws Exception {
        assertNotNull(drawablesCollector);
    }

    @Test
    public void getSprites() throws Exception {
        assertNotNull(drawablesCollector.getSprites());
    }

    @Test
    public void addSprite() throws Exception {
        drawablesCollector.addSprite(spriteAdapter);

        assertEquals(drawablesCollector.getSprites().size, 1);
        assertTrue(drawablesCollector.getSprites().contains(spriteAdapter, false));
    }

    @Test
    public void removeSprite() throws Exception {
        drawablesCollector.removeSprite(spriteAdapter);

        assertEquals(drawablesCollector.getSprites().size, 0);
        assertFalse(drawablesCollector.getSprites().contains(spriteAdapter, false));
    }

    @Test
    public void drawSprites() throws Exception {
        //TODO
    }

    @Test
    public void addStage() throws Exception {
        drawablesCollector.addStage(stage);

        assertEquals(drawablesCollector.getStages().size, 1);
        assertTrue(drawablesCollector.getStages().contains(stage, false));
    }

    @Test
    public void removeStage() throws Exception {
        drawablesCollector.removeStage(stage);

        assertEquals(drawablesCollector.getStages().size, 0);
        assertFalse(drawablesCollector.getStages().contains(stage, false));
    }

    @Test
    public void getStages() throws Exception {
        assertNotNull(drawablesCollector.getStages());
    }

    @Test
    public void drawStages() throws Exception {
        //TODO
    }

    @Test
    public void drawAll() throws Exception {
        //TODO
    }

}