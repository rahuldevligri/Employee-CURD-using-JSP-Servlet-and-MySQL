package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDAO 
{
    private Connection connection;

    public EmployeeDAO() 
    {
        String url = "jdbc:mysql://localhost:3306/curd";
        String username = "root";
        String password = "4642";
        
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } 
        catch(SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        
        if (connection == null) 
        {
            System.out.println("Failed to establish connection.");
        }
    }

    public List<Employee> getAllEmployees() 
    {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhone(resultSet.getString("phone"));
                employees.add(employee);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
        return employees;
    }

    public void addEmployee(Employee employee) 
    {
        String sql = "INSERT INTO employees (name, email, phone) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setString(3, employee.getPhone());
            statement.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeById(int id) 
    {
        Employee employee = null;
        String sql = "SELECT * FROM employees WHERE id=?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) 
            {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhone(resultSet.getString("phone"));
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return employee;
    }

    public void updateEmployee(Employee employee) 
    {
        String sql = "UPDATE employees SET name=?, email=?, phone=? WHERE id=?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setString(3, employee.getPhone());
            statement.setInt(4, employee.getId());
            statement.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) 
    {
        String sql = "DELETE FROM employees WHERE id=?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setInt(1, id);
            statement.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}