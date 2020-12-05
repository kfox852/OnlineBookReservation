package catalog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import main.Main;
import model.CatalogedBook;
import util.Screen;


public class BrowseCatalog {

	private Main main = null;
	
	/**
	 * Default constructor - hidden
	 */
	protected BrowseCatalog()
	{
		// All done
		return;
	}
	/**
	 * Public constructor
	 * 
	 * @param main main class
	 */
	public BrowseCatalog(Main main)
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
		// Get list of available books
		ArrayList<CatalogedBook> availableBooks = CatalogedBook.getCatalogedBooks();
		
		// Print number and book for every book in available list
		for(int i = 0; i < availableBooks.size(); i++){  
			System.out.println((i + 1) + " " + availableBooks.get(i));  
		} 
		
		System.out.println("Please enter book number for more details: ");
		
		// Create a reader to prepare for user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Book number user selects
		int bookNumber = -1; 
		
		while(bookNumber == -1) {
			try {
				bookNumber = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("Please enter a number");
			}
		}
		
		// Get selected book by book number
		CatalogedBook selectedBook = availableBooks.get(bookNumber - 1);
		
		// Store selected book in main
		main.setCatalogedBook(selectedBook);
		
		ViewDetails viewDetails = new ViewDetails(main);
		viewDetails.begin();

		// All done
		return;
	}
}
