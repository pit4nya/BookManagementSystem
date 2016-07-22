import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

//DB에 입출력하는 Class !
public class DAO_DB {

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

	///////////////////////////////////////////////////// insert 이거는 회원 넣을 때 사용
	public void insert(Member member) throws FileNotFoundException, IOException {
		try {
			// member.properties에 있는 member_insert의 ? 값에 1 2 3 4 5의 값 넣어서
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));

			pstmt = con.prepareStatement(pro.getProperty("member_insert"));
			pstmt.setInt(1, member.getNum());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getTel());
			pstmt.setString(4, member.getAddr());
			pstmt.setString(5, member.getEmail());

			// 여기서 excuteUpdate해서 Query문 실행 시킴.
			int n = pstmt.executeUpdate();

			// 입력 성공 실패 여부
			if (n != 0)
				System.out.println("입력 성공.");
			else
				System.out.println("입력 실패.");

			// Commit 후 DAO 접속 해제
			con.commit();
			discardConnection();

		} catch (SQLException e) {
			errMsg = e.getMessage();
			System.out.println(errMsg);
			if (errMsg.contains("unique")) {
				System.out.println("회원번호 중복 불가!");
			} else {
				System.out.println("회원번호 이름 전화번호는 필수 입력!");
			}
		}
	}

	////////////////////////////////////////////////// insert_Book 함수는 책 정보 넣기
	////////////////////////////////////////////////// 위해 사용
	public void insert_Book(Book book) throws FileNotFoundException, IOException {
		try {
			// book.properties에 있는 book_insert의 ? 값에 1 2 3 4 의 값 넣어서
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));

			pstmt = con.prepareStatement(pro.getProperty("book_insert"));
			pstmt.setInt(1, book.getNum());
			pstmt.setString(2, book.getName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPub());

			// Query문 실행
			int n = pstmt.executeUpdate();

			// 성공 여부
			if (n == 1)
				System.out.println("입력 성공.");
			else
				System.out.println("입력 실패.");

			// Commit 후 DAO 접속 종료
			con.commit();
			discardConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException");
		}
	}

	// 아직 안씀
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

	// Member 삭제 하는 함수
	public void delete(Member member) {
		try {
			// member.properties에 있는 member_insert의 ? 값에 1 2의 값 넣어서
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));
			pstmt = con.prepareStatement(pro.getProperty("member_delete"));
			pstmt.setInt(1, member.getNum());
			pstmt.setString(2, member.getName());

			// Query문 실행
			int n = pstmt.executeUpdate();

			// Query문 성공 여부
			if (n != 0)
				System.out.println("삭제 성공.");
			else
				System.out.println("삭제 실패.");

			// Commit 후 접속 해제
			con.commit();
			discardConnection();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	// 이거 책 모든 DB 삭제 하는 함수
	public void deleteAll_Book() {
		try {

			// book.properties에서 book_deleteAll 실행
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));
			pstmt = con.prepareStatement(pro.getProperty("book_deleteAll"));

			int n = pstmt.executeUpdate();

			// 성공 여부
			if (n != 0)
				System.out.println("BOOK TABLE 모두 삭제 성공.");
			else
				System.out.println("BOOK TABLE 모두 삭제 실패.");

			// Commit 후 접속 해제
			con.commit();
			discardConnection();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	// 접속 해제 하는 함수 에러 있을 시 rollback 시킴
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

	// public static void printStudent(Vector<Book> List) {
	// for (int i = 0; i < List.size(); i++) {
	// Book tmp = List.get(i);
	// }
	// }

	// book_selectAll은 BOOK TABLE에 있는 모든 정보 받아와서 Vector에 담아서 반환해줌
	// JTable에 추가하기 위해 필요한 Vector생성
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
				// rs에 모든 결과 다 받아옴
				Vector dataVec = new Vector();
				Book tmp = new Book();

				// rs에 있는 결과들을 Book Class와 dataVec에 각각 담음
				tmp.setNum(Integer.parseInt(rs.getString("num")));
				temp_int = Integer.parseInt(rs.getString("num"));
				dataVec.add(temp_int);
				tmp.setName(rs.getString("name"));
				temp_str = rs.getString("name");
				dataVec.add(temp_str);
				tmp.setAuthor(rs.getString("author"));
				temp_str = rs.getString("author");
				dataVec.add(temp_str);
				tmp.setPub(rs.getString("pub"));
				temp_str = rs.getString("pub");
				dataVec.add(temp_str);

				// 모든 Book Class를 bookList에 저장 그리고 dataVec을 retVec에 저장 후 return
				bookList.add(tmp);
				retVec.add(dataVec);
			}
			// printStudent(bookList);
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return retVec;
	}

	// 위에 book_selectAll 함수와 같은 방식으로 Member다 불러옴
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
			// printStudent(memList);
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return retVec;
	}

}
