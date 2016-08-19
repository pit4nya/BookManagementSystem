import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class searchFrame_Book_return extends JFrame {
	Toolkit tk = Toolkit.getDefaultToolkit();

	private JPanel pn_Info;
	private JTable book_table;
	private JScrollPane scrollPane_Mem;
	private tabR_returnBook tr;
	private Vector data_Book = new Vector();
	private Vector title_Book = new Vector();
	private Book book = new Book();

	public searchFrame_Book_return() {
	}

	public searchFrame_Book_return(String name) {
		super("도서 검색");
		setSize(500, 800);
		setLocation((tk.getScreenSize().width - getWidth()) / 2, (tk.getScreenSize().height - getHeight()) / 2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		init(name);
		this.setContentPane(pn_Info);
	}

	public void init(String name) {
		pn_Info = new JPanel();
		tr = new tabR_returnBook();
		scrollPane_Mem = new JScrollPane();

		DefaultTableModel model_book = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		book_table = new JTable(model_book);
		scrollPane_Mem.setViewportView(book_table);

		title_Book.add("도서번호");
		title_Book.add("도서명");
		title_Book.add("저자");
		title_Book.add("출판사");
		book.setName(name);
		// TextField 에서 text 받아와서 Book에 setName 해주고 book넘김

		DAO_DB dao_Book = new DAO_DB();
		data_Book = dao_Book.book_selectName(book);
		model_book.setDataVector(data_Book, title_Book);

		book_table.addMouseListener(new MyMouseListener_return() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					DAO_DB dao_Book = new DAO_DB();
					data_Book = dao_Book.book_selectName(book);

					Vector inner_Book = new Vector();
					inner_Book = (Vector) data_Book.elementAt(book_table.getSelectedRow());

					tr.setBookTextField(Integer.parseInt(inner_Book.elementAt(0).toString()),
							inner_Book.elementAt(1).toString(), inner_Book.elementAt(2).toString(),
							inner_Book.elementAt(3).toString());
					setVisible(false);
				} // 더블클릭
			}
		});

		pn_Info.add(scrollPane_Mem);
	}

}

abstract class MyMouseListener_return extends MouseAdapter {
	abstract public void mouseClicked(MouseEvent e);
}
