import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

public class tabR_infoView extends JTabbedPane {

	private JPanel mem_Info;
	private JPanel book_Info;
	private JPanel book_Mngt;
	private JPanel inner_bookMngt;

	private JLabel tp_bookInfo;
	private JLabel tp_memInfo;
	private JLabel tp_memNum;
	private static JTextField tf_memNum;
	private JLabel tp_memName;
	private static JTextField tf_memName;
	private JLabel tp_telNum;
	private static JTextField tf_telNum;
	private JLabel tp_bookNum;
	private static JTextField tf_bookNum;
	private JLabel tp_bookName;
	private static JTextField tf_bookName;
	private JLabel tp_Author;
	private static JTextField tf_Author;
	private JLabel tp_Pub;
	private static JTextField tf_Pub;
	private JLabel tp_Memo_Up;
	private JLabel tp_Memo_Down;
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
		book_Mngt.setLayout(new MigLayout("", "[grow]", "[571.00,grow][232.00,grow]"));

		inner_bookMngt = new JPanel();
		inner_bookMngt.setBackground(Color.WHITE);
		inner_bookMngt.setLayout(null);
		book_Mngt.add(inner_bookMngt, "cell 0 0,grow");

		tp_memInfo = new JLabel();
		tp_memInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		tp_memInfo.setText("회원정보");
		tp_memInfo.setBounds(24, 10, 62, 23);
		inner_bookMngt.add(tp_memInfo);

		tp_bookInfo = new JLabel();
		tp_bookInfo.setBounds(24, 280, 62, 23);
		tp_bookInfo.setText("도서정보");
		tp_bookInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		inner_bookMngt.add(tp_bookInfo);

		mem_Info = new JPanel();
		mem_Info.setBackground(Color.WHITE);
		mem_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mem_Info.setBounds(12, 24, 548, 249);
		mem_Info.setLayout(null);
		inner_bookMngt.add(mem_Info);

		tp_memNum = new JLabel();
		tp_memNum.setText("회원번호");
		tp_memNum.setBounds(12, 32, 54, 21);
		mem_Info.add(tp_memNum);

		tf_memNum = new JTextField();
		tf_memNum.setEditable(false);
		tf_memNum.setBounds(79, 32, 187, 21);
		tf_memNum.setColumns(10);
		mem_Info.add(tf_memNum);

		tp_memName = new JLabel();
		tp_memName.setText("회원명");
		tp_memName.setBounds(280, 32, 42, 21);
		mem_Info.add(tp_memName);

		tf_memName = new JTextField();
		tf_memName.setEditable(false);
		tf_memName.setColumns(10);
		tf_memName.setBounds(334, 32, 187, 21);
		mem_Info.add(tf_memName);

		tp_telNum = new JLabel();
		tp_telNum.setText("전화번호");
		tp_telNum.setBounds(12, 63, 54, 21);
		mem_Info.add(tp_telNum);

		tf_telNum = new JTextField();
		tf_telNum.setEditable(false);
		tf_telNum.setColumns(10);
		tf_telNum.setBounds(79, 63, 187, 21);
		mem_Info.add(tf_telNum);

		tp_Memo_Up = new JLabel();
		tp_Memo_Up.setText("메      모");
		tp_Memo_Up.setBounds(12, 96, 54, 21);
		mem_Info.add(tp_Memo_Up);

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
		book_Info.setBounds(12, 292, 548, 237);
		inner_bookMngt.add(book_Info);

		tp_bookNum = new JLabel();
		tp_bookNum.setText("도서번호");
		tp_bookNum.setBounds(12, 24, 54, 21);
		book_Info.add(tp_bookNum);

		tf_bookNum = new JTextField();
		tf_bookNum.setEditable(false);
		tf_bookNum.setColumns(10);
		tf_bookNum.setBounds(79, 24, 187, 21);
		book_Info.add(tf_bookNum);

		tp_bookName = new JLabel();
		tp_bookName.setText("도서명");
		tp_bookName.setBounds(280, 24, 42, 21);
		book_Info.add(tp_bookName);

		tf_bookName = new JTextField();
		tf_bookName.setEditable(false);
		tf_bookName.setColumns(10);
		tf_bookName.setBounds(334, 24, 187, 21);
		book_Info.add(tf_bookName);

		tp_Author = new JLabel();
		tp_Author.setText("저자");
		tp_Author.setBounds(12, 55, 54, 21);
		book_Info.add(tp_Author);

		tf_Author = new JTextField();
		tf_Author.setEditable(false);
		tf_Author.setColumns(10);
		tf_Author.setBounds(79, 55, 187, 21);
		book_Info.add(tf_Author);

		tp_Pub = new JLabel();
		tp_Pub.setText("출판사");
		tp_Pub.setBounds(280, 55, 42, 21);
		book_Info.add(tp_Pub);

		tf_Pub = new JTextField();
		tf_Pub.setEditable(false);
		tf_Pub.setColumns(10);
		tf_Pub.setBounds(334, 55, 187, 21);
		book_Info.add(tf_Pub);

		tp_Memo_Down = new JLabel();
		tp_Memo_Down.setText("메      모");
		tp_Memo_Down.setBounds(12, 86, 54, 21);
		book_Info.add(tp_Memo_Down);

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
