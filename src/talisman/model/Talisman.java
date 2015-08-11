package talisman.model;

/**
 * Talisman.java
 *
 * Name: rolim91
 *
 * Description: Talisman.java is the container for the talismans inputted by the user
 * 
 * Features:
 * 				Create a talisman to be used by the program
 * 
 * BUGS: NONE
 *
 *
 * Versions: 	1.0 - 
 *
 * rolim91 
 */

public class Talisman {
	
	private int id;
	private String skill_1;
	private String skill_2;
	private int skill1_Value;
	private int skill2_Value;
	private int slots;
	private int type;	//1 for  single, 2 for double
	private int rarity;
	
	public Talisman() {
		super();
		this.id = 0;
		this.skill_1 = "skill_1";
		this.skill_2 = "skill_2";
		this.skill1_Value = 0;
		this.skill2_Value = 0;
		this.slots = 0;
		this.rarity = 0;
		this.type = 0;
	}

	public Talisman(String skill_1, String skill_2, int skill1_Value, int skill2_Value, int slots, int rarity) {
		super();
		
		this.skill_1 = skill_1;
		this.skill_2 = skill_2;
		this.skill1_Value = skill1_Value;
		this.skill2_Value = skill2_Value;
		this.slots = slots;
		this.rarity = rarity;
		this.type = 1;
		
		if(!skill_2.equals("--"))
			this.type = 2;
		
	}
	
	public Talisman(int id, String skill_1, String skill_2, int skill1_Value, int skill2_Value, int slots, int rarity) {
		super();
		
		this.id = id;
		this.skill_1 = skill_1;
		this.skill_2 = skill_2;
		this.skill1_Value = skill1_Value;
		this.skill2_Value = skill2_Value;
		this.slots = slots;
		this.rarity = rarity;
		this.type = 1;
		
		if(!skill_2.equals("--"))
			this.type = 2;
		
	}
	
	
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the skill_1
	 */
	public String getSkill_1() {
		return skill_1;
	}

	/**
	 * @return the skill_2
	 */
	public String getSkill_2() {
		return skill_2;
	}

	/**
	 * @return the skill1_Value
	 */
	public int getSkill1_Value() {
		return skill1_Value;
	}

	/**
	 * @return the skill2_Value
	 */
	public int getSkill2_Value() {
		return skill2_Value;
	}

