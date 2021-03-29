import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LookupCSV {
	public static void main(String[] args) {
		// args[0] : csv file name
		// args[1] : key integer
		// args[2] : value integer
		
		// In csv file, each line - url, ip address
		// If args[1] = 0 and args[2] = 1, then key = 0 and value = 1.
		// i.e. key = url, value = ip address
		// If args[1] = 1 and args[2] = 0, then key = 1 and value = 0.
		// i.e. key = ip address, value = url
		
		In in = new In(args[0]);
		int keyField = Integer.parseInt(args[1]);
		int valueField = Integer.parseInt(args[2]);
		
		ST<String, String> st = new ST<String, String>();
		while(!in.isEmpty()) {
			String line = in.readLine();
			String[] tokens = line.split(",");
			String key = tokens[keyField];
			String val = tokens[valueField];
			st.put(key, val);
		}
		
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(!st.contains(s)) StdOut.println("Not found");
			else StdOut.println(st.get(s));
		}
	}
}
