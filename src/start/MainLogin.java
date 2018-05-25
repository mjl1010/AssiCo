package start;

import controller.LoginController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.DatosModel;
import utilities.AlertHelper;
import utilities.VariablesAndMethodsUtils;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;

public class MainLogin extends Application {

    private static Parent root;
    private static Stage stage;
    public static Screen screen;
    private static boolean maximized = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/view/intLoading.fxml"));
        Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.getIcons().add(new Image("/view/res/AssiCoLogoMini.png"));
        primaryStage.setTitle("AssiCo - Cargando");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        double centerXPosition = d.getWidth()/2d;
        double centerYPosition = d.getHeight()/2d;

        primaryStage.setX(centerXPosition - 1000 / 2d);
        primaryStage.setY(centerYPosition - 540 / 2d);

        stage = primaryStage;
        screen = Screen.getPrimary();
        String error = null;

        VariablesAndMethodsUtils.PATH_PROPERTIES = "assiconfig.properties";

        try {
            Properties p = new Properties();
            File f = new File(VariablesAndMethodsUtils.PATH_PROPERTIES);
            if (!f.exists()) {
                f.createNewFile();
                p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));
                p.setProperty("server", "skimdoo.ddns.jazztel.es");
                p.setProperty("token", "");
                p.setProperty("curso", "");
                p.setProperty("year", "");
                p.setProperty("month", "");
                p.store(new FileWriter(VariablesAndMethodsUtils.PATH_PROPERTIES), "Config generated");
            }

            try {
                p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));
                String stoken = p.getProperty("token");

                DatosModel.connect(null, null);
                LoginController.token = DatosModel.getToken(stoken);
                DatosModel.closeConnection();

                if (!stoken.isEmpty() && LoginController.token != null && LoginController.token.isActivo()) {
                    openStage(getClass().getResource("/view/intMenu.fxml"), "Menú principal", primaryStage);
                    return;
                } else if (!stoken.isEmpty()) {
                    error = "Error 503. No se ha podido verificar la sesión anterior, por favor, accede de nuevo.";
                }
            } catch (Exception e) {
                error = "Error 504. No se ha podido establecer conexión con el servidor.";
            }
        } catch (Exception e) {
            error = "Error 403. No se ha podido abrir el programa por falta de permisos. O versión de java obsoleta >1.8.";
        }

        openStage(getClass().getResource("/view/intLogin.fxml"), "Asistente de Coordinación Academica", primaryStage);
        if (error != null) AlertHelper.showAlert(Alert.AlertType.ERROR, null, "AssiCo - Error", error);
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

    public static void openStage(URL url, String title, Stage primaryStage) {
        try {root = FXMLLoader.load(url);} catch (IOException e) {e.printStackTrace();}
        Stage miniStage = primaryStage != null ? primaryStage : new Stage();
        miniStage.getIcons().add(new Image("/view/res/AssiCoLogoMini.png"));
        miniStage.setTitle("AssiCo - " + title);
        miniStage.setScene(new Scene(root));

        // When window is maximized
        miniStage.maximizedProperty().addListener((ov, t, t1) -> maximized = t1);

        // Calculate the center position of the parent Stage
        double centerXPosition = stage.getX() + stage.getWidth()/2d;
        double centerYPosition = stage.getY() + stage.getHeight()/2d;

        // Relocate the Stage
        miniStage.setOnShown(ev -> {
            if (maximized) setMaximized(miniStage);
            else {
                miniStage.setX(centerXPosition - miniStage.getWidth() / 2d);
                miniStage.setY(centerYPosition - miniStage.getHeight() / 2d);
            }
            miniStage.show();
        });

        miniStage.show();
        stage = miniStage;
    }

    public static void setMaximized(Stage miniStage) {
        // Get current screen of the stage
        if (screen == null) screen = Screen.getScreensForRectangle(miniStage.getX(), miniStage.getY(), miniStage.getWidth(), miniStage.getHeight()).get(0);

        // Change stage properties
        Rectangle2D bounds = screen.getVisualBounds();
        miniStage.setX(bounds.getMinX());
        miniStage.setY(bounds.getMinY());
        miniStage.setWidth(bounds.getWidth());
        miniStage.setHeight(bounds.getHeight());
        miniStage.setMaximized(true);
    }
}
