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
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author TaKeBo, LLC
 *
 */
public class Reservation  implements Serializable 
{
	private static final long serialVersionUID = 6722543174362096435L;
	
	// Class properties
	private String userName = null;
	private String isbn13 = null;
	private LocalDate requestedDate = null;

	// Database of valid users
	private static String reservationsTable = "ReservationsTable.txt";
	private static ArrayList<Reservation> reservations = new ArrayList<Reservation>();
											
	/**
	 * Default instance constructor - hidden
	 */
	protected Reservation()
	{
		// All done
		return;
	}
	
	/**
	 * 
	 * @param userName user name
	 * @param isbn13 cataloged book 13-digit ISBN
	 * @param requestedDate date cataloged book is requested
	 */
	public Reservation(String userName 
			         , String isbn13 
			         , LocalDate requestedDate)
	{
		this.userName = userName;
		this.isbn13 = isbn13;
		this.requestedDate = requestedDate;
		
		// All done
		return;
	}
	
	/**
	 * Gets all reservations
	 * 
	 * @return list of all reservations
	 */
	public static ArrayList<Reservation> getReservations()
	{		
		// All done
		return reservations;
	}
	
	/**
	 * Gets the reservations for the specified user name
	 * 
	 * @param userName user name to get the reservations for
	 * @return list of reservations for the specified user name
	 */
	public static ArrayList<Reservation> getReservations(String userName)
	{
		ArrayList<Reservation> listReservations = new ArrayList<Reservation>();
		
		for (Reservation reservation : reservations)
		{
			if(reservation.userName.equals(userName))
			{
				listReservations.add(reservation);
			}
		}
		
		// All done
		return listReservations;
	}
	
	/**
	 * Gets the reservation for the specified user name / 13-digit ISBN
	 * 
	 * @param userName user name to get the reservations for
	 * @param isbn13 13-digit ISBN to get the reservations for
	 * @return reservation for the specified user name / 13-digit ISBN
	 */
	public static Reservation getReservation(String userName, String isbn13)
	{
		for (Reservation reservation : reservations)
		{
			if(reservation.userName.equals(userName) && reservation.isbn13.equals(isbn13))
			{
				return reservation;
			}
		}
		
		// All done
		return null;
	}
	
	/**
	 * Adds the reservation instance in the database
	 */
	public void makeReservation()
	{
		for (Reservation reservation : reservations)
		{
			if(reservation.userName.equals(userName) && reservation.isbn13.equals(isbn13))
			{
				System.out.println("ERROR: Reservation already exists for user name (" + userName + ")" + "and ISBN13 (" + isbn13 + ")");
				return;
			}
		}  
		
		// New reservation...add it
		reservations.add(this);
		
		// Save the data
		save();
		
		// All done
		return;
	}
	
	/**
	 * Removes the reservation instance in the database
	 */
	public void cancelReservation()
	{
		// Find this member
		int index = 0;
		for (Reservation listReservation : reservations)
		{
			if (listReservation.userName.equals(userName))
			{
				reservations.remove(index);
				
				break;
			}
			index++;
		}
		
		// Save the data
		save();
		
		// All done
		return;
	}
	
	/**
	 * Updates the reservation instance in the database
	 * 
	 * @param requestedDate date cataloged book is requested
	 */
	public void updateReservation(LocalDate requestedDate)
	{
		// Find this reservation
		int index = 0;
		for (Reservation listReservation : reservations)
		{
			if (listReservation.userName.equals(userName) && listReservation.isbn13.equals(isbn13))
			{
				reservations.remove(index);
				this.setRequestedDate(requestedDate);
				reservations.add(index, this);
				
				break;
			}
			index++;
		}
		
		// Save the data
		save();
		
		// All done
		return;
	}
	
	/**
	 * Gets the user name for this reservation
	 * 
	 * @return user name
	 */
	public String getUserName()
	{
		// All done
		return userName;
	}
	
	/**
	 * Gets the 13-digit ISBN for this reservation
	 * 
	 * @return 13-digit ISBN
	 */
	public String getIsbn13()
	{
		// All done
		return isbn13;
	}
	
	/**
	 * Gets the requested date for this reservation
	 * 
	 * @return requested date
	 */
	public LocalDate getRequestedDate()
	{
		// All done
		return requestedDate;
	}
	
	/**
	 * Sets the requested date for this reservation
	 * 
	 * @param requestedDate date cataloged book is requested
	 * @return user name
	 */
	public void setRequestedDate(LocalDate requestedDate)
	{
		this.requestedDate =  requestedDate;
		
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
			FileInputStream fileIn = new FileInputStream(reservationsTable);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			reservations = (ArrayList<Reservation>) in.readObject();
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
			FileOutputStream fileOut = new FileOutputStream(reservationsTable);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(reservations);
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
		reservations.clear();
		// NOTE: NO BULK LOAD OF DEFAULT DATA

		// All done
		return;
	}
}
