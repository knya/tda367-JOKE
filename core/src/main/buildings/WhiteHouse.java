package buildings;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

import politics.parties.Party;
import utilities.PartyObserver;

public class WhiteHouse extends Building{
	private int health=20; //temp
	private float money;
	private ArrayMap<Integer, String> pVotes;
	
	public WhiteHouse(String name, int x, int y,float size){
		super(name, x, y,size);
		pVotes = new ArrayMap<Integer, String>(false, 7);
	}
	
	/**
	 * Vote for party. Any class that implements a subclass to "Party" will work
	 * @param party
	 */
	public <T extends Party> void voteParty(T party){
		
		for(Class<?> iFace : party.getClass().getInterfaces()){
			if(iFace.getInterfaces().length > 0){ //Checks so we dont vote with "Party" interface
				if(Party.class.isAssignableFrom(iFace)){ //Checks if Party is superclass
					System.out.println("Party: "+ party.getClass().getInterfaces()[0].getSimpleName());
					setPartyValue(party.getClass().getInterfaces()[0].getSimpleName(), party.getVotes());
					notifyObservers(party.getClass().getInterfaces()[0].getSimpleName(), party.getVotes());
				}
			}
			
		}
	}
	
	
	private void setPartyValue(String party, int votes) {
		if(pVotes.containsValue(party, false)){
			int index = pVotes.indexOfValue(party, false);
			pVotes.setKey(index, pVotes.getKeyAt(index)+votes);
		} else {
			pVotes.put(votes, party);
		}
		System.out.println("VOTES PARTY = "+ pVotes.getKey(party, false));
	}

	public void removeHealth(int amount){
		health -=amount;
	}
	
	public void removeHealth(){
		health -=1;
		System.out.println(health);
	}
	
	public void setHealth(int amount){
		health = amount;
	}
	
	public void addHealth(int amount){
		health += amount;
	}
	
	public void addHealth(){
		health += 1;
	}
	
	public int getHealth(){
		return health;
	}

	public void addMoney(float amount){
		money += amount;
	}
	
	public void setMoney(float amount){
		money = amount;
	}
	
	public void removeMoney(float amount){
		money -= amount;
	}
	
	public float getMoney(){
		return money;
	}
	
	private Array<PartyObserver> observers = new Array<PartyObserver>(false, 10);

	public void addObserver(PartyObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(PartyObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(String party, int votes) {
		for (PartyObserver observer : observers)
			observer.actOnPartyVote(party, votes);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
