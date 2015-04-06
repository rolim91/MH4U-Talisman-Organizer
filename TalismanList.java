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
		if(thisTalisman.getType() == 1)
		{
			//add to primary only
			this.addToPrimary(thisTalisman);
		}
		else if(thisTalisman.getType() == 2)
		{
			//add to both
			this.addToPrimary(thisTalisman);
			this.addToSecondary(thisTalisman);
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
