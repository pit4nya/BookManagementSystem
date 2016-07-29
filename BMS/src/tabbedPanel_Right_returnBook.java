import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import net.miginfocom.swing.MigLayout;

public class tabbedPanel_Right_returnBook {
	
	JPanel rentBook;
	JPanel inner_rentBook;
	JTextPane tp_memInfo;
	JTextPane tp_bookInfo;
	JPanel mem_Info;
	JTextPane tp_memNum;
	JTextField tf_memTel;
	JTextField tf_memNum;
	JTextPane tp_memName;
	JTextPane tp_memTel;
	JTextPane tp_Memo_Up;
	JTextField tf_memName;
	JScrollPane sp_Memo_Up;
	JButton bt_memSearch;
	JPanel book_Info;
	JScrollPane sp_Memo_Down;
	JTextPane tp_Memo_Down;
	JTextField tf_Author;
	JTextField tf_bookNum;
	JTextField tf_bookName;
	JTextField tf_Pub;
	JTextPane tp_Author;
	JTextPane tp_bookNum;
	JTextPane tp_Pub;
	JButton bt_rentSave;
	JButton bt_bookSearch;
	JPanel blank;
	JTextPane textPane_31;
	
	public tabbedPanel_Right_returnBook() {

	}

	public tabbedPanel_Right_returnBook(String title) {
		init();
	}

	public void init() {
		rentBook = new JPanel();
		rentBook.setLayout(new MigLayout("", "[grow]", "[555.00,grow][grow]"));

		inner_rentBook = new JPanel();
		rentBook.add(inner_rentBook, "cell 0 0,grow");
		inner_rentBook.setLayout(null);

		tp_memInfo = new JTextPane();
		tp_memInfo.setText("회원정보");
		tp_memInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		tp_memInfo.setBounds(22, 10, 62, 23);
		inner_rentBook.add(tp_memInfo);

		tp_bookInfo = new JTextPane();
		tp_bookInfo.setText("도서정보");
		tp_bookInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		tp_bookInfo.setBounds(22, 280, 62, 23);
		inner_rentBook.add(tp_bookInfo);

		mem_Info = new JPanel();
		mem_Info.setLayout(null);
		mem_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mem_Info.setBackground(Color.WHITE);
		mem_Info.setBounds(12, 21, 548, 249);
		inner_rentBook.add(mem_Info);

		tp_memNum = new JTextPane();
		tp_memNum.setText("회원번호");
		tp_memNum.setBounds(12, 32, 54, 21);
		mem_Info.add(tp_memNum);

		tf_memNum = new JTextField();
		tf_memNum.setColumns(10);
		tf_memNum.setBounds(79, 32, 187, 21);
		mem_Info.add(tf_memNum);

		tp_memName = new JTextPane();
		tp_memName.setText("회원명");
		tp_memName.setBounds(280, 32, 42, 21);
		mem_Info.add(tp_memName);

		tp_memTel = new JTextPane();
		tp_memTel.setText("전화번호");
		tp_memTel.setBounds(12, 63, 54, 21);
		mem_Info.add(tp_memTel);

		tf_memTel = new JTextField();
		tf_memTel.setColumns(10);
		tf_memTel.setBounds(79, 63, 187, 21);
		mem_Info.add(tf_memTel);

		tp_Memo_Up = new JTextPane();
		tp_Memo_Up.setText("메      모");
		tp_Memo_Up.setBounds(12, 96, 54, 21);
		mem_Info.add(tp_Memo_Up);

		tf_memName = new JTextField();
		tf_memName.setColumns(10);
		tf_memName.setBounds(334, 32, 187, 21);
		mem_Info.add(tf_memName);

		sp_Memo_Up = new JScrollPane();
		sp_Memo_Up.setBounds(79, 98, 443, 137);
		mem_Info.add(sp_Memo_Up);

		bt_memSearch = new JButton("검색");
		bt_memSearch.setBounds(334, 61, 187, 23);
		mem_Info.add(bt_memSearch);

		book_Info = new JPanel();
		book_Info.setLayout(null);
		book_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		book_Info.setBackground(Color.WHITE);
		book_Info.setBounds(12, 294, 548, 298);
		inner_rentBook.add(book_Info);

		sp_Memo_Down = new JScrollPane();
		sp_Memo_Down.setBounds(79, 88, 443, 137);
		book_Info.add(sp_Memo_Down);

		tp_Memo_Down = new JTextPane();
		tp_Memo_Down.setText("메      모");
		tp_Memo_Down.setBounds(12, 86, 54, 21);
		book_Info.add(tp_Memo_Down);

		tf_Author = new JTextField();
		tf_Author.setColumns(10);
		tf_Author.setBounds(79, 55, 187, 21);
		book_Info.add(tf_Author);

		tp_Author = new JTextPane();
		tp_Author.setText("저자");
		tp_Author.setBounds(12, 55, 54, 21);
		book_Info.add(tp_Author);

		tp_bookNum = new JTextPane();
		tp_bookNum.setText("도서번호");
		tp_bookNum.setBounds(12, 24, 54, 21);
		book_Info.add(tp_bookNum);

		tf_bookNum = new JTextField();
		tf_bookNum.setColumns(10);
		tf_bookNum.setBounds(79, 24, 187, 21);
		book_Info.add(tf_bookNum);

		tf_bookName = new JTextField();
		tf_bookName.setColumns(10);
		tf_bookName.setBounds(334, 24, 187, 21);
		book_Info.add(tf_bookName);

		tf_Pub = new JTextField();
		tf_Pub.setColumns(10);
		tf_Pub.setBounds(334, 55, 187, 21);
		book_Info.add(tf_Pub);

		tp_Pub = new JTextPane();
		tp_Pub.setText("출판사");
		tp_Pub.setBounds(280, 55, 42, 21);
		book_Info.add(tp_Pub);

		textPane_31 = new JTextPane();
		textPane_31.setText("도서명");
		textPane_31.setBounds(280, 24, 42, 21);
		book_Info.add(textPane_31);

		bt_rentSave = new JButton("저장");
		bt_rentSave.setBounds(425, 249, 97, 23);
		book_Info.add(bt_rentSave);

		bt_bookSearch = new JButton("검색");
		bt_bookSearch.setBounds(316, 249, 97, 23);
		book_Info.add(bt_bookSearch);

		blank = new JPanel();
		rentBook.add(blank, "cell 0 1,grow");
		blank.setLayout(null);
	}
	public JPanel getPanel() {
		return rentBook;
	}
}
