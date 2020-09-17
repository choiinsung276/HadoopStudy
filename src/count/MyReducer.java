package count;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	private IntWritable result = new IntWritable();
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context ctx) throws IOException, InterruptedException {
	
			int sum = 0;
			for(IntWritable val : values) {
				sum += val.get();
			}
			
			result.set(sum);
			ctx.write(key,  result);
	}
}
