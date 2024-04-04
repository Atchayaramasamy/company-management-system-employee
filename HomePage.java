//Creating an Homepage to get input from the user and methods for accessing

//Importing the required packages for user input accessing
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;

/**
 * Class HomePage represents the main interface of the company management system
 * for employee class.
 * It provides options for adding, updating, deleting, and displaying employee
 * details.
 */

// Creating class HomePage
class HomePage {
    public static void main(String args[]) {

        // Creating a Scanner object to take user input
        Scanner sc = new Scanner(System.in);

        // Creating an instance of the EmployeeServiceImpl class
        EmployeeService service = new EmployeeServiceImpl();

        System.out.println();

        System.out.println("************************* COMPANY MANAGEMENT SYSTEM *************************");

        System.out.println();

        // Variable to hold the user's choice
        int choice;

        // Boolean flag for controlling the loop
        boolean bool = true;

        // Loop to continuously display menu and process user's choice
        while (bool) {

            System.out.println();
            // Displaying menu options
            System.out.println(
                    " 1. ADD EMPLOYEE\n 2. UPDATE EMPLOYEE\n 3. DELETE EMPLOYEE\n 4. DISPLAY EMPLOYEE\n 5. EXIT");

            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            // sc.nextLine();
            switch (choice) {
                case 1:
                    // Adding a new employee
                    LinkedHashMap<String, Object> empDetails = new LinkedHashMap<>();
                    System.out.print("Enter Employee Id :");
                    int employeeId = sc.nextInt();
                    // sc.nextLine();

                    // Getting firstname
                    System.out.print("Enter Employee First Name : ");
                    String firstName = sc.next();

                    // Optionally gettig middle name
                    System.out.print("Do you need to enter Middle name (Y/N) :");
                    char ch = sc.next().charAt(0);
                    String middleName = "";
                    if (ch == 'y' || ch == 'Y') {
                        System.out.print("Enter Employee Middle Name :");
                        middleName = sc.next();
                    }
                    // sc.nextLine();
                    // Getting last name
                    System.out.print("Enter Employee Last Name : ");
                    String lastName = sc.next();

                    // Getting email
                    System.out.print("Enter Employee Email : ");
                    String email = sc.next();

                    // Getting employee status he/she is active/not
                    System.out.print("Enter the Employee is active or not(true/false) : ");
                    boolean isActive = sc.nextBoolean();

                    // Collecting and inserting the values in the hashmap
                    empDetails.put("Employee Id", employeeId);
                    empDetails.put("First Name", firstName);
                    empDetails.put("Middle Name", middleName);
                    empDetails.put("Last Name", lastName);
                    empDetails.put("Email ID", email);
                    empDetails.put("isActive", isActive);

                    // employeeList.add(empDetails);

                    service.insertEmployee(empDetails);
                    break;
                case 2:
                    // Updating the fields
                    int option;
                    boolean value = true;
                    while (value) {

                        // Options for Updating individual fields
                        System.out.println(
                                "  1. UPDATE FIRST NAME\n  2. UPDATE MIDDLE NAME\n  3. UPDATE LAST NAME\n  4. UPDATE EMAIL\n  5. UPDATE EMPLOYEE STATUS\n  6. EXIT");
                        System.out.print("Enter your choice : ");
                        option = sc.nextInt();
                        sc.nextLine();
                        switch (option) {
                            case 1:
                                // Updating the first name of an employee
                                System.out.print("Enter Employee ID : ");
                                int firstId = sc.nextInt();

                                System.out.print("Enter Employee's New First Name :");
                                String newFirstName = sc.next();

                                // Calling the updateFirstName method of the EmployeeService to update first
                                // name
                                service.updateFirstName(firstId, newFirstName);
                                break;

                            case 2:
                                // Updating the middle name of an employee
                                System.out.print("Enter Employee ID : ");
                                int middleId = sc.nextInt();

                                System.out.print("Enter Employee's New Middle Name :");
                                String newMiddleName = sc.next();

                                // Calling the updateMiddleName method of the EmployeeService to update middle
                                // name
                                service.updateMiddleName(middleId, newMiddleName);
                                break;
                            case 3:
                                // Updating the last name of an employee
                                System.out.print("Enter Employee ID : ");
                                int lastId = sc.nextInt();

                                System.out.print("Enter Employee's New Last Name :");
                                String newLastName = sc.next();

                                // Calling the updateLastName method of the EmployeeService to update last name
                                service.updateLastName(lastId, newLastName);
                                break;
                            case 4:
                                // Updating the email of an employee
                                System.out.print("Enter Employee ID : ");
                                int empId = sc.nextInt();

                                System.out.print("Enter Employee's New Email :");
                                String newEmail = sc.next();

                                // Calling the updateEmail method of the EmployeeService to update email
                                service.updateEmail(empId, newEmail);
                                break;
                            case 5:
                                // Updating the employee status
                                System.out.print("Enter Employee ID : ");
                                int activeId = sc.nextInt();
                                // sc.nextLine();

                                System.out.print("Enter Employee Active or Not(true/false) :");
                                boolean active = sc.nextBoolean();

                                // Call updateStatus method of service to update employee status
                                service.updateStatus(activeId, active);
                                break;
                            case 6:
                                value = false;
                                // System.exit(0);
                                break;

                            default:
                                System.out.println("Invalid option!");
                        }
                        /*
                         * System.out.print("Do you want to continue(Y/N) :");
                         * char letter = sc.next().charAt(0);
                         * if(letter == 'y' || letter == 'Y'){
                         * value = true;
                         * }
                         */
                        System.out.println();
                    }
                    break;

                case 3:
                    // Deleting an employee by ID
                    System.out.print("Enter the Employee ID :");
                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    // Call deleteEmployee method of service to delete employee
                    service.deleteEmployee(deleteId);
                    break;

                case 4:
                    // Displaying details of the employee
                    int choose;
                    boolean answer = true;
                    while (answer) {

                        // Options for displaying employee details
                        System.out.println("  1. DISPLAY BY ID\n  2. DISPLAY ALL EMPLOYEES\n  3. EXIT");
                        System.out.print("Enter your choice : ");
                        choose = sc.nextInt();
                        sc.nextLine();

                        switch (choose) {
                            case 1:

                                // Displaying employee by ID
                                System.out.println("Enter the Employee ID :");
                                int displayId = sc.nextInt();
                                sc.nextLine();

                                // Call displayById method of service to display employee details
                                service.displayById(displayId);
                                break;
                            case 2:
                                // Displaying all employees
                                System.out.println("Displaying all the Employees");

                                // Call displayAllEmployees method of service to display employee details
                                service.displayAllEmployees();
                                break;
                            case 3:
                                answer = false;

                        }
                        System.out.println();
                    }
                    break;

                case 5:
                    // Exiting the program
                    bool = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }

    }
}
