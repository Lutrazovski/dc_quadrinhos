package br.senac.dao;

import br.senac.dto.CategoriaDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CategoriaDao {

    public List<CategoriaDto> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM categoria";
        List<CategoriaDto> listaCategoria = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CategoriaDto categoria = new CategoriaDto();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                listaCategoria.add(categoria);
            }
        }
        return listaCategoria;
    }

    public CategoriaDto findById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CategoriaDto categoria = new CategoriaDto();
                    categoria.setId(rs.getInt("id"));
                    categoria.setNome(rs.getString("nome"));
                    return categoria;
                }
            }
        }
        return null;
    }
}
