package mapreduce.mapper;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import hadoop.Constants;
import hadoop.model.CooccurrenceMap;

public class SentenceMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	/**
	 * Cached entry indicating one result is found for given language
	 */
	private IntWritable one = new IntWritable(1);
	
	/**
	 * Cached key for writing a Dutch entry
	 */
	private Text dutchResult = new Text("Dutch");
	
	/**
	 * Cached key for writing an English entry
	 */
	private Text englishResult = new Text("English");
	
	/**
	 * Cached key for writing an entry for an unknown language
	 */
	private Text unknownResult = new Text("Unknown");
	
	/**
	 * @param text - one line of given document
	 */
	@Override 
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// Ignore empty lines
		if(value.toString().trim().length() == 0)
			return;
		
		// Grab an instance of the cooccurrence map for each language 
		CooccurrenceMap en = new CooccurrenceMap(Constants.OUTPUT_DIR_EN + "/part-r-00000");
		CooccurrenceMap nl = new CooccurrenceMap(Constants.OUTPUT_DIR_NL + "/part-r-00000");
		
		// Set the default result, starts from 1 since can't multiply by 0
		double resEn = 1d;
		double resNl = 1d;
			
		String inputString = value.toString();
		String[] characters = inputString.split("");		
		if (characters.length > 1) {
			for (int charPos = 0; charPos < characters.length; charPos++) {	
				
				char sourceCharacter = characters[charPos].toLowerCase().charAt(0);
				
				// Ensure we want to map the coexistence of the character at the current position
				if (!Character.isAlphabetic(sourceCharacter)) {
					continue;
				}
				
				int neighborBefore = Math.max(0, charPos - 1);
				int neighborAfter = Math.min(charPos + 1, characters.length - 1);
				
				for (int neighborPos = neighborBefore; neighborPos <= neighborAfter; neighborPos++) {
					
					// Avoid mapping coexistence for our source character, since it doesn't 'coexist' with itself
					if (neighborPos == charPos) {
						continue;
					}
					
					char neighborCharacter = characters[neighborPos].toLowerCase().charAt(0);
					
					// Ensure we want to map the coexistence of the character
					if (!Character.isAlphabetic(neighborCharacter)) {
						continue;
					}
					
					// Compare chances, avoid multiplying by 0 since this produces an error
					double chanceEn = en.getOccurrenceChance((characters[charPos] + characters[neighborPos]));
					double chanceNl = nl.getOccurrenceChance((characters[charPos] + characters[neighborPos]));
					if((chanceEn == 0d || chanceNl == 0d)) {
						continue;
					}									
					resEn *= chanceEn;
					resNl *= chanceNl;
				}
			}
		}
		
		if(resEn == resNl) {
			context.write(unknownResult, one);
		} else {
			context.write(resEn > resNl ? englishResult : dutchResult, one);
		}
	}
}