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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farm2/farmer_login.fxml"));//get the resources
            Scene scene = new Scene(loader.load());//Prepping the scene for show

            primaryStage.setTitle("Farm Management App");//Set the title
            primaryStage.setScene(scene);//telling the computer which "curtains to open"
            primaryStage.show();//You are shown the tab

            FarmerLoginController farmerLoginController = loader.getController(); //The actual controller of the tab is not this one, but the farmerLoginController
            farmerLoginController.setPrimaryStage(primaryStage);//
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        launch();
    }
}
/*

  */