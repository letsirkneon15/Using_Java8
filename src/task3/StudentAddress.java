package task3;

public class StudentAddress {
	private String houseNo;
	private String street;
	private String city;
	private int postalCode;

	/* Student Address constructor */
	public StudentAddress(String houseNo, String street, String city, int postalCode) {
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
	}

	/* setters and getters for houseNo, street, city and postal code */
	@Override
	public String toString() {
		return "StudentAddress{" +
				"houseNo='" + houseNo + '\'' +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", postalCode='" + postalCode + '\'' +
				'}';
	}

	public String getHouseNo() {
		return null;
	}

	public String getStreet() {
		return null;
	}

	public String getCity() {
		return null;
	}

	public int getPostalCode() {
		return 0;
	}
}
