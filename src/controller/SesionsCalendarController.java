package controller;

import entity.Master;
import entity.Sesion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.GridSesion;
import model.TabCalendarMaster;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static utilities.VariablesAndMethodsUtils.*;

/**
 *
 */
public class SesionsCalendarController implements Initializable {

    @FXML
    GridPane gp_calendar1;

    @FXML
    Label lblYear1, lblMonth1;

    @FXML
    SplitMenuButton smb_menuOption1;

    @FXML
    MenuItem menuOpt1_1, menuOpt2_1, menuOpt3_1, menuOpt4_1, menuOpt5_1, menuOpt6_1, menuOpt7_1, menuOpt8_1, menuOpt9_1, menuOpt10_1;

    private ArrayList<MenuItem> aSplitMenuButton;
    public static Master master_current;

    private static SplitMenuButton smb_menuOption_st;

    private TabCalendarMaster tcm1, tcm2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addOptionsToSplitMenu();
        configSplitMenuButton();
        tcm1 = new TabCalendarMaster(gp_calendar1, master1, smb_menuOption1, lblYear1, lblMonth1);
    }

    /**
     * addOptionsToSplitMenu
     */
    private void addOptionsToSplitMenu() {
        aSplitMenuButton = new ArrayList<>();
        aSplitMenuButton.add(menuOpt1_1);
        aSplitMenuButton.add(menuOpt2_1);
        aSplitMenuButton.add(menuOpt3_1);
        aSplitMenuButton.add(menuOpt4_1);
        aSplitMenuButton.add(menuOpt5_1);
        aSplitMenuButton.add(menuOpt6_1);
        aSplitMenuButton.add(menuOpt7_1);
        aSplitMenuButton.add(menuOpt8_1);
        aSplitMenuButton.add(menuOpt9_1);
        aSplitMenuButton.add(menuOpt10_1);
    }

    /**
     * configSplitMenuButton
     */
    private void configSplitMenuButton() {
        for (MenuItem mi :
                aSplitMenuButton) {
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
                    switch (id) {
                        case "menuOpt1_1":
                            // agrega sesión
                            new SesionTableController().openScene(tcm1);
                            break;
                        case "menuOpt2_1":
                            // Quitar sesión
                            outSesion();
                            tcm1.updateCalendar();
                            printSesionReg();
                            break;
                        case "menuOpt3_1":
                            // Intercambio de Sesión
                            break;
                        case "menuOpt4_1":
                            // Editar Practica 1
                            break;
                        case "menuOpt5_1":
                            // Editar Practica 2
                            break;
                        case "menuOpt6_1":
                            // Editar de Asignatura
                            new UpdateAsignController().openScene();

                            break;
                        case "menuOpt7_1":
                            // Editar Contenido
                            new UpdateContenidoController().openScene();
                            break;
                        case "menuOpt8_1":
                            // Editar Profesor(s)
                            new UpdateDocenteController().openScene();
                            break;
                        case "menuOpt9_1":
                            // Editar Tipo de Aula
                            new UpdateTipoAulaController().openScene();
                            break;
                        case "menuOpt10_1":
                            // Editar Aula
                            new UpdateAulaController().openScene();
                            break;
                    }
                }
            });
        }
    }

    /**
     * sesion sube
     * al limpio
     */
    private void outSesion() {
        for (GridSesion gs :
                aGridSesions) {
            if (gs.getMiniGrid().equals(gp_waiting)){
                System.out.println("gs.getSesionID() : " + gs.getSesionID());
                System.out.println("getSesion(gs.getSesionID()) : " + getSesion(gs.getSesionID()));
                if (getSesion(gs.getSesionID()).getMaster1() != null)
                    removeSesionToPlanifList(getCalBasID(gs.getLblDateID().getText()), master1);
                if (getSesion(gs.getSesionID()).getMaster2() != null)
                    removeSesionToPlanifList(getCalBasID(gs.getLblDateID().getText()), master2);
                setActiveValueSesion(gs.getSesionID());
                break;
            }
        }
    }

    /**
     * setActive() - sesion object
     * back limbo
     * @param sesionID
     */
    private void setActiveValueSesion(int sesionID) {
        for (Sesion s :
                aSession) {
            if (s.getId() == sesionID) {
                s.setActivo(false);
                break;
            }
        }
    }

    /**
     * month previous
     *
     * @param mouseEvent
     */
    public void updateMonth(MouseEvent mouseEvent) {
        tcm1.updateMonth(mouseEvent);
    }
}




