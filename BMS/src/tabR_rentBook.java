import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

public class tabR_rentBook {
	JPanel rentBook;
	JPanel inner_rentBook;
	JPanel mem_Info;
	JPanel book_Info;
	JPanel blank;

	JLabel lb_memInfo;
	JLabel lb_bookInfo;
	JLabel lb_memNum;
	JLabel lb_memName;
	JLabel lb_memTel;
	JLabel lb_Memo_Up;
	JLabel lb_Memo_Down;
	JLabel lb_bookNum;
	JLabel lb_bookName;
	JLabel lb_Author;
	JLabel lb_Pub;

	JScrollPane sp_Memo_Up;
	JScrollPane sp_Memo_Down;

	JTextField tf_memNum;
	JTextField tf_memName;
	JTextField tf_memTel;
	JTextField tf_Author;
	JTextField tf_bookNum;
	JTextField tf_bookName;
	JTextField tf_Pub;

	JButton bt_rentSave;
	JButton bt_memSearch;
	JButton bt_bookSearch;

	public tabR_rentBook() {

	}

	public tabR_rentBook(String title) {
		init();
	}

	public void init() {
		rentBook = new JPanel();
		rentBook.setLayout(new MigLayout("", "[grow]", "[524.00,grow][grow]"));

		inner_rentBook = new JPanel();
		inner_rentBook.setLayout(null);
		rentBook.add(inner_rentBook, "cell 0 0,grow");

		lb_memInfo = new JLabel();
		lb_memInfo.setText("회원정보");
		lb_memInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_memInfo.setBounds(22, 10, 62, 23);
		inner_rentBook.add(lb_memInfo);
		
		mem_Info = new JPanel();
		mem_Info.setLayout(null);
		mem_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mem_Info.setBackground(Color.WHITE);
		mem_Info.setBounds(12, 21, 548, 249);
		inner_rentBook.add(mem_Info);

		lb_bookInfo = new JLabel();
		lb_bookInfo.setText("도서정보");
		lb_bookInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_bookInfo.setBounds(22, 280, 62, 23);
		inner_rentBook.add(lb_bookInfo);

		lb_memNum = new JLabel();
		lb_memNum.setText("회원번호");
		lb_memNum.setBounds(12, 32, 54, 21);
		mem_Info.add(lb_memNum);

		tf_memNum = new JTextField();
		tf_memNum.setColumns(10);
		tf_memNum.setBounds(79, 32, 187, 21);
		mem_Info.add(tf_memNum);

		lb_memName = new JLabel();
		lb_memName.setText("회원명");
		lb_memName.setBounds(280, 32, 42, 21);
		mem_Info.add(lb_memName);

		tf_memName = new JTextField();
		tf_memName.setColumns(10);
		tf_memName.setBounds(334, 32, 187, 21);
		mem_Info.add(tf_memName);

		lb_memTel = new JLabel();
		lb_memTel.setText("전화번호");
		lb_memTel.setBounds(12, 63, 54, 21);
		mem_Info.add(lb_memTel);

		tf_memTel = new JTextField();
		tf_memTel.setColumns(10);
		tf_memTel.setBounds(79, 63, 187, 21);
		mem_Info.add(tf_memTel);

		lb_Memo_Up = new JLabel();
		lb_Memo_Up.setText("메      모");
		lb_Memo_Up.setBounds(12, 96, 54, 21);
		mem_Info.add(lb_Memo_Up);

		sp_Memo_Up = new JScrollPane();
		sp_Memo_Up.setBounds(79, 98, 443, 137);
		mem_Info.add(sp_Memo_Up);

		bt_memSearch = new JButton("검색");
		bt_memSearch.setBounds(334, 61, 187, 23);
		mem_Info.add(bt_memSearch);
		////////////////////////////////////////////// member information
		book_Info = new JPanel();
		book_Info.setLayout(null);
		book_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		book_Info.setBackground(Color.WHITE);
		book_Info.setBounds(12, 294, 548, 298);
		inner_rentBook.add(book_Info);

		lb_bookNum = new JLabel();
		lb_bookNum.setText("도서번호");
		lb_bookNum.setBounds(12, 24, 54, 21);
		book_Info.add(lb_bookNum);

		tf_bookNum = new JTextField();
		tf_bookNum.setColumns(10);
		tf_bookNum.setBounds(79, 24, 187, 21);
		book_Info.add(tf_bookNum);

		lb_bookName = new JLabel();
		lb_bookName.setText("도서명");
		lb_bookName.setBounds(280, 24, 42, 21);
		book_Info.add(lb_bookName);

		tf_bookName = new JTextField();
		tf_bookName.setColumns(10);
		tf_bookName.setBounds(334, 24, 187, 21);
		book_Info.add(tf_bookName);

		lb_Author = new JLabel();
		lb_Author.setText("저자");
		lb_Author.setBounds(12, 55, 54, 21);
		book_Info.add(lb_Author);

		tf_Author = new JTextField();
		tf_Author.setColumns(10);
		tf_Author.setBounds(79, 55, 187, 21);
		book_Info.add(tf_Author);

		lb_Pub = new JLabel();
		lb_Pub.setText("출판사");
		lb_Pub.setBounds(280, 55, 42, 21);
		book_Info.add(lb_Pub);

		tf_Pub = new JTextField();
		tf_Pub.setColumns(10);
		tf_Pub.setBounds(334, 55, 187, 21);
		book_Info.add(tf_Pub);

		sp_Memo_Down = new JScrollPane();
		sp_Memo_Down.setBounds(79, 88, 443, 137);
		book_Info.add(sp_Memo_Down);

		lb_Memo_Down = new JLabel();
		lb_Memo_Down.setText("메      모");
		lb_Memo_Down.setBounds(12, 86, 54, 21);
		book_Info.add(lb_Memo_Down);

		bt_bookSearch = new JButton("검색");
		bt_bookSearch.setBounds(316, 249, 97, 23);
		book_Info.add(bt_bookSearch);

		bt_rentSave = new JButton("저장");
		bt_rentSave.setBounds(425, 249, 97, 23);
		book_Info.add(bt_rentSave);

		blank = new JPanel();
		rentBook.add(blank, "cell 0 1,grow");
		blank.setLayout(null);
	}

	public JPanel getPanel() {
		return rentBook;
	}
}
