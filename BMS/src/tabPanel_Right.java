import javax.swing.JTabbedPane;

public class tabPanel_Right extends JTabbedPane {
	private JTabbedPane tabbedPane;
	tabR_infoView tab_iV = new tabR_infoView("");
	tabR_memMngt tab_mM = new tabR_memMngt("");
	tabR_rentBook tab_rB = new tabR_rentBook("");
	tabR_returnBook tab_rtB = new tabR_returnBook("");

	public tabPanel_Right() {
	}

	public tabPanel_Right(String title) {
		init();
	}

	public void init() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.add(tab_iV.getPanel(), "정보열람");
		tabbedPane.add(tab_mM.getPanel(), "회원관리");
		tabbedPane.add(tab_rB.getPanel(), "대    여");
		tabbedPane.add(tab_rtB.getPanel(), "반    납");

	}

	public JTabbedPane getPanel() {
		return tabbedPane;
	}
}
