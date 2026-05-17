package com.project.artconnect.service.impl;

import com.project.artconnect.dao.impl.GalleryDao;
import com.project.artconnect.model.Exhibition;
import com.project.artconnect.model.Gallery;
import com.project.artconnect.persistence.JdbcExhibitionDao;
import com.project.artconnect.persistence.JdbcGalleryDao;
import com.project.artconnect.service.GalleryService;

import java.util.List;
import java.util.Optional;

public class JdbcGalleryService implements GalleryService {
    JdbcGalleryDao galleryDao = new JdbcGalleryDao();
    JdbcExhibitionDao exhibitionDao = new JdbcExhibitionDao();
    @Override
    public List<Gallery> getAllGalleries() {
        return galleryDao.findAll();
    }

    @Override
    public Optional<Gallery> getGalleryByName(String name) {
        return galleryDao.findByName(name);
    }

    @Override
    public List<Exhibition> getExhibitionsByGallery(Gallery gallery) {
        return List.of();
    }

    public List<Exhibition> getAllExhibitions() {
        return exhibitionDao.findAll();
    }
}
