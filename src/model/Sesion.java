package model;

import javax.print.Doc;

public class Sesion {
    private int id;
    private String aula;
    private String tipoAula;
    private Docente docente1;
    private Docente docente2;
    private boolean confirmContenidos;
    private boolean confirmAula;
    private boolean confirmDocente1;
    private boolean confirmDocente2;
    private boolean confirmWarning;
    private String confirmNota;
    private String asignatura;
    private String contenidos;
    private boolean activo;
    private String colorFondo;
    private String colorTexto;
    private String nota0;
    private String nota1;
    private String nota2;
    private String nota3;
    private String nota4;
    private String nota5;
    private String nota6;
    private Master master;
    private Master masterComun;

    public Sesion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(String tipoAula) {
        this.tipoAula = tipoAula;
    }

    public Docente getDocente1() {
        return docente1;
    }

    public void setDocente1(Docente docente1) {
        this.docente1 = docente1;
    }

    public Docente getDocente2() {
        return docente2;
    }

    public void setDocente2(Docente docente2) {
        this.docente2 = docente2;
    }

    public boolean isConfirmContenidos() {
        return confirmContenidos;
    }

    public void setConfirmContenidos(boolean confirmContenidos) {
        this.confirmContenidos = confirmContenidos;
    }

    public boolean isConfirmAula() {
        return confirmAula;
    }

    public void setConfirmAula(boolean confirmAula) {
        this.confirmAula = confirmAula;
    }

    public boolean isConfirmDocente1() {
        return confirmDocente1;
    }

    public void setConfirmDocente1(boolean confirmDocente1) {
        this.confirmDocente1 = confirmDocente1;
    }

    public boolean isConfirmDocente2() {
        return confirmDocente2;
    }

    public void setConfirmDocente2(boolean confirmDocente2) {
        this.confirmDocente2 = confirmDocente2;
    }

    public boolean isConfirmWarning() {
        return confirmWarning;
    }

    public void setConfirmWarning(boolean confirmWarning) {
        this.confirmWarning = confirmWarning;
    }

    public String getConfirmNota() {
        return confirmNota;
    }

    public void setConfirmNota(String confirmNota) {
        this.confirmNota = confirmNota;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getContenidos() {
        return contenidos;
    }

    public void setContenidos(String contenidos) {
        this.contenidos = contenidos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
    }

    public String getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(String colorTexto) {
        this.colorTexto = colorTexto;
    }

    public String getNota0() {
        return nota0;
    }

    public void setNota0(String nota0) {
        this.nota0 = nota0;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    public String getNota4() {
        return nota4;
    }

    public void setNota4(String nota4) {
        this.nota4 = nota4;
    }

    public String getNota5() {
        return nota5;
    }

    public void setNota5(String nota5) {
        this.nota5 = nota5;
    }

    public String getNota6() {
        return nota6;
    }

    public void setNota6(String nota6) {
        this.nota6 = nota6;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Master getMasterComun() {
        return masterComun;
    }

    public void setMasterComun(Master masterComun) {
        this.masterComun = masterComun;
    }
}
