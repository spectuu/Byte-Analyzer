/*

Program: Antivirus
Authors: Santiago Martinez and Miguel Moreno Rojas
Date: 27/02/2025

This program is an antivirus that allows the user to analyze files and detect viruses.
The user can add a file to the program and analyze it to check if it contains any viruses.
The program uses a set of predefined virus signatures to detect viruses in the files.
If a virus is detected, the program displays the name of the virus.
The user can also save new virus signatures to the program for future use.

ResourceManager class: This class contains methods to manage the resources of the program.

 */

package spectu.antivirus.resource;

import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.SneakyThrows;
import spectu.antivirus.resource.data.VirusData;
import spectu.antivirus.resource.json.VirusJson;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ResourceManager {

    @Getter
    private static ResourceManager instance;

    private final VirusJson virusJson;

    // Constructor
    public ResourceManager() {

        instance = this;

        this.virusJson = getJson("virus.json", VirusJson.class);

    }

    //method to initialize the ResourceManager
    public void init() {
        System.out.println("Resource Manager Initialized!");
        System.out.println("Virus Data: " + virusJson.getVirusData().size());
    }

    //method to get a JSON file
    @SneakyThrows
    private <T> T getJson(String jsonFile, Class<T> clazz) {
        return new GsonBuilder().create().fromJson(getReader(jsonFile), clazz);
    }

    //method to get a reader
    @SneakyThrows
    private Reader getReader(String file) {
        return new InputStreamReader(getInputStream(file), StandardCharsets.UTF_8);
    }

    //method to get an input stream
    @SneakyThrows
    private InputStream getInputStream(String file) {
        return this.getClass().getClassLoader().getResourceAsStream(file);
    }

    //method to add a virus
    @SneakyThrows
    public void addVirus(String name, byte[] bytes) {

        List<VirusData> virusList = new ArrayList<>(virusJson.getVirusData());

        if (virusList.stream().anyMatch(virus -> virus.getName().equalsIgnoreCase(name)))
            return;

        virusList.add(new VirusData(name, bytes));

        virusJson.setVirusData(virusList);

        File file = new File("src/main/resources/virus.json");

        try (Writer writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(virusJson, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //method to get the instance of the VirusJson
    public VirusJson getVirusJson() {
        return instance.virusJson;
    }

    //method to get the instance of the ResourceManager
    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

}
