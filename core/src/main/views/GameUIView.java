package views;

import com.badlogic.gdx.scenes.scene2d.Stage;

import buildings.Building;
import buildings.WhiteHouse;
import observers.BuildingObserver;
import observers.WhiteHouseObserver;
import stages.NextWaveStage;
import stages.PoliticalMeterStage;
import stages.RightGameUIStage;
import stages.SelectedBuildingStage;
import stages.TopLeftGameUIStage;
import utilities.DrawablesCollector;

public class GameUIView implements WhiteHouseObserver, BuildingObserver {
	private RightGameUIStage HS;
	private PoliticalMeterStage PMS;
	private SelectedBuildingStage SBS;
	private TopLeftGameUIStage TL;
	private NextWaveStage NW;
	private DrawablesCollector SC = DrawablesCollector.getInstance();
	

	public GameUIView(PoliticalMeterStage PMS, RightGameUIStage HS, TopLeftGameUIStage TL,SelectedBuildingStage SBS, NextWaveStage NW) {
		this.HS = HS;
		this.PMS = PMS;
		this.SBS = SBS;
		this.TL = TL;
		this.NW = NW;
		addToView(NW);
		addToView(TL);
		addToView(HS);
		addToView(PMS);
	}

	public void addToView(Stage stage) {
		SC.addStage(stage);
	}

	public void removeFromView(Stage stage) {
		SC.removeStage(stage);
	}

	@Override
	public void actOnWhiteHouseChange(WhiteHouse whitehouse) {
		PMS.updateParty(whitehouse.getPartyMap());
		TL.updateUI(Float.toString(whitehouse.getMoney()), Float.toString(whitehouse.getHealth()));
	}

	@Override
	public void actOnBuildingChange(Building building, boolean remove, boolean clickedOn) {
		if(!remove && clickedOn){
			SBS.setBuilding(building);
			removeFromView(HS);
			addToView(SBS);
		} else if(remove){
			removeFromView(SBS);
			addToView(HS);
		} else if(!remove && !clickedOn){
			removeFromView(SBS);
			addToView(HS);
		}

	}



}
