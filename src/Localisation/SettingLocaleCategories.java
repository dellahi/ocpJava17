package Localisation;

import java.text.NumberFormat;
import java.util.Locale;

public class SettingLocaleCategories {
    public static void main(String[] args) {
        double n = 77_000.11; 
        Locale locFrench = new Locale("fr", "FR");
        Locale locEnglishGB = new Locale("en", "GB");
        Locale locArabicMauritania = new Locale.Builder()
                .setRegion("MR")    // country first
                .setLanguage("ar")  // language second
                .build();

        
        Locale defaultLocale = Locale.getDefault();               // default locale is "en_IE" (English in Ireland)
        System.out.println(defaultLocale.getDisplayName());       // English (Ireland)
        System.out.println(defaultLocale.getDisplayLanguage());   // English
        System.out.println(defaultLocale.getDisplayCountry());    // Ireland
        System.out.println(NumberFormat.getInstance().format(n)); // 77,000.11 (en_IE locale)
        
        //Locale.setDefault(Locale.Category.DISPLAY, locArabicMauritania);
        Locale.setDefault(locEnglishGB);   // changes *both* DISPLAY and FORMAT.
        System.out.println("\nDISPLAY changed: " + defaultLocale.getDisplayName());     // anglais (Irlande)
        System.out.println("DISPLAY changed: " + defaultLocale.getDisplayLanguage());   // anglais
        System.out.println("DISPLAY changed: " + defaultLocale.getDisplayCountry());    // Irlande
        System.out.println("DISPLAY changed: " + NumberFormat.getInstance().format(n)); // 77,000.11 (en_IE locale)

        //Locale.setDefault(Locale.Category.FORMAT, locArabicMauritania);
        System.out.println("FORMAT changed: \t" + NumberFormat.getInstance().format(n)); // 77 000,11
    }
    
}
