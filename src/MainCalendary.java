

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/***
 * Created by Michael
 */
public class MainCalendary extends Application {

    private static Parent root;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        root = FXMLLoader.load(getClass().getResource("view/intRango.fxml"));
        stage = primaryStage;
        stage.setTitle("Calendary Stage");
        stage.setScene(new Scene(root, 495, 307));
        stage.show();

    }



    /**
     * close stage
     * @param stage
     */
    public static void closeStage(Stage stage){
        stage.close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static Parent getRoot() {
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
