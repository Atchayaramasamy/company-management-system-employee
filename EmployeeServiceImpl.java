
//Service Class Implementation
// Importing required Java util classes
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;

// Implementation of the EmployeeService interface
class EmployeeServiceImpl implements EmployeeService {

    // Scanner object for user input
    Scanner sc = new Scanner(System.in);
    // DAO object for database operations
    EmployeeDAO dao = new EmployeeDAOImpl();
    // Current user name for modifications
    String currentUser = "Atchaya";

    // Timestamp for tracking modifications
    LocalDateTime modifiedAt = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String modified_at = modifiedAt.format(format);
    Timestamp time = Timestamp.valueOf(modified_at);

    // Method to insert a new employee
    public void insertEmployee(LinkedHashMap<String, Object> empDetails) {

        // Extracting employee details from the HashMap
        Integer employeeId = Integer.parseInt(String.valueOf(empDetails.get("Employee Id")));
        String firstName = String.valueOf(empDetails.get("First Name"));
        String middleName = String.valueOf(empDetails.get("Middle Name"));
        String lastName = String.valueOf(empDetails.get("Last Name"));
        String email = String.valueOf(empDetails.get("Email ID"));
        boolean active = Boolean.parseBoolean(String.valueOf(empDetails.get("isActive")));

        // Formatting current time for tracking creation
        LocalDateTime createdAt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdTime = createdAt.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(createdTime);

        // Adding additional details to the HashMap
        empDetails.put("Created By", currentUser);
        empDetails.put("Created At", timestamp);
        String modifiedUser = currentUser;
        empDetails.put("Modified By", modifiedUser);
        empDetails.put("Modified At", time);

        // Calling DAO method to insert the employee
        int row = dao.insertEmployee(employeeId, firstName, middleName, lastName, email, active, currentUser, timestamp,
                modifiedUser, time);

        // Checking if the insertion was successful
        if (row > 0) {
            System.out.println("Employee is added successfully!");
            System.out.println();
        } else {
            System.out.println("Employee is not added!");
            System.out.println();
        }
    }

    // Method to update the first name of an employee
    public void updateFirstName(int firstId, String newFirstName) {
        // Parsing empId to Integer wrapper class
        Integer employeeId = Integer.parseInt(String.valueOf(firstId));

        // Getting the new first name from user input
        String firstName = String.valueOf(newFirstName);

        // Getting the current username from user input
        System.out.println("Enter Current username : ");
        String modifiedUser = sc.next();

        // Calling the dao method to update the first name
        int row = dao.updateFirstName(employeeId, firstName, modifiedUser, time);

        // Checking if the update was successful or not
        if (row > 0) {
            System.out.println("Employee's first name updated successfully!");
            System.out.println();
        } else {
            System.out.println("Failed to update employee's first name!");
            System.out.println();
        }
    }

    // Method to update the middle name of an employee
    public void updateMiddleName(int middleId, String newMiddleName) {
        // Parsing empId to Integer wrapper class
        Integer employeeId = Integer.parseInt(String.valueOf(middleId));

        // Getting the new middle name from user input
        String middleName = String.valueOf(newMiddleName);

        // Getting the current username from user input
        System.out.println("Enter Current username : ");
        String modifiedUser = sc.next();

        // Calling the dao method to update the middle name
        int row = dao.updateMiddleName(employeeId, middleName, modifiedUser, time);

        // Checking if the update was successful or not
        if (row > 0) {
            System.out.println("Employee's middle name updated successfully!");
            System.out.println();
        } else {
            System.out.println("Failed to update employee's middle name!");
            System.out.println();
        }
    }

