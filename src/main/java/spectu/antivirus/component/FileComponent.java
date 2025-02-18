/*

Program: Antivirus
Authors: Santiago Martinez and Miguel Moreno Rojas
Date: 27/02/2025

This program is an antivirus that allows the user to analyze files and detect viruses.
The user can add a file to the program and analyze it to check if it contains any viruses.
The program uses a set of predefined virus signatures to detect viruses in the files.
If a virus is detected, the program displays the name of the virus.
The user can also save new virus signatures to the program for future use.

FileComponent class: This class contains methods to analyze files and detect viruses.

 */

package spectu.antivirus.component;

import lombok.Getter;
import spectu.antivirus.resource.ResourceManager;
import spectu.antivirus.resource.data.VirusData;
import spectu.antivirus.resource.json.VirusJson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileComponent {

    @Getter
    private static FileComponent instance;

    // Constructor
    public FileComponent(){

        instance = this;

    }

    // method to initialize the FileComponent
    public void init() {
        System.out.println("File Component Initialized!");
    }

    // method to analyze a file and detect viruses
    public List<String> analyzeBytes(byte[] fileBytes) {

        VirusJson virusJson = ResourceManager.getInstance().getVirusJson();
        List<VirusData> virusDataList = virusJson.getVirusData();
        List<String> detectedViruses = new ArrayList<>();

        for (VirusData virusData : virusDataList) {
            byte[] virusBytes = virusData.getBytes();
            if (containsSequence(fileBytes, virusBytes)) {
                detectedViruses.add(virusData.getName());
            }
        }

        return detectedViruses;
    }

    // method to analyze a file and detect new viruses
    public List<String> analyzeBytes(byte[] fileBytes, byte[] virusBytes) {

        List<String> detectedViruses = new ArrayList<>();

        if (containsSequence(fileBytes, virusBytes)) {
            detectedViruses.add("New Virus");
        }

        return detectedViruses;
    }

    // method to analyze the sequence of bytes in a file
    private boolean containsSequence(byte[] fileBytes, byte[] virusBytes) {
        int fileLength = fileBytes.length;
        int virusLength = virusBytes.length;

        if (virusLength > fileLength) {
            return false;
        }

        for (int i = 0; i <= fileLength - virusLength; i++) {
            boolean match = true;
            for (int j = 0; j < virusLength; j++) {
                if (fileBytes[i + j] != virusBytes[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }

        return false;
    }

    // method to map a sequence of strings to a new sequence of bytes
    public byte[] mapNewSequence(String[] sequenceArray) {

        byte[] newSequence = new byte[sequenceArray.length];

        for (int i = 0; i < sequenceArray.length; i++) {
            newSequence[i] = Byte.parseByte(sequenceArray[i]);
        }

        return newSequence;
    }

    // method to get the instance of the FileComponent
    public static FileComponent getInstance() {
        if (instance == null) {
            instance = new FileComponent();
        }
        return instance;
    }

}
