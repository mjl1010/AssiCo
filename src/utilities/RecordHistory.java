package utilities;

import entity.Master;
import entity.Sesion;
import sun.plugin2.util.SystemUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Michael
 */
public class RecordHistory {

    private LocalDateTime ldt_changeDate;
    private Master master;
    private Sesion sesion;
    private String originalDate;
    private String destinationDate;

    private static String SALTO_LINEA = "\n";
    private static String SPACE = " ";
    private static String FORMATTER_TIME = "HH:mm:ss";

    /**
     * Método Cosntructor
     * @param ldt_changeDate
     * @param master
     * @param sesion
     * @param originalDate
     * @param destinationDate
     */
    public RecordHistory(LocalDateTime ldt_changeDate, entity.Master master, Sesion sesion,
                         String originalDate, String destinationDate) {
        this.ldt_changeDate = ldt_changeDate;
        this.master = master;
        this.sesion = sesion;
        this.originalDate = originalDate;
        this.destinationDate = destinationDate;
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

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public String getOriginalDate() {
        return originalDate;
    }

    public void setOriginalDate(String originalDate) {
        this.originalDate = originalDate;
    }

    public String getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(String destinationDate) {
        this.destinationDate = destinationDate;
    }

    /**
     *
     * @return
     */
    public StringBuilder getRowForRegistredFile() {
//        System.out.println("ENTRO AQUI");
        StringBuilder sb = new StringBuilder();
        String doc1_aux = "", doc2_aux = "";
//        System.out.println("sesion : " + sesion);
//        System.out.println(sesion.getDocente1() == null);
//        System.out.println(sesion.getDocente2() == null);

        if (sesion != null){
            if (sesion.getDocente1() != null) doc1_aux = sesion.getDocente1().getCode();
            if (sesion.getDocente2() != null) doc1_aux = sesion.getDocente2().getCode();
            sb.append(ldt_changeDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
                    .append(SPACE)
                    .append(ldt_changeDate.format(DateTimeFormatter.ofPattern(FORMATTER_TIME)))
                    .append(SPACE)
                    .append(master.getCode())
                    .append(SPACE)
                    .append(sesion.getId())
                    .append(SPACE)
                    .append(sesion.getAsignatura())
                    .append(SPACE)
                    .append(sesion.getContenidos())
                    .append(SPACE)
                    .append(doc1_aux)
                    .append(SPACE)
                    .append(doc2_aux)
                    .append(SPACE)
                    .append(originalDate)
                    .append(SPACE)
                    .append(destinationDate)
                    .append(SALTO_LINEA);
        }
        return sb;
    }
}
