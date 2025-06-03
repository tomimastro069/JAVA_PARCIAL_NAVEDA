package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.modelo.Auto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoDAO implements Interfaz<Auto> {

    private static final Logger logger = LogManager.getLogger(AutoDAO.class);

    @Override
    public void insertar(Connection conn, Auto auto) {
        if(buscarPorPatente(conn, auto.getPatente())!= null){
            logger.warn("//logger//La patente ya esta registrada '{}'",auto.getPatente());
            throw new RuntimeException("La patente ya esta registrada");
        }
        String sql = "INSERT INTO Auto (PATENTE, PESO, CAPACIDAD_PASAJEROS, MARCA, MODELO, PUERTAS) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, auto.getPatente());
            stmt.setDouble(2, auto.getPeso());
            stmt.setInt(3, auto.getCapPasajeros());
            stmt.setString(4, auto.getMarca());
            stmt.setString(5, auto.getModelo());
            stmt.setInt(6, auto.getPuertas());
            stmt.executeUpdate();
            logger.info("//logger//Insertado Auto con patente '{}'", auto.getPatente()); //Muestra informacion
        } catch (SQLException e) {
            logger.error("//logger//Error al insertar Auto con patente '{}'", auto.getPatente(), e);
            throw new RuntimeException("Error al insertar Auto", e);
        }
    }

    @Override
    public Auto buscarPorPatente(Connection conn, String patente) {
        String sql = "SELECT * FROM Auto WHERE PATENTE = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Auto auto = new Auto(
                        rs.getString("PATENTE"),
                        rs.getDouble("PESO"),
                        rs.getInt("CAPACIDAD_PASAJEROS"),
                        rs.getString("MARCA"),
                        rs.getString("MODELO"),
                        rs.getInt("PUERTAS")
                );
                logger.info("//logger//Auto encontrado con PATENTE '{}'",patente);
                return auto;
            }
        } catch (SQLException e) {
            logger.error("//logger//Error al buscar Auto con PATENTE '{}'", patente, e);
            throw new RuntimeException("Error al buscar Auto", e);
        }
        return null;
    }

    @Override
    public List<Auto> listarTodos(Connection conn) {
        List<Auto> autos = new ArrayList<>();
        String sql = "SELECT * FROM Auto";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Auto auto = new Auto(
                        rs.getString("PATENTE"),
                        rs.getDouble("PESO"),
                        rs.getInt("CAPACIDAD_PASAJEROS"),
                        rs.getString("MARCA"),
                        rs.getString("MODELO"),
                        rs.getInt("PUERTAS")
                );
                autos.add(auto);
            }
            logger.info("//logger//Listado de Autos completado, total encontrados: {}", autos.size());
        } catch (SQLException e) {
            logger.error("//logger//Error al listar Autos", e);
            throw new RuntimeException("Error al listar Autos", e);
        }
        return autos;
    }

    @Override
    public void actualizar(Connection conn, Auto auto) {
        String sql = "UPDATE Auto SET PESO = ?, CAPACIDAD_PASAJEROS = ?, MARCA = ?, MODELO = ?, PUERTAS = ? WHERE PATENTE = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, auto.getPeso());
            stmt.setInt(2, auto.getCapPasajeros());
            stmt.setString(3, auto.getMarca());
            stmt.setString(4, auto.getModelo());
            stmt.setInt(5, auto.getPuertas());
            stmt.setString(6, auto.getPatente());
            stmt.executeUpdate();
            logger.info("//logger//Actualizado Auto con patente '{}'", auto.getPatente());
        } catch (SQLException e) {
            logger.error("//logger//Error al actualizar Auto con patente '{}'", auto.getPatente(), e);
            throw new RuntimeException("Error al actualizar Auto", e);
        }
    }

    @Override
    public Auto eliminar(Connection conn, int id) {
        String sql = "DELETE FROM Auto WHERE ID_AUTO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("//logger//Eliminado Auto con ID '{}'", id);
        } catch (SQLException e) {
            logger.error("//logger//Error al eliminar Auto con ID '{}'", id, e);
            throw new RuntimeException("Error al eliminar Auto", e);
        }
        return null;
    }
}
