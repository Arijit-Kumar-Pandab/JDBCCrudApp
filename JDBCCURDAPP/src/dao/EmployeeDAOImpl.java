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
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		String deleteQuery = null;

		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				deleteQuery = "Delete from employees where empId = ?";
				pstmt = connection.prepareStatement(deleteQuery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, id);
			}

			int rowAffected = -1;
			if (pstmt != null) {
				rowAffected = pstmt.executeUpdate();
			}

			if (rowAffected >= 1) {
				return true;
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
