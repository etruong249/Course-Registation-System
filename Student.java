import java.util.ArrayList;

public class Student extends User implements StudentInterface{

	private String username, password, fullName, firstName, lastName;		//Lists the variables that will be used and can only be used in this class
	private String vacant="";
	private ArrayList<Course> registeredCourses = new ArrayList<Course>();
	
	public Student()
	{
		username = "";
		password = "";
	}
	
	public Student(String u, String p)	//Constructor that creates a new user name and password
	{
		username = u;
		password = p;
	}
	public String getUsername()	//Following are getters and setters for user name, password, first and last name
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setFirstName (String f)
	{
		firstName = f;
	}
	
	public void setLastName (String l)
	{
		lastName = l;
	}
	public String getFullName()
	{
		return firstName + " " + lastName;
	}
	
	public String vacantCourse(ArrayList<Course> c) //Method to check for courses that have vacancy
	{
		for (int i=0; i<c.size(); i++)	//for-loop to go through the array
		{
			if (c.get(i).getMax() != c.get(i).getCurrent()) 	//For-loop to check if that class's max is lower than the current amount of students registered
			{												//I didn't check if the current goes over because the size of the array for list of names is set to the max
				if (i== c.size()-1)								//If-else statement included so there isn't a comma at the end of the list
					vacant += c.get(i).getName();				//The two lines of course += adds the following strings to one
				else
					vacant += c.get(i).getName() + ", ";
			}
		}
		return vacant;
	}

	@Override
	public void register(ArrayList<Course> c, String name, String section) //Method to register the student into the course
	{
		int count = 0;	//Mix it up by using a while-loop instead of a for-loop like before
		while (!c.get(count).getName().equalsIgnoreCase(name) && !c.get(count).getSection().equalsIgnoreCase(section))
			count++; //Used the while loop to find the number that the course falls in on the list
		

		fullName = getFullName();	//retrieves the student's full name
		c.get(count).addList(fullName);	//finds the class and adds the student to the list of names registered
		registeredCourses.add(c.get(count));	//adds the course to the student's schedule/courses they are registered in
	}

	@Override
	public void withdraw(ArrayList<Course> c, Student s, String name) //Withdraws a student from the specified course
	{
		int count = 0;
		while (!c.get(count).getName().equalsIgnoreCase(name))	//use a while loop to find the name of the course in the Course array
			count++;
		c.get(count).removeList(s.getFullName());	//Removes the student's name from the list of studnets registered
		
		count = 0;
		while (registeredCourses.get(count).getName().equalsIgnoreCase(name)) //Finds the index of the course the student wishes to withdraw from
			registeredCourses.remove(count);	//removes the course from the student's registered courses
	}

	@Override
	public String schedule()	//method to return the schedule of the student
	{
		return registeredCourses.toString();	//returns the course info of all the courses the student is enrolled in
	}

}
