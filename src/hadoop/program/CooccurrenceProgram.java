package hadoop.program;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import hadoop.Constants;
import hadoop.model.CharacterPair;
import hadoop.utils.FileUtils;
import mapreduce.mapper.CharacterCooccuranceMapper;
import mapreduce.reducer.CharacterPairsReducer;

public class CooccurrenceProgram {
	
	public static void main(String[] args) throws Exception {		
		if(args == null || args.length == 0) {
			// Test
			System.out.println("No arguments given, using default files: " + Constants.TRAIN_FILE_EN + ", " + Constants.TRAIN_FILE_NL);
			FileUtils.deleteDirectoryIfPresent(Constants.OUTPUT_DIR_EN);
			FileUtils.deleteDirectoryIfPresent(Constants.OUTPUT_DIR_NL);
			countOccurrences(Constants.TRAIN_FILE_EN, Constants.OUTPUT_DIR_EN);
			countOccurrences(Constants.TRAIN_FILE_NL, Constants.OUTPUT_DIR_NL);
		}
		else if(args[2].equals("generate")) {
			// Live
			countOccurrences(args[0], args[1]);
		}
	}
	
	private static void countOccurrences(String input, String output) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		Job job = Job.getInstance(new Configuration(), CooccurrenceProgram.class.getName() + "job");
		job.setJarByClass(CooccurrenceProgram.class);		
		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		job.setMapperClass(CharacterCooccuranceMapper.class);
		job.setReducerClass(CharacterPairsReducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputKeyClass(CharacterPair.class);
		job.setOutputValueClass(IntWritable.class);
		job.waitForCompletion(true);
	}
}