package com.example.app;

import com.example.controller.TorreDeControl;
import com.example.exception.AeronaveDuplicadaException;
import com.example.model.*;
import com.example.util.Logger;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal del programa de Simulación de un Sistema de Control de Tráfico Aéreo
 *
 * @author GITCarlosBarrera
 * @version 1.0
 */
public class Simulador {

    static Scanner sc = new Scanner(System.in);
    static TorreDeControl torreDeControl = TorreDeControl.getInstance();

    /**
     * Método principal que inicia la ejecución del programa.
     * Carga el menú que se encarga de llamar a todos los métodos
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        cargarEjemplo(); // OPCIONAL: carga y añade a la cola distintas aeronaves por defecto para la realización de pruebas
        menu();
    }

    /**
     * Muestra el menú de opciones del simulador.
     */
    public static void menu() {
        int opcion;

        System.out.println("=== Simulador de Tráfico Aéreo ===");

        do {
            System.out.println("1. Registrar aeronave");
            System.out.println("2. Visualizar colas de aterrizaje y despegue");
            System.out.println("3. Comenzar simulación");
            System.out.println("4. Salir");
            System.out.print("> ");
            opcion = leerInt(4);

            switch (opcion) {
                case 1 -> registrarAeronave();
                case 2 -> visualizarColas();
                case 3 -> comenzarSimulacion();
                case 4 -> System.out.println("Saliendo de la simulación...");
            }
        } while (opcion != 4);
    }

    /**
     * Crea diferentes objetos de aeronave y los añade a las colas de aterrizaje y despegue.
     */
    public static void cargarEjemplo() {
        Random random = new Random();

        Aeronave[] aeronaves = {
                new Emergencia("EM1234", TipoAeronave.EMERGENCIA),
                new Emergencia("EM5678", TipoAeronave.EMERGENCIA),
                new Emergencia("EM9012", TipoAeronave.EMERGENCIA),

                new Militar("MI3456", TipoAeronave.MILITAR),
                new Militar("MI7890", TipoAeronave.MILITAR),
                new Militar("MI2345", TipoAeronave.MILITAR),

                new Pasajeros("PA6789", TipoAeronave.PASAJEROS),
                new Pasajeros("PA1122", TipoAeronave.PASAJEROS),
                new Pasajeros("PA3344", TipoAeronave.PASAJEROS),

                new Carga("CA5566", TipoAeronave.CARGA),
                new Carga("CA7788", TipoAeronave.CARGA),
                new Carga("CA9900", TipoAeronave.CARGA)
        };

        for (Aeronave a : aeronaves) {
            if (random.nextBoolean()) {
                torreDeControl.registrarAeronaveParaAterrizaje(a);
            } else {
                torreDeControl.registrarAeronaveParaDespegue(a);
            }
        }
    }

    /**
     * Muestra un menú para crear un objeto aeronave con un id y el tipo deseado
     * y lo añade a la cola de aterrizaje o despegue según desee el usuario.
     */
    public static void registrarAeronave() {
        int opcion;
        String id;
        Aeronave a = null;

        System.out.println("=== Registrar Aeronave ===");
        System.out.print("Id: ");
        id = leerId();

        System.out.println("Tipo de Aeronave:");
        System.out.println("1. Emergencia");
        System.out.println("2. Militar");
        System.out.println("3. Pasajeros");
        System.out.println("4. Carga");
        System.out.print("> ");
        opcion = leerInt(4);

        try {
            boolean existeEnAterrizajes = torreDeControl.getColaAterrizajes().stream()
                    .anyMatch(aircraft -> aircraft.getId().equals(id));
            boolean existeEnDespegues = torreDeControl.getColaDespegues().stream()
                    .anyMatch(aircraft -> aircraft.getId().equals(id));

            if (existeEnAterrizajes || existeEnDespegues) {
                throw new AeronaveDuplicadaException("La aeronave con ID: " + id + ", ya está registrada.");
            }

            switch (opcion) {
                case 1 -> a = new Emergencia(id, TipoAeronave.EMERGENCIA);
                case 2 -> a = new Militar(id, TipoAeronave.MILITAR);
                case 3 -> a = new Pasajeros(id, TipoAeronave.PASAJEROS);
                case 4 -> a = new Carga(id, TipoAeronave.CARGA);
            }

            System.out.println("Seleccione la cola:");
            System.out.println("1. Añadir a la cola de aterrizaje");
            System.out.println("2. Añadir a la cola de despegue");
            System.out.print("> ");
            opcion = leerInt(2);

            switch (opcion) {
                case 1 -> a.solicitarAterrizaje();
                case 2 -> a.solicitarDespegue();
            }
        } catch (AeronaveDuplicadaException e) {
            Logger.log(e.getMessage());
            System.err.println(e.getMessage());
        }
    }

    /**
     * Muestra las aeronaves que hay registradas en ambas colas.
     */
    public static void visualizarColas() {
        int i;

        System.out.println("=== Cola de aterrizaje ===");

        if (!torreDeControl.getColaAterrizajes().isEmpty()) {
            i = 1;
            for (Aeronave a : torreDeControl.getColaAterrizajes()) {
                System.out.println(i++ + ". " + a);
            }
        } else {
            System.out.println("No quedan aterrizajes en la cola.");
        }

        System.out.println("=== Cola de despegue ===");

        if (!torreDeControl.getColaDespegues().isEmpty()) {
            i = 1;
            for (Aeronave a : torreDeControl.getColaDespegues()) {
                System.out.println(i++ + ". " + a);
            }
        } else {
            System.out.println("No quedan despegues en la cola.");
        }
    }

    /**
     * Ejecuta la simulación procesando cada evento por cada aeronave registrada en cada cola.
     */
    public static void comenzarSimulacion() {
        while (!torreDeControl.getColaDespegues().isEmpty() || !torreDeControl.getColaAterrizajes().isEmpty()) {
            torreDeControl.procesarSiguienteEvento();
        }
    }

    /**
     * Lee un número int pasado por teclado y lo devuelve.
     *
     * @param maxOpcion Cantidad máxima de opciones a elegir.
     * @return Número introducido por teclado
     */
    private static int leerInt(int maxOpcion) {
        int num;

        do {
            while (!sc.hasNextInt()) {
                System.out.print("Por favor, introduce una opción válida: ");
                sc.next();
            }
            num = sc.nextInt();
            sc.nextLine();
        } while (num < 1 || num > maxOpcion);

        return num;
    }
    /**
     * Lee el id pasado por teclado y si cumple el patrón requerido
     * lo devuelve.
     *
     * @return ID introducido por teclado
     */
    private static String leerId() {
        String id;

        do {
            System.out.print("Introduce el id (Ejemplo: IB3721): ");
            id = sc.nextLine().trim();

            if (!id.matches("[A-Z]{2}[0-9]{4}")) {
                System.out.println("Id no válido");
            }
        } while (!id.matches("[A-Z]{2}[0-9]{4}"));

        return id;
    }
}
