package model;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.awt.*;


/**
 * Created by Michael
 */
public class GridSesion {

    private Label lblDateID, lblAux1, lblAux2, lblAsign, lblContenido, lblJuntSep, lblAula;
    private GridPane miniGrid;
    private HBox hBox_doc;
    private ComboBox cbo_tipoAula, cbo_doc1, cbo_doc2;

    /**
     * Método Constructor 1
     * @param dateID
     */
    public GridSesion(String dateID) {
        lblDateID = new Label(dateID);
        lblAux1 = new Label();
        lblAux2 = new Label();
        lblAsign = new Label();
        lblContenido = new Label();
        lblJuntSep = new Label();
        lblAula = new Label();
        cbo_doc1 = new ComboBox();
        cbo_doc2 = new ComboBox();
        cbo_tipoAula = new ComboBox();
        hBox_doc = new HBox();
        settingMiniGrid();
    }

    /**
     * Método Constructor 2
     */
    public GridSesion(String dateID, String aux1, String aux2, String Asign, String conten,
                      String juntSep, String aula) {
        lblDateID = new Label(dateID);
        lblAux1 = new Label(aux1);
        lblAux2 = new Label(aux2);
        lblAsign = new Label(Asign);
        lblContenido = new Label(conten);
        lblJuntSep = new Label(juntSep);
        lblAula = new Label(aula);
        cbo_doc1 = new ComboBox();
        cbo_doc2 = new ComboBox();
        cbo_tipoAula = new ComboBox();
        hBox_doc = new HBox();
        settingMiniGrid();
    }

    /*** Getters and Setters ***/

    public GridPane getMiniGrid() {
        return miniGrid;
    }

    public Label getLblDateID() {
        return lblDateID;
    }

    public Label getLblAux1() {
        return lblAux1;
    }

    public Label getLblAux2() {
        return lblAux2;
    }

    public Label getLblAsign() {
        return lblAsign;
    }

    public Label getLblContenido() {
        return lblContenido;
    }

    public Label getLblJuntSep() {
        return lblJuntSep;
    }

    public Label getLblAula() {
        return lblAula;
    }

    /**** Métodos Agregados ****/

    /**
     * setting Mini Grid
     */
    private void settingMiniGrid() {
        miniGrid = new GridPane();
        miniGrid.gridLinesVisibleProperty().setValue(true);

        miniGrid.getColumnConstraints().add(0, new ColumnConstraints());
        miniGrid.getColumnConstraints().add(1, new ColumnConstraints(100));
        miniGrid.getColumnConstraints().add(2, new ColumnConstraints());

        miniGrid.getRowConstraints().add(0, new RowConstraints(28));
        miniGrid.getRowConstraints().add(1, new RowConstraints(27));
        miniGrid.getRowConstraints().add(2, new RowConstraints(27));

        lblDateID.setPrefHeight(26);

        lblDateID.setStyle("-fx-background-color: #BE81F7");
        lblDateID.setTextFill(Paint.valueOf("#f2efef"));
        lblDateID.setFont(new Font("System Bold", 10));
        lblAsign.setFont(new Font(9));
        lblContenido.setFont(new Font(9));

        hBox_doc.getChildren().addAll(cbo_doc1, cbo_doc2);

        miniGrid.add(lblDateID, 0, 0);
        miniGrid.add(lblAux1, 0, 1);
        miniGrid.add(lblAux2, 0, 2);

        miniGrid.add(lblAsign, 1, 0);
        miniGrid.add(lblContenido, 1, 1);
        miniGrid.add(hBox_doc, 1, 2);

        miniGrid.add(lblJuntSep, 2, 0);
        miniGrid.add(cbo_tipoAula, 2, 1);
        miniGrid.add(lblAula, 2, 2);

        miniGrid.getColumnConstraints().get(0).setHalignment(HPos.CENTER);
        miniGrid.getColumnConstraints().get(1).setHalignment(HPos.CENTER);
        miniGrid.getColumnConstraints().get(2).setHalignment(HPos.CENTER);
    }

}
