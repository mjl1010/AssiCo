package utilities;

import entity.DiaPlanificado;
import entity.Sesion;

import static utilities.VariablesAndMethodsUtils.getDiaPlanificado;

/**
 * Created by Michael
 */
public class SesionsExchange {

    private static TabCalendarMaster tcm;
    private static DiaPlanificado dp_exch1;
    private static DiaPlanificado dp_exch2;
    private static Sesion ses_aux;

    /**
     * change method
     * @param tcm
     * @param date1
     * @param date2
     */
    public static void change(TabCalendarMaster tcm, String date1, String date2) {
        dp_exch1 = getDiaPlanificado(date1, tcm);
        dp_exch2 = getDiaPlanificado(date2, tcm);
        ses_aux = dp_exch1.getSesion();
        procederChange(dp_exch1, dp_exch2);
        dp_exch2.setSesion(ses_aux);
        tcm.updateCalendar();
    }

    /**
     * procede Change
     * @param dp1
     * @param dp2
     */
    private static void procederChange(DiaPlanificado dp1, DiaPlanificado dp2) {
        dp1.setSesion(dp2.getSesion());
    }
}
