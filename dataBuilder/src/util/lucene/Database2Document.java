/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.lucene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

public class Database2Document {
	public final static String[] needStore = { "Title", "Company", "Industry",
			"PostingDate", "Function", "Description" };
	public final static String[] needAnalyze = { "Title", "Company",
			"Industry", "Function", "Description" };

	//
	// public List<Document> getDocList() throws Exception {
	//
	// List<Document> doclist = new ArrayList<Document>();
	//
	//
	// List<Resource> list1 = dao.query(Resource.class, Cnd.where(null), null);
	// List<Attachment> list2 = dao.query(Attachment.class, Cnd.where(null),
	// null);
	// for (Resource r : list1) {
	// for(Attachment att :list2){
	// if(att.getId()==r.getAttachmentId()){
	// r.setAttachment(att);
	// break;
	// }
	// }
	// doclist.add(this.DocTool(r));
	// }
	// return doclist;
	// }
	public static List<Document> getDocList(List<Map<String, String>> l) {
		List<Document> list = new ArrayList<Document>();
		for(Iterator<Map<String, String>> it=l.iterator();it.hasNext();){
			list.add(DocTool(it.next()));
		}
		return list;
	}

	public static Document DocTool(Map<String, String> map) {
		Document doc = new Document();
		// Attachment att = r.getAttachment();
		/*
		 * filename Title URL Company Industry CompanyType CompanySize Remark
		 * PostingDate Location Number Experience Education Function Description
		 * Saraly
		 */

		List<String> storeList = Arrays.asList(needStore);
		List<String> indexList = Arrays.asList(needAnalyze);

		for (Iterator<String> i = map.keySet().iterator(); i.hasNext();) {
			String key = i.next();
			String val = map.get(key);
			if (val != null && !val.equals("")) {
				doc.add(new Field(key, val, storeList.contains(key) ? Store.YES
						: Store.NO, indexList.contains(key) ? Index.ANALYZED
						: Index.NOT_ANALYZED));
			}
		}
		// doc.add(new Field("filename",map.get("filename"), Store.NO,
		// Index.NOT_ANALYZED));
		// doc.add(new Field("Title",map.get("Title"), Store.YES,
		// Index.ANALYZED));
		// doc.add(new Field("URL",map.get("URL"), Store.NO,
		// Index.NOT_ANALYZED));
		// doc.add(new Field("Company",map.get("Company"), Store.YES,
		// Index.ANALYZED));
		// doc.add(new Field("Industry",map.get("Industry"), Store.YES,
		// Index.ANALYZED));
		// doc.add(new Field("CompanyType",map.get("CompanyType"), Store.NO,
		// Index.NOT_ANALYZED));
		// doc.add(new Field("CompanySize",map.get("CompanySize"), Store.NO,
		// Index.NOT_ANALYZED));
		// doc.add(new Field("Remark",map.get("Remark"), Store.NO,
		// Index.NOT_ANALYZED));
		// doc.add(new Field("PostingDate",map.get("PostingDate"), Store.YES,
		// Index.NOT_ANALYZED));
		// doc.add(new Field("Location",map.get("Location"), Store.NO,
		// Index.NOT_ANALYZED));
		// doc.add(new Field("Experience",map.get("Experience"), Store.NO,
		// Index.NOT_ANALYZED));
		// doc.add(new Field("Education",map.get("Education"), Store.NO,
		// Index.NOT_ANALYZED));
		// doc.add(new Field("Function",map.get("Function"), Store.YES,
		// Index.ANALYZED));
		// doc.add(new Field("Description",map.get("Description"), Store.YES,
		// Index.ANALYZED));
		// doc.add(new Field("Saraly",map.get("Saraly"), Store.NO,
		// Index.NOT_ANALYZED));
		return doc;

	}

}
