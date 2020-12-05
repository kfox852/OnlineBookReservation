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
public class CancelReservation 
{
	private Main main = null;
	
	/**
	 * Default constructor - hidden
	 */
	protected CancelReservation()
	{
		// All done
		return;
	}
	/**
	 * Public constructor
	 * 
	 * @param main main class
	 */
	public CancelReservation(Main main)
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
		
		// Get logged in user name
		userName = main.getLoggedInMember().getUserName();
		
		// Get current cataloged book 13-digit ISBN
		isbn13 = main.getCatalogedBook().getIsbn13();
		
		// Cancel the reservation
		Reservation reservation = Reservation.getReservation(userName, isbn13);
		reservation.cancelReservation();
				
		// Update the book status
		CatalogedBook catalogedBook = CatalogedBook.getCatalogedBook(isbn13);
		catalogedBook.setStatus("available");
		catalogedBook.updateCatalogedBook();
		
		// All done
		return;
	}

}
