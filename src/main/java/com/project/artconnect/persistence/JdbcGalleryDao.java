package com.project.artconnect.persistence;

import com.project.artconnect.dao.impl.GalleryDao;
import com.project.artconnect.model.Exhibition;
import com.project.artconnect.model.Gallery;
import com.project.artconnect.util.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcGalleryDao implements GalleryDao {

    @Override
    public Optional<Gallery> findById(Long id) {
        String sql = "SELECT g.name, g.address, g.rating FROM gallery g WHERE idGallery = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Optional <Gallery> gallery = Optional.empty();
            if (rs.next()) {
                gallery = Optional.of(new Gallery(rs.getString("name"), rs.getString("address"), rs.getDouble("rating")));
            }
            return gallery;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Gallery> findAll() {
        List<Gallery> galleries = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection()) {
            try( Statement stmt = conn.createStatement()){
                ResultSet rs = stmt.executeQuery("SELECT g.name, g.address, g.rating FROM gallery g");
                while (rs.next()) {
                    galleries.add(new Gallery(rs.getString("name"), rs.getString("address"), rs.getDouble("rating")));
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return galleries;
    }

    public Optional<Gallery> findByName(String name) {
        String sql = "SELECT g.name, g.address, g.rating FROM gallery g WHERE name = ?";
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Optional <Gallery> gallery = Optional.empty();
            if (rs.next()) {
                gallery = Optional.of(new Gallery(rs.getString("name"), rs.getString("address"), rs.getDouble("rating")));
            }
            return gallery;
    }catch (SQLException e) {
        throw new RuntimeException(e);}
    }

    public List<Exhibition> findGalleryExhibitions(Gallery gallery) {
        List<Exhibition> exhibitions = new ArrayList<>();
        String sql = "SELECT e.title, e.startDate, e.endDate, g.name as galleryName, g.address, g.rating FROM exhibition e JOIN gallery g ON e.idGallery = g.idGallery WHERE g.name = ?";
        try (Connection conn= ConnectionManager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exhibitions.add(new Exhibition(rs.getString("title"), rs.getDate("startDate").toLocalDate(), rs.getDate("endDate").toLocalDate(), new Gallery(rs.getString("galleryName"), rs.getString("address"), rs.getDouble("rating"))));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exhibitions;
    }
}
