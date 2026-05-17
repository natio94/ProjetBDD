package com.project.artconnect.ui;

import com.project.artconnect.model.Artwork;
import com.project.artconnect.model.Booking;
import com.project.artconnect.model.CommunityMember;
import com.project.artconnect.model.Review;
import com.project.artconnect.service.CommunityService;
import com.project.artconnect.service.WorkshopService;
import com.project.artconnect.util.ServiceProvider;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CommunityController {


    @FXML
    private TableView<CommunityMember> memberTable;
    @FXML
    private TableColumn<CommunityMember, String> nameColumn;
    @FXML
    private TableColumn<CommunityMember, String> emailColumn;
    @FXML
    private TableColumn<CommunityMember, String> cityColumn;
    @FXML
    private TableColumn<CommunityMember, String> membershipColumn;

    @FXML
    private TableView<Review> reviewTable;
    @FXML
    private TableColumn<Review, Integer> reviewRatingCol;
    @FXML
    private TableColumn<Review, String> reviewArtworkCol;
    @FXML
    private TableColumn<Review, String> reviewCommentCol;

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, String> bookingWorkshopCol;
    @FXML
    private TableColumn<Booking, String> bookingDateCol;
    @FXML
    private TableColumn<Booking, String> bookingStatusCol;

    private final CommunityService communityService = ServiceProvider.getCommunityService();
    private final WorkshopService workshopService = ServiceProvider.getWorkshopService();
    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        membershipColumn.setCellValueFactory(new PropertyValueFactory<>("membershipType"));

        reviewRatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        reviewArtworkCol.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getArtwork() != null ? cellData.getValue().getArtwork().getTitle() : "Unknown"));
        reviewCommentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));

        bookingStatusCol.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        bookingWorkshopCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getWorkshop().getTitle()));
        bookingDateCol.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));

        refreshData();
        memberTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        memberTable.getSelectionModel().selectedItemProperty().addListener((obs, oldMember, newMember) -> {
            if (newMember != null) {
                showMemberDetails(newMember);
            } else {
                clearMemberDetails();
            }
        });

    }

    private void refreshData() {
        memberTable.setItems(FXCollections.observableArrayList(communityService.getAllMembers()));
        clearMemberDetails();
    }

    private void clearMemberDetails() {
        bookingTable.setItems(FXCollections.observableArrayList());
        reviewTable.setItems(FXCollections.observableArrayList());
    }

    private void showMemberDetails(CommunityMember member) {
        List<Review> reviews = communityService.getReviewsByMember(member);
        List<Booking> bookings = workshopService.getBookingsByMember(member);
        bookingTable.setItems(FXCollections.observableArrayList(bookings));
        reviewTable.setItems(FXCollections.observableArrayList(reviews));
    }


}
