package bms;

public class rentInfo {

	private int num;
	private int bookNum;
	private int memNum;
	private String rentDate;
	private String returnDate;

	public rentInfo() {
	}

	public rentInfo(int _num, int _bookNum, int _memNum, String _rentDate, String _returnDate) {
		this.num = _num;
		this.bookNum = _bookNum;
		this.memNum = _memNum;
		this.rentDate = _rentDate;
		this.returnDate = _returnDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getmemNum() {
		return memNum;
	}

	public void setmemNum(int memNum) {
		this.memNum = memNum;
	}

	public int getbookNum() {
		return bookNum;
	}

	public void setbookNum(int bookNum) {
		this.bookNum = bookNum;
	}

}
