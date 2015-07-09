import java.util.HashMap;
import java.util.ArrayList;

public class TalismanList {

	private HashMap<String, ArrayList<Talisman>> primary;
	private HashMap<String, ArrayList<Talisman>> secondary;
	private ArrayList<Talisman> deleteList;
	
	public TalismanList()
	{
		super();
		primary = new HashMap<String, ArrayList<Talisman>>();
		secondary = new HashMap<String, ArrayList<Talisman>>();
		deleteList = new ArrayList<Talisman>();
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
			//this.addToPrimary(thisTalisman);
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
		System.out.println("insertSingle");
		
		//check if thisTalisman.skill_1 is in the HashMaps
		ArrayList<Talisman> checkListPrimary = primary.get(thisTalisman.getSkill_1());
		ArrayList<Talisman> checkListSecondary = secondary.get(thisTalisman.getSkill_1());
		
		if(checkListPrimary != null)
		{
			System.out.println("not null");
			this.iterateList(thisTalisman, checkListPrimary);
			
			// iterateList Primary
			
			//if result is -1 then delete
			//if result is 0 or 1 then move on
			
			//if(checkListSecondary != null && result != -1)
			//{
				// iterateList Secondary
				
				//if result is -1 then delete
				//if result is 1 then move on
				//if result is 0 then move on
			//}
			
		}
		if(checkListPrimary == null && checkListSecondary == null)
		{
			//create ArrayList and add talisman to listPrimary
			
			//Add ArrayList to HashMap with skill_1 as key
		}
		
		
		
	}
	
	/*
	 * Iterate list of thisList and compare thisTalisman to entries of thisList
	 * @param thisTalisman current talisman that is going to be inserted
	 * @param thisList ArrayList of talismans
	 * @return -1,0,1 -1 to delete this talisman, 0 to keep both, 1 to delete compTalisman
	 */
	public int iterateList(Talisman thisTalisman, ArrayList<Talisman> thisList) //CHANGE TO PRIVATE LATER
	{
		
		int size = thisList.size();
		
		int result = 0;
		
		for(int i = 0; i < size; i++)
		{
			Talisman tempTalisman = thisList.get(i);
			System.out.println(tempTalisman);
			
			int compareResult = thisTalisman.compare(tempTalisman);
			
			if(compareResult == -1)
			{
				result = compareResult;
				deleteList.clear();
				return result;
			}
			else if(compareResult == 1)
			{
				result = compareResult;
				deleteList.add(tempTalisman);
			}
			
		}
		
		return result;
	}
	
	/*
	 * Add Talisman to primary list
	 * @param thisTalisman the talisman going to be added to primary list
	 */
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
	
	/*
	 * Add thisTalisman to secondary list
	 * @param thisTalisman the talisman that is going to be added to secondary list
	 */
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
