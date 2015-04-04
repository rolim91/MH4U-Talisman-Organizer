import java.util.*;
import java.io.*;


public class Utils {

	
	public static void populateSkillList(String location, ArrayList<Skill> primarySkill, ArrayList<Skill> secondarySkill) throws IOException
	{
		//System.out.println(location);
		Scanner lineScanner = new Scanner(new File(location));
		
		while(lineScanner.hasNextLine())
		{
			//This populates primary
			String primaryLine = lineScanner.nextLine();
			String skillName = populatePrimary(primaryLine, primarySkill);
			
			//This populate secondary
			String secondaryLine = lineScanner.nextLine();
			populateSecondary(secondaryLine, secondarySkill, skillName);
		}
		
		lineScanner.close();
	}
	
	
	private static String populatePrimary(String line, ArrayList<Skill> primarySkill)
	{
		int index = 0;
		
		String skillName = null;
		int min = 0;
		int max = 0;
		
		Scanner wordScanner = new Scanner(line);
		while(wordScanner.hasNext())
		{
			String word = wordScanner.next();
			
			if (!word.equals("-"))
			{
				if(index == 0)
					skillName = word;
				else if(index == 1)
					min = Integer.parseInt(word);
				else if(index == 2)
					max = Integer.parseInt(word);
			}
			else
				break;
			
			index++;
		}
		wordScanner.close();
		
		primarySkill.add(new Skill(skillName, min, max));
		
		return skillName;
	}
	
	private static void populateSecondary(String line, ArrayList<Skill> secondarySkill, String skillName)
	{
		//method vars
		boolean secondary = false;
		int index = 0;
		
		//named variables
		int min = 0;
		int max = 0;
		
		//This populates secondary
		Scanner wordScanner = new Scanner(line);
		while(wordScanner.hasNext())
		{
			String word = wordScanner.next();
			
			if (!word.equals("-"))
			{
				secondary = true;
				if(index == 0)
					min = Integer.parseInt(word);
				else if(index == 1)
					max = Integer.parseInt(word);
			}
			else
				break;
			
			index++;
		}
		wordScanner.close();
		
		if (secondary)
			secondarySkill.add(new Skill(skillName, min, max));
		
		return;
	}
	
}
