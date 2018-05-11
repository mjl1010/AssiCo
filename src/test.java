import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class test {

    public static void main(String[] args) {

        String f = "20181005";

        LocalDate date = LocalDate.parse(f, DateTimeFormatter.BASIC_ISO_DATE);

        System.out.println(date.getYear() + "*" + date.getMonth().name());

    }
}
