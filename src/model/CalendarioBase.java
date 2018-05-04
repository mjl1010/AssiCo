package model;

public class CalendarioBase {
    private int dia;
    private Universidad universidad;
    private String descSpa;
    private String descCat;
    private Integer weekDay;
    private boolean isSummer;
    private boolean isFestivo;
    private boolean isActive = true;
    private String cursoAcademico;

    // Solo para interfaz
    private String idDate;

    public CalendarioBase() {
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public String getDescSpa() {
        return descSpa;
    }

    public void setDescSpa(String descSpa) {
        this.descSpa = descSpa;
    }

    public String getDescCat() {
        return descCat;
    }

    public void setDescCat(String descCat) {
        this.descCat = descCat;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public boolean isSummer() {
        return isSummer;
    }

    public void setSummer(boolean summer) {
        isSummer = summer;
    }

    public boolean isFestivo() {
        return isFestivo;
    }

    public void setFestivo(boolean festivo) {
        isFestivo = festivo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        this.cursoAcademico = cursoAcademico;
    }

    public String getIdDate() {
        return idDate;
    }

    public void setIdDate(String idDate) {
        this.idDate = idDate;
    }
}
