package utilities;

public class SesionTableRow {

    private int sesionID;
    private int master1;
    private int master2;
    private String asignatura;
    private String contenido;
    private String docentet1;
    private String docentet2;
    private String TipoAula;
    private String Aula;
    private String nota;

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
    public SesionTableRow(int sesionID, int master1, int master2, String asignatura, String contenido, String docentet1,
                          String docentet2, String tipoAula, String aula, String nota) {
        this.sesionID = sesionID;
        this.master1 = master1;
        this.master2 = master2;
        this.asignatura = asignatura;
        this.contenido = contenido;
        this.docentet1 = docentet1;
        this.docentet2 = docentet2;
        TipoAula = tipoAula;
        Aula = aula;
        this.nota = nota;
    }

    /**** Getters and Setters *****/

    public int getSesionID() {
        return sesionID;
    }

    public void setSesionID(int sesionID) {
        this.sesionID = sesionID;
    }

    public int getMaster1() {
        return master1;
    }

    public void setMaster1(int master1) {
        this.master1 = master1;
    }

    public int getMaster2() {
        return master2;
    }

    public void setMaster2(int master2) {
        this.master2 = master2;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDocentet1() {
        return docentet1;
    }

    public void setDocentet1(String docentet1) {
        this.docentet1 = docentet1;
    }

    public String getDocentet2() {
        return docentet2;
    }

    public void setDocentet2(String docentet2) {
        this.docentet2 = docentet2;
    }

    public String getTipoAula() {
        return TipoAula;
    }

    public void setTipoAula(String tipoAula) {
        TipoAula = tipoAula;
    }

    public String getAula() {
        return Aula;
    }

    public void setAula(String aula) {
        Aula = aula;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SesionTableRow that = (SesionTableRow) o;

        return sesionID == that.sesionID;
    }

    @Override
    public int hashCode() {
        return sesionID;
    }
}
