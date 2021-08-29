package hadoop.program;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import hadoop.Constants;
import hadoop.utils.FileUtils;
import mapreduce.mapper.SentenceMapper;
import mapreduce.reducer.SentenceReducer;

public class LanguageFinderProgram {

	public static void main(String[] args) throws Exception {
		if(args == null || args.length == 0) {
			// Test
			System.out.println("No arguments given, using default file: " + Constants.OUTPUT_DIR_LANG);
			FileUtils.deleteDirectoryIfPresent(Constants.OUTPUT_DIR_LANG);
			checkLanguage(Constants.INPUT_DIR + "/sentences-nl-en.txt", Constants.OUTPUT_DIR_LANG);
		} else {
			// Live
			checkLanguage(args[0], args[1]);
		}
	}
	
	private static void checkLanguage(String input, String output) throws IOException, ClassNotFoundException, InterruptedException {
		Job job = Job.getInstance(new Configuration(), LanguageFinderProgram.class.getName() + "job");
		job.setJarByClass(LanguageFinderProgram.class);
		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		job.setMapperClass(SentenceMapper.class);
		job.setReducerClass(SentenceReducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.waitForCompletion(true);
	}
}
