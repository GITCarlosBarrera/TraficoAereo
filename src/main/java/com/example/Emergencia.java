package com.example;

public class Emergencia extends Aeronave {
    public Emergencia(String id, TipoAeronave tipo) {
        super(id, tipo, 1);
    }

    @Override
    public void solicitarAterrizaje() {
        System.out.println("Emergencia " + getId() + " solicita aterrizaje.");
        TorreDeControl.getInstance().registrarAeronaveParaAterrizaje(this);
    }

    @Override
    public void solicitarDespegue() {
        System.out.println("Emergencia " + getId() + " solicita despegue.");
        TorreDeControl.getInstance().registrarAeronaveParaDespegue(this);
    }
}
