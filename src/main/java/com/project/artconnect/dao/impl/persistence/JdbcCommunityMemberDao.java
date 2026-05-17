package com.project.artconnect.dao.impl.persistence;

import com.project.artconnect.dao.CommunityMemberDao;
import com.project.artconnect.model.CommunityMember;
import com.project.artconnect.model.Review;
import com.project.artconnect.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcCommunityMemberDao implements CommunityMemberDao {
    JdbcArtworkDao artworkDao = new JdbcArtworkDao();

    private CommunityMember mapRow(ResultSet rs) throws SQLException {
        CommunityMember member = new CommunityMember(rs.getString("name"), rs.getString("email"));
        member.setCity(rs.getString("city"));
        member.setMembershipType(rs.getString("membershipType"));
        return member;
    }

    @Override
    public Optional<CommunityMember> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<CommunityMember> findAll() {
        String sql = "select * from communitymember";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            var rs = ps.executeQuery();
            List<CommunityMember> members = new java.util.ArrayList<>();
            while (rs.next()) {
                members.add(mapRow(rs));
            }
            return members;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CommunityMember> findByName(String name) {
        String sql = "select * from communitymember where name = ?";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Review> findReviewByMember(CommunityMember member){
        String sql = "select r.*, a.Title from review r join communitymember c on r.idMember = c.idMember join artwork a on a.id_artworks = r.id_artworks where c.name = ?";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, member.getName());
            var rs = ps.executeQuery();
            List<Review> reviews = new java.util.ArrayList<>();
            while (rs.next()) {
                reviews.add(new Review(member,artworkDao.findByTitle(rs.getString("Title")).get(), rs.getInt("rating"),rs.getString("comment")));
            }
            return reviews;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
