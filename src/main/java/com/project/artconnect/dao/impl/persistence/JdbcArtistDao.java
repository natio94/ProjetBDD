package com.project.artconnect.dao.impl.persistence;

import com.project.artconnect.dao.ArtistDao;
import com.project.artconnect.model.Artist;
import com.project.artconnect.model.Discipline;
import com.project.artconnect.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation for ArtistDao.
 * TODO: Students must implement this using JDBC and SQL.
 */
public class JdbcArtistDao implements ArtistDao {


    public JdbcArtistDao() {
    }

    @Override
    public List<Artist> findAll(){
        // TODO: Implement SELECT * FROM artist
        List<Artist> artists = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection()) {
            try( Statement stmt = conn.createStatement()){
                ResultSet rs = stmt.executeQuery("SELECT * FROM artiste");
                while (rs.next()) {
                    artists.add(new Artist(rs.getString("name"), rs.getString("bio"),  rs.getDate("birth"), rs.getString("Email"), rs.getString("city")));
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return artists;
    }

    @Override
    public void save(Artist artist){
        String sql = "INSERT INTO artiste(bio,birth,Email,phone,city,website,socialMedia,isActive,name) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection con = ConnectionManager.getConnection()) {
              try (PreparedStatement ps = con.prepareStatement(sql)) {
                  ps.setString(1, artist.getBio());
                    ps.setDate(2, artist.getBirthYear());
                    ps.setString(3, artist.getContactEmail());
                    ps.setString(4, artist.getPhone());
                    ps.setString(5, artist.getCity());
                    ps.setString(6, artist.getWebsite());
                    ps.setString(7, artist.getSocialMedia());
                    ps.setBoolean(8, artist.isActive());
                    ps.setString(9, artist.getName());
                ps.executeUpdate();
            }}catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Artist artist) {
        String sql="UPDATE artiste SET bio=?, birth=?, Email=?, phone=?, city=?, website=?, socialMedia=?, isActive=? WHERE name=?";
        try(Connection con = ConnectionManager.getConnection()){
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, artist.getBio());
                ps.setDate(2, artist.getBirthYear());
                ps.setString(3, artist.getContactEmail());
                ps.setString(4, artist.getPhone());
                ps.setString(5, artist.getCity());
                ps.setString(6, artist.getWebsite());
                ps.setString(7, artist.getSocialMedia());
                ps.setBoolean(8, artist.isActive());
                ps.setString(9, artist.getName());
                ps.executeUpdate();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // TODO: Implement UPDATE artist SET ... WHERE name = ?

    }

    @Override
    public void delete(String artistName) {
        String sql="delete from artiste WHERE name=?";
        try(Connection con = ConnectionManager.getConnection()){
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, artistName);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // TODO: Implement DELETE FROM artist WHERE name = ?

    }

    @Override
public List<Artist> findByCity(String city) {
    String sql = "SELECT * FROM artiste WHERE city = ?";
    try (Connection con = ConnectionManager.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, city);
        ResultSet rs = ps.executeQuery();
        List<Artist> artists = new ArrayList<>();
        while (rs.next()) {
            artists.add(new Artist(rs.getString("name"), rs.getString("bio"),
                                 rs.getDate("birth"), rs.getString("Email"),
                                 rs.getString("city")));
        }
        return artists;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
public Optional<Artist> findByName(String name) {
    String sql = "SELECT * FROM artiste WHERE name = ?";
    try (Connection con = ConnectionManager.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        Optional <Artist> artist = Optional.empty();
        if (rs.next()) {
            artist = Optional.of(new Artist(rs.getString("name"), rs.getString("bio"),
                    rs.getDate("birth"), rs.getString("Email"),
                    rs.getString("city")));
        }
        return artist;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    public List<Discipline> findAllDisciplines(){
        List<Discipline> disciplines = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection(); Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT name FROM discipline");
                while (rs.next()) {
                    disciplines.add(new Discipline(rs.getString("name")));
                }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return disciplines;
    }
}
