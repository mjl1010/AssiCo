package utilities;

import entity.Master;
import entity.Sesion;

import java.time.LocalDateTime;

/**
 * Created by Michael
 */
public class RecordHistory {

    private LocalDateTime ldt_changeDate;
    private Master master;
    private Sesion sesion_1;
    private String originalDate_1;
    private String destinationDate_1;
    private Sesion sesion_2;
    private String originalDate_2;
    private String destinationDate_2;

    /**
     * Método Cosntructor
     * @param ldt_changeDate
     * @param master
     * @param sesion_1
     * @param originalDate_1
     * @param destinationDate_1
     * @param sesion_2
     * @param originalDate_2
     * @param destinationDate_2
     */
    public RecordHistory(LocalDateTime ldt_changeDate, entity.Master master, Sesion sesion_1, String originalDate_1, String destinationDate_1,
                         Sesion sesion_2, String originalDate_2, String destinationDate_2) {
        this.ldt_changeDate = ldt_changeDate;
        this.master = master;
        this.sesion_1 = sesion_1;
        this.originalDate_1 = originalDate_1;
        this.destinationDate_1 = destinationDate_1;
        this.sesion_2 = sesion_2;
        this.originalDate_2 = originalDate_2;
        this.destinationDate_2 = destinationDate_2;
    }

    /**** Getters and Setters ****/

    public LocalDateTime getLdt_changeDate() {
        return ldt_changeDate;
    }

    public void setLdt_changeDate(LocalDateTime ldt_changeDate) {
        this.ldt_changeDate = ldt_changeDate;
    }

    public entity.Master getMaster() {
        return master;
    }

    public void setMaster(entity.Master master) {
        this.master = master;
    }

    public Sesion getSesion_1() {
        return sesion_1;
    }

    public void setSesion_1(Sesion sesion_1) {
        this.sesion_1 = sesion_1;
    }

    public String getOriginalDate_1() {
        return originalDate_1;
    }

    public void setOriginalDate_1(String originalDate_1) {
        this.originalDate_1 = originalDate_1;
    }

    public String getDestinationDate_1() {
        return destinationDate_1;
    }

    public void setDestinationDate_1(String destinationDate_1) {
        this.destinationDate_1 = destinationDate_1;
    }

    public Sesion getSesion_2() {
        return sesion_2;
    }

    public void setSesion_2(Sesion sesion_2) {
        this.sesion_2 = sesion_2;
    }

    public String getOriginalDate_2() {
        return originalDate_2;
    }

    public void setOriginalDate_2(String originalDate_2) {
        this.originalDate_2 = originalDate_2;
    }

    public String getDestinationDate_2() {
        return destinationDate_2;
    }

    public void setDestinationDate_2(String destinationDate_2) {
        this.destinationDate_2 = destinationDate_2;
    }
}
