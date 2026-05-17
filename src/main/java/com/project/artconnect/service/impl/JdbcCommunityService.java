package com.project.artconnect.service.impl;

import com.project.artconnect.dao.impl.CommunityMemberDao;
import com.project.artconnect.model.CommunityMember;
import com.project.artconnect.model.Review;
import com.project.artconnect.persistence.JdbcCommunityMemberDao;
import com.project.artconnect.service.CommunityService;

import java.util.List;
import java.util.Optional;

public class JdbcCommunityService implements CommunityService {
    CommunityMemberDao memberDao = new JdbcCommunityMemberDao();
    @Override
    public List<CommunityMember> getAllMembers() {
        return memberDao.findAll();
    }

    @Override
    public Optional<CommunityMember> getMemberByName(String name) {
        return memberDao.findByName(name);
    }

    @Override
    public List<Review> getReviewsByMember(CommunityMember member) {
        return memberDao.findReviewByMember(member);
    }
}
