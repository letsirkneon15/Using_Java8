package Task1; 
public class CompactDisc extends ComputerDisc implements IDataStorage {

private final java.lang.String NAME="Compact disc";
private final long CAPACITY_MB=900L;
private java.lang.String label;
private java.lang.String contents;

public CompactDisc(){
	super(120.0D, 15.0D, 1.1D);
	contents = "";
	label = "[no-label]";
}
public CompactDisc(String l, String c){
	super(120.0D, 15.0D, 1.1D);
	contents =c; 
	label =l;
}
public String getLabel(){
return label;
}
public void setLabel(String l){
label=l;
}
public void setContents(String c){
contents = c;
}
public String getContents(){
return contents;
}
public long getCapacity(){
return 900000000L;
}
public String getShape(){
return "Middle-hollowed, flat, circular plate";
}
public String getName(){
return "Compact disc";
}
private double getThickness(){
return 1.1;
}
private double getExternalRadius(){
return 120.0;
}
private double getInternalRadius(){
return 15.0;
}
public static void main(String[] args){
	CompactDisc cd=new CompactDisc();
	System.out.format("%s has been created!\nA %s's basic shape is a %s that has the dimensions of:\n-%5.1f mm internal radius\n-%5.1f mm external radius\n-%5.1f mm thickness\n", new Object[] { 
		cd.getName(), cd.getName(), cd.getShape(), Double.valueOf(cd.getInternalRadius()), Double.valueOf(cd.getExternalRadius()), Double.valueOf(cd.getThickness())}); 

	System.out.format("\nIt can hold a massive %d bytes of data!\nThis is equivalent to %d MEGABYTES!!!\n", new Object[] {
 		Long.valueOf(cd.getCapacity()), Long.valueOf(cd.getCapacity() / 1000L / 1000L) });

	cd.setContents("lots of music, pictures, and a few random video clips");
	System.out.format("\nThe contents of %s are: %s\n", new Object[] {cd.getName(), cd.getContents() });
  }
}