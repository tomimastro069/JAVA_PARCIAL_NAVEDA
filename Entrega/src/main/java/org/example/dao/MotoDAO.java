package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.modelo.Auto;
import org.example.modelo.Moto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class MotoDAO implements Interfaz<Moto> {
        private static final Logger logger = LogManager.getLogger(MotoDAO.class);
        @Override
        public void insertar(Connection conn, Moto moto) {
            if(buscarPorPatente(conn, moto.getPatente())!= null){
                logger.warn("//logger//La patente ya esta registrada '{}'",moto.getPatente());
                throw new RuntimeException("La patente ya esta registrada");
            }
            String sql = "INSERT INTO Moto (PATENTE, PESO, CAPACIDAD_PASAJEROS, MARCA, MODELO, CILINDRADA) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, moto.getPatente());
                stmt.setDouble(2, moto.getPeso());
                stmt.setInt(3, moto.getCapPasajeros());
                stmt.setString(4, moto.getMarca());
                stmt.setString(5, moto.getModelo());
                stmt.setInt(6, moto.getCilindrada());
                logger.info("//logger//Insertada Moto con patente '{}'", moto.getPatente());
                stmt.executeUpdate();
            } catch (SQLException e) {
                logger.error("//logger//Error al insertar Moto",e);
                throw new RuntimeException("Error al insertar Moto", e);
            }
        }

        @Override
        public Moto buscarPorPatente(Connection conn, String patente) {
            String sql = "SELECT * FROM Moto WHERE PATENTE = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, patente);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Moto moto = new Moto(
                            rs.getString("PATENTE"),
                            rs.getDouble("PESO"),
                            rs.getInt("CAPACIDAD_PASAJEROS"),
                            rs.getString("MARCA"),
                            rs.getString("MODELO"),
                            rs.getInt("CILINDRADA")
                    );
                    logger.info("//logger//Moto encontrada con patente'{}'",patente);
                    return moto;
                }
            } catch (SQLException e) {
                logger.error("//logger//Error al buscar Moto por patente ",e);
                throw new RuntimeException("Error al buscar Moto por patente", e);
            }
            return null;
        }


        @Override
        public List<Moto> listarTodos(Connection conn) {
            List<Moto> motos = new ArrayList<>();
            String sql = "SELECT * FROM Moto";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Moto moto = new Moto(
                            rs.getString("PATENTE"),
                            rs.getDouble("PESO"),
                            rs.getInt("CAPACIDAD_PASAJEROS"),
                            rs.getString("MARCA"),
                            rs.getString("MODELO"),
                            rs.getInt("CILINDRADA")
                    );
                    motos.add(moto);
                }
                logger.info("//logger//Listado de Motos completado, total encontrados: {}", motos.size());

            } catch (SQLException e) {
                logger.error("//logger//Error al listar Motos", e);
                throw new RuntimeException("Error al listar Motos", e);
            }
            return motos;
        }

        @Override
        public void actualizar(Connection conn, Moto moto) {
            String sql = "UPDATE Moto SET PESO = ?, CAPACIDAD_PASAJEROS = ?, MARCA = ?, MODELO = ?, CILINDRADA = ? WHERE PATENTE = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDouble(1, moto.getPeso());
                stmt.setInt(2, moto.getCapPasajeros());
                stmt.setString(3, moto.getMarca());
                stmt.setString(4, moto.getModelo());
                stmt.setInt(5, moto.getCilindrada());
                stmt.setString(6, moto.getPatente());
                stmt.executeUpdate();
                logger.info("//logger//Actualizado Moto con patente '{}'", moto.getPatente());

            } catch (SQLException e) {
                logger.error("//logger//Error al actualizar Moto con patente '{}'", moto.getPatente(), e);
                throw new RuntimeException("Error al actualizar Moto", e);
            }
        }

        @Override
        public Auto eliminar(Connection conn, int id) {
            String sql = "DELETE FROM Moto WHERE ID_MOTO = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                logger.info("//logger//Eliminada Moto con ID '{}'", id);
            } catch (SQLException e) {
                logger.error("//logger//Error al eliminar Moto con id'{}'",id);
                throw new RuntimeException("Error al eliminar Moto", e);
            }
            return null;
        }
    }


