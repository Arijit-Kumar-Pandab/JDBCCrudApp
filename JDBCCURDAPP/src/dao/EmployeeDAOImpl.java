package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.Employee;
import util.JdbcUtil;

public class EmployeeDAOImpl implements IEmployeeDAO {

	@Override
	public boolean addEmployee(String name, String dept, int salary) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		String insertQuery = null;
		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				insertQuery = "Insert into employees(empDept, salary, Name) values(?, ?, ?)";
				pstmt = connection.prepareStatement(insertQuery);
			}
			if (pstmt != null) {
				pstmt.setString(1, dept);
				pstmt.setInt(2, salary);
				pstmt.setString(3, name);
			}
			int rowAffected = -1;
			if (pstmt != null) {
				rowAffected = pstmt.executeUpdate();
				if (rowAffected >= 1)
					return true;
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeResources(connection, pstmt, null);
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) {
		return false;
	}

	@Override
	public Employee searchEmployee(int id) {
		return null;
	}

	@Override
	public boolean updateEmployeeDetails(int id, String name, String dept, int salary) {
		return false;
	}

}
