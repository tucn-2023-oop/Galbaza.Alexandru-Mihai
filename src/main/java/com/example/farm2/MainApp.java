package com.example.farm2;// MainApp.java

import com.example.farm2.Controllers.FarmerLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farm2/farmer_login.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setTitle("Farm Management App");
            primaryStage.setScene(scene);
            primaryStage.show();

            FarmerLoginController farmerLoginController = loader.getController();
            farmerLoginController.setPrimaryStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
/*

  */