package br.senac.dao;

import br.senac.dto.IlustradorDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class IlustradorDao {

    public List<IlustradorDto> findAll(Connection conn) throws SQLException {
        String sql = "select * from Ilustrador";
        List<IlustradorDto> listaIlustrador = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                IlustradorDto ilustrador = new IlustradorDto();
                ilustrador.setId(rs.getInt("id"));
                ilustrador.setNome(rs.getString("nome"));
                listaIlustrador.add(ilustrador);
            }
        }
        return listaIlustrador;
    }

    public IlustradorDto findById(Connection conn, int id) throws SQLException {
        String sql = "select * from Ilustrador where id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                IlustradorDto ilustrador = new IlustradorDto();
                ilustrador.setId(rs.getInt("id"));
                ilustrador.setNome(rs.getString("nome"));
                return ilustrador;
            }
        }
        return null;
    }
}
