package test;

import java.io.IOException;
import java.util.Iterator;
import java.util.NavigableMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class PutExample {

	static final String[] rows = { "row0", "row1", "row2", "row3", "row4" };

	public static void main(String[] args) throws IOException {
		Configuration conf = HBaseConfiguration.create();
		// co PutExample-1-CreateConf Create the required configuration.
		// ^^ PutExample
		// conf.setInt("hbase.zookeeper.property.clientPort", 5181);
		// conf.set("hbase.master", "192.168.1.128:60000");
		/*
		 * 
		 * Configuration conf = HBaseConfiguration.create(); HTable table = new
		 * HTable(conf, "testtable"); Put put = new Put(Bytes.toBytes("row1"));
		 * put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),
		 * Bytes.toBytes("val1")); put.add(Bytes.toBytes("colfam1"),
		 * Bytes.toBytes("qual2"), Bytes.toBytes("val2")); table.put(put);
		 */
		HBaseHelper helper = HBaseHelper.getHelper(conf);
		 helper.dropTable("Jobs");
		// helper.createTable("testtable","colfam1"); // vv PutExample
		// helper.createTable("testtable");
//		HTable table = new HTable(conf, "Jobs"); // co
		// // PutExample-2-NewTable
		// // Instantiate a new
		// // client.
		// Put put = new Put(Bytes.toBytes("row1")); // co PutExample-3-NewPut
		// // Create put with specific
		// // row.
		// put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),
		// Bytes.toBytes("val1")); // co PutExample-4-AddCol1 Add a column,
		// // whose name is "colfam1:qual1", to the
		// // put.
		// put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"),
		// Bytes.toBytes("val2")); // co PutExample-4-AddCol2 Add another
		// // column, whose name is
		// // "colfam1:qual2", to the put.
		// table.put(put); // co PutExample-5-DoPut Store row with column into
		// the
		// // HBase table.
		// // helper.`
		// Get get = new Get(Bytes.toBytes("row1"));
		// get.addColumn(Bytes.toBytes("detail"), Bytes.toBytes("CompanyType"));
		// Result result = table.get(get);
		// byte[] val = result.getValue(Bytes.toBytes("colfam1"),
		// Bytes.toBytes("qual1"));
		// System.out.println("Value: " + Bytes.toString(val));
		// table.close();
//		StringBuffer sf =new StringBuffer();
//		Long l =System.nanoTime();
//		for (String s : rows) {
//			Get get = new Get(Bytes.toBytes(s));
//			Result result = table.get(get);
//			NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = result
//					.getMap();
//
//			for (Iterator<byte[]> fIt = map.keySet().iterator(); fIt.hasNext();) {
//				byte[] fKey = fIt.next();
//				sf.append("column = " + Bytes.toString(fKey) + ":");
//				NavigableMap<byte[], NavigableMap<Long, byte[]>> fVal = map
//						.get(fKey);
//				for (Iterator<byte[]> qIt = fVal.keySet().iterator(); qIt
//						.hasNext();) {
//					byte[] qKey = qIt.next();
//					sf.append(Bytes.toString(qKey) + " , ");
//					NavigableMap<Long, byte[]> qVal = fVal.get(qKey);
//					for (Iterator<byte[]> vIt = qVal.values().iterator(); vIt
//							.hasNext();) {
//						sf.append(" value=" + Bytes.toString(vIt.next())
//								+ " , ");
//					}
//					sf.append("\n\t");
//				}
//				sf.append("\n");
//			}
//			sf.append("====================================\n");
//		}
//		System.out.println(sf.toString()+"\ncount :"+(System.nanoTime()-l)+"scend");
//		table.close();
//
	}
}
