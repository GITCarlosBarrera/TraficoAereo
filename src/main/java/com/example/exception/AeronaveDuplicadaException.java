package com.example.exception;

/**
 * Excepción personalizada que indica que ya existe una aeronave
 * registrada con el mismo identificador, evitando duplicados en el sistema.
 * Extiende RuntimeException para manejar errores en tiempo de ejecución.
 *
 * @author GITCarlosBarrera
 */
public class AeronaveDuplicadaException extends RuntimeException {
    public AeronaveDuplicadaException(String message) {
        super(message);
    }
}
