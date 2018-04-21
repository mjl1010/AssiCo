package model;

/**
 * Created by J Michael on 20/04/2018.
 */
public class TableRow {

    private String dateMonday;
    private String dateTuesday;
    private String dateWednesday;
    private String dateThursDay;
    private String dateFriday;
    private String dateSaturday;
    private String dateSunday;

    /**
     *
     */
    public TableRow() {
    }

    /**
     * Método Constructor
     * @param dateMonday
     * @param dateTuesday
     * @param dateWednesday
     * @param dateThursDay
     * @param dateFriday
     * @param dateSaturday
     * @param dateSunday
     */
    public TableRow(String dateMonday, String dateTuesday, String dateWednesday,
                    String dateThursDay, String dateFriday, String dateSaturday,
                    String dateSunday) {

        this.dateMonday = dateMonday;
        this.dateTuesday = dateTuesday;
        this.dateWednesday = dateWednesday;
        this.dateThursDay = dateThursDay;
        this.dateFriday = dateFriday;
        this.dateSaturday = dateSaturday;
        this.dateSunday = dateSunday;
    }

    /*** Getters and Setters ***/

    public String getDateMonday() {
        return dateMonday;
    }

    public void setDateMonday(String dateMonday) {
        this.dateMonday = dateMonday;
    }

    public String getDateTuesday() {
        return dateTuesday;
    }

    public void setDateTuesday(String dateTuesday) {
        this.dateTuesday = dateTuesday;
    }

    public String getDateWednesday() {
        return dateWednesday;
    }

    public void setDateWednesday(String dateWednesday) {
        this.dateWednesday = dateWednesday;
    }

    public String getDateThursDay() {
        return dateThursDay;
    }

    public void setDateThursDay(String dateThursDay) {
        this.dateThursDay = dateThursDay;
    }

    public String getDateFriday() {
        return dateFriday;
    }

    public void setDateFriday(String dateFriday) {
        this.dateFriday = dateFriday;
    }

    public String getDateSaturday() {
        return dateSaturday;
    }

    public void setDateSaturday(String dateSaturday) {
        this.dateSaturday = dateSaturday;
    }

    public String getDateSunday() {
        return dateSunday;
    }

    public void setDateSunday(String dateSunday) {
        this.dateSunday = dateSunday;
    }
}
