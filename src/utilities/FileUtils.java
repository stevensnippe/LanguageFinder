package utilities;

import java.io.File;
import java.io.IOException;

public class FileUtils {

	public static void deleteDirectoryIfPresent(String directoryString) throws IOException {
		File dir = new File(directoryString);
		if(dir.exists()) {
			FileUtils.deleteDirectory(dir);
		}
	}
	
	/**
	 * Author: Robert Piasecki
	 * https://softwarecave.org/2018/03/24/delete-directory-with-contents-in-java/
	 */
	public static void deleteDirectory(File file) throws IOException {
		if (file.isDirectory()) {
			File[] entries = file.listFiles();
			if (entries != null) {
				for (File entry : entries) {
					deleteDirectory(entry);
				}
			}
		}
		if (!file.delete()) {
			throw new IOException("Failed to delete " + file);
		}
	}
	
}
