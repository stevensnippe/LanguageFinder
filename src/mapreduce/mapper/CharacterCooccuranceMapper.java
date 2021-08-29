package mapreduce.mapper;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import hadoop.model.CharacterPair;

public class CharacterCooccuranceMapper extends Mapper<LongWritable, Text, CharacterPair, IntWritable> {
	
	/**
	 * Cached pair reused for writing two coexisting characters 
	 * Purpose: performance optimization by avoiding multiple constructor calls
	 */
	private CharacterPair pair = new CharacterPair();
	
	/**
	 * Cached entry indicating one result is found for given character pair
	 */
	private final IntWritable one = new IntWritable(1);
	
	/**
	 * @param text - one line of given document
	 */
	@Override 
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		try {
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
					
					// Reuse the cached pair for performance optimization
					pair.setSource(sourceCharacter);
					pair.setNeighbor(neighborCharacter);
					
					context.write(pair, one);
				}
			}
		}}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
