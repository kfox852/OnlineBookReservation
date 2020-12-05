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
public class CatalogedBook implements Serializable
{
	private static final long serialVersionUID = -193320843477950213L;
	
	// Class properties
	private String isbn13 = null;
	private String title = null;
	private String author = null;
	private String description = null;
	private String category = null;
	private String location = null;
	private String status = null;

	// Database of valid users
	private static String catalogedBookTable = "CatalogedBookTable.txt";
	private static ArrayList<CatalogedBook> catalogedBooks = new ArrayList<CatalogedBook>();
											
	/**
	 * Default instance constructor - hidden
	 */
	protected CatalogedBook()
	{
		// All done
		return;
	}
	
	/**
	 * Public instance constructor
	 * 
	 * @param isbn13 use name
	 * @param title title
	 * @param author author address
	 * @param description first name
	 * @param category last name
	 * @param location building location
	 * @param status availability status
	 */
	public CatalogedBook(String isbn13 
			         , String title 
			         , String author
			         , String description
			         , String category
			         , String location
			         , String status)
	{
		this.isbn13 = isbn13;
		this.title = title;
		this.author = author;
		this.description = description;
		this.category = category;
		this.location = location;
		this.status = status;
		
		// All done
		return;
	}
	
	/**
	 * Gets a list of unique categories from the available cataloged books
	 * 
	 * @return list of unique categories
	 */
	public static ArrayList<String> getCategories()
	{
		ArrayList<String> categories = new ArrayList<String>();
		
		for (CatalogedBook catalogedBook : catalogedBooks)
		{
			if(catalogedBook.isAvailable())
			{
				if (!categories.contains(catalogedBook.getCategory()))
				{
					categories.add(catalogedBook.getCategory());
				}
			}
		}
		
		// All done
		return categories;
	}
	
	/**
	 * Gets a list of available cataloged books
	 * 
	 * @return list of unique categories
	 */
	public static ArrayList<CatalogedBook> getCatalogedBooks()
	{
		ArrayList<CatalogedBook> availableCatalogedBooks = new ArrayList<CatalogedBook>();
		
		for (CatalogedBook catalogedBook : catalogedBooks)
		{
			if(catalogedBook.isAvailable())
			{
				availableCatalogedBooks.add(catalogedBook);
			}
		}
		
		// All done
		return availableCatalogedBooks;
	}
	
	/**
	 * Gets a list of all cataloged books
	 * 
	 * @return list all cataloged books
	 */
	public static ArrayList<CatalogedBook> getCatalogedBooksAll()
	{
		// All done
		return catalogedBooks;
	}
	
	/**
	 * Gets a list of available cataloged books that match the category specified
	 * 
	 * @return list of unique categories
	 */
	public static ArrayList<CatalogedBook> getCatalogedBooks(String category)
	{
		ArrayList<CatalogedBook> availableCatalogedBooks = new ArrayList<CatalogedBook>();
		
		for (CatalogedBook catalogedBook : catalogedBooks)
		{
			if(catalogedBook.isAvailable())
			{
				if (catalogedBook.getCategory().equals(category))
				{
					availableCatalogedBooks.add(catalogedBook);
				}
			}
		}
		
		// All done
		return availableCatalogedBooks;
	}
	
	/**
	 * Get the cataloged book for the specified isbn13
	 * 
	 * @param isbn13 user name to find catalog book
	 * 
	 * @return catalog book for specified isbn13
	 */
	public static CatalogedBook getCatalogedBook(String isbn13)
	{
		CatalogedBook catalogedBook = null;
		
		for (CatalogedBook listCatalogedBook : catalogedBooks)
		{
			if (listCatalogedBook.isbn13.equals(isbn13))
			{
				catalogedBook = listCatalogedBook;
				break;
			}
		}
		
		// All done
		return catalogedBook;
	}
	
