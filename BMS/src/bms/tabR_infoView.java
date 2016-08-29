package bms;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

public class tabR_infoView extends JTabbedPane {

	private JPanel mem_Info;
	private JPanel book_Info;
	private JPanel book_Mngt;
	private JPanel inner_bookMngt;

	private JLabel lb_bookInfo;
	private JLabel lb_memInfo;
	private JLabel lb_memNum;
	private static JTextField tf_memNum;
	private JLabel lb_memName;
	private static JTextField tf_memName;
	private JLabel lb_telNum;
	private static JTextField tf_telNum;
	private JLabel lb_bookNum;
	private static JTextField tf_bookNum;
	private JLabel lb_bookName;
	private static JTextField tf_bookName;
	private JLabel lb_Author;
	private static JTextField tf_Author;
	private JLabel lb_Pub;
	private static JTextField tf_Pub;
	private JLabel lb_Memo_Up;
	private JLabel lb_Memo_Down;
	private JScrollPane scrollPane_Up;
	private JScrollPane scrollPane_Down;
	private static JTextArea ta_Memo_Up;
	private static JTextArea ta_Memo_Down;
	private JPanel blank;

	public tabR_infoView() {
	}

	public tabR_infoView(String title) {
		init();
	}

	public void init() {
		book_Mngt = new JPanel();
		book_Mngt.setLayout(new MigLayout("", "[grow]", "[524.00,grow][grow]"));

		inner_bookMngt = new JPanel();
		inner_bookMngt.setBackground(Color.WHITE);
		inner_bookMngt.setLayout(null);
		book_Mngt.add(inner_bookMngt, "cell 0 0,grow");

		lb_memInfo = new JLabel();
		lb_memInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_memInfo.setText("회원정보");
		lb_memInfo.setBounds(22, 10, 62, 23);
		inner_bookMngt.add(lb_memInfo);

		lb_bookInfo = new JLabel();
		lb_bookInfo.setBounds(22, 280, 62, 23);
		lb_bookInfo.setText("도서정보");
		lb_bookInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		inner_bookMngt.add(lb_bookInfo);

		mem_Info = new JPanel();
		mem_Info.setBackground(Color.WHITE);
		mem_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mem_Info.setBounds(12, 21, 548, 249);
		mem_Info.setLayout(null);
		inner_bookMngt.add(mem_Info);

		lb_memNum = new JLabel();
		lb_memNum.setText("회원번호");
		lb_memNum.setBounds(12, 32, 54, 21);
		mem_Info.add(lb_memNum);

		tf_memNum = new JTextField();
		tf_memNum.setEditable(false);
		tf_memNum.setBounds(79, 32, 187, 21);
		tf_memNum.setColumns(10);
		mem_Info.add(tf_memNum);

		lb_memName = new JLabel();
		lb_memName.setText("회원명");
		lb_memName.setBounds(280, 32, 42, 21);
		mem_Info.add(lb_memName);

		tf_memName = new JTextField();
		tf_memName.setEditable(false);
		tf_memName.setColumns(10);
		tf_memName.setBounds(334, 32, 187, 21);
		mem_Info.add(tf_memName);

		lb_telNum = new JLabel();
		lb_telNum.setText("전화번호");
		lb_telNum.setBounds(12, 63, 54, 21);
		mem_Info.add(lb_telNum);

		tf_telNum = new JTextField();
		tf_telNum.setEditable(false);
		tf_telNum.setColumns(10);
		tf_telNum.setBounds(79, 63, 187, 21);
		mem_Info.add(tf_telNum);

		lb_Memo_Up = new JLabel();
		lb_Memo_Up.setText("메      모");
		lb_Memo_Up.setBounds(12, 96, 54, 21);
		mem_Info.add(lb_Memo_Up);

		scrollPane_Up = new JScrollPane();
		scrollPane_Up.setBounds(79, 98, 443, 137);
		mem_Info.add(scrollPane_Up);

		ta_Memo_Up = new JTextArea();
		ta_Memo_Up.setEditable(false);
		scrollPane_Up.setViewportView(ta_Memo_Up);

		book_Info = new JPanel();
		book_Info.setBackground(Color.WHITE);
		book_Info.setLayout(null);
		book_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		book_Info.setBounds(12, 294, 548, 298);
		inner_bookMngt.add(book_Info);

		lb_bookNum = new JLabel();
		lb_bookNum.setText("도서번호");
		lb_bookNum.setBounds(12, 24, 54, 21);
		book_Info.add(lb_bookNum);

		tf_bookNum = new JTextField();
		tf_bookNum.setEditable(false);
		tf_bookNum.setColumns(10);
		tf_bookNum.setBounds(79, 24, 187, 21);
		book_Info.add(tf_bookNum);

		lb_bookName = new JLabel();
		lb_bookName.setText("도서명");
		lb_bookName.setBounds(280, 24, 42, 21);
		book_Info.add(lb_bookName);

		tf_bookName = new JTextField();
		tf_bookName.setEditable(false);
		tf_bookName.setColumns(10);
		tf_bookName.setBounds(334, 24, 187, 21);
		book_Info.add(tf_bookName);

		lb_Author = new JLabel();
		lb_Author.setText("저자");
		lb_Author.setBounds(12, 55, 54, 21);
		book_Info.add(lb_Author);

		tf_Author = new JTextField();
		tf_Author.setEditable(false);
		tf_Author.setColumns(10);
		tf_Author.setBounds(79, 55, 187, 21);
		book_Info.add(tf_Author);

		lb_Pub = new JLabel();
		lb_Pub.setText("출판사");
		lb_Pub.setBounds(280, 55, 42, 21);
		book_Info.add(lb_Pub);

		tf_Pub = new JTextField();
		tf_Pub.setEditable(false);
		tf_Pub.setColumns(10);
		tf_Pub.setBounds(334, 55, 187, 21);
		book_Info.add(tf_Pub);

		lb_Memo_Down = new JLabel();
		lb_Memo_Down.setText("메      모");
		lb_Memo_Down.setBounds(12, 86, 54, 21);
		book_Info.add(lb_Memo_Down);

		scrollPane_Down = new JScrollPane();
		scrollPane_Down.setBounds(79, 88, 443, 137);
		book_Info.add(scrollPane_Down);

		ta_Memo_Down = new JTextArea();
		ta_Memo_Down.setEditable(false);
		scrollPane_Down.setViewportView(ta_Memo_Down);

		blank = new JPanel();
		blank.setLayout(null);
		book_Mngt.add(blank, "cell 0 1,grow");

	}

	public void setBookTextField(int num, String name, String author, String pub) {
		tf_bookNum.setText(Integer.toString(num));
		tf_bookName.setText(name);
		tf_Author.setText(author);
		tf_Pub.setText(pub);
	}

	public void setMemTextField(int num, String name, String tel) {
		tf_memNum.setText(Integer.toString(num));
		tf_memName.setText(name);
		tf_telNum.setText(tel);
	}

	public JPanel getPanel() {
		return book_Mngt;
	}
}
