import java.util.ArrayList;
import java.io.*;
public class CourseRegistrationMain {

	public static void main(String[] args) throws Exception {
		
		String profile, user, pass, courseName, courseID, fullName;	//Initializes the variables I need for the main
		int choice;
		int leave=0;
		String newStudent="";
//		Course c;
		Student st = new Student();
		ReadFile f = new ReadFile();
		boolean flag;
		Admin a = new Admin("Admin", "Admin001");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		try {
//			FileInputStream fis = new FileInputStream("MyCourses.ser");
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			
//		}
					//Initializes the ArrayLists
		
		  Course e = null;
	      try {
	         FileInputStream fis = new FileInputStream("src/MyCourses.ser");	
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         e = (Course) ois.readObject();
	         ois.close();
	         fis.close();
	      } catch (IOException i) {
	    	  i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         c.printStackTrace();
	       //  return;
	      }
	      ArrayList <Student> students = new ArrayList<Student>();
	      ArrayList <Course> courses = new ArrayList<Course>();
	      courses = f.convert();
			//converts the csv file into an ArrayList

		System.out.println("Hello. Please enter if you are a student or admin");	//Prompts user to answer whether they are an admin or student
		profile = in.readLine();
		
		//Admin code
			if(profile.equalsIgnoreCase("Admin"))	//Ignores case because it is not sensitive information
			{ 
				System.out.println("Please enter your username");
				user = in.readLine();
				System.out.println("Please enter your password");
				pass = in.readLine();
				if (a.getUsername().equals(user)&& a.getPassword().equals(pass))
					flag=true;
				else
					flag=false;
		
			while (flag == false)		//Boolean flag to decide whether the username and password are correct
			{
				System.out.println("Incorrect username or password! Please try again");	//Will keep looping until both username and password are correct
				System.out.println("Please enter your username");
				user = in.readLine();
				System.out.println("Please enter your password");
				pass = in.readLine();
				if (a.getUsername().equals(user) && a.getPassword().equals(pass))
					flag = true;
			}
			while (leave != 100)
			{
				System.out.println("What would you like to do: (Enter the number next to operation)");
				System.out.println("1 - Course Mangagement");
				System.out.println("2 - Reports");
			
			//Course Management
				choice = Integer.parseInt(in.readLine());
				if (choice == 1)
				{
					System.out.println("1. Create a new course");
					System.out.println("2. Delete a course");
					System.out.println("3. Edit a course");
					System.out.println("4. Display course info");
					System.out.println("5. Register a Student");
					System.out.println("6. Exit");
					choice = Integer.parseInt(in.readLine());
				
					if (choice == 1)
					{
						a.createCourse(courses);
					}
				
					if (choice == 2)
					{
						System.out.println("What is the name of the course you want to delete?");
						courseName = in.readLine();
						System.out.println("What is the section number of that course?");
						courseID = in.readLine();
						a.deleteCourse(courses, user, pass);
					}
				
					if (choice == 3)
					{
						System.out.println("What is the name of the course you want to edit?");
						courseName = in.readLine();
						System.out.println("What is the course ID?");
						courseID = in.readLine();
						a.editCourse(courses, courseName, courseID);
					}
				
					if (choice == 4)
					{
						System.out.println("What is the ID of the course?");
						courseID = in.readLine();
						a.getCourseInfo(courses, courseID);
					}
				
					if (choice == 5)
					{
						System.out.println("What is the full name of the student you want to register?");
						fullName = in.readLine();
						a.register(courses, students, fullName);
					}
				
					if (choice == 6)
					{
						 try {
					         FileOutputStream fos = new FileOutputStream("src/MyCourses.ser");
					         ObjectOutputStream oos = new ObjectOutputStream(fos);
					         oos.writeObject(e);
					         oos.close();
					         fos.close();
					         System.out.printf("Serialized data is saved in src/MyCourses.ser");
					      } catch (IOException i) {
					         i.printStackTrace();
					      }
						 System.out.println( );
						System.out.println("1. Exit program ");
						System.out.println("2. Back to menu");
						leave=Integer.parseInt(in.readLine());
						if(leave == 1)
						{
							leave = 100;
						}
					}
				}
			//Reports
				if (choice == 2)
				{
					System.out.println("1. View all courses");
					System.out.println("2. View courses that are filled");
					System.out.println("3. Add a class that is full"); // ???
					System.out.println("4. View class roster");
					System.out.println("5. View student schedules");
					System.out.println("6. Sort courses based on students registered");
					System.out.println("7. Exit");
					choice = Integer.parseInt(in.readLine());
					if (choice == 1)
					{
						System.out.println(a.viewCourse(courses));
					}
				
					if (choice == 2)
					{
						System.out.println(a.viewFull(courses));
					}
				
					if (choice == 3)
					{
						a.addFull(courses);
					}
				
					if (choice == 4)
					{
						System.out.println("What is the class name?");
						courseName = in.readLine();
						System.out.println("What is the course ID?");
						courseID = in.readLine();
						System.out.println(a.viewStudents(courses, courseName, courseID));
					}	
				
					if (choice == 5)
					{
						System.out.println("Whose schedule would you like to check?");
						fullName = in.readLine();
						a.studentSchedule(students, fullName);
					}
				
					if (choice == 6)
					{
						a.sort(courses);
					}
					if (choice == 7)
					{
						try {
					         FileOutputStream fos = new FileOutputStream("src/MyCourses.ser");
					         ObjectOutputStream oos = new ObjectOutputStream(fos);
					         oos.writeObject(e);
					         oos.close();
					         fos.close();
					         System.out.printf("Serialized data is saved in src/MyCourses.ser");
					      } catch (IOException i) {
					         i.printStackTrace();
					      }
						System.out.println();
						System.out.println("1. Exit program ");
						System.out.println("2. Back to menu");
						leave=Integer.parseInt(in.readLine());
						if(leave == 1)
						{
							leave = 100;
						}
					}
				}
			}
		}
		
		//Student code
		while (leave != 100)
		{
			if(profile.equalsIgnoreCase("student"))
			{
				
				System.out.println("Are you a new student?");
				newStudent = in.readLine();
				if (newStudent.equalsIgnoreCase("yes"))
				{
					System.out.println("Please create a username");
					user = in.readLine();
					System.out.println("Please create a password");
					pass = in.readLine();
					st = new Student(user, pass);
					System.out.println("What is your first name?");
					st.setFirstName(in.readLine());
					System.out.println("What is your last name?");
					st.setLastName(in.readLine());
					students.add(st);
				}
				else
				{
					System.out.println("Please enter your username");
					user = in.readLine();
					System.out.println("Please enter your password");
					pass = in.readLine();
					while (isStudent(students, user, pass) == false)
					{
						System.out.println("Incorrect username or password, please try again.");
						System.out.println("Please enter your username");
						user = in.readLine();
						System.out.println("Please enter your password");
						pass = in.readLine();
					}
					for (int i=0; i<students.size(); i++)
					{	
					if (students.get(i).getUsername().equals(user) && students.get(i).getPassword().equals(pass))
					{
						st=students.get(i);
					}
					else
					{
						System.out.println("Your username or password was incorrect.");
					}
				}
				
				}
				System.out.println("What would you like to do?");
				System.out.println("1. View all courses");
				System.out.println("2. View open courses");
				System.out.println("3. Register for a course");
				System.out.println("4. Withdraw from a course");
				System.out.println("5. View courses currently registered in");
				System.out.println("6. Exit");
				choice = Integer.parseInt(in.readLine());
				if (choice == 1) //View course options
				{
					System.out.println(st.viewCourse(courses));
				}
			
				if (choice == 2)//view open courses
				{
					System.out.println(st.vacantCourse(courses));
				}
			
				if (choice == 3)//register for courses
				{
					System.out.println("What is the name of the course you want to register for?");
					courseName = in.readLine();
					System.out.println("What is the course ID?");
					courseID = in.readLine();
					st.register(courses, courseName, courseID);
				}
			
				if (choice == 4)//withdraw from a course
				{
					System.out.println("What is the name of the course you want to drop?");
					courseName = in.readLine();
					st.withdraw(courses, st, courseName);
				}
				
				if (choice == 5)//view courses currently registered
				{
					System.out.println(st.schedule());
				}
			
				if (choice == 6)//exit
				{
					try {
				         FileOutputStream fos = new FileOutputStream("/tmp/employee.ser");
				         ObjectOutputStream oos = new ObjectOutputStream(fos);
				         oos.writeObject(e);
				         oos.close();
				         fos.close();
				         System.out.printf("Serialized data is saved in /tmp/employee.ser");
				      } catch (IOException i) {
				         i.printStackTrace();
				      }
					System.out.println("1. Exit program ");
					System.out.println("2. Back to menu");
					leave=Integer.parseInt(in.readLine());
					if(leave == 1)
					{
						leave = 100;
					}
			}
		}	
	}} 

	
	
	private static boolean isStudent(ArrayList<Student> s, String u, String p)
	{
		for (int i=0; i<s.size();i++)
		{
			if (s.get(i).getUsername().equals(u) && s.get(i).getPassword().equals(p))
				return true;
			else return false;
		}
		System.out.println();
		return false;
	}
	
	
}









	