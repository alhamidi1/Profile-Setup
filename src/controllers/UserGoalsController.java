package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class UserGoalsController implements WizardController, Initializable {

    @FXML private ToggleGroup goalTypeToggleGroup;
    @FXML private ToggleButton generalGoalButton;
    @FXML private ToggleButton exactGoalButton;
    @FXML private VBox generalGoalPane;
    @FXML private VBox exactGoalPane;
    @FXML private ToggleGroup goalActionToggleGroup;
    @FXML private Label userIdLabel;
    @FXML private TextField weightGoalField;
    @FXML private TextField weightChangeField;
    @FXML private Button backButton;
    @FXML private Button continueButton;

    private SceneNavigator sceneNavigator;
    private ProfileSetupData profileSetupData;

    @Override
    public void init(SceneNavigator sceneNavigator, ProfileSetupData profileSetupData) {
        this.sceneNavigator = sceneNavigator;
        this.profileSetupData = profileSetupData;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goalTypeToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == generalGoalButton) {
                setPaneVisibility(true);
            } else if (newToggle == exactGoalButton) {
                setPaneVisibility(false);
            }
        });
        setPaneVisibility(true); // Set initial state
    }

    @FXML
    private void onContinueClicked() {
        // Save goal data
        ToggleButton selectedGoalType = (ToggleButton) goalTypeToggleGroup.getSelectedToggle();
        profileSetupData.setGoalType(selectedGoalType.getText());

        if (selectedGoalType == generalGoalButton) {
            ToggleButton selectedAction = (ToggleButton) goalActionToggleGroup.getSelectedToggle();
            profileSetupData.setGeneralGoalAction(selectedAction.getText());
        } else {
            // Add logic here to get text from exact goal TextFields
            // profileSetupData.setExactWeightGoal(...)
        }

        sceneNavigator.showNextPage();
    }

    @FXML
    private void onBackClicked() {
        sceneNavigator.showPreviousPage();
    }

    private void setPaneVisibility(boolean isGeneralPaneVisible) {
        generalGoalPane.setVisible(isGeneralPaneVisible);
        generalGoalPane.setManaged(isGeneralPaneVisible);
        exactGoalPane.setVisible(!isGeneralPaneVisible);
        exactGoalPane.setManaged(!isGeneralPaneVisible);
    }
}