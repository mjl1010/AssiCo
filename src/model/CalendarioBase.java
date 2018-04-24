package model;

public class CalendarioBase {
    private int id;
    private int id_universitat;
    private String desc_spa;
    private String desc_cat;
    private Integer week_day;
    private boolean is_summer;
    private boolean is_festivo;
    private boolean is_active;
    private String curso_academico;

    public CalendarioBase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUniversitat() {
        return id_universitat;
    }

    public void setUniversitat(int id_universitat) {
        this.id_universitat = id_universitat;
    }

    public String getDescSpa() {
        return desc_spa;
    }

    public void setDescSpa(String desc_spa) {
        this.desc_spa = desc_spa;
    }

    public String getDescCat() {
        return desc_cat;
    }

    public void setDescCat(String desc_cat) {
        this.desc_cat = desc_cat;
    }

    public Integer getWeek_day() {
        return week_day;
    }

    public void setWeekDay(Integer week_day) {
        this.week_day = week_day;
    }

    public boolean isSummer() {
        return is_summer;
    }

    public void setSummer(boolean is_summer) {
        this.is_summer = is_summer;
    }

    public boolean isFestivo() {
        return is_festivo;
    }

    public void setFestivo(boolean is_festivo) {
        this.is_festivo = is_festivo;
    }

    public boolean isActive() {
        return is_active;
    }

    public void setActive(boolean is_active) {
        this.is_active = is_active;
    }

    public String getCursoAcademico() {
        return curso_academico;
    }

    public void setCursoAcademico(String curso_academico) {
        this.curso_academico = curso_academico;
    }
}
