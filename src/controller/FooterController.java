package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import utilities.TextResponsive;

import java.net.URL;
import java.util.ResourceBundle;

public class FooterController implements Initializable {
    protected static FooterController main;

    @FXML
    Label zoom;

    @FXML
    private Label zoom_menos;

    @FXML
    private Label zoom_mas;

    public static String zoom_value = "100%";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = this;
        if (zoom != null) zoom.setText(zoom_value);
    }

    @FXML
    private void zoomMenos(MouseEvent event) {
        TextResponsive.setH1("24px");
        TextResponsive.setH2("20px");
        TextResponsive.setH3("18px");
        TextResponsive.setH4("16px");
        TextResponsive.setH5("14px");
        TextResponsive.setH6("12px");
        zoom_value = "90%";
        zoom.setText(zoom_value);

        refresh();
    }

    @FXML
    private void zoomIgual(MouseEvent event) {
        TextResponsive.setH1("26px");
        TextResponsive.setH2("24px");
        TextResponsive.setH3("20px");
        TextResponsive.setH4("18px");
        TextResponsive.setH5("16px");
        TextResponsive.setH6("14px");
        zoom_value = "100%";
        zoom.setText(zoom_value);

        refresh();
    }

    @FXML
    private void zoomMas(MouseEvent event) {
        TextResponsive.setH1("30px");
        TextResponsive.setH2("26px");
        TextResponsive.setH3("24px");
        TextResponsive.setH4("20px");
        TextResponsive.setH5("18px");
        TextResponsive.setH6("16px");
        zoom_value = "110%";
        zoom.setText(zoom_value);

        refresh();
    }

    private void refresh() {
        HeaderController.refreshText();
        LoginController.refreshText();
        MenuController.refreshText();
        SelectMaster.refreshText();
    }
}
