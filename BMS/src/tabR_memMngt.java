import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

public class tabR_memMngt {

	private JPanel mem_Mngt;
	private JPanel inner_bookMngt;
	private JPanel mem_Info;

	private JLabel lb_memInfo;
	private JLabel lb_memGrade;
	private JLabel lb_memNum;
	private JLabel lb_memName;
	private JLabel lb_memTel;
	private JLabel lb_Addr;
	private JLabel lb_Email;
	private JLabel lb_Memo;
	private JTextField tf_memNum;
	private JTextField tf_memName;
	private JTextField tf_memTel;
	private JTextField tf_Addr;
	private JTextField tf_Email;
	private JTextArea ta_Memo;

	private JScrollPane sp_Memo;
	private JComboBox cb_memGrade;

	private JButton newButton;
	private JButton button_Delete;
	private JButton button_Save;
	private JButton button_Search;
	Member member = new Member();
	tabPanel_Left tbpl = new tabPanel_Left();
	Vector data_Member = new Vector();
	Vector title_Member = new Vector();
	Vector use_numCalc = new Vector();
	private String errMsg;

	public tabR_memMngt() {
	}

	public tabR_memMngt(String title) {
		init();
	}

	public void init() {
		mem_Mngt = new JPanel();
		mem_Mngt.setLayout(new MigLayout("", "[grow]", "[524.00,grow][grow]"));

		inner_bookMngt = new JPanel();
		inner_bookMngt.setLayout(null);
		mem_Mngt.add(inner_bookMngt, "cell 0 0,grow");

		lb_memInfo = new JLabel();
		lb_memInfo.setText("회원정보");
		lb_memInfo.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_memInfo.setBounds(24, 10, 62, 23);
		inner_bookMngt.add(lb_memInfo);

		mem_Info = new JPanel();
		mem_Info.setLayout(null);
		mem_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mem_Info.setBackground(Color.WHITE);
		mem_Info.setBounds(12, 22, 548, 544);
		inner_bookMngt.add(mem_Info);

		lb_memGrade = new JLabel();
		lb_memGrade.setText("회원등급");
		lb_memGrade.setBounds(12, 24, 54, 21);
		mem_Info.add(lb_memGrade);

		cb_memGrade = new JComboBox();
		cb_memGrade.setModel(new DefaultComboBoxModel(new String[] { "추후 구현" }));
		cb_memGrade.setBounds(79, 24, 187, 21);
		cb_memGrade.setEnabled(false);
		mem_Info.add(cb_memGrade);

		lb_memNum = new JLabel();
		lb_memNum.setText("회원번호");
		lb_memNum.setBounds(280, 24, 54, 21);
		mem_Info.add(lb_memNum);

		tf_memNum = new JTextField();
		tf_memNum.setColumns(10);
		tf_memNum.setBounds(346, 24, 108, 21);
		mem_Info.add(tf_memNum);

		newButton = new JButton("신규");
		newButton.setBounds(466, 24, 70, 23);

		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == newButton) {
					int num = 0;
					DAO_DB dao = new DAO_DB();
					use_numCalc = dao.mem_selectAll();
					for (int i = 0; i < use_numCalc.size(); i++) {
						if ((i + 1) != (int) ((Vector) use_numCalc.elementAt(i)).elementAt(0)) {
							num = i + 1;
							tf_memNum.setText(num + "");
							return;
						}
						num++;
					}
					if (num == use_numCalc.size()) {
						num = use_numCalc.size() + 1;
					}
					tf_memNum.setText(num + "");
				}
			}
		});
		mem_Info.add(newButton);

		lb_memName = new JLabel();
		lb_memName.setText("회  원  명");
		lb_memName.setBounds(12, 55, 54, 21);
		mem_Info.add(lb_memName);

		tf_memName = new JTextField();
		tf_memName.setColumns(10);
		tf_memName.setBounds(79, 55, 149, 21);
		mem_Info.add(tf_memName);

		lb_memTel = new JLabel();
		lb_memTel.setText("전화번호");
		lb_memTel.setBounds(240, 55, 54, 21);
		mem_Info.add(lb_memTel);

		tf_memTel = new JTextField();
		tf_memTel.setColumns(10);
		tf_memTel.setBounds(306, 55, 230, 21);
		mem_Info.add(tf_memTel);

		lb_Email = new JLabel();
		lb_Email.setText("이  메  일");
		lb_Email.setBounds(12, 86, 54, 21);
		mem_Info.add(lb_Email);

		tf_Email = new JTextField();
		tf_Email.setColumns(10);
		tf_Email.setBounds(79, 86, 187, 21);
		mem_Info.add(tf_Email);

		lb_Addr = new JLabel();
		lb_Addr.setText("주        소");
		lb_Addr.setBounds(11, 117, 54, 21);
		mem_Info.add(lb_Addr);

		tf_Addr = new JTextField();
		tf_Addr.setColumns(10);
		tf_Addr.setBounds(78, 117, 458, 21);
		mem_Info.add(tf_Addr);

		lb_Memo = new JLabel();
		lb_Memo.setText("메        모");
		lb_Memo.setBounds(12, 214, 54, 21);
		mem_Info.add(lb_Memo);

		sp_Memo = new JScrollPane();
		sp_Memo.setBounds(79, 159, 457, 137);
		mem_Info.add(sp_Memo);

		ta_Memo = new JTextArea();
		ta_Memo.setEditable(false);
		sp_Memo.setViewportView(ta_Memo);

		button_Save = new JButton("저장");
		button_Save.setBounds(121, 509, 97, 23);
		mem_Info.add(button_Save);

		title_Member.add("회원번호");
		title_Member.add("회원명");
		title_Member.add("전화번호");
		title_Member.add("주소");
		title_Member.add("이메일");
		title_Member.add("ID");

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
						member.setId("pit4nya");
						member.setPass("hi");
						db_Access.insert_Member(member);
					} catch (FileNotFoundException e1) {
						errMsg = e1.getMessage();
						System.out.println(errMsg);
						JOptionPane.showMessageDialog(null, "파일을 찾을 수 없습니다.");
						System.out.println("파일을 찾을 수 없습니다.");
					} catch (IOException e1) {
						errMsg = e1.getMessage();
						System.out.println(errMsg);
						JOptionPane.showMessageDialog(null, "IOException");
						System.out.println("IOException");
					} catch (NumberFormatException e1) {
						errMsg = e1.getMessage();
						System.out.println(errMsg);
						JOptionPane.showMessageDialog(null, "회원번호 형식을 확인해 주세요.");
						System.out.println("회원번호 형식을 확인해 주세요.");
					}
				DAO_DB db_Refresh_Insert = new DAO_DB();
				data_Member = db_Refresh_Insert.mem_selectAll();
				tbpl.setMemberTable(data_Member, title_Member);
				setClear();
			}

		});
		button_Delete = new JButton("삭제");
		button_Delete.setBounds(230, 509, 97, 23);
		mem_Info.add(button_Delete);

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
				tbpl.setMemberTable(data_Member, title_Member);
				setClear();
			}

		});

		button_Search = new JButton("검색");
		button_Search.setBounds(339, 509, 97, 23);
		mem_Info.add(button_Search);

		button_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button_Search) {

				}
			}

		});

	}

	public void setClear() {
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
