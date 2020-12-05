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
public class Logout 
{
	private Main main = null;
	
	/**
	 * Default constructor - hidden
	 */
	protected Logout()
	{
		// All done
		return;
	}
	/**
	 * Public constructor
	 * 
	 * @param main main class
	 */
	public Logout(Main main)
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
		// Set logged in member to null to indicate we are logged out
		main.setLoggedInMember(null);
		
		// All done
		return;
	}
}
