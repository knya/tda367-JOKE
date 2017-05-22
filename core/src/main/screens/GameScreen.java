package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.illegalaliens.IllegalAliensMain;

import buildings.WhiteHouse;
import controllers.AlienController;
import controllers.BuildingController;
import controllers.ExecutiveOrdersController;
import controllers.ProjectileController;
import controllers.SuperpowerController;
import cooldown.CooldownHandler;
import cooldown.WavesCDHandler;
import map.Map;
import map.MapNode;
import models.AlienModel;
import models.BuildingModel;
import models.ExecutiveOrdersModel;
import models.ProjectileModel;
import models.SuperpowerModel;
import path.PathFinder;
import stages.*;
import towers.BOPrototypes;
import utilities.DrawablesCollector;
import utilities.Node;
import utilities.Radar;
import utilities.SpriteAdapter;
import views.AlienView;
import views.BuildingView;
import views.GameUIView;
import views.ProjectileView;
import waves.EnemyWavesCreator;

public class GameScreen implements Screen{
	private SpriteBatch batch;
	private Sprite backgroundSprite;
	private DrawablesCollector SC = DrawablesCollector.getInstance();
	private PathFinder finder;
	private final int width = 1280;
	private final int height = 720;
	
	private Array<Node> nodes = new Array<Node>();
	private Array<MapNode> Mapnodes;
	private IllegalAliensMain IAMain;
	private EnemyWavesCreator ewc;
	
	private Camera camera;
	private Viewport WP;
	private GameUIView HV;
	private RightGameUIStage HS;
	private SuperpowerStage SS;

	private Map map;

	public GameScreen(IllegalAliensMain illegalAliensMain, Map map, SpriteBatch batch) {
		this.IAMain = illegalAliensMain;
		this.map = map;
		this.batch = batch;
	}

	@Override
	public void show() {
		Radar radar = new Radar();
		BOPrototypes prot = new BOPrototypes();
//		map = new Map("AlphaMap", new Texture("AlphaMap.png"));
		addNodes(map);
		finder = new PathFinder(Mapnodes, Mapnodes.peek(), map.getStartingNodes(),radar);
		
		CooldownHandler cdh = new CooldownHandler();
		WavesCDHandler wcd = new WavesCDHandler();
		WhiteHouse WH = new WhiteHouse("WhiteHouse", 1280, Gdx.graphics.getHeight() - 330,100, 100000);
		
		
		AlienView AW= new AlienView();
		AlienModel AM = new AlienModel(finder, map.getStartingNodes(), wcd);
		AlienController AController = new AlienController(AW, AM);
		BuildingView TW = new BuildingView();
		BuildingModel BM = new BuildingModel(AM.getAllEnemies(), cdh,radar, finder);
		ProjectileModel PM = new ProjectileModel(AM,radar);
		ProjectileView PW = new ProjectileView();
		
		
		
		
		
		camera = new OrthographicCamera();
		WP = new FitViewport(width, height, camera);
		ProjectileController PC = new ProjectileController(PM, PW, BM);
		//Maybe move these later
		
		
		//InputAdapter EWC = new EnemyWavesCreator(AController);
        
		
		
		//camera.position.set(1280/2, 720/2, 0);
		
		
		BuildingController TController = new BuildingController(BM, AM, TW, WP,finder, prot);
		
		
		IAMain.addObserver(BM);
		IAMain.addObserver(AM);
		IAMain.addObserver(PM);
		
		IAMain.addObserver(cdh);
		InputMultiplexer imp = new InputMultiplexer();
		imp.addProcessor(AController);
		imp.addProcessor(TController);

		Gdx.input.setInputProcessor(imp);
		
		AM.addObserver(WH);
		BM.addWhiteHouse(WH);
		
		SuperpowerModel SM = new SuperpowerModel(finder,BM, AM, cdh);
		SuperpowerController SC = new SuperpowerController(SM, WP, AM,finder, BM, prot);
		IAMain.addObserver(SM);
		
		ExecutiveOrdersModel EOM = new ExecutiveOrdersModel(BM,AM, wcd,prot);
		ExecutiveOrdersController EOC= new ExecutiveOrdersController(EOM);
		
		SelectedBoardObjectStage SBOS = new SelectedBoardObjectStage(imp, TController);
		PoliticalMeterStage PMS = new PoliticalMeterStage();
		TopLeftGameUIStage TL = new TopLeftGameUIStage();
		NextWaveStage NW = new NextWaveStage(AController);
		HS = new RightGameUIStage(AController, TController,EOC, prot);
		SS = new SuperpowerStage(SC);
		HV = new GameUIView(PMS, HS, TL, SBOS, NW, SS);
		BM.addObserver(HV);
		prot.addObserver(HV);
		imp.addProcessor(HS);
		imp.addProcessor(SBOS);
		imp.addProcessor(NW);
		imp.addProcessor(SC);
		imp.addProcessor(SS);
		imp.addProcessor(EOC);
		
		BM.getWhiteHouses().peek().addObserver(HV);
		BM.getWhiteHouses().peek().setHealth(20); //Fixes display issue on HV
		
	}

	private void addNodes(Map map) {
		
		
		Mapnodes = map.getMapNodes();
		nodes.clear();
		for(MapNode tmp : Mapnodes){
			nodes.add(tmp.getPos());
		}
		backgroundSprite = new Sprite(map.getTexture());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		WP.apply();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundSprite.draw(batch);
		SC.drawAll(batch);
		batch.end();
		SC.drawStages();
	}

	

	@Override
	public void resize(int width, int height) {
		WP.update(width-200*width/this.width, height, true);
		
		SC.refreshStagesVP();
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
		if(SC.getSprites() != null){
			for(SpriteAdapter sprite : SC.getSprites()){
				sprite.getTexture().dispose();
			}
		}
		if(SC.getStages() != null){
			for(Stage stage : SC.getStages()){
				stage.dispose();
			}
		}
		
	}

}
