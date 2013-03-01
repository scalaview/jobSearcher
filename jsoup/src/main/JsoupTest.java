package main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class JsoupTest {
	private static File file;
	private static final String f1 = "49230837.html";
	// private static final String f1 = "52948591.html";
	// private static final String f1 = "52718734.html";
	// private static final String f1 = "52506025.html";
	// private static final String f1 = "51423560.html";
	// private static final int bufferSize = 0x20000;
	private static final String[] files = { "49230837.html", "52948591.html",
			"52718734.html", "52506025.html", "51423560.html" };

	public static void main(String... args) {
		file = new File(System.getProperty("user.dir").toString() + "\\src\\"
				+ f1);
		Document doc = null;

		try {
			doc = Jsoup.parse(file, "GB2312");
			// String str =Charset.forName("GB2312").decode(readToByteBuffer(new
			// FileInputStream(file))).toString();
			// //
			// println("basic :"+str.replace(Jsoup.parse("&nbsp;").text(),""));
			// doc = Parser.htmlParser().parseInput(str,
			// file.getAbsolutePath());
			// println("str:"+doc.text());
			Elements e = doc.getElementsByClass("jobs_1");
			// System.out.println("count:" + e.size());
			// println("title : " + e.select("td[class=sr_bt]"));
			// println("<a>:" + e.select("a[href*=search.51job.com]").get(0));
			// println(""+e.getElementsMatchingText("��˾��ҵ��"));
			println("tr count : " + e.select("td").size());
			int i = 0;
			for (Iterator<Element> it = e.iterator(); it.hasNext();) {
				Element e1 = it.next();
				if (i == 0) {
					tables1(e1);
				}
				if (i == 1) {
					table2(e1);
				}
				if (i == 2) {
					table3(e1);
				}
				// println("<a>:"+e1.select("a[href*=search.51job.com]").text());//href="http://search.51job.com
				// println(new String((new String(e1.text().getBytes(),
				// "UTF-8"))
				// .replaceAll("&nbsp;", " ").getBytes(), "GB2312") );
				// println("note name : "+e1.nodeName());
				// println("tag " + i + " : "
				// + e1.text().replace(Jsoup.parse("&nbsp;").text(), " "));
				i++;
			}
			// println("content:" + e.text());
			// println("i=" + i);
			// doc.get
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// test();
	}

	// static ByteBuffer readToByteBuffer(InputStream inStream) throws
	// IOException {
	// byte[] buffer = new byte[bufferSize];
	// ByteArrayOutputStream outStream = new ByteArrayOutputStream(bufferSize);
	// int read;
	// while(true) {
	// read = inStream.read(buffer);
	// if (read == -1) break;
	// outStream.write(buffer, 0, read);
	// }
	// ByteBuffer byteData = ByteBuffer.wrap(outStream.toByteArray());
	// return byteData;
	// }
	public static void tables1(Element e) {
		// title
		println("title : " + e.select("td[class=sr_bt]").text().trim());
		println("company : "
				+ e.select("a[href*=search.51job.com]").get(0).text().trim());
		// println("absmiddle:"
		// + e.select("img[align=absmiddle").get(0).parent()
		// .nextElementSibling());

		// for (Element ie : e.select("img[align=absmiddle").get(0).parent()
		// .nextElementSibling().children()) {
		// // ie.nextElementSibling();
		// println("tag  :  "+ie.text());
		// println("next text : "+ie.nextElementSibling());
		// }
		// for (TextNode s : e.select("img[align=absmiddle" ).get(0).parent()
		// .nextElementSibling().textNodes()) {
		// // ie.nextElementSibling();
		// println( "tag  :  "+s.text());
		// }
		// println("&nbsp; "+Jsoup.parse("&nbsp;").text());
		for (Iterator<Node> tn = e.select("td[valign=bottom]").get(0)
				.previousElementSibling().childNodes().iterator(); tn.hasNext();) {
			// ie.nextElementSibling();
			Node d = tn.next();
			String tmp = d.toString();
			if (tn.hasNext()) {
				if (-1 != tmp.indexOf(">��˾��ҵ��<")) {
					println("��˾��ҵ��"
							+ tn.next().toString().replace("&nbsp;", " ")
									.trim());
				} else if (-1 != tmp.indexOf(">��˾���ʣ�<")) {
					println("��˾���ʣ�"
							+ tn.next().toString().replace("&nbsp;", " ")
									.trim());
				} else if (-1 != tmp.indexOf(">��˾��ģ��<")) {
					println("��˾��ģ��"
							+ tn.next().toString().replace("&nbsp;", " ")
									.trim());
				}
			}
			// println("tag  :  " + d.toString());
		}
		// println("size:" + e.getElementsMatchingText("��˾��ҵ��").text());
	}

	public static void table2(Element e) {
		// String tmp=e.text().replace(Jsoup.parse("&nbsp;").text(), " ");
		// for(Element el:e.select("tr")){
		// // println("tag : "+el.text());
		// String tmp = el.text().replace(Jsoup.parse("&nbsp;").text(), "");
		// if(-1!=tmp.indexOf("��ַ��")){
		// println("��ַ�� "+tmp.split("��")[1].trim());
		// }
		// else if(-1!=tmp.indexOf("�������룺")){
		// println("�������룺"+tmp.split("��")[1].trim());
		// }
		// }
		println("remark ��"
				+ e.text().replace(Jsoup.parse("&nbsp;").text(), " ").trim());

	}

	public static void table3(Element e) {
		// println("Ҫ�� : "+e.text().replace(Jsoup.parse("&nbsp;").text(),
		// " "));
		for (Iterator<Element> it = e.select("td").iterator(); it.hasNext();) {
			Element ie = it.next();
			String tmp = ie.outerHtml().replace("&nbsp;", "");
			// println("td:"+tmp);
			if (-1 != tmp.indexOf(">�������ڣ�<")) {
				Element etmp = it.next();
				String itmp = etmp.text()
						.replace(Jsoup.parse("&nbsp;").text(), "").trim();
				println("�������ڣ�" + itmp);
			} else if (-1 != tmp.indexOf(">�����ص㣺<")) {
				Element etmp = it.next();
				String itmp = etmp.text()
						.replace(Jsoup.parse("&nbsp;").text(), "").trim();
				println("�����ص㣺" + itmp);
			} else if (-1 != tmp.indexOf(">��Ƹ����<")) {
				Element etmp = it.next();
				String itmp = etmp.text()
						.replace(Jsoup.parse("&nbsp;").text(), "").trim();
				println("��Ƹ����" + itmp);
			} else if (-1 != tmp.indexOf(">�������ޣ�<")) {
				Element etmp = it.next();
				String itmp = etmp.text()
						.replace(Jsoup.parse("&nbsp;").text(), "").trim();
				println("�������ޣ�" + itmp);
			} else if (-1 != tmp.indexOf(">ѧ��<")) {
				Element etmp = it.next();
				String itmp = etmp.text()
						.replace(Jsoup.parse("&nbsp;").text(), "").trim();
				println("ѧ��" + itmp);
			} else if (-1 != tmp.indexOf(">нˮ��Χ��<")) {
				Element etmp = it.next();
				String itmp = etmp.text()
						.replace(Jsoup.parse("&nbsp;").text(), "").trim();
				println("нˮ��Χ��" + itmp);

			} else if (-1 != tmp.indexOf(">ְλְ��:<")) {
				tmp = ie.text().replace(Jsoup.parse("&nbsp;").text(), "")
						.trim();
				String itmp = tmp.split(":")[1].trim();
				println("ְλְ��:" + itmp);
			} else if (-1 != tmp.indexOf(">ְλ����:<")) {
				tmp = ie.text().replace(Jsoup.parse("&nbsp;").text(), "")
						.trim();
				String itmp = tmp.split(":")[1].trim();
				println("ְλ����:" + itmp);
			}
			// println("td:"+it.text());
		}
	}

	public static void println(String str) {
		System.out.println(str);
	}

	public static void test() {
		Properties p = new Properties();
		System.out.println(System.getProperty("user.dir").toString());

		System.out.println(JsoupTest.class.getClassLoader().getResource("./"));
	}
}
