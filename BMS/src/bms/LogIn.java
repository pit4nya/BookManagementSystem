
package bms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class LogIn extends JFrame {

	private JButton btnLogin;
	private JButton btnInit;
	private JPasswordField passText;
	private JTextField userText;

	boolean check = false;
	String id;
	String pass;

	public LogIn() {
		setTitle("Login");
		setSize(280, 150);
		setResizable(false);
		setLocation(800, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		placeLoginPanel(panel);
		add(panel);
		setVisible(true);

	}

	public void placeLoginPanel(JPanel panel) {
		panel.setLayout(null);
		JLabel userLabel = new JLabel("ID");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
		JLabel passLabel = new JLabel("PASSWORD");
		passLabel.setBounds(10, 40, 80, 25);
		panel.add(passLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);
		passText = new JPasswordField(20);
		passText.setBounds(100, 40, 160, 25);
		panel.add(passText);

		passText.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					id = userText.getText();
					pass = "";
					char[] charedPass = passText.getPassword();
					for (int i = 0; i < charedPass.length; i++) {
						pass += Character.toString(charedPass[i]);
					}

					loginIdCheck LIC = new loginIdCheck();

					try {
						check = LIC.loginCheck(id, pass);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(check == true)
						setVisible(false);

				}

			}
		});

		btnInit = new JButton("다시작성");
		btnInit.setBounds(10, 80, 100, 25);
		panel.add(btnInit);
		btnInit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passText.setText("");
			}
		});

		btnLogin = new JButton("로그인");
		btnLogin.setBounds(160, 80, 100, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = userText.getText();
				pass = "";
				char[] charedPass = passText.getPassword();
				for (int i = 0; i < charedPass.length; i++) {
					pass += Character.toString(charedPass[i]);
				}

				loginIdCheck LIC = new loginIdCheck();

				try {
					check = LIC.loginCheck(id, pass);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(check == true)
					setVisible(false);
			}
		});
	}

}