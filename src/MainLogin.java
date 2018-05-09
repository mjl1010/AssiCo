import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.DatosModel;

public class MainLogin extends Application {

    private static Parent root;
    private static Stage stage;

    private static DatosModel datosModel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("view/intLogin.fxml"));
        stage = primaryStage;
        stage.getIcons().add(new Image("view/res/AssiCoLogo@0,1x.png"));
        stage.setTitle("Assistent de Coordinació Acadèmica - AssiCo");
        stage.setScene(new Scene(root, 640 , 400));
        stage.show();
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
