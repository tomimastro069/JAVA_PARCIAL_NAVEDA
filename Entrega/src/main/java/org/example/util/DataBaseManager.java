package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/BD_Parcial_NAVEDA";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final Logger logger = LogManager.getLogger(DataBaseManager.class);


    public static Connection getConnection(){
        try {
            return  DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            logger.error("//logger//Error al conectar con la base de datos",e);
            throw new RuntimeException("Error al conectar con la base de datos",e);
        }
    }

    public static void inicializarBaseDeDatos() {
        try (Connection conn = getConnection()) {
            createTableVehiculo(conn);
            createTableMoto(conn);
            createTableAuto(conn);
            logger.info("//logger//Base de datos inicializada correctamente");
        } catch (SQLException e) {
            logger.error("//logger//Error al inicializar la base de datos", e);
            throw new RuntimeException("Error al inicializar la base de datos", e);
        }
    }
    public static void createTableVehiculo(Connection conn){
        String sql= "CREATE TABLE IF NOT EXISTS Vehiculo("+
                "ID INT PRIMARY KEY,"+
                "Nombre VARCHAR(52) NOT NULL)";
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
           logger.info("//logger//Tabla Vehiculo creada correctamente");
        } catch (SQLException e) {
            logger.error("//logger//Error al crear tabla Vehiculo",e);
            throw new RuntimeException("ERROR AL CREAR TABLA Vehiculo ",e);
        }

    }
    public static void createTableMoto(Connection conn){
        String sql = "CREATE TABLE IF NOT EXISTS Moto(" +
                "ID_MOTO INT AUTO_INCREMENT PRIMARY KEY," +
                "PATENTE VARCHAR(20) NOT NULL," +
                "PESO DOUBLE NOT NULL," +
                "CAPACIDAD_PASAJEROS INT NOT NULL," +
                "MARCA VARCHAR(50) NOT NULL," +
                "MODELO VARCHAR(50) NOT NULL,"+
                "CILINDRADA INT NOT NULL)";
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
            logger.info("//logger//Tabla Moto creada correctamente");
        } catch (SQLException e) {
            logger.error("//logger//Error al crear tabla moto",e);
            throw new RuntimeException("ERROR AL CREAR TABLA Moto", e);
        }
    }
    public static void createTableAuto(Connection conn){
        String sql = "CREATE TABLE IF NOT EXISTS Auto(" +
                "ID_AUTO INT AUTO_INCREMENT PRIMARY KEY," +
                "PATENTE VARCHAR(20) NOT NULL," +
                "PESO DOUBLE NOT NULL," +
                "CAPACIDAD_PASAJEROS INT NOT NULL," +
                "MARCA VARCHAR(50) NOT NULL," +
                "MODELO VARCHAR(50) NOT NULL," +
                "PUERTAS INT NOT NULL)";
        try (Statement stmt = conn.createStatement()){
            stmt.executeUpdate(sql);
           logger.info("//logger//Tabla Auto creada correctamente");
        } catch (SQLException e) {
            logger.error("//logger//Error al crear tabla Auto",e);
            throw new RuntimeException("ERROR AL CREAR TABLA Auto", e);
        }
    }


}