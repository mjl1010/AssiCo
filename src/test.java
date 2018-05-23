import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {

    public static void main(String[] args) {

//        String f = "menuOpt1_1";
//
//        System.out.println(f.toLowerCase()
//                .indexOf("men"));

        LocalDateTime ldt = LocalDateTime.now();

        System.out.println(ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
