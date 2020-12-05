/**
 * 
 */
package reservation;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import main.Main;
import membership.Login;
import model.CatalogedBook;
import model.Reservation;
import util.Screen;

/**
 * @author TaKeBo, LLC
 *
 */
public class ViewReservations
{
	private Main main = null;
	
	/**
	 * Default constructor - hidden
	 */
	protected ViewReservations()
	{
		// All done
		return;
	}
	/**
	 * Public constructor
	 * 
	 * @param main main class
	 */
	public ViewReservations(Main main)
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
		String isbn13 = null;
				
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		// Get logged in user name
		userName = main.getLoggedInMember().getUserName();
		
		// Get reservations for this user name
		ArrayList<Reservation> reservations = Reservation.getReservations(userName);
		int size = reservations.size();
		
		// Build up a display string for the reservation fields
		String[] reservationString = new String[size + 1];
		reservationString[0] = "Exit";
		int index = 1;
		for (Reservation reservation : reservations)
		{
			reservationString[index] = "ISBN: " + reservation.getIsbn13()
			                 + " | " + "Requested: " + reservation.getRequestedDate().format(dateTimeFormatter);
			index++;
		}
		
		while (true)
		{
			// Display reservations
			int option = Screen.getOption("Enter a reservation number to Cancel the reservation or 0 to Exit:", reservationString);
			switch (option)
			{
				case 0:
				{
					return;
				}
				default:
				{
					// Get reservation
					Reservation reservation = reservations.get(option - 1);
					
					// Prompt for confirmation of the Cancel request
					System.out.println("Confirm reservation Cancel (yes / y or no / n): ");
					String confirm = Main.SCANNER.next();
					
					if (confirm.equals("yes") || confirm.equals("y"))
					{					
						// Cancel reservation
						reservation.cancelReservation();
					}
					
					
					
					// for Kelly:
					// main.setCatalogedBook(catalogedBook)
					// ViewDetails viewDetails = new ViewDetails(this);
					// viewDetails.begin();
	
					break;
				}
			}
		}
	}
}
