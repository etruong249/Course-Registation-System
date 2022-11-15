import java.io.IOException;
import java.util.ArrayList;
interface AdminInterface {
	
	//Course Management
	public void createCourse(ArrayList<Course> x);
	public void deleteCourse(ArrayList<Course> c, String name, String id);
	public void editCourse(ArrayList<Course> c, String name, String id);
	public void getCourseInfo(ArrayList<Course> c, String id);
	public void register(ArrayList<Course> c, ArrayList<Student> s, String fullName);
	
	//Reports
	public String viewFull(ArrayList<Course> c);
	public void addFull(ArrayList<Course> c);
	public String viewStudents(ArrayList<Course> c, String name, String id);
	public String studentSchedule(ArrayList<Student> student, String name);
	public void sort(ArrayList<Course> c);

}
