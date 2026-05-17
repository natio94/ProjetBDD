package com.project.artconnect.ui;

import com.project.artconnect.model.Artist;
import com.project.artconnect.model.Artwork;
import com.project.artconnect.service.ArtistService;
import com.project.artconnect.service.ArtworkService;
import com.project.artconnect.util.ServiceProvider;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ArtworkAddTabController {
    ArtistService artistService = ServiceProvider.getArtistService();
    ArtworkService artworkService = ServiceProvider.getArtworkService();
    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<Artist> artistCombo;
    @FXML
    private TextField yearField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField dimensionsField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField priceField;

    public void initialize() {
        if (artistCombo != null) {
            List<Artist> artists = artistService.getAllArtists();
            artistCombo.setItems(FXCollections.observableArrayList(artists));
            artistCombo.setCellFactory(lv -> new ListCell<>() {
                @Override protected void updateItem(Artist item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getName());
                }
            });
            artistCombo.setButtonCell(new ListCell<>() {
                @Override protected void updateItem(Artist item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getName());
                }
            });
        }
    }

    public void handleAdd() {
        try{
        String title = titleField.getText().trim();
        if (title.isEmpty()) {
            alertError("Title cannot be empty.");
            return;
        }
        Artist artist = artistService.getArtistByName(artistCombo.getValue() != null ? artistCombo.getValue().toString() : "").orElse(null);
        if (artist == null) {
            alertError("Artist cannot be empty.");
            return;
        }
        int year=0;
        String yearStr = yearField.getText();
            if (!yearStr.isEmpty()) {
                try {
                    year = Integer.parseInt(yearStr);
                } catch (NumberFormatException e) {
                    alertError("Creation year must be a number.");
                    return;
                }
            }
            String type = typeField.getText().trim();
            if (type.isEmpty()) {
                alertError("Type cannot be empty.");
                return;
            }
        double price=0;
            String priceStr = priceField.getText();
            if (!priceStr.isEmpty()) {
                try {
                    price = Double.parseDouble(priceStr);

                }catch (NumberFormatException e) {
                    alertError("Price must be a number.");
                    return;
                }
            }
        String description = descriptionArea.getText().trim();
            if (description.isEmpty()) {
                alertError("Description cannot be empty.");
                return;
            }
        Artwork artwork = new Artwork(title,year,type,price,artist);
        artwork.setDescription(description);
        artwork.setDimensions(dimensionsField.getText().trim());

        artworkService.createArtwork(artwork);
        }
        catch (Exception e){
            if (e.getMessage().contains("MysqlDataTruncation")){
                alertError("Year must be a valid number and not too large.");
                return;
            }
            if (e.getMessage().equals("java.sql.SQLException: Artwork creation year older than artist birth year")) {
                alertError("Artwork creation year older than artist birth year");
                return;
            }

            System.out.println(e);
                alertError("Failed to add artwork: " + e.getMessage());
                return;
        }
        titleField.clear();
        artistCombo.setValue(null);
        yearField.clear();
        dimensionsField.clear();
        descriptionArea.clear();
        priceField.clear();
        typeField.clear();
    }
public void alertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
}
}

