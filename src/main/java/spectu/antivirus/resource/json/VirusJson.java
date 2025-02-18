/*

Program: Antivirus
Authors: Santiago Martinez and Miguel Moreno Rojas
Date: 27/02/2025

This program is an antivirus that allows the user to analyze files and detect viruses.
The user can add a file to the program and analyze it to check if it contains any viruses.
The program uses a set of predefined virus signatures to detect viruses in the files.
If a virus is detected, the program displays the name of the virus.
The user can also save new virus signatures to the program for future use.

VirusJson class: This class contains the data of a virus in JSON format.

 */

package spectu.antivirus.resource.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import spectu.antivirus.resource.data.VirusData;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VirusJson {

    private VirusData[] virusData;

    // Getters
    public List<VirusData> getVirusData() {
        return List.of(virusData);
    }

    // Setters
    public void setVirusData(List<VirusData> virusData) {
        this.virusData = virusData.toArray(new VirusData[0]);
    }

}
