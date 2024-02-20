<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Employee</title>
</head>
<body>
    <h2>Delete Employee</h2>
    <p>Are you sure you want to delete employee with ID <%= request.getParameter("id") %>?</p>
    <form action="EmployeeServlet" method="post">
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        <input type="hidden" name="action" value="delete">
        <input type="submit" value="Delete">
        <a href="index.jsp">Cancel</a>
    </form>
</body>
</html>