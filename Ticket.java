public class Ticket implements Printable {
    private Customer customer;
    private String createdAt;
    private String ticketId;

    public Ticket(Customer customer, String createdAt, String ticketId) {
        this.customer = customer;
        this.createdAt = createdAt;
        this.ticketId = ticketId;
    }

    // Getters and Setters
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    @Override
    public String getFileData() {
        return this.customer.getFileData() + "," + getTicketId() + "," + getCreatedAt();
    }
}
