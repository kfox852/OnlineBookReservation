/**
 * 
 */
package util;

import main.Main;

/**
 * @author TaKeBo, LLC
 *
 */
public class Screen 
{
	/**
	 * 
	 * @param prompt prompt to display
	 * @param options options to display
	 * @return option number entered
	 */

	public static int getOption(String prompt, String[] options)
	{
		int selection = -1;
		
		System.out.println(prompt);
		int optionNum = 0;
		for (String option : options)
		{
			System.out.println(optionNum + " - " + option);
			optionNum++;
		}
		
		// Get option 
		while (selection < 0)
		{
			try
			{
				selection = Main.SCANNER.nextInt();
				if (selection < 0 || selection > (options.length - 1))
				{
					System.out.println("ERROR: Selection out of range: " + selection);
					selection = -1;
				}
			}
			catch (Exception ex)
			{
				// Consume the invalid token
				String token = Main.SCANNER.next();
				System.out.println("ERROR: Invalid selection: " + token);
				selection = -1;
			}
		}
		
		
		// All done
		return selection;
	}

}
