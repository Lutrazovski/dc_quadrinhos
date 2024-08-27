package br.senac.dao;

import br.senac.dto.*;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class QuadrinhoDao {

    public void save(Connection conn ,QuadrinhoDto quadrinho) throws SQLException {
        String sql = "INSERT INTO quadrinho(id, titulo_quadrinho, data_publicacao, numero_paginas, sinopse, imagem, id_autor, id_editor, id_ilustrador, id_categoria) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quadrinho.getId());
            stmt.setString(2, quadrinho.getNome());
            stmt.setDate(3, new java.sql.Date(quadrinho.getDataPublicacao().getTime()));
            stmt.setInt(4, quadrinho.getNumeroPaginas());
            stmt.setString(5, quadrinho.getSinopse());
            stmt.setBytes(6, quadrinho.getImagem());
            stmt.setInt(7, quadrinho.getAutor().getId());
            stmt.setInt(8, quadrinho.getEditor().getId());
            stmt.setInt(9, quadrinho.getIlustrador().getId());
            stmt.setInt(10, quadrinho.getCategoria().getId());
            stmt.executeUpdate();
        }
    }

    public void update(Connection conn, QuadrinhoDto quadrinho) throws SQLException {
        String sql = "UPDATE quadrinho SET titulo_quadrinho = ?, data_publicacao = ?, numero_paginas = ?, sinopse = ?, imagem = ?, id_autor = ?, id_editor = ?, id_ilustrador = ?, id_categoria = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quadrinho.getNome());
            stmt.setDate(2, new java.sql.Date(quadrinho.getDataPublicacao().getTime()));
            stmt.setInt(3, quadrinho.getNumeroPaginas());
            stmt.setString(4, quadrinho.getSinopse());
            stmt.setBytes(5, quadrinho.getImagem());
            stmt.setInt(6, quadrinho.getAutor().getId());
            stmt.setInt(7, quadrinho.getEditor().getId());
            stmt.setInt(8, quadrinho.getIlustrador().getId());
            stmt.setInt(9, quadrinho.getCategoria().getId());
            stmt.setInt(10, quadrinho.getId());
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
        String sql = "SELECT titulo_quadrinho, data_publicacao, numero_paginas, sinopse, imagem, id_autor, id_editor, id_ilustrador, id_categoria FROM quadrinho WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    QuadrinhoDto quadrinho = new QuadrinhoDto();
                    quadrinho.setId(rs.getInt("id"));
                    quadrinho.setNome(rs.getString("titulo_quadrinho"));
                    quadrinho.setDataPublicacao(rs.getDate("data_publicacao"));
                    quadrinho.setNumeroPaginas(rs.getInt("numero_paginas"));
                    quadrinho.setSinopse(rs.getString("sinopse"));
                    quadrinho.setImagem(rs.getBytes("imagem"));

                    AutorDto autor = new AutorDto();
                    autor.setId(rs.getInt("id_autor"));
                    quadrinho.setAutor(autor);
                    EditorDto editor = new EditorDto();
                    editor.setId(rs.getInt("id_editor"));
                    quadrinho.setEditor(editor);
                    IlustradorDto ilustrador = new IlustradorDto();
                    ilustrador.setId(rs.getInt("id_ilustrador"));
                    quadrinho.setIlustrador(ilustrador);
                    CategoriaDto categoria = new CategoriaDto();
                    categoria.setId(rs.getInt("id_categoria"));
                    quadrinho.setCategoria(categoria);

                    return quadrinho;
                }
            }
        }
        return null;
    }

    public List<QuadrinhoDto> findAll(Connection conn) throws SQLException {
        String sql = "SELECT titulo_quadrinho, data_publicacao, numero_paginas, sinopse, imagem, id_autor, id_editor, id_ilustrador, id_categoria FROM quadrinho";
        List<QuadrinhoDto> quadrinhos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QuadrinhoDto quadrinho = new QuadrinhoDto();
                quadrinho.setId(rs.getInt("id"));
                quadrinho.setNome(rs.getString("titulo_quadrinho"));
                quadrinho.setDataPublicacao(rs.getDate("data_publicacao"));
                quadrinho.setNumeroPaginas(rs.getInt("numero_paginas"));
                quadrinho.setSinopse(rs.getString("sinopse"));
                quadrinho.setImagem(rs.getBytes("imagem"));

                AutorDto autor = new AutorDto();
                autor.setId(rs.getInt("id_autor"));
                quadrinho.setAutor(autor);
                EditorDto editor = new EditorDto();
                editor.setId(rs.getInt("id_editor"));
                quadrinho.setEditor(editor);
                IlustradorDto ilustrador = new IlustradorDto();
                ilustrador.setId(rs.getInt("id_ilustrador"));
                quadrinho.setIlustrador(ilustrador);
                CategoriaDto categoria = new CategoriaDto();
                categoria.setId(rs.getInt("id_categoria"));
                quadrinho.setCategoria(categoria);

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
