package com.project.artconnect.dao;

import com.project.artconnect.model.CommunityMember;
import com.project.artconnect.model.Review;

import java.util.List;
import java.util.Optional;

public interface CommunityMemberDao {
    Optional<CommunityMember> findById(Long id);

    List<CommunityMember> findAll();

    Optional<CommunityMember> findByName(String name);

    List<Review> findReviewByMember(CommunityMember member);
}
