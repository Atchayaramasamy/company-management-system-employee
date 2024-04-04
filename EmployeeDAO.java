
// Import required Java utility classes
import java.sql.Timestamp;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;

// DAO interface for Employee
interface EmployeeDAO {
    // Method to insert a new employee with provided details
    int insertEmployee(int employeeId, String firstName, String middleName, String lastName, String email,
            boolean active, String currentUser, Timestamp timestamp, String modifiedUser, Timestamp time);

    // Method to update the first name of an employee identified by employeeId
    int updateFirstName(int employeeId, String newFirstName, String modifiedUser, Timestamp time);

    // Method to update the middle name of an employee identified by employeeId
    int updateMiddleName(int employeeId, String newMiddleName, String modifiedUser, Timestamp time);

    // Method to update the last name of an employee identified by employeeId
    int updateLastName(int employeeId, String newLastName, String modifiedUser, Timestamp time);

    // Method to update the email of an employee identified by employeeId
    int updateEmail(int employeeId, String newEmail, String modifiedUser, Timestamp time);

    // Method to update the status of an employee identified by employeeId
    int updateStatus(int employeeId, boolean isActiveValue, String modifiedUser, Timestamp time);

    // Method to delete an employee identified by deleteId
    int deleteEmployee(int deleteId);

    // Method to display details of all employees
    ArrayList<LinkedHashMap<String, Object>> displayAllEmployees();

    // Method to display details of an employee identified by displayId
    LinkedHashMap<String, Object> displayById(int displayId);
}
