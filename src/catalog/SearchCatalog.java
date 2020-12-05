package catalog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import main.Main;
import model.CatalogedBook;

public class SearchCatalog {
	
	private Main main = null;
	
	/**
	 * Default constructor - hidden
	 */
	protected SearchCatalog()
	{
		// All done
		return;
	}
	/**
	 * Public constructor
	 * 
	 * @param main main class
	 */
	public SearchCatalog(Main main)
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
		// Get categories
		ArrayList<String> categories = CatalogedBook.getCategories();
		
		// Print a numbered list of categories
		for(int i = 0; i < categories.size(); i++){  
			System.out.println((i + 1) + " " + categories.get(i));  
		} 

		System.out.println("Please enter category number for more details: ");
		
		// Create a reader to prepare for user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Books in the users selected category
		ArrayList<CatalogedBook> booksByCategory = new ArrayList<CatalogedBook>();
		
		int categoryNumber = -1;
		while(categoryNumber == -1) {
			try {
				categoryNumber = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("Please enter a number.");
			}	
		}
		
		// Get name of category user entered
		String catagoryName = categories.get(categoryNumber - 1);
		
		// Gets books matching the category
		booksByCategory = CatalogedBook.getCatalogedBooks(catagoryName);
		
		// Printing books in the category
		for(int i = 0; i < booksByCategory.size(); i++){  
			System.out.println((i + 1) + " " + booksByCategory.get(i));  
		}
		
		System.out.println("Please enter book number for more details: ");
		
		int bookNumber = -1;
		while(bookNumber == -1) {
			try {
				 // Retrieve book number the user entered in the command line
				bookNumber = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Please enter a number");
			}
		}
		 
		CatalogedBook book = booksByCategory.get(bookNumber - 1);
		main.setCatalogedBook(book);
		 
		ViewDetails viewDetails = new ViewDetails(main);
		viewDetails.begin();
		 
		// All done
		return;
	}
}


