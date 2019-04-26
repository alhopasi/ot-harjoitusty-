package swduel.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokassa on apumetodeita tiedon käsittelyyn.
 */
public class Utils {
    
    /**
     * Metodi lukee tiedoston ja palauttaa sen sisällön listana merkkijonona.
     * @param file Luettava tiedosto
     * @return Palauttaa listan merkkijonona.
     */
    public static List<String> readFile(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }
}
