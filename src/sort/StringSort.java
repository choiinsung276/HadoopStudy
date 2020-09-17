package sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class StringSort {
   public static void main(String[] args) throws Exception {
      Configuration conf = new Configuration();
      
      if(args.length != 2) {
         System.out.println("사용방법 : StringSort <input> <output>");
         System.exit(1); //강제 종료
      }
      
      Job job = Job.getInstance(conf, "StringSort");
      
      job.setJarByClass(StringSort.class);
      job.setMapperClass(Mapper.class);
      job.setReducerClass(Reducer.class);
      
      job.setInputFormatClass(KeyValueTextInputFormat.class);
      job.setOutputFormatClass(SequenceFileOutputFormat.class);
      
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(Text.class);
      
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(Text.class);
      
      job.setNumReduceTasks(1);
      
      SequenceFileInputFormat.addInputPath(job, new Path(args[0]));
      SequenceFileOutputFormat.setOutputPath(job, new Path(args[1]));

      SequenceFileOutputFormat.setOutputCompressionType(job, 
    		  SequenceFile.CompressionType.BLOCK);
           
      job.waitForCompletion(true);
   }
}