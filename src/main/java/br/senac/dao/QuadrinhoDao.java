package br.senac.dao;

import br.senac.dto.QuadrinhoDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;

@ApplicationScoped
public class QuadrinhoDao {

    @Inject
    DataSource dataSource;

    public void criarQuadrinho(QuadrinhoDto quadrinho) throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "insert into quadrinho(id, titulo_quadrinho, data_publicacao, numero_paginas, sinopse, imagem, id_autor, id_editor, id_ilustrador, id_categoria) values(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, getNextIdQuadrinho());
            stmt.setString(2, quadrinho.getNome());
            stmt.setDate(3, new java.sql.Date(quadrinho.getDataPublicacao().getTime()));
            stmt.setInt(4, quadrinho.getNumeroPaginas());
            stmt.setString(5, quadrinho.getSinopse());
            stmt.setBytes(6, quadrinho.getImagem());


        }
    }

    public int getNextIdQuadrinho() throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "select nextval('sq_quadrinho')";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }
}
