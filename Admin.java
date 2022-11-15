import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class Admin extends User implements AdminInterface{

	private String username, password;		//Lists the variables that will be used in this class
	private int choice=0;
	private Scanner str = new Scanner(System.in);	//Used a scanner because I am more comfortable with Scanner class compared to BufferedReader
	private Scanner number = new Scanner(System.in);
	
	public Admin(String u, String p)	//Constructor to set a username and password for a new account
	{
		username = u;
		password = p;
	}
	public String getUsername()	//getters to check on the username and password and test if they were set correctly
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	@Override
	public void createCourse(ArrayList<Course> c) { //Creates a new course by asking for all the information needed by the constructor to make a new course
		String CourseName;	//All the variables required to construct a new course
		String CourseID;
		int max;
		String prof;
		String section;
		String location;
		System.out.println("You chose to create a course.");
		System.out.println("Please enter the course name");		//Each print line tells the user what to enter
		CourseName = str.nextLine();
		System.out.println("Please enter the course ID");
		CourseID = str.nextLine();
		System.out.println("Please enter the max allowed in the class");
		max = number.nextInt();
		System.out.println("Who will be the instructor");
		prof = str.nextLine();
		System.out.println("Enter the section");
		section = str.nextLine();
		System.out.println("Enter the location");
		location = str.nextLine();
		ArrayList<String> listNames = new ArrayList<String>(max);
		Course create = new Course(CourseName, CourseID, max, 0, listNames, prof, section, location);
		c.add(create);	//adds the new course to the original ArrayList
	}

	@Override
	public void deleteCourse(ArrayList<Course> c, String name, String id) {	//deletes the course that is specified by name and id
		for (int i=0; i<c.size(); i++)	//for-loop and if-statement to find the course that was specified
		{
			if (c.get(i).getName().equalsIgnoreCase(name) && c.get(i).getID().equalsIgnoreCase(id))	
			{
				c.remove(i);
			}
		}
	}

	@Override
	public void editCourse(ArrayList<Course> c, String name, String id) 
	{
		int changeN = 0;	//This variable is to change in max/current
		String changeS = "";	//changeS is to chagne the strings
		for (int i=0; i<c.size(); i++)
		{
			if (c.get(i).getName().equalsIgnoreCase(name) && c.get(i).getID().equalsIgnoreCase(id))	//Find the course entered
			{
				System.out.println("What would you like to edit?");	//Prompts for the user to enter
				System.out.println("1. Edit max students allowed");
				System.out.println("2. Edit current students");
				System.out.println("3. Edit list of names");
				System.out.println("4. Edit the instructor");
				System.out.println("5. Edit section number");
				System.out.println("6. Edit location");
				choice = number.nextInt();
				if (choice == 1)		//following if-statements to get to whichever part of the course the user wishes to change
				{
					System.out.println("What would you like to set the max to?");	//sets a new max in the course
					c.get(i).setMax(number.nextInt());
				}
				if (choice == 2)
				{
					System.out.println("What would you like to set the current number of students to?");	//Changes the current number of students, numerically only
					c.get(i).setCurrent(number.nextInt());
				}
				if (choice == 3)
				{
					int temp;
					System.out.println("How would you like to adjust the list of students? 1. Add student 2. Remove student");	//prompts the admin to either add or remove someone from the list
					temp = number.nextInt();	//temp as a variable name to not confuse choice variable with temp variable
					if(temp == 1)	
					{
						System.out.println("Who would you like to add?");
						c.get(i).addList(str.nextLine());	//calls method to add a student to the list of students registered, and user enters the name
					}
					if(temp == 2)
					{
						System.out.println("Who would you like to remove?");
						c.get(i).removeList(str.nextLine());	//calls method to remove a student if they are in the registered list already
					}
					
				}
				if (choice == 4)
				{
					System.out.println("What will be the name of the new instructor?");
					changeS = str.nextLine();	//Changes the name of the instructor through the setter method in the Course class
					c.get(i).setProf(changeS);
				}
				if (choice == 5)
				{
					System.out.println("What would you like to change the section number to?");
					changeS = str.nextLine();	//Changes the section number of the course
					c.get(i).setSection(changeS);
				}
				if (choice == 6)
				{
					System.out.println("What would you like to set the location to?");
					changeS = str.nextLine();	//Changes the location of the course
					c.get(i).setLocation(changeS);
				}
			}
		}
	}

	@Override
	public void getCourseInfo(ArrayList<Course> c, String id) {//Method that gets the course info by entering the course ID
		for (int i=0; i<c.size(); i++)
		{
			if(c.get(i).getID().equalsIgnoreCase(id))	//Finds a course that has the corresponding ID and uses the getters from Course class to get the information 
			{
				System.out.println(c.get(i).getName());
				System.out.println(c.get(i).getID());
				System.out.println(c.get(i).getMax());
				System.out.println(c.get(i).getCurrent());
				System.out.println(c.get(i).getList());
				System.out.println(c.get(i).getProf());
				System.out.println(c.get(i).getSection());
				System.out.println(c.get(i).getLoc());
			}
		}
		
	}

	@Override
	public void register(ArrayList<Course> c, ArrayList<Student> s, String fullName) 	//Registers a student to a class
	{
		String course, id;
		for (int i=0; i<s.size(); i++)
		{
			if (s.get(i).getFullName().equals(fullName))	//Finds the student matching the name entered
			{
				System.out.println("What class would you like to register this student in?");	//Prompts the user to enter the course name and ID
				course = str.nextLine();
				System.out.println("What section will he be entered in?");
				id = str.nextLine();
				s.get(i).register(c, course, id); //Registers the student in the course through the register method in the Student class
			}
		}
		
	}

	public String viewCourse(ArrayList<Course> c)	//Method override from User class because this allows for more information to be displayed
	{	 
		String courseName="";
		for (int i=0; i<c.size(); i++)	//goes through the ArrayList
		{
			if (i==c.size()-1)	//if-statement so there isn't an extra line after the final course is displayed
				courseName +=c.get(i).getName() + ", " + c.get(i).getID() + ", " + c.get(i).getCurrent() + ", " + c.get(i).getMax();	
			else
				courseName += c.get(i).getName() + ", " + c.get(i).getID() + ", " + c.get(i).getCurrent() + ", " + c.get(i).getMax() + "\n";
		}
		return courseName;
	}
	@Override
	public String viewFull(ArrayList<Course> c) //Method that views all the courses that are full
	{
		String courseList="";
		for (int i=0; i<c.size(); i++)	//Goes through the ArrayList
		{
			if (c.get(i).getMax() == c.get(i).getCurrent())	//If it encounters a course that has its current and max number matching
				courseList += c.get(i).getName();	//Places it onto a string
		}
		return courseList;	//returns the string of full courses
	}

	@Override
	public void addFull(ArrayList<Course> c) {	//Adds the full courses to a different ArrayList
		ArrayList<Course> fullCourses = new ArrayList<Course>();	//Creates a new ArrayList
		String courseList="";
		for (int i=0; i<c.size(); i++)
		{
			if (c.get(i).getMax() == c.get(i).getCurrent())	//Locates the courses that are full
				fullCourses.add(c.get(i));	//Places the full courses into the ArrayList
		}
		
	}

	@Override
	public String viewStudents(ArrayList<Course> c, String name, String id) //Method to get the list of students registered in a class
	{
		int count=0;
		while (!c.get(count).getName().equalsIgnoreCase(name) && !c.get(count).getID().equals(id))	//Finds the course that is specified
			count ++;
		return c.get(count).getList();	//Returns the list of students that are enrolled in the course
	}

	@Override
	public String studentSchedule(ArrayList<Student> student, String name) //Takes the student arrayList and the name of the student that should be found
	{
		String courses = "";
		for (int i=0; i<student.size(); i++)
		{
			if(student.get(i).getFullName().equals(name))	//Finds the student in the ArrayList
				student.get(i).schedule();	//Gets the complete schedule of the student's courses
				courses += student.get(i).schedule() + "\n";	//Separates each course by a new line so it doesn't pile up in one line
		}
		return courses;
	}

	@Override
	public void sort(ArrayList<Course> c) //Used to sort the array by ascending order
	{
		int temp1;	//2 temp values & nested for-loop
		int temp2;
		for(int i=0; i<c.size(); i++)	//first for-loop for the first temp value
		{
			for (int j=0; j<c.size(); j++)	//second for-loop for the second temp value
			{
				temp1 = c.get(i).getCurrent();	//sets temp1 equal to the index of i
				temp2 = c.get(j).getCurrent();	//sets temp2 equal to the index of j
				//As j goes through the loop, i will stay the same until j finishes its round through the entire array
				if (temp1 > temp2)
				{	 //if the value temp1 is larger than temp2, the two objects will switch place with each other and this will be iterated until everything is sorted
					c.set(i, c.get(j));
					c.set(j, c.get(i));
				}
			}
		}
		
	}
	

}
