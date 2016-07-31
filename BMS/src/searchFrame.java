import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class searchFrame extends JFrame {
	private JButton bt_Add = new JButton();
	private JTextField[] tf_Info = new JTextField[5];
	private JPanel pn_Info = new JPanel();
	Toolkit tk = Toolkit.getDefaultToolkit();

	DAO_DB dao = new DAO_DB();

	public searchFrame() {
		super("bookManagementSystem");
		setSize(500, 800);
		setLocation((tk.getScreenSize().width - getWidth()) / 2, (tk.getScreenSize().height - getHeight()) / 2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel init() {
		pn_Info.setLayout(null);
		for (int i = 0; i < tf_Info.length; i++) {
			tf_Info[i] = new JTextField();
			tf_Info[i].setSize(100, 20);
			tf_Info[i].setLocation(200, 140 + i * 30);
			pn_Info.add(tf_Info[i]);
			setVisible(true);
		}
		///////////////////////
		bt_Add.setText("Ãß°¡");
		bt_Add.setSize(100, 40);
		bt_Add.setLocation(200, 290);
		pn_Info.add(bt_Add);
		return pn_Info;
	}
}
