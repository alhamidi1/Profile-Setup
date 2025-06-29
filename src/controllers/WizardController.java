package controllers;

public interface WizardController {
    /**
     * Initializes the controller with the necessary data and the scene navigator.
     * @param sceneNavigator The navigator to control scene transitions.
     * @param profileSetupData The shared data object for the wizard.
     */
    void init(SceneNavigator sceneNavigator, ProfileSetupData profileSetupData);
}