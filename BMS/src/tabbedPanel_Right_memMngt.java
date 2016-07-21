import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class tabbedPanel_Right_memMngt extends JFrame {

	private JPanel mem_Mngt;
	private JPanel inner_bookMngt;
	JTextPane tp_memInfo;
	private JPanel mem_Info;
	private JTextPane tp_Email;
	private JTextField tf_Email;
	private JTextPane tp_Addr;
	private JTextField tf_Addr;
	private JTextPane tp_Memo;
	private JScrollPane sp_Memo;
	private JTextPane tp_memGrade;
	private JComboBox cb_memGrade;
	private JTextPane tp_memNum;
	private JTextField tf_memNum;
	private JButton newButton;
	private JTextPane tp_memName;
	private JTextField tf_memName;
	private JTextField tf_memTel;
	private JTextPane tp_memTel;
	private JTextArea ta_Memo;
	JButton button_Delete;
	JButton button_Save;
	JButton button_Search;
	Member member = new Member();
	tabbedPanel_Left tbpl = new tabbedPanel_Left();
	Vector data_Member = new Vector();
	Vector title_Member = new Vector();
	Vector use_numCalc = new Vector();
	int num;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tabbedPanel_Right_memMngt frame = new tabbedPanel_Right_memMngt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public tabbedPanel_Right_memMngt() {
		mem_Mngt = new JPanel();
		mem_Mngt.setLayout(new MigLayout("", "[grow]", "[524.00,grow][grow]"));

		inner_bookMngt = new JPanel();
		inner_bookMngt.setLayout(null);
		mem_Mngt.add(inner_bookMngt, "cell 0 0,grow");

		tp_memInfo = new JTextPane();
		tp_memInfo.setText("회원정보");
		tp_memInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		tp_memInfo.setBounds(24, 10, 62, 23);
		tp_memInfo.setEditable(false);
		inner_bookMngt.add(tp_memInfo);

		mem_Info = new JPanel();
		mem_Info.setLayout(null);
		mem_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mem_Info.setBackground(Color.WHITE);
		mem_Info.setBounds(12, 22, 548, 544);
		inner_bookMngt.add(mem_Info);
		///////////////////////////////////////////////////////////////////////////
		tp_memGrade = new JTextPane();
		tp_memGrade.setText("회원등급");
		tp_memGrade.setBounds(12, 24, 54, 21);
		tp_memGrade.setEditable(false);
		mem_Info.add(tp_memGrade);

		cb_memGrade = new JComboBox();
		cb_memGrade.setModel(new DefaultComboBoxModel(new String[] { "일반", "VIP" }));
		cb_memGrade.setBounds(79, 24, 187, 21);
		mem_Info.add(cb_memGrade);

		tp_memNum = new JTextPane();
		tp_memNum.setText("회원번호");
		tp_memNum.setBounds(280, 24, 54, 21);
		tp_memNum.setEditable(false);
		mem_Info.add(tp_memNum);

		tf_memNum = new JTextField();
		tf_memNum.setColumns(10);
		// tf_memNum.setEditable(false);
		tf_memNum.setBounds(346, 24, 108, 21);
		mem_Info.add(tf_memNum);
		//////////////////////////////////////////////////////////////////////////////////////////////
		newButton = new JButton("신규");
		newButton.setBounds(466, 24, 70, 23);
		// new Button Listener
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == newButton) {
					DAO_DB dao = new DAO_DB();
					use_numCalc = dao.mem_selectAll();
					for (int i = 0; i < use_numCalc.size(); i++) {
						if ((i + 1) != (int) ((Vector) use_numCalc.elementAt(i)).elementAt(0)) {
							num = i + 1;
							break;
						}
						num = i + 1;
					}
					if (num == use_numCalc.size()) {
						num = use_numCalc.size() + 1;
					}
					tf_memNum.setText(num + "");
				}
			}
		});
		mem_Info.add(newButton);
		//////////////////////////////////////////////////////////////////////////////////////////////
		tp_memName = new JTextPane();
		tp_memName.setText("회  원  명");
		tp_memName.setBounds(12, 55, 54, 21);
		tp_memName.setEditable(false);
		mem_Info.add(tp_memName);

		tf_memName = new JTextField();
		tf_memName.setColumns(10);
		tf_memName.setBounds(79, 55, 149, 21);
		mem_Info.add(tf_memName);

		tp_memTel = new JTextPane();
		tp_memTel.setText("전화번호");
		tp_memTel.setBounds(240, 55, 54, 21);
		tp_memTel.setEditable(false);
		mem_Info.add(tp_memTel);

		tf_memTel = new JTextField();
		tf_memTel.setColumns(10);
		tf_memTel.setBounds(306, 55, 230, 21);
		mem_Info.add(tf_memTel);

		tp_Email = new JTextPane();
		tp_Email.setText("이  메  일");
		tp_Email.setBounds(12, 86, 54, 21);
		mem_Info.add(tp_Email);

		tf_Email = new JTextField();
		tf_Email.setColumns(10);
		tf_Email.setBounds(79, 86, 187, 21);
		tp_Email.setEditable(false);
		mem_Info.add(tf_Email);

		tp_Addr = new JTextPane();
		tp_Addr.setText("주        소");
		tp_Addr.setBounds(11, 117, 54, 21);
		tp_Addr.setEditable(false);
		mem_Info.add(tp_Addr);

		tf_Addr = new JTextField();
		tf_Addr.setColumns(10);
		tf_Addr.setBounds(78, 117, 458, 21);
		mem_Info.add(tf_Addr);

		tp_Memo = new JTextPane();
		tp_Memo.setText("메        모");
		tp_Memo.setBounds(12, 214, 54, 21);
		tp_Memo.setEditable(false);
		mem_Info.add(tp_Memo);

		sp_Memo = new JScrollPane();
		sp_Memo.setBounds(79, 159, 457, 137);
		mem_Info.add(sp_Memo);

		ta_Memo = new JTextArea();
		ta_Memo.setEditable(false);
		sp_Memo.setViewportView(ta_Memo);

		button_Save = new JButton("저장");
		button_Save.setBounds(121, 509, 97, 23);
		mem_Info.add(button_Save);
		///////////////////////////////////////////////////////////////////// Save
		///////////////////////////////////////////////////////////////////// Button
		///////////////////////////////////////////////////////////////////// Listener
		///////////////////////////////////////////////////////////////////// 정보
		title_Member.add("회원번호");
		title_Member.add("회원명");
		title_Member.add("전화번호");
		title_Member.add("주소");
		title_Member.add("이메일");
		button_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button_Save)
					try {
						DAO_DB db_Access = new DAO_DB();
						member.setNum(Integer.parseInt(tf_memNum.getText()));
						member.setName(tf_memName.getText());
						member.setTel(tf_memTel.getText());
						member.setAddr(tf_Addr.getText());
						member.setEmail(tf_Email.getText());
						db_Access.insert(member);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
						System.out.println("FileNotFoundException");
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("IOException");
					} catch (NumberFormatException e1) {
						System.out.println("입력 형식이 잘못되었습니다.");
					}
				DAO_DB db_Refresh_Insert = new DAO_DB();
				data_Member = db_Refresh_Insert.mem_selectAll();
				tbpl.setTable(data_Member, title_Member);
				setClear();
			}

		});
		/////////////////////////////////////////////////////////////////////////////////

		button_Delete = new JButton("삭제");
		button_Delete.setBounds(230, 509, 97, 23);
		mem_Info.add(button_Delete);
		///////////////////////////////////////////////////////////////////// Delete
		///////////////////////////////////////////////////////////////////// Button
		///////////////////////////////////////////////////////////////////// Listener
		///////////////////////////////////////////////////////////////////// 정보
		button_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button_Delete)
					try {
						DAO_DB db_Access = new DAO_DB();
						member.setNum(Integer.parseInt(tf_memNum.getText()));
						member.setName(tf_memName.getText());
						db_Access.delete(member);
					} catch (NumberFormatException e1) {
						System.out.println("입력 형식이 잘못되었습니다.");
					}
				DAO_DB db_Refresh_Delete = new DAO_DB();
				data_Member = db_Refresh_Delete.mem_selectAll();
				tbpl.setTable(data_Member, title_Member);
				setClear();
			}

		});
		/////////////////////////////////////////////////////////////////////
		button_Search = new JButton("검색");
		button_Search.setBounds(339, 509, 97, 23);
		mem_Info.add(button_Search);
		///////////////////////////////////////////////////////////////////// Search
		///////////////////////////////////////////////////////////////////// Button
		///////////////////////////////////////////////////////////////////// Listener
		///////////////////////////////////////////////////////////////////// 정보
		button_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button_Search)
					try {
						DAO_DB db_Access = new DAO_DB();
						member.setNum(Integer.parseInt(tf_memNum.getText()));
						// db_Access.delete(member);
					} catch (NumberFormatException e1) {
						System.out.println("입력 형식이 잘못되었습니다.");
					}
				DAO_DB db_Refresh_Delete = new DAO_DB();
				data_Member = db_Refresh_Delete.mem_selectAll();
				tbpl.setTable(data_Member, title_Member);
			}

		});

	}

	public void setClear(){
		tf_memNum.setText("");
		tf_memName.setText("");
		tf_memTel.setText("");
		tf_Addr.setText("");
		tf_Email.setText("");
	}
	public JPanel getPanel() {
		return mem_Mngt;
	}

}
