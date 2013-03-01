package test;

import java.io.IOException;
import java.util.Iterator;
import java.util.NavigableMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class GetExample {
	static final String[] rows = { "row0", "row1", "row2", "row3", "row4" };
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Configuration conf = HBaseConfiguration.create();
//		HBaseHelper helper = HBaseHelper.getHelper(conf);
		HTable table = new HTable(conf, "Jobs"); 
		StringBuffer sf =new StringBuffer();
		Long l =System.nanoTime();
		for (String s : rows) {
			Get get = new Get(Bytes.toBytes(s));
			Result result = table.get(get);
			NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = result
					.getMap();

			for (Iterator<byte[]> fIt = map.keySet().iterator(); fIt.hasNext();) {
				byte[] fKey = fIt.next();
				sf.append("column = " + Bytes.toString(fKey) + ":");
				NavigableMap<byte[], NavigableMap<Long, byte[]>> fVal = map
						.get(fKey);
				for (Iterator<byte[]> qIt = fVal.keySet().iterator(); qIt
						.hasNext();) {
					byte[] qKey = qIt.next();
					sf.append(Bytes.toString(qKey) + " , ");
					NavigableMap<Long, byte[]> qVal = fVal.get(qKey);
					for (Iterator<byte[]> vIt = qVal.values().iterator(); vIt
							.hasNext();) {
						sf.append(" value=" + Bytes.toString(vIt.next())
								+ " , ");
					}
					sf.append("\n\t");
				}
				sf.append("\n");
			}
			sf.append("====================================\n");
		}
		System.out.println(sf.toString()+"\ncount :"+((System.nanoTime()-l)/10000000F)+"scend");
		table.close();
	}

}
