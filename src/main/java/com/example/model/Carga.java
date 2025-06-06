package com.example.model;

import com.example.util.Logger;
import com.example.controller.TorreDeControl;

/**
 * Clase que representa una aeronave de carga.
 * Tiene la prioridad más baja (4) para aterrizajes y despegues.
 * Hereda de la clase abstracta Aeronave e implementa su comportamiento.
 *
 * @author GITCarlosBarrera
 */
public class Carga extends Aeronave {

    /**
     * Crea una aeronave de carga con prioridad 4.
     *
     * @param id Identificador de la aeronave
     * @param tipo Tipo de aeronave (definido en enum)
     */
    public Carga(String id, TipoAeronave tipo) {
        super(id, tipo, 4);
    }

    /**
     * Solicita el aterrizaje de una aeronave de carga,
     * registrándola en la torre de control y mostrando los mensajes.
     */
    @Override
    public void solicitarAterrizaje() {
        Logger.log("Carga " + getId() + " solicita aterrizaje.");
        System.out.println("Carga " + getId() + " solicita aterrizaje.");
        TorreDeControl.getInstance().registrarAeronaveParaAterrizaje(this);
    }

    /**
     * Solicita el despegue de una aeronave de carga,
     * registrándola en la torre de control y mostrando los mensajes.
     */
    @Override
    public void solicitarDespegue() {
        Logger.log("Carga " + getId() + " solicita despegue.");
        System.out.println("Carga " + getId() + " solicita despegue.");
        TorreDeControl.getInstance().registrarAeronaveParaDespegue(this);
    }
}
