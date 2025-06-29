package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDetailsController implements WizardController, Initializable {

    @FXML private ToggleGroup sexToggleGroup;
    @FXML private ToggleGroup bodyfatToggleGroup;
    @FXML private TextField heightField;
    @FXML private TextField weightField;
    @FXML private TextField ageField;
    @FXML private ChoiceBox<String> activityLevelChoiceBox;
    @FXML private Label userIdLabel;
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
        activityLevelChoiceBox.setItems(FXCollections.observableArrayList(
                "Not very active",
                "Lightly active (exercise 1-2 days/week)",
                "Active daily, frequent exercise",
                "Very active (hard exercise every day)"
        ));
        activityLevelChoiceBox.setValue("Active daily, frequent exercise");
    }

    @FXML
    private void onContinueClicked() {
        // Save all data from the form
        ToggleButton selectedSex = (ToggleButton) sexToggleGroup.getSelectedToggle();
        if (selectedSex != null) {
            profileSetupData.setSex(selectedSex.getText());
        }

        ToggleButton selectedBodyfat = (ToggleButton) bodyfatToggleGroup.getSelectedToggle();
        if (selectedBodyfat != null) {
            profileSetupData.setBodyfatLevel(selectedBodyfat.getText());
        }

        try {
            profileSetupData.setHeight(Integer.parseInt(heightField.getText()));
            profileSetupData.setWeight(Integer.parseInt(weightField.getText()));
            profileSetupData.setAge(Integer.parseInt(ageField.getText()));
        } catch (NumberFormatException e) {
            System.err.println("Invalid number in text field.");
            // Optionally, show an error alert to the user
        }

        profileSetupData.setActivityLevel(activityLevelChoiceBox.getValue());
        
        sceneNavigator.showNextPage();
    }

    @FXML
    private void onBackClicked() {
        sceneNavigator.showPreviousPage();
    }
}