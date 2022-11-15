import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable{	//The parent class of student and admin

	private String username, password, firstName, lastName;	//Variables used in this class
	private String courseName ="";

	public User()	//Just a default constructor because different profiles are made between student and admin
	{
		username = "";
		password = "";
	}

	public String getFullName()	//Gets the full name of the admin/student
	{
		return firstName + " " + lastName;
	}
	
	public String viewCourse(ArrayList<Course> c)	//Method inherited by student but overridden in admin
	{	 
		for (int i=0; i<c.size(); i++)	//goes through the ArrayList
		{
			if (i==c.size()-1)	//if-statement so there isn't a comma at the end of the list
				courseName +=c.get(i).getName();	
			else
				courseName += c.get(i).getName() + ", ";
		}
		return courseName;
	}
}
