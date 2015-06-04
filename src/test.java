
import java.util.ArrayList;
import java.io.IOException;

public class test {
	
	private static String skillListLocation = "bin/resources/skill.txt";
	private static ArrayList<Skill> primarySkill;
	private static ArrayList<Skill> secondarySkill;

	public static void main(String[] args) {
		
		init();
		
	}
	
	
	/*
	 * Initialize Variables
	 * 
	 * 
	 */
	public static void init()
	{
		initSkills();
		//testSkills(primarySkill);
		//testSkills(secondarySkill);
		testTalismanList();
	}
	
	
	/*
	 * Initialize List of primary and secondary skills
	 * 
	 * returns nothing
	 */
	public static void initSkills()
	{
		
		primarySkill = new ArrayList<Skill>();
		secondarySkill = new ArrayList<Skill>();
		
		try{
			Utils.populateSkillList(skillListLocation, primarySkill, secondarySkill);
		} catch (IOException e) {
			System.out.println("File not found");
			System.exit(0);
		}  
		
		
		//System.out.println(primarySkill.get(0));
	}
	
	public static void testSkills(ArrayList<Skill> thisSkill)
	{
		
		for(int i = 0; i < thisSkill.size(); i++)
		{
			System.out.println(thisSkill.get(i));
		}
		
	}

	
	public static void testTalismanList()
	{
		System.out.println("Test TalismanList\n");
		
		//Talisman talisman6 = new Talisman("Amplify", null, 4, 0, 2, 0);
		//Talisman talisman2 = new Talisman("Amplify", null, 10, 0, 2, 0);
		//Talisman talisman3 = new Talisman("Anti-Theft", null, 7, 0, 2, 0);
		//Talisman talisman4 = new Talisman("Anti-Theft", "Amplify", 7, 5, 2, 0);
		//Talisman talisman5 = new Talisman("Amplify", null, 4, 0, 2, 0);
		
		
		
		//Talisman lesser
		Talisman talisman1 = new Talisman("Amplify", null, 4, 0, 0, 0);
		Talisman talisman2 = new Talisman("Amplify", null, 4, 0, 1, 0);
		
		//skill equal
		talisman1.compare(talisman2);
		System.out.println();
		//skill greater
		Talisman talisman3 = new Talisman("Amplify", null, 2, 0, 1, 0);
		talisman1.compare(talisman3);	
		System.out.println();
		//skill lesser
		Talisman talisman4 = new Talisman("Amplify", null, 6, 0, 1, 0);
		talisman1.compare(talisman4);
		System.out.println();
		
		//Talisman equal
		Talisman talisman5 = new Talisman("Amplify", null, 4, 0, 0, 0);
		Talisman talisman6 = new Talisman("Amplify", null, 4, 0, 0, 0);
		
		//skill equal
		talisman5.compare(talisman6);
		System.out.println();
		//skill greater
		Talisman talisman7 = new Talisman("Amplify", null, 2, 0, 0, 0);
		talisman5.compare(talisman7);
		System.out.println();
		//skill lesser
		Talisman talisman8 = new Talisman("Amplify", null, 6, 0, 0, 0);
		talisman5.compare(talisman8);
		System.out.println();
		
		//Talisman greater
		Talisman talisman9 = new Talisman("Amplify", null, 4, 0, 1, 0);
		Talisman talisman10 = new Talisman("Amplify", null, 4, 0, 0, 0);
		
		//skill equal
		talisman9.compare(talisman10);
		System.out.println();
		//skill greater
		Talisman talisman11 = new Talisman("Amplify", null, 2, 0, 0, 0);
		talisman9.compare(talisman11);
		System.out.println();
		//skill lesser
		Talisman talisman12 = new Talisman("Amplify", null, 6, 0, 0, 0);
		talisman9.compare(talisman12);
		System.out.println();
		
		
		//Talisman compared double with negative
		Talisman talisman13 = new Talisman("Amplify", null, 6, 0, 0, 0);
		Talisman talisman14 = new Talisman("Amplify", "Bomb_Boost", 6, -1, 0, 0);
		talisman13.compare(talisman14);
		System.out.println();
		
		
		//Talisman equal
		//Talisman talisman3 = new Talisman("Amplify", null, 4, 0, 1, 0);
		//System.out.println(talisman2.compare(talisman3));
		
		
		//Talisman greater
		//System.out.println(talisman3.compare(talisman1));
		
		
		//TalismanList list1 = new TalismanList(); 
		
		
		//list1.addTalisman(talisman1);
		//list1.addTalisman(talisman2);
		//list1.addTalisman(talisman3);
		//list1.addTalisman(talisman4);
	}
	
}
