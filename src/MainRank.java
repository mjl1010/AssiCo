

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/***
 * Created by Michael
 */
public class MainRank extends Application {

    private static Parent root;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        root = FXMLLoader.load(getClass().getResource("view/intCourseRangeOLD.fxml"));
        stage = primaryStage;
        stage.setTitle("Rango Stage");
        stage.setScene(new Scene(root, 495, 307));
        stage.show();

//        root = FXMLLoader.load(getClass().getResource("view/intSesionsCalendar.fxml"));
//        stage = primaryStage;
//        stage.setTitle("Sessions Calendar");
//        stage.setScene(new Scene(root, 1120 , 650));
//        stage.show();
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
