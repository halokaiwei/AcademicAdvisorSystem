import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Users {          //place allocated
	private String s_birthdate; //10
	private String s_address; //50
	private String s_course; //5
	private String s_studyYearSem; //10
	private String s_subjectTaken; //50
	private String s_comments; 
	private String s_appointment;
	private String s_app_adv;
	
	public Student()
	{
		
	};

	public Student(String s_id, String s_name, String s_birthdate, String s_address, String s_faculty, String s_email, String s_contactNum, String s_course, String s_studyYearSem, String s_subjectTaken)
	{
		super(s_id,s_name,s_faculty,s_contactNum,s_email);
		this.s_birthdate=s_birthdate;
		this.s_address=s_address;
		this.s_course=s_course;
		this.s_studyYearSem=s_studyYearSem;
		this.s_subjectTaken=s_subjectTaken;
	}
	public Student(String s_comments)
	{
		this.s_comments=s_comments;
	}
	public Student(String s_appointment, String ad_id)
	{
		this.s_appointment=s_appointment;
		this.s_app_adv=ad_id;
	}
	public String getS_appointment() {
		return s_appointment;
	}
	public void setS_appointment(String s_appointment) {
		this.s_appointment = s_appointment;
	}
	public String getS_app_adv() {
		return s_app_adv;
	}
	public void setS_app_adv(String s_app_adv) {
		this.s_app_adv = s_app_adv;
	}
	public String getS_id() {
		return super.getId();
	}
	public void setS_id(String s_id) {
		super.setId(s_id);
	}
	public String getS_name() {
		return super.getName();
	}
	public void setS_name(String s_name) {
		super.setName(s_name);
	}
	public String getS_birthdate() {
		return s_birthdate;
	}
	public void setS_birthdate(String s_birthdate) {
		this.s_birthdate = s_birthdate;
	}
	public String getS_address() {
		return s_address;
	}
	public void setS_address(String s_address) {
		this.s_address = s_address;
	}
	public String getS_faculty() {
		return super.getFaculty();
	}
	public void setS_faculty(String s_faculty) {
		super.setFaculty(s_faculty);
	}
	public String getS_email() {
		return super.getEmail();
	}
	public void setS_email(String s_email) {
		super.setEmail(s_email);
	}
	public String getS_contactNum() {
		return super.getContactNum();
	}
	public void setS_contactNum(String s_contactNum) {
		super.setContactNum(s_contactNum);
	}
	public String getS_course() {
		return s_course;
	}
	public void setS_course(String s_course) {
		this.s_course = s_course;
	}
	public String getS_studyYearSem() {
		return s_studyYearSem;
	}
	public void setS_studyYearSem(String s_studyYearSem) {
		this.s_studyYearSem = s_studyYearSem;
	}
	public String getS_subjectTaken() {
		return s_subjectTaken;
	}
	public void setS_subjectTaken(String s_subjectTaken) {
		this.s_subjectTaken = s_subjectTaken;
	}
	public String getS_comments() {
		return s_comments;
	}
	public void setS_comments(String s_comments) {
		this.s_comments = s_comments;
	}
	public boolean afterLogin(Users s)
	{
		boolean conti = true;
		int cont = 2;
		Scanner input = new Scanner(System.in);
		while(conti)
		{
		try
		{
		System.out.println("1. Add Student details. ");
		System.out.println("2. Edit Student details. ");
		System.out.println("3. Search advisor details. "); 
		System.out.println("4. Display advisor available appointment time. ");
		System.out.println("5. Display comments on student. ");
		System.out.println("6. Display programme structure. ");
		System.out.println("7. Exit to Menu. ");
		int choice = input.nextInt();
		Users after; // use to store the return Stud object
		boolean found;
		if (choice==1)
		{
			after = addUsersDetails(s);
		}
		else if (choice==2)
		{
			System.out.println("Pleae enter student id to edit student details:");
			String sfedit = input.next();
			int idsearch = Integer.parseInt(sfedit);
			File file = new File("D:\\Lecture Notes Jan 2022\\StudentDetails.txt");
			found = usersExistence(sfedit,file);  
			if (found)  
			{
				toTemp(sfedit); 
				try {
					editUsersDetails(sfedit);
				} catch (IOException e) {
					e.printStackTrace();
				}
				File tempfile = new File("D:\\Lecture Notes Jan 2022\\STDTemp.txt");
				dRFile(file,tempfile); 
			}
			else if (!found)
			{
				System.out.println("Student id not found."); 
			}
		}
		else if (choice==3)
		{
			File file = new File("D:\\Lecture Notes Jan 2022\\AdvisorDetails.txt");
			try {
				adSearch(file,"adv");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (choice==4)
		{
			try {
				displayAdvTime();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if (choice==5)
		{
			try {
			displayComments();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (choice==6)
		{
			displayProgrammeStructure();
		}
		else if (choice==7)
		{
			conti=false;
			return conti;
		}
		}
		catch (NumberFormatException nfe)
		{
			System.out.println("Please enter valid input.");
			continue;
		}
		System.out.println("Continue to another function?(1 for yes/ 2 for no) ");
		cont = input.nextInt();
		if (cont == 1)
		{
			continue;
		}
		else if (cont == 2)
		{
			conti = false;
			return conti;
		}
		}
		input.close();
		return false;
		}
	public Users addUsersDetails(Users s)
	{
		{
			boolean cont = true;
			Scanner input = new Scanner(System.in);
			while(cont)
			{
			try
			{
			System.out.println("Please enter student id: ");
			super.setId(input.nextLine());
			int id=Integer.parseInt(super.getId());
			System.out.println("Please enter student name: ");
			super.setName(input.nextLine());
			System.out.println("Please enter student birthdate: ");
			setS_birthdate(input.nextLine());
			System.out.println("Please enter student address: ");
			setS_address(input.nextLine());
			System.out.println("Please enter student faculty: ");
			super.setFaculty(input.nextLine());
			System.out.println("Please enter student email: ");
			super.setEmail(input.nextLine());
			System.out.println("Please enter student contact number: ");
			super.setContactNum(input.nextLine());
			System.out.println("Please enter student course: ");
			setS_course(input.nextLine());
			System.out.println("Please enter student study year and sem: ");
			setS_studyYearSem(input.nextLine());
			System.out.println("Please enter student subject taken: ");
			setS_subjectTaken(input.nextLine());
			s= new Student();
				File file = new File("D:\\Lecture Notes Jan 2022\\StudentDetails.txt"); 
				FileWriter filewriter = new FileWriter(file,true); 
				BufferedWriter bufferwrite = new BufferedWriter(filewriter);
				if (!file.exists())
				{
					file.createNewFile(); 
				}
				bufferwrite.append("\n"); 
				bufferwrite.append("ID:");
				bufferwrite.append(String.format("%-10s",super.getId()));
				bufferwrite.append("\r\n");
				bufferwrite.append("Name:");
				bufferwrite.append(String.format("%-30s", super.getName()));
				bufferwrite.append("\r\n");
				bufferwrite.append("Birth Date:");
				bufferwrite.append(String.format("%-15s", getS_birthdate()));
				bufferwrite.append("\r\n");
				bufferwrite.append("Addresss:");
				bufferwrite.append(String.format("%-50s", getS_address()));
				bufferwrite.append("\r\n");
				bufferwrite.append("Faculty:");
				bufferwrite.append(String.format("%-15s", super.getFaculty()));
				bufferwrite.append("\r\n");
				bufferwrite.append("Email:");
				bufferwrite.append(String.format("%-50s", super.getEmail()));
				bufferwrite.append("\r\n");
				bufferwrite.append("Contact Num:");
				bufferwrite.append(String.format("%-15s", super.getContactNum()));
				bufferwrite.append("\r\n");
				bufferwrite.append("Course:");
				bufferwrite.append(String.format("%-15s", getS_course()));
				bufferwrite.append("\r\n");
				bufferwrite.append("Study Year and Sem:");
				bufferwrite.append(String.format("%-15s", getS_studyYearSem()));
				bufferwrite.append("\r\n");
				bufferwrite.append("Subject Taken:");
				bufferwrite.append(String.format("%-50s", getS_subjectTaken()));
				bufferwrite.append("\r\n");
				bufferwrite.close();
				filewriter.close();
				cont=false;
				}
			catch (IOException e)
			{
				System.out.println("An error occurred.");
				e.printStackTrace();
			}	
			catch (NumberFormatException nfe)
			{
				System.out.println("Please enter valid input.");
				continue;
			}
			}
			return s;
			}
		}
	public void editUsersDetails(String id) throws IOException
	{
		 File originalFile = new File("D:\\Lecture Notes Jan 2022\\StudentDetails.txt");
		 File tempFile = new File("D:\\Lecture Notes Jan 2022\\STDTemp.txt");
		 boolean found=false;
		        Scanner input = new Scanner(System.in);
		        System.out.println("Edit student details:");
		        System.out.println("1. Student id. ");
		        System.out.println("2. Student name.");
		        System.out.println("3. Student birthdate.");
		        System.out.println("4. Student address.");
		        System.out.println("5. Student faculty.");
		        System.out.println("6. Student email.");
		        System.out.println("7. Student contact num.");
		        System.out.println("8. Student course.");
		        System.out.println("9. Student study year and sem.");
		        System.out.println("0. Student subject taken.");
		        System.out.println("00. Exit.");
		        int toEdit = input.nextInt();
		        BufferedReader br = new BufferedReader(new FileReader(originalFile));
		        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		        String line = null;
		        	while ((line = br.readLine()) != null) {
		            if (line.contains(id.substring(0,id.length()))) {
		            	found=true;
		            	if (toEdit == 1)
		            	{
		            		System.out.println("Please enter new student id: ");
				        	String edit = input.next();
				        	line="ID:" +edit; 
		            	}
		            	else if (toEdit == 2)
		                {
		                	pw.println(line);
		                	System.out.println("Please enter new student name: ");
				        	String edit = input.nextLine();
		                	String nextDetails = br.readLine();
		                	nextDetails = "Name:"+edit;
		                	pw.println(nextDetails);
		                }
		            	else if (toEdit == 3)
		            	{
		            		pw.println(line);
		            		line = br.readLine();
		            		pw.println(line);
		                	System.out.println("Please enter new student birthdate: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Birth Date:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 4)
		            	{
		            		pw.println(line);
		            		for(int i=0;i<2;i++)
		            		{
		            		line = br.readLine();
		            		pw.println(line);
		            		}
		                	System.out.println("Please enter new student address: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Address:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 5)
		            	{
		            		pw.println(line);
		            		for (int i=0;i<3;i++)
		            		{
		            		line = br.readLine();
		            		pw.println(line);
		            		}
		                	System.out.println("Please enter new student faculty: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Faculty:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 6)
		            	{
		            		pw.println(line);
		            		for(int i=0;i<4;i++)
		            		{
		            		line = br.readLine();
		            		pw.println(line);
		            		}
		                	System.out.println("Please enter new student email: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Email:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 7)
		            	{
		            		pw.println(line);
		            		for (int i=0;i<5;i++)
		            		{
		            		line = br.readLine();
		            		pw.println(line);
		            		}
		                	System.out.println("Please enter new student contact number: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Contact num:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 8)
		            	{
		            		pw.println(line);
		            		for(int i=0;i<6;i++)
		            		{
		            		line = br.readLine();
		            		pw.println(line);
		            		}
		                	System.out.println("Please enter new student course: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Course:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 9)
		            	{
		            		pw.println(line);
		            		for (int i =0;i<7;i++)
		            		{
		            		line = br.readLine();
		            		pw.println(line);
		            		}
		                	System.out.println("Please enter new student study year and sem: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Study year and sem:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 0)
		            	{
		            		pw.println(line);
		            		for (int i =0;i<8;i++)
		            		{
		            		line = br.readLine();
		            		pw.println(line);
		            		}
		                	System.out.println("Please enter new student subject taken: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Subject taken:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 00)
		            	{
		            		
		            	}
		            	else
		            	{
		            		System.out.println("Please enter valid input.");
		            	}
		  

		            }
		            
		            pw.println(line);
		            pw.flush();
		        }
		        pw.close();
		        br.close();    

		    }
	public void toTemp(String id)
	{
		try {
		File file = new File("D:\\Lecture Notes Jan 2022\\StudentDetails.txt");
		ArrayList<String> List = new ArrayList<String>();
		FileReader filereader = new FileReader(file);
		BufferedReader bufferreader = new BufferedReader(filereader);
		String linetxt;
		while((linetxt = bufferreader.readLine())!=null)
		{
			List.add(linetxt);
		}
		File Tempfile = new File("D:\\Lecture Notes Jan 2022\\STDTemp.txt");
		FileWriter filewriter = new FileWriter(Tempfile);
		BufferedWriter bufferwrite = new BufferedWriter(filewriter);
		if (!Tempfile.exists())
		{
			Tempfile.createNewFile();
		}
		boolean found = false;
		int i=0;
		for (String elements:List) 
		{
			i++;
			elements.replace(",", ""); 
			elements.replace("[", ""); 
			elements.replace("]", "");
		if (elements.contains(id.substring(0,id.length()))) 
		{
			i=0; 
			found = true; 
		}
		if(i<10 && found) 
		{ 					
			continue;
		}
		bufferwrite.append(elements);
		bufferwrite.append("\r\n");
		}
		bufferwrite.close();
		}
		catch (IOException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
	public void displayComments() throws IOException
	{
		boolean found;
		File stdfile = new File("D:\\Lecture Notes Jan 2022\\Comments.txt");
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your student id to see comments: ");
		String id = input.next();
		int verify = Integer.parseInt(id);
		found = usersExistence(id,stdfile);
		if (found)
		{
			String str;
			FileReader fr=null;
			fr=new FileReader(stdfile);
			BufferedReader br=new BufferedReader(fr);
			int i=0;
			while((str=br.readLine()) != null)
			{
				i++;
				if (str.contains("Student ID: "+id)) 
				{
					i=0;
					found=true;
					}
					if(i<2&&found)
					{
						System.out.println(str);
						i++;
						
					}
				
							
			}
			if (!found)
			{
				System.out.println("No comment found.");
			}
			br.close();
			fr.close();
		}
		else if (!found)
		{
			System.out.println("Student id not found.");
		}
	}
	public void displayAdvTime() throws FileNotFoundException
	{
		System.out.println("Enter advisor ID: ");
	    String advisor;
	    Scanner input = new Scanner(System.in);
	    advisor=input.nextLine();
	      File search = new File("D:\\Lecture Notes Jan 2022\\appointment.txt");
	      Scanner adivisorFile = new Scanner(search);
	      boolean found = false;
	      String preLine = "";
	      String curLine = "";
	      while(adivisorFile.hasNextLine())
	      {
	        preLine = curLine;
	        curLine = adivisorFile.nextLine();
	        if(curLine.contains(advisor))
	        {
	          found = true;
	          System.out.println(curLine);
	        }
	        
	      }
	      if(!found)
	      {
	        System.out.println("Advisor ID not found.");
	      }
	      found = false;
	      adivisorFile.close();
	    }

	public void displayProgrammeStructure()
	{
		try
		{
		System.out.println("Enter course you want to search(Sample: (SE)): ");
		File search=new File("D:\\Lecture Notes Jan 2022\\course.txt");
		FileReader fr=null;
		Scanner coursesee=new Scanner(System.in);
		String CourseFound,str;
		CourseFound=coursesee.nextLine();
		fr=new FileReader(search);
		BufferedReader br=new BufferedReader(fr);
		int i=0;
		boolean found=false;
			while((str=br.readLine()) != null)
			{
				i++;

				if (str.contains("Course:"+CourseFound)) 
				{
					i=0;
					found=true;
					}
				
					if(i<100&&found)
					{
						System.out.println(str);
						i++;
						
					}
					
			}
			br.close();
			fr.close();
			if (!found)
			{
				System.out.println("Course Not found");  
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
