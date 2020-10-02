package pathgenerator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

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
	private CSVPrinter csvPrinter;
	/**
	 * writes a txt file filled with generated path
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
			System.err.println(fullpathtext + " could not be written!!!!1");
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

	/***
	 * Outputs Generated Path to csv file but with  .Path Extension
	 * 
	 * @param String Directory, Filename
	 * @param Path   robot path
	 * 
	 */
	public void writePathFile(int sequenceNum, final String Directory, final String fileName, final String fileExtension,final Path path) throws IOException {
		try {
			Main.logger.info("Starting to write data to csv file");
            writer = Files.newBufferedWriter(Paths.get(Directory + fileName + fileExtension));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "x", "y", "pos", "vel","acc","jerk","dt"));
			
			for(int i = 0; i < sequenceNum; i++){
				csvPrinter.printRecord(
					i, trajectory.generatedX.get(i), trajectory.generatedY.get(i), 
					trajectory.generatedPos.get(i),trajectory.generatedVel.get(i),
					trajectory.generatedAcc.get(i),trajectory.generatedJerk.get(i),
					trajectory.generatedDt.get(i));

			}

			csvPrinter.flush();    
			Main.logger.warning("Finished Writing Data to FIle"+ Directory+"/"+fileName+fileExtension);
		}
		catch(Exception e){
			System.err.println(e);
			}
 		}
	}
 
