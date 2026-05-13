package com.project.artconnect;

import com.project.artconnect.persistence.JdbcArtistDao;
import com.project.artconnect.persistence.JdbcArtworkDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/artconnect/ui/MainView.fxml"));
        Scene scene = new Scene(loader.load(), 1200, 800);
        stage.setTitle("ArtConnect Pro - Local Art Community Platform");
        stage.setScene(scene);
//        JdbcArtistDao artistDao = new JdbcArtistDao();
//        JdbcArtworkDao artworkDao = new JdbcArtworkDao();
//        System.out.println(artworkDao.findByArtistName("Lucas Moreau"));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    } 
}
