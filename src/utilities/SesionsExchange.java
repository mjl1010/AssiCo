package utilities;

import entity.DiaPlanificado;
import entity.Sesion;
import utilities.TabCalendarMaster;

import static utilities.VariablesAndMethodsUtils.getDiaPlanificado;


public class SesionsExchange {

    private static TabCalendarMaster tcm;
    private static DiaPlanificado dp_exch1;
    private static DiaPlanificado dp_exch2;
    private static Sesion ses_aux;


    public static void change(TabCalendarMaster tcm, String date1, String date2) {
//        System.out.println("tcm : " + tcm);
//        System.out.println("date1 : " + date1);
//        System.out.println("date2 : " + date2);
        dp_exch1 = getDiaPlanificado(date1, tcm);
//        System.out.println("dp_exch1 : " + dp_exch1);
        dp_exch2 = getDiaPlanificado(date2, tcm);
//        System.out.println("dp_exch2 : " + dp_exch2);
        ses_aux = dp_exch1.getSesion();
        procederChange(dp_exch1, dp_exch2);
        dp_exch2.setSesion(ses_aux);
        tcm.updateCalendar();
    }


    private static void procederChange(DiaPlanificado dp1, DiaPlanificado dp2) {
        dp1.setSesion(dp2.getSesion());
    }
}
