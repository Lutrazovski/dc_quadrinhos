package br.senac.dao;

import br.senac.dto.AutorDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AutorDao {

    public List<AutorDto> findAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM autor";
        List<AutorDto> listaAutor = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AutorDto autor = new AutorDto();
                autor.setId(rs.getInt("id"));
                autor.setNome(rs.getString("nome"));
                listaAutor.add(autor);
            }
        }
        return listaAutor;
    }

    public AutorDto findById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM autor WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    AutorDto autor = new AutorDto();
                    autor.setId(rs.getInt("id"));
                    autor.setNome(rs.getString("nome"));
                    return autor;
                }
            }
        }
        return null;
    }
}
