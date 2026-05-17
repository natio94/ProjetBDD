package com.project.artconnect.dao;

import com.project.artconnect.model.Artist;

import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object for Artist entity.
 */
public interface ArtistDao {
    List<Artist> findAll() throws SQLException;

    void save(Artist artist) throws SQLException;

    void update(Artist artist);

    void delete(String artistName);

    List<Artist> findByCity(String city);
}
