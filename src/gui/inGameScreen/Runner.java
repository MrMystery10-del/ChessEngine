package gui.inGameScreen;

import java.util.Locale;
import java.util.ResourceBundle;

public class Runner {

    public static void main(String[] args) {

        //Locale locale_en_EN = new Locale("en", "EN");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.translations.bundle", Locale.getDefault());
        System.out.println(resourceBundle.getString("load"));
        System.out.println(Locale.getDefault());


    }
}