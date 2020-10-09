package pathgenerator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlMappingBuilder;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlPrinter;
import com.amihaiemil.eoyaml.YamlSequence;
import com.amihaiemil.eoyaml.YamlStream;
import com.amihaiemil.eoyaml.YamlStreamBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javafx.stage.Stage;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.Trajectory;
import pathgenerator.trajectory.WaypointSequence;
import pathgenerator.trajectory.io.JavaSerializer;
import pathgenerator.trajectory.io.TextFileSerializer;

import pathgenerator.Main;

/**
 * this class is to Generate java and txt file for paths that are Generated
 * 
 * @author nicholas blackburn
 * @param pathName location of file path
 * @param path     path for path generation
 */
// TODO: generate output to csv format
public class FileGeneration {
	private Trajectory trajectory = new Trajectory();
	private WaypointSequence sequence = new WaypointSequence();
	private WaypointTableData tableData = new WaypointTableData();
	
	private static String yamlBaseKey = "Waypoint.";

	/**
	 * writes a .path file filled with generated path
	 * 
	 * @param Directory
	 * @param fileName
	 * @param path
	 * @param sequence
	 * @throws IOException
	 */
	public void writeFiles(String comments,final String Directory, final String fileName, final Path path){

		int id = 0;
		File file = new File(Directory+fileName+".path");
		for (id =0; id<path.getRightWheelTrajectory().getNumSegments(); ++id){
		final YamlMapping yaml = Yaml.createYamlMappingBuilder()
				.add(yamlBaseKey+id, "")
				.add("pos",Double.toString(path.getLeftWheelTrajectory().getSegment(id).pos)+","+Double.toString(path.getRightWheelTrajectory().getSegment(id).pos))
				.add("vel",Double.toString(path.getLeftWheelTrajectory().getSegment(id).vel)+","+Double.toString(path.getRightWheelTrajectory().getSegment(id).vel))
				.add("acc",Double.toString(path.getLeftWheelTrajectory().getSegment(id).acc)+","+Double.toString(path.getRightWheelTrajectory().getSegment(id).acc))
				.add("jerk",Double.toString(path.getLeftWheelTrajectory().getSegment(id).jerk)+","+Double.toString(path.getRightWheelTrajectory().getSegment(id).jerk))
				.add("heading",Double.toString(path.getLeftWheelTrajectory().getSegment(id).heading)+","+Double.toString(path.getRightWheelTrajectory().getSegment(id).heading))
				.add("dt",Double.toString(path.getLeftWheelTrajectory().getSegment(id).dt)+","+Double.toString(path.getRightWheelTrajectory().getSegment(id).dt))
				.add("x",Double.toString(path.getLeftWheelTrajectory().getSegment(id).x)+","+Double.toString(path.getRightWheelTrajectory().getSegment(id).x))
				.add("y",Double.toString(path.getLeftWheelTrajectory().getSegment(id).y)+","+Double.toString(path.getRightWheelTrajectory().getSegment(id).y))
			
				.build(comments);
	
		
		final Collection<YamlNode> documents = yaml.values();
		Main.logger.warning("yaml data"+ documents.stream().toArray().length);
		try {

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			final YamlPrinter printer = Yaml.createYamlPrinter(new FileWriter(file));
			
			printer.print(yaml);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	}
	
		

	

	/**
	 * Creates Java class file with path values in it
	 * 
	 * @param path
	 * @param data
	 * @return
	 */
	private static boolean writeFile(final String path, final String data) {
		try {
			final File file = new File(path);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			final FileWriter fw = new FileWriter(file.getAbsolutePath());
			final BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
		} catch (final IOException e) {
			return false;
		}

		return true;
	}

	// Path joiner
	public static String joinPath(final String path1, final String path2) {
		final File file1 = new File(path1);
		final File file2 = new File(file1, path2);
		return file2.getPath();
	}
}
 
