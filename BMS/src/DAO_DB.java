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

//DB�� ������ϴ� Class !
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

	///////////////////////////////////////////////////// insert �̰Ŵ� ȸ�� ���� �� ���
	public void insert_Member(Member member) throws FileNotFoundException, IOException {
		try {
			// member.properties�� �ִ� member_insert�� ? ���� 1 2 3 4 5�� �� �־
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
			// ���⼭ excuteUpdate�ؼ� Query�� ���� ��Ŵ.
			int n = pstmt.executeUpdate();

			// �Է� ���� ���� ����
			if (n != 0)
				System.out.println("�Է� ����.");
			else
				System.out.println("�Է� ����.");

			// Commit �� DAO ���� ����
			con.commit();
			discardConnection();

		} catch (SQLException e) {
			errMsg = e.getMessage();
			System.out.println(errMsg);
			if (errMsg.contains("unique")) {
				System.out.println("ȸ����ȣ �ߺ� �Ұ�!");
			} else {
				System.out.println("ȸ����ȣ �̸� ��ȭ��ȣ�� �ʼ� �Է�!");
			}
		}
	}

	////////////////////////////////////////////////// insert_Book �Լ��� å ���� �ֱ�
	////////////////////////////////////////////////// ���� ���
	public void insert_Book(Book book) throws FileNotFoundException, IOException {
		try {
			// book.properties�� �ִ� book_insert�� ? ���� 1 2 3 4 �� �� �־
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));

			pstmt = con.prepareStatement(pro.getProperty("book_insert"));
			pstmt.setInt(1, book.getNum());
			pstmt.setString(2, book.getName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPub());

			// Query�� ����
			int n = pstmt.executeUpdate();

			// ���� ����
			if (n == 1)
				System.out.println("�Է� ����.");
			else
				System.out.println("�Է� ����.");

			// Commit �� DAO ���� ����
			con.commit();
			discardConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLException");
		}
	}

	// ���� �Ⱦ�
	public void modify(Book book) {
		try {
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));
			System.out.println("������ ������ ��ȣ�� �Է��ϼ���");
			int num = in.nextInt();
			in.nextLine();
			String str;
			book.setNum(num);
			System.out.println(num + "�� å�� �̸� �Է�");
			str = in.nextLine();
			book.setName(str);
			System.out.println(num + "�� å�� ���� �Է�");
			str = in.nextLine();
			book.setAuthor(str);
			System.out.println(num + "���� �������� �Է�");
			str = in.nextLine();
			book.setPub(str);

			pstmt = con.prepareStatement(pro.getProperty("member_update"));
			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPub());
			pstmt.setInt(4, book.getNum());

			int n = pstmt.executeUpdate();
			if (n == 1)
				System.out.println("���� ����.");
			else
				System.out.println("���� ����.");
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

	// Member ���� �ϴ� �Լ�
	public void delete(Member member) {
		try {
			// member.properties�� �ִ� member_insert�� ? ���� 1 2�� �� �־
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));
			pstmt = con.prepareStatement(pro.getProperty("member_delete"));
			pstmt.setInt(1, member.getNum());
			pstmt.setString(2, member.getName());

			// Query�� ����
			int n = pstmt.executeUpdate();
			if (n != 0)
				System.out.println("���� ����.");
			else
				System.out.println("���� ����.");

			// Commit �� ���� ����
			con.commit();
			discardConnection();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	// �̰� å ��� DB ���� �ϴ� �Լ�
	public void deleteAll_Book() {
		try {

			// book.properties���� book_deleteAll ����
			pro = new Properties();
			pro.load(new FileInputStream("src/properties/book.properties"));
			pstmt = con.prepareStatement(pro.getProperty("book_deleteAll"));

			int n = pstmt.executeUpdate();

			// ���� ����
			if (n != 0)
				System.out.println("BOOK TABLE ��� ���� ����.");
			else
				System.out.println("BOOK TABLE ��� ���� ����.");

			// Commit �� ���� ����
			con.commit();
			discardConnection();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	// ���� ���� �ϴ� �Լ� ���� ���� �� rollback ��Ŵ
	public void discardConnection() {
		try {
			con.commit();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e1) {
			try {
				System.out.println("�ѹ�!");
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
	}

	// book_selectAll�� BOOK TABLE�� �ִ� ��� ���� �޾ƿͼ� Vector�� ��Ƽ� ��ȯ����
	// JTable�� �߰��ϱ� ���� �ʿ��� Vector����
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
				// rs�� ��� ��� �� �޾ƿ�
				Vector dataVec = new Vector();
				// rs�� �ִ� ������� Book Class�� dataVec�� ���� ����
				dataVec.add(Integer.parseInt(rs.getString("num")));
				dataVec.add(rs.getString("name"));
				dataVec.add(rs.getString("author"));
				dataVec.add(rs.getString("pub"));
				// ��� Book Class�� bookList�� ���� �׸��� dataVec�� retVec�� ���� �� return
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

	// ���� book_selectAll �Լ��� ���� ������� Member�� �ҷ���
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
				// dataVec.add(rs.getString("pass"));
				retVec.add(dataVec);
			}
		} catch (IOException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.discardConnection();
		return retVec;
	}

}
