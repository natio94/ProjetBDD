package com.project.artconnect.ui;

import com.project.artconnect.model.CommunityMember;
import com.project.artconnect.model.Workshop;
import com.project.artconnect.service.CommunityService;
import com.project.artconnect.service.WorkshopService;
import com.project.artconnect.util.ServiceProvider;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class WorkshopController {
    @FXML
    private TableView<Workshop> workshopTable;
    @FXML
    private TableColumn<Workshop, String> titleColumn;
    @FXML
    private TableColumn<Workshop, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<Workshop, String> instructorColumn;
    @FXML
    private TableColumn<Workshop, Double> priceColumn;
    @FXML
    private TableColumn<Workshop, String> levelColumn;
    @FXML 
    private TableColumn<Workshop, Integer> maxParticipantsColumn;
    @FXML 
    private TableColumn<Workshop, String> locationColumn;
    @FXML
    private TableColumn<Workshop, Integer> remainingPlacesColumn;
    @FXML
    private TableColumn<Workshop, Integer> durationColumn;

    @FXML private ComboBox<CommunityMember> memberCombo;
    @FXML private Button bookButton;
    @FXML private Label bookingStatusLabel;
    private final WorkshopService  workshopService  = ServiceProvider.getWorkshopService();
    private final CommunityService communityService = ServiceProvider.getCommunityService();

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        remainingPlacesColumn.setCellValueFactory(new PropertyValueFactory<>("remainingPlaces"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("durationMinutes"));
        instructorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getInstructor() != null ? cellData.getValue().getInstructor().getName()
                        : "Unknown"));

        if (maxParticipantsColumn != null)
            maxParticipantsColumn.setCellValueFactory(new PropertyValueFactory<>("maxParticipants"));
        if (locationColumn != null)
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        refreshTable();

        // Populate member combo if present
        if (memberCombo != null) {
            List<CommunityMember> members = communityService.getAllMembers();
            memberCombo.setItems(FXCollections.observableArrayList(members));
            memberCombo.setCellFactory(lv -> new ListCell<>() {
                @Override protected void updateItem(CommunityMember item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getName());
                }
            });
            memberCombo.setButtonCell(new ListCell<>() {
                @Override protected void updateItem(CommunityMember item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getName());
                }
            });
        }
    }

    @FXML
    private void handleBook() {
        if (memberCombo == null || bookingStatusLabel == null) return;
        Workshop selected = workshopTable.getSelectionModel().getSelectedItem();
        CommunityMember member = memberCombo.getValue();
        if (selected == null) {
            bookingStatusLabel.setText("⚠ Sélectionnez un atelier.");
            return;
        }
        if (member == null) {
            bookingStatusLabel.setText("⚠ Sélectionnez un membre.");
            return;
        }
        try {
            workshopService.bookWorkshop(selected,member);
            bookingStatusLabel.setText("✓ Réservation confirmée pour " + member.getName());
        } catch (RuntimeException e) {
            if (Objects.equals(e.getMessage(), "java.sql.SQLException: No more place available")){
                bookingStatusLabel.setText("Plus de place disponible pour cet atelier.");

            }else{
                bookingStatusLabel.setText("✗ " + e.getMessage());
            }
        }
        refreshTable();
    }

    private void refreshTable() {
        workshopTable.setItems(FXCollections.observableArrayList(workshopService.getAllWorkshops()));

    }
}
