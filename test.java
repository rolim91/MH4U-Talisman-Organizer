
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
		testSkills(secondarySkill);
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
		
		Talisman talisman1 = new Talisman("Amplify", null, 4, 0, 2, 0);
		Talisman talisman2 = new Talisman("Amplify", null, 10, 0, 2, 0);
		Talisman talisman3 = new Talisman("Anti-Theft", null, 7, 0, 2, 0);
		Talisman talisman4 = new Talisman("Anti-Theft", "Amplify", 7, 5, 2, 0);
		
		
		TalismanList list1 = new TalismanList(); 
		
		
		list1.addTalisman(talisman1);
		list1.addTalisman(talisman2);
		list1.addTalisman(talisman3);
		list1.addTalisman(talisman4);
	}
	
}
