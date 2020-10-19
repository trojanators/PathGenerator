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
import java.util.ArrayList;
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

import org.simpleyaml.configuration.file.YamlFile;

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
	private File file;
	private FileWriter fileWriter;

	private Map<String,Object> map = new HashMap<>();

	private static String yamlBaseKey = "Waypoint.";

	/**
	 * writes a .path file filled with generated path
	 * 
	 * @param Directory
	 * @param fileName
	 * @param path
	 * @param sequence
	 */
	/*
	public void writeFiles(String comments, final String Directory, final String fileName, final Path path) {

		this.file = new File(Directory + fileName + ".path");
		for (int id = 0; id < sequence.getNumWaypoints(); id++) {
			YamlMapping layoutMap = Yaml.createYamlMappingBuilder().add(yamlBaseKey, Integer.toString(id))
					.add("pos",
							Double.toString(path.getLeftWheelTrajectory().getSegment(id).pos) + ','
									+ Double.toString(path.getRightWheelTrajectory().getSegment(id).pos))
					.add("vel",
							Double.toString(path.getLeftWheelTrajectory().getSegment(id).vel) + ','
									+ Double.toString(path.getRightWheelTrajectory().getSegment(id).vel))
					.add("acc",
							Double.toString(path.getLeftWheelTrajectory().getSegment(id).acc) + ','
									+ Double.toString(path.getRightWheelTrajectory().getSegment(id).acc))
					.add("jerk",
							Double.toString(path.getLeftWheelTrajectory().getSegment(id).jerk) + ','
									+ Double.toString(path.getRightWheelTrajectory().getSegment(id).jerk))
					.add("heading",
							Double.toString(path.getLeftWheelTrajectory().getSegment(id).heading) + ','
									+ Double.toString(path.getRightWheelTrajectory().getSegment(id).heading))
					.add("dt",
							Double.toString(path.getLeftWheelTrajectory().getSegment(id).dt) + ','
									+ Double.toString(path.getRightWheelTrajectory().getSegment(id).dt))
					.add("x",
							Double.toString(path.getLeftWheelTrajectory().getSegment(id).x) + ','
									+ Double.toString(path.getRightWheelTrajectory().getSegment(id).x))
					.add("y", Double.toString(path.getLeftWheelTrajectory().getSegment(id).y) + ','
							+ Double.toString(path.getRightWheelTrajectory().getSegment(id).y))
					.build(comments);

			map.add(id, layoutMap);

			try {

				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				final YamlPrinter printer = Yaml.createYamlPrinter(new FileWriter(file.getAbsoluteFile()));
				new printer.print(map.get(id).asMapping());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}*/
		
		/***
		 * This is an empty file .path file for Testing
		 * @param Id
		 * @param comments
		 * @param Directory
		 * @param fileName
		 * @param path
		 */
		public void createBase(int Id, String comments, final String Directory, final String fileName, final Path path){
			final YamlFile yamlFile = new YamlFile(Directory+fileName+".path");
			
			try {
				if (!yamlFile.exists()) {
					Main.logger.info("New file has been created: " + yamlFile.getFilePath() + "\n");
					yamlFile.createNewFile(true);
				} else {
					Main.logger.info(yamlFile.getFilePath() + " already exists, loading configurations...\n");
				}
				yamlFile.load(); // Loads the entire file
				// If your file has comments inside you have to load it with yamlFile.loadWithComments()
			} catch (final Exception e) {
				e.printStackTrace();
			}
			// Yaml Default layout
			yamlFile.addDefault(yamlBaseKey, Id);
			yamlFile.addDefault(yamlBaseKey+"Pos", 0.0+","+0.0);
			yamlFile.addDefault(yamlBaseKey+"Vel",0.0+","+0.0);
			yamlFile.addDefault(yamlBaseKey+"Acc",0.0+","+0.0);
			yamlFile.addDefault(yamlBaseKey+"Jerk", 0.0+","+0.0);
			yamlFile.addDefault(yamlBaseKey+"Heading", 0.0+","+0.0);
			yamlFile.addDefault(yamlBaseKey+"Dt", 0.0+","+0.0);
			yamlFile.addDefault(yamlBaseKey+"X", 0.0+","+0.0);
			yamlFile.addDefault(yamlBaseKey+"Y", 0.0+","+0.0);
			
		}
		/**
		 *  Writes Data to .path file for generated Path
		 * @param Id
		 * @param comments
		 * @param Directory
		 * @param fileName
		 * @param path
		 */
		public void writeFiles(String comments, final String Directory, final String fileName, final Path path){
			final YamlFile yamlFile = new YamlFile(Directory+fileName+".path");
			
			try {
				if (!yamlFile.exists()) {
					Main.logger.info("New file has been created: " + yamlFile.getFilePath() + "\n");
					yamlFile.createNewFile(true);
				} else {
					Main.logger.info(yamlFile.getFilePath() + " already exists, loading configurations...\n");
				}
				yamlFile.load(); // Loads the entire file
				// If your file has comments inside you have to load it with yamlFile.loadWithComments()
			} catch (final Exception e) {
				e.printStackTrace();
			}
			for(int id=0; id<sequence.getNumWaypoints(); id++){
			// Yaml Default layout
			map.put("Pos",path.getLeftWheelTrajectory().getSegmentId(id).pos+","+path.getRightWheelTrajectory().getSegmentId(id).pos);
			map.put("Vel",path.getLeftWheelTrajectory().getSegmentId(id).vel+","+path.getRightWheelTrajectory().getSegmentId(id).vel);
			map.put("Acc",path.getLeftWheelTrajectory().getSegmentId(id).acc+","+path.getRightWheelTrajectory().getSegmentId(id).acc);
			map.put("Jerk",path.getLeftWheelTrajectory().getSegmentId(id).jerk+","+path.getRightWheelTrajectory().getSegmentId(id).jerk);
			map.put("Heading",path.getLeftWheelTrajectory().getSegmentId(id).heading+","+path.getRightWheelTrajectory().getSegmentId(id).heading);
			map.put("Dt",path.getLeftWheelTrajectory().getSegmentId(id).dt+","+path.getRightWheelTrajectory().getSegmentId(id).dt);
			map.put("X", path.getLeftWheelTrajectory().getSegmentId(id).x+","+path.getRightWheelTrajectory().getSegmentId(id).x);
			map.put("Y", path.getLeftWheelTrajectory().getSegmentId(id).y+","+path.getRightWheelTrajectory().getSegmentId(id).y);
			yamlFile.createSection(Integer.toString(id),map);
			
			
			// Finally, save changes!
			try {
				yamlFile.save();
				// If your file has comments inside you have to save it with yamlFile.saveWithComments()
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}
}
