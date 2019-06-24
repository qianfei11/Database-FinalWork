package dbsystem;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by qianfei on 2019/6/24.
 */

@SuppressWarnings("serial")
public class StuUpdate extends JDialog implements ActionListener {
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JTextField jf1, jf2, jf3, jf4, jf5, jf6;
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2;

	public StuUpdate(Frame owner, String title, boolean modal, StuModel sm, int rowNum) {
		super(owner, title, modal);

		jl1 = new JLabel("No.");

		jl2 = new JLabel("Name");

		jl3 = new JLabel("Gender");
		jl4 = new JLabel("Age");
		jl5 = new JLabel("Birthplace");

		jl6 = new JLabel("Course");

		jf1 = new JTextField(10);
		jf1.setText((sm.getValueAt(rowNum, 0)).toString());
		jf2 = new JTextField(10);
		jf2.setText((String) sm.getValueAt(rowNum, 1));
		jf3 = new JTextField(10);
		jf3.setText(sm.getValueAt(rowNum, 2).toString());
		jf4 = new JTextField(10);
		jf4.setText((sm.getValueAt(rowNum, 3)).toString());
		jf5 = new JTextField(10);
		jf5.setText((String) sm.getValueAt(rowNum, 4));
		jf6 = new JTextField(10);
		jf6.setText((String) sm.getValueAt(rowNum, 5));

		jb1 = new JButton("Update");
		jb1.addActionListener(this);
		jb2 = new JButton("Quit");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		jp3.add(jb1);
		jp3.add(jb2);

		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		jp2.add(jf1);
		jp2.add(jf2);
		jp2.add(jf3);
		jp2.add(jf4);
		jp2.add(jf5);
		jp2.add(jf6);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		this.setSize(350, 200);
		this.setLocation(450, 250);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {
			Connection ct = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				String url = "jdbc:mysql://localhost/StuInfo?useSSL=FALSE&serverTimezone=UTC";
				String user = "root";
				String passwd = "admin123";
				ct = DriverManager.getConnection(url, user, passwd);

				String strsql = "update stu set name=?, gender=?, age=?, birthplace=?, course=? where number=?";
				pstmt = ct.prepareStatement(strsql);

				pstmt.setString(1, jf2.getText());
				pstmt.setString(2, jf3.getText());
				pstmt.setString(3, jf4.getText());
				pstmt.setString(4, jf5.getText());
				pstmt.setString(5, jf6.getText());
				pstmt.setString(6, jf1.getText());

				pstmt.executeUpdate();

				this.dispose();

			} catch (Exception arg1) {
				arg1.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
					if (ct != null) {
						ct.close();
						ct = null;
					}
				} catch (Exception arg2) {
					arg2.printStackTrace();
				}
			}

		}

	}

}
