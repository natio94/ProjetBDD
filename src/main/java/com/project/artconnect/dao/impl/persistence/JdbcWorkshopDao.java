package com.project.artconnect.dao.impl.persistence;

import com.project.artconnect.dao.WorkshopDao;
import com.project.artconnect.model.Artist;
import com.project.artconnect.model.Booking;
import com.project.artconnect.model.CommunityMember;
import com.project.artconnect.model.Workshop;
import com.project.artconnect.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcWorkshopDao implements WorkshopDao {
    public Workshop mapRowWorkshop(ResultSet rs) throws SQLException {
        Artist artist=new Artist(rs.getString("name"), rs.getString("bio"), rs.getDate("birth"), rs.getString("Email"), rs.getString("city"));
        Workshop workshop=new Workshop(rs.getString("title"),  rs.getDate("date").toLocalDate().atStartOfDay(),artist,rs.getDouble("price"));
        workshop.setLocation(rs.getString("location"));
        workshop.setDescription(rs.getString("description"));
        workshop.setLevel(rs.getString("level"));
        workshop.setDurationMinutes(rs.getInt("duration"));
        workshop.setRemainingPlaces(findRemainingPlaces(workshop.getTitle()));
        return workshop;
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        String sql = "select * from workshop w join artiste a on w.id_artiste=a.id_artiste where idWorkshop = ?";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRowWorkshop(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Workshop> findByTitle(String title) {
        String sql = "select * from workshop w join artiste a on w.id_artiste=a.id_artiste where title = ?";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRowWorkshop(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Workshop> findAll() {
        String sql = "select * from workshop w join artiste a on w.id_artiste=a.id_artiste";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            var rs = ps.executeQuery();
            List<Workshop> workshops = new java.util.ArrayList<>();
            while (rs.next()) {
                workshops.add(mapRowWorkshop(rs));
            }
            return workshops;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void book(CommunityMember member, Workshop workshop) {
        String sqlIdWorkshop="select idWorkshop from workshop where title = ?";
        String sqlIdMember="select idMember from communitymember where name = ?";
        String sqlBook = "call InscrireMembreWorkshop(?,?)";
        try (Connection con = ConnectionManager.getConnection();) {

            PreparedStatement ps = con.prepareStatement(sqlIdWorkshop);
            ps.setString(1, workshop.getTitle());
            ResultSet rs = ps.executeQuery();
            int idWorkshop=0;
            if (rs.next()) {
                idWorkshop = rs.getInt("idWorkshop");
            }
            PreparedStatement ps2 = con.prepareStatement(sqlIdMember);
            ps2.setString(1,member.getName());
            ResultSet rs2 = ps2.executeQuery();
            int idMember=0;
            if (rs2.next()) {
                idMember = rs2.getInt("idMember");
            }
            PreparedStatement ps3 = con.prepareStatement(sqlBook);
            ps3.setInt(1, idMember);
            ps3.setInt(2, idWorkshop);
            ps3.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> findBookingByMember(CommunityMember member) {
        String sql = "select b.*, w.title from booking b join communitymember c on b.idMember = c.idMember join workshop w on w.idWorkshop = b.idWorkshop where c.name = ?";
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, member.getName());
            var rs = ps.executeQuery();
            List<Booking> bookings = new java.util.ArrayList<>();
            while (rs.next()) {
                Workshop workshop=findByTitle(rs.getString("title")).get();
                Booking booking=new Booking(workshop,member);
                booking.setBookingDate(rs.getDate("Booking_Date").toLocalDate().atStartOfDay());
                booking.setPaymentStatus(rs.getString("paymentStatus"));
                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findRemainingPlaces(String title) {
        String sqlWorkshop="select idWorkshop from workshop where title = ?";
        String sqlPlaces="select PlacesRestantes(?) as nbPlaceRestantes";
        int nbPlacesRestantes=0;
        int idWorkshop=0;
        try(Connection conn = ConnectionManager.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement(sqlWorkshop)){
                ps.setString(1, title);
               ResultSet rs=ps.executeQuery();
               if(rs.next()){idWorkshop=rs.getInt("idWorkshop");}

            }
            try(PreparedStatement ps = conn.prepareStatement(sqlPlaces)){
                ps.setInt(1, idWorkshop);
                ResultSet rs2=ps.executeQuery();
                if(rs2.next()){nbPlacesRestantes=rs2.getInt("nbPlaceRestantes");}
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nbPlacesRestantes;
    }
}
