package com.example.model;

import com.example.util.Logger;
import com.example.controller.TorreDeControl;

/**
 * Clase que representa una aeronave de pasajeros.
 * Tiene prioridad baja (3) para aterrizajes y despegues.
 * Hereda de la clase abstracta Aeronave e implementa su comportamiento.
 *
 * @author GITCarlosBarrera
 */
public class Pasajeros extends Aeronave {

    /**
     * Crea una aeronave de pasajeros con prioridad 3.
     *
     * @param id Identificador de la aeronave
     * @param tipo Tipo de aeronave (definido en enum)
     */
    public Pasajeros(String id, TipoAeronave tipo) {
        super(id, tipo, 3);
    }

    /**
     * Solicita el aterrizaje de una aeronave de pasajeros,
     * registrándola en la torre de control y mostrando los mensajes.
     */
    @Override
    public void solicitarAterrizaje() {
        Logger.log("Pasajeros " + getId() + " solicita aterrizaje.");
        System.out.println("Pasajeros " + getId() + " solicita aterrizaje.");
        TorreDeControl.getInstance().registrarAeronaveParaAterrizaje(this);
    }

    /**
     * Solicita el despegue de una aeronave de pasajeros,
     * registrándola en la torre de control y mostrando los mensajes.
     */
    @Override
    public void solicitarDespegue() {
        Logger.log("Pasajeros " + getId() + " solicita despegue.");
        System.out.println("Pasajeros " + getId() + " solicita despegue.");
        TorreDeControl.getInstance().registrarAeronaveParaDespegue(this);
    }
}
