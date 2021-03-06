package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Stage for About
 * @author Johan Svennungsson
 */
public class AboutStage extends AbstractStage {

    private ClickListener mainMenuController;

    public AboutStage(ClickListener mainMenuController) {
        this.mainMenuController = mainMenuController;

        this.addActor(addBackButton());
        this.addActor(addAboutLabel());
        this.addActor(addAboutTextArea());

        this.setVisible(false);
    }

    private Actor addBackButton() {
        return ActorFactory.createTextButton("backToMainMenu","Back to Main menu",
                centerWidth, centerHeight - 150, center, mainMenuController);
    }

    private Actor addAboutLabel() {
        return ActorFactory.createLabel("About", centerWidth, centerHeight + 150, center);
    }

    private Actor addAboutTextArea() {
        return ActorFactory.createTextArea(
                "Developed by four students at Chalmers University of Technology "
                        + "as part of a software engineering project.",
                centerWidth, centerHeight, 200f, 200f, center);
    }

}
