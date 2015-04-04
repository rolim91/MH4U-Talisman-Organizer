
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

}
