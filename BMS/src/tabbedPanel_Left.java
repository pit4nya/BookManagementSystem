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

public class tabbedPanel_Left extends JTabbedPane {
	static JTable book_table;
	static JTable member_table;
	private JTabbedPane tabbedPane;
	JScrollPane scrollPane_Mem;
	JScrollPane scrollPane_Book;
	Vector data_Book = new Vector();
	Vector data_Member = new Vector();
	Vector title_Book = new Vector();
	Vector title_Member = new Vector();

	public tabbedPanel_Left() {

	}

	public tabbedPanel_Left(String title) {
		init();
	}

	public void init() {
		readExcel readEx = new readExcel();
		////////////////////////////////////////////////////////////////////
		tabbedPanel_Right_bookMngt tbprb = new tabbedPanel_Right_bookMngt();
		////////////////////////////////////////////////////////////////////
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		// 여기서 부터는 member_table
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

		// DB에 있는 모든 Member 불러와서 JTable에 붙임
		DAO_DB dao_Member = new DAO_DB();
		data_Member = dao_Member.mem_selectAll();
		model_Member.setDataVector(data_Member, title_Member);

		// 회원현황 table을 클릭 했을 때 실행되는 이벤트
		member_table.addMouseListener(new MyMouseListener_Member() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					DAO_DB dao_Member = new DAO_DB();
					data_Member = dao_Member.mem_selectAll();

					Vector inner_Member = new Vector();
					inner_Member = (Vector) data_Member.elementAt(member_table.getSelectedRow());
					tbprb.setMemTextField(Integer.parseInt(inner_Member.elementAt(0).toString()),
							inner_Member.elementAt(1).toString(), inner_Member.elementAt(2).toString());
				}
			}
		});

		scrollPane_Book = new JScrollPane();
		tabbedPane.addTab("도서현황", null, scrollPane_Book, null);

		///////////////////////////////////////////////////// 테이블 수정 안되게 막는 코드
		DefaultTableModel model_Book = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		////////////////////////////////////////////// 여긴 도서현황
		book_table = new JTable(model_Book);

		Book book = new Book();
		title_Book.add("도서번호");
		title_Book.add("도서명");
		title_Book.add("저자");
		title_Book.add("출판사");

		// readEx를 통해 Excel에 있는 파일에서 정보 다 받아옴
		data_Book = readEx.getVector();

		// JTable에 붙임
		model_Book.setDataVector(data_Book, title_Book);
		scrollPane_Book.setViewportView(book_table);

		//////////////////////////////////////////////////////////////////// 마우스
		//////////////////////////////////////////////////////////////////// 클릭
		//////////////////////////////////////////////////////////////////// 이벤트
		book_table.addMouseListener(new MyMouseListener_Book() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					DAO_DB dao_Book = new DAO_DB();
					data_Book = dao_Book.book_selectAll();
					Vector inner_Book = new Vector();
					inner_Book = (Vector) data_Book.elementAt(book_table.getSelectedRow());
					System.out.println(inner_Book.size());
					tbprb.setBookTextField(Integer.parseInt(inner_Book.elementAt(0).toString()),
							inner_Book.elementAt(1).toString(), inner_Book.elementAt(2).toString(),
							inner_Book.elementAt(3).toString());
				} // 클릭
			}
		});
		////////////////////////////////////////////////////////////////////// 여기까지
		////////////////////////////////////////////////////////////////////// book_table
	}

	// 이건 JTable -> 즉 회원형황을 수정 했을 때 바로 새로고침 하기 위해 만든 함수
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
		// 수정된 값들 테이블에 붙임 수정된 값들은 Parameter data_Member를 통해 들어옴
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
