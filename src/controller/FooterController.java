package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import start.MainLogin;
import utilities.TextResponsive;

import java.net.URL;
import java.util.ResourceBundle;

public class FooterController implements Initializable {
    protected static FooterController main;

    @FXML
    private Label zoom, zoom_menos, zoom_mas;

    @FXML
    private HBox screen;

    private static int zoom_value = 100;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = this;
        if (zoom != null) zoom.setText(zoom_value + "%");

        if (Screen.getScreens().size() > 1) {
            for (int i = 0; i<Screen.getScreens().size(); i++) {
                Label l = new Label();
                l.setPrefHeight(25);
                l.setPrefWidth(25);
                l.setText(String.valueOf(i+1));
                l.setAlignment(Pos.CENTER);
                if (!Screen.getScreens().get(i).equals(MainLogin.screen)) l.setStyle("-fx-background-image: url(/view/res/computer-screen.png); -fx-background-repeat: no-repeat;");
                else l.setStyle("-fx-background-image: url(/view/res/computer-screen-selected.png); -fx-background-repeat: no-repeat;");

                int finalI = i;
                l.setOnMouseClicked(t -> {
                    for (Node node : screen.getChildren()) node.setStyle("-fx-background-image: url(/view/res/computer-screen.png); -fx-background-repeat: no-repeat;");
                    l.setStyle("-fx-background-image: url(/view/res/computer-screen-selected.png); -fx-background-repeat: no-repeat;");
                    MainLogin.screen = Screen.getScreens().get(finalI);
                });

                screen.getChildren().add(l);
            }
        }
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
                TextResponsive.setH1(22);
                TextResponsive.setH2(18);
                TextResponsive.setH3(16);
                TextResponsive.setH4(14);
                TextResponsive.setH5(12);
                TextResponsive.setH6(10);
                break;
            case 90:
                TextResponsive.setH1(24);
                TextResponsive.setH2(20);
                TextResponsive.setH3(18);
                TextResponsive.setH4(16);
                TextResponsive.setH5(14);
                TextResponsive.setH6(12);
                break;
            case 100:
                TextResponsive.setH1(26);
                TextResponsive.setH2(24);
                TextResponsive.setH3(20);
                TextResponsive.setH4(18);
                TextResponsive.setH5(16);
                TextResponsive.setH6(14);
                break;
            case 110:
                TextResponsive.setH1(30);
                TextResponsive.setH2(26);
                TextResponsive.setH3(24);
                TextResponsive.setH4(20);
                TextResponsive.setH5(18);
                TextResponsive.setH6(16);
                break;
            case 120:
                TextResponsive.setH1(36);
                TextResponsive.setH2(30);
                TextResponsive.setH3(26);
                TextResponsive.setH4(24);
                TextResponsive.setH5(20);
                TextResponsive.setH6(18);
                break;
        }

        zoom.setText(zoom_value + "%");
        refresh();
    }

    private void refresh() {
        HeaderController.refreshText();
        LoginController.refreshText();
        MastersList.refreshText();
        MenuController.refreshText();
        SelectCourse.refreshText();
        SelectMaster.refreshText();
        CourseRangeController.refreshText();
        HolydayCalendarController.refreshText();
        SesionsCalendarController.refreshText();
    }
}
