package dbsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by qianfei on 2019/6/24.
 */

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {
	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JButton jb1, jb2, jb3, jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;

	Statement stat = null;
	PreparedStatement ps;
	Connection ct = null;
	ResultSet rs = null;

	public Window() {
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("Select Infomation");
		jb1.addActionListener(this);
		jl1 = new JLabel("Please input name: ");

		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jb2 = new JButton("Add");
		jb2.addActionListener(this);
		jb3 = new JButton("Update");
		jb3.addActionListener(this);
		jb4 = new JButton("Delete");
		jb4.addActionListener(this);

		jp2 = new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);

		sm = new StuModel();

		jt = new JTable(sm);

		jsp = new JScrollPane(jt);

		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");
		this.setSize(600, 400);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jb1) { // select
			String name = this.jtf.getText().trim();

			String sql = "select * from stu where name = '" + name + "' ";

			sm = new StuModel(sql);

			jt.setModel(sm);

		} else if (arg0.getSource() == jb2) { // add
			StuAdd sa = new StuAdd(this, "Add Student", true);

			sm = new StuModel();
			jt.setModel(sm);
		} else if (arg0.getSource() == jb4) { // delete

			int rowNum = this.jt.getSelectedRow();

			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please choose one line");
				return;
			}
			String stuId = (String) sm.getValueAt(rowNum, 0);

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost/StuInfo?useSSL=FALSE&serverTimezone=UTC";
				String user = "root";
				String passwd = "admin123";

				ct = DriverManager.getConnection(url, user, passwd);
				ps = ct.prepareStatement("delete from stu where number = ?");
				ps.setString(1, stuId);
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;

					}
					if (ps != null) {
						ps.close();
						ps = null;
					}
					if (ct != null) {
						ct.close();
						ct = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sm = new StuModel();
			jt.setModel(sm);
		} else if (arg0.getSource() == jb3) { // update

			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please choose one line");
				return;
			}

			StuUpdate su = new StuUpdate(this, "Update", true, sm, rowNum);
			sm = new StuModel();
			jt.setModel(sm);
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Window w = new Window();
	}
}
