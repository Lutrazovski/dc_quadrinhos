package br.senac.dao;

import br.senac.dto.*;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class HeroiDao {

    public void save(Connection conn , HeroiDto heroi) throws SQLException {
        String sql = "INSERT INTO heroi(id, nome_heroi, imagem,) VALUES(?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, heroi.getId());
            stmt.setString(2, heroi.getNome());
            stmt.setBytes(3, heroi.getImagem());
            stmt.executeUpdate();
        }
    }

    public void update(Connection conn, HeroiDto heroi) throws SQLException {
        String sql = "UPDATE heroi SET nome_heroi = ?, imagem = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, heroi.getNome());
            stmt.setBytes(2, heroi.getImagem());
            stmt.setInt(3, heroi.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Connection conn , int id) throws SQLException {
        String sql = "DELETE FROM heroi WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public HeroiDto findById(Connection conn, int id) throws SQLException {
        String sql = "SELECT nome_heroi, imagem, FROM heroi WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    HeroiDto heroi = new HeroiDto();
                    heroi.setId(rs.getInt("id"));
                    heroi.setNome(rs.getString("titulo_quadrinho"));
                    heroi.setImagem(rs.getBytes("imagem"));

                    return heroi;
                }
            }
        }
        return null;
    }

    public List<HeroiDto> findAll(Connection conn) throws SQLException {
        String sql = "SELECT nome_heroi, imagem FROM heroi";
        List<HeroiDto> herois = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HeroiDto heroi = new HeroiDto();
                heroi.setId(rs.getInt("id"));
                heroi.setNome(rs.getString("titulo_quadrinho"));
                heroi.setImagem(rs.getBytes("imagem"));

                herois.add(heroi);
            }
        }
        return herois;
    }

    public int getNextId(Connection conn) throws SQLException {
        String sql = "SELECT nextval('sq_heroi')";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }
}
