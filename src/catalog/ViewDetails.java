package catalog;

import main.Main;
import model.CatalogedBook;
import model.Reservation;
import reservation.MakeReservation;

public class ViewDetails {

	private Main main = null;
	
	/**
	 * Default constructor - hidden
	 */
	protected ViewDetails()
	{
		// All done
		return;
	}
	/**
	 * Public constructor
	 * 
	 * @param main main class
	 */
	public ViewDetails(Main main)
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
		CatalogedBook book = main.getCatalogedBook();
		System.out.println(book.toDetail());
		
		if(main.isLoggedIn()) {
		MakeReservation makeReservation = new MakeReservation(main);
		makeReservation.begin();
		}
		// All done
		return;
	}
}

