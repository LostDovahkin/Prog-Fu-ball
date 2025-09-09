package Gamelogic;

import java.io.*;

public class SaveLoadUtil {
    public static void saveGame(SaveGame save, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(save);
            System.out.println("Spiel erfolgreich gespeichert!");
        } catch (Exception e) {
            System.out.println("Fehler beim Speichern!");
            e.printStackTrace();
        }
    }

    public static SaveGame loadGame(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (SaveGame) in.readObject();
        } catch (Exception e) {
            // Optional: Fehlerausgabe für Debugging
            // e.printStackTrace();
            return null;
        }
    }
}