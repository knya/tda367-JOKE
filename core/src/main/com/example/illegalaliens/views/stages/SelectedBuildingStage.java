package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.boardobjects.towers.Tower;

public class SelectedBuildingStage extends SelectedBoardObjectStage {
	private Skin skin;
	private Label description, name;
	private Table target;
	private Table upgrades;

	public SelectedBuildingStage(ClickListener buildingC) {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
		Table table = new Table();
		table.setZIndex(9001); // Put on top
		table.setPosition(Gdx.graphics.getWidth() - 200, 50);
		table.setWidth(200);
		table.setHeight(670);

		target = getTargetsTable(buildingC);
		upgrades = getUpgradesTable(buildingC);
		upgrades.setVisible(false);
		target.setVisible(false);
		description = new Label("", skin);
		name = new Label("", skin);
		name.setWidth(200);
		description.setWrap(true);

		table.add(name).expand().top();
		table.row();
		table.add(upgrades);
		table.row();
		table.add(target);
		table.row();
		table.add(description).width(180).bottom();
		table.row();
		table.add(removeButton(buildingC)).expand();
		this.addActor(table);
		table.setVisible(false);
	}

	private TextButton removeButton(ClickListener CL) {
		return addTextButton("remove", "Sell", CL);
	}

	private TextButton addTargetButton(String name, String text, ClickListener CL) {
		return addTextButton(name, text, CL);
	}

	private Table getTargetsTable(ClickListener buildingC) {
		Table table = new Table();
		table.add(addTargetButton("tFirst", "Target first", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tLast", "Target last", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tStrong", "Target strongest", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tWeak", "Target weakest", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tClose", "Target closest", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tFar", "Target furthest", buildingC)).expand().bottom();
		return table;
	}

	private Table getUpgradesTable(ClickListener buildingC){
		Table table = new Table();
		table.add(addTargetButton("uRadius", "Upgrade radius", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("uDamage", "Increase damage", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("uCooldown", "Reduce cooldown", buildingC)).expand().bottom();
		return table;
	}

	public void setBuilding(BoardObject building) {
		if(building instanceof Tower && building.isActive()){
			target.setVisible(true);
			upgrades.setVisible(true);
		} else{
			target.setVisible(false);
			upgrades.setVisible(false);
		}
		description.setText(building.getDescription());
		name.setText(building.getName());
	}

	private TextButton addTextButton(String name, String text, ClickListener CL) {
		TextButton textButton = new TextButton(text, skin, "default");

		textButton.setName(name);
		textButton.setTransform(false);
		textButton.addListener(CL);
		return textButton;
	}
}
