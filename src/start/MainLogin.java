package start;

import controller.LoginController;
import javafx.application.Application;
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
import java.util.Properties;
import java.util.Random;

public class MainLogin extends Application {

    private static Parent root;
    private static Stage stage;

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
                root = FXMLLoader.load(getClass().getResource("/view/intMenu.fxml"));
                stage = primaryStage;
                stage.getIcons().add(new Image("/view/res/AssiCoLogo@0,1x.png"));
                stage.setScene(new Scene(root));
                stage.setTitle("AssiCo - Menú");
                stage.show();
                return;
            } else if (!stoken.isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "No se ha podido verificar la sesión anterior, por favor, accede de nuevo.");
            }

            root = FXMLLoader.load(getClass().getResource("/view/intLogin.fxml"));
            stage = primaryStage;
            stage.getIcons().add(new Image("/view/res/AssiCoLogo@0,1x.png"));
            stage.setTitle("AssiCo - Asistente de Coordinación Academica");
            stage.setScene(new Scene(root, 1000 , 540));
            stage.show();

        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Error - AssiCo", "Hay un problema entre la pantalla y la silla. Vuelve a intentarlo.");
            //e.printStackTrace();
        }
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
