import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Users {
	private String id;
	private String name;
	private String faculty;
	private String contactNum;
	private String email;
	public Users(String id,String name,String faculty,String contactNum,String email)
	{
		this.id=id;
		this.name=name;
		this.faculty=faculty;
		this.contactNum=contactNum;
		this.email=email;
	}
	public Users()
	{
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public abstract boolean afterLogin(Users sad);
	public abstract Users addUsersDetails(Users sad);
	public abstract void editUsersDetails(String id) throws IOException;
	public boolean usersExistence(String id, File file)
	{
			boolean success=false;
			try {
			ArrayList<String> List = new ArrayList<String>();
			FileReader filereader = new FileReader(file);
			BufferedReader bufferreader = new BufferedReader(filereader);
			String linetxt;
			while((linetxt = bufferreader.readLine())!=null)
			{
				List.add(linetxt); 
				List.add("\r\n");
			}
			for (String elements:List) 
			{
				if (elements.contains(id.substring(0,id.length()))) 
				{
					success=true; 
				}
			}
			filereader.close();
			bufferreader.close();
			}
			catch (IOException e)
			{
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			
			return success; 
		}
	public abstract void toTemp(String id);
	public void dRFile(File file, File tempfile)
	{
		if(!tempfile.exists())
		{
			try {
				tempfile.createNewFile();
			} 
			catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		file.delete();
		tempfile.renameTo(file);
		
	}
	public void adSearch(File file,String adstd) throws IOException
	{
		boolean cont = true;
		Scanner input=new Scanner(System.in);
		while(cont)
		{
		try
		{
		String str;
		String idSearch;
		System.out.println("Please enter id to search: ");
		idSearch=input.nextLine();
		int verify=Integer.parseInt(idSearch);
			FileReader fr=null;
			fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			int i=0;
			boolean found=false;
			while((str=br.readLine()) != null)
			{
				i++;

				if (str.contains("ID:"+idSearch)) 
				{
					i=0;
					found=true;
					}
					if(i<20&&found&&adstd=="std") 
					{
						System.out.println(str);
						i++;
						
					}
					else if(i<10&&found&&adstd=="adv") 
					{
						System.out.println(str);
						i++;
					}
				
							
			}
			if(!found)
			{
				System.out.println("id not found.");
				continue;
			}
		}
		catch (NumberFormatException nfe)
		{
			System.out.println("Please enter valid input.");
			continue;
		}
		cont=false;
		
}
	}
}

