package controller;

import entity.DiaPlanificado;
import model.GridSesion;
import model.TabCalendarMaster;

import static utilities.VariablesAndMethodsUtils.getDiaPlanificado;
import static utilities.VariablesAndMethodsUtils.printSesionReg_TEST;


public class SesionsExchange {

    private static TabCalendarMaster tcm;
    private static DiaPlanificado dp_exch1;
    private static DiaPlanificado dp_exch2;
    private static DiaPlanificado dp_aux;


    public static void change(TabCalendarMaster tcm, String date1, String date2){
        printSesionReg_TEST();
        System.out.println("**date1 : " + date1 + "- date2 : " + date2);
        dp_exch1 = getDiaPlanificado(date1, tcm);
        dp_exch2 = getDiaPlanificado(date2, tcm);
        dp_aux = dp_exch1;
        procederChange(dp_exch1, dp_exch2);
        procederChange(dp_exch2, dp_aux);
        tcm.updateCalendar();
        printSesionReg_TEST();
    }

    private static void procederChange(DiaPlanificado dp1,
                                       DiaPlanificado dp2) {
        System.out.println("dp1 : " + dp1);
        System.out.println("dp2 : " + dp2);
        System.out.println("inicio de cambio : " + dp1.getSesion());
        dp1.setSesion(dp2.getSesion());
        System.out.println("fin de cambio : " + dp1.getSesion());
    }
}
