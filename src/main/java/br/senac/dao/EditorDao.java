package br.senac.dao;

import br.senac.dto.EditorDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EditorDao {

    public List<EditorDto> findAll(Connection conn) throws SQLException {
        String sql = "select * from editor";
        List<EditorDto> listaEditor = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EditorDto editor = new EditorDto();
                editor.setId(rs.getInt("id"));
                editor.setNome(rs.getString("nome"));
                listaEditor.add(editor);
            }
        }
        return listaEditor;
    }

    public EditorDto findById(Connection conn, int id) throws SQLException {
        String sql = "select * from editor where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EditorDto editor = new EditorDto();
                editor.setId(rs.getInt("id"));
                editor.setNome(rs.getString("nome"));
                return editor;
            }
        }
        return null;
    }
}
