package bms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.RowId;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

public class tabR_returnBook {
	JPanel returnBook;
	JPanel inner_returnBook;
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

	JTextArea ta_Memo_Up;
	JTextArea ta_Memo_Down;

	JScrollPane sp_Memo_Up;
	JScrollPane sp_Memo_Down;

	static JTextField tf_memNum;
	static JTextField tf_memName;
	static JTextField tf_memTel;

	static JTextField tf_Author;
	static JTextField tf_bookNum;
	static JTextField tf_bookName;
	static JTextField tf_Pub;

	JButton bt_return;
	JButton bt_memSearch;
	JButton bt_bookSearch;

	Book book;
	Member member;

	Vector title_rentinfo = new Vector();
	Vector data_rentinfo = new Vector();

	tabPanel_Left tbpl = new tabPanel_Left();

	public tabR_returnBook() {
	}

	public tabR_returnBook(String title) {
		init();
	}

	public void init() {
		book = new Book();
		member = new Member();

		returnBook = new JPanel();
		returnBook.setLayout(new MigLayout("", "[grow]", "[524.00,grow][grow]"));

		inner_returnBook = new JPanel();
		inner_returnBook.setBackground(Color.WHITE);
		inner_returnBook.setLayout(null);
		returnBook.add(inner_returnBook, "cell 0 0,grow");

		lb_memInfo = new JLabel();
		lb_memInfo.setText("회원정보");
		lb_memInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_memInfo.setBounds(22, 10, 62, 23);
		inner_returnBook.add(lb_memInfo);

		mem_Info = new JPanel();
		mem_Info.setLayout(null);
		mem_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mem_Info.setBackground(Color.WHITE);
		mem_Info.setBounds(12, 21, 548, 249);
		inner_returnBook.add(mem_Info);

		lb_bookInfo = new JLabel();
		lb_bookInfo.setText("도서정보");
		lb_bookInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_bookInfo.setBounds(22, 280, 62, 23);
		inner_returnBook.add(lb_bookInfo);

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
		ta_Memo_Up = new JTextArea();
		ta_Memo_Up.setEditable(false);
		sp_Memo_Up.setViewportView(ta_Memo_Up);
		mem_Info.add(sp_Memo_Up);

		bt_memSearch = new JButton("검색");
		bt_memSearch.setBounds(334, 61, 187, 23);
		mem_Info.add(bt_memSearch);
		bt_memSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == bt_memSearch) {
					searchFrame_Member_return sfm = new searchFrame_Member_return(tf_memName.getText());
					sfm.setVisible(true);
				}
			}
		});

		book_Info = new JPanel();
		book_Info.setLayout(null);
		book_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		book_Info.setBackground(Color.WHITE);
		book_Info.setBounds(12, 294, 548, 298);
		inner_returnBook.add(book_Info);

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
		sp_Memo_Down.setBounds(79, 98, 443, 137);
		ta_Memo_Down = new JTextArea();
		ta_Memo_Down.setEditable(false);
		sp_Memo_Down.setViewportView(ta_Memo_Down);
		book_Info.add(sp_Memo_Down);

		lb_Memo_Down = new JLabel();
		lb_Memo_Down.setText("메      모");
		lb_Memo_Down.setBounds(12, 86, 54, 21);
		book_Info.add(lb_Memo_Down);

		bt_bookSearch = new JButton("검색");
		bt_bookSearch.setBounds(316, 249, 97, 23);
		book_Info.add(bt_bookSearch);
		bt_bookSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == bt_bookSearch) {
					searchFrame_Book_return sfb = new searchFrame_Book_return(tf_bookName.getText());
					sfb.setVisible(true);
				}
			}
		});

		bt_return = new JButton("반납");
		bt_return.setBounds(425, 249, 97, 23);
		book_Info.add(bt_return);

		title_rentinfo.add("번호");
		title_rentinfo.add("도서번호");
		title_rentinfo.add("회원번호");
		title_rentinfo.add("대여일");
		title_rentinfo.add("반납일");

		bt_return.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == bt_return) {

					book.setNum(Integer.parseInt(tf_bookNum.getText().toString()));
					member.setNum(Integer.parseInt(tf_memNum.getText().toString()));
					// DB TABLE에 BOOKNUM UNIQUE로 바꾸고 NUM 없는 값 찾아서 알아서 입력 하도록

					DAO_DB delete_rentInfo = new DAO_DB();
					delete_rentInfo.delete_rentBook(book, member);

					DAO_DB db_Refresh_Insert = new DAO_DB();
					data_rentinfo = db_Refresh_Insert.rentedBook_selectAll();
					tbpl.set_rentinfoTable(data_rentinfo, title_rentinfo);
					setrentinfoClear();
				}
			}
		});

		blank = new JPanel();
		returnBook.add(blank, "cell 0 1,grow");
		blank.setLayout(null);
	}

	public JPanel getPanel() {
		return returnBook;
	}

	public void setrentinfoClear() {
		tf_memNum.setText("");
		tf_memName.setText("");
		tf_memTel.setText("");
		tf_bookNum.setText("");
		tf_bookName.setText("");
		tf_Author.setText("");
		tf_Pub.setText("");
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
		tf_memTel.setText(tel);
	}

}