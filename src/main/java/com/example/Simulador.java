package com.example;

import java.util.Random;
import java.util.Scanner;

public class Simulador {
    static Scanner sc = new Scanner(System.in);
    static TorreDeControl torreDeControl = TorreDeControl.getInstance();

    public static void main(String[] args) {
        cargarEjemplo();
        menu();
    }

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
                case 4 -> System.out.println("Saliendo de la simualación...");
            }
        } while (opcion != 4);
    }

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
    }

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

    public static void comenzarSimulacion() {
        while (!torreDeControl.getColaDespegues().isEmpty() || !torreDeControl.getColaAterrizajes().isEmpty()) {
            torreDeControl.procesarSiguienteEvento();
        }
    }


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

    private static String leerId() {
        String id;
        do {
            System.out.print("Introduce el id (Ejemplo: IB3721): ");
            id = sc.nextLine().trim();

            if (!id.matches("[A-Z]{2}[0-9]{4}")) {
                System.out.println("Id no valido");
            }
        } while (!id.matches("[A-Z]{2}[0-9]{4}"));
        return id;
    }
}