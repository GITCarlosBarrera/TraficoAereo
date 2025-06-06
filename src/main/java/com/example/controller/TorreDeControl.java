package com.example.controller;

import com.example.exception.FalloOperacionException;
import com.example.exception.PistaOcupadaException;
import com.example.model.Aeronave;
import com.example.model.EstadoPista;
import com.example.util.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Clase que controla las acciones principales del simulador.
 * Define atributos y comportamientos comunes como las colas de aterrizaje y despegue
 * y el estado de la pista.
 *
 * @author GITCarlosBarrera
 */
public class TorreDeControl {

    private static final TorreDeControl instancia = new TorreDeControl();
    private final List<Aeronave> colaAterrizajes;
    private final List<Aeronave> colaDespegues;
    private EstadoPista estado;

    /**
     * Crea un objeto TorreDeControl por defecto inicializando las listas
     * y asignando el estado de pista "LIBRE".
     */
    public TorreDeControl() {
        this.colaAterrizajes = new ArrayList<>();
        this.colaDespegues = new ArrayList<>();
        this.estado = EstadoPista.LIBRE;
    }

    public static TorreDeControl getInstance() {
        return instancia;
    }

    public List<Aeronave> getColaAterrizajes() {
        return colaAterrizajes;
    }

    public List<Aeronave> getColaDespegues() {
        return colaDespegues;
    }

    public EstadoPista getEstadoPista() {
        return estado;
    }

    /**
     * Añade la aeronave a la cola de aterrizaje.
     *
     * @param a Aeronave que desea agregar.
     */
    public void registrarAeronaveParaAterrizaje(Aeronave a) {
        colaAterrizajes.add(a);
        ordenarCola(colaAterrizajes);
        Logger.log(a.getStringTipo() + a.getId() + " se registró para la cola de aterrizaje");
    }

    /**
     * Añade la aeronave a la cola de despegue.
     *
     * @param a Aeronave que desea agregar
     */
    public void registrarAeronaveParaDespegue(Aeronave a) {
        colaDespegues.add(a);
        ordenarCola(colaDespegues);
        Logger.log(a.getStringTipo() + a.getId() + " se registró para la cola de despegue");
    }

    /**
     * Procesa el aterrizaje o despegue según el orden de prioridad de las aeronaves
     * y el orden definido.
     *
     * Contiene un sistema de fallos aleatorio en el cual hay un 15% de posibilidades
     * de que la aeronave falle al aterrizar o despegar.
     */
    public void procesarSiguienteEvento() {
        Aeronave a;
        Random random = new Random();

        sleep(2000);

        if (random.nextInt(100) < 5) {
            estado = EstadoPista.OCUPADA;
        }
        try {
            if (!colaAterrizajes.isEmpty() && estado.equals(EstadoPista.LIBRE)) {
                a = colaAterrizajes.getFirst();
                try {
                    // ⚠ SISTEMA DE FALLO ALEATORIO ⚠
                    if (random.nextInt(100) < 15) {
                        throw new FalloOperacionException("⚠ FALLO: " + a.getStringTipo() + "|" + a.getId() + " no ha podido aterrizar.");
                    } else {
                        Logger.log(a.getStringTipo() + "|" + a.getId() + " aterrizando en pista...");
                        System.out.println(a.getStringTipo() + "|" + a.getId() + " aterrizando en pista...");

                        colaAterrizajes.remove(a);
                        sleep(1500);
                        estado = EstadoPista.OCUPADA;

                        Logger.log("=== Pista ocupada por " + a.getStringTipo() + "|" + a.getId() + " ===\n" +
                                a.getStringTipo() + "|" + a.getId() + " ha aterrizado con éxito.");
                        System.out.println("=== Pista ocupada por " + a.getStringTipo() + "|" + a.getId() + " ===\n" +
                                a.getStringTipo() + "|" + a.getId() + " ha aterrizado con éxito.");

                        sleep(1000);
                        Logger.log("Se procede con la liberación de la pista...");
                        System.out.println("Se procede con la liberación de la pista...");

                        sleep(3000);
                        liberarPista();

                        Logger.log("=== Pista libre ===");
                        System.out.println("=== Pista libre ===");
                    }
                } catch (FalloOperacionException e) {
                    Logger.log(e.getMessage() + "\nIntentará el aterrizaje nuevamente en breve.");
                    System.err.println(e.getMessage() + "\nIntentará el aterrizaje nuevamente en breve.");
                    sleep(3000);
                }
            } else if (!colaDespegues.isEmpty() && estado.equals(EstadoPista.LIBRE)){
                a = colaDespegues.getFirst();

                try {
                    // ⚠ SISTEMA DE FALLO ALEATORIO ⚠
                    if (random.nextInt(100) < 15) {
                        throw new FalloOperacionException("⚠ FALLO: " + a.getStringTipo() + "|" + a.getId() + " no ha podido despegar.");
                    } else {
                        Logger.log(a.getId() + " entrando en pista...");
                        System.out.println(a.getId() + " entrando en pista...");

                        sleep(3000);
                        estado = EstadoPista.OCUPADA;

                        Logger.log("=== Pista ocupada por " + a.getStringTipo() + "|" + a.getId() + " ===\n" +
                                a.getStringTipo() + "|" + a.getId() + " procede con el despegue.");
                        System.out.println("=== Pista ocupada por " + a.getStringTipo() + "|" + a.getId() + " ===\n" +
                                a.getStringTipo() + "|" + a.getId() + " procede con el despegue.");

                        sleep(2000);
                        liberarPista();

                        colaDespegues.remove(a);

                        Logger.log(a.getId() + " ha despegado con éxito.");
                        System.out.println(a.getId() + " ha despegado con éxito.");

                        sleep(500);
                        Logger.log("=== Pista libre ===");
                        System.out.println("=== Pista libre ===");
                    }
                } catch (FalloOperacionException e) {
                    Logger.log(e.getMessage() + "\nIntentará el despegue nuevamente en breve.");
                    System.err.println(e.getMessage() + "\nIntentará el despegue nuevamente en breve.");
                    sleep(3000);
                }
            } else {
                throw new PistaOcupadaException("No se puede registrar aterrizaje: pista ocupada.");
            }
        } catch (PistaOcupadaException e) {
            Logger.log(e.getMessage() + "\nSe procede a la liberación de la pista.");
            System.err.println(e.getMessage() + "\nSe procede a la liberación de la pista.");
            estado = EstadoPista.LIBRE;
        }
    }

    /**
     * Modifica el estado de la pista a "LIBRE".
     */
    private void liberarPista() {
        estado = EstadoPista.LIBRE;
    }

    /**
     * Ordena la lista en base al orden de prioridad poniendo la prioridad 1 al principio.
     *
     * @param lista Lista de aeronaves
     */
    private static void ordenarCola(List<Aeronave> lista) {
        lista.sort(Comparator.comparingInt(Aeronave::getPrioridad));
    }

    /**
     * Provoca una pequeña pausa al momento de imprimir por consola.
     *
     * @param milis Milisegundos que pausará.
     */
    private static void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
