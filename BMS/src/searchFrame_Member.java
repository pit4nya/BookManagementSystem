import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class searchFrame_Member extends JFrame {
	Toolkit tk = Toolkit.getDefaultToolkit();

	private JPanel pn_Info;
	private JTable member_table;
	private JScrollPane scrollPane_Mem;
	private tabR_memMngt tm;
	private Vector data_Member = new Vector();
	private Vector title_Member = new Vector();
	private Member member = new Member();

	public searchFrame_Member() {
	}

	public searchFrame_Member(String name) {
		super("회원 검색");
		setSize(500, 800);
		setLocation((tk.getScreenSize().width - getWidth()) / 2, (tk.getScreenSize().height - getHeight()) / 2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		init(name);
		this.setContentPane(pn_Info);
	}

	public void init(String name) {
		pn_Info = new JPanel();
		tm = new tabR_memMngt();
		scrollPane_Mem = new JScrollPane();

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
		// TextField 에서 text 받아와서 Member에 setName 해주고 member넘김\
		member.setName(name);
		DAO_DB dao_Member = new DAO_DB();
		data_Member = dao_Member.mem_selectName(member);
		model_Member.setDataVector(data_Member, title_Member);

		member_table.addMouseListener(new MyMouseListener_searchMember() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					DAO_DB dao_Member = new DAO_DB();
					data_Member = dao_Member.mem_selectName(member);
					
					Vector inner_Book = new Vector();
					inner_Book = (Vector) data_Member.elementAt(member_table.getSelectedRow());
					
					tm.setMemTextField(inner_Book.elementAt(0).toString(), inner_Book.elementAt(1).toString(),
							inner_Book.elementAt(2).toString(), inner_Book.elementAt(3).toString(),
							inner_Book.elementAt(4).toString());
					setVisible(false);
				} // 더블클릭
			}
		});

		pn_Info.add(scrollPane_Mem);
	}

}

abstract class MyMouseListener_searchMember extends MouseAdapter {
	abstract public void mouseClicked(MouseEvent e);
}
