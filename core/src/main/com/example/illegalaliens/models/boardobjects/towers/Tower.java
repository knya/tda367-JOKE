package com.example.illegalaliens.models.boardobjects.towers;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.ITargetState;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetFirst;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.models.politics.parties.Party;
import com.example.illegalaliens.models.projectiles.Projectile;
import com.example.illegalaliens.models.projectiles.ProjectileObserver;
import com.example.illegalaliens.utilities.cooldown.CooldownObject;

public abstract class Tower extends BoardObject{
	private float radius;
	private Enemy target;
	private ITargetState TState;
	private float damage;
	private Party party;

	private Array<ProjectileObserver> observers = new Array<ProjectileObserver>();
    private CooldownObject cooldown;


    //Used in temporary tower boosts
    private float modifiedDamage;
    private float modifiedRadius;
    private float modifiedCooldown;

	protected Tower(int x, int y, float radius, String name, float cost, float cooldown,float size, float damage, Party party){
		super(name, x, y,size, cost);
		this.cooldown = new CooldownObject(cooldown);
		this.radius = radius;
		this.TState = new TargetFirst();
		this.damage = damage;
		this.party = party;
	}

	//getters and setters
	@Override
	public Party getParty() {
		return party;
	}

	public float getModifiedDamage() {
		return modifiedDamage;
	}

	public void setModifiedDamage(float modifiedDamage) {
		this.modifiedDamage = modifiedDamage;
	}

	public float getModifiedRadius() {
		return modifiedRadius;
	}

	public void setModifiedRadius(float modifiedRadius) {
		this.modifiedRadius = modifiedRadius;
	}

	public float getModifiedCooldown() {
		return modifiedCooldown;
	}

	public void setModifiedCooldown(float modifiedCooldown) {
		this.modifiedCooldown = modifiedCooldown;
	}

	public CooldownObject getCooldownObject(){
		return cooldown;
	}
	
	public void setTargetState(ITargetState state) {
		this.TState = state;
	}

	public ITargetState getTargetState() {
		return TState;
	}

	public float getRadius() {
		return this.radius;
	}

	public float getDamage(){
    	return this.damage;
	}
	
	public void setRadius(float radius){
    	this.radius = radius;
	}

	public void setDamage(float damage){
		this.damage = damage;
	}

	public void setCooldown(float cooldown){this.cooldown.setCooldownTicks(cooldown);}

	public void shoot() {
		this.makeProjectile();
    }


    public abstract Projectile makeProjectile();


	/**
	 * Sets the target for the tower accordingly to the target state
	 * @param targets all enemies in the tower radius
	 */
	public void setTarget(Array<Enemy> targets) {
		if(targets.size == 0){
			target = null;
		}else{
			this.target = TState.getEnemy(super.getPos(), targets);
			super.getSpriteAdapter().rotateTowards(target.getPos(),-90);
		}
		
	}
	
	public boolean hasTarget(){
		return target != null;
	}
	

	public Enemy getTarget() {
		return target;
	}

    public void removeObserver(ProjectileObserver observer) {
        observers.removeValue(observer, false);
    }

    public void notifyObservers(Projectile projectile, String change){
        for (ProjectileObserver observer : observers){
            observer.actOnProjectileChange(projectile, change);
        }
    }


    public void addObserver(ProjectileObserver observer){
        observers.add(observer);
    }

    

}
