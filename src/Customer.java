
public class Customer {
	String name;
	String ssn;
	String address;
	String zipcode;
	public Customer(String name, String ssn, String address, String zipcode) {
		super();
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.zipcode = zipcode;
	}
	public Customer() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", ssn=" + ssn + ", address=" + address + ", zipcode=" + zipcode + "]";
	}
	
	
}
