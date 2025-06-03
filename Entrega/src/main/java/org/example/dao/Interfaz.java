package org.example.dao;

import org.example.modelo.Auto;

import java.sql.Connection;
import java.util.List;

public interface Interfaz<T> {
    void insertar(Connection conn, T entidad);
    T buscarPorPatente(Connection conn, String patente);
    List<T> listarTodos(Connection conn);
    void actualizar(Connection conn, T entidad);
    Auto eliminar(Connection conn, int id);
}
