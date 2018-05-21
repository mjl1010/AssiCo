package utilities;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import start.MainLogin;
import utilities.TextResponsive;

import static utilities.VariablesAndMethodsUtils.aDocentesID;
import static utilities.VariablesAndMethodsUtils.aTiposAula;


/**
 * Created by Michael
 */
public class GridSesion {

    private Label lblDateID, lblAux1, lblAux2, lblAsign, lblContenido, lblJuntSep, lblAula;
    private GridPane miniGrid;
    private HBox hBox_doc;
    private ComboBox cbo_tipoAula, cbo_doc1, cbo_doc2;
    private int indexRow, indexColum;
    private int sesionID = -1;
    private String backgroundColor, textColor;

    public GridSesion() {
    }

    /**
     * Método Constructor 2
     * @param dateID
     */
    public GridSesion(String dateID, int indexRow, int indexColum) {
        lblDateID = new Label(dateID);
        lblDateID.setId(dateID);
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
        this.indexRow = indexRow;
        this.indexColum = indexColum;
        backgroundColor = "#f5f5f5";
        textColor = "#000000";
        settingMiniGrid();
    }

    /**
     * Método Constructor 2
     */
    public GridSesion(String dateID, String aux1, String aux2, String Asign, String conten,
                      String juntSep, String aula) {
        lblDateID = new Label(dateID);
        lblDateID.setId(dateID);
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

    public int getIndexRow() {
        return indexRow;
    }

    public void setIndexRow(int indexRow) {
        this.indexRow = indexRow;
    }

    public int getIndexColum() {
        return indexColum;
    }

    public ComboBox getCbo_tipoAula() {
        return cbo_tipoAula;
    }

    public ComboBox getCbo_doc1() {
        return cbo_doc1;
    }

    public ComboBox getCbo_doc2() {
        return cbo_doc2;
    }

    public void setIndexColum(int indexColum) {
        this.indexColum = indexColum;
    }

    public void setLblDateID(Label lblDateID) {
        this.lblDateID = lblDateID;
    }

    public void setLblAux1(Label lblAux1) {
        this.lblAux1 = lblAux1;
    }

    public void setLblAux2(Label lblAux2) {
        this.lblAux2 = lblAux2;
    }

    public void setLblAsign(Label lblAsign) {
        this.lblAsign = lblAsign;
    }

    public void setLblContenido(Label lblContenido) {
        this.lblContenido = lblContenido;
    }

    public void setLblJuntSep(Label lblJuntSep) {
        this.lblJuntSep = lblJuntSep;
    }

    public void setLblAula(Label lblAula) {
        this.lblAula = lblAula;
    }

    public int getSesionID() {
        return sesionID;
    }

    public void setSesionID(int sesionID) {
        this.sesionID = sesionID;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        System.out.println(":D");
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    /**** Métodos Agregados ****/

    /**
     * setting Mini Grid
     */
    private void settingMiniGrid() {
        miniGrid = new GridPane();
        miniGrid.gridLinesVisibleProperty().setValue(false);

        miniGrid.getColumnConstraints().add(0, new ColumnConstraints());
        miniGrid.getColumnConstraints().add(1, new ColumnConstraints(100));
        miniGrid.getColumnConstraints().add(2, new ColumnConstraints());

        miniGrid.getRowConstraints().add(0, new RowConstraints(28));
        miniGrid.getRowConstraints().add(1, new RowConstraints(27));
        miniGrid.getRowConstraints().add(2, new RowConstraints(27));

        lblDateID.setPrefHeight(26);
        miniGrid.setStyle("-fx-background-color: " + backgroundColor + ";");

        lblDateID.setStyle("-fx-background-color: " + backgroundColor + "; -fx-font: " + TextResponsive.getH6() + " \"System Bold\"; -fx-text-fill: " + textColor + ";");

        lblAsign.setFont(new Font(9));
        lblContenido.setFont(new Font(9));

        hBox_doc.getChildren().addAll(cbo_doc1, cbo_doc2);
        hBox_doc.setAlignment(Pos.CENTER);

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

        cbo_tipoAula.getItems().addAll(aTiposAula);
        cbo_tipoAula.setStyle("-fx-font: 10px \"Serif\";");

        cbo_doc1.getItems().addAll(aDocentesID);
        cbo_doc1.setPrefHeight(20);
        cbo_doc1.setStyle("-fx-font: 9px \"Serif\";");

        cbo_doc2.getItems().addAll(aDocentesID);
        cbo_doc2.setPrefHeight(20);
        cbo_doc2.setStyle("-fx-font: 9px \"Serif\";");

        setVisibleComboBoxs(false);

    }

    /**
     *
     * @param val
     */
    public void setVisibleComboBoxs(boolean val){
        cbo_tipoAula.setVisible(val);
        cbo_doc1.setVisible(val);
        cbo_doc2.setVisible(val);
    }

    @Override
    public String toString() {
        return "GridSesion{" +
                "lblDateID=" + lblDateID +
                ", miniGrid=" + miniGrid +
                ", indexRow=" + indexRow +
                ", indexColum=" + indexColum +
                '}';
    }
}
