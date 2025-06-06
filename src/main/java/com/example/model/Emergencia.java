package com.example.model;

import com.example.util.Logger;
import com.example.controller.TorreDeControl;

/**
 * Clase que representa una aeronave de tipo emergencia.
 * Tiene prioridad máxima (1) para aterrizajes y despegues.
 * Hereda de la clase abstracta Aeronave e implementa su comportamiento.
 *
 * @author GITCarlosBarrera
 */
public class Emergencia extends Aeronave {

    /**
     * Crea una aeronave de emergencia con prioridad 1.
     *
     * @param id Identificador de la aeronave
     * @param tipo Tipo de aeronave (definido en enum)
     */
    public Emergencia(String id, TipoAeronave tipo) {
        super(id, tipo, 1);
    }

    /**
     * Solicita el aterrizaje de una aeronave de emergencia,
     * registrándola en la torre de control y mostrando los mensajes.
     */
    @Override
    public void solicitarAterrizaje() {
        Logger.log("Emergencia " + getId() + " solicita aterrizaje.");
        System.out.println("Emergencia " + getId() + " solicita aterrizaje.");
        TorreDeControl.getInstance().registrarAeronaveParaAterrizaje(this);
    }

    /**
     * Solicita el despegue de una aeronave de emergencia,
     * registrándola en la torre de control y mostrando los mensajes.
     */
    @Override
    public void solicitarDespegue() {
        Logger.log("Emergencia " + getId() + " solicita despegue.");
        System.out.println("Emergencia " + getId() + " solicita despegue.");
        TorreDeControl.getInstance().registrarAeronaveParaDespegue(this);
    }
}
