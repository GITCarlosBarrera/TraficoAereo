package com.example.exception;

/**
 * Excepción personalizada que indica que una operación
 * (aterrizaje o despegue) ha fallado debido a un error imprevisto.
 * Extiende RuntimeException para señalar un fallo en tiempo de ejecución.
 *
 * @author GITCarlosBarrera
 */
public class FalloOperacionException extends RuntimeException {
    public FalloOperacionException(String message) {
        super(message);
    }
}
