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
	JTable book_table;
	static JTable member_table;
	private JTabbedPane tabbedPane;
	JScrollPane scrollPane_Mem;
	JScrollPane scrollPane_Book;
	Vector data_Book = new Vector();
	Vector data_Member = new Vector();
	Vector title_Book = new Vector();
	Vector title_Member = new Vector();

	public tabPanel_Left() {

	}

	public tabPanel_Left(String title) {
		init();
	}

	public void init() {
		readExcel readEx = new readExcel();
		tabR_infoView tbpr_iV = new tabR_infoView();
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

		data_Book = readEx.getVector();

		model_Book.setDataVector(data_Book, title_Book);
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
				}
			}
		});

	}

	public void setTable(Vector data_Member, Vector title_Member) {
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
