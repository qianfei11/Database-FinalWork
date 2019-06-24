package dbsystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Created by qianfei on 2019/6/24.
 */

@SuppressWarnings("serial")
public class Client extends JFrame implements ActionListener {
	JPanel panel;
	JLabel label, label2;
	JButton loginButton, exitButton;
	JTextField jTextField;
	JPasswordField passwordField;

	public Client() {
		this.setTitle("BlueSky Student Database System");
		this.setSize(300, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new FlowLayout());// 设置为流式布局
		label = new JLabel("Username");
		label2 = new JLabel("Password");
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);// 监听事件
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);// 监听事件
		jTextField = new JTextField(16);// 设置文本框长度
		passwordField = new JPasswordField(16);// 设置密码框长度
		panel.add(label);// 把组件添加到面板panel
		panel.add(jTextField);
		panel.add(label2);
		panel.add(passwordField);
		panel.add(loginButton);// 登录按钮
		panel.add(exitButton);// 退出按钮
		this.add(panel);// 实现面板panel
		this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == loginButton) {
			if (jTextField.getText().contains("assassinq")) {
				String hash = MD5Utils.MD5Encode(passwordField.getText(), "utf8");
//				System.out.println(hash);
				if (hash.equals("0192023a7bbd73250516f069df18b500")) {
					this.dispose();
					new Window();
				} else {
					JOptionPane.showMessageDialog(null, "Password is wrong!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Username is wrong!");
			}
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}
