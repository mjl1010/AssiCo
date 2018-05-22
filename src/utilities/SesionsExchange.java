package utilities;

import entity.DiaPlanificado;
import entity.Sesion;

import static utilities.VariablesAndMethodsUtils.getDiaPlanificado;
import static utilities.VariablesAndMethodsUtils.isSesionComun;

/**
 * Created by Michael
 */
public class SesionsExchange {

    private static TabCalendarMaster tcm_current;
    private static DiaPlanificado dp_exch1;
    private static DiaPlanificado dp_exch2;
    private static Sesion ses_aux;

    private static DiaPlanificado dp_exch1_mv;
    private static DiaPlanificado dp_exch2_mv;

    /**
     * change method
     * @param tcm
     * @param date1
     * @param date2
     */
    public static void change(TabCalendarMaster tcm, String date1, String date2) {
        tcm_current = tcm;
        dp_exch1 = getDiaPlanificado(date1, tcm_current);
        dp_exch2 = getDiaPlanificado(date2, tcm_current);
        ses_aux = dp_exch1.getSesion();
        procederChange(dp_exch1, dp_exch2);
        dp_exch2.setSesion(ses_aux);
        tcm_current.updateCalendar();
        validarChangeInMasterVinculado(date1, date2);
    }

    /**
     * validarChangeInMasterVinculado()
     * @param date1
     * @param date2
     */
    private static void validarChangeInMasterVinculado(String date1, String date2) {
        if ((isSesionComun(dp_exch1.getSesion()) || isSesionComun(dp_exch2.getSesion())) ||
                tcm_current.getIv_seguro().getImage().impl_getUrl().endsWith("close.png")){
            dp_exch1_mv = getDiaPlanificado(date1, tcm_current.getTcm_vinculado());
            dp_exch2_mv = getDiaPlanificado(date2, tcm_current.getTcm_vinculado());
            ses_aux = dp_exch1_mv.getSesion();
            procederChange(dp_exch1_mv, dp_exch2_mv);
            dp_exch2_mv.setSesion(ses_aux);
            tcm_current.getTcm_vinculado().updateCalendar();
        }
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
