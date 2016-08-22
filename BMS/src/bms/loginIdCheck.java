package bms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class loginIdCheck extends JFrame {

	static Connection con = null;
	static PreparedStatement pstmt = null;
	static Properties pro = null;
	static ResultSet rs = null;

	boolean check = false;
	String dbPass;

	public boolean loginCheck(String id, String pass) throws FileNotFoundException, IOException {

		try {

			pro = new Properties();
			pro.load(new FileInputStream("src/properties/member.properties"));
			con = ConnectionUtil.getConnection();
			pstmt = con.prepareStatement(pro.getProperty("member_select"));

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			rs.next();
			dbPass = rs.getString("pass");

			if (pass.equals(dbPass)) {
				if (rs.getInt("permission") != 1) {
					JOptionPane.showMessageDialog(null, "������ �����ϴ�.");
					return false;
				}
				JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�.");
				mainFrame main = new mainFrame("BookManagementSystem Version 1.0");
				main.setVisible(true);
				return true;
			} else if (!pass.equals(dbPass)) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				return false;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "���̵� �������� �ʽ��ϴ�.");
		}
		return false;
	}
}
