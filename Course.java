import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.Serializable;
public class Course implements Serializable{
	
	
	private String CourseName;	//These are the variables that will be used to store each column in the data set where each variable is named by the column they are storing
	private String CourseID;	
	private int max;
	private int current;
	private ArrayList<String> listNames = new ArrayList<String>(max);
	private String prof;
	private String section;
	private String location;
	private static final long serialVersionUID = 1L;
	
	public Course(String name, String id, int m, int c, ArrayList<String> list, String p, String s, String l) //Constructor that stores course data
	{
		CourseName = name;
		CourseID = id;
		max = m;
		current = c;
		listNames = list;
		prof = p;
		section = s;
		location = l;
	}
	public void setMax(int x)	//used to set the max amount of students allowed in a course
	{
		max = x;
	}
	public void setCurrent(int x)	//used to set the current amount of students in that course
	{
		current = x;
	}
	public void setProf(String s)	//Used to set the professor/instructor of the course
	{
		prof = s;
	}
	public void setSection(String s)	//used to set the section number of the course, it is a string instead of an int because it does not have to be computed
	{
		section = s;
	}
	public void setLocation(String s)	//used to set the location of the course
	{
		location = s;
	}
	public String getName()	//the following getters are named by the variable it is meant to control, like the setters above
	{
		return CourseName;
	}
	
	public String getID()
	{
		return CourseID;
	}
	
	public int getMax()
	{
		return max;
	}
	
	public int getCurrent()
	{
		return current;
	}
	
	public String getList()
	{
		return listNames.toString();
	}
	
	public String getProf()
	{
		return prof;
	}
	
	public String getSection()
	{
		return section;
	}
	
	public String getLoc()
	{
		return location;
	}
	public void addList(String s)	//Adds a student to the list of students in a course, and because the size of the array is set to the max students allowed
	{
		current++;			//adjusts the current number of students in the class whenever this method is called
		listNames.add(s);	//adds the name of the student to the list
	}
	
	public void removeList(String s)	//Removes a student from the list of students registered in the course
	{
		for (int i=0; i<listNames.size(); i++)	
		{
			if (listNames.get(i).equals(s))		//for-loop and if statement used to find the student that is entered
				listNames.remove(i);		//removes the student once their name is found
		}
	}

}
