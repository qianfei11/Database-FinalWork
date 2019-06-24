package dbsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.*;

/**
 * Created by qianfei on 2019/6/24.
 */

@SuppressWarnings("serial")
public class StuModel extends AbstractTableModel {

	@SuppressWarnings("rawtypes")
	Vector rowData, columnNames;

	Statement stat = null;
	Connection ct = null;
	ResultSet rs = null;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void init(String sql) {
		if (sql.equals("")) {
			sql = "select * from stu";
		}

		columnNames = new Vector();
		columnNames.add("No.");
		columnNames.add("Name");
		columnNames.add("Gender");
		columnNames.add("Age");
		columnNames.add("Birthplace");
		columnNames.add("Course");

		rowData = new Vector();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/StuInfo?useSSL=FALSE&serverTimezone=UTC";
			String user = "root";
			String passwd = "admin123";

			ct = DriverManager.getConnection(url, user, passwd);
			stat = ct.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				rowData.add(hang);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (ct != null) {
					ct.close();
					ct = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addStu(String sql) {

	}

	public StuModel(String sql) {
		this.init(sql);
	}

	public StuModel() {
		this.init("");
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	@SuppressWarnings("rawtypes")
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector) (this.rowData.get(row))).get(column);
	}

	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}
}
