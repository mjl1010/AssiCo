package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

/**
 * Created by J Michael on 20/04/2018.
 */
public class WeekDates {

    private final StringProperty dateMonday;
    private StringProperty dateTuesday;
    private StringProperty dateWednesday;
    private StringProperty dateThursDay;
    private StringProperty dateFriday;
    private StringProperty dateSaturday;
    private StringProperty dateSunday;
    private ArrayList<StringProperty> aListDayDates;

    /**
     * Método Constructor
     * por default
     */
    public WeekDates() {
        dateMonday = new SimpleStringProperty();
        dateTuesday = new SimpleStringProperty();
        dateWednesday = new SimpleStringProperty();
        dateThursDay = new SimpleStringProperty();
        dateFriday = new SimpleStringProperty();
        dateSaturday = new SimpleStringProperty();
        dateSunday = new SimpleStringProperty();
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
    public WeekDates(String dateMonday, String dateTuesday, String dateWednesday,
                     String dateThursDay, String dateFriday, String dateSaturday,
                     String dateSunday) {

        this.dateMonday = new SimpleStringProperty(dateMonday);
        this.dateTuesday = new SimpleStringProperty(dateTuesday);;
        this.dateWednesday = new SimpleStringProperty(dateWednesday);;
        this.dateThursDay = new SimpleStringProperty(dateThursDay);;
        this.dateFriday = new SimpleStringProperty(dateFriday);;
        this.dateSaturday = new SimpleStringProperty(dateSaturday);;
        this.dateSunday = new SimpleStringProperty(dateSunday);;
    }

    private void addList() {
        aListDayDates = new ArrayList<StringProperty>();
        aListDayDates.add(dateMonday);
        aListDayDates.add(dateTuesday);
        aListDayDates.add(dateWednesday);
        aListDayDates.add(dateThursDay);
        aListDayDates.add(dateFriday);
        aListDayDates.add(dateSaturday);
        aListDayDates.add(dateSunday);
    }

    /*** Getters and Setters ***/

    public String getDateMonday() {
        return dateMonday.toString();
    }
    public void setDateMonday(String dateMonday) {
        this.dateMonday.set(dateMonday);
    }
    public StringProperty mondayProperty() {
        return dateMonday;
    }

   public String getDateTuesday() {
        return dateTuesday.get();
    }
    public void setDateTuesday(String dateTuesday) {
        this.dateTuesday.set(dateTuesday);
    }
    public StringProperty tuesdayProperty() {
        return dateTuesday;
    }

    public String getDateWednesday() {
        return dateWednesday.get();
    }
    public void setDateWednesday(String dateWednesday) {
        this.dateWednesday.set(dateWednesday);
    }
    public StringProperty wednesdayProperty() {
        return dateWednesday;
    }

    public String getDateThursDay() {
        return dateThursDay.get();
    }
    public void setDateThursDay(String dateThursDay) {
        this.dateThursDay.set(dateThursDay);
    }
    public StringProperty thursDayProperty() {
        return dateThursDay;
    }

    public String getDateFriday() {
        return dateFriday.get();
    }
    public void setDateFriday(String dateFriday) {
        this.dateFriday.set(dateFriday);
    }
    public StringProperty fridayProperty() {
        return dateFriday;
    }

    public String getDateSaturday() {
        return dateSaturday.get();
    }
    public void setDateSaturday(String dateSaturday) {
        this.dateSaturday.set(dateSaturday);
    }
    public StringProperty saturdayProperty() {
        return dateSaturday;
    }

    public String getDateSunday() {
        return dateSunday.get();
    }
    public void setDateSunday(String dateSunday) {
        this.dateSunday.set(dateSunday);
    }
    public StringProperty sundayProperty() {
        return dateSunday;
    }

    /**** Métodos Agregados ****/

    /**
     * completa semana
     */
    public void completeWeek(String firstDay, ArrayList<String> days) {

        int numDay = getNumbeDayWeek(firstDay);
        int cont_days = -1;

        for (int i = numDay - 1; i < aListDayDates.size(); i++) {
//            aListDayDates.set(i, aListDayDates.get(i).toString().replace(0, 1, days.get(++cont_days)));

            aListDayDates.get(i).set(days.get(++cont_days));
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
