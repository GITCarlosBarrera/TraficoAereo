package com.example.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Clase utilitaria para registrar logs de eventos con timestamp.
 * Escribe los mensajes en un fichero "logs.txt".
 *
 * @author GITCarlosBarrera
 */
public class Logger {

    /**
     * Escribe un mensaje en el archivo de logs con la fecha y hora actual.
     *
     * @param mensaje Mensaje que se desea registrar.
     */
    public static void log(String mensaje) {
        try (FileWriter fw = new FileWriter("logs.txt")) {
            fw.write(LocalDateTime.now() + " - " + mensaje + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
