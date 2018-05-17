package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
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

//                ImageView img = new ImageView("/view/res/computer-screen.png");
//                img.setFitWidth(25);
//                img.setFitHeight(25);
//                img.setOnMouseClicked(t -> {
//                    ColorAdjust blackout = new ColorAdjust();
//                    blackout.setBrightness(1.0);
//                    MainLogin.screen = s;
//                    img.setEffect(blackout);
//                });

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
        MastersList.refreshText();
        MenuController.refreshText();
        SelectCourse.refreshText();
        SelectMaster.refreshText();
        CourseRangeController.refreshText();
        HolydayCalendarController.refreshText();
        SesionsCalendarController.refreshText();
    }
}
