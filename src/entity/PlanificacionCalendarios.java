package entity;

import java.io.Serializable;
import java.util.Objects;

public class PlanificacionCalendarios implements Serializable {
    private CalendarioBase calendarioBase;
    private int dia;
    private Universidad universidad;
    private Sesion sesion;
    private Master master;

    public PlanificacionCalendarios() {}

    public CalendarioBase getCalendarioBase() {
        return calendarioBase;
    }

    public void setCalendarioBase(CalendarioBase calendarioBase) {
        this.calendarioBase = calendarioBase;
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

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanificacionCalendarios that = (PlanificacionCalendarios) o;
        return dia == that.dia &&
                Objects.equals(calendarioBase, that.calendarioBase) &&
                Objects.equals(universidad, that.universidad) &&
                Objects.equals(sesion, that.sesion) &&
                Objects.equals(master, that.master);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendarioBase, dia, universidad, sesion, master);
    }

    @Override
    public String toString() {
        return "PlanificacionCalendarios{" +
                "calendarioBase=" + calendarioBase +
                ", dia=" + dia +
                ", universidad=" + universidad +
                ", sesion=" + sesion +
                ", master=" + master +
                '}';
    }
}
