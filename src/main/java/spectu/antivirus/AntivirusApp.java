/*

Program: Antivirus
Authors: Santiago Martinez and Miguel Moreno Rojas
Date: 27/02/2025

This program is an antivirus that allows the user to analyze files and detect viruses.
The user can add a file to the program and analyze it to check if it contains any viruses.
The program uses a set of predefined virus signatures to detect viruses in the files.
If a virus is detected, the program displays the name of the virus.
The user can also save new virus signatures to the program for future use.

AntivirusApp class: This class contains the main method to start the application.

 */

package spectu.antivirus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import spectu.antivirus.component.FileComponent;
import spectu.antivirus.resource.ResourceManager;

import java.io.File;
import java.io.IOException;

public class AntivirusApp extends Application {

    public static Stage antivirusStage;

    // method to start the application stage
    @Override
    public void start(Stage stage) throws IOException {

        antivirusStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(AntivirusApp.class.getResource("/antivirus.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 788, 578);

        stage.setTitle("Antivirus [By: @Spectu]");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }

    // method to start the components
    public static void start() {

        ResourceManager resourceManager = new ResourceManager();
        FileComponent fileComponent = new FileComponent();

        fileComponent.init();
        resourceManager.init();

    }

    // main method
    public static void main(String[] args) {
        start();
        launch();
    }

}