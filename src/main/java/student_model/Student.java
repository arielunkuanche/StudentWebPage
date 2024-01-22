package student_model;

public class Student {

	private int id;
	private String firstname, lastname, streetaddress, postcode, postoffice;

	public Student(int id, String firstName, String lastName, String streetAddress, String postCode,
			String postOffice) {
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
		this.streetaddress = streetAddress;
		this.postcode = postCode;
		this.postoffice = postOffice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPostoffice() {
		return postoffice;
	}

	public void setPostoffice(String postoffice) {
		this.postoffice = postoffice;
	}

	@Override

	public String toString() {
		return id + ": " + firstname + " " + lastname + ", " + streetaddress + ", " + postcode + " " + postoffice;
	}

}
