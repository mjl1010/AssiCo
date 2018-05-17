package start;

import controller.LoginController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.DatosModel;
import utilities.AlertHelper;
import utilities.VariablesAndMethodsUtils;

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

    private static boolean maximized = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VariablesAndMethodsUtils.PATH_PROPERTIES = "assiconfig.properties";

        Properties p = new Properties();
        File f = new File(VariablesAndMethodsUtils.PATH_PROPERTIES);
        if (!f.exists()) {
            f.createNewFile();
            p.load(new FileReader(VariablesAndMethodsUtils.PATH_PROPERTIES));
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
                openStage(getClass().getResource("/view/intMenu.fxml"), "Menú", primaryStage);
                return;
            } else if (!stoken.isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "No se ha podido verificar la sesión anterior, por favor, accede de nuevo.");
            }
        } catch (Exception e) {
            //AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "Hay un problema entre la pantalla y la silla. Vuelve a intentarlo.");
            //e.printStackTrace();
        }

        openStage(getClass().getResource("/view/intLogin.fxml"), "Asistente de Coordinación Academica", primaryStage);
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
        stage = primaryStage != null ? primaryStage : new Stage();
        stage.getIcons().add(new Image("/view/res/AssiCoLogo@0,1x.png"));
        stage.setTitle("AssiCo - " + title);
        stage.setScene(new Scene(root));
        stage.setMaximized(maximized);
        stage.show();
        stage.maximizedProperty().addListener((ov, t, t1) -> maximized = t1);
    }
}
