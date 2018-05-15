package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.DatosModel;
import utilities.AlertHelper;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Random;

public class MainLogin extends Application {

    private static Parent root;
    private static Stage stage;

    private static DatosModel datosModel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/view/intLogin.fxml"));
        stage = primaryStage;
        stage.getIcons().add(new Image("/view/res/AssiCoLogo@0,1x.png"));
        stage.setTitle("AssiCo - Asistente de Coordinación Academica");
        stage.setScene(new Scene(root, 1000 , 540));
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

    public static String generateRandomColor() {
        Random random = new Random();
        int nextInt = random.nextInt(256*256*256);

        return String.format("#%06x", nextInt);
    }
}