    // Method to update the last name of an employee
    public void updateLastName(int lastId, String newLastName) {
        // Parsing empId to Integer wrapper class
        Integer employeeId = Integer.parseInt(String.valueOf(lastId));

        // Getting the new last name from user input
        String lastName = String.valueOf(newLastName);

        // Getting the current username from user input
        System.out.println("Enter Current username : ");
        String modifiedUser = sc.next();

        // Calling the dao method to update the last name
        int row = dao.updateLastName(employeeId, lastName, modifiedUser, time);

        // Checking if the update was successful or not
        if (row > 0) {
            System.out.println("Employee's last name updated successfully!");
            System.out.println();
        } else {
            System.out.println("Failed to update employee's last name!");
            System.out.println();
        }
    }

    // Method to update the email of an employee
    public void updateEmail(int empId, String newEmail) {
        // Parsing empId to Integer wrapper class
        Integer employeeId = Integer.parseInt(String.valueOf(empId));

        // Getting the new email from user input
        String email = String.valueOf(newEmail);

        // Getting the current username from user input
        System.out.println("Enter Current username : ");
        String modifiedUser = sc.next();

        // Calling the dao method to update the email
        int row = dao.updateEmail(employeeId, email, modifiedUser, time);

        // Checking if the update was successful or not
        if (row > 0) {
            System.out.println("Employee's email updated successfully!");
            System.out.println();
        } else {
            System.out.println("Failed to update employee's email!");
            System.out.println();
        }
    }

    // Method to update the active status of an employee
    public void updateStatus(int empId, boolean active) {
        Integer employeeId = Integer.parseInt(String.valueOf(empId));
        boolean isActiveValue = Boolean.parseBoolean(String.valueOf(active));

        System.out.println("Enter Current username : ");
        String modifiedUser = sc.next();

        int row = dao.updateStatus(employeeId, isActiveValue, modifiedUser, time);
        if (row > 0) {
            System.out.println("Employee updated successfully!");
            System.out.println();
        } else {
            System.out.println("Employee not updated!");
            System.out.println();
        }
    }

    // Method to delete an employee
    public void deleteEmployee(int deleteId) {
        int rows = dao.deleteEmployee(deleteId);
        if (rows > 0) {
            System.out.println("Employee " + deleteId + " deleted successfully!");
            System.out.println();
        } else {
            System.out.println("Employee not deleted!");
            System.out.println();
        }
    }

    // Method to display details of an employee by ID
    public void displayById(int displayId) {

        // Calling DAO method to retrieve employee details by ID
        LinkedHashMap<String, Object> empDetail = dao.displayById(displayId);

        System.out.println("Details of employee " + displayId + " :");
        // Iterating through the details and formatting them for display
        for (Map.Entry<String, Object> detail : empDetail.entrySet()) {
            String detailKey = detail.getKey();
            Object detailValue = detail.getValue();
            // Formatting timestamps if necessary
            if (detailKey.equals("Created At") || detailKey.equals("Modified At")) {
                Timestamp timestamp = (Timestamp) detailValue;
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"))
                        .toUpperCase();
                System.out.printf("%-15s : %-20s%n", detailKey, formattedDateTime);
            } else {
                System.out.printf("%-15s : %-20s%n", detailKey, detailValue);
            }
        }

        System.out.println();

    }

    // displaying all the employess
    @Override
    public void displayAllEmployees() {

        // Calling DAO method to retrieve details of all employees
        ArrayList<LinkedHashMap<String, Object>> empDetail = dao.displayAllEmployees();
        System.out.println("Displaying all the Employees");

        // Iterating through the list of employees and formatting their details for
        // display
        for (LinkedHashMap<String, Object> employee : empDetail) {
            for (String key : employee.keySet()) {
                Object value = employee.get(key);
                if (key.equals("Created At") || key.equals("Modified At")) {
                    Timestamp timestamp = (Timestamp) value;
                    LocalDateTime dateTime = timestamp.toLocalDateTime();
                    String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"))
                            .toUpperCase();
                    System.out.printf("%-15s : %-20s%n", key, formattedDateTime);
                } else {
                    System.out.printf("%-15s : %-20s%n", key, value);
                }
            }
            System.out.println();
        }
    }

}