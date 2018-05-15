package utilities;

public class TextResponsive {
    private static String h1 = "26px";
    private static String h2 = "24px";
    private static String h3 = "20px";
    private static String h4 = "18px";
    private static String h5 = "16px";
    private static String h6 = "14px";

    public static String getFontStyle(String h) {
        switch (h) {
            case "h1":
                return "-fx-font-size: " + h1 + ";";
            case "h2":
                return "-fx-font-size: " + h2 + ";";
            case "h3":
                return "-fx-font-size: " + h3 + ";";
            case "h4":
                return "-fx-font-size: " + h4 + ";";
            case "h5":
                return "-fx-font-size: " + h5 + ";";
            case "h6":
                return "-fx-font-size: " + h6 + ";";
        }
        return "";
    }

    public static String getH1() {
        return h1;
    }

    public static void setH1(String h1) {
        TextResponsive.h1 = h1;
    }

    public static String getH2() {
        return h2;
    }

    public static void setH2(String h2) {
        TextResponsive.h2 = h2;
    }

    public static String getH3() {
        return h3;
    }

    public static void setH3(String h3) {
        TextResponsive.h3 = h3;
    }

    public static String getH4() {
        return h4;
    }

    public static void setH4(String h4) {
        TextResponsive.h4 = h4;
    }

    public static String getH5() {
        return h5;
    }

    public static void setH5(String h5) {
        TextResponsive.h5 = h5;
    }

    public static String getH6() {
        return h6;
    }

    public static void setH6(String h6) {
        TextResponsive.h6 = h6;
    }
}
