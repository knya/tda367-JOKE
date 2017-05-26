package executive_orders;

import boardobjects.WhiteHouse;
import boardobjects.towers.BOPrototypes;
import cooldown.WavesCooldown;
import politics.parties.Party;

public class DeclareWar implements ExecutiveOrder, WavesCooldown {

	private int amount;
	private WhiteHouse whitehouse;
	private Party party;
	private BOPrototypes prots;
	public DeclareWar(WhiteHouse whitehouse, int amount, Party party, BOPrototypes prots){
		this.whitehouse = whitehouse;
		this.amount = amount;
		this.party= party;
		this.prots = prots;
	}

	@Override
	public void execute() {
		giveMoney(amount);
		whitehouse.voteParty(party);
	}
	
	private void giveMoney(int amount){
		whitehouse.addMoney(amount);
		prots.changeCost(1.5f);
	}

	@Override
	public String getDescription() {
		return "You instantly get "+amount+ "$. The next 5 turns everything is more expensive.";
	}

	@Override
	public Party getParty() {
		return party;
	}

	@Override
	public int cdTurns() {
		return 5;
	}

	@Override
	public void afterCD(String hash) {
		prots.revertCost(1.5f);
	}
}
