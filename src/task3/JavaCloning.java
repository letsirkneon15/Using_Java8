package task3;

public class JavaCloning {
	public static void main(String args[]){
	    StudentAddress studentAddress=new StudentAddress("109A","Knight Roads", "Lower Hutt Central", 5010);
	    Student student = new Student("Kristel", "Villanueva", 23, studentAddress);
	    Student studentClone=null;
	    try {
	      studentClone=(Student) student.clone();
	    }catch(CloneNotSupportedException cnse){
	      cnse.printStackTrace();
	    }
	 
	    System.out.println("Cloned Student Object: \n" + studentClone);
	  }
}
