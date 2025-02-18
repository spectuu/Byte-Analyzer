/*

Program: Antivirus
Authors: Santiago Martinez and Miguel Moreno Rojas
Date: 27/02/2025

This program is an antivirus that allows the user to analyze files and detect viruses.
The user can add a file to the program and analyze it to check if it contains any viruses.
The program uses a set of predefined virus signatures to detect viruses in the files.
If a virus is detected, the program displays the name of the virus.
The user can also save new virus signatures to the program for future use.

VirusData class: This class contains the data of a virus.

 */

package spectu.antivirus.resource.data;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VirusData {

    private String name;
    private byte[] bytes;

    public VirusData(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    // Getters
    public String getName() {
        return name;
    }

    public byte[] getBytes() {
        return bytes;
    }

}
