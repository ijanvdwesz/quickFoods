package quickFoods;

public class Customer {
	// Attributes
	private int orderNo;
	private String customerName;
	private String contactNo;
	private String address;
	private String location;
	private String email;

//Constructor method used to create a customer object with specified attributes
	public Customer(int orderNo, String customerName, String contactNo, String address, String location, String email) {
		this.orderNo = orderNo;
		this.customerName = customerName;
		this.contactNo = contactNo;
		this.address = address;
		this.location = location;
		this.email = email;
	}

	// Getters and Setters
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// toString method to print the object's information
	@Override
	public String toString() {
		return "Customer{" + "orderNo=" + orderNo + ", customerName='" + customerName + '\'' + ", contactNo='" + contactNo
				+ '\'' + ", address='" + address + '\'' + ", location='" + location + '\'' + ", email='" + email + '\'' + '}';
	}
}