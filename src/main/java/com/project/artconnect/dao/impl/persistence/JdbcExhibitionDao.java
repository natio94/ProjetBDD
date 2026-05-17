package com.project.artconnect.dao.impl.persistence;

import com.project.artconnect.dao.ExhibitionDao;
import com.project.artconnect.model.Exhibition;
import com.project.artconnect.model.Gallery;
import com.project.artconnect.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcExhibitionDao implements ExhibitionDao {

    public Exhibition mapRowToExhibition(ResultSet rs) throws SQLException {
        Exhibition exhibition= new Exhibition(rs.getString("name"), rs.getDate("startDate").toLocalDate(),
                rs.getDate("endDate").toLocalDate(), new Gallery(rs.getString("g.name"),
                rs.getString("address"), rs.getDouble("rating")));
        exhibition.setCuratorName(rs.getString("curatorName"));
        exhibition.setTheme(rs.getString("theme"));
        return exhibition;
    }

    @Override
    public List<Exhibition> findAll() {
        List<Exhibition> exhibitions=new ArrayList<>();
    String sql = "SELECT e.*, g.* FROM exhibition e join gallery g on g.idGallery = e.idGallery";
    try(Connection con = ConnectionManager.getConnection(); Statement stmt = con.createStatement()){
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            exhibitions.add(mapRowToExhibition(rs));
        }
    }catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return exhibitions;
    }

    @Override
    public void save(Exhibition exhibition) {
        String sqlGallery = "select g.idGallery from Gallery g where g.name=?";
        String sqlExhib = "INSERT INTO exhibition(title, startDate, endDate, idGallery) VALUES (?,?,?,?)";
        try (Connection con = ConnectionManager.getConnection()){
        int idGallery;
        try (PreparedStatement ps = con.prepareStatement(sqlGallery)){
            ps.setString(1, exhibition.getGallery().getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idGallery = rs.getInt("idGallery");
            } else {
                throw new RuntimeException("Gallery not found: " + exhibition.getGallery().getName());
            }
        }
        try (PreparedStatement ps = con.prepareStatement(sqlExhib)){
            ps.setString(1, exhibition.getTitle());
            ps.setDate(2, Date.valueOf(exhibition.getStartDate()));
            ps.setDate(3, Date.valueOf(exhibition.getEndDate()));
            ps.setInt(4, idGallery);
            ps.executeUpdate();
        }

        }catch (SQLException e) {
            throw new RuntimeException(e);
    }
    }

    @Override
    public void update(Exhibition exhibition) {
        String sql = "UPDATE exhibition SET startDate = ?, endDate = ? WHERE title = ?";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setDate(1, Date.valueOf(exhibition.getStartDate()));
            ps.setDate(2, Date.valueOf(exhibition.getEndDate()));
            ps.setString(3, exhibition.getTitle());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String title) {
        String sql = "DELETE FROM exhibition WHERE title = ?";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, title);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
