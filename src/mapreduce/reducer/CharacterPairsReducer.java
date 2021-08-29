package mapreduce.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import hadoop.model.CharacterPair;

public class CharacterPairsReducer extends Reducer<CharacterPair, IntWritable, CharacterPair, IntWritable> {

	private IntWritable occurances = new IntWritable();

	@Override
	protected void reduce(CharacterPair key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;		
		for (IntWritable value : values) {
			sum += value.get();
		}		
		occurances.set(sum);
		context.write(key, occurances);
	}
}