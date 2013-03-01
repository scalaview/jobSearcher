package main;

import builder.dao.lucence.SearchDAO;

public class IndexTest {

	static String[] fields = { "Title", "URL", "Company", "Industry",
			"CompanyType", "CompanySize", "Remark", "PostingDate", "Location",
			"Number", "Experience", "Education", "Function", "Description",
			"Salary"
	};

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SearchDAO s=new SearchDAO();
		
		s.search(s.getQuery("本科", new String[]{"Description"}), 1, 5);
	}

}
