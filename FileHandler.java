import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileHandler {

    // Static method to write work order data to a file
	public static void writeData(String workOrderFileName) {
        try {
            // Open the work order file for writing in append mode
            FileWriter fileWriter = new FileWriter(workOrderFileName, true);

            // Step 1: Write the header for the CSV file if the file is empty
            File file = new File(workOrderFileName);
            if (file.length() == 0) {
                fileWriter.write("customer_id, customer_first_name, customer_last_name, ticket_id, ticket_createdAt, workorder_createdAt, employee_id, "
                		+ "employee_first_name, employee_last_name, clocked_in, certification\n");
            }

            // Step 2: Get current timestamp and log the initial messages with it
            String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            logger(currentTimestamp + " : Loading Employee Data");
            logger(currentTimestamp + " : Loading Ticket Data");
            logger(currentTimestamp + " : Writing Work Order Data to File");
            logger(currentTimestamp + " : Work Orders Created. Progress Existing");

            // Step 3: Iterate over the workOrderList and write each work order to the file
            for (WorkOrder workOrder : Project3.workOrderList) {
                String workOrderData = workOrder.getFileData();
                String workOrderCreatedAt = workOrder.getCreatedAt();

                // Log the work order data with timestamp
                logger(workOrderCreatedAt + " : " + workOrderData);

                // Write the work order data to the file
                fileWriter.write(workOrderData + "\n");
            }

            // Close the FileWriter
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Static logger method to append messages to a log file
    private static void logger(String log) {
        try {
            FileWriter logWriter = new FileWriter("log.txt", true); // Append mode

            // Write the log message
            logWriter.write("log: " + log + "\n");

            logWriter.close();
        } catch (IOException e) {
            System.err.println("Error logging: " + e.getMessage());
        }
    }



    // Static method to read employee data
    public static void readEmployeeData(String employeeFileName) {
        try {
            FileInputStream fileStream = new FileInputStream(employeeFileName);
            Scanner fileScanner = new Scanner(fileStream);

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();  // Skip header line if exists
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                String employeeId = data[0];
                String firstName = data[1];
                String lastName = data[2];
                String email = data[3];
                String address = data[4];
                String phoneNumber = data[5];
                String clockedIn = data[6];
                String hiredDate = data[7];
                String certification = data[9];  // Assuming the certification is in the 9th column for tier 2 employees

                
                Employee employee;

                if (data[8].equals("tier1")) {
                    employee = new Employee(firstName, lastName, address, phoneNumber, email, employeeId, clockedIn, hiredDate);
                } else if (data[8].equals("tier2")) {
                    employee = new Tier2Employee(firstName, lastName, address, phoneNumber, email, employeeId, clockedIn, hiredDate, certification);
                    
                } else {
                    continue; // Skip invalid data
                }

                Project3.employeeList.add(employee);
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + employeeFileName);
        }
    }

    // Static method to read ticket data
    public static LinkedList<Ticket> readTicketData(String ticketFileName) {
        // Create a LinkedList to store tickets for the given file
        LinkedList<Ticket> ticketList = new LinkedList<Ticket>();
        
        try {
            // Open the ticket file
            FileInputStream fileStream = new FileInputStream(ticketFileName);
            Scanner fileScanner = new Scanner(fileStream);

            // Skip the header line if there is one
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            // Process each line in the ticket file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                // Extract fields for Customer and Ticket objects
                String customerId = data[0];
                String firstName = data[1];
                String lastName = data[2];
                String email = data[3];
                String address = data[4];
                String phoneNumber = data[5];
                String accountNumber = data[6];
                String ticketId = data[7];
                String createdAt = data[8];

                // Create a Customer object
                Customer customer = new Customer(firstName, lastName, address, phoneNumber, email, customerId, accountNumber);

                // Create a Ticket object
                Ticket ticket = new Ticket(customer, createdAt, ticketId);

                // Add the ticket to the list
                ticketList.add(ticket);
            }
            
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + ticketFileName);
        }

        // Print the number of tickets in the list for verification

        // Return the populated list of tickets
        return ticketList;
    }
}
