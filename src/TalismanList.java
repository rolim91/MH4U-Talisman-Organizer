import java.util.HashMap;
import java.util.ArrayList;

public class TalismanList {

	private HashMap<String, ArrayList<Talisman>> primary;
	private HashMap<String, ArrayList<Talisman>> secondary;
	
	public TalismanList()
	{
		super();
		primary = new HashMap<String, ArrayList<Talisman>>();
		secondary = new HashMap<String, ArrayList<Talisman>>();
	}
	

	/*
	 * Add Talisman to ArrayList.
	 */
	public void addTalisman(Talisman thisTalisman)
	{
		//single
		if(thisTalisman.getType() == 1)
		{
			//add to primary only
			this.insertSingle(thisTalisman);
			this.addToPrimary(thisTalisman);
		}
		//double
		else if(thisTalisman.getType() == 2)
		{
			//add to both
			this.addToPrimary(thisTalisman);
			this.addToSecondary(thisTalisman);
		}
			
	}
	
	/*
	 * Check to see if the talisman can be inserted()
	 */
	private void insertSingle(Talisman thisTalisman)
	{
		System.out.println("inserSingle");
		
		//check if thisTalisman.skill_1 is in the HashMaps
		ArrayList<Talisman> checkListPrimary = primary.get(thisTalisman.getSkill_1());
		ArrayList<Talisman> checkListSecondary = secondary.get(thisTalisman.getSkill_1());
		
		if(checkListPrimary != null)
		{
			System.out.println("not null");
			this.iterateListSingle(thisTalisman, checkListPrimary);
		}
		
		
		
	}
	
	private void iterateListSingle(Talisman thisTalisman, ArrayList<Talisman> thisList)
	{
		
		int size = thisList.size();
		
		for(int i = 0; i < size; i++)
		{
			Talisman tempTalisman = thisList.get(i);
			System.out.println(tempTalisman);
			
			//Single
			if(tempTalisman.getType() == 1)
			{
				
			}
			//Double
			else if(tempTalisman.getType() == 2)
			{
				
			}
		}
	}
	
	private void addToPrimary(Talisman thisTalisman)
	{
		String key = thisTalisman.getSkill_1();
		//System.out.println("In Primary, adding " + key);
		
		ArrayList<Talisman> tempArrayList = (ArrayList<Talisman>)primary.get(key);
		
		if(tempArrayList == null)
			tempArrayList = new ArrayList<Talisman>();
		
		//Add talisman to ArrayList
		tempArrayList.add(thisTalisman);
		
		//Put the arrayList to map with key, arraylist pair
		this.primary.put(key, tempArrayList);
		
		//System.out.println(tempArrayList);
	}
	
	private void addToSecondary(Talisman thisTalisman)
	{
		String key = thisTalisman.getSkill_2();
		//System.out.println("In Secondary, adding " + key);
		
		ArrayList<Talisman> tempArrayList = (ArrayList<Talisman>)secondary.get(key);
		
		if(tempArrayList == null)
			tempArrayList = new ArrayList<Talisman>();
		
		//Add talisman to ArrayList
		tempArrayList.add(thisTalisman);
		
		//Put the arrayList to map with key, arraylist pair
		this.secondary.put(key, tempArrayList);
		
		//System.out.println(tempArrayList);
	}
}
