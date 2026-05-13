package com.project.artconnect.persistence;

import com.project.artconnect.dao.impl.ArtworkDao;
import com.project.artconnect.model.Artist;
import com.project.artconnect.model.Artwork;
import com.project.artconnect.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcArtworkDao implements ArtworkDao {


    public JdbcArtworkDao( ) {

    }


    private Artwork mapRow(ResultSet rs) throws SQLException {
        Artwork artwork = new Artwork();
        artwork.setTitle(rs.getString("Title"));
        artwork.setCreationYear(rs.getInt("CreationYear"));
        artwork.setType(rs.getString("Type"));
        artwork.setMedium(rs.getString("Medium"));
        artwork.setDimensions(rs.getString("Dimensions"));
        artwork.setDescription(rs.getString("Descriptions"));
        artwork.setPrice(rs.getDouble("Price"));
        artwork.setStatus(Artwork.Status.valueOf(rs.getString("Status").toUpperCase()));

        Artist artist = new Artist();
        artist.setName(rs.getString("artist_name"));
        artwork.setArtist(artist);

        return artwork;
    }

    @Override
    public List<Artwork> findAll() {
        String sql = "SELECT aw.*, a.name AS artist_name " +
                "FROM Artwork aw " +
                "JOIN Artiste a ON aw.id_artiste = a.id_artiste";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Artwork> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Artwork artwork) {
        // D'abord on récupère l'id_artiste depuis le nom de l'artiste
        String sqlArtiste = "SELECT id_artiste FROM Artiste WHERE name = ?";
        String sqlInsert = "INSERT INTO Artwork (Title, CreationYear, Type, Medium, " +
                "Dimensions, Descriptions, Price, Status, id_artiste) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionManager.getConnection()) {
            // Récupérer l'id de l'artiste
            int idArtiste;
            try (PreparedStatement ps = con.prepareStatement(sqlArtiste)) {
                ps.setString(1, artwork.getArtist().getName());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) throw new RuntimeException("Artiste introuvable : " + artwork.getArtist().getName());
                idArtiste = rs.getInt("id_artiste");
            }
            // Insérer l'artwork
            try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
                ps.setString(1, artwork.getTitle());
                ps.setInt(2, artwork.getCreationYear());
                ps.setString(3, artwork.getType());
                ps.setString(4, artwork.getMedium());
                ps.setString(5, artwork.getDimensions());
                ps.setString(6, artwork.getDescription());
                ps.setDouble(7, artwork.getPrice());
                ps.setString(8, artwork.getStatus().name());
                ps.setInt(9, idArtiste);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Artwork artwork) {
        String sql = "UPDATE Artwork SET Type = ?, Medium = ?, Descriptions = ?, " +
                "Price = ?, Status = ? WHERE Title = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, artwork.getType());
            ps.setString(2, artwork.getMedium());
            ps.setString(3, artwork.getDescription());
            ps.setDouble(4, artwork.getPrice());
            ps.setString(5, artwork.getStatus().name());
            ps.setString(6, artwork.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String title) {
        String sql = "DELETE FROM Artwork WHERE Title = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Artwork> findByArtistName(String artistName) {
        String sql = "SELECT aw.*, a.name AS artist_name " +
                    "FROM Artwork aw " +
                    "JOIN Artiste a ON aw.id_artiste = a.id_artiste " +
                    "WHERE a.name = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, artistName);
            ResultSet rs = ps.executeQuery();
            List<Artwork> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Artwork> findByTitle(String title) {
        String sql = "SELECT aw.*, a.name AS artist_name " +
                "FROM Artwork aw " +
                "JOIN Artiste a ON aw.id_artiste = a.id_artiste " +
                "WHERE aw.Title = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}