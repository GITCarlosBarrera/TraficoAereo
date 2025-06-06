package com.example.model;

import com.example.util.Logger;
import com.example.controller.TorreDeControl;

/**
 * Clase que representa una aeronave de tipo militar.
 * Tiene prioridad media (2) para aterrizajes y despegues.
 * Hereda de la clase abstracta Aeronave e implementa su comportamiento.
 *
 * @author GITCarlosBarrera
 */
public class Militar extends Aeronave {

    /**
     * Crea una aeronave militar con prioridad 2.
     *
     * @param id Identificador de la aeronave
     * @param tipo Tipo de aeronave (definido en enum)
     */
    public Militar(String id, TipoAeronave tipo) {
        super(id, tipo, 2);
    }

    /**
     * Solicita el aterrizaje de una aeronave militar,
     * registrándola en la torre de control y mostrando los mensajes.
     */
    @Override
    public void solicitarAterrizaje() {
        Logger.log("Militar " + getId() + " solicita aterrizaje.");
        System.out.println("Militar " + getId() + " solicita aterrizaje.");
        TorreDeControl.getInstance().registrarAeronaveParaAterrizaje(this);
    }

    /**
     * Solicita el despegue de una aeronave militar,
     * registrándola en la torre de control y mostrando los mensajes.
     */
    @Override
    public void solicitarDespegue() {
        Logger.log("Militar " + getId() + " solicita despegue.");
        System.out.println("Militar " + getId() + " solicita despegue.");
        TorreDeControl.getInstance().registrarAeronaveParaDespegue(this);
    }
}
