package pathgenerator.util;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlMappingBuilder;
import com.amihaiemil.eoyaml.YamlStream;

import org.junit.Test;
import org.simpleyaml.configuration.file.YamlFile;

import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.PathGenerator;
import pathgenerator.trajectory.WaypointSequence;

/**
 * This Tests the Functionality fot the FileGeneration
 * 
 * @author Nicholas Blackburn
 */
// TODO: acutally think file generator output testing
public class FileGenerationsTests {

    @Test
    public void Test_Yaml_Output_Generation() {
        // Arrange
        String key = "test";
        var layoutMap = Yaml.createYamlMappingBuilder().add(key, Double.toString(1)).build();
        
        // Act
        var getyaml = layoutMap.string(key);

        // Assert
        assertEquals(Double.toString(1),getyaml);
    }
}
