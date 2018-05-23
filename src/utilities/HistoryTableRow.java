package utilities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Michael
 */
public class HistoryTableRow {

    private StringProperty fechaInter;
    private StringProperty horaInter;
    private StringProperty masterID;
    private StringProperty sesionID;
    private StringProperty asignatura;
    private StringProperty contenido;
    private StringProperty doc1;
    private StringProperty doc2;
    private StringProperty dateOrigen;
    private StringProperty dateDestino;

    /**
     * Método constructor
     * @param fechaInter
     * @param horaInter
     * @param masterID
     * @param sesionID
     * @param asignatura
     * @param contenido
     * @param doc1
     * @param doc2
     * @param dateOrigen
     * @param dateDestino
     */
    public HistoryTableRow(String fechaInter, String horaInter, String masterID, String sesionID,
                           String asignatura, String contenido, String doc1, String doc2, String dateOrigen,
                           String dateDestino) {
        this.fechaInter = new SimpleStringProperty(fechaInter);
        this.horaInter = new SimpleStringProperty(horaInter);
        this.masterID = new SimpleStringProperty(masterID);
        this.sesionID = new SimpleStringProperty(sesionID);
        this.asignatura = new SimpleStringProperty(asignatura);
        this.contenido = new SimpleStringProperty(contenido);
        this.doc1 = new SimpleStringProperty(doc1);
        this.doc2 = new SimpleStringProperty(doc2);
        this.dateOrigen = new SimpleStringProperty(dateOrigen);
        this.dateDestino = new SimpleStringProperty(dateDestino);
    }

    /*** Getters and Setters ****/

    public String getFechaInter() {
        return fechaInter.get();
    }

    public StringProperty fechaInterProperty() {
        return fechaInter;
    }

    public void setFechaInter(String fechaInter) {
        this.fechaInter.set(fechaInter);
    }

    public String getHoraInter() {
        return horaInter.get();
    }

    public StringProperty horaInterProperty() {
        return horaInter;
    }

    public void setHoraInter(String horaInter) {
        this.horaInter.set(horaInter);
    }

    public String getMasterID() {
        return masterID.get();
    }

    public StringProperty masterIDProperty() {
        return masterID;
    }

    public void setMasterID(String masterID) {
        this.masterID.set(masterID);
    }

    public String getSesionID() {
        return sesionID.get();
    }

    public StringProperty sesionIDProperty() {
        return sesionID;
    }

    public void setSesionID(String sesionID) {
        this.sesionID.set(sesionID);
    }

    public String getAsignatura() {
        return asignatura.get();
    }

    public StringProperty asignaturaProperty() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura.set(asignatura);
    }

    public String getContenido() {
        return contenido.get();
    }

    public StringProperty contenidoProperty() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido.set(contenido);
    }

    public String getDoc1() {
        return doc1.get();
    }

    public StringProperty doc1Property() {
        return doc1;
    }

    public void setDoc1(String doc1) {
        this.doc1.set(doc1);
    }

    public String getDoc2() {
        return doc2.get();
    }

    public StringProperty doc2Property() {
        return doc2;
    }

    public void setDoc2(String doc2) {
        this.doc2.set(doc2);
    }

    public String getDateOrigen() {
        return dateOrigen.get();
    }

    public StringProperty dateOrigenProperty() {
        return dateOrigen;
    }

    public void setDateOrigen(String dateOrigen) {
        this.dateOrigen.set(dateOrigen);
    }

    public String getDateDestino() {
        return dateDestino.get();
    }

    public StringProperty dateDestinoProperty() {
        return dateDestino;
    }

    public void setDateDestino(String dateDestino) {
        this.dateDestino.set(dateDestino);
    }
}
