package models;

import com.badlogic.gdx.utils.Array;

import buildings.BoardObject;
import buildings.Building;
import buildings.Wall;
import buildings.WhiteHouse;
import buildings.towers.AlienNerfer;
import buildings.towers.Tower;
import buildings.towers.TowerBooster;
import buildings.towers.TowerUpgrader;
import enemies.Enemy;
import observers.BuildingObserver;
import observers.UpdateObserver;
import politics.parties.Party;
import utilities.Node;
import utilities.Radar;

public class BuildingModel implements UpdateObserver {
	private Array<Tower> towers;
	private Array<WhiteHouse> whitehouses;
	private Array<Wall> walls;
	private Array<Enemy> enemies;
	private Array<AlienNerfer> nerfers;
	private Array<TowerBooster> boosters; //should create a new superclass for boosters and nerfers and combine to 1 array
	private Radar radar;
	private TowerUpgrader upgrader;
	

    public BuildingModel(Array<Enemy> enemies) {
		towers = new Array<Tower>(false, 100);
		whitehouses = new Array<WhiteHouse>(false, 4);
		walls= new Array<Wall>(false,20);
		nerfers = new Array<AlienNerfer>(false, 100);
		boosters = new Array<TowerBooster>(false, 100);
		this.enemies = enemies;
		this.radar = new Radar();
		upgrader = new TowerUpgrader();

	}
    
    
    public Array<BoardObject> getAllBuildings(){
    	Array<BoardObject> allBuildings = new Array<BoardObject>();
    	for(Tower tower : towers){
    		allBuildings.add(tower);
    	}
    	for(WhiteHouse wh : whitehouses){
    		allBuildings.add(wh);
    	}

    	for (AlienNerfer nerfer : nerfers){
    		allBuildings.add(nerfer);
		}
    	return allBuildings;
    }
    
    private void voteBoardObject(BoardObject BO){
    	if(BO instanceof Party){
    		whitehouses.peek().voteParty((Party)BO);
    	}
    }

    public void clickedBuilding(BoardObject building){
    	notifyObservers(building, false, true);
    }
    
	public void deselect(BoardObject building) {
		notifyObservers(building, true, true);
	}
    
	public void addBoardObject(BoardObject BO){
		if(BO instanceof Tower){
			towers.add((Tower)BO);
			BO.setActive(true);
		}
		voteBoardObject(BO);
		notifyObservers(BO, false, false);
	}
	
	public void addBoardObject(BoardObject BO, int x, int y){
		BO.setPos(x, y);
		this.addBoardObject(BO);
	}
	
	public void addWall(Wall wall){
		walls.add(wall);
		notifyObservers(walls.peek(), false, false);
	}

	public void addAlienNerfer(AlienNerfer nerfer, int x, int y){
		nerfer.setPos(x,y);
		this.addAlienNerfer(nerfer);

	}
    public void addTowerBooster(TowerBooster booster){
	    boosters.add(booster);
        booster.setActive(true);
        notifyObservers(booster, false, false);
    }
    public void addTowerBooster(TowerBooster booster, int x, int y){
        booster.setPos(x,y);
        this.addTowerBooster(booster);

    }
    public void addAlienNerfer(AlienNerfer nerfer){
        nerfers.add(nerfer);
        nerfer.setActive(true);
        notifyObservers(nerfer, false, false);
    }

	public void addWhiteHouse(WhiteHouse whitehouse) {
		whitehouses.add(whitehouse); //just tmp size for WH;
		notifyObservers(whitehouses.peek(), false, false);
	}
	
	public Array<WhiteHouse> getWhiteHouses(){
		return whitehouses;
	}
	public Tower getTower(int index) {
		return towers.get(index);
	}

	public Array<Tower> getTowers(){
		return towers;
	}

	public Tower peekTower() {
		return towers.peek();
	}

	public void sellBuilding(BoardObject building) {
		building.setActive(false);
		if(building instanceof Tower)
			towers.removeValue((Tower)building, false);
		notifyObservers(building, true, false);
	}

	public void upgradeTower(Tower tower) {

	}
	
	public void fireAllTowers(){
		for(Tower tower : towers){
			if(!tower.isInCooldown() && tower.hasTarget()){
				tower.shoot();
			}
		}
	}

	public boolean checkIfInRadius(Tower tower, Node enemyNode) {
		float deltaX = enemyNode.getX() - tower.getPos().getX();
		float deltaY = enemyNode.getY() - tower.getPos().getY();

		if ((deltaX * deltaX) + (deltaY * deltaY) < (tower.getRadius() * tower.getRadius())) {
			return true;
		}

		return false;
	}

	private void setTargets() {
		Array<Enemy> foundAliens;
		for (Tower tower : towers) {

			foundAliens = radar.scan(tower.getPos(), tower.getRadius(), enemies); // tmp
			tower.setTarget(foundAliens);
		}
		
	}

	private void slowAllInRadius(){
	    Array<Enemy> inRadius;
	    for (AlienNerfer nerfer : nerfers){
	        inRadius = radar.scan(nerfer.getPos(), nerfer.getRadius(), enemies);
	        nerfer.slow(inRadius);
        }
    }

    private void boostAllInRadius(){
	    Array<Tower> inRadius;
	    for (TowerBooster booster: boosters){
        }
    }
	
	private void checkWhitehouses(){
		Array<Enemy> closeAliens;
		for (WhiteHouse whitehouse : whitehouses) {
			closeAliens = radar.scan(whitehouse.getPos(), 5, enemies); //5 radius for now
			if (closeAliens.size > 0) {
				for (Enemy enemy : closeAliens){
					if(!enemy.isDead()){
						whitehouse.removeHealth();
						enemy.kill();
					}
				}
			}
		}
	}

	@Override
	public void update(float deltaTime) {
		setTargets();
		fireAllTowers();
		slowAllInRadius();
		checkWhitehouses();
		
	}

	private Array<BuildingObserver> observers = new Array<BuildingObserver>(false, 10);

	public void addObserver(BuildingObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(BuildingObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(BoardObject building, boolean remove, boolean clickedOn) {
		for (BuildingObserver observer : observers)
			observer.actOnBuildingChange(building, remove, clickedOn);
	}




}
