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

    public static int zoom_value = 100;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = this;
        if (zoom != null) zoom.setText(zoom_value + "%");
    }

    @FXML
    private void zoomMenos(MouseEvent event) {
        if (zoom_value == 80) return;
        zoom_value = zoom_value-10;
        applyZoom();
    }

    @FXML
    private void zoomIgual(MouseEvent event) {
        zoom_value = 100;
        applyZoom();
    }

    @FXML
    private void zoomMas(MouseEvent event) {
        if (zoom_value == 120) return;
        zoom_value = zoom_value+10;
        applyZoom();
    }

    private void applyZoom() {
        switch (zoom_value) {
            case 80:
                TextResponsive.setH1("22px");
                TextResponsive.setH2("18px");
                TextResponsive.setH3("16px");
                TextResponsive.setH4("14px");
                TextResponsive.setH5("12px");
                TextResponsive.setH6("10px");
                break;
            case 90:
                TextResponsive.setH1("24px");
                TextResponsive.setH2("20px");
                TextResponsive.setH3("18px");
                TextResponsive.setH4("16px");
                TextResponsive.setH5("14px");
                TextResponsive.setH6("12px");
                break;
            case 100:
                TextResponsive.setH1("26px");
                TextResponsive.setH2("24px");
                TextResponsive.setH3("20px");
                TextResponsive.setH4("18px");
                TextResponsive.setH5("16px");
                TextResponsive.setH6("14px");
                break;
            case 110:
                TextResponsive.setH1("30px");
                TextResponsive.setH2("26px");
                TextResponsive.setH3("24px");
                TextResponsive.setH4("20px");
                TextResponsive.setH5("18px");
                TextResponsive.setH6("16px");
                break;
            case 120:
                TextResponsive.setH1("36px");
                TextResponsive.setH2("30px");
                TextResponsive.setH3("26px");
                TextResponsive.setH4("24px");
                TextResponsive.setH5("20px");
                TextResponsive.setH6("18px");
                break;
        }

        zoom.setText(zoom_value + "%");
        refresh();
    }

    private void refresh() {
        HeaderController.refreshText();
        LoginController.refreshText();
        MenuController.refreshText();
        SelectMaster.refreshText();
    }
}
