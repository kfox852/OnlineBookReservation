/**
 * 
 */
package util;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.CatalogedBook;
import model.Member;
import model.Reservation;

/**
 * @author TaKeBo, LLC
 *
 */
public class Data 
{

	public static void DumpAll()
	{
		// Dump all members
		DumpMembers();
		
		// Dump all cataloged books
		DumpCatalogedBooks();
		
		// Dump all reservations
		DumpReservations();

		// All done
		return;
	}
	
	public static void DumpMembers()
	{
		// Get all members
		ArrayList<Member> members = Member.getMembers();
		
		// Display all reservations
		System.out.println("Dump Members:");
		System.out.println("userName".concat(" ".repeat(20)).substring(0, 20) + "\t"
                         + "password".concat(" ".repeat(10)).substring(0, 10) + "\t"
                         + "email".concat(" ".repeat(30)).substring(0, 30) + "\t"
                         + "firstName".concat(" ".repeat(12)).substring(0, 12) + "\t"
				         + "lastName"
		                  );
		for (Member member : members)
		{
			System.out.println(member.getUserName().concat(" ".repeat(20)).substring(0, 20) + "\t"
                             //+ member.getPassword().concat(" ".repeat(10)).substring(0, 10) + "\t"
                             + "*".repeat(10).substring(0, 10) + "\t"
                             + member.getEmail().concat(" ".repeat(30)).substring(0, 30) + "\t"
                             + member.getFirstName().concat(" ".repeat(12)).substring(0, 12) + "\t"
                             + member.getLastName()
                              );
		}
		
		// All done
		return;
	}

	public static void DumpCatalogedBooks()
	{
		// Get all cataloged books
		ArrayList<CatalogedBook> catalogedBooks = CatalogedBook.getCatalogedBooksAll();
		
		// Display all reservations
		System.out.println("Dump Cataloged Books Categories:");
		System.out.println("category"
				          );
		for (String category : CatalogedBook.getCategories())
		{
			System.out.println(category);
		}
		
		// Display all reservations
		System.out.println("Dump Cataloged Books:");
		System.out.println("isbn13".concat(" ".repeat(13)).substring(0, 13) + "\t" 
		                 + "status".concat(" ".repeat(10)).substring(0, 10) + "\t"
		                 + "category".concat(" ".repeat(20)).substring(0, 20) + "\t"
		                 + "title(first 25 chars)".concat(" ".repeat(25)).substring(0, 25) + "\t"
		                 + "author(first 25 chars)".concat(" ".repeat(25)).substring(0, 25) + "\t"
		                 + "description(first 30 chars)".concat(" ".repeat(30)).substring(0, 30) + "\t"
		                 + "location"
				          );
		for (CatalogedBook catalogedBook : catalogedBooks)
		{
			System.out.println(catalogedBook.getIsbn13().concat(" ".repeat(13)).substring(0, 13) + "\t"
		                     + catalogedBook.getStatus().concat(" ".repeat(10)).substring(0, 10) + "\t"
                             + catalogedBook.getCategory().concat(" ".repeat(20)).substring(0, 20) + "\t"
		                     + catalogedBook.getTitle().concat(" ".repeat(25)).substring(0, 25) + "\t"
		                     + catalogedBook.getAuthor().concat(" ".repeat(25)).substring(0, 25) + "\t"
		                     + catalogedBook.getDescription().concat(" ".repeat(30)).substring(0, 30) + "\t"
		                     + catalogedBook.getLocation()
                              );
		}
		
		// All done
		return;
	}

	public static void DumpReservations()
	{
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		// Get all reservations
		ArrayList<Reservation> reservations = Reservation.getReservations();
		
		// Display all reservations
		System.out.println("Dump Reservations:");
		System.out.println("userName".concat(" ".repeat(20)).substring(0, 20) + "\t"
		                 + "isbn13".concat(" ".repeat(13)).substring(0, 13) + "\t"
				         + "requestedDate"
		                  );
		for (Reservation reservation : reservations)
		{
			System.out.println(reservation.getUserName().concat(" ".repeat(20)).substring(0, 20) + "\t"
		                     + reservation.getIsbn13().concat(" ".repeat(13)).substring(0, 13) + "\t"
                             + reservation.getRequestedDate().format(dateTimeFormatter)
                              );
		}
		
		// All done
		return;
	}
}
