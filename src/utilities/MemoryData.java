package utilities;

import model.Docente;
import model.Master;
import model.PlanificadorCalendarios;
import model.Universidad;

import java.util.ArrayList;

/**
 *  Created by Michael
 */
public class MemoryData {

    private static ArrayList<PlanificadorCalendarios> listPlanifCalend = new ArrayList<>();

    /**
     * set Planicador Calendario List
     * @param listPlanifCalend
     */
    public static void setListPlanifCalend(ArrayList<PlanificadorCalendarios> listPlanifCalend) {
        MemoryData.listPlanifCalend = listPlanifCalend;
    }

    /**
     * metodo de test
     */
    public void test_fillListPlanifCalend(){

        Universidad uni = new Universidad(1, "uoc");
        Master master1 = new Master(1, "M01", "master1");
        Master master2 = new Master(2, "M02", "master2");

        ArrayList<Docente> aDocentes = new ArrayList<>();

        for (int i = 0; i < 35; i++) {
            aDocentes.add(new Docente(i+1, "d" + (i+1), "pablo" + (i+1)));
        }

        




    }
}
