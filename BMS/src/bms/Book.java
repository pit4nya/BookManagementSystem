package bms;
public class Book {

	int num;
	String name;
	String author;
	String pub;

	public Book() {
	}

	public Book(int _num, String _name, String _author, String _pub) {
		this.num = _num;
		this.name = _name;
		this.author = _author;
		this.pub = _pub;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPub() {
		return pub;
	}

	public void setPub(String pub) {
		this.pub = pub;
	}

}
