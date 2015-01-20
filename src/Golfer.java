/**NAME: Alex Adusei
 * DATE: Monday, June 4, 2012
 * COURSE CODE: ICS4U1
 * PROGRAM: Golfer Class *Run via Golf Tournament Program*.
 */

public class Golfer implements Comparable<Golfer> // Allows class to include a functionality that sorts data
{

	//Declare fields
	private String name, gender;

	public static final int SORT_BY_NAME = 0;
	public static final int SORT_BY_GENDER = 1;
	public static int sort;

	//Declare no args constructor
	public Golfer()
	{
		this.name = "Celebrity";
		this.gender = "M";
	}

	//Declare overloaded constructor(s)
	public Golfer(String name, String gender)
	{
		this.name = name;
		this.gender = gender;
	}

	//Declare Accecssor methods
	public String getName()
	{
		return this.name;
	}

	public String getGender()
	{
		return this.gender;
	}

	//Declare Mutator Methods
	public void setName(String name)
	{
		this.name = name;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	//Formats the data in the class
	public String toString()
	{
		return String.format("%-5s%-25s%-5s", "", this.name, this.gender);
	}

	/*compareTo method sorts data according to what user asks for (sorted by names or by gender). If the user chooses by names, the
	 * program will sort the data according to names in ascending, alphabetical order. If the user picks gender, the program will sort the
	 * data by gender. If the same gender occurs, then by alphabetical name order.
	 */
	public int compareTo(Golfer g)
	{
		if (PlayerList.choice.equals("Gender"))
		{
			int comparison = gender.compareTo(g.getGender());

			if (comparison == 0)
			{
				int otherComp = name.compareTo(g.getName());
				return otherComp;
			}
			
			return comparison;
		}
		else
		{
			int comparison = name.compareTo(g.getName());
			
			return comparison;
		}
	}
}