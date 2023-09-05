import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Advisor extends Users{
	
	public Advisor()
	{
		super();
	}
	public Advisor(String ad_id, String ad_name, String ad_faculty, String ad_contactNum, String ad_email)
	{
		super(ad_id,ad_name,ad_faculty,ad_contactNum,ad_email);
	}
	public String getAd_name() {
		return super.getName();
	}
	public void setAd_name(String ad_name) {
		super.setName(ad_name);
	}
	public String getAd_id() {
		return super.getId();
	}
	public void setAd_id(String ad_id) {
		super.setId(ad_id);
	}
	public String getAd_faculty() {
		return super.getFaculty();
	}
	public void setAd_faculty(String ad_faculty) {
		super.setFaculty(ad_faculty);
	}
	public String getAd_contactNum() {
		return super.getContactNum();
	}
	public void setAd_contactNum(String ad_contactNum) {
		super.setContactNum(ad_contactNum);
	}
	public String getAd_email() {
		return super.getEmail();
	}
	public void setAd_email(String ad_email) {
		super.setEmail(ad_email);
	}
	public boolean afterLogin(Users ad)
	{
		{
			Scanner input = new Scanner(System.in);
			boolean conti = true;
			while (conti)
			{
			try
			{
			System.out.println("1. Add advisor details. ");
			System.out.println("2. Edit advisor details. ");
			System.out.println("3. Search student details. ");
			System.out.println("4. Comment on students.");
			System.out.println("5. Update available appoitment time.");
			System.out.println("6. Exit to Menu. ");
			Users after; 
			boolean found;
			int choice = input.nextInt();
			if (choice==1)
			{
				after = addUsersDetails(ad);
			}
			else if (choice==2) 
			{
				{
					input.nextLine();
					System.out.println("Pleae enter advisor id to edit advisor details:");
					String sfedit = input.nextLine();
					int id = Integer.parseInt(sfedit);
					File file = new File("D:\\Lecture Notes Jan 2022\\AdvisorDetails.txt");
					found = usersExistence(sfedit,file); 
					if (found)  
					{
						toTemp(sfedit);
						try {
							editUsersDetails(sfedit);
						} catch (IOException e) {
							e.printStackTrace();
						} 
						File tempfile = new File("D:\\Lecture Notes Jan 2022\\ADTemp.txt");
						dRFile(file,tempfile);
					}
					else if (!found)
					{
						System.out.println("Advisor id not found.");
					}
				}
			}
			else if (choice==3)
			{
					File file = new File("D:\\Lecture Notes Jan 2022\\StudentDetails.txt");
					try {
						adSearch(file,"std");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			else if (choice==4)
			{
				boolean next = true;
				while (next)
				{
				input.nextLine();
				System.out.println("Please enter your advisor id to give comment to student:");
				String adid = input.nextLine();
				File advfile = new File("D:\\Lecture Notes Jan 2022\\AdvisorDetails.txt");
				found = usersExistence(adid, advfile); 
				if (found)
				{
				System.out.println("1. Add Comments on Student. ");
				System.out.println("2. Edit Comments on Student. ");
				int select = input.nextInt();
					if (select == 1)
					{
					File stdfile = new File("D:\\Lecture Notes Jan 2022\\StudentDetails.txt");
					System.out.println("Please enter student id to leave comments:");
					String sid = input.next();
					found = usersExistence(sid,stdfile); 
					if (found)
					{
						addComments(adid,sid);
					}
					else if (!found)
					{
						System.out.println("Student id not found.");
						continue;
					}
					}
					else if (select == 2)
					{
						System.out.println("Please enter student id to edit comments:");
						String sid=input.next();
						editComments(sid,adid);
					}
					else
					{
						System.out.println("Please enter valid input.");
						continue;
					}
				}
				else if (!found)
				{
					System.out.println("Advisor id not found.");
					continue;
				}
				next=false;
			}
			}
			else if (choice==5)
			{
				boolean cont=true;
				while(cont)
				{
				System.out.println("1. Add available appointment time.");
				System.out.println("2. Edit available appointment time.");
				int sel = input.nextInt();
				if(sel==1)
				{
					try {
						addAppointment();
					} catch (IOException e) {
						e.printStackTrace();
					}
					cont=false;
				}
				else if (sel==2)
				{
				try {
					updateAppointment();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				cont=false;
				}
				else
				{
					System.out.println("Please enter valid input.");
					continue;
				}
			}
			}
			else if (choice==6)
			{
				conti=false;
				return conti;
			}
			else
			{
				System.out.println("Please enter valid input.");
				continue;
			}
			System.out.println("Continue to another function? (1 for yes/ 2 for no) ");
			int cont = input.nextInt();
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
			catch (NumberFormatException nfe)
			{
				System.out.println("Please enter valid input.");
				continue;
			}
			catch (InputMismatchException ime)
			{
				System.out.println("Please enter valid input.");
				continue;
			}
			
		}
			return false;
		}
		
	}
	public Users addUsersDetails(Users ad)
	{
	boolean cont = true;
	Scanner input = new Scanner(System.in);
	System.out.println("Please enter advisor id: "); 
	super.setId(input.nextLine());
	int id=Integer.parseInt(super.getId());
	System.out.println("Please enter advisor name: "); 
	super.setName(input.nextLine());
	System.out.println("Please enter advisor faculty: "); 
	super.setFaculty(input.nextLine());
	System.out.println("Please enter advisor contact number: "); 
	super.setContactNum(input.nextLine());
	System.out.println("Please enter advisor email address: "); 
	super.setEmail(input.nextLine());
	while (cont)
	{
	try
	{
			File file = new File("D:\\Lecture Notes Jan 2022\\AdvisorDetails.txt"); 
			FileWriter filewriter = new FileWriter(file,true); 
			BufferedWriter bufferwrite = new BufferedWriter(filewriter);
			if (!file.exists())
			{
				file.createNewFile(); 
			}		
			bufferwrite.append("\n");
			bufferwrite.append("ID:");
			bufferwrite.append(String.format("%-10s", super.getId()));
			bufferwrite.append("\r\n");
			bufferwrite.append("Name:");
			bufferwrite.append(String.format("%-30s", super.getName()));
			bufferwrite.append("\r\n");
			bufferwrite.append("Faculty:");
			bufferwrite.append(String.format("%-10s", super.getFaculty()));
			bufferwrite.append("\r\n");
			bufferwrite.append("Contact Number:");
			bufferwrite.append(String.format("%-15s", super.getContactNum()));
			bufferwrite.append("\r\n");
			bufferwrite.append("Email:");
			bufferwrite.append(String.format("%-50s", super.getEmail()));
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
	return ad; 
	}
	public void editUsersDetails(String id) throws IOException 
	{
		 File originalFile = new File("D:\\Lecture Notes Jan 2022\\AdvisorDetails.txt");
		 File tempFile = new File("D:\\Lecture Notes Jan 2022\\ADTemp.txt");
		 	boolean found=false;
		        Scanner input = new Scanner(System.in);
		        System.out.println("Edit advisor details:");
		        System.out.println("1. Advisor id. ");
		        System.out.println("2. Advisor name.");
		        System.out.println("3. Advisor faculty.");
		        System.out.println("4. Advisor contact number.");
		        System.out.println("5. Advisor email.");
		        System.out.println("6. Exit.");
		        int toEdit = input.nextInt();
		        BufferedReader br = new BufferedReader(new FileReader(originalFile));
		        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		        String line = null;
		        
		        	while ((line = br.readLine()) != null) {
		            if (line.contains(id.substring(0,id.length()))) {
		            	found=true;
		            	if (toEdit == 1)
		            	{
		            		System.out.println("Please enter new advisor id: ");
				        	String edit = input.next();
				        	line="ID:" +edit; 
		            	}
		            	else if (toEdit == 2)
		                {
		                	pw.println(line);
		                	System.out.println("Please enter new advisor name: ");
				        	String edit = input.next();
		                	line = br.readLine();
		                	line = "Name:"+edit;
		                	pw.println(line);
		                	line = br.readLine();
		                }
		            	else if (toEdit == 3)
		            	{
		            		pw.println(line);
		            		line = br.readLine();
		            		pw.println(line);
		                	System.out.println("Please enter new advisor faculty: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Faculty:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 4)
		            	{
		            		pw.println(line);
		            		line = br.readLine();
		            		pw.println(line);
		            		line = br.readLine();
		            		pw.println(line);
		                	System.out.println("Please enter new advisor contact number: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Contact Num:"+edit;
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
		                	System.out.println("Please enter new advisor email: ");
		                	input.nextLine();
				        	String edit = input.nextLine();
		                	line = "Email:"+edit;
		                	pw.println(line);
		                	line=br.readLine();
		                	line=br.readLine();
		            	}
		            	else if (toEdit == 6)
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
		File file = new File("D:\\Lecture Notes Jan 2022\\AdvisorDetails.txt");
		ArrayList<String> List = new ArrayList<String>();
		FileReader filereader = new FileReader(file);
		BufferedReader bufferreader = new BufferedReader(filereader);
		String linetxt;
		while((linetxt = bufferreader.readLine())!=null)
		{
			List.add(linetxt);
		}
		File Tempfile = new File("D:\\Lecture Notes Jan 2022\\ADTemp.txt");
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
			if(i<6 && found)
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
	 public void addComments(String adid,String sid)
	 {
			try
			{
				Scanner input=new Scanner(System.in);
				String comment;
				File file = new File("D:\\Lecture Notes Jan 2022\\Comments.txt");
				FileWriter filewriter = new FileWriter(file,true);
				BufferedWriter bufferwrite = new BufferedWriter(filewriter);
				input.nextLine();
				System.out.printf("Please enter your comment to student "+sid+" : ");
				comment = input.nextLine();
				bufferwrite.append("\n Advisor ID: "+adid);
				bufferwrite.append("\t | Student ID: "+sid);
				bufferwrite.append("\t | Comments:"+comment);
				bufferwrite.close();
				filewriter.close();
				}
					
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
	 public void editComments(String iniAdvId, String iniStudId)
	 {
			File oriComment = new File("D:\\Lecture Notes Jan 2022\\Comments.txt");
			String oldComment = null;
			String newComment = null;
			
			if(!oriComment.exists())
			{
				System.out.println("No record found.\n");
			}
			else
			{
				try
				{
					Scanner sc = new Scanner (oriComment);	
					while (sc.hasNextLine())
					{
						String line = sc.nextLine();
							
						if(line.contains(iniAdvId) && line.contains(iniStudId))
						{
							StringTokenizer st = new StringTokenizer (line, "|");
							String studId = null;
							String advId = null;
							
							while (st.hasMoreTokens())
							{
								advId = st.nextToken();
								studId = st.nextToken();
								oldComment = st.nextToken();
							}
							
							advId = advId.trim();
							studId = studId.trim();
								System.out.printf("%s to %s %s \n", advId, studId, oldComment);
								System.out.println("Enter new comment: ");
								Scanner keyb = new Scanner(System.in);
								newComment = keyb.nextLine();
								break;	
							}
							
						}
						sc.close();
						File tempComment = new File ("D:\\Lecture Notes Jan 2022\\TempComments.txt");
						try 
						{
							FileReader fr = new FileReader (oriComment.getName());
							FileWriter fw = new FileWriter(tempComment.getName());
							BufferedReader in = new BufferedReader(fr);
							BufferedWriter out = new BufferedWriter(fw);
							
							String line=null;
							while ((line=in.readLine()) != null)
							{
								if (line.contains(oldComment))
								{
									line = line.replace(oldComment, "Comments: "+newComment); 
								}
								out.write(line);
								out.newLine();
							}
							in.close();
							out.close();
							fw.close();
							fr.close();
							
							System.out.println("Update successful.\n");
						}
						
						catch (IOException e)
						{
							System.out.println(e.getMessage());
						}
						
						finally 
						{
							oriComment.delete(); 
							tempComment.renameTo(oriComment); 
						}
					}
				catch (IOException e)
				{
					System.out.println(e.getMessage());
				}
				catch(NullPointerException npe)
				{
					System.out.println("You have not given any comment to this student.");
				}
			}
	 }
	 public void addAppointment() throws IOException
	 {
		 Scanner input=new Scanner(System.in);
		 File apFile = new File("D:\\Lecture Notes Jan 2022\\appointment.txt");
	        BufferedWriter bw = new BufferedWriter(new FileWriter(apFile,true));
	        System.out.println("Enter advisor ID: ");
	        String id=input.nextLine();
	        File avFile = new File("D:\\Lecture Notes Jan 2022\\tempAppointment.txt");
	        System.out.println("Enter advisor name: ");
	        String name=input.nextLine();
	        System.out.println("Enter available appointment time： ");
	        String av=input.nextLine();
	        if(!apFile.exists())
	        {
	        	apFile.createNewFile();
	        }
	        bw.append(id+",");
	        bw.append(name+",");
	        bw.append(av);
	        bw.append("\r\n");
	        bw.close();
	        System.out.println("Appointment time updated.");
	        }
	        

	 public void updateAppointment() throws IOException
	 {
		 File originalFile = new File("D:\\Lecture Notes Jan 2022\\appointment.txt");
		 File tempFile = new File("D:\\Lecture Notes Jan 2022\\tempAppoingment.txt");
		        Scanner console = new Scanner(System.in);
		        System.out.print("Enter ID : ");
		        String pID = console.nextLine();
		        boolean found = usersExistence(pID,originalFile);
		        System.out.print("Enter new appointment time: ");
		        String newTime = console.nextLine();
		        BufferedReader br = new BufferedReader(new FileReader(originalFile));
		        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		        String line = null;
		        while ((line = br.readLine()) != null) {
		            if (line.contains(pID)) {
		                String strAvailableTime = line.substring(line.lastIndexOf(","), line.length());
		                if (strAvailableTime != null){
		                    String UpdatedTime = newTime;
		                    System.out.println("Updated available time : " + UpdatedTime);
		                    line = line.substring(0,line.lastIndexOf(","))+"," + UpdatedTime;
		                }

		            }
		            pw.println(line);
		            pw.flush();
		        }
		        pw.close();
		        br.close();
		        if (!originalFile.delete()) 
		            System.out.println("Could not delete file");
		        if (!tempFile.renameTo(originalFile))
		            System.out.println("Could not rename file");
		        if (!found)
		        {
		        	System.out.println("This advisor have not update his/her appointment time yet.");
		        }  

		    }

	 }


