package dao;

import dto.Employee;

public class EmployeeDAOImpl implements IEmployeeDAO {

	@Override
	public boolean addEmployee(String name, String dept, int salary) {
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
