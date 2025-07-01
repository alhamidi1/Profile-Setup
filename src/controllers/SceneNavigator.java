package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SceneNavigator {

    private final Stage stage;
    private final ProfileSetupData profileSetupData = new ProfileSetupData();
    private final List<String> wizardPages = List.of(
            "ProfileSetup.fxml",
            "DietWelcome.fxml",
            "DietType.fxml",
            "FoodAvoidance.fxml",
            "AboutYouWelcome.fxml",
            "UserDetails.fxml",
            "UserGoals.fxml"
    );
    private int currentPageIndex = 0;

    public SceneNavigator(Stage stage) {
        this.stage = stage;
    }

    public void showFirstPage() {
        currentPageIndex = 0;
        loadSceneByName(wizardPages.get(currentPageIndex));
    }

    public void showNextPage() {
        if (currentPageIndex < wizardPages.size() - 1) {
            currentPageIndex++;
            loadSceneByName(wizardPages.get(currentPageIndex));
        } else {
            // Wizard finished, maybe show the main app dashboard
            System.out.println("Wizard finished! Final data: " + profileSetupData);
            // exit the application
            System.exit(0);
        }
    }

    public void showPreviousPage() {
        if (currentPageIndex > 0) {
            currentPageIndex--;
            loadSceneByName(wizardPages.get(currentPageIndex));
        }
    }

    private void loadSceneByName(String fxmlFileName) {
        try {
            // Construct the full path to the FXML file
            String fxmlPath = "/resources/fxml/" + fxmlFileName;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Get the controller and pass it the navigator and data model
            WizardController controller = loader.getController();
            controller.init(this, profileSetupData);

            Scene scene = stage.getScene();
            if (scene == null) {
                scene = new Scene(root, 1400, 1000);
                stage.setScene(scene);
            } else {
                stage.getScene().setRoot(root);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}