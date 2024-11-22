import java.text.SimpleDateFormat;
import java.util.*;

public class Project3 {
    // Static file names
    public static String employeeFileName = "employee_data.csv";
    public static String tier1TicketFileName = "tier1_ticket_data.csv";
    public static String tier2TicketFileName = "tier2_ticket_data.csv";
    public static String workOrderFileName = "workorder_data.csv";

    // Static collections
    public static ArrayList<Employee> employeeList = new ArrayList<Employee>();
    public static Queue<Ticket> tier1TicketList = new LinkedList<Ticket>();
    public static Queue<Ticket> tier2TicketList = new LinkedList<Ticket>();
    public static ArrayList<WorkOrder> workOrderList = new ArrayList<WorkOrder>();

   
    public static void main(String[] args) {
    	System.out.println("Project 3 Work Order Generator");
    	System.out.println("");
    	System.out.println("Loading Employee Data");
    	
    	
        FileHandler.readEmployeeData(employeeFileName);
        
        System.out.println("Loading Ticket Data");
        
        LinkedList<Ticket> tier1Tickets = FileHandler.readTicketData(tier1TicketFileName);
        tier1TicketList.addAll(tier1Tickets);
        LinkedList<Ticket> tier2Tickets = FileHandler.readTicketData(tier2TicketFileName);
        tier2TicketList.addAll(tier2Tickets); 
        
        createWorkOrders();
        
        System.out.println("Writing Work Order Data to File");
        
       FileHandler.writeData(workOrderFileName);
        
        System.out.println("Work Orders Created. Program Exiting");
    }
    
    public static void createWorkOrders() {
        // Get the current date and time for when the work orders are created
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());



        // Continue assigning tickets until both queues are empty
        while (!tier1TicketList.isEmpty() || !tier2TicketList.isEmpty()) {
            Iterator<Employee> employeeIterator = employeeList.iterator();

            while (employeeIterator.hasNext()) {
                Employee employee = employeeIterator.next();

                // Assign Tier 1 tickets to any employee
                if (!tier1TicketList.isEmpty() && employee instanceof Employee) {
                    Ticket ticket = tier1TicketList.poll(); // Get a Tier 1 ticket
                    if (ticket != null) {
                        WorkOrder workOrder = new WorkOrder(employee, ticket, currentDateTime);
                        workOrderList.add(workOrder);
                    }
                }

                // Assign Tier 2 tickets only to Tier2Employee instances
                if (!tier2TicketList.isEmpty() && employee instanceof Tier2Employee) {
                    Ticket ticket = tier2TicketList.poll(); // Get a Tier 2 ticket
                    if (ticket != null) {
                        WorkOrder workOrder = new WorkOrder(employee, ticket, currentDateTime);
                        workOrderList.add(workOrder);
                        }
                }

                // Break out of the loop if both queues are empty
                if (tier1TicketList.isEmpty() && tier2TicketList.isEmpty()) {
                    break;
                }
            }
        }
        
        workOrderList.sort(Comparator.comparing(workOrder -> workOrder.getTicket().getCreatedAt()));

    }





}
