/*

Program: Antivirus
Authors: Santiago Martinez and Miguel Moreno Rojas
Date: 27/02/2025

This program is an antivirus that allows the user to analyze files and detect viruses.
The user can add a file to the program and analyze it to check if it contains any viruses.
The program uses a set of predefined virus signatures to detect viruses in the files.
If a virus is detected, the program displays the name of the virus.
The user can also save new virus signatures to the program for future use.

AntivirusController class: This class is the UI controller for the Antivirus application.

 */

package spectu.antivirus;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import lombok.SneakyThrows;
import spectu.antivirus.component.FileComponent;
import spectu.antivirus.resource.ResourceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class AntivirusController {

    @FXML
    private Button addFile;

    @FXML
    private Button analyze;

    @FXML
    private Button saveVirus;

    @FXML
    private Text virusName;

    @FXML
    private Text status;

    @FXML
    private TextFlow fileBytes;

    @FXML
    private TextField virusSequence;

    @FXML
    private TextField saveVirusName;

    @FXML
    private Label fileName;

    private byte[] fileBytesArray;

    private byte[] virusBytesArray;

    // Add file to the program
    @FXML
    @SneakyThrows
    public void onAddFile() {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(AntivirusApp.antivirusStage);

        if (file == null)
            return;

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);

            fileBytesArray = buffer;

        } catch (IOException e) {
            e.printStackTrace();
        }

        fileName.setText(file.getName());

        Text text = new Text(Arrays.toString(fileBytesArray));

        fileBytes.getChildren().add(text);

    }

    // Analyze the file
    @FXML
    @SneakyThrows
    public void onAnalyze() {

        if (fileBytesArray == null)
            return;

        List<String> virusDetected = FileComponent.getInstance().analyzeBytes(fileBytesArray);

        String sequence = virusSequence.getText();
        String[] sequenceArray = sequence.split(",");

        if (sequenceArray.length >= 3) {

            byte[] bytes = FileComponent.getInstance().mapNewSequence(sequenceArray);
            virusDetected = FileComponent.getInstance().analyzeBytes(fileBytesArray, bytes);

            if (virusDetected.isEmpty()) {
                virusDetected = FileComponent.getInstance().analyzeBytes(fileBytesArray);
            } else {
                virusBytesArray = bytes;
            }

        }

        if (virusDetected.isEmpty()) {

            status.setText("File is clean!");
            virusName.setText("NaN");

        } else if (virusDetected.contains("Virus Detected")) {

            status.setText("File is infected!");
            virusName.setText("new virus");

        } else {

            status.setText("File is infected!");
            virusName.setText(String.join(", ", virusDetected));

        }

    }

    // Save the virus
    @FXML
    public void onSave() {

        if (virusBytesArray == null || saveVirusName.getText().isEmpty())
            return;

        ResourceManager.getInstance().addVirus(saveVirusName.getText(), virusBytesArray);

    }

    // Initialize the controller
    @FXML
    public void initialize() {
        validatedFormat();
    }


    // Validate the format of the virus sequence
    @FXML
    public void validatedFormat() {

        Pattern pattern = Pattern.compile("-?[0-9]*([,]-?[0-9]*)*");

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();

            if (newText.matches("-?") || newText.matches("-?[0-9]+[,]?") || pattern.matcher(newText).matches()) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        virusSequence.setTextFormatter(textFormatter);
    }


}