import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	Toolkit tk = Toolkit.getDefaultToolkit();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame("Version 1.0");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainFrame(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1700, 1000);
		setLocation((tk.getScreenSize().width - getWidth()) / 2, (tk.getScreenSize().height - getHeight()) / 2);
		//////////////////////////////////////////////////////////////////////
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file_Menu = new JMenu("File");
		menuBar.add(file_Menu);

		JMenuItem exit_Menu = new JMenuItem("Exit");
		file_Menu.add(exit_Menu);
		exit_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				}
		});
		//////////////////////////////////////////////////////////////////////// menuBar
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[933.00px,grow][589px]", "[47px][479px,grow]"));

		JPanel name_Panel = new JPanel();
		name_Panel.setLayout(null);
		name_Panel.setBounds(100, 100, 200, 200);
		contentPane.add(name_Panel, "cell 0 0,grow");

		JLabel label = new JLabel("도서관리프로그램");
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 28));
		label.setBounds(0, 0, 611, 47);
		name_Panel.add(label);

		JPanel ad_Panel = new JPanel();
		ad_Panel.setLayout(null);
		contentPane.add(ad_Panel, "cell 1 0,grow");

		tabbedPanel_Left tabpL = new tabbedPanel_Left("z");
		tabbedPanel_Right_bookMngt tabpR_bM = new tabbedPanel_Right_bookMngt("정보열람");

		contentPane.add(tabpL.getPanel(), "cell 0 1,grow");

		contentPane.add(tabpR_bM.getPanel(), "cell 1 1,grow");
	}
}