	/**
	 * @return the slots
	 */
	public int getSlots() {
		return slots;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the rarity
	 */
	public int getRarity() {
		return rarity;
	}

	@Override
	public String toString() {
		return "Talisman [id=" + id + ", skill_1=" + skill_1 + ", skill_2=" + skill_2 + ", skill1_Value=" + skill1_Value
				+ ", skill2_Value=" + skill2_Value + ", slots=" + slots + ", type=" + type + ", rarity=" + rarity + "]";
	}

	public int compare(Talisman compTalisman)
	{
		
		
		
		if(this.type == 1 && compTalisman.getType() == 1)
			return this.singleToSingle(compTalisman);
		else if(this.type == 1 && compTalisman.getType() == 2)
			return this.singleToDouble(compTalisman);
		else if(this.type == 2 && compTalisman.getType() == 1)
			return this.doubleToSingle(compTalisman);
		else if(this.type == 2 && compTalisman.getType() == 2)
			return this.doubleToDouble(compTalisman);
			
		
		
		return 0;
	}
	
	/**
	 * Check if object instance is negative
	 * 
	 * @return true if talisman is both skills are negative for double or the skill is negative for single
	 * @return false o.w.
	 */
	public boolean checkNegative()
	{
		if(this.type == 1)
		{
			if(this.skill1_Value < 0)
				return true;
		}
		else if(this.type == 2)
		{
			if(this.skill1_Value < 0 && this.skill2_Value < 0)
				return true;
		}
		
		
		return false;
	}
	
	/**
	 * Compare single talisman to another single talisman
	 * 
	 * @param Talisman compTalisman talisman to be compared to
	 * @return -1,0,1 -1 to delete this talisman, 0 to keep both, 1 to delete compTalisman
	 */
	private int singleToSingle(Talisman compTalisman)
	{
		System.out.println("singleToSingle");
		
		if(this.slots < compTalisman.getSlots())
		{
			System.out.println("*myTalisman lesser");
			
			if(this.skill1_Value > compTalisman.getSkill1_Value())
			{
				System.out.println("**myTalisman skill greater");
				return 0;
			}
			else
			{
				System.out.println("**myTalisman skill lesser or equal");
				return -1;
			}
			
		}
		else if(this.slots == compTalisman.getSlots())
		{
			System.out.println("*myTalisman equal");
			
			if(this.skill1_Value >= compTalisman.getSkill1_Value())
			{
				System.out.println("**myTalisman skill equal or greater");
				return 1;
			}
			else
			{
				System.out.println("**myTalisman skill lesser");
				return -1;
			}
		}
		else //this.slots > compTalisman.getSlots()
		{
			System.out.println("*myTalisman greater");
			
			if(this.skill1_Value >= compTalisman.getSkill1_Value())
			{
				System.out.println("**myTalisman skill equal or greater");
				return 1;
			}
			else
			{
				System.out.println("**myTalisman skill lesser");
				return 0;
			}
		}
		
	}
	
	/**
	 * Compare single skilled Talisman to a double skilled Talisman
	 * 
	 * @param Talisman compTalisman talisman to be compared to
	 * @return -1,0,1 -1 to delete this talisman, 0 to keep both, 1 to delete compTalisman
	 */
	private int singleToDouble(Talisman compTalisman)
	{
		System.out.println("singleToDouble");
		
		if(compTalisman.getSkill2_Value() < 0)
		{
			System.out.println("compTalisman second is negative removing negative");
			int result = singleToSingle(compTalisman);
			return (result <= 0 ? 0 : result);
		}
		
		Talisman tempTalisman;
		
		if(this.skill_1.equals(compTalisman.getSkill_1()))
			tempTalisman = compTalisman;
		else
			tempTalisman = swapSkills(compTalisman);
		
		
		if(this.slots > compTalisman.getSlots())
			return 0;
		else
			return (this.skill1_Value <= tempTalisman.getSkill1_Value() ? -1 : 0);
	}
	
	/**
	 * Swap Talismans that swaps the primary and secondary skills of the given talisman
	 * 
	 * @param currTalisman Talisman the Talisman that needs to be swapped
	 * @return Talisman with swapped skills (includes values)
	 */
	private Talisman swapSkills(Talisman currTalisman)
	{
		Talisman temp = new Talisman(	currTalisman.getSkill_2(), currTalisman.getSkill_1(), 
										currTalisman.getSkill2_Value(), currTalisman.getSkill1_Value(), 
										currTalisman.getSlots(), currTalisman.getRarity()	);
		
		
		return temp;
	}
	
	/**
	 * Compare double skilled Talisman to a single skilled Talisman
	 * 
	 * @param Talisman compTalisman talisman to be compared to
	 * @return -1,0,1 -1 to delete this talisman, 0 to keep both, 1 to delete compTalisman
	 */
	private int doubleToSingle(Talisman compTalisman)
	{
		System.out.println("doubleToSingle");
		return -1 * compTalisman.singleToDouble(this);
	}
	
	/**
	 * Compare double skilled Talisman to another double skilled Talisman
	 * 
	 * @param Talisman compTalisman talisman to be compared to
	 * @return -1,0,1 -1 to delete this talisman, 0 to keep both, 1 to delete compTalisman
	 */
	private int doubleToDouble(Talisman compTalisman)
	{
		System.out.println("doubleToDouble");
		
		Talisman tempTalisman;
		
		if(this.getSkill_1().equals(compTalisman.getSkill_1()))
			tempTalisman = compTalisman;
		else
			tempTalisman = swapSkills(compTalisman);
		
		//Both tempTalisman and thisTalisman have the same skills
		if(this.getSkill_1().equals(tempTalisman.getSkill_1()) && this.getSkill_2().equals(tempTalisman.getSkill_2()))
		{
			System.out.println("*Have the same skill");
			
			if(this.getSlots() < tempTalisman.getSlots())
			{
				System.out.println("**myTalisman lesser");
				if(this.getSkill1_Value() > tempTalisman.getSkill1_Value() || this.getSkill2_Value() > tempTalisman.getSkill2_Value())
				{
					System.out.println("***myTalisman skill one or both greater");
					return 0;
				}
				else
				{
					System.out.println("**myTalisman both skills are lesser or equal");
					return -1;
				}
				
			}
			else if(this.getSlots() == tempTalisman.getSlots())
			{
				
				if(this.getSkill1_Value() > tempTalisman.getSkill1_Value() && this.getSkill2_Value() < tempTalisman.getSkill2_Value())
				{
					return 0;
				}
				else if(this.getSkill1_Value() < tempTalisman.getSkill1_Value() && this.getSkill2_Value() > tempTalisman.getSkill2_Value())
				{
					return 0;
				}
				else if(this.getSkill1_Value() > tempTalisman.getSkill1_Value() || this.getSkill2_Value() > tempTalisman.getSkill2_Value())
				{
					return 1;
				}
				else
				{
					return -1;
				}
				
			}
			else
			{
				
			}
		}
		
		return 0;
	}

	
}
