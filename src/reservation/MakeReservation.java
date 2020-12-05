/**
 * 
 */
package reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import main.Main;
import model.CatalogedBook;
import model.Reservation;

/**
 * @author TaKeBo, LLC
 *
 */
public class MakeReservation 
{
	private Main main = null;
	
	/**
	 * Default constructor - hidden
	 */
	protected MakeReservation()
	{
		// All done
		return;
	}
	/**
	 * Public constructor
	 * 
	 * @param main main class
	 */
	public MakeReservation(Main main)
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
		String dateFormat = "dd/MM/yyyy";
		String userName = null;
		String isbn13 = null;
		LocalDate requestedDate = null;
		
		// Get logged in user name
		userName = main.getLoggedInMember().getUserName();
		
		// Get current cataloged book 13-digit ISBN
		isbn13 = main.getCatalogedBook().getIsbn13();
		
		// Loop until we get a valid date
		while (requestedDate == null)
		{
			// Prompt for requested date
			System.out.println("Enter Requested Date (" + dateFormat + ") or \"Exit\": ");
			
			// Get requested date
			String requestedDateString = Main.SCANNER.next();
			
			// Check for exit requested
			if (requestedDateString.toLowerCase().equals("exit"))
			{
				return;
			}
			
			// Validate the date string
			try
			{
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
				requestedDate = LocalDate.parse(requestedDateString, dateTimeFormatter);
			}
			catch (DateTimeParseException ex)
			{
				System.out.println("ERROR: Invalid formatted date: " + requestedDateString);
				continue;
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
						
			// See if a reservation already exists for user name and isbn13
			Reservation reservation = Reservation.getReservation(userName, isbn13);
			
			// If the reservation already exists we will just update the requested date
			if (reservation == null)
			{
				// Make the reservation
				reservation = new Reservation(userName, isbn13, requestedDate);
				reservation.makeReservation();
				
				// Update the book status
				CatalogedBook catalogedBook = CatalogedBook.getCatalogedBook(isbn13);
				catalogedBook.setStatus("requested");
				catalogedBook.updateCatalogedBook();
			}
			else
			{
				// Update the reservation
				reservation.updateReservation(requestedDate);
			}
			
		}
		
		// All done
		return;
	}

}
