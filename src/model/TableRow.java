package model;

import java.util.ArrayList;

/**
 * Created by J Michael on 20/04/2018.
 */
public class TableRow {

    private StringBuilder dateMonday;
    private StringBuilder dateTuesday;
    private StringBuilder dateWednesday;
    private StringBuilder dateThursDay;
    private StringBuilder dateFriday;
    private StringBuilder dateSaturday;
    private StringBuilder dateSunday;
    private ArrayList<StringBuilder> aListDayDates;

    /**
     * Método Constructor
     * por default
     */
    public TableRow() {
        dateMonday = new StringBuilder("");
        dateTuesday = new StringBuilder("");
        dateWednesday = new StringBuilder("");
        dateThursDay = new StringBuilder("");
        dateFriday = new StringBuilder("");
        dateSaturday = new StringBuilder("");
        dateSunday = new StringBuilder("");
        addList();
    }

    /**
     * Método Constructor
     *
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

        this.dateMonday = new StringBuilder(dateMonday);
        this.dateTuesday = new StringBuilder(dateTuesday);;
        this.dateWednesday = new StringBuilder(dateWednesday);;
        this.dateThursDay = new StringBuilder(dateThursDay);;
        this.dateFriday = new StringBuilder(dateFriday);;
        this.dateSaturday = new StringBuilder(dateSaturday);;
        this.dateSunday = new StringBuilder(dateSunday);;
    }

    private void addList() {
        aListDayDates = new ArrayList<StringBuilder>();
        aListDayDates.add(this.dateMonday);
        aListDayDates.add(this.dateTuesday);
        aListDayDates.add(this.dateWednesday);
        aListDayDates.add(this.dateThursDay);
        aListDayDates.add(this.dateFriday);
        aListDayDates.add(this.dateSaturday);
        aListDayDates.add(this.dateSunday);
    }

    /*** Getters and Setters ***/

    public StringBuilder getDateMonday() {
        return dateMonday;
    }

    public void setDateMonday(StringBuilder dateMonday) {
        this.dateMonday = dateMonday;
    }

    public StringBuilder getDateTuesday() {
        return dateTuesday;
    }

    public void setDateTuesday(StringBuilder dateTuesday) {
        this.dateTuesday = dateTuesday;
    }

    public StringBuilder getDateWednesday() {
        return dateWednesday;
    }

    public void setDateWednesday(StringBuilder dateWednesday) {
        this.dateWednesday = dateWednesday;
    }

    public StringBuilder getDateThursDay() {
        return dateThursDay;
    }

    public void setDateThursDay(StringBuilder dateThursDay) {
        this.dateThursDay = dateThursDay;
    }

    public StringBuilder getDateFriday() {
        return dateFriday;
    }

    public void setDateFriday(StringBuilder dateFriday) {
        this.dateFriday = dateFriday;
    }

    public StringBuilder getDateSaturday() {
        return dateSaturday;
    }

    public void setDateSaturday(StringBuilder dateSaturday) {
        this.dateSaturday = dateSaturday;
    }

    public StringBuilder getDateSunday() {
        return dateSunday;
    }

    public void setDateSunday(StringBuilder dateSunday) {
        this.dateSunday = dateSunday;
    }

    /**** Métodos Agregados ****/

    /**
     * completa semana
     */
    public void completeWeek(String firstDay, ArrayList<String> days) {

        int numDay = getNumbeDayWeek(firstDay);
        int cont_days = -1;

        for (int i = numDay - 1; i < aListDayDates.size(); i++) {
            aListDayDates.set(i, aListDayDates.get(i).replace(0, 1, days.get(++cont_days)));
        }
    }

    /**
     * @param firstDay
     * @return
     */
    private int getNumbeDayWeek(String firstDay) {

        int ret = -1;

        switch (firstDay) {

            case "MONDAY":
                ret = 1;
                break;
            case "TUESDAY":
                ret = 2;
                break;
            case "WEDNESDAY":
                ret = 3;
                break;
            case "THURSDAY":
                ret = 4;
                break;
            case "FRIDAY":
                ret = 5;
                break;
            case "SATURDAY":
                ret = 6;
                break;
            case "SUNDAY":
                ret = 7;
                break;
        }
        return ret;
    }
}
