
// Importing required Java utility classes
import java.util.HashMap;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;

// Interface for EmployeeService
public interface EmployeeService {
    // Method to insert a new employee with details provided in a LinkedHashMap
    public void insertEmployee(LinkedHashMap<String, Object> empDetails);

    // Method to update the first name of an employee identified by firstId
    public void updateFirstName(int firstId, String newFirstName);

    // Method to update the middle name of an employee identified by middleId
    public void updateMiddleName(int middleId, String newMiddleName);

    // Method to update the last name of an employee identified by lastId
    public void updateLastName(int lastId, String newLastName);

    // Method to update the email of an employee identified by empId
    public void updateEmail(int empId, String newEmail);

    // Method to update the status of an employee identified by activeId
    public void updateStatus(int activeId, boolean active);

    // Method to delete an employee identified by deleteId
    public void deleteEmployee(int deleteId);

    // Method to display details of an employee identified by displayId
    public void displayById(int displayId);

    // Method to display details of all employees
    public void displayAllEmployees();

}
