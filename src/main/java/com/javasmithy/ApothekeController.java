package com.javasmithy;

import com.javasmithy.data.skills.ApothekeSkill;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class ApothekeController {
    private final Builder<Region> viewBuilder;
    private final ApothekeInteractor interactor;

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

    private void incrementSkill(ApothekeSkill skill){
        interactor.incrementSkillValue(skill);
    }

    private void decrementSkill(ApothekeSkill skill){
        interactor.decrementSkillValue(skill);
    }

    private void updatePlayerPortraitPath(String path) {interactor.updatePlayerPortraitPath(path);}



}
