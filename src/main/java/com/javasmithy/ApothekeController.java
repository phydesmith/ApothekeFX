package com.javasmithy;

import com.javasmithy.skills.ApothekeSkill;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class ApothekeController {
    private final Builder<Region> viewBuilder;
    private ApothekeInteractor interactor;

    public ApothekeController() {
        //  Model
        ApothekeModel model = new ApothekeModel();

        //  View
        this.viewBuilder = new ApothekeViewBuilder(model, this::incrementSkill, this::decrementSkill);

        //  Interactor
        this.interactor = new ApothekeInteractor(model);
    }

    public Parent getView() {
        return viewBuilder.build();
    }

    private void incrementSkill(ApothekeSkill skill){
        interactor.increment(skill);
    }

    private void decrementSkill(ApothekeSkill skill){
        interactor.decrement(skill);
    }
}
