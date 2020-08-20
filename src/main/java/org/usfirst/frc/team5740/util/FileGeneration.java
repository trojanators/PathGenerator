package org.usfirst.frc.team5740.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team5740.trajectory.Path;
import org.usfirst.frc.team5740.trajectory.io.JavaSerializer;
import org.usfirst.frc.team5740.trajectory.io.TextFileSerializer;

/** 
 * this class is to Generate java and txt file for paths that are Generated
 * @author
 */
public class FileGeneration {

    private static String javaDirectory = "";

    private static void writeFiles(final String path_name, final Path path) {

        // Outputs to the directory supplied as the first argument.
        final JavaSerializer js = new JavaSerializer();
        final String serialized = js.serialize(path);
        System.out.print(serialized);
        final String fullpath = joinPath(javaDirectory, path_name + ".java");
		if (!writeFile(fullpath, serialized)) {
			System.err.println(fullpath + " could not be written!!!!1");
			System.exit(1);
		} else {
			System.out.println("Wrote " + fullpath);
		}

		// Outputs to the directory supplied as the first argument.
		final TextFileSerializer ts = new TextFileSerializer();
		final String serializedtext = ts.serialize(path);
		// System.out.print(serialized);
		final String fullpathtext = joinPath(javaDirectory, path_name + ".txt");
		if (!writeFile(fullpathtext, serializedtext)) {
			System.err.println(fullpathtext + " could not be written!!!!1");
			System.exit(1);
		} else {
			System.out.println("Wrote " + fullpathtext);
        }
        
    }

	private static boolean writeFile(final String path, final String data) {
		try {
			final File file = new File(path);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			final FileWriter fw = new FileWriter(file.getAbsoluteFile());
			final BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
		} catch (final IOException e) {
			return false;
		}

		return true;
    }
    public static String joinPath(final String path1, final String path2) {
		final File file1 = new File(path1);
		final File file2 = new File(file1, path2);
		return file2.getPath();
	}
}