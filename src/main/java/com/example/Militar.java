package com.example;

public class Militar extends Aeronave{
    public Militar(String id, TipoAeronave tipo) {
        super(id, tipo, 2);
    }

    @Override
    public void solicitarAterrizaje() {
        System.out.println("Militar " + getId() + " solicita aterrizaje.");
        TorreDeControl.getInstance().registrarAeronaveParaAterrizaje(this);
    }

    @Override
    public void solicitarDespegue() {
        System.out.println("Militar " + getId() + " solicita despegue.");
        TorreDeControl.getInstance().registrarAeronaveParaDespegue(this);
    }
}
