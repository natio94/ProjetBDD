package com.project.artconnect.service.impl;

import com.project.artconnect.model.Artist;
import com.project.artconnect.model.Discipline;
import com.project.artconnect.dao.impl.persistence.JdbcArtistDao;
import com.project.artconnect.service.ArtistService;

import java.util.*;
import java.util.stream.Collectors;

public class JdbcArtistService implements ArtistService {
    private final JdbcArtistDao artistDao;

    public JdbcArtistService()   {
        this.artistDao = new JdbcArtistDao();
    }

    @Override
    public List<Artist> getAllArtists() {
            return artistDao.findAll();

    }

    @Override
    public Optional<Artist> getArtistByName(String name) {

            return artistDao.findByName(name);


    }

    @Override
    public void createArtist(Artist artist) {

            artistDao.save(artist);

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

        return artistDao.findAllDisciplines();
    }

    @Override
    public List<Artist> searchArtists(String query, String disciplineName, String city) {
        List<Artist> candidates;
            candidates = (city != null && !city.trim().isEmpty()) ?
                    artistDao.findByCity(city) : artistDao.findAll();
        return candidates.stream()
                .filter(a -> query == null || query.trim().isEmpty() ||
                        a.getName().toLowerCase().contains(query.toLowerCase()))
                .filter(a -> disciplineName == null ||
                        a.getDisciplines().stream().anyMatch(d -> d.getName().equals(disciplineName)))
                .collect(Collectors.toList());
    }
}
