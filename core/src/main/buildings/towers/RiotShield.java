package buildings.towers;

import com.badlogic.gdx.utils.Array;

import buildings.Building;
import enemies.Enemy;
import politics.parties.Party;
import politics.parties.PartyFactory;
import politics.parties.Voter;

/**
 * Created by Emil on 2017-05-09.
 */
public class RiotShield extends Building implements Voter {

    private static final String NAME = "RIOT SHIELD";
    private static final int COST = 50;
    private static final int COOLDOWN = 10;
    private Party party = PartyFactory.Democrat(6); // Just for now
    private static String DESCRIPTION = "Pushes back nearby enemies";
    private static final int RADIUS = 500;
    private static final float SIZE = 50;
    private int cooldown;
    private int cooldownTimer;

    private Array<Enemy> enemies;

    public RiotShield(int x, int y){
        super(NAME, x, y, SIZE, RADIUS);
        this.cooldown = 20;
        cooldownTimer = cooldown;
    }

    public boolean isInCooldown(){
        cooldownTimer--;
        if(cooldownTimer <= 0){
            cooldownTimer = cooldown;
            return false;
        }
        return true;
    }


    @Override
    public String getDescription() {
        return DESCRIPTION;
    }



    public void updateEnemies(Array<Enemy> enemies){
        this.enemies = enemies;
    }

    @Override
    public void usePower() {
        if (enemies != null){
            for (Enemy e: enemies){
                e.setNodeArrayPos(e.getNodeArrayPos() - 1000);
            }
        }
    }

	@Override
	public Party getParty() {
		return party;
	}
}
