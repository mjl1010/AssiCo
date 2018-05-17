package controller;

import entity.Master;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Window;
import utilities.TabCalendarMaster;
import utilities.TextResponsive;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static utilities.AlertHelper.showAlert;
import static utilities.VariablesAndMethodsUtils.*;

/**
 * Created by Michael
 */
public class SesionsCalendarController implements Initializable {
    private static SesionsCalendarController main;

    @FXML
    GridPane gp_calendar1, gp_calendar2;

    @FXML
    Label lblYear1, lblMonth1, lblYear2, lblMonth2;

    @FXML
    SplitMenuButton smb_menuOption1, smb_menuOption2;

    @FXML
    MenuItem menuOpt1_1, menuOpt2_1, menuOpt3_1, menuOpt4_1, menuOpt5_1, menuOpt6_1, menuOpt7_1, menuOpt8_1, menuOpt9_1, menuOpt10_1, menuOpt1_2, menuOpt2_2, menuOpt3_2, menuOpt4_2, menuOpt5_2, menuOpt6_2, menuOpt7_2, menuOpt8_2, menuOpt9_2, menuOpt10_2;


    private ArrayList<MenuItem> aSplitMenuButton1;
    private ArrayList<MenuItem> aSplitMenuButton2;
    public static Master master_current1;


    private static TabCalendarMaster tcm1, tcm2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main = this;
        tcm1 = new TabCalendarMaster(gp_calendar1, master1, smb_menuOption1, lblYear1, lblMonth1);
        tcm2 = new TabCalendarMaster(gp_calendar2, master2, smb_menuOption2, lblYear2, lblMonth2);
        tcm1.setTcm_vinculado(tcm2);
        tcm2.setTcm_vinculado(tcm1);
        addOptionsToSplitMenu();
        configSplitMenuButton(aSplitMenuButton1, tcm1);
        configSplitMenuButton(aSplitMenuButton2, tcm2);
        refreshText();
    }

    /**
     * addOptionsToSplitMenu
     */
    private void addOptionsToSplitMenu() {
        aSplitMenuButton1 = new ArrayList<>();
        aSplitMenuButton2 = new ArrayList<>();
        aSplitMenuButton1.add(menuOpt1_1);
        aSplitMenuButton1.add(menuOpt2_1);
        aSplitMenuButton1.add(menuOpt3_1);
        aSplitMenuButton1.add(menuOpt4_1);
        aSplitMenuButton1.add(menuOpt5_1);
        aSplitMenuButton1.add(menuOpt6_1);
        aSplitMenuButton1.add(menuOpt7_1);
        aSplitMenuButton1.add(menuOpt8_1);
        aSplitMenuButton1.add(menuOpt9_1);
        aSplitMenuButton1.add(menuOpt10_1);
        aSplitMenuButton2.add(menuOpt1_2);
        aSplitMenuButton2.add(menuOpt2_2);
        aSplitMenuButton2.add(menuOpt3_2);
        aSplitMenuButton2.add(menuOpt4_2);
        aSplitMenuButton2.add(menuOpt5_2);
        aSplitMenuButton2.add(menuOpt6_2);
        aSplitMenuButton2.add(menuOpt7_2);
        aSplitMenuButton2.add(menuOpt8_2);
        aSplitMenuButton2.add(menuOpt9_2);
        aSplitMenuButton2.add(menuOpt10_2);
    }

    /**
     * configSplitMenuButton
     */
    private void configSplitMenuButton(ArrayList<MenuItem> aSplitMenuButton, TabCalendarMaster tcm) {
        for (MenuItem mi : aSplitMenuButton) {
            mi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        gestionarOpcion(mi.getId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                private void gestionarOpcion(String id) throws IOException {
                    id = id.substring(0, id.length() - 2);
                    switch (id) {
                        case "menuOpt1":// agrega sesión
                            new SesionTableController().openScene(tcm);
                            break;
                        case "menuOpt2":// Quitar sesión
                            tcm.outSesion();
                            tcm.updateCalendar();
                            break;
                        case "menuOpt3":// Intercambio de Sesión
                            Window owner = smb_menuOption1.getScene().getWindow();
                            showAlert(Alert.AlertType.INFORMATION, owner, "Intercambio de Sesiones", "Seleccione con doble Click la sesión que desea cambiar");
                            tcm.setDate_exchange1(tcm.getDate(tcm.getGp_waiting()));
                            tcm.setIschange(true);
                            break;
                        case "menuOpt4":// Editar Practica 1
                            break;
                        case "menuOpt5":// Editar Practica 2
                            break;
                        case "menuOpt6":// Editar de Asignatura
                            new UpdateAsignController().openScene(tcm);
                            break;
                        case "menuOpt7":// Editar Contenido
                            new UpdateContenidoController().openScene(tcm);
                            break;
                        case "menuOpt8":// Editar Profesor(s)
                            new UpdateDocenteController().openScene(tcm);
                            break;
                        case "menuOpt9":// Editar Tipo de Aula
                            new UpdateTipoAulaController().openScene(tcm);
                            break;
                        case "menuOpt10":// Editar Aula
                            new UpdateAulaController().openScene(tcm);
                            break;
                    }
                }
            });
        }
    }


    /**
     * month previous
     *
     * @param mouseEvent
     */
    public void updateMonth1(MouseEvent mouseEvent) {
        tcm1.updateMonth(mouseEvent);
        if (tcm2 != null) tcm2.updateMonth(mouseEvent);

    }

    public void updateMonth2(MouseEvent mouseEvent) {
        tcm1.updateMonth(mouseEvent);
        tcm2.updateMonth(mouseEvent);
    }

    public static void refreshText() {
        if (main == null) return;

        main.lblYear1.setStyle(TextResponsive.getFontStyle("h3"));
        main.lblYear2.setStyle(TextResponsive.getFontStyle("h3"));
        main.lblMonth1.setStyle(TextResponsive.getFontStyle("h3"));
        main.lblMonth2.setStyle(TextResponsive.getFontStyle("h3"));

        tcm1.updateCalendar();
        tcm2.updateCalendar();
    }
}




