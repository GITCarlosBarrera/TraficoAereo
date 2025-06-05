package com.example;

public abstract class Aeronave {
    private final String id;
    private final TipoAeronave tipo;
    private final int prioridad;

    public Aeronave(String id, TipoAeronave tipo, int prioridad) {
        this.id = id;
        this.tipo = tipo;
        this.prioridad = prioridad;
    }

    public String getId() { return id; }
    public String getStringTipo() { return tipo.toString(); }
    public int getPrioridad() { return prioridad; }
    public abstract void solicitarAterrizaje();
    public abstract void solicitarDespegue();

    @Override
    public String toString() {
        return "ID: " + id + " | " + tipo + " | Prioridad: " + prioridad;
    }
}
