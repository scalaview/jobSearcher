package builder.dao.lucence;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKSimilarity;

import vo.mySearchFile;
import vo.searchPage;

public class SearchDAO {
	static String[] fields = { "Title", "URL", "Company", "Industry",
		"CompanyType", "CompanySize", "Remark", "PostingDate", "Location",
		"Number", "Experience", "Education", "Function", "Description",
		"Salary"
};
	private static Logger log = Logger.getLogger(searchDaoBak.class);
	private final Version LUCENE = Version.LUCENE_35;
	private final Analyzer analyzer = new IKAnalyzer();

	private String filePath = System.getProperty("user.dir") + "\\index";

	public void buildIndex(List<Document> doclist) {
		IndexWriterConfig iwc = new IndexWriterConfig(LUCENE, analyzer);
		iwc.setOpenMode(OpenMode.CREATE);
		IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(this.getDirectory(), iwc);
			for (Document doc : doclist) {
				indexWriter.addDocument(doc);
				indexWriter.commit();
			}
		} catch (Exception e) {
			log.error("获取索引失败", e);
			throw new RuntimeException(e);
		} finally {
			try {
				indexWriter.forceMerge(1, true);
				indexWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public Map<String, Object> search(Query query, int pageIndex, int pageSize)
			throws Exception {
		/**
		 * 读取索引并建一个搜索器
		 */
		IndexReader r = IndexReader.open(this.getDirectory());
		IndexSearcher indexSearcher = new IndexSearcher(r);
		indexSearcher.setSimilarity(new IKSimilarity());

		Map<String, Object> map = new HashMap<String, Object>();
		// ================================================
		log.info("Query:" + query.toString());
		System.out.println("Query:" + query.toString());
		// ================================================

		/**
		 * 查找并返回一个结果集
		 */
		TopDocs docs = indexSearcher.search(query, pageIndex * pageSize);
		int count = docs.totalHits;
		ScoreDoc doc = null;
		List<mySearchFile> relist = new ArrayList<mySearchFile>();
		for (int i = ((pageIndex - 1) > 0 ? (pageIndex - 1) : 0) * pageSize; i < pageIndex
				* pageSize&&i<count; i++) {
			doc = docs.scoreDocs[i];
			Document document = indexSearcher.doc(doc.doc);
//			for (Iterator<Fieldable> it = document.getFields().iterator(); it
//					.hasNext();) {
//				println("it.next:" + it.next());
//			}
			for(String fie : fields){
				println(fie+":"+document.get(fie));
			}
			
		}
		println("count :" + count);
		return null;
	}

	/**
	 * 生成对全部类型的查询
	 */
	public Query getQuery(String queryStr,String[] fields) throws Exception {
		
		QueryParser queryParser = new MultiFieldQueryParser(LUCENE, fields,
				analyzer);
		Query query = queryParser.parse(queryStr);

		return query;
	}

	public Directory getDirectory() throws Exception {
		File indexFile = new File(filePath);

		println("created search index in " + indexFile);

		if (!indexFile.exists()) {
			indexFile.mkdir();
		}

		return FSDirectory.open(indexFile);
	}

	public void setDirectory(String filePath) {
		this.filePath = filePath;
	}

	public static void println(String s) {
		System.out.println(s);

	}
}
