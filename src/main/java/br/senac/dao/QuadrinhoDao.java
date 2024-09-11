package br.senac.dao;

import br.senac.dto.*;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class QuadrinhoDao {

    public void save(Connection conn ,QuadrinhoDto quadrinho) throws SQLException {
        String sql = "INSERT INTO quadrinho(id, titulo_quadrinho, imagem,) VALUES(?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quadrinho.getId());
            stmt.setString(2, quadrinho.getNome());
            stmt.setBytes(3, quadrinho.getImagem());
            stmt.executeUpdate();
        }
    }

    public void update(Connection conn, QuadrinhoDto quadrinho) throws SQLException {
        String sql = "UPDATE quadrinho SET titulo_quadrinho = ?, imagem = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quadrinho.getNome());
            stmt.setBytes(2, quadrinho.getImagem());
            stmt.setInt(3, quadrinho.getId());
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
        String sql = "SELECT titulo_quadrinho, imagem, FROM quadrinho WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    QuadrinhoDto quadrinho = new QuadrinhoDto();
                    quadrinho.setId(rs.getInt("id"));
                    quadrinho.setNome(rs.getString("titulo_quadrinho"));
                    quadrinho.setImagem(rs.getBytes("imagem"));

                    return quadrinho;
                }
            }
        }
        return null;
    }

    public List<QuadrinhoDto> findAll(Connection conn) throws SQLException {
        String sql = "SELECT titulo_quadrinho, imagem FROM quadrinho";
        List<QuadrinhoDto> quadrinhos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QuadrinhoDto quadrinho = new QuadrinhoDto();
                quadrinho.setId(rs.getInt("id"));
                quadrinho.setNome(rs.getString("titulo_quadrinho"));
                quadrinho.setImagem(rs.getBytes("imagem"));

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
}
