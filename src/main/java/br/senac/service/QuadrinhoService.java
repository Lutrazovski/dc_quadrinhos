package br.senac.service;

import br.senac.dao.*;
import br.senac.dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class QuadrinhoService {

    @Inject
    private QuadrinhoDao quadrinhoDao;

    @Inject
    private AutorDao autorDao;

    @Inject
    private CategoriaDao categoriaDao;

    @Inject
    private EditorDao editorDao;

    @Inject
    private IlustradorDao illustradorDao;

    @Inject
    DataSource dataSource;

    @Transactional
    public void createQuadrinho(QuadrinhoDto quadrinho) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            int nextId = quadrinhoDao.getNextId(conn);
            quadrinho.setId(nextId);
            quadrinhoDao.save(conn, quadrinho);
        }
    }

    @Transactional
    public void updateQuadrinho(QuadrinhoDto quadrinho) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            quadrinhoDao.update(conn, quadrinho);
        }
    }

    @Transactional
    public void deleteQuadrinho(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            quadrinhoDao.delete(conn, id);
        }
    }

    public QuadrinhoDto getQuadrinhoById(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return quadrinhoDao.findById(conn, id);
        }
    }

    public List<QuadrinhoDto> getAllQuadrinhos() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            List<QuadrinhoDto> quadrinhos = quadrinhoDao.findAll(conn);
            for (QuadrinhoDto quadrinho : quadrinhos) {
                AutorDto autor = autorDao.findById(conn, quadrinho.getAutor().getId());
                quadrinho.setAutor(autor);
                CategoriaDto categoria = categoriaDao.findById(conn, quadrinho.getCategoria().getId());
                quadrinho.setCategoria(categoria);
                EditorDto editor = editorDao.findById(conn, quadrinho.getEditor().getId());
                quadrinho.setEditor(editor);
                IlustradorDto ilustrador  = illustradorDao.findById(conn, quadrinho.getIlustrador().getId());
                quadrinho.setIlustrador(ilustrador);
                return quadrinhos;
            }
        }
        return null;
    }
}
