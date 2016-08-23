package bms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.Timer;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class tabPanel_Left extends JTabbedPane {
	static JTable book_table;
	static JTable isRented_table;
	static JTable member_table;

	private JTabbedPane tabbedPane;
	JScrollPane scrollPane_Mem;
	JScrollPane scrollPane_Book;
	JScrollPane scrollPane_isRented;
	Vector data_Book = new Vector();
	Vector title_Book = new Vector();
	Vector data_Member = new Vector();
	Vector title_Member = new Vector();
	Vector data_rentinfo = new Vector();
	Vector title_rentinfo = new Vector();

	public tabPanel_Left() {
	}

	public tabPanel_Left(String title) {
		init();
	}

	public void init() {
		// readExcel readEx = new readExcel();
		tabR_infoView tbpr_iV = new tabR_infoView();
		tabR_rentBook tbpr_rent = new tabR_rentBook();
		tabR_returnBook tbpr_return = new tabR_returnBook();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		scrollPane_Mem = new JScrollPane();
		tabbedPane.addTab("회원현황", null, scrollPane_Mem, null);

		DefaultTableModel model_Member = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		member_table = new JTable(model_Member);
		scrollPane_Mem.setViewportView(member_table);
		title_Member.add("회원번호");
		title_Member.add("회원명");
		title_Member.add("전화번호");
		title_Member.add("주소");
		title_Member.add("이메일");
		title_Member.add("ID");

		DAO_DB dao_Member = new DAO_DB();
		data_Member = dao_Member.mem_selectAll();
		model_Member.setDataVector(data_Member, title_Member);

		member_table.addMouseListener(new MyMouseListener_Member() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					DAO_DB dao_Member = new DAO_DB();
					data_Member = dao_Member.mem_selectAll();

					Vector inner_Member = new Vector();
					inner_Member = (Vector) data_Member.elementAt(member_table.getSelectedRow());
					tbpr_iV.setMemTextField((int) inner_Member.elementAt(0), (String) inner_Member.elementAt(1),
							(String) inner_Member.elementAt(2));
					tbpr_rent.setMemTextField(Integer.parseInt(inner_Member.elementAt(0).toString()),
							(String) inner_Member.elementAt(1), (String) inner_Member.elementAt(2));
					tbpr_return.setMemTextField(Integer.parseInt(inner_Member.elementAt(0).toString()),
							(String) inner_Member.elementAt(1), (String) inner_Member.elementAt(2));
				}
			}
		});
		scrollPane_Book = new JScrollPane();
		tabbedPane.addTab("도서현황", null, scrollPane_Book, null);

		DefaultTableModel model_Book = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		book_table = new JTable(model_Book);

		Book book = new Book();
		title_Book.add("도서번호");
		title_Book.add("도서명");
		title_Book.add("저자");
		title_Book.add("출판사");

		DAO_DB dao_Book = new DAO_DB();
		data_Book = dao_Book.book_selectAll();
		model_Book.setDataVector(data_Book, title_Book);
		// data_Book = readEx.getVector();

		// model_Book.setDataVector(data_Book, title_Book);
		scrollPane_Book.setViewportView(book_table);

		book_table.addMouseListener(new MyMouseListener_Book() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					DAO_DB dao_Book = new DAO_DB();
					data_Book = dao_Book.book_selectAll();

					Vector inner_Book = new Vector();
					inner_Book = (Vector) data_Book.elementAt(book_table.getSelectedRow());
					tbpr_iV.setBookTextField(Integer.parseInt(inner_Book.elementAt(0).toString()),
							(String) inner_Book.elementAt(1), (String) inner_Book.elementAt(2),
							(String) inner_Book.elementAt(3));

					tbpr_rent.setBookTextField(Integer.parseInt(inner_Book.elementAt(0).toString()),
							(String) inner_Book.elementAt(1), (String) inner_Book.elementAt(2),
							(String) inner_Book.elementAt(3));

					tbpr_return.setBookTextField(Integer.parseInt(inner_Book.elementAt(0).toString()),
							(String) inner_Book.elementAt(1), (String) inner_Book.elementAt(2),
							(String) inner_Book.elementAt(3));
				}
			}
		});

		scrollPane_isRented = new JScrollPane();
		tabbedPane.addTab("대여중", null, scrollPane_isRented, null);

		DefaultTableModel model_isRented = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		isRented_table = new JTable(model_isRented);

		rentInfo rentinfo = new rentInfo();
		title_rentinfo.add("번호");
		title_rentinfo.add("도서번호");
		title_rentinfo.add("회원번호");
		title_rentinfo.add("대여일");
		title_rentinfo.add("반납일");

		DAO_DB dao_rentInfo = new DAO_DB();
		data_rentinfo = dao_rentInfo.rentedBook_selectAll();
		model_isRented.setDataVector(data_rentinfo, title_rentinfo);
		scrollPane_isRented.setViewportView(isRented_table);

		isRented_table.addMouseListener(new MyMouseListener_Book() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					int num = 0;
					num = ((int) isRented_table.getValueAt(isRented_table.getSelectedRow(), 1));
					Book book = new Book();
					book.setNum(num);
					DAO_DB dao_Book = new DAO_DB();
					Vector inner_Book = new Vector();
					inner_Book = dao_Book.book_selectNum(book);

					num = ((int) isRented_table.getValueAt(isRented_table.getSelectedRow(), 2));
					Member member = new Member();
					member.setNum(num);
					DAO_DB dao_Mem = new DAO_DB();
					Vector inner_Mem = new Vector();
					inner_Mem = dao_Mem.mem_selectNum(member);

					tbpr_iV.setBookTextField(Integer.parseInt(inner_Book.elementAt(0).toString()),
							(String) inner_Book.elementAt(1), (String) inner_Book.elementAt(2),
							(String) inner_Book.elementAt(3));
					tbpr_iV.setMemTextField(Integer.parseInt(inner_Mem.elementAt(0).toString()),
							(String) inner_Mem.elementAt(1), (String) inner_Mem.elementAt(2));

					tbpr_rent.setBookTextField(Integer.parseInt(inner_Book.elementAt(0).toString()),
							(String) inner_Book.elementAt(1), (String) inner_Book.elementAt(2),
							(String) inner_Book.elementAt(3));

					tbpr_rent.setMemTextField(Integer.parseInt(inner_Mem.elementAt(0).toString()),
							(String) inner_Mem.elementAt(1), (String) inner_Mem.elementAt(2));

					tbpr_return.setBookTextField(Integer.parseInt(inner_Book.elementAt(0).toString()),
							(String) inner_Book.elementAt(1), (String) inner_Book.elementAt(2),
							(String) inner_Book.elementAt(3));

					tbpr_return.setMemTextField(Integer.parseInt(inner_Mem.elementAt(0).toString()),
							(String) inner_Mem.elementAt(1), (String) inner_Mem.elementAt(2));
				}
			}
		});
	}

	public void setMemberTable(Vector data_Member, Vector title_Member) {
		DefaultTableModel model_Temp = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		member_table.setModel(model_Temp);
		model_Temp.setDataVector(data_Member, title_Member);
	}
	
	public void setBookTable(Vector data_Book, Vector title_Book) {
		DefaultTableModel model_Temp = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		book_table.setModel(model_Temp);
		model_Temp.setDataVector(data_Book, title_Book);
	}

	public void set_rentinfoTable(Vector data_rentinfo, Vector title_rentinfo) {
		DefaultTableModel model_Temp = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		isRented_table.setModel(model_Temp);
		model_Temp.setDataVector(data_rentinfo, title_rentinfo);
	}

	public JTabbedPane getPanel() {
		return tabbedPane;
	}
}

abstract class MyMouseListener_Book extends MouseAdapter {
	abstract public void mouseClicked(MouseEvent e);
}

abstract class MyMouseListener_Member extends MouseAdapter {
	abstract public void mouseClicked(MouseEvent e);
}
