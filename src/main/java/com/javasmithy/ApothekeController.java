package com.javasmithy;

import com.javasmithy.data.skills.ApothekeSkill;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.util.Builder;

/**
 *  Controller implementation of MVCI
 */
public class ApothekeController {
    /**
     * An instance of the build that creates the View
     */
    private final Builder<Region> viewBuilder;
    /**
     * An instance of the interactor which contains all the domain/business logic of the app
     */
    private final ApothekeInteractor interactor;

    /**
     * Constructor to instantiate the model, pass it to the view builder and the interactor. Also passes consumers
     * to the view to ensure the view does not directly control the model
     */
    public ApothekeController() {
        //  Model
        ApothekeModel model = new ApothekeModel();

        //  View
        this.viewBuilder = new ApothekeViewBuilder(
                model,
                this::incrementSkill,
                this::decrementSkill,
                this::updatePlayerPortraitPath);

        //  Interactor
        this.interactor = new ApothekeInteractor(model);
    }

    public Parent getView() {
        return viewBuilder.build();
    }

    /**
     * Calls the increment skill of interactor, used as consumer in view
     * @param skill - skill type
     */
    private void incrementSkill(ApothekeSkill skill){
        interactor.incrementSkillValue(skill);
    }

    /**
     * Calls the decrement skill of interactor, used as consumer in view
     * @param skill - skill type
     */
    private void decrementSkill(ApothekeSkill skill){
        interactor.decrementSkillValue(skill);
    }

    /**
     * Calls the update player portrait path of interactor, used as consumer in view
     * @param path - path of image
     */
    private void updatePlayerPortraitPath(String path) {interactor.updatePlayerPortraitPath(path);}



}
