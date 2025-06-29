package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class AboutYouWelcomeController implements WizardController {

    private SceneNavigator sceneNavigator;
    private ProfileSetupData profileSetupData;

    @FXML private Label userIdLabel;
    @FXML private Button backButton;
    @FXML private Button continueButton;

    @Override
    public void init(SceneNavigator sceneNavigator, ProfileSetupData profileSetupData) {
        this.sceneNavigator = sceneNavigator;
        this.profileSetupData = profileSetupData;
    }

    @FXML
    private void onContinueClicked() {
        sceneNavigator.showNextPage();
    }

    @FXML
    private void onBackClicked() {
        sceneNavigator.showPreviousPage();
    }
}