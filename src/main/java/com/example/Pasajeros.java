package com.example;

public class Pasajeros extends Aeronave {
    public Pasajeros(String id, TipoAeronave tipo) {
        super(id, tipo, 3);
    }

    @Override
    public void solicitarAterrizaje() {
        System.out.println("Pasajeros " + getId() + " solicita aterrizaje.");
        TorreDeControl.getInstance().registrarAeronaveParaAterrizaje(this);
    }

    @Override
    public void solicitarDespegue() {
        System.out.println("Pasajeros " + getId() + " solicita despegue.");
        TorreDeControl.getInstance().registrarAeronaveParaDespegue(this);
    }
}
