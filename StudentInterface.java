import java.util.ArrayList;
interface StudentInterface{

	public String vacantCourse(ArrayList<Course> x);
	public void register(ArrayList<Course> x, String n, String s);
	public void withdraw(ArrayList<Course> x, Student s, String name);
	public String schedule();
}
