import java.util.*;
import java.io.*;


public class Utils {

	
	public static void populateSkillList(String location, ArrayList<Skill> primarySkill, ArrayList<Skill> secondarySkill) throws IOException
	{
		
		System.out.println(location);
		
		Scanner lineScanner = new Scanner(new File(location));
		
		while(lineScanner.hasNextLine())
		{
			int index = 0;
			
			String skillName = null;
			int min = 0;
			int max = 0;
			
			//This populates primary
			Scanner wordScanner = new Scanner(lineScanner.nextLine());
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
			
			
			//This populates secondary
			wordScanner = new Scanner(lineScanner.nextLine());
			while(wordScanner.hasNext())
			{
				String word = wordScanner.next();
				
				if (!word.equals("-"))
				{
					if(index == 3)
						min = Integer.parseInt(word);
					else if(index == 4)
						max = Integer.parseInt(word);
				}
				else
					break;
				
				index++;
			}
			wordScanner.close();
			
			secondarySkill.add(new Skill(skillName, min, max));
		}
		
		lineScanner.close();
	}
	
}
