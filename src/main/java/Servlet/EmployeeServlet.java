package Servlet;

import java.io.IOException;
import java.util.List;
import DAO.EmployeeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;

    public void init() 
    {
        employeeDAO = new EmployeeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String action = request.getParameter("action");
        
        if (action != null) {
            if (action.equals("edit")) 
            {
                int id = Integer.parseInt(request.getParameter("id"));
                Employee employee = employeeDAO.getEmployeeById(id);
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("edit.jsp").forward(request, response);
            } 
            else if (action.equals("delete")) 
            {
                int id = Integer.parseInt(request.getParameter("id"));
                employeeDAO.deleteEmployee(id);
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
            }
        } 
        else 
        {
            List<Employee> employees = employeeDAO.getAllEmployees();
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String action = request.getParameter("action");
        
        if (action != null && action.equals("update")) 
        {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Employee employee = new Employee(id, name, email, phone);
            employeeDAO.updateEmployee(employee);
            response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
        } 
        else 
        {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Employee employee = new Employee(name, email, phone);
            employeeDAO.addEmployee(employee);
            response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
        }
    }
}