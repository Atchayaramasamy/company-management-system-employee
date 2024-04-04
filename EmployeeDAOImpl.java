
//Creating DAO implementation class 
//Importing necessary java utility classes
import java.util.HashMap;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Map;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.ArrayList;

// Implementation of the EmployeeDAO interface
class EmployeeDAOImpl implements EmployeeDAO {

    // Method to insert employee details into the database
    public int insertEmployee(int employeeId, String firstName, String middleName, String lastName, String email,
            boolean active, String currentUser, Timestamp timestamp, String modifiedUser, Timestamp time) {
        Connection connection = null;
        Statement stmt = null;
        int row = 0;
        // System.out.println(is_active);
        try {
            connection = DBConnection.createConnection();
            stmt = connection.createStatement();
            // SQL query to insert employee details into the database
            String query = "INSERT INTO employee_details(emp_id, emp_firstname, emp_middlename, emp_lastname, emp_email, is_active, created_by, created_at, modified_by, modified_at) VALUES ("
                    +
                    "'" + employeeId + "', " +
                    "'" + firstName + "', " +
                    "'" + middleName + "', " +
                    "'" + lastName + "', " +
                    "'" + email + "', " +
                    "'" + (active ? 1 : 0) + "', " +
                    "'" + currentUser + "', " +
                    "'" + timestamp + "', " +
                    "'" + modifiedUser + "', " +
                    "'" + time + "')";

            // Executing the insert query
            row = stmt.executeUpdate(query);

            System.out.println(row + " rows inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return row;
    }

    // Method to delete an employee by ID

    public int deleteEmployee(int deleteId) {
        Connection connection = null;
        Statement stmt = null;
        int row = 0;
        try {
            connection = DBConnection.createConnection();
            stmt = connection.createStatement();
            String query = "DELETE FROM employee_details WHERE emp_id = " + deleteId;
            row = stmt.executeUpdate(query);
            // System.out.println(row + " rows deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return row;
    }

    // Method to display employee details by ID
    public LinkedHashMap<String, Object> displayById(int displayId) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        LinkedHashMap<String, Object> employeeDetails = new LinkedHashMap<>();
        try {
            connection = DBConnection.createConnection();
            stmt = connection.createStatement();
            String query = "SELECT * FROM employee_details WHERE emp_id = " + displayId;
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                employeeDetails.put("Employee Id", rs.getInt("emp_id"));
                employeeDetails.put("First Name", rs.getString("emp_firstname"));
                employeeDetails.put("Middle Name", rs.getString("emp_middlename"));
                employeeDetails.put("Last Name", rs.getString("emp_lastname"));
                employeeDetails.put("Email ID", rs.getString("emp_email"));
                employeeDetails.put("isActive", rs.getBoolean("is_active"));
                employeeDetails.put("Created By", rs.getString("created_by"));
                employeeDetails.put("Created At", rs.getTimestamp("created_at"));
                employeeDetails.put("Modified By", rs.getString("modified_by"));
                employeeDetails.put("Modified At", rs.getTimestamp("modified_at"));
            } else {
                System.out.println("No employee found with ID: " + displayId);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return employeeDetails;
    }

    // Method to display all employees
    @Override
    public ArrayList<LinkedHashMap<String, Object>> displayAllEmployees() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<LinkedHashMap<String, Object>> allEmployees = new ArrayList<>();
        try {
            connection = DBConnection.createConnection();
            stmt = connection.createStatement();
            String query = "SELECT * FROM employee_details";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                LinkedHashMap<String, Object> employeeDetails = new LinkedHashMap<>();
                int emp_id = rs.getInt("emp_id");
                employeeDetails.put("Employee Id", emp_id);
                employeeDetails.put("First Name", rs.getString("emp_firstname"));
                employeeDetails.put("Middle Name", rs.getString("emp_middlename"));
                employeeDetails.put("Last Name", rs.getString("emp_lastname"));
                employeeDetails.put("Email ID", rs.getString("emp_email"));
                employeeDetails.put("isActive", rs.getBoolean("is_active"));
                employeeDetails.put("Created By", rs.getString("created_by"));
                employeeDetails.put("Created At", rs.getTimestamp("created_at"));
                employeeDetails.put("Modified By", rs.getString("modified_by"));
                employeeDetails.put("Modified At", rs.getTimestamp("modified_at"));
                allEmployees.add(employeeDetails);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return allEmployees;
    }

    // Method to update the first name of an employee
    @Override
    public int updateFirstName(int employeeId, String newFirstName, String modifiedUser, Timestamp time) {
        Connection connection = null;
        Statement stmt = null;
        int row = 0;
        try {
            connection = DBConnection.createConnection();
            stmt = connection.createStatement();
            String query = "UPDATE employee_details SET emp_firstname = '" + newFirstName + "', modified_by = '"
                    + modifiedUser + "', modified_at = '" + time + "' WHERE emp_id = '" + employeeId + "'";
            row = stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return row;
    }

    @Override
    public int updateMiddleName(int employeeId, String newMiddleName, String modifiedUser, Timestamp time) {
        Connection connection = null;
        Statement stmt = null;
        int row = 0;
        try {
            connection = DBConnection.createConnection();
            stmt = connection.createStatement();
            String query = "UPDATE employee_details SET emp_middlename = '" + newMiddleName + "', modified_by = '"
                    + modifiedUser + "', modified_at = '" + time + "' WHERE emp_id = '" + employeeId + "'";
            row = stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return row;
    }

    @Override
    public int updateLastName(int employeeId, String newLastName, String modifiedUser, Timestamp time) {
        Connection connection = null;
        Statement stmt = null;
        int row = 0;
        try {
            connection = DBConnection.createConnection();
            stmt = connection.createStatement();
            String query = "UPDATE employee_details SET emp_lastname = '" + newLastName + "', modified_by = '"
                    + modifiedUser + "', modified_at = '" + time + "' WHERE emp_id = '" + employeeId + "'";
            row = stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return row;
    }

    @Override
    public int updateEmail(int employeeId, String newEmail, String modifiedUser, Timestamp time) {
        Connection connection = null;
        Statement stmt = null;
        int row = 0;
        try {
            connection = DBConnection.createConnection();
            stmt = connection.createStatement();
            String query = "UPDATE employee_details SET emp_email = '" + newEmail + "', modified_by = '" + modifiedUser
                    + "', modified_at = '" + time + "' WHERE emp_id = '" + employeeId + "'";
            row = stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return row;
    }

    // update Employee Status
    @Override
    public int updateStatus(int employeeId, boolean isActiveValue, String modifiedUser, Timestamp time) {
        Connection connection = null;
        Statement stmt = null;
        int row = 0;
        try {
            connection = DBConnection.createConnection();
            stmt = connection.createStatement();
            String query = "UPDATE employee_details SET is_active = " + (isActiveValue ? 1 : 0) + ", modified_by = '"
                    + modifiedUser + "', modified_at = '" + time + "' WHERE emp_id = '" + employeeId + "'";
            row = stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return row;
    }

}
