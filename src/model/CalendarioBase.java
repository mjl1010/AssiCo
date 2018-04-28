package model;

import java.io.Serializable;

/**
 *
 */
public class CalendarioBase implements Serializable {

    private int id;
    private int id_universitat;
    private String desc_spa;
    private String desc_cat;
    private Integer week_day;
    private boolean is_summer;
    private boolean is_festivo;
    private boolean is_active;
    private String curso_academico;

    /**
     * Metodo Constructor
     */
    public CalendarioBase() {
    }

    /**
     * Metodo Constructor
     * first insert calendario base
     * @param id
     * @param is_summer
     */
    public CalendarioBase(int id, boolean is_summer) {
        this.id = id;
        this.is_summer = is_summer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarioBase that = (CalendarioBase) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

//    @Override
//    public String toString() {
//        return "CalendarioBase{" +
//                "id=" + id +
//                ", is_summer=" + is_summer +
//                '}';
//    }


    @Override
    public String toString() {
        return "CalendarioBase{" +
                "id=" + id +
                ", id_universitat=" + id_universitat +
                ", desc_spa='" + desc_spa + '\'' +
                ", desc_cat='" + desc_cat + '\'' +
                ", week_day=" + week_day +
                ", is_summer=" + is_summer +
                ", is_festivo=" + is_festivo +
                ", is_active=" + is_active +
                ", curso_academico='" + curso_academico + '\'' +
                '}';
    }

    public static void main(String[] args) {
        CalendarioBase c = new CalendarioBase(123, true);

        System.out.println(c);

    }
}
