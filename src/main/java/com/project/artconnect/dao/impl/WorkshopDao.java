package com.project.artconnect.dao.impl;

import com.project.artconnect.model.Booking;
import com.project.artconnect.model.CommunityMember;
import com.project.artconnect.model.Workshop;

import java.util.List;
import java.util.Optional;

public interface WorkshopDao {
    Optional<Workshop> findById(Long id);

    Optional<Workshop> findByTitle(String title);

    List<Workshop> findAll();

    void book(CommunityMember member, Workshop workshop);

    List<Booking> findBookingByMember(CommunityMember member);

    int findRemainingPlaces(String title);
}
