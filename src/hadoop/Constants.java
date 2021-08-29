package hadoop;

import org.apache.hadoop.fs.Path;

public class Constants {	
	/**
	 * The directory where all input files are located
	 */
	public static final String INPUT_DIR = "." + Path.SEPARATOR + "src" + Path.SEPARATOR + "resource" + Path.SEPARATOR + "input";
	
	/**
	 * The default training file for the Dutch language
	 */
	public static final String TRAIN_FILE_NL = INPUT_DIR + Path.SEPARATOR + "nl-oorlog.txt";
	
	/**
	 * The default training file for the English language
	 */
	public static final String TRAIN_FILE_EN = INPUT_DIR + Path.SEPARATOR + "en-alice.txt";	
	
	/**
	 * The directory where all output files are located
	 */
	public static final String OUTPUT_DIR = "." + Path.SEPARATOR + "src/resource/generated";
	
	/**
	 * The directory where the output of the training with Dutch language files is stored
	 */
	public static final String OUTPUT_DIR_NL = OUTPUT_DIR + Path.SEPARATOR + "output-nl";
	
	/**
	 * The directory where the output of the training with English language files is stored
	 */
	public static final String OUTPUT_DIR_EN = OUTPUT_DIR + Path.SEPARATOR + "output-en";
	
	/**
	 * The directory where the result of the language scans is stored
	 */
	public static final String OUTPUT_DIR_LANG = OUTPUT_DIR + Path.SEPARATOR + "output-lang";
}
