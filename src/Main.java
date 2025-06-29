import controllers.SceneNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the navigator and pass it the main stage
        SceneNavigator sceneNavigator = new SceneNavigator(primaryStage);

        primaryStage.setTitle("Meal Planner");
        
        // Tell the navigator to show the first page
        sceneNavigator.showFirstPage();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}