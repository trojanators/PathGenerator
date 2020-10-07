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
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.composer.*; 

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

	private static String yamlBaseKey = "Waypoint";
	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * writes a .path file filled with generated path
	 * 
	 * @param Directory
	 * @param fileName
	 * @param path
	 * @param sequence
	 * @throws IOException
	 */
	public void writeFiles(final String Directory, final String fileName, final Path path){
		
		for(int i = 0; i<sequence.getNumWaypoints(); i++){
		data.put(yamlBaseKey + ".Id", tableData.getId());
		data.put(yamlBaseKey + ".UserComments","Exampel String");
		data.put(yamlBaseKey + ".Pos", path.getPair().left.getPos());
		data.put(yamlBaseKey + ".Acc", path.getPair().left.getAcc());
		data.put(yamlBaseKey + ".Jerk",path.getPair().left.getJerk());
		data.put(yamlBaseKey + ".Heading",path.getPair().left.getHeading());
		//data.put(yamlBaseKey + ".Dt",path.getPair().left.;
		data.put(yamlBaseKey + ".X", path.getPair().left.getX());
		data.put(yamlBaseKey +".Y", path.getPair().left.getY());
		}
		Yaml yaml = new Yaml();
		FileWriter writer;
		try {
			writer = new FileWriter(Directory + fileName);
			yaml.dump(data, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
 
