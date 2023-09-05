import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
	public static void main(String [] args)
	{
		try
			{
			boolean cont = true; 
			while (cont)
			{
			Users s = new Student();
			Users ad = new Advisor();
			String username; 
			String password;
				System.out.println("Welcome to Academic Advisor and Student Management System");
				System.out.println("1. New students id registration ");
				System.out.println("2. New advisors id registration ");
				System.out.println("3. Log in existed id(students) ");
				System.out.println("4. Log in existed id(advisors) ");   
				System.out.println("5. Exit the system ");
				Scanner input = new Scanner(System.in);
				int newOrOld=input.nextInt(); 
				if (newOrOld==1)  
				{
					boolean ok = true;
					File file = new File("D:\\Lecture Notes Jan 2022\\StudentUserPass.txt");
					FileWriter filewriter = new FileWriter(file,true); 
					BufferedWriter bufferwrite = new BufferedWriter(filewriter); 
					if (!file.exists())
					{
						file.createNewFile(); 
					}
					while(ok)
					{
					System.out.println("Set your username: ");
					username=input.next();
					System.out.println("Set your password: ");
					password=input.next();
					if (username.contentEquals(password))
					{
						System.out.println("Same username and password is not encouraged.");
						continue;
					}
					else
					{
					bufferwrite.append((String.format("%-30s", username)));  
					bufferwrite.append("|");                                 
					bufferwrite.append((String.format("%-30s", password)));
					bufferwrite.append("\r\n");
					bufferwrite.close();
					ok = false;
					}
					}
					continue;
					
				}
				else if (newOrOld==2)  
				{
					boolean ok=true;
					File file = new File("D:\\Lecture Notes Jan 2022\\AdvisorUserPass.txt"); //please change this according to your location
					FileWriter filewriter = new FileWriter(file,true); 
					BufferedWriter bufferwrite = new BufferedWriter(filewriter);
					if (!file.exists())
					{
						file.createNewFile(); 
					}			
					while(ok)
					{
					System.out.println("Set your username: ");
					username=input.next();
					System.out.println("Set your password: ");
					password=input.next();
					if (username.contentEquals(password))
					{
						System.out.println("Same username and password is not encouraged.");
						continue;
					}
					else
					{
					bufferwrite.append((String.format("%-30s", username)));  
					bufferwrite.append("|");                                 
					bufferwrite.append((String.format("%-30s", password)));
					bufferwrite.newLine();
					bufferwrite.close();
					ok=false;
					}
					}
					continue;
				}
				else if (newOrOld==3) 
				{
					ArrayList<String> List = new ArrayList<String>();
					File file = new File("D:\\Lecture Notes Jan 2022\\StudentUserPass.txt");
					FileReader filereader = new FileReader(file);  
					BufferedReader bufferreader = new BufferedReader(filereader);		
					String linetxt;
					while((linetxt = bufferreader.readLine())!=null) 
					{
						List.add(linetxt); 
						List.add("\r\n"); 
					}
					System.out.println("Please enter username: ");
					String usern = input.next();
					System.out.println("Please enter password: ");
					String passw=input.next();
					boolean success = false;
					for (String elements:List) 
					{
						if (elements.contains(usern.substring(0,usern.length())) && elements.contains(passw.substring(0,passw.length())))
						{  
							System.out.println("Log in as student successful. "); 
							success=true;
							if (success)  
							{
								boolean nxtfunc = s.afterLogin(s); 
								if (nxtfunc)
								{
									s.afterLogin(s);
								}

							}
						}
					}
					if (!success)
					{
						System.out.println("Username and password not found.");
					}
					bufferreader.close();
				}
					else if (newOrOld==4)
					{
						ArrayList<String> List = new ArrayList<String>();
						File file = new File("D:\\Lecture Notes Jan 2022\\AdvisorUserPass.txt");
						FileReader filereader = new FileReader(file);
						BufferedReader bufferreader = new BufferedReader(filereader);
						if (!file.exists())
						{
							file.createNewFile(); 
						}			
						String linetxt;
						while((linetxt = bufferreader.readLine())!=null)
						{
							List.add(linetxt);
							List.add("\r\n");
						}
						System.out.println("Please enter username: ");
						String usern = input.next();
						System.out.println("Please enter password: ");
						String passw=input.next();
						boolean success = false;
						for (String elements:List)
						{
							if (elements.contains(usern.substring(0,usern.length())) && elements.contains(passw.substring(0,passw.length())))
							{
								System.out.println("Log in as advisor successful. ");
								success=true;
								if (success)
								{
									boolean nxtfunc = ad.afterLogin(ad);
									if (nxtfunc)
									{
										ad.afterLogin(ad);
									}
									
								
								}
								
							}
						}
						if (!success)
						{
							System.out.println("Username and password not found.");
						}
						bufferreader.close();
					
					}
					else if (newOrOld==5)
					{
						System.out.println("Existing...");
						cont=false;
					}
			
			}
			}
		catch (IOException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		catch (InputMismatchException ime)
		{
			System.out.println("Invalid input. Existing the system.");
		}
		

		}

}