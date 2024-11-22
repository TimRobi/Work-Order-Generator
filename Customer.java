public class Customer extends Person{
    private String customerId;
    private String accountNumber;
   

    public Customer(String firstName, String lastName, String address, String phoneNumber,
    				String email, String customerId, String accountNumber) {
    	super(firstName, lastName, address, phoneNumber, email);
    	this.customerId = customerId;
        this.accountNumber = accountNumber;
    }


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	 @Override
	    public String getFileData() {
	        return getCustomerId() + "," + super.getFileData();
	    }
}
