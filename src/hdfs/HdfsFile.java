package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsFile {
	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("사용방법 : HdfsFile <filename> <contents>");
		}
		else {
			try {
				Configuration conf = new Configuration();
				FileSystem hdfs = FileSystem.get(conf);
				
				Path path = new Path(args[0]);
				if(hdfs.exists(path)) {
					hdfs.delete(path, true);
				}
				
				FSDataOutputStream output = hdfs.create(path);
				output.writeUTF(args[1]);
				output.close();
				
				FSDataInputStream input = hdfs.open(path);
				String inputString = input.readUTF();
				input.close();
				System.out.println("Input Data : " + inputString);
			}
			catch(Exception err) {
				err.printStackTrace();
			}
		}
	}
}

// HdfsFile a.txt	"Hello Hadoop!!!~~~"