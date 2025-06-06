package com.example.exception;

/**
 * Excepción personalizada que se lanza cuando se intenta usar la pista
 * pero esta se encuentra en estado OCUPADA.
 * Extiende RuntimeException para indicar un error en tiempo de ejecución.
 *
 * @author GITCarlosBarrera
 */
public class PistaOcupadaException extends RuntimeException {
    public PistaOcupadaException(String message) {
        super(message);
    }
}
