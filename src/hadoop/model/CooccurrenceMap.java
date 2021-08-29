package hadoop.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

public class CooccurrenceMap {
	
	private HashMap<String, Double> cooccurence = new HashMap<String, Double>();

	public CooccurrenceMap(String path) {
		generateCooccurrenceOdds(path);
	}
	
	private void generateCooccurrenceOdds(String path) {
		HashMap<String, Integer> occurences = parseFile(path);
		double total = (double) occurences.values().stream().mapToInt(Integer::intValue).sum();
		for(Entry<String, Integer> entry : occurences.entrySet()) {
			cooccurence.put(entry.getKey(), ((double) entry.getValue()) / total);
		}
	}
	
	private HashMap<String, Integer> parseFile(String path){
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		try {
			List<String> lines;
			if (System.console() != null) {
				lines = IOUtils.readLines(this.getClass().getResourceAsStream(path.replace("./src", "")), StandardCharsets.UTF_8);
			} else {
				lines = Files.readAllLines(Paths.get(path));
			}
			for(String line : lines) {
				String[] parts = line.split("	");
				result.put(parts[0], Integer.parseInt(parts[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public double getOccurrenceChance(String pair) {
		Double result = cooccurence.get(pair.toLowerCase());
		return result != null ? result : 0d;
	}	
}
