package count;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// 1, read a book
// 2, write a book
// <read, 1> <a, 1> <book, 1> ...
public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
	
			StringTokenizer token = new StringTokenizer(value.toString());
			while(token.hasMoreTokens()) {
				word.set(token.nextToken());
				context.write(word,  one);
			}
	}
}
