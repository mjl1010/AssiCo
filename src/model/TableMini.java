package model;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 */
public class TableMini {

    private TableColumn tc_session;
    private TableColumn tc_date;
    private TableColumn tc_aux1;
    private TableColumn tc_aux2;
    private String session;
    private String date;
    private String aux1;
    private String aux2;
    private TableView tv_mini;

    /**
     * Método constructor
     * @param session
     * @param date
     * @param aux1
     * @param aux2
     */
    public TableMini(String session, String date, String aux1, String aux2) {
        this.session = session;
        this.date = date;
        this.aux1 = aux1;
        this.aux2 = aux2;
        createTable();
    }

    /*** Getters and Setters ***/

    public TableView getTv_mini() {
        return tv_mini;
    }

    public void setTv_mini(TableView tv_mini) {
        this.tv_mini = tv_mini;
    }

    public TableColumn getTc_session() {
        return tc_session;
    }

    public void setTc_session(TableColumn tc_session) {
        this.tc_session = tc_session;
    }

    public TableColumn getTc_date() {
        return tc_date;
    }

    public void setTc_date(TableColumn tc_date) {
        this.tc_date = tc_date;
    }

    public TableColumn getTc_aux1() {
        return tc_aux1;
    }

    public void setTc_aux1(TableColumn tc_aux1) {
        this.tc_aux1 = tc_aux1;
    }

    public TableColumn getTc_aux2() {
        return tc_aux2;
    }

    public void setTc_aux2(TableColumn tc_aux2) {
        this.tc_aux2 = tc_aux2;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAux1() {
        return aux1;
    }

    public void setAux1(String aux1) {
        this.aux1 = aux1;
    }

    public String getAux2() {
        return aux2;
    }

    public void setAux2(String aux2) {
        this.aux2 = aux2;
    }

    /**** Métodos Agregados *****/

    /**
     * create table mini
     */
    private void createTable() {
        tv_mini = new TableView();
        tv_mini.setEditable(true);

        tc_date = new TableColumn(date);
        tc_date.setPrefWidth(30);

        tc_session = new TableColumn(session);
        tc_session.setPrefWidth(60);

        tc_aux1 = new TableColumn(aux1);
        tc_aux1.setPrefWidth(30);

        tc_aux2 = new TableColumn(aux2);
        tc_aux2.setPrefWidth(30);

        tc_date.getColumns().add(tc_aux1);
        tc_aux1.getColumns().add(tc_aux2);

        tv_mini.getColumns().addAll(tc_date, tc_session);

    }
}
