package br.senac.dao;

import br.senac.dto.*;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class QuadrinhoDao {

    public void save(Connection conn ,QuadrinhoDto quadrinho) throws SQLException {
        String sql = "INSERT INTO quadrinho(id, nome, imagem, id_heroi) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quadrinho.getId());
            stmt.setString(2, quadrinho.getNome());
            stmt.setString(3, quadrinho.getImagem());
            stmt.setInt(4, quadrinho.getHeroi().getId());
            stmt.executeUpdate();
        }
    }

    public void update(Connection conn, QuadrinhoDto quadrinho) throws SQLException {
        String sql = "UPDATE quadrinho SET nome = ?, imagem = ?, id_heroi = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quadrinho.getNome());
            stmt.setString(2, quadrinho.getImagem());
            stmt.setInt(3, quadrinho.getHeroi().getId());
            stmt.setInt(4, quadrinho.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Connection conn , int id) throws SQLException {
        String sql = "DELETE FROM quadrinho WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public QuadrinhoDto findById(Connection conn, int id) throws SQLException {
        String sql = "SELECT id, nome, imagem, id_heroi FROM quadrinho WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    QuadrinhoDto quadrinho = new QuadrinhoDto();
                    quadrinho.setId(rs.getInt("id"));
                    quadrinho.setNome(rs.getString("nome"));
                    quadrinho.setImagem(rs.getString("imagem"));

                    HeroiDto heroi = new HeroiDto();
                    heroi.setId(rs.getInt("id_heroi"));
                    quadrinho.setHeroi(heroi);

                    return quadrinho;
                }
            }
        }
        return null;
    }

    public List<QuadrinhoDto> findAll(Connection conn) throws SQLException {
        String sql = "SELECT id, nome, imagem, id_heroi FROM quadrinho";
        List<QuadrinhoDto> quadrinhos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QuadrinhoDto quadrinho = new QuadrinhoDto();
                quadrinho.setId(rs.getInt("id"));
                quadrinho.setNome(rs.getString("nome"));
                quadrinho.setImagem(rs.getString("imagem"));

                HeroiDto heroi = new HeroiDto();
                heroi.setId(rs.getInt("id_heroi"));
                quadrinho.setHeroi(heroi);

                quadrinhos.add(quadrinho);
            }
        }
        return quadrinhos;
    }

    public int getNextId(Connection conn) throws SQLException {
        String sql = "SELECT nextval('sq_quadrinho')";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    public QuadrinhoDto findByHeroiId(Connection conn, int id) throws SQLException {
        String sql = "SELECT id, nome, imagem, id_heroi FROM quadrinho WHERE id_heroi = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    QuadrinhoDto quadrinho = new QuadrinhoDto();
                    quadrinho.setId(rs.getInt("id"));
                    quadrinho.setNome(rs.getString("nome"));
                    quadrinho.setImagem(rs.getString("imagem"));

                    HeroiDto heroi = new HeroiDto();
                    heroi.setId(rs.getInt("id_heroi"));
                    heroi.setNome(rs.getString("nome"));
                    quadrinho.setHeroi(heroi);

                    return quadrinho;
                }
            }
        }
        return null;
    }
}
