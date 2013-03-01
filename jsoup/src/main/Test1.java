package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import test.TestMain;
import util.lucene.Database2Document;
import builder.dao.lucence.SearchDAO;
import builder.fileFilter.BasicFileFilter;
import builder.fileFilter.imp.CompanyEssentialInfo;
import builder.fileFilter.imp.PositionInf;
import builder.fileFilter.imp.RemarkInf;

public class Test1 {
	private static final String f1 = "49230837.html";
	private static final String[] files = { "49230837.html", "52948591.html",
			"52718734.html", "52506025.html", "51423560.html" };
	private static File file;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Map<String, String>> mlist = new ArrayList<Map<String, String>>();
		// TODO Auto-generated method stub
		for (String s : files) {
			file = new File(System.getProperty("user.dir").toString()
					+ "\\src\\" + s);
			Document doc = null;
			RemarkInf ri = new RemarkInf();
			PositionInf pf = new PositionInf();
			CompanyEssentialInfo cf = new CompanyEssentialInfo();
			List<BasicFileFilter> list = new ArrayList<BasicFileFilter>();
			list.add(cf);
			list.add(ri);
			list.add(pf);
			try {
				doc = Jsoup.parse(file, "GB2312");
				// String str
				// =Charset.forName("GB2312").decode(readToByteBuffer(new
				// FileInputStream(file))).toString();
				// //
				// println("basic :"+str.replace(Jsoup.parse("&nbsp;").text(),""));
				// doc = Parser.htmlParser().parseInput(str,
				// file.getAbsolutePath());
				// println("str:"+doc.text());
				Elements e = doc.getElementsByClass("jobs_1");
				// System.out.println("count:" + e.size());
				// println("title : " + e.select("td[class=sr_bt]"));
				// println("<a>:" +
				// e.select("a[href*=search.51job.com]").get(0));
				// println(""+e.getElementsMatchingText("��˾��ҵ��"));
				println("tr count : " + e.select("td").size());
				Iterator<BasicFileFilter> lit = list.iterator();
				Map<String, String> map = new HashMap<String, String>();
				for (Iterator<Element> it = e.iterator(); it.hasNext()
						&& lit.hasNext();) {
					Element e1 = it.next();
					BasicFileFilter bf = lit.next();
					bf.Filter(e1, map);
					// println("<a>:"+e1.select("a[href*=search.51job.com]").text());//href="http://search.51job.com
					// println(new String((new String(e1.text().getBytes(),
					// "UTF-8"))
					// .replaceAll("&nbsp;", " ").getBytes(), "GB2312") );
					// println("note name : "+e1.nodeName());
					// println("tag " + i + " : "
					// + e1.text().replace(Jsoup.parse("&nbsp;").text(), " "));
				}
				//
				for (Iterator<String> it = map.keySet().iterator(); it
						.hasNext();) {
					String key = it.next();
					println(key + ":" + map.get(key));
				}

				mlist.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		try {
//			Database2Document.getDocList(mlist);
//			SearchDAO sDao = new SearchDAO();
//			sDao.buildIndex(Database2Document.getDocList(mlist));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try{
			TestMain.testAdd(mlist);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void println(String str) {
		System.out.println(str);
	}

}
