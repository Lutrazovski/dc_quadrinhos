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
    QuadrinhoDao quadrinhoDao;

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
            return quadrinhoDao.findAll(conn);
        }
    }

    public List<QuadrinhoDto> getQuadrinhoByHeroiId(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return quadrinhoDao.findByHeroiId(conn, id);
        }
    }
}
