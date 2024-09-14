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
public class HeroiService {

    @Inject
    HeroiDao heroiDao;

    @Inject
    DataSource dataSource;

    @Transactional
    public void createQuadrinho(HeroiDto heroi) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            int nextId = heroiDao.getNextId(conn);
            heroi.setId(nextId);
            heroiDao.save(conn, heroi);
        }
    }

    @Transactional
    public void updateQuadrinho(HeroiDto heroi) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            heroiDao.update(conn, heroi);
        }
    }

    @Transactional
    public void deleteQuadrinho(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            heroiDao.delete(conn, id);
        }
    }

    public HeroiDto getQuadrinhoById(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return heroiDao.findById(conn, id);
        }
    }

    public List<HeroiDto> getAllQuadrinhos() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return heroiDao.findAll(conn);
        }
    }
}
