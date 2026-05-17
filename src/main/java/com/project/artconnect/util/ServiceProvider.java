package com.project.artconnect.util;

import com.project.artconnect.service.*;
import com.project.artconnect.service.impl.*;

/**
 * Service Provider to manage singleton instances of services and handle their
 * initialization.
 */
public class ServiceProvider {
    private static final InMemoryArtistService artistService = new InMemoryArtistService();
    private static final InMemoryArtworkService artworkService = new InMemoryArtworkService();
    private static final InMemoryGalleryService galleryService = new InMemoryGalleryService();
    private static final InMemoryWorkshopService workshopService = new InMemoryWorkshopService();
    private static final InMemoryCommunityService communityService = new InMemoryCommunityService();
    private static final JdbcArtistService jdbcartistService = new JdbcArtistService();
    private static final JdbcArtworkService jdbcArtworkService = new JdbcArtworkService();
    private static final JdbcGalleryService jdbcGalleryService = new JdbcGalleryService();
    private static final JdbcWorkshopService jdbcWorkshopService = new JdbcWorkshopService();
    private static final JdbcCommunityService jdbcCommunityService = new JdbcCommunityService();

    static {
        // Initialize services with their dependencies
        artworkService.initData(artistService);
        galleryService.initData(artworkService);
        workshopService.initData(artistService);
        communityService.initData(artworkService);
    }

    public static ArtistService getArtistService() {
        return jdbcartistService;
    }

    public static ArtworkService getArtworkService() {
        return jdbcArtworkService;
    }

    public static GalleryService getGalleryService() {
        return jdbcGalleryService;
    }

    public static WorkshopService getWorkshopService() {
        return jdbcWorkshopService;
    }

    public static CommunityService getCommunityService() {
        return jdbcCommunityService;
    }

}
