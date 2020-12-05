/**
 * 
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author TaKeBo, LLC
 *
 */
public class Member implements Serializable 
{
	private static final long serialVersionUID = -7971509395097406660L;
	
	// Class properties
	private String userName = null;
	private String password = null;
	private String email = null;
	private String firstName = null;
	private String lastName = null;

	// Database of valid users
	private static String membersTable = "MembersTable.txt";
	private static ArrayList<Member> members = new ArrayList<Member>();
											
	/**
	 * Default instance constructor - hidden
	 */
	protected Member()
	{
		// All done
		return;
	}
	
	/**
	 * Public instance constructor
	 * 
	 * @param userName use name
	 * @param password password
	 * @param email email address
	 * @param firstName first name
	 * @param lastName last name
	 */
	public Member(String userName 
			    , String password 
			    , String email
			    , String firstName
			    , String lastName)
	{
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		
		// All done
		return;
	}
	
	/**
	 * Gets all members
	 * 
	 * @return list of all members
	 */
	public static ArrayList<Member> getMembers()
	{		
		// All done
		return members;
	}
	
	/**
	 * 
	 * @param userName user name to find member
	 * @return member for specified userName
	 */
	public static Member getMember(String userName)
	{
		Member member = null;
		
		for (Member listMember : members)
		{
			if (listMember.userName.equals(userName))
			{
				member = listMember;
				break;
			}
		}
		
		// All done
		return member;
	}
	
	/**
	 * Updates the member instance in the database
	 */
	public void updateMember()
	{
		// Find this member
		int index = 0;
		for (Member listMember : members)
		{
			if (listMember.userName.equals(userName))
			{
				members.remove(index);
				members.add(index, this);
				
				break;
			}
			index++;
		}
		
		// Save the data
		save();
		
		// All done
		return;
	}
	
	public String getUserName()
	{
		// All done
		return userName;
	}
	
	private void setUserName(String userName)
	{
		this.userName = userName;
		
		// All done
		return;
	}
	
	public String getPassword()
	{
		// All done
		return password;
	}
	
	public void setPasssword(String password)
	{
		this.password = password;
		
		// All done
		return;
	}
	
	public String getEmail()
	{
		// All done
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
		
		// All done
		return;
	}
	
	public String getFirstName()
	{
		// All done
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
		
		// All done
		return;
	}
	
	public String getLastName()
	{
		// All done
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
		
		// All done
		return;
	}
	
	/**
	 * Load the data from the database
	 */
	@SuppressWarnings("unchecked")
	public static void load()
	{
		// Let's deserialize an Object
		try {
			FileInputStream fileIn = new FileInputStream(membersTable);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			members = (ArrayList<Member>) in.readObject();
			//System.out.println("INFO: Members Deserialization Successful... Members count: " + members.size());
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			// Initialize data
			bulkLoad();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// All done
		return;
	}

	/**
	 * Save the data to the database
	 */
	public static void save()
	{
		// Let's serialize an Object
		try {
			FileOutputStream fileOut = new FileOutputStream(membersTable);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(members);
			out.close();
			fileOut.close();
			//System.out.println("INFO: Members Serialization Successful... Checkout file: " + membersTable);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		// All done
		return;
	}

	/**
	 * Bulk load the default data
	 */
	private static void bulkLoad()
	{
		// Initialize data
		members.clear();
		members.add(new Member("relwa136", "password", "relwa136@live.kutztown.edu", "Bob", "Elward"));
		members.add(new Member("kfox852", "password", "kfox852@live.kutztown.edu", "Kelly", "Fox"));
		members.add(new Member("tjenn300", "password", "tjenn300@live.kutztown.edu", "Tamara", "Jennings"));

		// All done
		return;
	}
}
