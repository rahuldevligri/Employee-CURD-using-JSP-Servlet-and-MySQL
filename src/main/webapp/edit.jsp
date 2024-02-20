<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
    <h2>Edit Employee</h2>
    <form action="EmployeeServlet" method="post">
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        <% 
        Employee employee = (Employee) request.getAttribute("employee");
        if(employee != null) {
        %>
            Name: <input type="text" name="name" value="<%= employee.getName() %>"><br>
            Email: <input type="text" name="email" value="<%= employee.getEmail() %>"><br>
            Phone: <input type="text" name="phone" value="<%= employee.getPhone() %>"><br>
        <% 
        }
        %>
        <input type="hidden" name="action" value="update">
        <input type="submit" value="Update Employee">
    </form>
</body>
</html>
