package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TorreDeControl {
    private static final TorreDeControl instancia = new TorreDeControl();
    private final List<Aeronave> colaAterrizajes;
    private final List<Aeronave> colaDespegues;
    private EstadoPista estado;

    public TorreDeControl() {
        this.colaAterrizajes = new ArrayList<>();
        this.colaDespegues = new ArrayList<>();
        this.estado = EstadoPista.LIBRE;
    }

    public static TorreDeControl getInstance() { return instancia; }

    public List<Aeronave> getColaAterrizajes() {
        return colaAterrizajes;
    }

    public List<Aeronave> getColaDespegues() {
        return colaDespegues;
    }

    public EstadoPista getEstadoPista() { return estado; }

    public void registrarAeronaveParaAterrizaje(Aeronave a) {
        colaAterrizajes.add(a);
        ordenarCola(colaAterrizajes);
    }

    public void registrarAeronaveParaDespegue(Aeronave a) {
        colaDespegues.add(a);
        ordenarCola(colaDespegues);
    }

    public void procesarSiguienteEvento() {
        Aeronave a;
        if (!colaAterrizajes.isEmpty()) {
            a = colaAterrizajes.getFirst();
            System.out.println(a.getStringTipo() + "|" + a.getId() + " aterrizando en pista...");
            colaAterrizajes.remove(a);
            sleep(1500);
            estado = EstadoPista.OCUPADA;
            System.out.println("=== Pista ocupada por " + a.getStringTipo() + "|" + a.getId() + " ===\n" + a.getStringTipo() + "|" + a.getId() + " ha aterrizado con exito.");
            sleep(1000);
            System.out.println("Se procede con la liberacion de la pista...");
            sleep(3000);
            liberarPista();
            System.out.println("=== Pista libre ===");
        } else {
            a = colaDespegues.getFirst();
            System.out.println(a.getId() + " entrando en pista...");
            sleep(3000);
            estado = EstadoPista.OCUPADA;
            System.out.println("=== Pista ocupada por " + a.getStringTipo() + "|" + a.getId() + " ===\n" + a.getStringTipo() + "|" + a.getId() + " procede con el despegue.");
            sleep(2000);
            liberarPista();
            colaDespegues.remove(a);
            System.out.println(a.getId() + " ha despegado con exito.");
            sleep(500);
            System.out.println("=== Pista libre ===");
        }
    }

    public void liberarPista() {
        estado = EstadoPista.LIBRE;
    }

    public static void ordenarCola(List<Aeronave> lista) {
        lista.sort(Comparator.comparingInt(Aeronave::getPrioridad));
    }

    public static void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
