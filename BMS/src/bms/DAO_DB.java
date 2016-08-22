package bms;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DAO_DB {

	// pstmt.setDate(1, new java.sql.Timestamp(dat.getTime()); // DB에 시간 입력
	static Scanner in = new Scanner(System.in);
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static Properties pro = null;
	static ResultSet rs = null;
	String errMsg;

	DAO_DB() {
		try {
			con = ConnectionUtil.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert_Member(Member member) throws FileNotFoundException, IOException {
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));

			pstmt = con.prepareStatement(pro.getProperty("member_insert"));
			pstmt.setInt(1, member.getNum());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getTel());
			pstmt.setString(4, member.getAddr());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getId());
			pstmt.setString(7, member.getPass());

			int n = pstmt.executeUpdate();

			if (n != 0)
				JOptionPane.showMessageDialog(null, "입력되었습니다.");
			else
				JOptionPane.showMessageDialog(null, "입력정보가 잘못되었습니다.");

			con.commit();
			discardConnection();

		} catch (SQLException e) {
			errMsg = e.getMessage();
			System.out.println(errMsg);
			if (errMsg.contains("unique")) {
				JOptionPane.showMessageDialog(null, "회원번호 중복 불가!");
			} else {
				JOptionPane.showMessageDialog(null, "회원번호 이름 전화번호는 필수 입력!");
			}
		}
	}

	public void insert_Book(Book book) throws FileNotFoundException, IOException {
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));

			pstmt = con.prepareStatement(pro.getProperty("book_insert"));
			pstmt.setInt(1, book.getNum());
			pstmt.setString(2, book.getName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPub());

			int n = pstmt.executeUpdate();

			if (n == 1)
				System.out.println("입력 성공.");
			else
				System.out.println("입력 실패.");

			con.commit();
			discardConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException");
		}
	}

	public void insert_rentBook(int num, Book book, Member member, String rentDate, String returnDate)
			throws FileNotFoundException, IOException {
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/rentedbook.properties"));

			pstmt = con.prepareStatement(pro.getProperty("rentedbook_insert"));
			pstmt.setInt(1, num);
			pstmt.setInt(2, book.getNum());
			pstmt.setInt(3, member.getNum());
			pstmt.setString(4, rentDate);
			pstmt.setString(5, returnDate);

			int n = pstmt.executeUpdate();

			if (n == 1)
				JOptionPane.showMessageDialog(null, "대여되었습니다.");
			else
				JOptionPane.showMessageDialog(null, "입력 정보가 잘못되었습니다.");

			con.commit();
			discardConnection();

		} catch (SQLException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "대여 중인 책입니다.");
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

			pstmt = con.prepareStatement(pro.getProperty("member_update"));
			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPub());
			pstmt.setInt(4, book.getNum());

			int n = pstmt.executeUpdate();
			if (n == 1)
				System.out.println("수정 성공.");
			else
				System.out.println("수정 실패.");
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

	public void delete(Member member) {
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));
			pstmt = con.prepareStatement(pro.getProperty("member_delete"));
			pstmt.setInt(1, member.getNum());
			pstmt.setString(2, member.getName());

			int n = pstmt.executeUpdate();
			if (n != 0)
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			else
				JOptionPane.showMessageDialog(null, "입력정보가 잘못되었습니다.");

			con.commit();
			discardConnection();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll_Book() {
		try {

			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));
			pstmt = con.prepareStatement(pro.getProperty("book_deleteAll"));

			int n = pstmt.executeUpdate();

			if (n != 0)
				System.out.println("BOOK TABLE 모두 삭제 성공.");
			else
				System.out.println("BOOK TABLE 모두 삭제 실패.");

			con.commit();
			discardConnection();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public void delete_rentBook(Book book, Member member) {
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/rentedbook.properties"));
			pstmt = con.prepareStatement(pro.getProperty("rentedbook_delete"));
			pstmt.setInt(1, book.getNum());
			pstmt.setInt(2, member.getNum());

			int n = pstmt.executeUpdate();
			if (n != 0) {
				JOptionPane.showMessageDialog(null, "반납되었습니다.");
			} else
				JOptionPane.showMessageDialog(null, "입력정보가 잘못되었습니다.");

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
				dataVec.add(Integer.parseInt(rs.getString("num")));
				dataVec.add(rs.getString("name"));
				dataVec.add(rs.getString("author"));
				dataVec.add(rs.getString("pub"));
				retVec.add(dataVec);
			}
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return retVec;
	}

	public Vector rentedBook_selectAll() {
		Vector bookList = new Vector();
		Vector retVec = new Vector();

		int temp_int;
		String temp_str;

		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/rentedbook.properties"));
			pstmt = con.prepareStatement(pro.getProperty("rentedbook_selectAll"));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector dataVec = new Vector();
				dataVec.add(Integer.parseInt(rs.getString("num")));
				dataVec.add(Integer.parseInt(rs.getString("booknum")));
				dataVec.add(Integer.parseInt(rs.getString("memnum")));
				dataVec.add(rs.getString("rentdate"));
				dataVec.add(rs.getString("returndate"));
				retVec.add(dataVec);
			}
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
				dataVec.add(Integer.parseInt(rs.getString("num")));
				dataVec.add(rs.getString("name"));
				dataVec.add(rs.getString("tel"));
				dataVec.add(rs.getString("addr"));
				dataVec.add(rs.getString("email"));
				dataVec.add(rs.getString("id"));
				retVec.add(dataVec);
			}
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return retVec;
	}

	public Vector mem_selectName(Member member) {
		Vector memList = new Vector();
		Vector retVec = new Vector();

		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));
			pstmt = con.prepareStatement(pro.getProperty("member_selectName"));
			pstmt.setString(1, member.getName());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector dataVec = new Vector();
				dataVec.add(Integer.parseInt(rs.getString("num")));
				dataVec.add(rs.getString("name"));
				dataVec.add(rs.getString("tel"));
				dataVec.add(rs.getString("addr"));
				dataVec.add(rs.getString("email"));
				dataVec.add(rs.getString("id"));
				retVec.add(dataVec);
			}
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return retVec;
	}

	public Vector mem_selectNum(Member member) {
		Vector memList = new Vector();
		Vector dataVec = new Vector();

		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));
			pstmt = con.prepareStatement(pro.getProperty("member_selectNum"));
			pstmt.setInt(1, member.getNum());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				dataVec.add(Integer.parseInt(rs.getString("num")));
				dataVec.add(rs.getString("name"));
				dataVec.add(rs.getString("tel"));
				dataVec.add(rs.getString("addr"));
				dataVec.add(rs.getString("email"));
				dataVec.add(rs.getString("id"));
			}
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return dataVec;
	}

	public Vector book_selectName(Book book) {
		Vector memList = new Vector();
		Vector retVec = new Vector();
		int temp_int;
		String temp_str;

		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));
			pstmt = con.prepareStatement(pro.getProperty("book_selectName"));
			pstmt.setString(1, book.getName());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector dataVec = new Vector();
				dataVec.add(Integer.parseInt(rs.getString("num")));
				dataVec.add(rs.getString("name"));
				dataVec.add(rs.getString("author"));
				dataVec.add(rs.getString("pub"));
				retVec.add(dataVec);
			}
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return retVec;
	}

	public Vector book_selectNum(Book book) {
		Vector memList = new Vector();
		Vector dataVec = new Vector();
		int temp_int;
		String temp_str;

		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));
			pstmt = con.prepareStatement(pro.getProperty("book_selectNum"));
			pstmt.setInt(1, book.getNum());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dataVec.add(Integer.parseInt(rs.getString("num")));
				dataVec.add(rs.getString("name"));
				dataVec.add(rs.getString("author"));
				dataVec.add(rs.getString("pub"));
			}
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return dataVec;
	}

}
