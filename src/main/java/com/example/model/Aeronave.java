package com.example.model;

/**
 * Clase abstracta que representa una aeronave.
 * Define atributos comunes como ID, tipo y prioridad.
 * Contiene métodos abstractos que deben implementar las subclases
 * para solicitar aterrizaje y despegue.
 *
 * @author GITCarlosBarrera
 */
public abstract class Aeronave {

    private final String id;
    private final TipoAeronave tipo;
    private final int prioridad;

    /**
     * Crea una nueva aeronave con sus valores básicos asignados.
     *
     * @param id Identificador de la aeronave
     * @param tipo Tipo de aeronave (definido en enum)
     * @param prioridad Nivel de prioridad (1 = mayor prioridad)
     */
    public Aeronave(String id, TipoAeronave tipo, int prioridad) {
        this.id = id;
        this.tipo = tipo;
        this.prioridad = prioridad;
    }

    public String getId() {
        return id;
    }

    public String getStringTipo() {
        return tipo.toString();
    }

    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Define el comportamiento para solicitar aterrizaje.
     * Lo implementan las clases hijas.
     */
    public abstract void solicitarAterrizaje();

    /**
     * Define el comportamiento para solicitar despegue.
     * Lo implementan las clases hijas.
     */
    public abstract void solicitarDespegue();

    /**
     * Devuelve la información básica de la aeronave.
     *
     * @return ID, tipo y prioridad en formato texto
     */
    @Override
    public String toString() {
        return "ID: " + id + " | " + tipo + " | Prioridad: " + prioridad;
    }
}
