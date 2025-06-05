package com.example;

public class Carga extends Aeronave {
    public Carga(String id, TipoAeronave tipo) {
        super(id, tipo, 4);
    }

    @Override
    public void solicitarAterrizaje() {
        System.out.println("Carga " + getId() + " solicita aterrizaje.");
        TorreDeControl.getInstance().registrarAeronaveParaAterrizaje(this);
    }

    @Override
    public void solicitarDespegue() {
        System.out.println("Carga " + getId() + " solicita despegue.");
        TorreDeControl.getInstance().registrarAeronaveParaDespegue(this);
    }
}
