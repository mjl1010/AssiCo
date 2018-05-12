package utilities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SesionTableRow {

    private StringProperty sesionID;
    private StringProperty master1;
    private StringProperty master2;
    private StringProperty asignatura;
    private StringProperty contenido;
    private StringProperty docentet1;
    private StringProperty docentet2;
    private StringProperty tipoAula;
    private StringProperty aula;
    private StringProperty nota;

    /**
     * Método Constructor
     * @param sesionID
     * @param master1
     * @param master2
     * @param asignatura
     * @param contenido
     * @param docentet1
     * @param docentet2
     * @param tipoAula
     * @param aula
     * @param nota
     */
    public SesionTableRow(String sesionID, String master1, String master2, String asignatura, String contenido, String docentet1,
                          String docentet2, String tipoAula, String aula, String nota) {
        this.sesionID = new SimpleStringProperty(sesionID);
        this.master1 = new SimpleStringProperty(master1);
        this.master2 = new SimpleStringProperty(master2);
        this.asignatura = new SimpleStringProperty(asignatura);
        this.contenido = new SimpleStringProperty(contenido);
        this.docentet1 = new SimpleStringProperty(docentet1);
        this.docentet2 = new SimpleStringProperty(docentet2);
        this.tipoAula = new SimpleStringProperty(tipoAula);
        this.aula = new SimpleStringProperty(aula);
        this.nota = new SimpleStringProperty(nota);
    }

    /**** Getters and Setters *****/

    public String getSesionID() {
        return sesionID.toString();
    }
    public void setSesionID(String sesionID) {
        this.sesionID.set(sesionID);
    }
    public StringProperty sesionIDProperty() {
        return sesionID;
    }

    public String getMaster1() {
        return master1.toString();
    }
    public void setMaster1(String master1) {
        this.master1.set(master1);
    }
    public StringProperty master1Property() {
        return master1;
    }

    public String getMaster2() {
        return master2.toString();
    }
    public void setMaster2(String master2) {
        this.master2.set(master2);
    }
    public StringProperty master2Property() {
        return master2;
    }

    public String getAsignatura() {
        return asignatura.toString();
    }
    public void setAsignatura(String asignatura) {
        this.asignatura.set(asignatura);
    }
    public StringProperty asignaturaProperty() {
        return asignatura;
    }

    public String getContenido() {
        return contenido.toString();
    }
    public void setContenido(String contenido) {
        this.contenido.set(contenido);
    }
    public StringProperty contenidoProperty() {
        return contenido;
    }

    public String getDocentet1() {
        return docentet1.toString();
    }
    public void setDocentet1(String docentet1) {
        this.docentet1.set(docentet1);
    }
    public StringProperty docentet1Property() {
        return docentet1;
    }

    public String getDocentet2() {
        return docentet2.toString();
    }
    public void setDocentet2(String docentet2) {
        this.docentet2.set(docentet2);
    }
    public StringProperty docentet2Property() {
        return docentet2;
    }

    public String getTipoAula() {
        return tipoAula.toString();
    }
    public void setTipoAula(String tipoAula) {
        this.tipoAula.set(tipoAula);
    }
    public StringProperty tipoAulaProperty() {
        return tipoAula;
    }

    public String getAula() {
        return aula.toString();
    }
    public void setAula(String aula) {
        this.aula.set(aula);
    }
    public StringProperty aulaProperty() {
        return aula;
    }

    public String getNota() {
        return nota.toString();
    }
    public void setNota(String nota) {
        this.nota.set(nota);
    }
    public StringProperty notaProperty() {
        return nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SesionTableRow that = (SesionTableRow) o;

        return sesionID != null ? sesionID.equals(that.sesionID) : that.sesionID == null;
    }

    @Override
    public int hashCode() {
        return sesionID != null ? sesionID.hashCode() : 0;
    }
}
