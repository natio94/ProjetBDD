package com.project.artconnect.service.impl;

import com.project.artconnect.dao.WorkshopDao;
import com.project.artconnect.model.Booking;
import com.project.artconnect.model.CommunityMember;
import com.project.artconnect.model.Workshop;
import com.project.artconnect.dao.impl.persistence.JdbcWorkshopDao;
import com.project.artconnect.service.WorkshopService;

import java.util.List;
import java.util.Optional;

public class JdbcWorkshopService implements WorkshopService {
    WorkshopDao workshopDao=new JdbcWorkshopDao();

    @Override
    public List<Workshop> getAllWorkshops() {
        return workshopDao.findAll();
    }

    @Override
    public Optional<Workshop> getWorkshopByTitle(String title) {
        return workshopDao.findByTitle(title);
    }

    @Override
    public void bookWorkshop(Workshop workshop, CommunityMember member) {
            workshopDao.book(member, workshop);
    }

    @Override
    public List<Booking> getBookingsByMember(CommunityMember member) {
        return workshopDao.findBookingByMember(member);
    }

    @Override
    public int getRemainingPlaces(String workshopTitle){return workshopDao.findRemainingPlaces(workshopTitle);}
}
