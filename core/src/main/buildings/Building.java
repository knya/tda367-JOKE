package buildings;

import cooldown.CooldownObject;
import politics.parties.Party;

public abstract class Building extends BoardObject {

	private float radius;
	private CooldownObject cooldown;
	private Party party;
	


	protected Building(String name, int x, int y, float size, float radius, float cooldown, float cost, Party party){
		super(name, x, y, size, cost);
		this.radius = radius;
		this.cooldown = new CooldownObject(cooldown);
		this.party = party;
	}
	
	@Override
	public Party getParty() {
		return party;
	}

	public float getRadius(){
		return radius;
	}
	
	public void setRadius(float radius){
		this.radius = radius;
	}
	
	public CooldownObject getCooldownObject(){
		return cooldown;
	}
	
	
	public abstract void usePower();
	
}
