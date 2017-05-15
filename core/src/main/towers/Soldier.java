package towers;


import factories.ProjectileFactory;
import politics.parties.Party;
import politics.parties.PartyFactory;
import politics.parties.Voter;
import projectiles.Projectile;

public class Soldier extends Tower implements Voter{
    private static final int RADIUS = 500;
    private static final String NAME = "SOLDIER";
    private static final int COST = 50;
    private static final int COOLDOWN = 10;
    private Party party = PartyFactory.Republican(3); // Just for now
	private static String DESCRIPTION = "A regular soldier";
	private static final float SIZE = 50;
    private static final float DAMAGE = 40;
    private static final float SPEED = 10;


    public Soldier(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN, SIZE, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = ProjectileFactory.createBullet(super.getTarget().getPos(), super.getPos(), DAMAGE, SPEED);
        super.notifyObservers(p, "spawn");
        return p;    }


	@Override
	public Party getParty() {
		return party;
	}
}