	/**
	 * Updates the cataloged book instance in the database
	 * 

	 */
	public void updateCatalogedBook()
	{
		// Find this cataloged book
		int index = 0;
		for (CatalogedBook listCatalogedBook : catalogedBooks)
		{
			if (listCatalogedBook.isbn13.equals(isbn13))
			{
				catalogedBooks.remove(index);
				catalogedBooks.add(index, this);
				
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
	 * Checks to see if the book is available
	 * 
	 * @return if available true else false
	 */
	public boolean isAvailable()
	{
		boolean available = false;
		
		if (status.toLowerCase().equals("available")) available = true;
		
		// All done
		return available;
	}
	
	public String getIsbn13()
	{
		// All done
		return isbn13;
	}
	
	private void setIsbn13(String isbn13)
	{
		this.isbn13 = isbn13;
		
		// All done
		return;
	}
	
	public String getTitle()
	{
		// All done
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
		
		// All done
		return;
	}
	
	public String getAuthor()
	{
		// All done
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
		
		// All done
		return;
	}
	
	public String getDescription()
	{
		// All done
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
		
		// All done
		return;
	}
	
	public String getCategory()
	{
		// All done
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
		
		// All done
		return;
	}
	
	public String getLocation()
	{
		// All done
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
		
		// All done
		return;
	}
	
	public String getStatus()
	{
		// All done
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
		
		// All done
		return;
	}
	
	public String toString() {
		return String.format("%-75.74s %16s %16s %16s", getTitle(), getAuthor(), getIsbn13(), getCategory()); 
	}
	
	public String toDetail() {
		String formattedDescription = "";
		for(int i = 0; i < getDescription().length(); i += 100) {
			int length = Math.min(i+100, getDescription().length());
			formattedDescription += getDescription().substring(i, length) + "\n";
		}
			
		return String.format("%s\n%16s%16s", formattedDescription, getLocation(), getStatus());
	}
	
	public String toSearch() {
		return String.format("%-%16s", getCategory());
	}

	/**
	 * Load the data from the database
	 */
	@SuppressWarnings("unchecked")
	public static void load()
	{
		// Let's deserialize an Object
		try {
			FileInputStream fileIn = new FileInputStream(catalogedBookTable);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			catalogedBooks = (ArrayList<CatalogedBook>) in.readObject();
			//System.out.println("INFO: CatalogedBooks Deserialization Successful... Members count: " + catalogedBooks.size());
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
			FileOutputStream fileOut = new FileOutputStream(catalogedBookTable);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(catalogedBooks);
			out.close();
			fileOut.close();
			//System.out.println("INFO: CatalogedBooks Serialization Successful... Checkout file: " + catalogedBookTable);
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
		catalogedBooks.clear();
		catalogedBooks.add(new CatalogedBook("9780321767530","100 Things Every Designer Needs to Know about People","Weinschenk, Susan M.","We design to elicit responses from people. We want them to buy something, read more, or take action of some kind. Designing without understanding what makes people act the way they do is like exploring a new city without a map: results will be haphazard, confusing, and inefficient. This book combines real science and research with practical examples to deliver a guide every designer needs. With it you'll be able to design more intuitive and engaging work for print, websites, applications, and products that matches the way people think, work, and play.","Design","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780932633675","Adrenaline Junkies and Template Zombies: Understanding Patterns of Project Behavior","DeMarco, Tom","Adrenaline junkies, dead fish, project sluts, true believers, Lewis and Clark, template zombies . . .Most developers, testers, and managers on IT projects are pretty good at recognizing patterns of behavior and gut-level hunches, as in, I sense that this project is headed for disaster.","Project Management","Kutztown Community","available"));
		catalogedBooks.add(new CatalogedBook("9780932633491","An Introduction to General Systems Thinking","Weinberg, Gerald M.","For more than twenty-five years, An Introduction to General Systems Thinking has been hailed as an innovative introduction to systems theory, with applications in computer science and beyond. Used in university courses and professional seminars all over the world, the text has proven its ability to open minds and sharpen thinking.","Design","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780932633163","Are Your Lights On?: How to Figure Out What the Problem Really is","Gause, Donald C.","Written with obvious and playful humour, a short book on problem solving. \"A problem is a difference between things as desired, and things as perceived\" - and this book goes on to arm you with a number of problem solving techniques, from the venerable \"ignore the problem\" to understanding just what the problem is, whether the solution is desirable, whether people will believe you've solved the problem, who's problem it actually is, and defining problems well.","Design","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780932633026","Becoming a Technical Leader: An Organic Problem-Solving Approach","Weinberg, Gerald M.","Becoming a Technical Leader is a personalized guide to developing the qualities that make a successful problem-solving leader. The book emphasizes that we all contain the ingredients for leadership, though some elements are better developed than others. \"Anyone can improve as a leader simply by building the strength of our weakest elements, \" author Gerald M. Weinberg writes. ","Project Management","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780316010665","Blink: The Power of Thinking Without Thinking","Gladwell, Malcolm","Drawing on cutting-edge neuroscience and psychology and displaying all of the brilliance that made�The Tipping Point�a classic,�Blink�changes the way you'll understand every decision you make. Never again will you think about thinking the same way.","Business","Kutztown Community","available"));
		catalogedBooks.add(new CatalogedBook("9780060566104","Built to Last: Successful Habits of Visionary Companies","Collins, James C.","This is not a book about charismatic visionary leaders. It is not about visionary product concepts or visionary products or visionary market insights. Nor is it about just having a corporate vision. This is a book about something far more important, enduring, and substantial. This is a book about visionary companies.","Business","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780131717114","Controlling Software Projects: Management, Measurement, and Estimates","DeMarco, Tom","Controlling Software Projects shows managers how to organize software projects so they are objectively measurable, and prescribes techniques for making early and accurate projections of time and cost to deliver.","Project Management","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780887307171","Crossing the Chasm","Moore, Geoffrey A.","Every year, companies gamble away millions of dollars and countless hours of technical talent on doomed efforts to market technology products that are greeted with enthusiasm by a few technologically literate consumers but ultimately fizzle in the wider marketplace.","Business","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780321344755","Don't Make Me Think: A Common Sense Approach to Web Usability","Krug, Steve","Five years and more than 100,000 copies after it was first published, it's hard to imagine anyone working in Web design who hasn't read Steve Krug's \"instant classic\" on Web usability, but people are still discovering it every day.","Design","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780932633132","Exploring Requirements: Quality Before Design","Gause, Donald C.","Partial ContentsPart 1: Negotiating a Common Understanding1. Methodologies Aren't Enough2. Ambiguity in Stating Requirements3. Sources of Ambiguity4. The Tried but Untrue Use of Direct QuestionsPart II: Ways to Get Started5. Starting Points6. Context-Free Questions7. Getting the Right People Involved8. Making Meetings Work for Everybody9. Reducing Ambiguity","Design","Kutztown Community","available"));
		catalogedBooks.add(new CatalogedBook("9780061234002","Freakonomics: A Rogue Economist Explores the Hidden Side of Everything","Levitt, Steven D.","Which is more dangerous, a gun or a swimming pool? What do schoolteachers and sumo wrestlers have in common? Why do drug dealers still live with their moms? How much do parents really matter? What kind of impact did Roe v. Wade have on violent crime? Freakonomics will literally redefine the way we view the modern world.","Business","Kutztown Community","available"));
		catalogedBooks.add(new CatalogedBook("9780201699463","Fundamentals of Object-Oriented Design in UML","Page-Jones, Meilir","Object technology is increasingly recognized as a valuable tool in application development, but what is not yet recognized is the importance of design in the construction of robust and adaptable object-oriented (OO) applications. With the recent introduction and widespread adoption of the Unified Modeling Language (UML), programmers are now equipped with a powerful tool for expressing software designs.","Design","Kutztown Community","repair"));
		catalogedBooks.add(new CatalogedBook("9780932633071","General Principles of Systems Design","Weinberg, Gerald M.","Partial Contents1: The Problem of PersistenceWeinberg's Law(s) of Twins - The General Systems Approach to Continuity2: AggregatesBirths and Deaths--The Fundamental Aggregate Equation3: Birth-Free AggregatesSocial Versus Innate Survival - Exponential Decay - Unimodal Life Tables, and Ogives4: Reasoning About AggregatesCooperation and Competition","Design","Kutztown Community","available"));
		catalogedBooks.add(new CatalogedBook("9781400209606","Girl, Stop Apologizing: A Shame-Free Plan for Embracing and Achieving Your Goals","Hollis, Rachel","Rachel Hollis has seen it too often: women not living into their full potential. They feel a tugging on their hearts for something more, but they�re afraid of embarrassment, of falling short of perfection, of not being enough.","Business","Kutztown Community","available"));
		catalogedBooks.add(new CatalogedBook("9780066620992","Good to Great: Why Some Companies Make the Leap... and Others Don't","Collins, James C.","To find the keys to greatness, Collins's 21-person research team read and coded 6,000 articles, generated more than 2,000 pages of interview transcripts and created 384 megabytes of computer data in a five-year project. The findings will surprise many readers and, quite frankly, upset others.","Business","Kutztown Community","available"));
		catalogedBooks.add(new CatalogedBook("9780066620732","Just for Fun: The Story of an Accidental Revolutionary","Torvalds, Linus","Once upon a time Linus Torvalds was a skinny unknown, just another nerdy Helsinki techie who had been fooling around with computers since childhood. Then he wrote a groundbreaking operating system and distributed it via the Internet -- for free. Today Torvalds is an international folk hero. And his creation LINUX is used by over 12 million people as well as by companies such as IBM.","Project Management","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9781400064281","Made to Stick: Why Some Ideas Survive and Others Die","Heath, Chip","The instant classic about why some ideas thrive, why others die, and how to improve your idea's chances--essential reading in the \"fake news\" era.","Business","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780932633521","More Secrets of Consulting: The Consultant's Tool Kit","Weinberg, Gerald M.","Widely acclaimed as a consultant's consultant, Gerald M. Weinberg builds on his perennial best-seller The Secrets of Consulting with all-new laws, rules, and principles. You'll learn how to fight burnout, stay curious, understand your clients, negotiate effectively, and much, much more.Consultants need more than technical skills -- they need self-awareness and a strong set of personal abilities. Weinberg helps computer consultants identify and strengthen each aspect of their performance using a \"consultant's tool kit\" of seventeen memorable symbols. ","Business","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780932633439","Peopleware: Productive Projects and Teams","DeMarco, Tom","Two of the computer industry's most popular authors and lecturers return with a new edition of the software management book that started a revolution.With humor and wisdom drawn from years of management and consulting experience, DeMarco and Lister demonstrate that the major issues of software development are human, not technical -- and that managers ignore them at their peril.","Project Management","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780932633699","Perfect Software--And Other Illusions about Testing","Weinberg, Gerald M.","This book should be mandatory reading for all software development professionals, particularly managers. Gerry has a great way of communicating the value of testing, the pitfalls of common approaches to development and test management, as well as what testers should be focusing on when it comes to communication.","Design","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780136907695","Practical Guide to Structured Systems Design","Page-Jones, Meilir","This practical guide takes the theoretical concepts of structured design and makes them applicable to real-world software development. It also integrates the approach of structured analysis with that of structured design. The book also gives a brief outline of the tools of structured analysis and shows how these tools are an asset not only to the analyst, but also to the designer of a computer system.","Design","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780932633002","Practical Project Management: Restoring Quality To Dp Projects And Systems","Page-Jones, Meilir","Practical Project Management is not just another management book, promising to divulge the latest secrets to successful project management. Nor is it merely a catalog of perennial woes and bad practices. Rather, it is a book full of fresh insights on what makes organizations effective, on how a project might be doomed to failure before it even starts, on what a manager can do to prevent disaster, on what managers need to understand in order to carry out their duties in a constantly changing environment, and on ways that a manager can motivate project members and users to achieve positive results.","Project Management","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9781591840213","Purple Cow: Transform Your Business by Being Remarkable","Godin, Seth","What do Starbucks and JetBlue and KrispyKreme and Apple and DutchBoy and Kensington and Zespri and Hard Candy have that you don't? How do they continue to confound critics and achieve spectacular growth, leaving behind former tried-and true brands to gasp their last?","Business","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780446520942","Selling the Invisible: A Field Guide to Modern Marketing","Beckwith, Harry","A treasury of hundreds of quick, practical, and easy-to-read strategies - few are more than a page long - Selling the Invisible will open your eyes to new ideas in this crucial branch of marketing including why focus groups, value-price positioning, discount pricing, and being the best usually fail; the critical emotion that most influences your prospects - and how to deal with it; the vital role of vividness, focus, \"anchors, \" and stereotypes; the importance of Halo, Cocktail Party, and Lake Wobegon Effects.","Business","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9781501121746","Sprint: How to Solve Big Problems and Test New Ideas in Just Five Days","Knapp, Jake","While working at Google, designer Jake Knapp created a unique problem-solving method that he coined a �design sprint��a five-day process to help companies answer crucial questions. His �sprints� were used on everything from Google Search to Chrome to Google X. When he moved to Google Ventures, he joined Braden Kowitz and John Zeratsky, both designers and partners there who worked on products like YouTube and Gmail. Together Knapp, Zeratsky, and Kowitz have run over 100 sprints with their portfolio companies. They�ve seen firsthand how sprints can overcome challenges in all kinds of companies: healthcare, fitness, finance, retailers, and more.","Design","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9781591842804","Start with Why: How Great Leaders Inspire Everyone to Take Action","Sinek, Simon","Why are some people and organizations more innovative, more influential, and more profitable than others? Why do some command greater loyalty from customers and employees alike? Even among the successful, why are so few able to repeat their success over and over?","Project Management","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780932633392","The Deadline: A Novel about Project Management","DeMarco, Tom","A pretty interesting read that brings across 101 ideas that Mr DeMarco has found in his many years of helping make projects operate better.\n\nWhile the story won't win any prizes, the ideas covered are quite topical and well described. If you are looking for an occasionally amusing, easily digestible way to take in some key concepts of software program management, than this is the book for you.","Project Management","Rohrbach","repair"));
		catalogedBooks.add(new CatalogedBook("9780465067107","The Design of Everyday Things","Norman, Donald A.","Anyone who designs anything to be used by humans -- from physical objects to computer programs to conceptual tools -- must read this book, and it is an equally tremendous read for anyone who has to use anything created by another human. It could forever change how you experience and interact with your physical surroundings, open your eyes to the perversity of bad design and the desirability of good design, and raise your expectations about how things should be designed.","Design","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780887307287","The E-Myth Revisited: Why Most Small Businesses Don't Work and What to Do About It","Gerber, Michael E.","E-Myth \\ 'e-,'mith\\ n 1: the entrepreneurial myth: the myth that most people who start small businesses are entrepreneurs 2: the fatal assumption that an individual who understands the technical work of a business can successfully run a business that does that technical work","Business","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780307887894","The Lean Startup: How Today's Entrepreneurs Use Continuous Innovation to Create Radically Successful Businesses","Ries, Eric","Most startups fail. But many of those failures are preventable. The Lean Startup is a new approach being adopted across the globe, changing the way companies are built and new products are launched.","Business","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780201835953","The Mythical Man-Month: Essays on Software Engineering","Brooks Jr., Frederick P.","Few books on software project management have been as influential and timeless as The Mythical Man-Month. With a blend of software engineering facts and thought-provoking opinions, Fred Brooks offers insight for anyone managing complex projects. These essays draw from his experience as project manager for the IBM","Project Management","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780688014292","The One Minute Manager","Blanchard, Kenneth H.","For more than twenty years, millions of managers in Fortune 500 companies and small businesses nationwide have followed The One Minute Manager's techniques, thus increasing their productivity, job satisfaction, and personal prosperity. These very real results were achieved through learning the management techniques that spell profitability for the organization and its employees.","Project Management","Hamburg Community","repair"));
		catalogedBooks.add(new CatalogedBook("9780932633422","The Psychology of Computer Programming","Weinberg, Gerald M.","This landmark 1971 classic is reprinted with a new preface, chapter-by-chapter commentary, and straight-from-the-heart observations on topics that affect the professional life of programmers.Long regarded as one of the first books to pioneer a people-oriented approach to computing, The Psychology of Computer Programming endures as a penetrating analysis of the intelligence, skill, teamwork, and problem-solving power of the computer programmer.","Project Management","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780932633019","The Secrets of Consulting: A Guide to Giving and Getting Advice Successfully","Weinberg, Gerald M.","If you are a consultant, ever use one, or want to be one, this book will show you how to succeed.With wit, charm, humor, and wisdom, Gerald M. Weinberg shows you exactly how to become a more effective consultant. He reveals specific techniques and strategies that really work.","Project Management","Rohrbach","available"));
		catalogedBooks.add(new CatalogedBook("9780316346627","The Tipping Point: How Little Things Can Make a Big Difference","Gladwell, Malcolm","The tipping point is that magic moment when an idea, trend, or social behavior crosses a threshold, tips, and spreads like wildfire. Just as a single sick person can start an epidemic of the flu, so too can a small but precisely targeted push cause a fashion trend, the popularity of a new product, or a drop in the crime rate.�","Business","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780932633606","Waltzing with Bears: Managing Risk on Software Projects","DeMarco, Tom","The authors, consultants in risk and management, show how to identify and embrace worthwhile risks in software development and offer strategies for common risks that software projects face, such as schedule flaws, requirements inflation, and specification breakdown.","Project Management","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780091883768","Who Moved My Cheese?","Johnson, Spencer","It is the amusing and enlightening story of four characters who live in a maze and look for cheese to nourish them and make them happy. Cheese is a metaphor for what you want to have in life, for example a good job, a loving relationship, money or possessions, health or spiritual peace of mind.","Project Management","Hamburg Community","available"));
		catalogedBooks.add(new CatalogedBook("9780932633347","Why Does Software Cost So Much?: And Other Puzzles of the Information Age","DeMarco, Tom","Known for his ability to find provocative answers to the most puzzling questions of software development, Peopleware coauthor Tom DeMarco explores a wide range of issues in twenty-four masterful essays. The offerings range from the wise to the kooky -- in fact, many of them defy categorization. But all are marked by the author's eye-opening perspectives on topics that demand your professional attention.","Project Management","Kutztown Community","available"));

		// All done
		return;
	}
}
