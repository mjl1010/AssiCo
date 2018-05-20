package utilities;

public class TextResponsive {
    private static int h1 = 26;
    private static int h2 = 24;
    private static int h3 = 20;
    private static int h4 = 18;
    private static int h5 = 16;
    private static int h6 = 14;

    public static String getFontStyle(String h) {
        switch (h) {
            case "h1":
                return "-fx-font-size: " + h1 + "px;";
            case "h2":
                return "-fx-font-size: " + h2 + "px;";
            case "h3":
                return "-fx-font-size: " + h3 + "px;";
            case "h4":
                return "-fx-font-size: " + h4 + "px;";
            case "h5":
                return "-fx-font-size: " + h5 + "px;";
            case "h6":
                return "-fx-font-size: " + h6 + "px;";
        }
        return "";
    }

    public static int getH1() {
        return h1;
    }

    public static void setH1(int h1) {
        TextResponsive.h1 = h1;
    }

    public static int getH2() {
        return h2;
    }

    public static void setH2(int h2) {
        TextResponsive.h2 = h2;
    }

    public static int getH3() {
        return h3;
    }

    public static void setH3(int h3) {
        TextResponsive.h3 = h3;
    }

    public static int getH4() {
        return h4;
    }

    public static void setH4(int h4) {
        TextResponsive.h4 = h4;
    }

    public static int getH5() {
        return h5;
    }

    public static void setH5(int h5) {
        TextResponsive.h5 = h5;
    }

    public static int getH6() {
        return h6;
    }

    public static void setH6(int h6) {
        TextResponsive.h6 = h6;
    }
}
