package com.example.illegalaliens.models.boardobjects.towers;


import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.politics.parties.Party;
import com.example.illegalaliens.models.politics.parties.PartyFactory;
import com.example.illegalaliens.models.projectiles.Projectile;
import com.example.illegalaliens.models.projectiles.ProjectileFactory;

public class Tank extends Tower {
    //Tower specific stats, name and description
    private static final String NAME = "TANK";
    private static String DESCRIPTION = "Great damage but shoots slow";
    private static final int RADIUS = 250;
    private static final int COST = 2000;
    private static final float COOLDOWN = 50;
    private static final Party PARTY = PartyFactory.Democrat(10); // Just for now
    private static final float SIZE = 55;
    private static final float DAMAGE = 60;
    private static final float SPEED = 10;
    private static final int projectileHealth = 1;

    public Tank(int x, int y, float radius, float cooldown, float cost, float damage){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage, PARTY);
    }

    public Tank(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }
   
   
    @Override
    public Projectile makeProjectile() {
        Projectile p = ProjectileFactory.createMissile(super.getTarget().getPos(), super.getPos(), super.getDamage(), SPEED,projectileHealth);
        super.notifyObservers(p, "spawn");
        return p;
    }

	@Override
	public BoardObject clone(int x, int y) {
		return new Tank(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}

}
