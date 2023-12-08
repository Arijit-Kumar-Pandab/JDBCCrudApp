package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Employee;
import service.IEmployeeService;
import serviceFactory.EmployeeServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		IEmployeeService employeeService = EmployeeServiceFactory.getEmployeeService();

		if (request.getRequestURI().endsWith("addform")) {
			String name = request.getParameter("ename");
			Integer salary = Integer.parseInt(request.getParameter("esalary"));
			String dept = request.getParameter("edept");

			boolean b = employeeService.addEmployee(name, dept, salary);
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (b == true) {
				out.println("<h1 style='color:green;text-align:center'>Seccessfully Inserted</h1>");
			} else {
				out.println("<h1 style='color:red;text-align:center'>Something went wrong</h1>");
			}
			out.close();
		} else if (request.getRequestURI().endsWith("deleteform")) {
			Integer id = Integer.parseInt(request.getParameter("eid"));
			boolean check = employeeService.deleteEmployee(id);

			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (check) {
				out.println("<h1 style='color:green;text-align:center;'>Deleted Successfully</h1>");
			} else {
				out.println("<h1 style='color:red;text-align:center;'>Something Went Wrong!!!</h1>");
			}
		} else if (request.getRequestURI().endsWith("searchform")) {
			Integer id = Integer.parseInt(request.getParameter("eid"));
			Employee employee = employeeService.searchEmployee(id);
			PrintWriter out = null;

			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (employee != null) {
				out.println("<html><head><title>Employee Details</title></head>");
				out.println("<body>");
				out.println("<center>");
				out.println("<table border='1'>");
				out.println("<tr><th>Name::</th><td>" + employee.getName() + "</td></tr>");
				out.println("<tr><th>I'D::</th><td>" + employee.getId() + "</td></tr>");
				out.println("<tr><th>Department::</th><td>" + employee.getDept() + "</td></tr>");
				out.println("<tr><th>Salary::</th><td>" + employee.getSalary() + "</td></tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");
			} else {
				out.println("<h1 style='color:red;text-align:center'>Details not found!!!</h1>");
			}
		}
	}

}
