package task3;

public class Student implements Cloneable{

	private String firstName;
	private String lastName;
	private int age;
	private StudentAddress studentAddress;

	/* Student constructor */
	public Student(String firstName, String lastName, int age, StudentAddress studentAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.studentAddress = studentAddress;
	}

	/* setters and getters for firstName, lastName, age and studentAddress */
	public String toString(){
		return "Student Name:"+ this.firstName + " " + this.lastName
				+"  \nAge:"+this.age
				+" \nAddress:"+studentAddress;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student studentObj = (Student) obj;
		return this.age == studentObj.age
				&& this.firstName.equalsIgnoreCase(studentObj.firstName)
				&& this.lastName.equalsIgnoreCase(studentObj.lastName);
	}
	
	/* Start --> This is for Shallow Cloning */
	/*@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}*/
	/* End --> This is for Shallow Cloning */
	
	/* Start --> These lines of code is for Deep Cloning */
	@Override
	public Object clone() throws CloneNotSupportedException {
	  Student studentClone = (Student) super.clone();
	  StudentAddress studentAddressClone = new StudentAddress(this.studentAddress.getHouseNo(),
	                                      this.studentAddress.getStreet(),
	                                      this.studentAddress.getCity(), 
	                                      this.studentAddress.getPostalCode());
	  studentClone.setStudentAddress(studentAddressClone);
	  return studentClone;
	}
	private void setStudentAddress(StudentAddress studentAddressClone) {
		
	}
	/* End --> These lines of code is for Deep Cloning */
}
