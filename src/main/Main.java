/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Scanner;

import catalog.BrowseCatalog;
import catalog.SearchCatalog;
import membership.Login;
import membership.Logout;
import model.CatalogedBook;
import model.Member;
import model.Reservation;
import reservation.CancelReservation;
import reservation.MakeReservation;
import reservation.ViewReservations;
import util.Data;
import util.Screen;

/**
 * @author TaKeBo, LLC
 *
 */
public class Main {

	public Main mainClass = null;
	private Member member = null;
	private CatalogedBook catalogedBook = null;
	
	// Open a Scanner to read from the console
	public static Scanner SCANNER = new Scanner(System.in);
	
	/**
	 * @param args command line arguments
	 */
	public static void main(String[] args) 
	{
		Main tempClass = new Main();
		tempClass.mainClass = tempClass; 
		
		// Start up
		startUp();
		
		// Demo concepts
		//tempClass.demo();
		
		// First options
		tempClass.begin();
		
		// Clean up
		cleanUp();
		
		// All done
		return;

	}
	
	/**
	 * Begin processing for this class
	 */
	public void begin() 
	{
		
		boolean loop = true;
		while (loop)
		{
			// Check login
			if(mainClass.isLoggedIn())
			{
				// Logged in
				
				// Display options
				String[] options = {"Exit"               // 0
						          , "Logout"             // 1
						          , "Browse Catalog"     // 2
						          , "Search Catalog"     // 3
						          , "View Reservations"  // 4
						          , "Change Password"    // 5
						          , "Check Membership"   // 6
						           };
				
				int option = Screen.getOption("Enter Option:", options);
				switch (option)
				{
					case 0:
					{
						loop = false;
						System.out.println("Terminating...");
						return;
					}
					case 1:
					{
						Logout logout = new Logout(this);
						logout.begin();
						break;
					}
					case 2:
					{
						//System.out.println("Browse Catalog not implemented yet");
						BrowseCatalog browseCatalog = new BrowseCatalog(this);
						browseCatalog.begin();
						break;
					}
					case 3:
					{
						//System.out.println("Search Catalog not implemented yet");
						SearchCatalog searchCatalog = new SearchCatalog(this);
						searchCatalog.begin();
						break;
					}
					case 4:
					{
						//System.out.println("View Reservations not implemented yet");
						ViewReservations viewReservations = new ViewReservations(this);
						viewReservations.begin();
						break;
					}
					case 5:
					{
						System.out.println("Change Password not implemented yet");
						//ChangePassword changePassword = new ChangePassword(this);
						//changePassword.begin();
						break;
					}
					case 6:
					{
						System.out.println("Check Membership not implemented yet");
						//CheckMembership checkMembership = new CheckMembership(this);
						//checkMembership.begin();
						break;
					}
					default:
					{
						System.out.println("ERROR: Invalid option");
						break;
					}
				}
			}
			else
			{
				// Not logged in
				
				// Display options
				String[] options = {"Exit"            // 0
						          , "Login"           // 1
 						          , "Browse Catalog"  // 2
						          , "Search Catalog"  // 3
						           };
				
				int option = Screen.getOption("Enter Option:", options);
				switch (option)
				{
					case 0:
					{
						loop = false;
						System.out.println("Terminating...");
						return;
					}
					case 1:
					{
						Login login = new Login(this);
						login.begin();
						break;
					}
					case 2:
					{
						//System.out.println("Browse Catalog not implemented");
						BrowseCatalog browseCatalog = new BrowseCatalog(this);
						browseCatalog.begin();
						break;
					}
					case 3:
					{
						//System.out.println("Search Catalog not implemented");
						SearchCatalog searchCatalog = new SearchCatalog(this);
						searchCatalog.begin();
						break;
					}
					default:
					{
						System.out.println("ERROR: Invalid option");
						break;
					}
				}
			}
		}
		
		// All done
		return;
	}
	
	/**
	 * Cleans up the system before termination
	 */
	public static void cleanUp()
	{
		// Save database info
		Member.save();
		CatalogedBook.save();
		Reservation.save();

		// Exit system
		System.exit(0);

		// All done
		return;
	}
	
	/**
	 * Start up the system before termination
	 */
	public static void startUp()
	{
		// load database info
		Member.load();
		CatalogedBook.load();
		Reservation.load();

		// All done
		return;
	}
	
	/**
	 * 
	 * @return if logged in then true else false
	 */
	public boolean isLoggedIn()
	{
		boolean loggedIn = false;
		
		if(member != null) loggedIn = true;;
		
		// All done
		return loggedIn;
	}

	/**
	 * 
	 * @return logged in member
	 */
	public Member getLoggedInMember()
	{
		// All done
		return member;
	}

	/**
	 * 
	 * @param member member that is logged in
	 */
	public void setLoggedInMember(Member member)
	{
		this.member = member;
		
		// All done
		return;
	}

	/**
	 * 
	 * @return current cataloged book
	 */
	public CatalogedBook getCatalogedBook()
	{
		// All done
		return catalogedBook;
	}

	/**
	 * 
	 * @param catalogedBook current cataloged book
	 */
	public void setCatalogedBook(CatalogedBook catalogedBook)
	{
		this.catalogedBook = catalogedBook;
		
		// All done
		return;
	}

	/**
	 * Used during development to test various classes and methods
	 * when the entire system isn't available
	 */
	public void demo()
	{
		// Dump all data sets
		Data.DumpAll();
		
		// Setup Reservation parameters
		Member member = null;
		CatalogedBook catalogedBook = null;
		String userName = null;
		String isbn13 = null;
		MakeReservation makeReservation = null;
		
		userName = "tjenn300";
		member = Member.getMember(userName);
		setLoggedInMember(member);
		
		isbn13 = "9780321767530";
		catalogedBook = CatalogedBook.getCatalogedBook(isbn13);
		setCatalogedBook(catalogedBook);
		
		// Test Make Reservation concepts
		System.out.println("Testing MakeReservation");
		makeReservation = new MakeReservation(this);
		makeReservation.begin();
		
		userName = "kfox852";
		member = Member.getMember(userName);
		setLoggedInMember(member);
		
		isbn13 = "9780932633675";
		catalogedBook = CatalogedBook.getCatalogedBook(isbn13);
		setCatalogedBook(catalogedBook);
		
		// Test Make Reservation concepts
		System.out.println("Testing MakeReservation");
		makeReservation = new MakeReservation(this);
		makeReservation.begin();
		
		userName = "relwa136";
		member = Member.getMember(userName);
		setLoggedInMember(member);
		
		isbn13 = "9780932633491";
		catalogedBook = CatalogedBook.getCatalogedBook(isbn13);
		setCatalogedBook(catalogedBook);
		
		// Test Make Reservation concepts
		System.out.println("Testing MakeReservation");
		makeReservation = new MakeReservation(this);
		makeReservation.begin();
		
		// Dump all data sets
		Data.DumpAll();
		
		/*
		// Test Cancel Reservation concepts
		System.out.println("Testing CancelReservation");
		CancelReservation cancelReservation = new CancelReservation(this);
		cancelReservation.begin();
		
		// Dump all data sets
		Data.DumpAll();
		*/

		// Clear control parameters
		setLoggedInMember(null);
		setCatalogedBook(null);

		//All done
		return;
	}
}
