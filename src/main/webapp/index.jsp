<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Management</title>
</head>
<body>
    <h2>Add Employee</h2>
    <form action="EmployeeServlet" method="post">
        Name: <input type="text" name="name"><br>
        Email: <input type="email" name="email"><br>
        Phone: <input type="text" name="phone"><br>
        <input type="hidden" name="action" value="add">
        <input type="submit" value="Add Employee">
    </form>

    <h2>Employee List</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Action</th>
        </tr>
        <% List<Employee> employees = (List<Employee>) request.getAttribute("employees");
           if (employees != null) {
               for (Employee employee : employees) { %>
                <tr>
                    <td><%= employee.getId() %></td>
                    <td><%= employee.getName() %></td>
                    <td><%= employee.getEmail() %></td>
                    <td><%= employee.getPhone() %></td>
                    <td>
                        <a href="EmployeeServlet?action=edit&id=<%= employee.getId() %>">Edit</a> | 
                        <a href="EmployeeServlet?action=delete&id=<%= employee.getId() %>">Delete</a>
                    </td>
                </tr>
            <% }
           } else { %>
               <tr>
                   <td colspan="5">No employees found</td>
               </tr>
           <% } %>
    </table>
</body>
</html>
