package vo;

public class searchPage {
	private int totalHits;
	private int currentPage;
	private int PageSize;
	
	public searchPage() {
		super();
		this.totalHits = 0;
		this.currentPage = 1;
		PageSize = 3;
	}

	public searchPage(int pageSize) {
		super();
		this.totalHits = 0;
		PageSize = pageSize;
		currentPage=1;
	}
	
	public int getTotalHits() {
		return totalHits;
	}
	public void setTotalHits(int totalHits) {
		this.totalHits = totalHits;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
}
