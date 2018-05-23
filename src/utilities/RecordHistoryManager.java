package utilities;

import java.io.*;
import java.nio.file.Files;

/**
 * Creted by Michael
 */
public class RecordHistoryManager {

    private static String PATH_HISTORY = "data/history.dat";
    private static File fileHistory;
    private static StringBuilder sb;

    private static byte[] aRegByte;
    private static BufferedWriter bw;
    private static FileWriter fw;

    private static FileInputStream fis;

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


}
