package pathgenerator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javafx.stage.Stage;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.Trajectory;
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
	private BufferedWriter writer;


	/**
	 * writes a .path file filled with generated path
	 * 
	 * @param Directory
	 * @param fileName
	 * @param path
	 */
	public static void writeFiles(final String Directory, final String fileName, final Path path) {

		// Outputs to the directory supplied as the first argument.
		final TextFileSerializer ts = new TextFileSerializer();
		final String serializedtext = ts.serialize(path);
		final String fullpathtext = joinPath(Directory, fileName + ".path");
	

		if (!writeFile(fullpathtext, serializedtext)) {
			System.err.println(fullpathtext + " could not be written!!!!");
			System.exit(1);
		} else {
			Main.logger.info("Wrote " + fullpathtext);
			Main.logger.warning("FINISHED");
			
			

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
 
