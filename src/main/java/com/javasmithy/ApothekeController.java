package com.javasmithy;

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
        this.viewBuilder = new ApothekeViewBuilder(model);

        //  Interactor
        this.interactor = new ApothekeInteractor(model);
    }

    public Parent getView() {
        return viewBuilder.build();
    }
}
