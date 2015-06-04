
public class Talisman {
	
	private String skill_1;
	private String skill_2;
	private int skill1_Value;
	private int skill2_Value;
	private int slots;
	private int type;	//1 for  single, 2 for double
	private int rarity;
	
	public Talisman() {
		super();
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
		
		if(skill_2 != null)
			this.type = 2;
		
	}

	public String getSkill_1() {
		return skill_1;
	}

	public String getSkill_2() {
		return skill_2;
	}

	public int getSkill1_Value() {
		return skill1_Value;
	}

	public int getSkill2_Value() {
		return skill2_Value;
	}

	public int getSlots() {
		return slots;
	}

	public int getType() {
		return type;
	}

	public int getRarity() {
		return rarity;
	}

	
	@Override
	public String toString() {
		return "Talisman [skill_1=" + skill_1 + ", skill_2=" + skill_2
				+ ", skill1_Value=" + skill1_Value + ", skill2_Value="
				+ skill2_Value + ", slots=" + slots + ", type=" + type
				+ ", rarity=" + rarity + "]";
	}

	
	public int compare(Talisman compTalisman)
	{
		
		if(this.type == 1 && compTalisman.getType() == 1)
			this.singleToSingle(compTalisman);
		else if(this.type == 1 && compTalisman.getType() == 2)
			this.singleToDouble(compTalisman);
		else if(this.type == 2 && compTalisman.getType() == 1)
			this.doubleToSingle(compTalisman);
		else if(this.type == 2 && compTalisman.getType() == 2)
			this.doubleToDouble(compTalisman);
			
		
		
		return 0;
	}
	
	/*
	 * Compare single talisman to another single talisman
	 * 
	 * @param Talisman compTalisman talisman to be compared to
	 * @returns -1,0,1 -1 to delete this talisman, 0 to keep both, 1 to delete compTalisman
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
			
			if(this.skill1_Value > compTalisman.getSkill1_Value())
			{
				System.out.println("**myTalisman skill greater");
				return 1;
			}
			else
			{
				System.out.println("**myTalisman skill lesser or equal");
				return -1;
			}
		}
		else
		{
			System.out.println("*myTalisman greater");
			
			if(this.skill1_Value > compTalisman.getSkill1_Value())
			{
				System.out.println("**myTalisman skill greater");
				return 1;
			}
			else
			{
				System.out.println("**myTalisman skill lesser or equal");
				return 0;
			}
		}
		
	}
	
	
	private int singleToDouble(Talisman compTalisman)
	{
		System.out.println("singleToDouble");
		
		if(compTalisman.getSkill2_Value() < 0)
		{
			System.out.println("compTalisman second is negative");
			int result = singleToSingle(compTalisman);
			
			return (result < 0 ? 0 : result);
		}
		
		Talisman tempTalisman;
		
		if(this.skill_1.equals(compTalisman.getSkill_1()))
			tempTalisman = compTalisman;
		else
			tempTalisman = swapSkills(compTalisman);
		
		if(this.skill1_Value < tempTalisman.getSkill1_Value())
			return -1;
		
		return 0;
	}
	
	private Talisman swapSkills(Talisman currTalisman)
	{
		Talisman temp = new Talisman(	currTalisman.getSkill_2(), currTalisman.getSkill_1(), 
										currTalisman.getSkill2_Value(), currTalisman.getSkill1_Value(), 
										currTalisman.getSlots(), currTalisman.getRarity());
		
		
		return temp;
	}
	
	private int doubleToSingle(Talisman compTalisman)
	{
		System.out.println("doubleToSingle");
		
		return 0;
	}
	
	private int doubleToDouble(Talisman compTalisman)
	{
		System.out.println("doubleToDouble");
		
		return 0;
	}
	
}
