package com.project.artconnect.service.impl;

import com.project.artconnect.model.Artist;
import com.project.artconnect.model.Discipline;
import com.project.artconnect.persistence.JdbcArtistDao;
import com.project.artconnect.service.ArtistService;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class JdbcArtistService implements ArtistService {
    private final JdbcArtistDao artistDao;
    private final Map<String, Discipline> disciplines = new LinkedHashMap<>();
    public JdbcArtistService()   {
        this.artistDao = new JdbcArtistDao();
    }

    @Override
    public List<Artist> getAllArtists() {
        try {
            return artistDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des artistes", e);
        }
    }

    @Override
    public Optional<Artist> getArtistByName(String name) {

            return artistDao.findByName(name);


    }

    @Override
    public void createArtist(Artist artist) {
        try {
            artistDao.save(artist);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de l'artiste", e);
        }
    }

    @Override
    public void updateArtist(Artist artist) {
            artistDao.update(artist);

    }

    @Override
    public void deleteArtist(String name) {
        artistDao.delete(name);

    }

    @Override
    public List<Discipline> getAllDisciplines() {

        return new ArrayList<>(disciplines.values());
    }

    @Override
    public List<Artist> searchArtists(String query, String disciplineName, String city) {
        List<Artist> candidates;
        try {
            candidates = (city != null && !city.trim().isEmpty()) ?
                    artistDao.findByCity(city) : artistDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche", e);
        }
        return candidates.stream()
                .filter(a -> query == null || query.trim().isEmpty() ||
                        a.getName().toLowerCase().contains(query.toLowerCase()))
                .filter(a -> disciplineName == null ||
                        a.getDisciplines().stream().anyMatch(d -> d.getName().equals(disciplineName)))
                .collect(Collectors.toList());
    }
}
