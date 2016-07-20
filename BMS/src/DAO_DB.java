
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

public class DAO_DB {

	static Scanner in = new Scanner(System.in);
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static Properties pro = null;
	static ResultSet rs = null;

	DAO_DB() {
		try {
			con = ConnectionUtil.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Member member) throws FileNotFoundException, IOException {
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));

			pstmt = con.prepareStatement(pro.getProperty("member_insert"));
			pstmt.setInt(1, member.getNum());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getTel());
			pstmt.setString(4, member.getAddr());
			pstmt.setString(5, member.getEmail());
			
			int n = pstmt.executeUpdate();
			System.out.println(n + "개의 행이 수정되었습니다.");

			con.commit();
			discardConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modify(Book book) {
		try {
			
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));
			System.out.println("수정할 도서의 번호를 입력하세요");
			int num = in.nextInt();
			in.nextLine();
			String str;
			book.setNum(num);
			System.out.println(num + "번 책의 이름 입력");
			str = in.nextLine();
			book.setName(str);
			System.out.println(num + "번 책의 저자 입력");
			str = in.nextLine();
			book.setAuthor(str);
			System.out.println(num + "번의 수학점수 입력");
			str = in.nextLine();
			book.setPub(str);

			pstmt = con.prepareStatement(pro.getProperty("book_update"));
			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPub());
			pstmt.setInt(4, book.getNum());

			int n = pstmt.executeUpdate();
			System.out.println(n + "개의 행이 수정되었습니다.");

			con.commit();
			discardConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(Book book) {
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));
			System.out.println("삭제할 학생의 학번을 입력하세요");
			int num = in.nextInt();
			in.nextLine();
			pstmt = con.prepareStatement(pro.getProperty("book_delete"));
			pstmt.setInt(1, num);

			int n = pstmt.executeUpdate();
			System.out.println(n + "개의 행이 삭제되었습니다.");
			con.commit();
			discardConnection();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public void discardConnection() {
		try {
			con.commit();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e1) {
			try {
				System.out.println("롤백!");
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
	}

	public static void printStudent(Vector<Book> List) {
		for (int i = 0; i < List.size(); i++) {
			Book tmp = List.get(i);
			System.out.println("책 번호: " + tmp.getNum() + "\n" + "이름: " + tmp.getName() + "\n" + "저자: " + tmp.getAuthor() + " \n" + "출판사: " + tmp.getPub());
			System.out.println("/////////////");
		}
	}

	public Vector book_selectAll() {
		Vector bookList = new Vector();
		Vector retVec = new Vector();
		

		int temp_int;
		String temp_str;
		
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));
			pstmt = con.prepareStatement(pro.getProperty("book_selectAll"));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector dataVec = new Vector();
				Book tmp = new Book();
				tmp.setNum(Integer.parseInt(rs.getString("num")));
				temp_int = Integer.parseInt(rs.getString("num"));
				dataVec.add(temp_int);
				tmp.setName(rs.getString("name"));
				temp_str = rs.getString("name");
				dataVec.add(temp_str);
//				System.out.println(temp_str);
				tmp.setAuthor(rs.getString("author"));
				temp_str = rs.getString("author");
				dataVec.add(temp_str);
				tmp.setPub(rs.getString("pub"));
				temp_str = rs.getString("pub");
				dataVec.add(temp_str);
				bookList.add(tmp);
				retVec.add(dataVec);
			}
			printStudent(bookList);
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return retVec;
	}
	public Vector mem_selectAll() {
		Vector memList = new Vector();
		Vector retVec = new Vector();
		int temp_int;
		String temp_str;
		
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));
			pstmt = con.prepareStatement(pro.getProperty("member_selectAll"));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector dataVec = new Vector();
				Book tmp = new Book();
				tmp.setNum(Integer.parseInt(rs.getString("num")));
				temp_int = Integer.parseInt(rs.getString("num"));
				dataVec.add(temp_int);
				tmp.setName(rs.getString("name"));
				temp_str = rs.getString("name");
				dataVec.add(temp_str);
//				System.out.println(temp_str);
				tmp.setAuthor(rs.getString("tel"));
				temp_str = rs.getString("tel");
				dataVec.add(temp_str);
				tmp.setPub(rs.getString("addr"));
				temp_str = rs.getString("addr");
				dataVec.add(temp_str);
				tmp.setPub(rs.getString("email"));
				temp_str = rs.getString("email");
				dataVec.add(temp_str);
				memList.add(tmp);
				retVec.add(dataVec);
			}
			printStudent(memList);
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return retVec;
	}

}
