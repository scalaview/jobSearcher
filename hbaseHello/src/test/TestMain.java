package test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

import util.hbase.HBaseAdminHelper;
import builder.dao.hbase.JobsDAO;

public class TestMain {

	static final String tableName = "Jobs";
	static final String[] colfams = { "schema", "detail", "content" };
	static final List<String> schema = Arrays.asList(new String[] { "Title",
			"URL" });
	static final List<String> detail = Arrays.asList(new String[] { "Company",
			"Industry", "CompanyType", "CompanySize", "Remark" });
	static final List<String> content = Arrays.asList(new String[] {
			"PostingDate", "Location", "Number", "Experience", "Education",
			"Function", "Description", "Salary" });

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Configuration conf = HBaseConfiguration.create();
		HBaseAdminHelper admin = HBaseAdminHelper.getHelper(conf);
		if (!admin.isTableExists(tableName)) {
			admin.createTable(tableName, colfams);
		}
		JobsDAO j = new JobsDAO(admin, conf, tableName);
		// j.add(row, family, qualty, val);
	}

	public static void testAdd(List<Map<String, String>> list)
			throws IOException {
		int i = 0;
		for (Map<String, String> m : list) {
			testAdd(m, "row" + i);
			i++;
		}
	}

	public static void testAdd(Map<String, String> map, String row)
			throws IOException {
		Configuration conf = HBaseConfiguration.create();
		HBaseAdminHelper admin = HBaseAdminHelper.getHelper(conf);
		if (!admin.isTableExists(tableName)) {
			admin.createTable(tableName, colfams);
		}
		JobsDAO j = new JobsDAO(admin, conf, tableName);
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String qualty = it.next();
			String val = map.get(qualty);
			String fam = "another";
//			System.out.println("qualty"+qualty);
			if (schema.contains(qualty)) {
				fam = "schema";
			} else if (detail.contains(qualty)) {
				fam = "detail";
			} else if (content.contains(qualty)) {
				fam = "content";
			}
			else{
				System.out.println("qualty:"+qualty);
			}
			j.add(row, fam, qualty, val);
		}
		j.commit();
		j.colse();
		admin.close();
		conf.clear();
		System.out.println("insert finished");
	}

}
