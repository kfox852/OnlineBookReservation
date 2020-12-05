/**
 * 
 */
package membership;

import main.Main;
import model.Member;

/**
 * @author TaKeBo, LLC
 *
 */
public class Login 
{
	private Main main = null;
	
	/**
	 * Default constructor - hidden
	 */
	protected Login()
	{
		// All done
		return;
	}
	/**
	 * Public constructor
	 * 
	 * @param main main class
	 */
	public Login(Main main)
	{
		this.main = main;
		
		// All done
		return;
	}
	
	/**
	 * Initial method call to coordinate the process
	 */
	public void begin()
	{
		String userName = null;
		
		// Loop until we get a valid user name or "Exit"
		while (userName == null)
		{
			System.out.println("Enter User Name or \"Exit\": ");
			userName = main.SCANNER.next();
			
			// Check for "Exit"
			if (userName.toUpperCase().equals("EXIT")) return;
			
			// Get member
			Member member = Member.getMember(userName);
			
			// Check for valid member
			if (member == null)
			{
				System.out.println("ERROR: Invalid User Name");
				userName = null;
				continue;
			}
			
			String password = null;
			
			// Get password
			System.out.println("Enter Password: ");
			password = main.SCANNER.next();
			
			// Validate password
			if (password.equals(member.getPassword()))
			{
				// Valid password
				main.setLoggedInMember(member);
				break;
			}
			else
			{
				// Invalid password
				System.out.println("ERROR: Invalid Password");
				userName = null;
				password = null;
			}
			
		}
		
		// All done
		return;
	}
}
