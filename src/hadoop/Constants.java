package hadoop;

public class Constants {	
	/**
	 * The directory where all input files are located
	 */
	public static final String INPUT_DIR = "./src/resource/input";
	
	/**
	 * The default training file for the Dutch language
	 */
	public static final String TRAIN_FILE_NL = INPUT_DIR + "/nl-oorlog.txt";
	
	/**
	 * The default training file for the English language
	 */
	public static final String TRAIN_FILE_EN = INPUT_DIR + "/en-alice.txt";	
	
	/**
	 * The directory where all output files are located
	 */
	public static final String OUTPUT_DIR = "./src/resource/generated";
	
	/**
	 * The directory where the output of the training with Dutch language files is stored
	 */
	public static final String OUTPUT_DIR_NL = OUTPUT_DIR + "/output-nl";
	
	/**
	 * The directory where the output of the training with English language files is stored
	 */
	public static final String OUTPUT_DIR_EN = OUTPUT_DIR + "/output-en";
	
	/**
	 * The directory where the result of the language scans is stored
	 */
	public static final String OUTPUT_DIR_LANG = OUTPUT_DIR + "/output-lang";
}
