package utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Creted by Michael
 */
public class RecordHistoryManager {

    private static String PATH_HISTORY = "data/history.dat";
    private static String PATH_REP = "rep/informeHist.dat";
    private static File fileHistory;
    private static File fileRep;
    private static StringBuilder sb;

    private static byte[] aRegByte;
    private static BufferedWriter bw;
    private static FileWriter fw;
    private static File file;

    private static ArrayList<HistoryTableRow> aHr;


    public static void validatCreateFileHistory() throws IOException {
        if (!isFileCreated())
            fileHistory.createNewFile();
    }

    /**
     * verifica si está creado el file
     * @return
     */
    public static boolean isFileCreated(){
        return fileHistory.exists();
    }

    public static void printReport() throws IOException {
        if (!aHr.isEmpty() && aHr != null){
            fileRep = new File(PATH_REP );
            FileWriter fileWriter = new FileWriter(fileRep);
            fileRep.createNewFile();
            try {
                fileWriter.write("Fecha Intercambio\tHora Intercambio\tMaster\tSesion\tAsignatura\tContenido\tDoc1\tDoc2\tFecha Origen\tFecha Destino\n");
                for (int i = 0; i < aHr.size(); i++) {
                    sb = new StringBuilder();
                    sb.append(aHr.get(i).getFechaInter())
                            .append("\t\t")
                            .append(aHr.get(i).getHoraInter())
                            .append("\t\t")
                            .append(aHr.get(i).getMasterID())
                            .append("\t\t")
                            .append(aHr.get(i).getSesionID())
                            .append("\t\t")
                            .append(aHr.get(i).getAsignatura())
                            .append("\t\t")
                            .append(aHr.get(i).getContenido())
                            .append("\t\t")
                            .append(aHr.get(i).getDoc1())
                            .append("\t\t")
                            .append(aHr.get(i).getDoc2())
                            .append("\t\t")
                            .append(aHr.get(i).getDateOrigen())
                            .append("\t\t")
                            .append(aHr.get(i).getDateDestino())
                            .append("\n");
                    fileWriter.write(sb.toString());
                }

                AlertHelper.showAlert(Alert.AlertType.INFORMATION, null, "Informe Generado",
                        "El informe ha sido generado correctamente");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fileWriter.close();
            }


        }
    }

    /**
     * write change
     * into file
     */
    public static void writeChange_file(RecordHistory recordHistory) throws IOException {
        fileHistory = new File(PATH_HISTORY);
        fw = new FileWriter(fileHistory.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        validatCreateFileHistory();
        try {
            sb = new StringBuilder();
            sb = recordHistory.getRowForRegistredFile();
            aRegByte = sb.toString().getBytes();

            for (int i = 0; i < aRegByte.length; i++) {
                bw.write(aRegByte[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
    }


    /**
     * write change
     * into file
     */
    public static void read_fileHist() throws IOException {
        sb = new StringBuilder();
        aRegByte = Files.readAllBytes(fileHistory.toPath());

        for (int i = 0; i < aRegByte.length; i++) {
            sb.append((char)aRegByte[i]);
        }
        System.out.println("READ FILE HISTORY : ");
        System.out.println(sb);
    }


    public static void fillTvHist(String masterID, TableView<HistoryTableRow> tv) throws IOException {
        aHr = new ArrayList<>();
        file = new File(PATH_HISTORY);
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine = "";
        if (!tv.getItems().isEmpty()) tv.getItems().clear();
        while ((readLine = b.readLine()) != null) {
           String[] a = readLine.split(" ");
            if (!a[2].equalsIgnoreCase(masterID)) continue;
            HistoryTableRow hr = new HistoryTableRow(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9]);
            aHr.add(hr);
        }
        tv.getItems().addAll(aHr);
    }
}
