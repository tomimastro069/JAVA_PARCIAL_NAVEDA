package org.example.main;

import  org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.AutoDAO;
import org.example.dao.MotoDAO;
import org.example.modelo.Auto;
import org.example.modelo.Moto;
import org.example.util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ADMIN_PASSWORD = "1234";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DataBaseManager.getConnection()) {
            DataBaseManager.inicializarBaseDeDatos();
            boolean salir = false;
            while (!salir) {
                System.out.println("\n ----MENU PRINCIPAL----\n 1: Gestionar Autos\n 2: Gestionar Motos\n 3: Salir");
                switch (scanner.nextLine()) {
                    case "1":
                        menuAuto(conn);
                        break;
                    case "2":
                        menuMoto(conn);
                        break;
                    case "3":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion invalida\n");
                }
            }
        } catch (Exception e) {
            logger.error("//logger//Error al iniciar la base de datos");
        }
    }
    
    public static void menuAuto (Connection conn){
        AutoDAO dao = new AutoDAO();
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Menú Autos ---");
            System.out.println("1. Insertar Auto");
            System.out.println("2. Listar Autos");
            System.out.println("3. Buscar por Patente");
            System.out.println("4. Modo Administrador");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Ingresar patente: (6 caracteres)");
                    String patente = scanner.nextLine();
                    if (new AutoDAO().buscarPorPatente(conn, patente) != null) {
                        System.out.println("Ya existe un Auto con esa patente.");
                        return;
                    }
                    while (patente.length() > 6 || patente.length() < 5) {
                        System.out.println("Error al ingresar patente // cantidad de caracteres erroneos: (6 caracteres)");
                        patente = scanner.nextLine();
                        if (new AutoDAO().buscarPorPatente(conn, patente) != null) {
                            System.out.println("Ya existe un Auto con esa patente.");
                            return;
                        }
                    }
                    System.out.print("Ingresar peso: ");
                    double peso = Double.parseDouble(scanner.nextLine());
                    System.out.print("Ingresar Capacidad Pasajeros: ");
                    int pasajeros = scanner.nextInt();
                    while (pasajeros > 5) {
                        System.out.println("Maximo 5 pasajeros por auto");
                        pasajeros = scanner.nextInt();
                    }
                    scanner.nextLine();

                    System.out.print("Ingresar Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ingresar Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Ingresar Cantidad de Puertas: ");
                    int puertas = scanner.nextInt();
                    scanner.nextLine();
                    while(puertas > 5){
                        System.out.println("Maximo 5 puertas");
                        puertas = scanner.nextInt();
                        scanner.nextLine();
                    }
                    dao.insertar(conn, new Auto(patente, peso, pasajeros, marca, modelo, puertas));
                    break;
                case "2":
                    List<Auto> autos = dao.listarTodos(conn);
                    for (Auto a : autos) {
                        System.out.println("Patente: " + a.getPatente() + ", Peso: " + a.getPeso() + "kg, Marca: " + a.getMarca() + ", Modelo: " + a.getModelo() + ", Puertas: " + a.getPuertas());
                    }
                    break;
                case "3":
                    System.out.println("Ingresar patente: ");
                    patente = scanner.nextLine();
                    Auto auto = dao.buscarPorPatente(conn, patente);
                    if (auto != null) {
                        System.out.println("Auto encontrado:");
                        System.out.println("Patente: " + auto.getPatente());
                        System.out.println("Peso: " + auto.getPeso());
                        System.out.println("Capacidad pasajeros: " + auto.getCapPasajeros());
                        System.out.println("Marca: " + auto.getMarca());
                        System.out.println("Modelo: " + auto.getModelo());
                        System.out.println("Puertas: " + auto.getPuertas());
                    } else {
                        System.out.println("No se encontró un auto con esa patente.");
                    }
                    break;
                case "4":
                    modoAdminAuto(conn);
                    break;
                case "5":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void modoAdminAuto(Connection conn) {
        System.out.print("Ingrese contraseña de administrador: ");
        if (!scanner.nextLine().equals(ADMIN_PASSWORD)) {
            System.out.println("Contraseña incorrecta");
            return;
        }
        AutoDAO dao = new AutoDAO();
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Modo Admin Auto ---");
            System.out.println("1. Actualizar Auto");
            System.out.println("2. Eliminar Auto");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.print("Patente del auto a actualizar: ");
                    String patente = scanner.nextLine();
                    Auto autoExistente = dao.buscarPorPatente(conn, patente);

                    if (autoExistente == null) {
                        System.out.println("No se encontró un auto con esa patente.");
                    } else {
                        System.out.print("Nuevo peso (" + autoExistente.getPeso() + "): ");
                        double peso = Double.parseDouble(scanner.nextLine());

                        System.out.print("Nueva capacidad de pasajeros (" + autoExistente.getCapPasajeros() + "): ");
                        int pasajeros = Integer.parseInt(scanner.nextLine());


                        System.out.print("Nueva marca (" + autoExistente.getMarca() + "): ");
                        String marca = scanner.nextLine();

                        System.out.print("Nuevo modelo (" + autoExistente.getModelo() + "): ");
                        String modelo = scanner.nextLine();

                        System.out.print("Cantidad de puertas (" + autoExistente.getPuertas() + "): ");
                        int puertas = Integer.parseInt(scanner.nextLine());

                        Auto actualizado = new Auto(patente, peso, pasajeros, marca, modelo, puertas);
                        dao.actualizar(conn, actualizado);
                        logger.info("Auto actualizado correctamente");
                    }

                    break;
                case "2":
                    System.out.println("Id de auto a ELIMINAR");
                    int id = Integer.parseInt(scanner.nextLine());
                    dao.eliminar(conn,id);
                    logger.info("Se elimino el auto con id'{}' ", id);
                    break;
                case "3":
                    volver = true;
                    break;
                default:
                    logger.info("Opción inválida");
            }
        }
    }

    private static void menuMoto(Connection conn) {
        MotoDAO dao = new MotoDAO();
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Menú Motos ---");
            System.out.println("1. Insertar Moto");
            System.out.println("2. Listar Motos");
            System.out.println("3. Buscar por Patente");
            System.out.println("4. Modo Administrador");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Ingresar patente: (6 caracteres)");
                    String patente = scanner.nextLine();
                    if (new MotoDAO().buscarPorPatente(conn, patente) != null) {
                        System.out.println("Ya existe una moto con esa patente.");
                        return;
                    }
                    while (patente.length() > 6 || patente.length() < 5) {
                        System.out.println("Error al ingresar patente // cantidad de caracteres erroneos (6 caracteres)");
                        if (new MotoDAO().buscarPorPatente(conn, patente) != null) {
                            System.out.println("Ya existe una moto con esa patente.");
                            return;
                        }
                    }
                    System.out.print("Ingresar peso: ");
                    double peso = scanner.nextDouble();
                    System.out.print("Ingresar Capacidad Pasajeros: ");
                    int pasajeros = scanner.nextInt();

                        while (pasajeros > 2) {
                            System.out.println("Maximo 2 pasajeros por auto");
                            pasajeros = scanner.nextInt();
                        }
                    scanner.nextLine();

                    System.out.print("Ingresar Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ingresar Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Ingresar la cinlindrada: ");
                    int cilindrada = scanner.nextInt();
                    dao.insertar(conn, new Moto(patente, peso, pasajeros, marca, modelo, cilindrada));
                    break;
                case "2":
                    List<Moto> motos = dao.listarTodos(conn);
                    for (Moto a : motos) {
                        System.out.println("Patente: " + a.getPatente() + ", Peso: " + a.getPeso() + "kg," +", Marca: " + a.getMarca() + ", Modelo: " + a.getModelo() + ", Puertas: " + a.getCilindrada());
                    }
                    break;
                case "3":
                    System.out.println("Ingresar patente: ");
                    patente = scanner.nextLine();
                    Moto moto = dao.buscarPorPatente(conn, patente);
                    if (moto != null) {
                        System.out.println("Moto encontrada:");
                        System.out.println("Patente: " + moto.getPatente());
                        System.out.println("Peso: " + moto.getPeso());
                        System.out.println("Capacidad pasajeros: " + moto.getCapPasajeros());
                        System.out.println("Marca: " + moto.getMarca());
                        System.out.println("Modelo: " + moto.getModelo());
                        System.out.println("Cilindrada: " + moto.getCilindrada());
                    } else {
                        logger.info("No se encontró una Moto con esa patente.");
                    }
                    break;
                case "4":
                    modoAdminMoto(conn);
                    break;
                case "5":
                    volver = true;
                    break;
                default:
                   logger.info("Opción inválida");
            }
            
        }
    }

    private static void modoAdminMoto(Connection conn) {
        System.out.print("Ingrese contraseña de administrador: ");
        if (!scanner.nextLine().equals(ADMIN_PASSWORD)) {
            System.out.println("Contraseña incorrecta");
            return;
        }
        MotoDAO dao = new MotoDAO();
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Modo Admin Moto ---");
            System.out.println("1. Actualizar Moto");
            System.out.println("2. Eliminar Moto");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.print("Patente del Moto a actualizar: ");
                    String patente = scanner.nextLine();
                    Moto motoExistente = dao.buscarPorPatente(conn, patente);

                    if (motoExistente == null) {
                        System.out.println("No se encontró un auto con esa patente.");
                    } else {
                        System.out.print("Nuevo peso (" + motoExistente.getPeso() + "): ");
                        double peso = Double.parseDouble(scanner.nextLine());

                        System.out.print("Nueva capacidad de pasajeros (" + motoExistente.getCapPasajeros() + "): ");
                        int pasajeros = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nueva marca (" + motoExistente.getMarca() + "): ");
                        String marca = scanner.nextLine();

                        System.out.print("Nuevo modelo (" + motoExistente.getModelo() + "): ");
                        String modelo = scanner.nextLine();

                        System.out.print("Nueva cilindrada (" + motoExistente.getCilindrada() + "): ");
                        int cilindrada = Integer.parseInt(scanner.nextLine());

                        Moto actualizado = new Moto(patente, peso, pasajeros, marca, modelo, cilindrada);
                        dao.actualizar(conn, actualizado);
                        logger.info("Moto actualizada correctamente");
                    }

                    break;
                case "2":
                    System.out.println("Id de Moto a ELIMINAR");
                    int id = Integer.parseInt(scanner.nextLine());
                    dao.eliminar(conn,id);
                   logger.info("Se elimino la Moto con id'{}' ", id);
                    break;
                case "3":
                    volver = true;
                    break;
                default:
                    logger.info("Opción inválida");
            }
        }
    }
}
